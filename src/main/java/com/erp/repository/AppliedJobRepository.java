package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.AppliedJob;
import com.erp.entity.AppliedJobStatus;
import com.erp.entity.JobPost;

@Repository
public interface AppliedJobRepository extends JpaRepository<AppliedJob, Long> {
	
	List<AppliedJob> findBySignUpEntityUserIdAndJobPostJobId(Long userId, Long jobId);

	

	List<AppliedJob> findByJobPostJobId(Long jobId);

	List<AppliedJob> findByJobPost(JobPost jobPost);

	List<AppliedJob> findBySignUpEntityUserId(Long userId);

	List<AppliedJob> findByJobPost_InstituteId(Long instituteId);

//	 

	List<AppliedJob> findByJobPost_InstituteIdAndStatus(Long instituteId, AppliedJobStatus status);
//	    List<AppliedJob> findBySignUpEntity(SignUpEntity signUpEntity);
//	    List<AppliedJob> findByJobPost_InstituteIdAndStatus(Long instituteId, AppliedJobStatus status);



	boolean existsByJobPostJobIdAndSignUpEntityUserId(Long jobId, Long userId);



	

}
