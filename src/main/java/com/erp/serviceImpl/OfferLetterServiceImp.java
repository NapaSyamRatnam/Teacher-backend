package com.erp.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.AppliedJob;
import com.erp.entity.JobPost;
import com.erp.entity.OfferLetterEntity;
import com.erp.repository.AppliedJobRepository;
import com.erp.repository.JobPostRepository;
import com.erp.repository.OfferLetterRepo;
import com.erp.service.OfferLetterService;
import com.erp.utils.EmailUtils;
import com.erp.utils.OfferLetterPdfGeneratorUtils;

@Service
public class OfferLetterServiceImp implements OfferLetterService {

	@Autowired
	private OfferLetterRepo offerletterRepo;

	@Autowired
	private AppliedJobRepository appliedJobRepository;

	@Autowired
	private JobPostRepository jobPostRepository;

	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private OfferLetterPdfGeneratorUtils offerLetterUtils;


	@Override
	public List<OfferLetterEntity> getAll() {
		return offerletterRepo.findAll();
	}





	@Override
    public String sendToOfferLetter(OfferLetterEntity offerLetter, Long appliedId) throws IOException {
        // Find job post by job id
        AppliedJob appliedjob = appliedJobRepository.findById(appliedId).orElse(null);
        if (appliedjob == null) {
            return "Applied Job not found";
        }

        // Generate PDF for offer letter using data from offerLetter object
        byte[] pdfBytes;
        try {
            pdfBytes = offerLetterUtils.generateOfferLetterPDF(offerLetter, appliedjob);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error generating PDF";
        }

        // Send email with offer letter attachment
        boolean emailSent = emailUtils.sendEmailAttachment(offerLetter.getEmail(), "Offer Letter", "Please find attached offer letter.", pdfBytes, "offer_letter.pdf");

        if (emailSent) {
            // Save offer entity after sending offer letter
            offerLetter.setPdfContent(pdfBytes);
            offerLetter.setAppliedJob(appliedjob);
            offerletterRepo.save(offerLetter);
            return "Offer Letter sent successfully";
        } else {
            return "Error sending Offer Letter";
        }
    }
}