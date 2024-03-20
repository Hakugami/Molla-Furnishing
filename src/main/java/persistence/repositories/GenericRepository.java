package persistence.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import persistence.manager.DatabaseSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public abstract class GenericRepository<T, ID> {

    private final Class<T> entityClass;

    protected Logger logger = Logger.getLogger(GenericRepository.class.getName());

    public GenericRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public boolean create(T t) {
        try {
            DatabaseSingleton.getInstance().doTransaction(entityManager -> {
                entityManager.persist(t);
            });
            return true;
        } catch (Exception e) {
            logger.severe("An error occurred during create operation: " + e.getMessage());
            return false;
        }
    }

    public Optional<T> read(ID id) {
        try {
            return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
                T t = entityManager.find(entityClass, id);
                return Optional.of(t);
            });
        } catch (Exception e) {
            logger.severe("An error occurred during read operation: " + e.getMessage());
            return Optional.empty();
        }
    }

    public List<T> readAll() {
        try {
            return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
                CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                CriteriaQuery<T> cq = cb.createQuery(entityClass);
                Root<T> rootEntry = cq.from(entityClass);
                CriteriaQuery<T> all = cq.select(rootEntry);
                TypedQuery<T> allQuery = entityManager.createQuery(all);
                return allQuery.getResultList();
            });
        } catch (Exception e) {
            logger.severe("An error occurred during readAll operation: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean update(T t , EntityManager entityManager) {
        try {
            entityManager.merge(t);
            return true;
        } catch (Exception e) {
            logger.severe("An error occurred during update operation: " + e.getMessage());
            return false;
        }
    }

    public void delete(ID id) {
        try {
            DatabaseSingleton.getInstance().doTransaction(entityManager -> {
                T t = entityManager.find(entityClass, id);
                entityManager.remove(t);
            });
        } catch (Exception e) {
            logger.severe("An error occurred during delete operation: " + e.getMessage());
        }
    }
}