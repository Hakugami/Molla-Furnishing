package persistence.manager;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import persistence.manager.helpers.AutoCloseableEntityManager;
import utils.DataSourceConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;

public class DatabaseSingleton {
    private static volatile DatabaseSingleton instance = null;
    private static volatile EntityManagerFactory entityManagerFactory = null;
    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_THREAD_LOCAL = new ThreadLocal<>();
    private final Logger logger = Logger.getLogger(DatabaseSingleton.class.getName());

    private DatabaseSingleton() {
        HikariDataSource dataSource = DataSourceConfig.getHikariDataSource();
        Map<String, Object> properties = new HashMap<>();
        properties.put("jakarta.persistence.nonJtaDataSource", dataSource);
        entityManagerFactory = Persistence.createEntityManagerFactory("molla", properties);
    }

    public static DatabaseSingleton getInstance() {
        if (instance == null) {
            synchronized (DatabaseSingleton.class) {
                if (instance == null) {
                    instance = new DatabaseSingleton();
                }
            }
        }
        return instance;
    }

    public void init(){}


    public AutoCloseableEntityManager getAutoClosableEntityManager() {
        EntityManager entityManager = ENTITY_MANAGER_THREAD_LOCAL.get();
        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
            ENTITY_MANAGER_THREAD_LOCAL.set(entityManager);
        }
        return new AutoCloseableEntityManager(this, entityManager);
    }

    public void doTransaction(Consumer<EntityManager> consumer) {
        try (AutoCloseableEntityManager autoCloseableEntityManager = getAutoClosableEntityManager()) {
            EntityManager entityManager = autoCloseableEntityManager.getEntityManager();
            entityManager.getTransaction().begin();
            consumer.accept(entityManager);
            entityManager.getTransaction().commit();
        }
    }

    public <T> T doTransactionWithResult(Function<EntityManager, T> consumer) {
        T result = null;
        try (AutoCloseableEntityManager autoCloseableEntityManager = getAutoClosableEntityManager()) {
            EntityManager entityManager = autoCloseableEntityManager.getEntityManager();
            entityManager.getTransaction().begin();
            result = consumer.apply(entityManager);
            entityManager.getTransaction().commit();
        }
        return result;
    }

    public void closeEntityManager() {
        EntityManager entityManager = ENTITY_MANAGER_THREAD_LOCAL.get();
        if (entityManager != null) {
            entityManager.close();
            ENTITY_MANAGER_THREAD_LOCAL.remove();
        }
    }

    public void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
}