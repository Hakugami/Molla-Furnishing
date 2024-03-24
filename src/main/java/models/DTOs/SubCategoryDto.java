package models.DTOs;

import lombok.Value;
import models.entity.SubCategory;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link SubCategory}
 */
@Value
public class SubCategoryDto implements Serializable {
    long id;
    String name;
    List<ProductDto> products;
}