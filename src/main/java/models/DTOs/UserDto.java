package models.DTOs;

import lombok.Value;
import models.entity.User;
import models.enums.UserRole;

import java.io.Serializable;
import java.sql.Date;

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
    double creditLimit;
    AddressDto address;
    UserRole role;
}