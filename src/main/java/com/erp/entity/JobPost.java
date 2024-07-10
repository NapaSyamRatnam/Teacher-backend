package com.erp.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Posting_Job")
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    private Long jobId;   
    private String Role;
    private String Company;
    private String TotalExperience;
    private String RelevantExperience;
    private String Location;
    private String JobDescription;
    private String DesiredSkillSet;
    private String Responsibilities;
    private String AdditionalResponsibilities;
    private String ApplyLastDate;
    private String DatePosted;
    private String Postedby;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id")
    private Institute institute;
    

    
    
    
	public JobPost() {
		super();
		// TODO Auto-generated constructor stub
	}



	public JobPost(String role, String company, String totalExperience, String relevantExperience,
			String location, String jobDescription, String desiredSkillSet, String responsibilities,
			String additionalResponsibilities, String applyLastDate, String datePosted, String postedby) {
		super();
		
		
		this.Role = role;
		this.Company = company;
		this.TotalExperience = totalExperience;
		this.RelevantExperience = relevantExperience;
		this.Location = location;
		this.JobDescription = jobDescription;
		this.DesiredSkillSet = desiredSkillSet;
		this.Responsibilities = responsibilities;
		this.AdditionalResponsibilities = additionalResponsibilities;
		this.ApplyLastDate = applyLastDate;
		this.DatePosted = datePosted;
		this.Postedby = postedby;
	}







	public Long getJobId() {
		return jobId;
	}



	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

















	public Institute getInstitute() {
		return institute;
	}



	public void setInstitute(Institute institute) {
		this.institute = institute;
	}



	public String getRole() {
		return Role;
	}



	public void setRole(String role) {
		Role = role;
	}



	public String getCompany() {
		return Company;
	}



	public void setCompany(String company) {
		Company = company;
	}



	public String getTotalExperience() {
		return TotalExperience;
	}



	public void setTotalExperience(String totalExperience) {
		TotalExperience = totalExperience;
	}



	public String getRelevantExperience() {
		return RelevantExperience;
	}



	public void setRelevantExperience(String relevantExperience) {
		RelevantExperience = relevantExperience;
	}



	public String getLocation() {
		return Location;
	}



	public void setLocation(String location) {
		Location = location;
	}



	public String getJobDescription() {
		return JobDescription;
	}



	public void setJobDescription(String jobDescription) {
		JobDescription = jobDescription;
	}



	public String getDesiredSkillSet() {
		return DesiredSkillSet;
	}



	public void setDesiredSkillSet(String desiredSkillSet) {
		DesiredSkillSet = desiredSkillSet;
	}



	public String getResponsibilities() {
		return Responsibilities;
	}



	public void setResponsibilities(String responsibilities) {
		Responsibilities = responsibilities;
	}



	public String getAdditionalResponsibilities() {
		return AdditionalResponsibilities;
	}



	public void setAdditionalResponsibilities(String additionalResponsibilities) {
		AdditionalResponsibilities = additionalResponsibilities;
	}



	public String getApplyLastDate() {
		return ApplyLastDate;
	}



	public void setApplyLastDate(String applyLastDate) {
		ApplyLastDate = applyLastDate;
	}



	public String getDatePosted() {
		return DatePosted;
	}



	public void setDatePosted(String datePosted) {
		DatePosted = datePosted;
	}



	public String getPostedby() {
		return Postedby;
	}



	public void setPostedby(String postedby) {
		Postedby = postedby;
	}







	@Override
	public String toString() {
		return "JobPost [jobId=" + jobId + ", Role=" + Role + ", Company=" + Company + ", TotalExperience="
				+ TotalExperience + ", RelevantExperience=" + RelevantExperience + ", Location=" + Location
				+ ", JobDescription=" + JobDescription + ", DesiredSkillSet=" + DesiredSkillSet + ", Responsibilities="
				+ Responsibilities + ", AdditionalResponsibilities=" + AdditionalResponsibilities + ", ApplyLastDate="
				+ ApplyLastDate + ", DatePosted=" + DatePosted + ", Postedby=" + Postedby + "]";
	}







    
    
    

    
}
