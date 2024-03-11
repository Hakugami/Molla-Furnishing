package persistence.repositories.helpers;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import models.entity.Product;
@Data
public class ProductFilter {
    private String category;
    private String brand;
    private Double minPrice;
    private Double maxPrice;

    // Add getters and setters for each field

    public Predicate toPredicate(CriteriaBuilder criteriaBuilder, Root<Product> root) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (category != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category"), category));
        }

        if (brand != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("brand"), brand));
        }

        if (minPrice != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
        }

        if (maxPrice != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        return predicate;
    }
}