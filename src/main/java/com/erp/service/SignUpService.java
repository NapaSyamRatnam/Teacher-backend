package com.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.erp.binding.SignUpForm;
import com.erp.entity.Institute;
import com.erp.entity.JobPost;
import com.erp.entity.SignUpEntity;
import com.erp.repository.SignUpRepo;

public interface SignUpService 
{

  
	   String signUp(SignUpForm form);

//	   LoginResponse loginUser(LoginForm form);
	
//	     //method to Login User
//		com.erp.LoginResopnse.LoginMesage loginUser(Login login);
		
//		//method to reset password
//		LoginMesage resetPassword(Login login);
//		
//		//changing password by using otp
//		LoginMesage password(Login login);
		
		// This method is useful for frontend profile update
		SignUpEntity updateUser(SignUpEntity model, Long id);

		String deleteUserById(Long userId);  
		
		List<SignUpEntity> getAllService();
		

		SignUpEntity loginByEmail(String email);
		   SignUpEntity loginByPassword(String password);

		void saveOtp(SignUpEntity email, Long otp);

		 SignUpEntity getUserByEmail(SignUpEntity email);

		SignUpEntity getemail(SignUpEntity email);

		String resetPassword(SignUpEntity email, String newPassword);

		SignUpEntity getOtp(SignUpEntity email);

		SignUpEntity clearOtp(SignUpEntity email);

		SignUpEntity UpdateUser(Long userid, SignUpEntity user);

	 static SignUpEntity findById(Long userid) {
		// TODO Auto-generated method stub
		return null;
	}
			// TODO Auto-generated method stub

	Optional<SignUpEntity> getUserById(Long userId);
	
	SignUpEntity getSignUpEntityById(Long userId);

	SignUpEntity getSignupEntityById(Long userId);

	 SignUpEntity save(SignUpEntity signUpEntity);

	SignUpEntity saveJobPostForUser(Long userId, JobPost jobPost);

	List<JobPost> getSavedJobPostsForUser(Long userId);

	boolean isJobPostAlreadySaved(Long userId, Long jobId);

	List<SignUpEntity> findBySelectedOption(String string);

	

	SignUpEntity loginByEmailAndPassword(String email, String password);
	
	


	

		
	 

}

