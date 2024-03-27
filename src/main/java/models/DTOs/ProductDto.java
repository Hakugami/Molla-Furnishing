package models.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    boolean deleted;
    @JsonIgnore
    DiscountedProductDto discountedProduct;
    long productId;
    @JsonIgnore
    private Date dateAdded;
    private ProductDetailsDto productDetails;
}