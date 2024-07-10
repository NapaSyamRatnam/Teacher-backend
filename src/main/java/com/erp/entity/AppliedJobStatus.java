package com.erp.entity;

public enum AppliedJobStatus {
	
    APPLIED("Your application for the job at %s has been received."),
    SELECTED("Congratulations! You have been selected for the job."),
    SHORTLISTED("You have been shortlisted for further consideration."),
    INTERVIEW_SCHEDULED("An interview has been scheduled for you for the job.");

	AppliedJobStatus(String string) {
		// TODO Auto-generated constructor stub
	}
}
