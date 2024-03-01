package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import persistence.manager.DatabaseSingleton;

import java.util.Optional;

public abstract class GenericRepository <T, ID>{

    protected EntityTransaction transaction;

    public boolean create(T t) {
        EntityManager entityManager = DatabaseSingleton.getInstance().getEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(t);
            if (transaction.isActive()) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } finally {
            DatabaseSingleton.getInstance().closeEntityManager();
        }
        return true;
    }

    public Optional<T> read(ID id) {
        EntityManager entityManager = DatabaseSingleton.getInstance().getEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        Optional<T> result;
        T t = null;
        try {
            t = entityManager.find((Class<T>) this.getClass(), id);
            result = Optional.of(t);
            if (transaction.isActive()) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } finally {
            DatabaseSingleton.getInstance().closeEntityManager();
        }
        return result;
    }

    public boolean update(T t) {
        EntityManager entityManager = DatabaseSingleton.getInstance().getEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.merge(t);
            if (transaction.isActive()) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } finally {
            DatabaseSingleton.getInstance().closeEntityManager();
        }
        return true;
    }

    public void delete(ID id) {
        EntityManager entityManager = DatabaseSingleton.getInstance().getEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            T t = entityManager.find((Class<T>) this.getClass(), id);
            entityManager.remove(t);
            if (transaction.isActive()) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } finally {
            DatabaseSingleton.getInstance().closeEntityManager();
        }
    }

}