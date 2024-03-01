package services;

import mappers.UserMapper;
import models.DTOs.UserDto;
import models.entity.User;
import repositories.impl.UserRepository;

public class AuthenticationService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final HashService hashService;

    public AuthenticationService() {
        this.repository = new UserRepository();
        this.userMapper = UserMapper.getInstance();
        this.hashService = HashService.getInstance();
    }

    public boolean login(String email, String password) {
        User user = repository.findByEmail(email).orElse(null);
        if (user == null) {
            return false;
        }
        String hashedPassword = hashService.hashPasswordWithSalt(password, user.getSalt());
        return user.getPassword().equals(hashedPassword);
    }

    public boolean register(UserDto userDto) {
        if (repository.findByEmail(userDto.getEmail()).isPresent()) {
            return false;
        }
        User user = userMapper.toEntity(userDto);
        String salt = hashService.generateSalt();
        user.setSalt(salt);
        user.setPassword(hashService.hashPasswordWithSalt(user.getPassword(), salt));
        return repository.create(user);
    }
}