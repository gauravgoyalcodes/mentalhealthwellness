package com.wellness.mentalWellness.moodTracker.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;


public class MoodTrackingAtrributes {

    @JsonProperty(value = "dateOfRecord")
    private LocalDate dateOfRecord = LocalDate.now();
    @JsonProperty(value = "moodScore")
    private Integer moodScore; //score ranging 1-10 where one being the sad and 10 being the happiest
    @JsonProperty(value = "notes")
    private String notes;

    public LocalDate getDateOfRecord() {
        return dateOfRecord;
    }

    public void setDateOfRecord(LocalDate dateOfRecord) {
        this.dateOfRecord = dateOfRecord;
    }

    public Integer getMoodScore() {
        return moodScore;
    }

    public void setMoodScore(Integer moodScore) {
        this.moodScore = moodScore;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "MoodTrackingAtrributes{" +
                "dateOfRecord=" + dateOfRecord +
                ", moodScore=" + moodScore +
                ", notes='" + notes + '\'' +
                '}';
    }
}
