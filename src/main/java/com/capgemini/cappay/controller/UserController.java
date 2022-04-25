package com.capgemini.cappay.controller;

import com.capgemini.cappay.dto.UserDto;
import com.capgemini.cappay.model.Account;
import com.capgemini.cappay.model.User;
import com.capgemini.cappay.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Operation(summary = "Register a new user")
    public ResponseEntity<User> save(@RequestBody UserDto userDto) {
        User createdUser = userService.save(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    @Operation(summary = "Returns all registered users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
}
