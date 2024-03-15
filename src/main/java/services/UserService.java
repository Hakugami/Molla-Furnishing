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
}
