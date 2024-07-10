package com.erp.restcontroller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.erp.binding.SignUpForm;
import com.erp.entity.FileEntity;
import com.erp.entity.JobPost;
import com.erp.entity.SessionEntity;
import com.erp.entity.SignUpEntity;
import com.erp.repository.SignUpRepo;
import com.erp.service.FileEntityService;
import com.erp.service.SignUpService;



@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/prava")
public class SingUpController {

    @Autowired
    private SignUpRepo repo;

	@Autowired
	private SignUpService service;
	
  
	
    @Autowired
    private FileEntityService fileEntityService;
	
	
	@Autowired
	private JavaMailSender emailSender;
	
	@GetMapping("/all")
    public List<SignUpEntity> getAllService() {
        List<SignUpEntity> poj = service.getAllService();
        return poj;
	}
//	@GetMapping("/user/{userid}")
//	public SignUpEntity findById(@PathVariable Long userid){
//		return SignUpService.findById(userid);
//	}
	
    @GetMapping("/user/{userId}")
    public SignUpEntity getSignUpEntityById(@PathVariable Long userId) {
        return service.getSignUpEntityById(userId);
    }
	
	  @PostMapping("/{userid}")
	    public SignUpEntity UpdateUser(@PathVariable Long userid, @RequestBody SignUpEntity user){
	        return service.UpdateUser(userid, user);
	    }

