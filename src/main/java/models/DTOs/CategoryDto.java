package models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private List<SubCategoryDto> subCategories;
}