package com.erp.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.erp.entity.AppliedJob;
import com.erp.entity.AppliedJobStatus;
import com.erp.entity.JobPost;
import com.erp.repository.AppliedJobRepository;
import com.erp.repository.JobPostRepository;
import com.erp.service.AppliedJobService;

import jakarta.persistence.EntityNotFoundException;




@Service
public class AppliedJobImpl implements AppliedJobService {
    private final AppliedJobRepository appliedJobRepository;
    private final JobPostRepository jobPostRepository;

    public AppliedJobImpl(AppliedJobRepository appliedJobRepository, JobPostRepository jobPostRepository) {
        this.appliedJobRepository = appliedJobRepository;
        this.jobPostRepository = jobPostRepository;
    }
    
    
    public List<AppliedJob> getAppliedJobsByJobId(Long jobId) {
        return appliedJobRepository.findByJobPostJobId(jobId);
    }
   

    @Override
    public List<AppliedJob> getAppliedJobsByJobIdAndUserId(Long jobId, Long userId) {
        // Retrieve applied jobs by job id and user id
        return appliedJobRepository.findBySignUpEntityUserIdAndJobPostJobId(userId, jobId);
    }
    
    @Override
    public List<AppliedJob> getAppliedJobsForInstitute(Long instituteId) {
        return appliedJobRepository.findByJobPost_InstituteId(instituteId);
    }
    
    @Override
    public void selectCandidate(Long appliedJobId) {
        AppliedJob appliedJob = appliedJobRepository.findById(appliedJobId)
                .orElseThrow(() -> new EntityNotFoundException("Applied job not found"));
        appliedJob.setStatus(AppliedJobStatus.SELECTED);
        appliedJobRepository.save(appliedJob);
    }
    
    @Override
    public void shortlistCandidate(Long appliedJobId) {
        AppliedJob appliedJob = appliedJobRepository.findById(appliedJobId)
                .orElseThrow(() -> new EntityNotFoundException("Applied job not found"));
        appliedJob.setStatus(AppliedJobStatus.SHORTLISTED);
        appliedJobRepository.save(appliedJob);
    }
    
    @Override
    public void scheduleInterview(Long appliedJobId, LocalDateTime interviewDateTime) {
        AppliedJob appliedJob = appliedJobRepository.findById(appliedJobId)
                .orElseThrow(() -> new EntityNotFoundException("Applied job not found"));
        appliedJob.setStatus(AppliedJobStatus.INTERVIEW_SCHEDULED);
        appliedJob.setInterviewDateTime(interviewDateTime);
        appliedJobRepository.save(appliedJob);
    }

    @Override
    public List<AppliedJob> getAppliedJobsForInstituteByStatus(Long instituteId, AppliedJobStatus status) {
        return appliedJobRepository.findByJobPost_InstituteIdAndStatus(instituteId, status);
    }
    
    @Override
    public void updateAppliedJobStatus(Long appliedJobId, AppliedJobStatus status) {
        // Retrieve the applied job entity from the database
        AppliedJob appliedJob = appliedJobRepository.findById(appliedJobId)
                .orElseThrow(() -> new EntityNotFoundException("Applied job not found with ID: " + appliedJobId));
        
        // Update the status of the applied job
        appliedJob.setStatus(status);

        // Save the updated applied job entity back to the database
        appliedJobRepository.save(appliedJob);
    }

    @Override
    public AppliedJob findById(Long appliedJobId) {
        Optional<AppliedJob> appliedJobOptional = appliedJobRepository.findById(appliedJobId);
        return appliedJobOptional.orElse(null);
    }
    
    
    @Override
    public List<AppliedJob> getAppliedJobsByUserId(Long userId) {
        return appliedJobRepository.findBySignUpEntityUserId(userId);
    }
    
    @Override
    public List<AppliedJob> getAppliedJobsByJobPost(Long jobId) {
        JobPost jobPost = jobPostRepository.findById(jobId).orElse(null);
        if (jobPost == null) {
            throw new RuntimeException("Job post not found for id: " + jobId);
        }
        return appliedJobRepository.findByJobPost(jobPost);
    }
}
