package com.barclays.trainingsite.response;

public class UserResponse<T> {
	private String message;
	private T  t;
	public UserResponse() {
		super();
	}
	public UserResponse(String message, T t) {
		super();
		this.message = message;
		this.t = t;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	
}
