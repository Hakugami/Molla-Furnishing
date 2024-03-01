package repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import models.entity.User;
import persistence.manager.DatabaseSingleton;
import repositories.GenericRepository;

import java.util.Optional;

public class UserRepository extends GenericRepository<User, Long> {

    public UserRepository() {

    }

    public Optional<User> findByEmail(String email) {
        Optional<User> result = Optional.empty();
        try (EntityManager entityManager = DatabaseSingleton.getInstance().getEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
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
        }
        return result;
    }
}
