package com.requestdesign.testingservice.controller.auth;

import com.requestdesign.testingservice.dto.auth.UserDto;
import com.requestdesign.testingservice.entity.auth.User;
import com.requestdesign.testingservice.exceptions.auth.LoginIsUserException;
import com.requestdesign.testingservice.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody UserDto addUserDto) {
        try {
            userService.addUser(addUserDto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (LoginIsUserException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }
}
