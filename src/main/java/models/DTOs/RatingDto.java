package models.DTOs;

import lombok.Value;
import models.DTOs.UserDto;
import models.entity.Rating;

import java.io.Serializable;

/**
 * DTO for {@link Rating}
 */
@Value
public class RatingDto implements Serializable {
    long id;
    UserDto user;
    double value;
    ProductDto product;
}