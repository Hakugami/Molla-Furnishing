package persistence.manager.helpers;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import persistence.manager.DatabaseSingleton;

public class AutoCloseableEntityManager implements AutoCloseable {
    private final DatabaseSingleton databaseSingleton;
    @Getter
    private final EntityManager entityManager;

    public AutoCloseableEntityManager(DatabaseSingleton databaseSingleton, EntityManager entityManager) {
        this.databaseSingleton = databaseSingleton;
        this.entityManager = entityManager;
    }

    @Override
    public void close() {
        databaseSingleton.closeEntityManager();
    }
}
