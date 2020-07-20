package com.barclays.userservice.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotNull(message = "user name must not be null")
	@Column(name = "User_Name")
	private String userName;
	@NotNull(message = "password must not be null")
	private String password;	
	@NotNull(message = "first name must not be null")
	private String firstName;
	@NotNull(message = "last name must not be null")
	private String lastName;
	@NotNull(message = "Email must not be null")         
	// ([a-zA-Z0-9_\\-\\.]+)
	// ([a-zA-Z0-9_\\-\\.]+)
	// ([a-zA-Z]{2,5})$")
	@Pattern(regexp="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
	private String mailId;
	private boolean isActive;
	@ElementCollection
	private Set<Integer> courses= new LinkedHashSet<Integer>();
	
	private String role;
	
	public User() {
		super();
	}

	public User(int userId, @NotNull(message = "user name must not be null") String userName, String password,
			String firstName, String lastName,String mailId, boolean isActive, Set<Integer> courses, String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mailId=mailId;
		this.isActive = isActive;
		this.courses = courses;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	  public Set<Integer> getCourses() { return courses; }
	  
	  public void setCourse(Set<Integer> courses) { this.courses = courses; }
	 

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
}
