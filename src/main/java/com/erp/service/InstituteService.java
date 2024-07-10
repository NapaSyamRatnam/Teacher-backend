package com.erp.service;

import java.util.List;

import com.erp.entity.Institute;
import com.erp.entity.SessionEntity;



public interface InstituteService {
	
	

    Institute loginByEmailAndPhoneNo (String email, String phoneNo);
  

    Institute register(Institute institute);

//	List<Institute> findAll();
//
//
//
//	 Institute createInstituteSignup(Institute instituteSignup);
//	 
//	
//		    Institute getInstituteSignupById(Long id);
    
    Institute createInstitute(Institute institute);
    List<Institute> getAllInstitutes();
    Institute getInstituteById(Long id);


	Institute loginByEmail(String email);
   Institute loginByPhoneNo(String phoneNo);


Institute loginUser(String email, String phoneNo);


Institute findUserByEmail(String email);


void logoutUser(Institute user);


List<SessionEntity> getUserSessions(Long id);

  


		

}
