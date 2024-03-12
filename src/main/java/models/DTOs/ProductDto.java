package models.DTOs;

import lombok.*;
import models.entity.Product;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Product}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductDto implements Serializable {
    long id;
    String name;
    String description;
    double price;
    int quantity;
    List<String> images;
    List<RatingDto> ratings;
    String categoryName;
    double rating;
    DiscountedProductDto discountedProduct;

}