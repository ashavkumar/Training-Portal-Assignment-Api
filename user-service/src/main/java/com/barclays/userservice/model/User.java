package com.barclays.userservice.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotNull(message = "user name must not be null")
	@Column(name = "User_Name")
	private String userName;
	
	private String password;	
	private String firstName;
	private String lastName;
	private boolean isActive;
	
	@OneToMany(targetEntity = Course.class)
	@JoinColumn(name = "User_Id")
	private Set<Course> setOfCourse= new LinkedHashSet<Course>();
	
	private String role;
	
	public User() {
		super();
	}
	
	public User(int userId, @NotNull(message = "user name must not be null") String userName, String password,
			String firstName, String lastName, boolean isActive, Set<Course> setOfCourse,String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isActive = isActive;
		this.setOfCourse = setOfCourse;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<Course> getSetOfCourse() {
		return setOfCourse;
	}

	public void setSetOfCourse(Set<Course> setOfCourse) {
		this.setOfCourse = setOfCourse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
