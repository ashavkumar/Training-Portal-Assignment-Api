package com.barclays.userservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserCourse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "User_Id")
	private int userId;
	@Column(name = "Course_Id")
	private int courseId;
	private String courseName;
	
	public UserCourse() {
		super();
	}
	
	public UserCourse(int id, int userId, int courseId, String courseName) {
		super();
		this.id = id;
		this.userId = userId;
		this.courseId = courseId;
		this.courseName = courseName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCousreId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "UserCourse [id=" + id + ", userId=" + userId + ", courseId=" + courseId + ", courseName=" + courseName
				+ "]";
	}
	
}
