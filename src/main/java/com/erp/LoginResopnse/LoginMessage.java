package com.erp.LoginResopnse;

public class LoginMessage {

	
	private String message;
	private String Status;
	public LoginMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginMessage(String message, String status) {
		super();
		this.message = message;
		Status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	@Override
	public String toString() {
		return "LoginMessage [message=" + message + ", Status=" + Status + "]";
	}
	
	
}
