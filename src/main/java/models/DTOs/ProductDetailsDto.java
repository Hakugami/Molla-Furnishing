package models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.entity.ProductDetails;

import java.io.Serializable;

/**
 * DTO for {@link ProductDetails}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDto implements Serializable {
    private String material;
    private String dimensions;
    private String color;
    private String weight;
}