package com.erp.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.erp.binding.SignUpForm;
import com.erp.entity.Institute;
import com.erp.entity.JobPost;
import com.erp.entity.SessionEntity;
import com.erp.entity.SignUpEntity;
import com.erp.repository.SessionRepository;
import com.erp.repository.SignUpRepo;
import com.erp.service.SignUpService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private SignUpRepo repo;

	@Autowired
	private JavaMailSender mailService;
	
//	  @Autowired
//	    private SessionRepository sessionRepository;

	@Override
	public List<SignUpEntity> getAllService() {
		return repo.findAll();
	}
	
    @Override
    public List<SignUpEntity> findBySelectedOption(String selectedOption) {
        return repo.findBySelectedOption(selectedOption);
    }

//    public SignUpEntity loginUser(String email, String password) {
//        SignUpEntity user = repo.findByEmail(email);
//        if (user != null && user.getPassword().equals(password)) {
//            SessionEntity session = new SessionEntity();
//            session.setUserId(user.getUserId());
//            session.setLoginTime(LocalDateTime.now());
//            sessionRepository.save(session);
//            return user;
//        }
//        return null;
//    }
//
//    public void logoutUser(SignUpEntity user) {
//        List<SessionEntity> sessions = sessionRepository.findByUserIdOrderByLoginTimeDesc(user.getUserId());
//        if (!sessions.isEmpty()) {
//            SessionEntity latestSession = sessions.get(0);
//            if (latestSession.getLogoutTime() == null) {
//                latestSession.setLogoutTime(LocalDateTime.now());
//                sessionRepository.save(latestSession);
//            }
//        }
//    }
	// Registration method
	@Override
	public String signUp(SignUpForm form) {
		System.out.println("Entered into service ");
		if (form.getPassword().equals(form.getConfirmPassword())) {
			if (!repo.existsByEmail(form.getEmail())) {
				SignUpEntity entity = new SignUpEntity();
				System.out.println("Entered into if ");
				BeanUtils.copyProperties(form, entity);
				System.out.println("Entered into save ");
				repo.save(entity);
				System.out.println("Entered after save if ");
				return "Registration Successful";
			} else {
				return "Entered Email is Already Exists";
			}
		}
		return "Password and confirm password do not match";
	}
	
//    public SignUpEntity registerUser(SignUpEntity user) {
//        SignUpEntity users = new SignUpEntity();
//        users.setEmail(email);
//        users.setPassword(password);
//        return repo.save(user);
//    }

	// Login by Email / Phone Number.
//	@Override
//	public LoginResponse loginUser(LoginForm form) {
//		SignUpEntity user = null;
//
//		if (form.getIdentifier() != null) {
//			// Check if the identifier is an email
//			user = repo.findByEmailAndPassword(form.getIdentifier(), form.getPassword());
//
//			// If not found, check if the identifier is a mobile number
//			if (user == null) {
//				user = repo.findByMobileNumberAndPassword(form.getIdentifier(), form.getPassword());
//			}
//		}
//
//		if (user != null) {
//			return new LoginResponse(true, "Login Successful", user.getUserId());
//		} else {
//			return new LoginResponse(false, "Invalid Credentials", null);
//		}
//	}

//	@Override
//	public boolean forgetpassword(String email) {
//		SignUpEntity findByEmail = repo.findByEmail(email);
//
//		if (findByEmail == null) {
//			return false;
//		}
//		String subject = "recover password";
//		String body = "your password::" + findByEmail.getPassword();
//		emailUtil.sendEmail(email, subject, body);
//		return true;
//
//	}

	// method to update the User using User-Id.

	@Override
	public SignUpEntity updateUser(@RequestBody SignUpEntity modal, @PathVariable Long userId) {
		Optional<SignUpEntity> userOptional = repo.findById(userId);

		if (userOptional.isPresent()) {

			SignUpEntity userToBeUpdated = userOptional.get();
			// System.out.println("---> UserFromDB:"+userToBeUpdated);
			userToBeUpdated.setUserId(modal.getUserId());
			userToBeUpdated.setFirstName(modal.getFirstName());
			userToBeUpdated.setLastName(modal.getLastName());
			userToBeUpdated.setEmail(modal.getEmail());
			userToBeUpdated.setMobileNumber(modal.getMobileNumber());
			userToBeUpdated.setAddress(modal.getAddress());
			userToBeUpdated.setCountry(modal.getCountry());
			userToBeUpdated.setState(modal.getState());
			userToBeUpdated.setPassword(modal.getPassword());

			repo.save(userToBeUpdated);
			return userToBeUpdated;
		}
		return userOptional.orElseThrow();
	}

	public String deleteUserById(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return "success";
		} else {
			return "NO RECORD FOUND";
		}

	}

	public SignUpEntity findById(Long id) {
		Optional<SignUpEntity> findById = repo.findById(id);
		if (findById.isPresent()) {
			SignUpEntity user = findById.get();
			return user;
		}
		return null;

	}

