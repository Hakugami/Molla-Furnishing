package models.DTOs;

import lombok.Value;
import models.entity.User;
import models.enums.UserRole;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
    long id;
    String name;
    Date birthday;
    String password;
    String job;
    String interest;
    String email;
    String salt;
    double creditLimit;
    List<AddressDto> addresses;
    UserRole role;
}