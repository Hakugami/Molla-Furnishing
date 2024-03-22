package models.DTOs;

import lombok.*;
import models.entity.Product;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link Product}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductDto implements Serializable {
    long productId;
    String name;
    String description;
    double price;
    int quantity;
    List<String> images;
    List<RatingDto> ratings;
    String categoryName;
    String subCategoryName;
    double rating;
    DiscountedProductDto discountedProduct;

    private Date dateAdded;
    private ProductDetailsDto productDetails;
}