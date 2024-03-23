package persistence.repositories.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import models.entity.Order;
import models.entity.User;
import persistence.manager.DatabaseSingleton;
import persistence.repositories.GenericRepository;

import java.util.List;

public class OrderRepository extends GenericRepository<Order, Long> {
    public OrderRepository() {
        super(Order.class);
    }

    public List<Order> retrieveOrdersByUserId(Long userId) {

        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Order> cq = builder.createQuery(Order.class);

            Root<User> userRoot = cq.from(User.class);

            Join<User, Order> joinOrder = userRoot.join("orders");

            cq.select(joinOrder);

            cq.where(builder.equal(userRoot.get("id"), userId));

            return entityManager.createQuery(cq).getResultList();
        });
    }
}
