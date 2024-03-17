package services;

import jakarta.servlet.http.HttpServletResponse;
import mappers.UserMapper;
import models.DTOs.UserDto;
import models.entity.User;
import models.enums.UserRole;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import persistence.repositories.impl.UserRepository;
import utils.ValidationUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Optional;
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

    private boolean login(String email, String password) {
        Optional<User> user = repository.findByEmail(email);
        if (user.isEmpty()) {
            System.out.println("User not found");
            return false;
        }
        User user1 = user.get();
        String hashedPassword = hashService.hashPasswordWithSalt(password, user1.getSalt());
        if (user1.getPassword().equals(hashedPassword)) {
            System.out.println("Password matches!");
            return true;
        } else {
            System.out.println("Password does not match!");
            return false;
        }
    }

    public boolean register(UserDto userDto) {
        if (repository.findByEmail(userDto.getEmail()).isPresent() || !ValidationUtil.isValidEmailFormat(userDto.getEmail())
                || ValidationUtil.validatePassword(userDto.getPassword()) != null || ValidationUtil.validatePhoneNumber(userDto.getPhone()) != null) {
            return false;
        }
        User user = userMapper.userDtoToUser(userDto);
        String salt = hashService.generateSalt();
        user.setSalt(salt);
        user.setRole(UserRole.USER);
        user.setPassword(hashService.hashPasswordWithSalt(user.getPassword(), salt));
        return repository.create(user);

    }

    public String loginAndReturnToken(String email, String password, String audience, HttpServletResponse response) {
        if (login(email, password)) {
            Optional<User> user = repository.findByEmail(email);
            if (user.isPresent()) {
                String result = null;
                User user1 = user.get();
                JsonWebSignature jws = JWTService.getInstance().getNewSignedToken();
                Date now = new Date();
                JwtClaims claims = new JwtClaims();
                try {
                    claims.setIssuer("http://" + InetAddress.getLocalHost().getHostAddress() + ":4545/molla/api");
                } catch (UnknownHostException e) {
                    logger.severe("Error getting host address: " + e.getMessage());
                }
                user1.setPassword(null);
                user1.setSalt(null);
                UserDto userDto = userMapper.userToUserDto(user1);
                claims.setAudience(audience);
                claims.setSubject(String.valueOf(user1.getId()));
                claims.setIssuedAtToNow();
                claims.setExpirationTime(NumericDate.fromMilliseconds(now.getTime() + 86400000));
                claims.setClaim("name", user1.getName());
                claims.setClaim("email", user1.getEmail());
                claims.setClaim("role", user1.getRole().toString());
                claims.setClaim("date of birth", user1.getBirthday().toString());
                claims.setClaim("interest", user1.getInterest());
                claims.setClaim("phone", user1.getPhone());
                claims.setClaim("gender", user1.getGender());
                claims.setClaim("addresses", userDto.getAddresses());


                jws.setPayload(claims.toJson());

                try {
                    result = jws.getCompactSerialization();
                } catch (Exception e) {
                    logger.severe("Error creating JWT: " + e.getMessage());
                }
                return result;
            }
        }
        return null;
    }


}
