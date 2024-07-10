package com.erp.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.Institute;
import com.erp.entity.SessionEntity;
import com.erp.repository.InstituteSignupRepo;
import com.erp.repository.SessionRepository;
import com.erp.service.InstituteService;



@Service

public class InstituteImpl implements InstituteService{
	
	@Autowired
	private InstituteSignupRepo signupRepository;
	
	
	  @Autowired
	    private SessionRepository sessionRepository;


	    public Institute loginUser(String email, String phoneNo) {
	        Institute user = signupRepository.findByEmail(email);
	        if (user != null && user.getPhoneNo().equals(phoneNo)) {
	            SessionEntity session = new SessionEntity();
	            session.setUserId(user.getId());
	            session.setLoginTime(LocalDateTime.now());
	            sessionRepository.save(session);
	            return user;
	        }
	        return null;
	    }

	    public void logoutUser(Institute user) {
	        List<SessionEntity> sessions = sessionRepository.findByUserIdOrderByLoginTimeDesc(user.getId());
	        if (!sessions.isEmpty()) {
	            SessionEntity latestSession = sessions.get(0);
	            if (latestSession.getLogoutTime() == null) {
	                latestSession.setLogoutTime(LocalDateTime.now());
	                sessionRepository.save(latestSession);
	            }
	        }
	    }

	    public Institute findUserByEmail(String email) {
	        return signupRepository.findByEmail(email);
	    }

	    public List<SessionEntity> getUserSessions(Long userId) {
	        return sessionRepository.findByUserIdOrderByLoginTimeDesc(userId);
	    }
	

	


    @Override
    public Institute createInstitute(Institute institute) {
        return signupRepository.save(institute);
    }

    @Override
    public List<Institute> getAllInstitutes() {
        return signupRepository.findAll();
    }

    @Override
    public Institute getInstituteById(Long id) {
        return signupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Institute not found with id: " + id));
    }



    
    @Override
    public Institute register(Institute institute) {
        // Perform validation
        // For this example, let's assume validation is successful
        
        // Save user to database
        return signupRepository.save(institute);
    }

    @Override
    public Institute loginByEmailAndPhoneNo(String email, String phoneNo) {
        return signupRepository.findByEmailAndPhoneNo(email, phoneNo);
    }
	
    @Override
    public Institute loginByEmail(String email) {
        return signupRepository.findByEmail(email);
    }
	
    
    @Override
    public Institute loginByPhoneNo(String phoneNo) {
        return signupRepository.findByPhoneNo(phoneNo);
    }





	

	

	


//	@Override
//	public Long addSignup(InstituteSignupDto signupDto) {
//		
//		
//		Institute signup = new Institute(signupDto.getId(),signupDto.getName(),signupDto.getEmail(),signupDto.getPhoneNo(),signupDto.getAddress(),signupDto.getState(),signupDto.getBoardeducation(),signupDto.getWorktype());
//				
////				registrationDto.getId(),
////				registrationDto.getCity(),
////				registrationDto.getConfirmpassword(),
////				registrationDto.getEmail(),
////				registrationDto.getName(),
////				registrationDto.getPhoneNo(),				
////				registrationDto.getId(),				
////				registrationDto.getPassword(),
////				registrationDto.getZip()
//		
//		
// 
//		signupRepository.save(signup);
//		
//		return signup.getId() ;
//		
//		
//	}
//	
//	InstituteSignupDto signupDto;
//
//	private String message;
//
//	private boolean status;
//
//	@Override
//	public LoginResponse LoginSignup(InstituteSigninDto signinDto) {
//		String msg = "";
//		Institute signup1 = signupRepository.findByEmail(signinDto.getEmail());
//		if (signup1 != null) {
//			String phoneNo = signinDto.getPhoneNo();
//			String aphoneNo = signup1.getPhoneNo();
//		Boolean isPhnRight = phoneNo.matches(aphoneNo);
//			if (isPhnRight) {
//				Optional<Institute> signup = signupRepository.findOneByEmailAndPhoneNo(signinDto.getEmail(), signinDto.getPhoneNo());
//				if (signup.isPresent()) {
//					return new LoginResponse( message= "Login Success", status= true);
//				} else {
//					return new LoginResponse( message= "Login Failed", status =false);
//				}
//			} else {
//				return new LoginResponse(message= "Credentials not matched",status= false);
//			}
//		}else {
//			return new LoginResponse(message= "Email not Exists", status= false);
//		}
//		
//	}
//	@Override
//	public InstituteSignupDto getSignupById(Long signupId) {
//	     Optional<Institute> signupOptional = signupRepository.findById(signupId);
//
//	        if (signupOptional.isPresent()) {
//	            Institute signup = signupOptional.get();
//	            InstituteSignupDto signupDto = new InstituteSignupDto();
//	            signupDto.setId(signup.getId());
//	            signupDto.setName(signup.getName());
//	           signupDto.setEmail(signup.getEmail());
//	           signupDto.setPhoneNo(signup.getPhoneNo());
//	           signupDto.setAddress(signup.getAddress());
//
//	            return signupDto;
//	        } else {
//		return null;
//	}
//
//
//	}
//	@Override
//	public List<Institute> findAll() {
//	    List<Institute> users = signupRepository.findAll();
//	    return users;
//	}
//	@Override
//	public InstituteSignupDto UpdateSignup(Long signupId, InstituteSignupDto signupDto) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	@Override
//    public Institute createInstituteSignup(Institute instituteSignup) {
//        return signupRepository.save(instituteSignup);
//    }
//	
//    @Override
//    public Institute getInstituteSignupById(Long id) {
//        // Use Optional to handle the case where the institute signup might not exist
//        Optional<Institute> optionalInstituteSignup = signupRepository.findById(id);
//        
//        // Return the InstituteSignup entity if it exists, otherwise return null
//        return optionalInstituteSignup.orElse(null);
//    }
	


 




	


}
