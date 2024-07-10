package com.erp.service;

import java.io.IOException;
import java.util.List;

import com.erp.entity.OfferLetterEntity;

public interface OfferLetterService {


	
	public List<OfferLetterEntity> getAll();

	public String sendToOfferLetter(OfferLetterEntity offerLetter, Long jobId) throws IOException;

}
