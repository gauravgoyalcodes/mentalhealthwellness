package com.wellness.MentalWellness.Registration.DTO;

import com.wellness.MentalWellness.Registration.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistration extends MongoRepository<User, String> {

    User findByEmail(String email);
}
