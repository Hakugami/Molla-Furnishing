package mappers;

import models.DTOs.AddressDto;
import models.DTOs.UserDto;
import models.entity.Address;
import models.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    //instance of the mapper
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public User userDtoToUser(UserDto userDto);
    public UserDto userToUserDto(User user);
    public Address addressDtoToAddress(AddressDto addressDto);
    public Address addressToAddressDto(Address address);
}
