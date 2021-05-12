package com.javamaster.e4e1.controller;

import com.javamaster.e4e1.dtos.RegisterDto;
import com.javamaster.e4e1.dtos.UserDto;
import com.javamaster.e4e1.service.UserService;
import com.javamaster.e4e1.utils.SuccessDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
//orice endpoint cream va avea in api-ul urmatoarea structura
@RequestMapping("/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //v1/user/register
    @PostMapping("/register")
    @SneakyThrows
    public ResponseEntity<SuccessDto> registerUser(@RequestBody @Validated RegisterDto registerDto) {
        userService.registerUser(registerDto);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

    @DeleteMapping("")
    @SneakyThrows
    //v1/user/id
    public ResponseEntity<SuccessDto> deleteUser(@RequestParam Long userId) {
        userService.removeUser(userId);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

//    @GetMapping("")
//    //v1/user/id
//    @SneakyThrows
//    public ResponseEntity<UserDto> getUserInfo(OAuth2Authentication authentication) {
//        return new ResponseEntity<>(userService.getUser(Helper.getUser(authentication).getId()), HttpStatus.OK);
//    }
}

