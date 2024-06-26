package persistence.repositories.impl;

import jakarta.persistence.criteria.*;
import models.entity.Product;
import persistence.manager.DatabaseSingleton;
import persistence.repositories.GenericRepository;
import persistence.repositories.helpers.ProductFilter;

import java.util.List;

public class ProductRepository extends GenericRepository<Product, Long> {

    public ProductRepository() {
        super(Product.class);
    }

    public List<Product> retrieveProducts(int page, int size) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            CriteriaQuery<Product> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.select(root);
            return entityManager.createQuery(criteriaQuery)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        });
    }

    public long countProducts() {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            CriteriaQuery<Long> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Long.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.select(entityManager.getCriteriaBuilder().count(root));
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        });
    }

    public List<Product> retrieveProductsByCategory(int page, int size, String category) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            CriteriaQuery<Product> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.select(root).where(entityManager.getCriteriaBuilder().equal(root.get("category"), category));
            return entityManager.createQuery(criteriaQuery)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        });
    }

    public List<Product> filterProducts(int page, int size, ProductFilter filter) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            Predicate predicateDisjunction = filter.toPredicateDisjunction(criteriaBuilder, root);
            Predicate predicateConjunction = filter.toPredicateConjunction(criteriaBuilder, root);
            Order order = filter.toOrder(criteriaBuilder, root);
            criteriaQuery.select(root).where(criteriaBuilder.or(predicateDisjunction, predicateConjunction)).orderBy(order);
            return entityManager.createQuery(criteriaQuery)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        });
    }


    public void batchUpdate(List<Product> products) {
        DatabaseSingleton.getInstance().doTransaction(entityManager -> {
            try {
                products.forEach(entityManager::merge);
            } catch (Exception e) {
                System.out.println("An error occurred while updating products: " + e.getMessage());
                logger.severe("An error occurred while updating products: " + e.getMessage());
                throw e;
            }
        });
    }

    public void deleteProduct(Long productId) throws Exception {
        DatabaseSingleton.getInstance().doTransaction(entityManager -> {
            Product product = entityManager.find(Product.class,productId);
            if(product!=null){
                product.setDeleted(true);
                entityManager.persist(product);
            }else{
                throw new RuntimeException("Product Does not exist");
            }

        });
    }

    public void batchInsert(List<Product> products) {
        DatabaseSingleton.getInstance().doTransaction(entityManager -> {
            products.forEach(entityManager::persist);
        });
    }
}