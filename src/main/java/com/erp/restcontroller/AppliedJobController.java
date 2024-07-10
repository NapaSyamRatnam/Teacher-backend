package com.erp.restcontroller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.ResourceNotFoundException;
import com.erp.entity.AppliedJob;
import com.erp.entity.AppliedJobStatus;
import com.erp.entity.JobPost;
import com.erp.entity.SignUpEntity;
import com.erp.repository.AppliedJobRepository;
import com.erp.repository.JobPostRepository;
import com.erp.service.AppliedJobService;
import com.erp.service.JobService;


//AppliedJobController.java
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/applied-jobs")
public class AppliedJobController {
	
    @Autowired
    private JobPostRepository jobPostRepository;
	
    
    private final JobService jobService;
    
    @Autowired
    public AppliedJobController(JobService jobService) {
        this.jobService = jobService;
    }
    
 @Autowired
 private AppliedJobRepository appliedJobRepository;
 
 @Autowired
 private AppliedJobService appliedJobService;

 @GetMapping("/{jobId}/applied/{userId}")
 public boolean isJobAppliedByUser(@PathVariable Long jobId, @PathVariable Long userId) {
     return jobService.isJobAppliedByUser(jobId, userId);
 }

 
 @GetMapping("/job/{jobId}/user/{userId}/applied")
 public ResponseEntity<List<AppliedJob>> getAppliedJobsByJobIdAndUserId(@PathVariable Long jobId, @PathVariable Long userId) {
     List<AppliedJob> appliedJobs = appliedJobService.getAppliedJobsByJobIdAndUserId(jobId, userId);
     if (appliedJobs.isEmpty()) {
         return ResponseEntity.noContent().build();
     } else {
         return ResponseEntity.ok(appliedJobs);
     }
 }

 @GetMapping("/job/{jobId}/applied-users")
 public ResponseEntity<List<AppliedJob>> getAppliedUsersForJob(@PathVariable Long jobId) {
     List<AppliedJob> appliedJobs = appliedJobService.getAppliedJobsByJobId(jobId);
     if (appliedJobs.isEmpty()) {
         return ResponseEntity.noContent().build();
     } else {
         return ResponseEntity.ok(appliedJobs);
     }
 }
 


 @GetMapping("/users/{jobId}")
 public List<SignUpEntity> getUsersAppliedForJob(@PathVariable Long jobId) {
     JobPost job = jobPostRepository.findById(jobId).orElse(null);
     if (job == null) {
         throw new ResourceNotFoundException("Job not found with id: " + jobId);
     }
     List<AppliedJob> appliedJobs = appliedJobRepository.findByJobPost(job);
     return appliedJobs.stream().map(AppliedJob::getSignUpEntity).collect(Collectors.toList());
 }
 
 @GetMapping("/{instituteId}/applied-jobs")
 public ResponseEntity<List<AppliedJob>> getAppliedJobsForInstitute(@PathVariable Long instituteId) {
     List<AppliedJob> appliedJobs = appliedJobService.getAppliedJobsForInstitute(instituteId);
     return ResponseEntity.ok(appliedJobs);
 }
 
 
 @GetMapping("/institutes/{instituteId}/statuses/{status}")
 public ResponseEntity<List<AppliedJob>> getAppliedJobsForInstituteByStatus(@PathVariable Long instituteId,
                                                                            @PathVariable AppliedJobStatus status) {
     List<AppliedJob> appliedJobs = appliedJobService.getAppliedJobsForInstituteByStatus(instituteId, status);
     return ResponseEntity.ok(appliedJobs);
 }
 
 
 @PostMapping("/{appliedJobId}/select")
 public ResponseEntity<String> selectCandidate(@PathVariable Long appliedJobId) {
     appliedJobService.updateAppliedJobStatus(appliedJobId, AppliedJobStatus.SELECTED);
     return ResponseEntity.ok("Candidate selected successfully");
 }

 @PostMapping("/{appliedJobId}/shortlist")
 public ResponseEntity<String> shortlistCandidate(@PathVariable Long appliedJobId) {
     appliedJobService.updateAppliedJobStatus(appliedJobId, AppliedJobStatus.SHORTLISTED);
     return ResponseEntity.ok("Candidate shortlisted successfully");
 }

 @PostMapping("/{appliedJobId}/schedule-interview")
 public ResponseEntity<String> scheduleInterview(@PathVariable Long appliedJobId,
                                                 @RequestParam LocalDateTime interviewDateTime) {
     appliedJobService.updateAppliedJobStatus(appliedJobId, AppliedJobStatus.INTERVIEW_SCHEDULED);
     return ResponseEntity.ok("Interview scheduled successfully");
 }
 
 @GetMapping("/user/{userId}/applied-jobs")
 public ResponseEntity<List<AppliedJob>> getAppliedJobsForUser(@PathVariable Long userId) {
     List<AppliedJob> appliedJobs = appliedJobService.getAppliedJobsByUserId(userId);
     return ResponseEntity.ok(appliedJobs);
 }
 
 @GetMapping("/job/{jobId}")
 public ResponseEntity<List<AppliedJob>> getAppliedJobsByJobPost(@PathVariable Long jobId) {
     List<AppliedJob> appliedJobs = appliedJobService.getAppliedJobsByJobPost(jobId);
     return ResponseEntity.ok(appliedJobs);
 }
 

}



