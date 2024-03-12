package persistence.repositories.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.entity.Category;
import persistence.manager.DatabaseSingleton;
import persistence.repositories.GenericRepository;

public class CategoriesRepository extends GenericRepository<Category, Long> {

    public CategoriesRepository() {
        super(Category.class);
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
}