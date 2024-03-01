package models.DTOs;

import lombok.Value;
import models.DTOs.ProductDto;
import models.entity.Category;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Category}
 */
@Value
public class CategoryDto implements Serializable {
    long id;
    String name;
    List<ProductDto> products;
}