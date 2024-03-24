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

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    Address addressDtoToAddress(AddressDto addressDto);

    Address addressToAddressDto(Address address);
}
