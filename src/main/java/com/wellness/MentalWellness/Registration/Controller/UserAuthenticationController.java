package com.wellness.mentalWellness.registration.controller;

import com.wellness.mentalWellness.registration.entity.User;
import com.wellness.mentalWellness.registration.service.UserAuthenticationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mental-wellness")
public class UserAuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(UserAuthenticationController.class);

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            userAuthenticationService.registerUser(user);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("User Already Exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User is Successfully Registered", HttpStatusCode.valueOf(200));
    }

    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<String> registerUser(@PathVariable String email, @PathVariable String password) {
        try {
            userAuthenticationService.loginUser(email, password);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>("Either username/password is incorrect", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User is Logged in Successfully", HttpStatusCode.valueOf(200));
    }
}