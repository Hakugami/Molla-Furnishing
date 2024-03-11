package services;

import mappers.UserMapper;
import mappers.UserMapperImpl;
import models.DTOs.UserDto;
import persistence.repositories.impl.UserRepository;

public class UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserService() {
        this.repository = new UserRepository();
        this.userMapper = new UserMapperImpl();
    }

    public UserDto getUserByEmail(String email) {
        return userMapper.userToUserDto(repository.findByEmail(email).orElse(null));
    }
}
