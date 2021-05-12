package com.javamaster.e4e1.service;

import com.javamaster.e4e1.dtos.RegisterDto;
import com.javamaster.e4e1.dtos.UserDto;
import com.javamaster.e4e1.exception.UserNotFoundException;
import com.javamaster.e4e1.repository.UserRepository;
import com.javamaster.e4e1.model.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    public void registerUser(RegisterDto registerDto) {
//        if (userRepository.findUserByEmail(registerDto.getEmail()).isPresent()) {
//            throw new UserAlreadyExistException("Email is not unique");
//        }
        User user = User.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .email(registerDto.getEmail())
                .password(registerDto.getPassword())
                .username(registerDto.getUserName()).build();

        userRepository.save(user);
    }

    @SneakyThrows
    public void removeUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @SneakyThrows
    public UserDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        UserDto userDto = UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .name(user.getUsername()).build();

        return userDto;
    }

}
