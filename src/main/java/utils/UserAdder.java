package utils;

import models.DTOs.UserDto;
import services.AuthenticationService;
import java.util.Date;
import java.util.logging.Logger;

public class UserAdder {
    private static AuthenticationService authService;
    private static final Logger logger = Logger.getLogger(UserAdder.class.getName());

    public static void main(String[] args)  {
        // Create an ObjectMapper instance
        authService = new AuthenticationService();
        UserDto userDto = new UserDto();
        userDto.setName("Mohamed Nofal");
        userDto.setBirthday(new Date());
        userDto.setEmail("nofal@gmail.com");
        userDto.setPassword("12345678");
        userDto.setJob("SE");
        userDto.setInterest("molla");
        userDto.setCreditLimit(10000);
        authService.register(userDto);

//        try {
//
//            ObjectMapper mapper = new ObjectMapper();
//
//            mapper.setDateFormat(new SimpleDateFormat("yyyy-MMMM-dd")); // Add this line
//            // Read the request body and convert it into a UserDto object
//            userDto = mapper.readValue(req.getReader(), UserDto.class);
//
//        } catch (Exception e) {
//            logger.severe("Error reading user data: " + e.getMessage());
//            throw new ServletException(e);
//        }

        authService.register(userDto);

    }
}
