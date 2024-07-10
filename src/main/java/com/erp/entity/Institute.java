package com.erp.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Institute")

public class Institute {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	
	@Column(name="Institutename", length = 200)
	private String name;
	
	@Column(name="Email", length = 200)
	private String email;
	
	
	@Column(name="PhoneNo", length = 200)
	private String phoneNo;
	
	@Column(name="Address", length = 200)
	private String address;
	
	@Column(name="State", length = 200)
	private String state;
	
	@Column(name="BOE", length = 200)
	private String boardeducation;
	
	@Column(name="Worktype", length = 200)
	private String worktype;
	
	@JsonManagedReference
    @OneToMany(mappedBy = "institute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<JobPost> jobPosts;



	



	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBoardeducation() {
		return boardeducation;
	}

	public void setBoardeducation(String boardeducation) {
		this.boardeducation = boardeducation;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	
	
	
	
	




	public Set<JobPost> getJobPosts() {
		return jobPosts;
	}

	public void setJobPosts(Set<JobPost> jobPosts) {
		this.jobPosts = jobPosts;
	}

	public Institute( String name, String email, String phoneNo, String address, String state,
			String boardeducation, String worktype) {
		super();
		
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.state = state;
		this.boardeducation = boardeducation;
		this.worktype = worktype;
	}

	public Institute() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Institute [Id=" + Id + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo + ", address="
				+ address + ", state=" + state + ", boardeducation=" + boardeducation + ", worktype=" + worktype
				+ "]";
	}







	
	
}