//	//method to reset password if user forget their old password &  Generate New Password.
//	 String otp;
//	 Long id;
//	 
//	 public LoginMesage resetPassword(Login login) throws MailException { 
//		 
//		   SignUpEntity mail1;
//			mail1 = repo.findByEmail(login.getEmailId());
//			
//		   if(mail1 != null) {
//			   String mail = mail1.getEmail();
//			   String m =login.getEmailId();
//			   
//		  if(mail.equals(m)) 
//		    {  
//			  SimpleMailMessage message = new SimpleMailMessage();
//			  message.setFrom("muppuri47@gmail.com");
//				message.setTo(mail);
//				message.setSubject("Password Reset Request for Your ERP Tally Account ");
//				Random random =  new Random();
//				 otp =(Integer.toString( random.nextInt(999999)));
//				 // String body= "Greetings From ERP Tally Website ......!\n You are Requested for Reset Password\n Please enter Your OTP .... "+otp+" Don't Share This One Time Password ";
//				
//				String body = "\nGreetings from ERP Tally Website! You have requested a password reset for your account.\r\n"
//
//				 +"\nTo complete the process, please enter the following One Time Password (OTP) : "+otp+"\r\n"
//
//				 +"\nPlease ensure to keep this OTP confidential and refrain from sharing it with anyone." +"\n\nIf you did not initiate this password reset or have any concerns, please contact our support team immediately. \r\n"
//
//				 +"\nThank you for choosing ERP Tally!\r\n"
//
//				+" \nBest regards, \r\n"
//				+ "ERP Tally Support Team \r\n";
//
//				 message.setText(body);
//			   mailService.send(message);
//				   id = mail1.getUserId();
//			   return new LoginMesage("OTP Send to E-Mail check it Once",true);
//		  }
//		  else {
//			  return new LoginMesage("E-Mail Entered Not Matched With Data-Base E-mails Plz Try Again ",false);
//		  }
//		   }
//		   else {
//			   return new LoginMesage("E-Mail is Not Exist Plz Try Again ",false);
//		   }
//	  }
//	 
//	 //method to change password by using OTP
//		
//	  public LoginMesage password(Login login) {
//		  
//		  if(login.getOtp().equals(otp)) {
//			  SignUpEntity mail1 = repo.findByEmail(login.getEmailId());
//			    
//			  Optional<SignUpEntity> model = repo.findByUserId(id);
//			  SignUpEntity models = model.get();
//			  String mail2 =models.getEmail();	
//			  
////			  java.util.Base64.Encoder  encoder= Base64.getEncoder();
////			  String encodepassword=encoder.encodeToString(login.getPassword().getBytes());
////			  models.setPassword(encodepassword);
//			  
//			  models.setPassword(login.getPassword());
//			  
////			  java.util.Base64.Encoder  encoder1= Base64.getEncoder();
////			  String encodeConfirmpassword=encoder1.encodeToString(login.getConfirmPassword().getBytes());
////		       models.setConfirmPassword(encodeConfirmpassword);
//
//			  models.setConfirmPassword(login.getConfirmPassword());
//			  repo.save(models);
//			  	   
//				  SimpleMailMessage message = new SimpleMailMessage();
//					message.setTo(mail2);
//					message.setSubject("Password For ERP Tally Website Changed");
//					Random random =  new Random();
//					int atIndex = mail2.indexOf('@');
//					 String username = mail2.substring(0, atIndex);
//					
//				//	 String body= "Welcome To ERP Tally Website " + username  +"\n You are New  Password is "+login.getPassword()+" \nPlease be login with your New Password \n Don't Share this Password ";
//				String body="\nWelcome to ERP Tally Website! We're delighted to have you on board. \r\n\n"+username+" Your new password is: "+login.getPassword()
//
//					+ "\r\nPlease use this password to log in to your account. \n\nRemember to keep it confidential and avoid sharing it with anyone.\r\n"
//
//					 +"\r\nIf you have any questions or need assistance, feel free to reach out to our support team.\r\n"
//
//					 +"\r\nThank you for choosing ERP Tally!\r\n"
//
//					+ "\nBest regards,\r\n"
//					+ "ERP Tally Support Team\r\n";
//					 message.setText(body);
//				   mailService.send(message);
//				   
//			  return  new LoginMesage("Password Reset Successfully",true);
//		  }
//		  else {
//			  return new LoginMesage("OTP is Incorrect  ",false);
//		  }
//     }

	public boolean existsByEmail(String email) {
		return repo.existsByEmail(email);
	}

	public boolean existsByMobileNo(String mobileNo) {
		return repo.existsByMobileNumber(mobileNo);
	}

	@Override
	public void saveOtp(SignUpEntity email, Long otp) {
		// TODO Auto-generated method stub

	}

	@Override
	public SignUpEntity getUserByEmail(SignUpEntity email) {
		// TODO Auto-generated method stub
		return email;
	}

