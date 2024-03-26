package models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.entity.User;
import models.enums.UserRole;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserDto implements Serializable {
    long id;
    String name;
    Date birthday;
    String job;
    String interest;
    String email;
    String salt;
    double creditLimit;
    List<AddressDto> addresses;
    UserRole role;
    private String gender;
    private String phone;
    List<MyProfileOrderDto> orders;
}