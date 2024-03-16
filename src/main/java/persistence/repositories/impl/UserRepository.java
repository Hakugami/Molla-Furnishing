package persistence.repositories.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.entity.ShoppingCart;
import models.entity.User;
import persistence.manager.DatabaseSingleton;
import persistence.repositories.GenericRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserRepository extends GenericRepository<User, Long> {

    public UserRepository() {
        super(User.class);
    }


    @Override
    public boolean create(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setLastUpdate(new Date());
        user.setCart(shoppingCart);
        return super.create(user);
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

    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root).where(cb.equal(root.get("phone"), phoneNumber));
            TypedQuery<User> query = entityManager.createQuery(cq);
            List<User> resultList = query.getResultList();
            System.out.println("UserRepository: findByPhoneNumber: phoneNumber: " + phoneNumber);
            System.out.println("UserRepository: findByPhoneNumber: resultList: " + resultList.size());
            if (resultList.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(resultList.getFirst());
        });
    }
}