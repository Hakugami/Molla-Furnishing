package models.DTOs;

import lombok.Value;
import models.entity.Address;

import java.io.Serializable;

/**
 * DTO for {@link Address}
 */
@Value
public class AddressDto implements Serializable {
    long id;
    String street;
    String city;
    String state;
    String country;
    String zipCode;
    UserDto user;
}