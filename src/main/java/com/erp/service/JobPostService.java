package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.JobPost;
import com.erp.repository.JobPostRepository;

//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.erp.entity.Institute;
//import com.erp.entity.JobPost;
//import com.erp.repository.InstituteSignupRepo;
//import com.erp.repository.JobPostRepository;
//
//import jakarta.persistence.EntityNotFoundException;

@Service
public class JobPostService {

    @Autowired
    private JobPostRepository jobPostRepository;
    
   
    public JobPost save(JobPost jobPost) {
        return jobPostRepository.save(jobPost);
    }
//    
//    @Autowired
//    private InstituteSignupRepo instituteSignupRepo;
//    
//
//
//
//
//    public JobPost saveJobPost(JobPost jobPost) {
//        return jobPostRepository.save(jobPost);
//    }
//
//    public List<JobPost> getAllJobPosts() {
//        return jobPostRepository.findAll();
//    }
//    
//
//
//    public JobPost getJobPostById(Long jobId) {
//        return jobPostRepository.findById(jobId)
//                .orElseThrow(() -> new EntityNotFoundException("JobPost with id " + jobId + " not found"));
//    }
//    
//    
//  
//
//
//    
//    public JobPost createJobPost(JobPost jobPost) {
//        // You can include additional validation logic here before saving the job post
//        return jobPostRepository.save(jobPost);
//    }
//    
//    
//    public JobPost createJobPost(JobPost jobPost, Long instituteId) {
//        Optional<Institute> optionalInstitute = instituteSignupRepo.findById(instituteId);
//        if (optionalInstitute.isPresent()) {
//            Institute institute = optionalInstitute.get();
//            jobPost.setInstitute(institute);
//            return jobPostRepository.save(jobPost);
//        } else {
//            // Handle case where institute with given id is not found
//            return null;
//        }
//    }

}

