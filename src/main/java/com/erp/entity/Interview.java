package com.erp.entity;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Interview")

public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applied_job_id" )
    private AppliedJob appliedJob;

    private LocalDateTime scheduledTime;

    private String interviewerName;

    private String interviewLocation;

    private String interviewType; // e.g., phone, video, in-person

    private String interviewNotes;

    public Interview() {
        // Default constructor
    }

    public Interview(AppliedJob appliedJob, LocalDateTime scheduledTime, String interviewerName, String interviewLocation, String interviewType, String interviewNotes) {
        this.appliedJob = appliedJob;
        this.scheduledTime = scheduledTime;
        this.interviewerName = interviewerName;
        this.interviewLocation = interviewLocation;
        this.interviewType = interviewType;
        this.interviewNotes = interviewNotes;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppliedJob getAppliedJob() {
        return appliedJob;
    }

    public void setAppliedJob(AppliedJob appliedJob) {
        this.appliedJob = appliedJob;
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

	@Override
	public String toString() {
		return "Interview [id=" + id + ", appliedJob=" + appliedJob + ", scheduledTime=" + scheduledTime
				+ ", interviewerName=" + interviewerName + ", interviewLocation=" + interviewLocation
				+ ", interviewType=" + interviewType + ", interviewNotes=" + interviewNotes + "]";
	}
    
    
}

