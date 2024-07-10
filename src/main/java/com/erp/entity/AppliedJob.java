package com.erp.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//@Entity
//public class AppliedJob {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private SignUpEntity signUpEntity;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "job_post_id")
//    private JobPost jobPost;
//
//	public AppliedJob() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public AppliedJob(SignUpEntity signUpEntity, JobPost jobPost) {
//		super();
//		this.signUpEntity = signUpEntity;
//		this.jobPost = jobPost;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public SignUpEntity getSignUpEntity() {
//		return signUpEntity;
//	}
//
//	public void setSignUpEntity(SignUpEntity signUpEntity) {
//		this.signUpEntity = signUpEntity;
//	}
//
//	public JobPost getJobPost() {
//		return jobPost;
//	}
//
//	public void setJobPost(JobPost jobPost) {
//		this.jobPost = jobPost;
//	}

    // Constructors, getters, and setters...
    

@Entity
@Table(name = "Applied_jobs_Institute")
public class AppliedJob {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id")
    private JobPost jobPost;
    

    

    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private SignUpEntity signUpEntity;
    
  
    @JsonManagedReference
    @OneToMany(mappedBy = "appliedJob", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Interview> interviews;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "appliedJob", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OfferLetterEntity> appliedJob;

  














	

















	public List<Interview> getInterviews() {
		return interviews;
	}














	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}














	@Enumerated(EnumType.STRING)
    private AppliedJobStatus status;

	public AppliedJob() {
		super();
		// TODO Auto-generated constructor stub
	}






















	public AppliedJob(JobPost jobPost, SignUpEntity signUpEntity, AppliedJobStatus status) {
		super();
		this.jobPost = jobPost;
		this.signUpEntity = signUpEntity;
		
		this.status = status;
	}














	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}





	public SignUpEntity getSignUpEntity() {
		return signUpEntity;
	}



	public void setSignUpEntity(SignUpEntity signUpEntity) {
		this.signUpEntity = signUpEntity;
	}




	
	public JobPost getJobPost() {
		return jobPost;
	}







	public void setJobPost(JobPost jobPost) {
		this.jobPost = jobPost;
	}














	public AppliedJobStatus getStatus() {
		return status;
	}














	public void setStatus(AppliedJobStatus status) {
		this.status = status;
	}














	public void setInterviewDateTime(LocalDateTime interviewDateTime) {
		// TODO Auto-generated method stub
		
	}














	@Override
	public String toString() {
		return "AppliedJob [id=" + id + ", jobPost=" + jobPost + ", signUpEntity=" + signUpEntity + ", interviews="
				+ interviews + ", status=" + status + "]";
	}


	


    
    // Other fields like application status, resume, etc.
    
    // Constructors, getters, setters
    
}

