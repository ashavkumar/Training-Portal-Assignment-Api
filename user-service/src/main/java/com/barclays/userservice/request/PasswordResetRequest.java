package com.barclays.userservice.request;

public class PasswordResetRequest {

	private String username;
	private String oldPassword;
	private String newPassword;
	private String securityQuestion;
	private String securityAnswer;
	public PasswordResetRequest() {
		super();
	}
	public PasswordResetRequest(String username, String oldPassword, String newPassword, String securityQuestion,
			String securityAnswer) {
		super();
		this.username = username;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
}
