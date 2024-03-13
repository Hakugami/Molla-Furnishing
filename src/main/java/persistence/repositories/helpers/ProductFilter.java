package persistence.repositories.helpers;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import models.entity.Product;

@Data
public class ProductFilter {
    private String category;
    private String brand;
    private String name;
    private String sortBy;
    private String sortOrder;
    private Double minPrice;
    private Double maxPrice;
    private double minRating;
    private double maxRating;


    public Predicate toPredicate(CriteriaBuilder criteriaBuilder, Root<Product> root) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (category != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("category").get("name"), "%" + category + "%"));
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

        if (name != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }

        if (minRating != 0) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), minRating));
        }

        if (maxRating != 0) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("rating"), maxRating));
        }


        return predicate;
    }

    public Order toOrder(CriteriaBuilder criteriaBuilder, Root<Product> root) {
        if (sortBy == null) {
            return criteriaBuilder.asc(root.get("id"));
        }

        if ("asc".equalsIgnoreCase(sortOrder)) {
            return criteriaBuilder.asc(root.get(sortBy));
        } else {
            return criteriaBuilder.desc(root.get(sortBy));
        }
    }
}