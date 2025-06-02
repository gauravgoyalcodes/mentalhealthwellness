package com.wellness.mentalWellness.registration.service;

import com.wellness.mentalWellness.registration.dto.UserAuthenticationRepository;
import com.wellness.mentalWellness.registration.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(UserAuthenticationService.class);

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    public void registerUser(User user) {
        log.info("searching for the user in the database with emailid : " + user.getEmail() + "," + userAuthenticationRepository.findByEmail(user.getEmail()));
        if (userAuthenticationRepository.findByEmail(user.getEmail()) == null) {
            userAuthenticationRepository.save(user);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void loginUser(String emailId, String password) throws IllegalAccessException {
        log.info("searching for the user in the database with emailid : " + emailId + "," + userAuthenticationRepository.findByEmail(emailId));
        if(userAuthenticationRepository.findByEmail(emailId) == null){
            throw new IllegalAccessException();
        }else{
            if(!password.equals(userAuthenticationRepository.findByEmail(emailId).getPassword())){
                throw new IllegalAccessException();
            }
        }
    }
}
