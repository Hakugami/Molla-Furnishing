package repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.entity.User;
import persistence.AutoCloseableEntityManager;
import persistence.manager.DatabaseSingleton;
import repositories.GenericRepository;

import java.util.Optional;

public class UserRepository extends GenericRepository<User, Long> {

    public UserRepository() {

    }

    public Optional<User> findByEmail(String email) {
        Optional<User> result = Optional.empty();
        try (AutoCloseableEntityManager autoCloseableEntityManager = DatabaseSingleton.getInstance().getEntityManager()) {
            EntityManager entityManager = autoCloseableEntityManager.getEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            CriteriaQuery<User> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(entityManager.getCriteriaBuilder().equal(root.get("email"), email));
            TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
            User user = query.getSingleResult();
            result = Optional.of(user);
            if (transaction.isActive()) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (NoResultException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            logger.severe("An error occurred during findByEmail operation: " + e.getMessage());
        }
        return result;
    }
}
