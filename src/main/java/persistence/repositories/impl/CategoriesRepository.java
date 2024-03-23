package persistence.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.entity.Category;
import persistence.manager.DatabaseSingleton;
import persistence.repositories.GenericRepository;

import java.util.List;
import java.util.Optional;

public class CategoriesRepository extends GenericRepository<Category, Long> {

    public CategoriesRepository() {
        super(Category.class);
    }


    public boolean create(Category category, EntityManager entityManager) {
        try {
            entityManager.persist(category);
        } catch (Exception e) {
            logger.severe("An error occurred during create operation: " + e.getMessage());
            return false;
        }
        return true;
    }

    public Long getCategoryIdByName(String categoryName) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Category> root = criteriaQuery.from(Category.class);
            criteriaQuery.select(root.get("id"));
            criteriaQuery.where(criteriaBuilder.equal(root.get("name"), categoryName));
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        });
    }

    public Optional<Category> getCategoryByName(String key, EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root<Category> root = criteriaQuery.from(Category.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), key));
        return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
    }

    public List<Category> retrieveCategories(int i, int maxValue) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            CriteriaQuery<Category> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Category.class);
            Root<Category> root = criteriaQuery.from(Category.class);
            criteriaQuery.select(root);
            return entityManager.createQuery(criteriaQuery)
                    .setFirstResult((i - 1) * maxValue)
                    .setMaxResults(maxValue)
                    .getResultList();
        });
    }
}