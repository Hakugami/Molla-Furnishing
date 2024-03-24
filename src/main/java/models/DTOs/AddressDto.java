package models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.entity.Address;

import java.io.Serializable;

/**
 * DTO for {@link Address}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AddressDto implements Serializable {
    long id;
    String street;
    String city;
    String state;
    String country;
    String zipCode;
}