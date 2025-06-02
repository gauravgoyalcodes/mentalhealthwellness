package com.wellness.mentalWellness.registration.dto;

import com.wellness.mentalWellness.registration.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthenticationRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}