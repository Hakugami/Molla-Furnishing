package models.DTOs;

import lombok.*;
import models.DTOs.ProductDto;
import models.entity.Category;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Category}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CategoryDto implements Serializable {
    long id;
    String name;
    List<ProductDto> products;
}