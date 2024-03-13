package persistence.repositories.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.entity.User;
import persistence.manager.DatabaseSingleton;
import persistence.repositories.GenericRepository;

import java.util.List;
import java.util.Optional;

public class UserRepository extends GenericRepository<User, Long> {

    public UserRepository() {
        super(User.class);
    }

    public Optional<User> findByEmail(String email) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.equal(root.get("email"), email));
            TypedQuery<User> query = entityManager.createQuery(cq);
            List<User> resultList = query.getResultList();
            System.out.println("UserRepository: findByEmail: email: " + email);
            System.out.println("UserRepository: findByEmail: resultList: " + resultList.size());
            if (resultList.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(resultList.getFirst());
        });
    }
}