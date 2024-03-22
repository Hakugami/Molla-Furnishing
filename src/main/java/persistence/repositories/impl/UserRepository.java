package persistence.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import models.entity.Address;
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


    public Optional<User> read(Long id, EntityManager entityManager) {
        return Optional.of(entityManager.find(User.class, id));
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
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> getUser(email, entityManager));
    }

    public Optional<User> findByEmail(String email, EntityManager entityManager) {
        return getUser(email, entityManager);
    }

    private Optional<User> getUser(String email, EntityManager entityManager) {
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

    public boolean addAddress(Long id, Address address) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            User user = entityManager.find(User.class, id);
            user.getAddresses().add(address);
            address.setUser(user);
            entityManager.persist(address);
            return true;
        });
    }

    public boolean removeAddress(Long id, Address address) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            User user = entityManager.find(User.class, id);
            user.getAddresses().stream().filter(a -> a.getId() == address.getId()).findFirst().ifPresent(a -> {
                user.getAddresses().remove(a);
                entityManager.remove(a);
            });
            return true;
        });
    }

    public Optional<User> read(Long id, EntityManager entityManager, LockModeType lockModeType) {
        return Optional.of(entityManager.find(User.class, id, lockModeType));
    }

    public List<User> getUsers(int page, int size) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            Order order = criteriaBuilder.desc(root.get("id"));

            criteriaQuery.select(root).orderBy(order);

            return entityManager.createQuery(criteriaQuery)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        });
    }
}