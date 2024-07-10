package com.erp.restcontroller;

import java.io.IOException;
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

import com.erp.entity.OfferLetterEntity;
import com.erp.service.OfferLetterService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api")
public class OfferLetterController {
	
	@Autowired
	private OfferLetterService offerService;
	
///sendOfferLetter/{appliedJobId}

	    @GetMapping("/getAllOfferletters")
	    public ResponseEntity<List<OfferLetterEntity>> getAllOfferLetters() {
	        List<OfferLetterEntity> offerLetters = offerService.getAll();
	        return new ResponseEntity<>(offerLetters, HttpStatus.OK);
	    }
	    
	    @PostMapping("/sendOfferLetter/{appliedJobId}")
	    public ResponseEntity<String> sendOfferLetter(@RequestBody OfferLetterEntity offerLetter,
	                                                  @PathVariable Long appliedJobId) {
	        try {
	            String result = offerService.sendToOfferLetter(offerLetter,  appliedJobId);
	            return ResponseEntity.ok(result);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending Offer Letter: " + e.getMessage());
	        }
	    }
	    
	    
	

}
