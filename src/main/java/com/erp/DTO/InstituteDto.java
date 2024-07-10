package com.erp.DTO;

public class InstituteDto {

	private Long Id;
	
	
	private String name;
	
	
	private String email;
	
	
	private String phoneNo;
	
	
	private String address;
	
	
	private String state;
	
	
	private String boardeducation;
	
	
	private String worktype;


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
	
	
	
	
}
