package com.erp.restcontroller;

import java.util.List;
//import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.DTO.JobPostDto;
//import com.erp.ResourceNotFoundException;
import com.erp.entity.AppliedJob;
import com.erp.entity.JobPost;
import com.erp.entity.SignUpEntity;
//import com.erp.entity.SignUpEntity;
//import com.erp.repository.AppliedJobRepository;
//import com.erp.repository.JobPostRepository;
import com.erp.service.JobService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/jobs")
public class JobController {
    
    @Autowired
    private JobService jobService;
    
    

    
//    
//    @Autowired
//    private AppliedJobRepository appliedJobRepository;
//    
//    @Autowired
//    private JobPostRepository jobPostRepository;
    
    @PostMapping("/post")
    public ResponseEntity<JobPost> postJob(@RequestBody JobPost jobPost) {
    	
     jobService.postJob(jobPost);
        
        return  ResponseEntity.ok(jobPost);
    }
    
    @GetMapping("/jobPosts")
    public ResponseEntity<List<JobPost>> getAllJobPosts() {
        List<JobPost> jobPosts = jobService.getAllJobPosts();
        if (!jobPosts.isEmpty()) {
            return ResponseEntity.ok(jobPosts);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
    @PostMapping("/{jobId}/apply/{userId}")
    public ResponseEntity<JobPost> applyToJob(@PathVariable Long jobId, @PathVariable Long userId) {
        jobService.applyToJob(jobId, userId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{jobId}/applied/{userId}")
    public ResponseEntity<List<AppliedJob>> getAppliedJobsByUserIdAndJobId(@PathVariable Long userId, @PathVariable Long jobId) {
        List<AppliedJob> appliedJobs = jobService.getAppliedJobsByUserIdAndJobId(userId, jobId);
        return ResponseEntity.ok(appliedJobs);
    }
     
    @PostMapping("/create")
    public ResponseEntity<JobPost> createJobPost(@RequestBody JobPost jobPost) {
        JobPost savedJobPost = jobService.saveJobPost(jobPost);
        return ResponseEntity.ok(savedJobPost);
    }
    
    
 // Controller method to fetch unique job posts with associated users
 // Controller method to fetch unique job posts with associated users






    @GetMapping("/{jobId}")
    public ResponseEntity<JobPost> getJobPostById(@PathVariable Long jobId) {
        JobPost jobPost = jobService.getJobPostById(jobId);
        return ResponseEntity.ok(jobPost);
    }
    
    @PostMapping("/JobPost/{instituteId}")
    public ResponseEntity<JobPost> createJobPost(@RequestBody JobPost jobPost, @PathVariable Long instituteId) {
        JobPost createdJobPost = jobService.createJobPost(jobPost, instituteId);
            return new ResponseEntity<>(createdJobPost, HttpStatus.CREATED);
    }
    
    
    
    @GetMapping("/JobPost/{instituteId}")
    public ResponseEntity<List<JobPostDto>> getJobPostsByInstituteId(@PathVariable Long instituteId) {
        List<JobPostDto> jobPosts = jobService.getJobPostsByInstituteId(instituteId);
        if (jobPosts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jobPosts, HttpStatus.OK);
    }
//    
//    @GetMapping("/institutes/{instituteId}/job-posts")
//    public ResponseEntity<List<JobPost>> getJobPostsByInstituteId(@PathVariable Long instituteId) {
//        List<JobPost> jobPosts = jobService.getJobPostsByInstituteId(instituteId);
//        return ResponseEntity.ok(jobPosts);
//    }
//    @GetMapping("/JobPost/{instituteId}")
//    public ResponseEntity<List<JobPost>> getJobPostsByInstitute(@PathVariable Long instituteId) {
//        List<JobPost> jobPosts = jobService.getJobPostsByInstitute(instituteId);
//        if (jobPosts.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(jobPosts, HttpStatus.OK);
//        }
//        
//
//    }
    
    
    @GetMapping("/users/{userId}/applied")
    public List<AppliedJob> getAppliedJobsByUserId(@PathVariable Long userId) {
        return jobService.getAppliedJobsByUserId(userId);
    }


//    
//    @PostMapping("/jobposts/{instituteId}")
//    public ResponseEntity<JobPost> createJobPost(@RequestBody JobPost jobPost, @PathVariable Long instituteId) {
//        JobPost createdJobPost = jobService.createJobPost(jobPost, instituteId);
//        if (createdJobPost != null) {
//            return new ResponseEntity<>(createdJobPost, HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or handle differently as needed
//        }
//    }
    
    @GetMapping("/job/{jobId}/applied-users")
    public ResponseEntity<List<SignUpEntity>> getUsersAppliedForJob(@PathVariable Long jobId) {
        List<SignUpEntity> appliedUsers = jobService.getUsersAppliedForJob(jobId);
        return ResponseEntity.ok(appliedUsers);
    }
//    
//    @GetMapping("/institutes/{instituteId}/applied-jobs")
//    public ResponseEntity<List<AppliedJob>> getAppliedJobsForInstitute(@PathVariable Long instituteId) {
//        List<AppliedJob> appliedJobs = jobService.getAppliedJobsForInstitute(instituteId);
//        return ResponseEntity.ok(appliedJobs);
//    }


}
