package persistence.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.entity.Category;
import models.entity.SubCategory;
import persistence.manager.DatabaseSingleton;
import persistence.repositories.GenericRepository;

import java.util.Optional;

public class SubCategoriesRepository extends GenericRepository<SubCategory,Long> {

    public SubCategoriesRepository(){super(SubCategory.class);}
    public boolean create(SubCategory Subcategory, EntityManager entityManager) {
        try {
            entityManager.persist(Subcategory);
        } catch (Exception e) {
            logger.severe("An error occurred during create operation: " + e.getMessage());
            return false;
        }
        return true;
    }

    public Long getSubCategoryIdByName(String subCategoryName) {
        return DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<SubCategory> root = criteriaQuery.from(SubCategory.class);
            criteriaQuery.select(root.get("id"));
            criteriaQuery.where(criteriaBuilder.equal(root.get("name"), subCategoryName));
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        });
    }

    public Optional<SubCategory> getSubCategoryByName(String key, EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubCategory> criteriaQuery = criteriaBuilder.createQuery(SubCategory.class);
        Root<SubCategory> root = criteriaQuery.from(SubCategory.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), key));
        return Optional.of(entityManager.createQuery(criteriaQuery).getSingleResult());
    }
}
