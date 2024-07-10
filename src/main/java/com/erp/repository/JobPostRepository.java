package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erp.entity.Institute;
import com.erp.entity.JobPost;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {

	
	
	 List<JobPost> findByInstituteId(Long instituteId);
	
    // Define custom query methods if needed



}

