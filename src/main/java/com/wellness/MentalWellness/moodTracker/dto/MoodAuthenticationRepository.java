package com.wellness.mentalWellness.moodTracker.dto;

import com.wellness.mentalWellness.moodTracker.entity.MoodTrackingData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodAuthenticationRepository extends MongoRepository<MoodTrackingData, String> {
    public MoodTrackingData findByEmail(String email);
}
