package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import persistence.manager.DatabaseSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericRepository<T, ID> {

    protected EntityTransaction transaction;

    public boolean create(T t) {
        try (EntityManager entityManager = DatabaseSingleton.getInstance().getEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Optional<T> read(ID id) {
        try (EntityManager entityManager = DatabaseSingleton.getInstance().getEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            T t = entityManager.find((Class<T>) this.getClass(), id);
            transaction.commit();
            return Optional.of(t);
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<T> readAll() {
        List<T> resultList = new ArrayList<>();
        try (EntityManager entityManager = DatabaseSingleton.getInstance().getEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery((Class<T>) this.getClass());
            Root<T> rootEntry = cq.from((Class<T>) this.getClass());
            CriteriaQuery<T> all = cq.select(rootEntry);
            TypedQuery<T> allQuery = entityManager.createQuery(all);
            resultList = allQuery.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            // Log the exception
            System.err.println("An error occurred during readAll operation: " + e.getMessage());
        }
        return resultList;
    }

    public boolean update(T t) {
        try (EntityManager entityManager = DatabaseSingleton.getInstance().getEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void delete(ID id) {
        try (EntityManager entityManager = DatabaseSingleton.getInstance().getEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            T t = entityManager.find((Class<T>) this.getClass(), id);
            entityManager.remove(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}