package services;

import jakarta.servlet.http.HttpServletResponse;
import mappers.UserMapper;
import models.DTOs.UserDto;
import models.entity.User;
import models.enums.UserRole;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import persistence.manager.DatabaseSingleton;
import persistence.repositories.impl.UserRepository;
import utils.EmailUtil;
import utils.ValidationUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class AuthenticationService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final HashService hashService;
    private final Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    public AuthenticationService() {
        this.repository = new UserRepository();
        this.userMapper = UserMapper.INSTANCE;
        this.hashService = HashService.getInstance();
    }

    public User login(String email, String password) {
        Optional<User> user = repository.findByEmail(email);
        if (user.isEmpty()) {
            System.out.println("User not found");
            return null;
        }
        User user1 = user.get();
        String hashedPassword = hashService.hashPasswordWithSalt(password, user1.getSalt());
        if (user1.getPassword().equals(hashedPassword)) {
            System.out.println("Password matches!");
            return user1;
        } else {
            System.out.println("Password does not match!");
            return null;
        }
    }

    public boolean register(UserDto userDto, String password) {
        if (repository.findByEmail(userDto.getEmail()).isPresent() || !ValidationUtil.isValidEmailFormat(userDto.getEmail())
                || ValidationUtil.validatePassword(password) != null || ValidationUtil.validatePhoneNumber(userDto.getPhone()) != null) {
            return false;
        }
        User user = userMapper.userDtoToUser(userDto);
        String salt = hashService.generateSalt();
        user.setSalt(salt);
        user.setRole(UserRole.USER);
        user.setPassword(hashService.hashPasswordWithSalt(password, salt));
        try {
            EmailUtil.sendUserRegistrationEmail(user.getEmail(), user.getName());
        } catch (RuntimeException e) {
            return false;
        }
        return repository.create(user);

    }

    public String returnToken(User user, String audience) {
        System.out.println("Returning token");
        String result = null;
        JsonWebSignature jws = JWTService.getInstance().getNewSignedToken();
        Date now = new Date();
        JwtClaims claims = new JwtClaims();
        try {
            claims.setIssuer("http://" + InetAddress.getLocalHost().getHostAddress() + ":4545/molla/api");
        } catch (UnknownHostException e) {
            System.out.println("Error getting host address");
            logger.severe("Error getting host address: " + e.getMessage());
        }
        user.setPassword(null);
        user.setSalt(null);
        System.out.println("inside return token");
        UserDto userDto = userMapper.userToUserDto(user);
        System.out.println(userDto+" userDto");
        claims.setAudience(audience);
        claims.setSubject(String.valueOf(user.getId()));
        claims.setIssuedAtToNow();
        claims.setExpirationTime(NumericDate.fromMilliseconds(now.getTime() + 86400000));
        claims.setClaim("name", user.getName());
        claims.setClaim("email", user.getEmail());
        claims.setClaim("role", user.getRole().toString());
        claims.setClaim("date of birth", user.getBirthday().toString());
        claims.setClaim("interest", user.getInterest());
        claims.setClaim("phone", user.getPhone());
        claims.setClaim("gender", user.getGender());
        claims.setClaim("addresses", userDto.getAddresses());


        jws.setPayload(claims.toJson());

        try {
            System.out.println("Signing token");
            result = jws.getCompactSerialization();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error creating JWT");
            logger.severe("Error creating JWT: " + e.getMessage());
        }
        return result;
    }


    public String sendResetPasswordEmail(String email) {
        Optional<User> user = repository.findByEmail(email);
        String passwordResetId = null;
        if (user.isPresent()) {
            passwordResetId = UUID.randomUUID().toString();
            EmailUtil.sendResetPasswordEmail(email, user.get().getName(), passwordResetId);
        } else {
            throw new IllegalArgumentException("User doesn't exist!!");
        }
        return passwordResetId;
    }

    public void resetPassword(String email, String newPassword) {
        DatabaseSingleton.getInstance().doTransaction(entityManager -> {
            Optional<User> user = repository.findByEmail(email, entityManager);
            if (user.isPresent()) {
                User user1 = user.get();
                user1.setPassword(hashService.hashPasswordWithSalt(newPassword, user1.getSalt()));
                repository.update(user1, entityManager);
            } else {
                throw new IllegalArgumentException("User doesn't exist!!");
            }
        });

    }


}