	@PostMapping("/save")
	public ResponseEntity<String> signUp( @RequestBody SignUpForm form) {
		System.out.println("Name"+form.getFirstName());
		String save = service.signUp(form);
		System.out.println("Password "+form.getPassword());
		System.out.println(form.getConfirmPassword());
		System.out.println(save);		
		return new ResponseEntity<String>(save, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/company/{companyId}")
	public String deleteUserById(@PathVariable Long companyId) {
		return service.deleteUserById(companyId);
	}


	@PutMapping({ "/user/{userId}" })
	public ResponseEntity<SignUpEntity> updateUser(@PathVariable Long id, @RequestBody SignUpEntity modal) {
		System.out.println("UserId is :"+id);
		System.out.println("User:"+modal);
		service.updateUser(modal, id);
		return new ResponseEntity<SignUpEntity>(modal, HttpStatus.ACCEPTED);
	}

		
//		@GetMapping("/email/{emailId}")
//		public SignUpEntity viewUserbyEmail(@PathVariable String emailId){
//			return service.getUserbyEmail(emailId);
//		}
//
//			
//	@PutMapping("/reset")
//	public LoginMesage resetPassword(@RequestBody Login login ) {
//		LoginMesage msg = service.resetPassword(login);
//		return msg;
//	}
//		
//	
//	@PutMapping("/password")
//	public LoginMesage newpassword(@RequestBody Login login) {
//		LoginMesage msg = service.password(login);
//		return msg;	
//	}
			
	@PostMapping("/send-reset-email")
    public ResponseEntity<SignUpEntity> sendResetEmail(@RequestBody SignUpEntity email) {
        try {
            SignUpEntity user = service.getUserByEmail(email);
            if (user == null) {
                return new ResponseEntity<>( HttpStatus.NOT_FOUND);
            }

            Long otp = generateOTP();
            System.out.println("Entered into reset email");
            sendEmail(email, otp);

            service.saveOtp(email, otp);

            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/verify-reset-otp")
	public ResponseEntity<SignUpEntity> verifyResetOtp(@RequestParam SignUpEntity email, @RequestParam Long otp, @RequestParam String newPassword) {
	    try {
	        SignUpEntity user = service.getUserByEmail(email);
	        if (user == null) {
	            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	        }

	        SignUpEntity storedOtp = service.getOtp(email);

	        if (storedOtp == null || !otp.equals(storedOtp)) {
	            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
	        }

	        // Reset password logic
	        service.resetPassword(email, newPassword);

	        // Clear the OTP after successful verification
	        service.clearOtp(email);

	        return new ResponseEntity<>( HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	 private Long generateOTP() {
	        Random random = new Random();
	        int otp = 100000 + random.nextInt(900000); // Generates a random number between 100000 and 999999
	        return Long.valueOf(otp);
	    }

	private void sendEmail(SignUpEntity email, Long otp) {
	
		try {
			System.out.println("Entered into Send Email.");
			SimpleMailMessage message = new SimpleMailMessage();
			String sendMail = email.getEmail();
	        message.setTo(sendMail);
	        message.setSubject("Password Reset OTP");
	        message.setText("Your one-time password (OTP) for password reset is: " + otp);
	        emailSender.send(message);
		}catch (Exception e) {
			System.out.println(e);
			System.out.println("Error sending email: " + e.getMessage());
			System.err.println(e);
			// TODO: handle exception
		}
        
    }

    @PostMapping("/{userId}/saved-jobposts")
    public ResponseEntity<?> saveJobPostForUser(@PathVariable Long userId, @RequestBody JobPost jobPost) {
        // Check if the job post has already been saved by the user
        if (service.isJobPostAlreadySaved(userId, jobPost.getJobId())) {
            return ResponseEntity.badRequest().body("This job post is already saved by the user.");
        }

        // Save the job post for the user
        service.saveJobPostForUser(userId, jobPost);
        return ResponseEntity.ok("Job post saved successfully.");
    }

    @GetMapping("/{userId}/saved-jobposts")
    public List<JobPost> getSavedJobPostsForUser(@PathVariable Long userId) {
        return service.getSavedJobPostsForUser(userId);
    }
	
    @GetMapping("/getall/{selectedOption}")
    public List<SignUpEntity> getDataBySelectedOption(@PathVariable String selectedOption) {
        List<SignUpEntity> data;
        
        // Fetch data based on selected option
        if ("Teachers".equalsIgnoreCase(selectedOption)) {
            data = service.findBySelectedOption("Teachers");
        } else if ("Skilled Trainers".equalsIgnoreCase(selectedOption)) {
            data = service.findBySelectedOption("Skilled Trainers");
        } else if ("Religious Trainers".equalsIgnoreCase(selectedOption)) {
            data = service.findBySelectedOption("Religious Trainers");
        } else {
            // Handle invalid option or return all data
            data = service.getAllService();
        }
        
        return data;
    }
    
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
        String fileName = fileEntityService.storeFile(file);
        SignUpEntity signUpEntity = service.getSignupEntityById(userId);
        if (signUpEntity != null) {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(fileName);
            fileEntity.setContentType(file.getContentType());
            try {
                fileEntity.setData(file.getBytes());
            } catch (IOException e) {
                return ResponseEntity.badRequest().body("Failed to read file data.");
            }
            signUpEntity.setFile(fileEntity);
            service.save(signUpEntity);
            return ResponseEntity.ok().body("File uploaded successfully for user: " + userId);
        } else {
            return ResponseEntity.badRequest().body("User not found with id: " + userId);
        }
    }

//    @GetMapping("/file")
//    public ResponseEntity<byte[]> getFile(@RequestParam("userId") Long userId) {
//        SignUpEntity signUpEntity = service.getSignUpEntityById(userId);
//        if (signUpEntity != null && signUpEntity.getFile() != null) {
//            FileEntity fileEntity = signUpEntity.getFile();
//            return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType(fileEntity.getContentType()))
//                    .body(fileEntity.getData());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    
    @GetMapping("/user/{userId}/file")
    public ResponseEntity<String> getFileDetails(@PathVariable("userId") Long userId) {
        SignUpEntity signUpEntity = service.getSignupEntityById(userId);
        if (signUpEntity != null && signUpEntity.getFile() != null) {
            FileEntity fileEntity = signUpEntity.getFile();
            return ResponseEntity.ok().body("User ID: " + userId + " has a file uploaded: " + fileEntity.getFileName() + ", Content Type: " + fileEntity.getContentType());
        } else if (signUpEntity != null) {
            return ResponseEntity.ok().body("User ID: " + userId + " has no file uploaded.");
        } else {
            return ResponseEntity.badRequest().body("User not found with id: " + userId);
        }
    }


//    @PostMapping("/log")
//    public SignUpEntity loginUser(@RequestParam String email, @RequestParam String password) {
//        return service.loginUser(email, password);
//    }
//
//    @PostMapping("/logout")
//    public void logoutUser(@RequestParam String email) {
//        SignUpEntity user = service.findUserByEmail(email);
//        if (user != null) {
//            service.logoutUser(user);
//        }
//    }
//
//
//    @GetMapping("/{email}/sessions")
//    public List<SessionEntity> getUserSessions(@PathVariable String email) {
//        SignUpEntity user = service.findUserByEmail(email);
//        if (user != null) {
//            return service.getUserSessions(user.getUserId());
//        }
//        return null; // or throw an appropriate exception
//    }



}