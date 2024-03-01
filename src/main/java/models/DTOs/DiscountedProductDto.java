package models.DTOs;

import jakarta.validation.constraints.Min;
import lombok.Value;
import models.DTOs.ProductDto;
import models.entity.DiscountedProduct;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link DiscountedProduct}
 */
@Value
public class DiscountedProductDto implements Serializable {
    ProductDto product;
    @Min(0)
    double discountRate;
    @Min(0)
    double discountedPrice;
    Date startDate;
    Date endDate;
}