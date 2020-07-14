package com.barclays.userservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CourseRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int courseId;
	private String courseName;
	private int userId;
	private String status;
	
	public CourseRequest() {
		super();
	}
	public CourseRequest(int id, int courseId, String courseName, int userId, String status) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.courseName = courseName;
		this.userId = userId;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CourseRequest [id=" + id + ", courseId=" + courseId + ", courseName=" + courseName + ", userId="
				+ userId + ", status=" + status + "]";
	}
	
}
