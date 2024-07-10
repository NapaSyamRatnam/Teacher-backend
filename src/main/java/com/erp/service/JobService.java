package com.erp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.DTO.JobPostDto;
import com.erp.entity.AppliedJob;
import com.erp.entity.Institute;
import com.erp.entity.JobPost;
import com.erp.entity.SignUpEntity;
import com.erp.repository.AppliedJobRepository;
import com.erp.repository.InstituteSignupRepo;
import com.erp.repository.JobPostRepository;
import com.erp.repository.SignUpRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class JobService {
    
    @Autowired
    private JobPostRepository jobPostRepository;
    
    @Autowired
    private AppliedJobRepository appliedJobRepository;
    
    @Autowired
    private SignUpRepo signUpRepo;
    
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private InstituteSignupRepo instituteSignupRepo;
    
    public void postJob(JobPost jobPost) {
        jobPostRepository.save(jobPost);
    }
    
    public void applyToJob(Long jobId, Long userId) {
    	
        List<AppliedJob> userAppliedJobs = appliedJobRepository.findBySignUpEntityUserId(userId);
        boolean userAlreadyApplied = userAppliedJobs.stream()
                                                    .anyMatch(appliedJob -> appliedJob.getJobPost().getJobId().equals(jobId));

        // If the user has already applied, throw an exception
        if (userAlreadyApplied) {
            throw new IllegalStateException("User has already applied for this job");
        }
       
        JobPost jobPost = jobPostRepository.findById(jobId)
                .orElseThrow(() -> new EntityNotFoundException("Job not found"));

        SignUpEntity signUpEntity = signUpRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        
 
    
        
        AppliedJob appliedJob = new AppliedJob();
        appliedJob.setJobPost(jobPost);
        appliedJob.setSignUpEntity(signUpEntity);
        
        appliedJobRepository.save(appliedJob);
        
 
    }
    
//    public List<AppliedJob> getAppliedJobsForInstitute(Long instituteId) {
//        // Retrieve the job posts for the institute
//        List<JobPost> instituteJobPosts = jobPostRepository.findByInstituteId(instituteId);
//
//        // Retrieve applied jobs for each job post
//        List<AppliedJob> appliedJobs = appliedJobRepository.findByJobPostJobId(instituteId);
//
//        return appliedJobs;
//    }
    
    public List<SignUpEntity> getUsersAppliedForJob(Long jobId) {
        List<AppliedJob> appliedJobs = appliedJobRepository.findByJobPostJobId(jobId);
        List<SignUpEntity> users = new ArrayList<>();

        for (AppliedJob appliedJob : appliedJobs) {
            Long userId = appliedJob.getSignUpEntity().getUserId();
            SignUpEntity user = signUpService.getSignUpEntityById(userId); // Assuming you have a service method to fetch user by ID
            users.add(user);
        }

        return users;
    }
    

    
    public List<AppliedJob> getAppliedJobsByUserIdAndJobId(Long userId, Long jobId) {
        return appliedJobRepository.findBySignUpEntityUserIdAndJobPostJobId(userId, jobId);
    }

	public List<JobPost> getAllJobPosts() {
		// TODO Auto-generated method stub
		return jobPostRepository.findAll();
	}
	
	 public List<AppliedJob> getAppliedJobsByUserId(Long userId) {
	        return appliedJobRepository.findBySignUpEntityUserId(userId);
	    }


	    public JobPost saveJobPost(JobPost jobPost) {
	        return jobPostRepository.save(jobPost);
	    }


	    


	    public JobPost getJobPostById(Long jobId) {
	        return jobPostRepository.findById(jobId)
	                .orElseThrow(() -> new EntityNotFoundException("JobPost with id " + jobId + " not found"));
	    }
	    
	    
	  


	    
	    public JobPost createJobPost(JobPost jobPost) {
	        // You can include additional validation logic here before saving the job post
	        return jobPostRepository.save(jobPost);
	    }
	    
	    
	    
//	    public JobPost createJobPost(JobPost jobPost, Long instituteId) {
//	        System.out.println("Inside createJobPost method");
//	        try {
//	            // Associate job post with institute
//	            Optional<Institute> optionalInstitute = instituteSignupRepo.findById(instituteId);
//	            if (optionalInstitute.isPresent()) {
//	                Institute institute = optionalInstitute.get();
//	                jobPost.setInstitute(institute);
//
//	                // Save job post to database
//	                JobPost savedJobPost = jobPostRepository.save(jobPost);
//	                System.out.println("Job post saved successfully");
//	                return savedJobPost;
//	            } else {
//	                System.out.println("Institute with ID " + instituteId + " not found");
//	                return null;
//	            }
//	        } catch (Exception e) {
//	            System.err.println("Error creating job post: " + e.getMessage());
//	            e.printStackTrace();
//	            return null;
//	        }
//	    }
	    
	    
	    
	    public JobPost createJobPost(JobPost jobPost, Long instituteId) {
	        System.out.println("Inside createJobPost method");
	        
	        // Fetch institute by ID
	        Optional<Institute> optionalInstitute = instituteSignupRepo.findById(instituteId);
	        Institute institute = optionalInstitute.orElseThrow(() -> new EntityNotFoundException("Institute with ID " + instituteId + " not found"));
	        
	        // Associate job post with institute
	        jobPost.setInstitute(institute);

	        // Save job post to database
	        JobPost savedJobPost = jobPostRepository.save(jobPost);
	        System.out.println("Job post saved successfully");
	        return savedJobPost;
	    }
	    


	    
	    public List<JobPostDto> getJobPostsByInstituteId(Long instituteId) {
	        List<JobPost> jobPosts = jobPostRepository.findByInstituteId(instituteId);
	        return jobPosts.stream().map(this::convertToDTO).collect(Collectors.toList());
	    }
	    
	    
	    private JobPostDto convertToDTO(JobPost jobPost) {
	        JobPostDto dto = new JobPostDto();
	        dto.setAdditionalResponsibilities(jobPost.getAdditionalResponsibilities());
	        dto.setApplyLastDate(jobPost.getApplyLastDate());
	        dto.setCompany(jobPost.getCompany());
	        dto.setDatePosted(jobPost.getDatePosted());
	        dto.setDesiredSkillSet(jobPost.getDesiredSkillSet());
	        dto.setJobDescription(jobPost.getJobDescription());
	        dto.setLocation(jobPost.getLocation());
	        dto.setRelevantExperience(jobPost.getRelevantExperience());
	        dto.setResponsibilities(jobPost.getResponsibilities());
	        dto.setRole(jobPost.getRole());
	        dto.setTotalExperience(jobPost.getTotalExperience());
	        dto.setJobId(jobPost.getJobId());
	        dto.setPostedby(jobPost.getPostedby());
	        dto.setInstituteId(jobPost.getInstitute().getId());
	        return dto;
	        
}
	    public boolean isJobAppliedByUser(Long jobId, Long userId) {
	        return appliedJobRepository.existsByJobPostJobIdAndSignUpEntityUserId(jobId, userId);
	    }
}

