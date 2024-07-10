package com.erp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.DTO.ScheduledInterviewDTO;
import com.erp.entity.AppliedJob;
import com.erp.entity.Interview;
import com.erp.entity.Notification;
import com.erp.entity.ScheduleInterviewRequest;
import com.erp.repository.InterviewRepository;
import com.erp.repository.NotificationRepository;

import jakarta.transaction.Transactional;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    private AppliedJobService appliedJobService;

    @Transactional
    public void scheduleInterview(Long appliedJobId, LocalDateTime scheduledTime) {
        // Retrieve the AppliedJob from the database using appliedJobId
        AppliedJob appliedJob = appliedJobService.findById(appliedJobId);

        if (appliedJob != null) {
            // Create a new Interview entity
            Interview interview = new Interview();
            interview.setAppliedJob(appliedJob);
            interview.setScheduledTime(scheduledTime);

            // Save the Interview entity
            interviewRepository.save(interview);

            // Notify user about the interview
            String message = "Your interview for the job post '" + appliedJob.getJobPost().getRole() + " at " + appliedJob.getJobPost().getCompany() +
                    "' is scheduled on " + scheduledTime.toString();
            notificationService.sendNotification(appliedJob.getSignUpEntity(), message);
        } else {
            // Handle the case where the AppliedJob with the given ID is not found
            // You can throw an exception, return a response, or handle it based on your application logic
        }
    }
    
//    public void scheduleInterviewsForInstituteJobs(Long instituteId, LocalDateTime scheduledTime, String interviewerName, String interviewLocation, String interviewType, String interviewNotes) {
//        // Retrieve applied jobs associated with job posts from the institute
//        List<AppliedJob> appliedJobs = appliedJobService.getAppliedJobsForInstitute(instituteId);
//
//        // Iterate over the retrieved applied jobs
//        for (AppliedJob appliedJob : appliedJobs) {
//            // Create an interview entity
//            Interview interview = new Interview();
//            interview.setAppliedJob(appliedJob);
//            interview.setScheduledTime(scheduledTime);
//            interview.setInterviewerName(interviewerName);
//            interview.setInterviewLocation(interviewLocation);
//            interview.setInterviewType(interviewType);
//            interview.setInterviewNotes(interviewNotes);
//
//            // Save the interview entity
//            interviewRepository.save(interview);
//
//            // You can also send notifications to users here if needed
//            // notificationService.sendNotification(appliedJob.getSignUpEntity(), "Your interview has been scheduled.");
//            
//            String message = "Your interview for the job post '" + appliedJob.getJobPost().getRole() + "' at '" + appliedJob.getJobPost().getCompany() + "' has been scheduled.";
//
//            // Send notification to the user
//            notificationService.sendNotification(appliedJob.getSignUpEntity(), message);
//        }
//        }
    
    public void scheduleInterview(Long appliedJobId, ScheduleInterviewRequest interviewRequest) {
        // Retrieve the AppliedJob from the database using appliedJobId
        AppliedJob appliedJob = appliedJobService.findById(appliedJobId);

        if (appliedJob != null) {
            // Create a new Interview entity
            Interview interview = new Interview();
            interview.setAppliedJob(appliedJob);
            interview.setScheduledTime(interviewRequest.getScheduledTime());
            interview.setInterviewerName(interviewRequest.getInterviewerName());
            interview.setInterviewLocation(interviewRequest.getInterviewLocation());
            interview.setInterviewType(interviewRequest.getInterviewType());
            interview.setInterviewNotes(interviewRequest.getInterviewNotes());

            // Save the Interview entity
            interviewRepository.save(interview);

            // Notify user about the interview
            String message = "Your interview for the job role '" + appliedJob.getJobPost().getRole() +
                    "' at " + appliedJob.getJobPost().getCompany() + " is scheduled on " +
                    interviewRequest.getScheduledTime().toString()  + ".Your Interviewer Name is " + interviewRequest.getInterviewerName() + ". The Mode of interview will be " + interviewRequest.getInterviewType() + " at " + interviewRequest.getInterviewLocation() +
                    " Note: " + interviewRequest.getInterviewNotes();
            notificationService.sendNotification(appliedJob.getSignUpEntity(), message);
        } else {
            // Handle the case where the AppliedJob with the given ID is not found
            // You can throw an exception, return a response, or handle it based on your application logic
        }
    }

	public void scheduleInterviewRequest(AppliedJob appliedJob, LocalDateTime dateTime) {
		// TODO Auto-generated method stub
		
	}
	
    public List<ScheduledInterviewDTO> getScheduledInterviewsByAppliedJobId(Long appliedJobId) {
        return interviewRepository.findScheduledInterviewsByAppliedJobId(appliedJobId);
    }
	
   
}

