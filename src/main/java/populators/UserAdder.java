package populators;

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
        Address address = new Address();
        address.setCity("Giza");
        address.setCountry("Egypt");
        address.setState("El-Haram");
        address.setStreet("El-Molla St.");
        address.setZipCode("12345");
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
        Address address = new Address();
        address.setId(52L);
        address.setCity("Giza");
        address.setCountry("Egypt");
        address.setState("El-Haram");
        address.setStreet("El-Molla St.");
        address.setZipCode("12345");
        userService.removeAddress(1L, address);
    }

    public static void main(String[] args)  {
        // Create an ObjectMapper instance
//        authService = new AuthenticationService();
//        UserDto userDto = new UserDto();
//        userDto.setName("Mohamed Nofal");
//        userDto.setBirthday(new Date());
//        userDto.setEmail("nofal@gmail.com");
//        userDto.setPassword("12345678");
//        userDto.setJob("SE");
//        userDto.setInterest("molla");
//        userDto.setCreditLimit(10000);
//        authService.register(userDto);
//
//        authService.register(userDto);

        addUserAddressTest();
        removeUserAddressTest();
        printUserAddresses();

    }
}
