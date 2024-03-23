package models.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    String name;
    String description;
    double price;
    int quantity;
    List<String> images;
    @JsonIgnore
    List<RatingDto> ratings;
    String categoryName;
    String subCategoryName;
    double rating;
    @JsonIgnore
    DiscountedProductDto discountedProduct;
    @JsonIgnore
    private Date dateAdded;
    long productId;
    private ProductDetailsDto productDetails;
}