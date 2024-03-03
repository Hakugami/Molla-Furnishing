// UserMapper.java
package mappers;

import models.DTOs.AddressDto;
import models.DTOs.UserDto;
import models.entity.Address;
import models.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    private static volatile UserMapper INSTANCE =  null;
    private UserMapper() {
    }

    public static UserMapper getInstance() {
        if (INSTANCE == null) {
            synchronized (UserMapper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserMapper();
                }
            }
        }
        return INSTANCE;
    }

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        List<AddressDto> addressDtos = user.getAddresses().stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new UserDto(user.getId(), user.getName(), user.getBirthday(), user.getPassword(), user.getJob(), user.getInterest(), user.getEmail(), user.getSalt(), user.getCreditLimit(), addressDtos, user.getRole());
    }

    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        List<Address> addresses = userDto.getAddresses().stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

        return new User(userDto.getId(), userDto.getName(), userDto.getBirthday(), userDto.getPassword(), userDto.getJob(), userDto.getInterest(), userDto.getEmail(), userDto.getSalt(), userDto.getCreditLimit(), addresses, userDto.getRole());
    }

    private AddressDto toDto(Address address) {
        if (address == null) {
            return null;
        }

        return new AddressDto(address.getId(), address.getStreet(), address.getCity(), address.getState(), address.getCountry(), address.getZipCode(), toDto(address.getUser()));
    }

    private Address toEntity(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }
        return new Address(addressDto.getId(), toEntity(addressDto.getUser()), addressDto.getStreet(), addressDto.getCity(), addressDto.getState(), addressDto.getCountry(), addressDto.getZipCode());
    }
}