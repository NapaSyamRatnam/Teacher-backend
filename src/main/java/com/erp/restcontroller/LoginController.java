package com.erp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.entity.Institute;
import com.erp.entity.LoginInstitute;
import com.erp.service.InstituteService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private InstituteService instituteService;

    @PostMapping
    public ResponseEntity<Institute> login(@RequestBody LoginInstitute loginInstitute) {
        String email = loginInstitute.getEmail();
        String phoneNo = loginInstitute.getPhoneNo();

        if ((email != null && !email.isEmpty()) || (phoneNo != null && !phoneNo.isEmpty())) {
            Institute institute = null;
            if (email != null && !email.isEmpty()) {
                institute = instituteService.loginByEmail(email);
            } else if (phoneNo != null && !phoneNo.isEmpty()) {
                institute = instituteService.loginByPhoneNo(phoneNo);
            }
            if (institute != null) {
                return new ResponseEntity<>(institute, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
