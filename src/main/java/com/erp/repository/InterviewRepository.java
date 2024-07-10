package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.DTO.ScheduledInterviewDTO;
import com.erp.entity.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
	
    @Query("SELECT new com.erp.DTO.ScheduledInterviewDTO(i.appliedJob.signUpEntity.userId, " +
            "i.appliedJob.signUpEntity.firstName, i.appliedJob.signUpEntity.lastName, " +
            "i.appliedJob.signUpEntity.email, i.appliedJob.jobPost.jobId, " +
            "i.appliedJob.jobPost.Company, i.appliedJob.jobPost.Role) " +
            "FROM Interview i WHERE i.appliedJob.id = :appliedJobId")
    List<ScheduledInterviewDTO> findScheduledInterviewsByAppliedJobId(Long appliedJobId);
	
}

