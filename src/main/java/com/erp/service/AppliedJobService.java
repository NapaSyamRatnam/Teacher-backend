package com.erp.service;

import java.time.LocalDateTime;
import java.util.List;

import com.erp.entity.AppliedJob;
import com.erp.entity.AppliedJobStatus;
import com.erp.entity.JobPost;

public interface AppliedJobService {
	
	List<AppliedJob> getAppliedJobsByJobIdAndUserId(Long jobId, Long userId);

	List<AppliedJob> getAppliedJobsByJobId(Long jobId);
	
	
  
	List<AppliedJob> getAppliedJobsForInstitute(Long instituteId);
	
    List<AppliedJob> getAppliedJobsForInstituteByStatus(Long instituteId, AppliedJobStatus status);
    void selectCandidate(Long appliedJobId);
    void shortlistCandidate(Long appliedJobId);
    void scheduleInterview(Long appliedJobId, LocalDateTime interviewDateTime);

	List<AppliedJob> getAppliedJobsByUserId(Long userId);

	void updateAppliedJobStatus(Long appliedJobId, AppliedJobStatus status);

	AppliedJob findById(Long appliedJobId);

	List<AppliedJob> getAppliedJobsByJobPost(Long jobId);
    	 
    
 
}