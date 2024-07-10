package com.erp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.entity.SignUpEntity;

public interface SignUpRepo extends JpaRepository<SignUpEntity, Long> {

	public SignUpEntity findByEmailAndPassword(String email, String password);

	public SignUpEntity findByMobileNumberAndPassword(String mobile, String password);

	public boolean existsByEmail(String email);

//	public SignUpEntity findByEmail(String email); 
		
	public SignUpEntity findByMobileNumber(String mobile);

	public Optional<SignUpEntity> findByUserId(long id);

	boolean existsByMobileNumber(String mobileNo);

	public String deleteByUserId(Long id);
	

	public List<SignUpEntity> findBySelectedOption(String selectedOption);


	  SignUpEntity findByEmail(String email);

	public SignUpEntity findByPassword(String password);



	public SignUpEntity findByFirstName(String firstName);

	public SignUpEntity findUserByEmail(String email);
	
}

