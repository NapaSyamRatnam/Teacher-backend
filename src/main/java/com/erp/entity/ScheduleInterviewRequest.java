package com.erp.entity;

import java.time.LocalDateTime;

public class ScheduleInterviewRequest {
	
    
    private LocalDateTime scheduledTime;
    private String interviewerName;
    private String interviewLocation;
    private String interviewType;
    private String interviewNotes;
	public ScheduleInterviewRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ScheduleInterviewRequest(LocalDateTime scheduledTime, String interviewerName, String interviewLocation,
			String interviewType, String interviewNotes) {
		super();
		this.scheduledTime = scheduledTime;
		this.interviewerName = interviewerName;
		this.interviewLocation = interviewLocation;
		this.interviewType = interviewType;
		this.interviewNotes = interviewNotes;
	}
	@Override
	public String toString() {
		return "ScheduleInterviewRequest [scheduledTime=" + scheduledTime + ", interviewerName=" + interviewerName
				+ ", interviewLocation=" + interviewLocation + ", interviewType=" + interviewType + ", interviewNotes="
				+ interviewNotes + "]";
	}
	public LocalDateTime getScheduledTime() {
		return scheduledTime;
	}
	public void setScheduledTime(LocalDateTime scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	public String getInterviewerName() {
		return interviewerName;
	}
	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}
	public String getInterviewLocation() {
		return interviewLocation;
	}
	public void setInterviewLocation(String interviewLocation) {
		this.interviewLocation = interviewLocation;
	}
	public String getInterviewType() {
		return interviewType;
	}
	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}
	public String getInterviewNotes() {
		return interviewNotes;
	}
	public void setInterviewNotes(String interviewNotes) {
		this.interviewNotes = interviewNotes;
	}
    
    
    

    
    

}
