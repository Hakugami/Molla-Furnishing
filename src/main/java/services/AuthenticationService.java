package services;

import mappers.UserMapper;
import mappers.UserMapperImpl;
import models.entity.User;
import models.enums.UserRole;
import persistence.repositories.impl.UserRepository;
import models.DTOs.UserDto;
import utils.KeyGenerator;

import java.util.Optional;
import java.util.logging.Logger;

public class AuthenticationService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final HashService hashService;
    private final Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    public AuthenticationService() {
        this.repository = new UserRepository();
        this.userMapper = new UserMapperImpl();
        this.hashService = HashService.getInstance();
    }

    public boolean login(String email, String password) {
       Optional <User> user = repository.findByEmail(email);
        if (user.isEmpty()) {
            System.out.println("User not found");
            return false;
        }
        User user1 = user.get();
        String hashedPassword = hashService.hashPasswordWithSalt(password, user1.getSalt());
        if (user1.getPassword().equals(hashedPassword)){
            System.out.println("Password matches!");
            return true;
        }
        else {
            System.out.println("Password does not match!");
            return false;
        }
    }

    public boolean register(UserDto userDto) {
        if (repository.findByEmail(userDto.getEmail()).isPresent()) {
            return false;
        }
        User user = userMapper.userDtoToUser(userDto);
        String salt = hashService.generateSalt();
        user.setSalt(salt);
        user.setRole(UserRole.USER);
        user.setPassword(hashService.hashPasswordWithSalt(user.getPassword(), salt));
        boolean created = repository.create(user);
        if (created) {
            try {
                KeyGenerator.getInstance().generateKeyPairForUser(String.valueOf(user.getId()));
            } catch (Exception e) {
                logger.severe("An error occurred during key generation: " + e.getMessage());
                return false;
            }
        }
        return created;

    }
}