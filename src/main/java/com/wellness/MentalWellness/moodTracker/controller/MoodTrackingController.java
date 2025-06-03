package com.wellness.mentalWellness.moodTracker.controller;

import com.wellness.mentalWellness.moodTracker.entity.MoodTrackingAtrributes;
import com.wellness.mentalWellness.moodTracker.entity.MoodTrackingData;
import com.wellness.mentalWellness.moodTracker.service.MoodTrackingService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RestController
@RequestMapping("/mental-wellness/mood-tracking")
public class MoodTrackingController {
    private static final Logger log = LoggerFactory.getLogger(MoodTrackingController.class);

    @Autowired
    private MoodTrackingService moodTrackingService;

    @PostMapping("/{email}")
    public ResponseEntity<String> saveMoodTrackingScore(@PathVariable String email, @RequestBody MoodTrackingAtrributes moodTrackingAtrributes) {
        log.info("received mailid for the user : " + email);
        moodTrackingService.saveMoodTrackingData(email, moodTrackingAtrributes);
        return new ResponseEntity<>("Mood checked-in successfully", HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> GetMoodTrackingData(@PathVariable String email, @RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) LocalDate endDate) {
        if (startDate == null && endDate == null) {
            startDate = LocalDate.now().minusDays(6);
            endDate = LocalDate.now();
            log.info("user is looking for the data between these given dates : " + startDate + endDate);
        }
        double averageMoodTrackingscore = 0;
        try {
            averageMoodTrackingscore = moodTrackingService.getMoodTrackingDataOfLastWeek(email, startDate, endDate);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("issue due to null pointer exception", HttpStatus.BAD_REQUEST);
        } catch (ArithmeticException ex) {
            return new ResponseEntity<>("Issue due to Arithmatic exception", HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("Your Mood Score : ", averageMoodTrackingscore);
        map.put("How you looks based on your mood score : ", moodTrackingService.moodBasedOnMoodScore(averageMoodTrackingscore));
        return ResponseEntity.ok(map);
    }
}
