package com.wellness.MentalWellness.Registration.Controller;

import com.wellness.MentalWellness.Registration.Entity.User;
import com.wellness.MentalWellness.Registration.Service.UserAuthenticationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
