package com.erp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.service.JobPostService;
import com.erp.service.SignUpService;

//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.erp.entity.Institute;
//import com.erp.entity.JobPost;
//import com.erp.service.InstituteService;
//import com.erp.service.JobPostService;
//
//
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class JobPostController {
@Autowired
private SignUpService signUpService;

@Autowired
private JobPostService jobPostService;
	

	/*
	 * @Autowired private JobPostService jobPostService;
	 */
//    
//    @Autowired
//    private InstituteService instituteSignupService;
//    
	/*
	 * @PostMapping("/create") public ResponseEntity<JobPost>
	 * createJobPost(@RequestBody JobPost jobPost) { JobPost savedJobPost =
	 * jobPostService.saveJobPost(jobPost); return ResponseEntity.ok(savedJobPost);
	 * }
	 */
//
//    @GetMapping("/all")
//    public ResponseEntity<List<JobPost>> getAllJobPosts() {
//        List<JobPost> jobPosts = jobPostService.getAllJobPosts();
//        return ResponseEntity.ok(jobPosts);
//    }
//
//    @GetMapping("/{jobId}")
//    public ResponseEntity<JobPost> getJobPostById(@PathVariable Long jobId) {
//        JobPost jobPost = jobPostService.getJobPostById(jobId);
//        return ResponseEntity.ok(jobPost);
//    }
//    
//
//    
//    @PostMapping("/{instituteId}")
//    public ResponseEntity<JobPost> createJobPost(@RequestBody JobPost jobPost, @PathVariable Long instituteId) {
//        JobPost createdJobPost = jobPostService.createJobPost(jobPost, instituteId);
//        if (createdJobPost != null) {
//            return new ResponseEntity<>(createdJobPost, HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or handle differently as needed
//        }
//    }
}

