package com.wellness.MentalWellness.Registration.Service;

import com.wellness.MentalWellness.Registration.Controller.UserAuthenticationController;
import com.wellness.MentalWellness.Registration.DTO.UserRegistration;
import com.wellness.MentalWellness.Registration.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(UserAuthenticationController.class);

    @Autowired
    private UserRegistration userRegistration;

    public void registerUser(User user) {
        log.info("searching for the email id in the database for the emailid : " + user.getEmail() + " " + userRegistration.findByEmail(user.getEmail()));
        if (userRegistration.findByEmail(user.getEmail()) == null) {
            userRegistration.save(user);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
