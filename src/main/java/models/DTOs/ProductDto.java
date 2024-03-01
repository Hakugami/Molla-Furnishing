package models.DTOs;

import lombok.Value;
import models.entity.Product;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Product}
 */
@Value
public class ProductDto implements Serializable {
    long id;
    String name;
    String description;
    double price;
    int quantity;
    List<String> images;
    List<RatingDto> ratings;
    CategoryDto category;
    DiscountedProductDto discountedProduct;
}