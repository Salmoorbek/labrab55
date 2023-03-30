package com.example.labrab55.controller;

import com.example.labrab55.dto.UserDto;
import com.example.labrab55.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        UserDto registeredUser = userService.registerUser(name, email, password);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}
