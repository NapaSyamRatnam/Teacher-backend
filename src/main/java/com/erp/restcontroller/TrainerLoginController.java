package com.erp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.entity.LoginTrainer;
import com.erp.entity.SignUpEntity;
import com.erp.service.SignUpService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/login")
public class TrainerLoginController {

    @Autowired
    private SignUpService signUpService;
    


//    @PostMapping("/Trainer")
//    public ResponseEntity<SignUpEntity> login(@RequestBody LoginTrainer loginTrainer) {
//        String email = loginTrainer.getEmail();
//        String password = loginTrainer.getPassword();
//
//        if ((email != null && !email.isEmpty()) || (password != null && !password.isEmpty())) {
//            SignUpEntity signUpEntity = null;
//            if (email != null && !email.isEmpty()) {
//                signUpEntity = signUpService.loginByEmail(email);
//            } else if (password != null && !password.isEmpty()) {
//                signUpEntity = signUpService.loginByPassword(password);
//            }
//            if (signUpEntity != null) {
//            	userActivityService.logActivity(loginTrainer.getUserId(), "LOGIN");
//                return new ResponseEntity<>(signUpEntity, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//    }
}
