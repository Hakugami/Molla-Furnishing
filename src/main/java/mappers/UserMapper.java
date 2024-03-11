package mappers;

import models.DTOs.UserDto;
import models.entity.Address;
import models.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    public User userDtoToUser(UserDto userDto);
    public UserDto userToUserDto(User user);
    public Address addressDtoToAddress(Address address);
    public Address addressToAddressDto(Address address);
}
