package com.erp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.Institute;
import com.erp.entity.JobPost;




@Repository

public interface InstituteSignupRepo extends JpaRepository<Institute, Long> {
	
//	Optional<Institute> findOneByEmailAndPhoneNo(String email, String phoneNo);
//	
//	Institute findByEmail(String email);
	
	Institute findByEmailAndPhoneNo(String email, String phoneNo);
	
	List<Institute> findByName(String name);

	Institute findByEmail(String email);
	
	Institute findByPhoneNo(String phoneNo);
	
	 



	

}
