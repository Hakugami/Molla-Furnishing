package persistence;

import jakarta.persistence.EntityManager;
import persistence.manager.DatabaseSingleton;

public class AutoCloseableEntityManager implements AutoCloseable {
    private final DatabaseSingleton databaseSingleton;
    private final EntityManager entityManager;

    public AutoCloseableEntityManager(DatabaseSingleton databaseSingleton, EntityManager entityManager) {
        this.databaseSingleton = databaseSingleton;
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void close() {
        databaseSingleton.closeEntityManager();
    }
}