//		@Override
//		public String getUserByEmail(String email) {
//			// TODO Auto-generated method stub
//			return email;
//		}

	@Override
	public SignUpEntity getemail(SignUpEntity email) {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public String resetPassword(SignUpEntity email, String newPassword) {
		return newPassword;

	}

	@Override
	public SignUpEntity getOtp(SignUpEntity email) {
		return email;
	}

	@Override
	public SignUpEntity clearOtp(SignUpEntity email) {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public SignUpEntity UpdateUser(Long userid, SignUpEntity user) {
		// TODO Auto-generated method stub
		return user;
	}

	
    public SignUpEntity getSignUpById(Long userId) {
        return repo.findById(userId).orElse(null);
    }

	@Override
	public Optional<SignUpEntity> getUserById(Long userId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
    @Override
    public SignUpEntity getSignUpEntityById(Long userId) {
        // Call the UserRepository method to fetch the user by ID
        Optional<SignUpEntity> optionalUser = repo.findById(userId);
        return optionalUser.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    }
    
    public SignUpEntity getSignupEntityById(Long userId) {
        return repo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
    
    
    @Override
    public SignUpEntity save(SignUpEntity signUpEntity) {
        return repo.save(signUpEntity);
    }
    
    @Override
    public SignUpEntity saveJobPostForUser(Long userId, JobPost jobPost) {
        SignUpEntity user = getSignUpEntityById(userId);
        user.getSavedJobPosts().add(jobPost);
        return repo.save(user);
    }

    @Override
    public List<JobPost> getSavedJobPostsForUser(Long userId) {
        return getSignUpEntityById(userId).getSavedJobPosts();
    }
    
    
    @Override
    public boolean isJobPostAlreadySaved(Long userId, Long jobId) {
        Optional<SignUpEntity> signUpEntityOptional = repo.findById(userId);
        if (signUpEntityOptional.isPresent()) {
            SignUpEntity signUpEntity = signUpEntityOptional.get();
            // Assuming job posts are stored in a collection within the SignUpEntity
            return signUpEntity.getSavedJobPosts().stream().anyMatch(jobPost -> jobPost.getJobId().equals(jobId));
        }
        return false; // Return false if the user is not found or the job post is not saved
    }
    
    
    @Override
    public SignUpEntity loginByEmailAndPassword(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }
	
    @Override
    public SignUpEntity loginByEmail(String email) {
        return repo.findByEmail(email);
    }
	
    
//    @Override
//    public SignUpEntity loginByPassword(String password) {
//        return repo.findByPassword(password);
//    }

    public SignUpEntity findByFirstName(String firstName) {
        return repo.findByFirstName(firstName);
    }

//	@Override
//	public SignUpEntity findUserByEmail(String email) {
//		// TODO Auto-generated method stub
//		return repo.findUserByEmail(email);
//	}
	
//    public List<SessionEntity> getUserSessions(Long userId) {
//        return sessionRepository.findByUserIdOrderByLoginTimeDesc(userId);
//    }

@Override
public SignUpEntity loginByPassword(String password) {
	// TODO Auto-generated method stub
	return null;
}


}