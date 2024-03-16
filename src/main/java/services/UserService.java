package services;

import jakarta.servlet.http.HttpServletRequest;
import mappers.UserMapper;
import models.DTOs.UserDto;
import models.entity.Address;
import models.entity.User;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import persistence.repositories.impl.UserRepository;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

public class UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserService() {
        this.repository = new UserRepository();
        this.userMapper = UserMapper.INSTANCE;
    }

    public UserDto getUserByEmail(String email) {
        return userMapper.userToUserDto(repository.findByEmail(email).orElse(null));
    }

    public UserDto getUserById(Long id) {
        return userMapper.userToUserDto(repository.read(id).orElse(null));
    }

    public UserDto getUserByPhoneNumber(String phoneNumber) {
        return userMapper.userToUserDto(repository.findByPhoneNumber(phoneNumber).orElse(null));
    }

    public boolean updateUser(HttpServletRequest req, String jwt) throws InvalidJwtException, UnknownHostException, MalformedClaimException {
        System.out.println("EditProfileServlet: updateUser");
        JWTService jwtService = JWTService.getInstance();
        JwtClaims claims = jwtService.validateToken(jwt, req.getRemoteAddr());
        User user = repository.read(Long.valueOf(claims.getSubject())).orElse(null);
        if (user != null) {
            System.out.println("EditProfileServlet: updateUser: user: " + user);

            updateField(req, "name", user::setName);
            updateField(req, "email", user::setEmail);
            updateField(req, "phone", user::setPhone);
            updateField(req, "gender", user::setGender);
            updateField(req, "interest", user::setInterest);
            updateField(req, "job", user::setJob);
            updateDateField(req, "DOB", user::setBirthday);
            addAddress(req, user);

            return repository.update(user);
        }
        System.out.println("EditProfileServlet: updateUser: user is null");
        return false;
    }

    private void updateField(HttpServletRequest req, String parameterName, Consumer<String> setter) {
        String parameterValue = req.getParameter(parameterName);
        if (parameterValue != null && !parameterValue.isEmpty()) {
            setter.accept(parameterValue);
        }
    }

    private void updateDateField(HttpServletRequest req, String parameterName, Consumer<Date> setter) {
        String parameterValue = req.getParameter(parameterName);
        if (parameterValue != null && !parameterValue.isEmpty()) {
            try {
                System.out.println("EditProfileServlet: updateDateField: " + parameterName + " : " + parameterValue);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMMM-dd");
                Date date = sdf.parse(parameterValue);
                setter.accept(date);
            } catch (ParseException e) {
                throw new RuntimeException("Invalid date format for " + parameterName, e);
            }
        }
    }

    private void addAddress(HttpServletRequest req, User user) {
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String country = req.getParameter("country");
        String zipCode = req.getParameter("zipCode");

        // Check if any of the address fields are null or empty
        if (street != null && !street.isEmpty() &&
                city != null && !city.isEmpty() &&
                state != null && !state.isEmpty() &&
                country != null && !country.isEmpty() &&
                zipCode != null && !zipCode.isEmpty()) {

            Address address = new Address();
            address.setStreet(street);
            address.setCity(city);
            address.setState(state);
            address.setCountry(country);
            address.setZipCode(zipCode);
            user.getAddresses().add(address);
        }
    }


}
