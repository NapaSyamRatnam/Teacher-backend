package com.erp.restcontroller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.DTO.ScheduledInterviewDTO;
import com.erp.entity.ScheduleInterviewRequest;
import com.erp.service.AppliedJobService;
import com.erp.service.InterviewService;

@RestController
@CrossOrigin( origins = { "http://localhost:3000"})
@RequestMapping("/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;
    
    @Autowired
    private AppliedJobService appliedJobService;
    

    @PostMapping("/schedule/{appliedJobId}")
    public ResponseEntity<String> scheduleInterview(@PathVariable Long appliedJobId,
                                                    @RequestParam String scheduledTime) {
        try {
            // Parse scheduledTime string to LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(scheduledTime);

            // Schedule the interview
            interviewService.scheduleInterview(appliedJobId, dateTime);

            return ResponseEntity.ok("Interview scheduled successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error scheduling interview: " + e.getMessage());
        }
    }
   
//    @PostMapping("/schedule")
//    public ResponseEntity<String> scheduleInterview(@RequestParam Long appliedJobId,
//                                                    @RequestParam String scheduledTime) {
//        try {
//            // Parse scheduledTime string to LocalDateTime
//            LocalDateTime dateTime = LocalDateTime.parse(scheduledTime);
//
//            // Schedule the interview
//            interviewService.scheduleInterview(appliedJobId, dateTime);
//
//            return ResponseEntity.ok("Interview scheduled successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error scheduling interview: " + e.getMessage());
//        }
//    }
    
    
    @PostMapping("/interview/schedule/{appliedJobId}")
    public ResponseEntity<String> scheduleInterview(@PathVariable Long appliedJobId, @RequestBody ScheduleInterviewRequest interviewRequest) {
        try {
            interviewService.scheduleInterview(appliedJobId, interviewRequest);
            return ResponseEntity.ok("Interview scheduled successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error scheduling interview: " + e.getMessage());
        }
    }
    
    @GetMapping("/interview/schedule/{appliedJobId}")
    public ResponseEntity<List<ScheduledInterviewDTO>> getScheduledInterviewsByAppliedJobId(@PathVariable Long appliedJobId) {
        try {
            List<ScheduledInterviewDTO> scheduledInterviews = interviewService.getScheduledInterviewsByAppliedJobId(appliedJobId);
            return ResponseEntity.ok(scheduledInterviews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Handle error appropriately
        }
    }
}

