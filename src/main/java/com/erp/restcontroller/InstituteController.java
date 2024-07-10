package com.erp.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.entity.Institute;
import com.erp.entity.SessionEntity;
import com.erp.service.InstituteService;



@RestController
@CrossOrigin( origins = { "http://localhost:3000"})


@RequestMapping("api/signup")
public class InstituteController {


	
	
//    @Autowired
//    private JobPostService jobPostService;
//
//
//	
//	
//	@PostMapping(path ="/save")
//	public Long saveSignup(@RequestBody InstituteSignupDto signupDto)
//	{
//		Long id = signupService.addSignup(signupDto);
//		return id;
//	}
//	@GetMapping(path = "/{signupId}")
//	public ResponseEntity<InstituteSignupDto> getSignup(@PathVariable Long signupId) {
//	    InstituteSignupDto signupDto = signupService.getSignupById(signupId);
//
//	    if (signupDto != null) {
//	        return ResponseEntity.ok(signupDto);
//	    } else {
//	        return ResponseEntity.notFound().build();
//	    }
//	}
//	
//	  @PostMapping("/{signupId}")
//	    public ResponseEntity<InstituteSignupDto> UpdateSignup(@PathVariable Long signupId, @RequestBody InstituteSignupDto signupDto){
//	        // Assuming signupService has a method to create or update a signup
//	        InstituteSignupDto UpdateSignup = signupService.UpdateSignup(signupId, signupDto);
//	        
//	        if (UpdateSignup != null) {
//	            return ResponseEntity.ok(UpdateSignup);
//	        } else {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//	            // You can modify the status code and message according to your requirement
//	        }
//	    }
//	
//	 @PostMapping(path = "/login")
//	    public ResponseEntity<?> loginSignup(@RequestBody InstituteSigninDto signinDto)
//	    {
//	        LoginResponse loginResponse = signupService.LoginSignup(signinDto);
//	        return ResponseEntity.ok(loginResponse);
//	    }
//	 @GetMapping("/allUsers")
//	 public List<Institute> findAll() {
//	     List<Institute> users = signupService.findAll();
//	     return users;
//	 }
//	 
//	 
//	    @PostMapping("/signup")
//	    public ResponseEntity<Institute> createInstituteSignup(@RequestBody Institute instituteSignup) {
//	        Institute createdInstituteSignup = signupService.createInstituteSignup(instituteSignup);
//	        return new ResponseEntity<>(createdInstituteSignup, HttpStatus.CREATED);
//	    }
	
    @Autowired
    private InstituteService instituteService;
    


    @PostMapping("/signup")
    public ResponseEntity<Institute> createInstitute(@RequestBody Institute institute) {
        Institute createdInstitute = instituteService.createInstitute(institute);
        return new ResponseEntity<>(createdInstitute, HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    public ResponseEntity<Institute> login(@RequestBody LoginInstitute loginInstitute) {
//        String email = loginInstitute.getEmail();
//        String phoneNo = loginInstitute.getPhoneNo();
//
//        if ((email != null && !email.isEmpty()) || (phoneNo != null && !phoneNo.isEmpty())) {
//            Institute institute = null;
//            if (email != null && !email.isEmpty()) {
//                institute = instituteService.loginByEmail(email);
//            } else if (phoneNo != null && !phoneNo.isEmpty()) {
//                institute = instituteService.loginByPhoneNo(phoneNo);
//            }
//            if (institute != null) {
//                return new ResponseEntity<>(institute, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//    }
    
    @PostMapping("/register")
    public ResponseEntity<Institute> register(@RequestBody Institute institute) {
        Institute registeredUser = instituteService.register(institute);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public Institute getInstituteById(@PathVariable Long id) {
        return instituteService.getInstituteById(id);
    }
    
    


    @PostMapping("/login")
    public Institute loginUser(@RequestParam String email, @RequestParam String phoneNo) {
        return instituteService.loginUser(email, phoneNo);
    }

    @PostMapping("/logout")
    public void logoutUser(@RequestParam String email) {
        Institute user = instituteService.findUserByEmail(email);
        if (user != null) {
            instituteService.logoutUser(user);
        }
    }

    @GetMapping("/{email}/sessions")
    public List<SessionEntity> getUserSessions(@PathVariable String email) {
        Institute user = instituteService.findUserByEmail(email);
        if (user != null) {
            return instituteService.getUserSessions(user.getId());
        }
        return null; // or throw an appropriate exception
    }
    

    


    
    @GetMapping("/all")
    public ResponseEntity<List<Institute>> getAllInstitutes() {
        List<Institute> institutes = instituteService.getAllInstitutes();
        return new ResponseEntity<>(institutes, HttpStatus.OK);
    }
    
    

}
