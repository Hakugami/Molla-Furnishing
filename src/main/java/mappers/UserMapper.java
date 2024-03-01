package mappers;

import models.DTOs.AddressDto;
import models.DTOs.UserDto;
import models.entity.Address;
import models.entity.User;

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
    public  UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        AddressDto addressDto = new AddressDto(user.getAddress().getStreet(), user.getAddress().getCity(), user.getAddress().getState(), user.getAddress().getCountry(), user.getAddress().getZipCode());
        return new UserDto(user.getId(), user.getName(), user.getBirthday(), user.getPassword(), user.getJob(), user.getInterest(), user.getEmail(), user.getSalt(), user.getCreditLimit(), addressDto, user.getRole());
    }

    public  User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        Address address = new Address(userDto.getAddress().getStreet(), userDto.getAddress().getCity(), userDto.getAddress().getState(), userDto.getAddress().getCountry(), userDto.getAddress().getZipCode());
        return new User(userDto.getId(), userDto.getName(), userDto.getBirthday(), userDto.getPassword(), userDto.getJob(), userDto.getInterest(), userDto.getEmail(), userDto.getSalt(), userDto.getCreditLimit(), address, userDto.getRole());
    }
}