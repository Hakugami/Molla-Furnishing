package services;

import jakarta.servlet.http.HttpServletRequest;
import mappers.OrderMapper;
import mappers.UserMapper;
import models.DTOs.AddressDto;
import models.DTOs.OrderDto;
import models.DTOs.UserDto;
import models.entity.Address;
import models.entity.Order;
import models.entity.User;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import persistence.manager.DatabaseSingleton;
import persistence.repositories.impl.UserRepository;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

public class UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;

    public UserService() {
        this.repository = new UserRepository();
        this.userMapper = UserMapper.INSTANCE;
        this.orderMapper = OrderMapper.INSTANCE;
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
        DatabaseSingleton.getInstance().doTransactionWithResult(entityManager -> {
            System.out.println("EditProfileServlet: updateUser: doTransaction");
            System.out.println("EditProfileServlet: updateUser");
            JWTService jwtService = JWTService.getInstance();
            JwtClaims claims = null;
            try {
                claims = jwtService.validateToken(jwt, req.getRemoteAddr());
            } catch (InvalidJwtException | UnknownHostException e) {
                throw new RuntimeException(e);
            }
            User user = null;
            try {
                user = repository.read(Long.valueOf(claims.getSubject()), entityManager).orElse(null);
            } catch (MalformedClaimException e) {
                throw new RuntimeException(e);
            }
            if (user != null) {
                System.out.println("EditProfileServlet: updateUser: user: " + user);

                updateField(req, "name", user::setName);
                updateField(req, "email", user::setEmail);
                updateField(req, "phone", user::setPhone);
                updateField(req, "gender", user::setGender);
                updateField(req, "interest", user::setInterest);
                updateField(req, "job", user::setJob);
                updateDateField(req, "DOB", user::setBirthday);

                return repository.update(user, entityManager);
            }
            return false;
        });

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


    public boolean addAddress(Long id, AddressDto newAddress) {

        System.out.println("UserService: addAddress: id: " + id + " newAddress: " + newAddress);
        // Validate the input
        if (newAddress.getStreet() == null || newAddress.getStreet().isEmpty() ||
                newAddress.getCity() == null || newAddress.getCity().isEmpty() ||
                newAddress.getState() == null || newAddress.getState().isEmpty() ||
                newAddress.getCountry() == null || newAddress.getCountry().isEmpty() ||
                newAddress.getZipCode() == null || newAddress.getZipCode().isEmpty()) {
            throw new IllegalArgumentException("All address fields must be provided");
        }

        // Retrieve the user
        User user = repository.read(id).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Check for duplicates
        for (Address existingAddress : user.getAddresses()) {
            if (existingAddress.getStreet().equals(newAddress.getStreet()) &&
                    existingAddress.getCity().equals(newAddress.getCity()) &&
                    existingAddress.getState().equals(newAddress.getState()) &&
                    existingAddress.getCountry().equals(newAddress.getCountry()) &&
                    existingAddress.getZipCode().equals(newAddress.getZipCode())) {
                throw new IllegalArgumentException("Duplicate address");
            }
        }


        // Add the address


        // Save the changes
        return repository.addAddress(id, userMapper.addressDtoToAddress(newAddress));
    }


    public void removeAddress(Long l, AddressDto addressDto) {
        repository.removeAddress(l, userMapper.addressDtoToAddress(addressDto));
    }

    public List<UserDto> getUsersByPaginate(int page, int size) {
        Optional<List<User>> optionalUsers = repository.getUsersByNameAndPaginate(page, size, null);
        return optionalUsers
                .map(users -> users.stream().map(userMapper::userToUserDto).toList())
                .orElseGet(ArrayList::new);
    }

    public List<UserDto> getUsersByNameAndPaginate(int page, int size, String name) {
        Optional<List<User>> optionalUsers = repository.getUsersByNameAndPaginate(page, size, name);
        return optionalUsers
                .map(users -> users.stream().map(userMapper::userToUserDto).toList())
                .orElseGet(ArrayList::new);
    }

    public List<OrderDto> getOrdersByUserId(Long userId) {
        Optional<List<Order>> optionalOrders = repository.getOrdersByUserId(userId);
        return optionalOrders
                .map(orders -> orders.stream().map(orderMapper::orderToOrderDto).toList())
                .orElseGet(Collections::emptyList);

    }
}
