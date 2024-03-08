package persistence.manager;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import persistence.AutoCloseableEntityManager;
import utils.DataSourceConfig;

import java.util.HashMap;
import java.util.Map;

public class DatabaseSingleton {
    private static volatile DatabaseSingleton instance = null;
    private static volatile EntityManagerFactory entityManagerFactory = null;
    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_THREAD_LOCAL = new ThreadLocal<>();

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


    public AutoCloseableEntityManager getEntityManager() {
        EntityManager entityManager = ENTITY_MANAGER_THREAD_LOCAL.get();
        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
            ENTITY_MANAGER_THREAD_LOCAL.set(entityManager);
        }
        return new AutoCloseableEntityManager(this, entityManager);
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