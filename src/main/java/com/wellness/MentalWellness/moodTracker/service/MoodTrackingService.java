package com.wellness.mentalWellness.moodTracker.service;

import com.wellness.mentalWellness.moodTracker.dto.MoodAuthenticationRepository;
import com.wellness.mentalWellness.moodTracker.entity.MoodTrackingAtrributes;
import com.wellness.mentalWellness.moodTracker.entity.MoodTrackingData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoodTrackingService {
    private static final Logger log = LoggerFactory.getLogger(MoodTrackingService.class);

    @Autowired
    private MoodAuthenticationRepository moodAuthenticationRepository;

    public void saveMoodTrackingData(String email, MoodTrackingAtrributes moodTrackingAtrributes) {
        MoodTrackingData moodTrackingData = new MoodTrackingData();
        if (moodAuthenticationRepository.findByEmail(email) == null) {
            ArrayList<MoodTrackingAtrributes> l1 = new ArrayList<>();
            l1.add(moodTrackingAtrributes);
            moodTrackingData.setEmail(email);
            moodTrackingData.setMoodTrackingAtrributes(l1);
        } else {
            moodTrackingData = moodAuthenticationRepository.findByEmail(email);
            moodTrackingData.getMoodTrackingAtrributes().add(moodTrackingAtrributes);
        }
        moodAuthenticationRepository.save(moodTrackingData);
    }

    public double getMoodTrackingDataOfLastWeek(String email, LocalDate startDate, LocalDate endDate) {
        ArrayList<MoodTrackingAtrributes> listOfMoodTrackingAttributes = moodAuthenticationRepository.findByEmail(email).getMoodTrackingAtrributes();
        log.info("List of all the moods tracked : " + listOfMoodTrackingAttributes);
        LocalDate finalStartDate = startDate.minusDays(1);
        LocalDate finalEndDate = endDate.plusDays(1);

        List<MoodTrackingAtrributes> filteredList = new ArrayList<>();
        try {
            filteredList = listOfMoodTrackingAttributes
                    .stream()
                    .filter(obj ->
                    {
                        LocalDate date = obj.getDateOfRecord();
                        return date.isAfter(finalStartDate) && date.isBefore(finalEndDate);
                    }).collect(Collectors.toList());

        } catch (NullPointerException e) {

        }
        log.info("Filtered list of Mood Tracking attributes : " + filteredList);

        Integer sumOfMoodScores = 0;
        for (MoodTrackingAtrributes m : filteredList) {
            sumOfMoodScores += m.getMoodScore();
        }
        double averageMoodScore = 0.0;
        try {
            log.info("total sum of mood scores : " + sumOfMoodScores);
            log.info("No of days being tracked : " + filteredList.toArray().length);
            averageMoodScore = Math.round(((double) sumOfMoodScores) / filteredList.toArray().length);
        } catch (ArithmeticException e) {
        }

        return averageMoodScore;
    }

    public String moodBasedOnMoodScore(Double moodScore) {
        if (moodScore <= 3) {
            return "low mood, potentially including feelings of sadness, anxiety, or depression";
        } else if (moodScore > 3 && moodScore <= 7) {
            return "a neutral or slightly positive mood, with feelings ranging from okay to pleasant";
        } else {
            return "a positive mood, potentially including feelings of happiness, joy, or elation";
        }
    }
}
