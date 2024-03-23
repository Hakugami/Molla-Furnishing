package populators;

import models.DTOs.AddressDto;
import models.DTOs.UserDto;
import models.entity.Address;
import models.entity.User;
import persistence.repositories.impl.UserRepository;
import services.AuthenticationService;
import services.UserService;

import java.util.Date;
import java.util.logging.Logger;

public class UserAdder {
    private static AuthenticationService authService;
    private static final Logger logger = Logger.getLogger(UserAdder.class.getName());

    private static UserService userService;

    public static void addUserAddressTest(){
        userService = new UserService();
        AddressDto address = new AddressDto();
        address.setCity("Gizaaaaaaaaaaa");
        address.setCountry("Egyptaaaaaaa");
        address.setState("El-Haramaaaaaaaaaaaaa");
        address.setStreet("El-Mollas St.aaaaaaaaaaaa");
        address.setZipCode("12345aaaaaaaaaaaaaaaaaaa");
        userService.addAddress(1L, address);
    }

    public static void printUserAddresses(){
        userService = new UserService();
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        User user = new UserRepository().read(1L).orElse(null);
        System.out.println(user.getAddresses());


    }

    public static void removeUserAddressTest(){
        userService = new UserService();
        AddressDto address = new AddressDto();
        address.setId(1L);
        address.setCity("Giza");
        address.setCountry("Egypt");
        address.setState("El-Haram");
        address.setStreet("El-Molla St.");
        address.setZipCode("12345");
        userService.removeAddress(1L, address);
    }

    public static void main(String[] args)  {
        // Create an ObjectMapper instance
        for (int i = 0; i < 20; i++) {
//            authService = new AuthenticationService();
//            UserDto userDto = new UserDto();
//            userDto.setName("Mohamed Nofal" + i);
//            userDto.setBirthday(new Date());
//            userDto.setEmail("nofal" + i + "@gmail.com");
//            userDto.setGender("Male");
//            userDto.setPhone("1234654894894165");
//            userDto.setPassword("12345678");
//            userDto.setJob("SE");
//            userDto.setInterest("molla");
//            userDto.setCreditLimit(10000);
//            authService.register(userDto);

        }

//        addUserAddressTest();
//        removeUserAddressTest();
//        printUserAddresses();

    }
}
