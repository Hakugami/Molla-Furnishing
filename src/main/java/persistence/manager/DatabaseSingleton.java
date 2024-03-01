package persistence.manager;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import utils.DataSourceConfig;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DatabaseSingleton {
    private static volatile DatabaseSingleton instance = null;
    private static volatile EntityManagerFactory entityManagerFactory = null;
    private static final ThreadLocal<EntityManager> threadLocalSession = new ThreadLocal<>();

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


    public EntityManager getEntityManager() {
        EntityManager entityManager = threadLocalSession.get();
        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
            threadLocalSession.set(entityManager);
        }
        return entityManager;
    }

    public void closeEntityManager() {
        EntityManager entityManager = threadLocalSession.get();
        if (entityManager != null) {
            entityManager.close();
            threadLocalSession.remove();
        }
    }

    public void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
}