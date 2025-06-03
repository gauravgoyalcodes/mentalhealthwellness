package com.wellness.mentalWellness.moodTracker.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "mood-tracking-data")
public class MoodTrackingData {
    @Id
    private String id;
    private String email;
    private ArrayList<MoodTrackingAtrributes> moodTrackingAtrributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<MoodTrackingAtrributes> getMoodTrackingAtrributes() {
        return moodTrackingAtrributes;
    }

    public void setMoodTrackingAtrributes(ArrayList<MoodTrackingAtrributes> moodTrackingAtrributes) {
        this.moodTrackingAtrributes = moodTrackingAtrributes;
    }

    @Override
    public String toString() {
        return "MoodTrackingData{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", moodTrackingAtrributes=" + moodTrackingAtrributes +
                '}';
    }
}
