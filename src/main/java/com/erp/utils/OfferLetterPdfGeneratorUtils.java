package com.erp.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import com.erp.entity.AppliedJob;
import com.erp.entity.OfferLetterEntity;

	@Component
	public class OfferLetterPdfGeneratorUtils {

	    public byte[] generateOfferLetterPDF(OfferLetterEntity offerLetterEntity, AppliedJob appliedJob) throws IOException {
	        try (PDDocument document = new PDDocument()) {
	            PDPage page = new PDPage();
	            document.addPage(page);

	            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
	                // Set font and size for offer letter heading
	                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 22);
	                contentStream.setLeading(20); // Set line spacing

	                // Calculate position for centered text
	                float textWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("Offer Letter") / 1000f * 22f;
	                float centeredXPosition = (page.getMediaBox().getWidth() - textWidth) / 2;

	                // Write offer letter heading
	                contentStream.beginText();
	                contentStream.newLineAtOffset(centeredXPosition, 700);
	                contentStream.showText("Offer Letter");
	                contentStream.newLineAtOffset(0, -40); // Adjust spacing
	                contentStream.endText();

	                // Set font and size for content
	                contentStream.setFont(PDType1Font.HELVETICA, 12);

	                // Write date
	                contentStream.beginText();
	                contentStream.newLineAtOffset(50, 650);
	                contentStream.showText("Date: " + LocalDate.now());
	                contentStream.newLineAtOffset(0, -20);

	                // Write recipient's name
	                contentStream.showText("Dear " + appliedJob.getSignUpEntity().getFirstName() + ",");
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.endText();

	                // Write offer letter content
	                contentStream.beginText();
	                contentStream.newLineAtOffset(50, 590); // Adjust position
	                contentStream.showText("We are pleased to offer you employment at " + appliedJob.getJobPost().getCompany() + ", effective " + offerLetterEntity.getDateOfJoining() + ".");
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("You have been selected for the position of " + appliedJob.getJobPost().getRole() + ".");
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("Your compensation for this position will be " + offerLetterEntity.getSalary() + ", which includes Details of Benefits, Bonuses, etc.");
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("if applicable. "+"Please join within deadline " + offerLetterEntity.getDeadline() + ".");
	                contentStream.newLineAtOffset(0, -20);
	            
	                contentStream.showText("Your primary responsibilities will include:");
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("- Brief Description of Job Responsibilities" + appliedJob.getJobPost().getAdditionalResponsibilities());
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("- [Additional Responsibilities as Needed]");
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("Your employment with " + appliedJob.getJobPost().getCompany() + " will be at-will, which means that either you or the company ");
	                contentStream.newLineAtOffset(0, -20);
	               
	               
	                contentStream.showText("may terminate your employment at any time and for any reason, with or without cause.");
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("Please review the attached Employee Handbook for more detailed information regarding ");
	                //
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("company policies and procedures. ");
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("If you accept this offer, please sign and return this letter by " + offerLetterEntity.getDeadline() + ". ");
	                
	                //
	                contentStream.showText("If you have any questions or concerns,"
	                		+ " please do not hesitate to contact HR Manager/Point of Contact.");
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("We look forward to welcoming you to the team!");
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.endText();

	                // Set font and size for signature
	                contentStream.beginText();
	                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
	                contentStream.newLineAtOffset(50, 120); // Adjust position
	                contentStream.showText("Campany Info");
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.setFont(PDType1Font.HELVETICA, 12);
	                contentStream.showText("Talent acquisition Head");
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("");
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText("  " +appliedJob.getJobPost().getRole() );
	                contentStream.newLineAtOffset(0, -20);
	                contentStream.showText(" " + appliedJob.getJobPost().getCompany());
	                contentStream.endText();
	            }

	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            document.save(baos);
	            return baos.toByteArray();
	        }
	    }
	}
