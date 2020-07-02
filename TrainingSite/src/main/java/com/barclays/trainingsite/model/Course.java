package com.barclays.trainingsite.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	
	private String courseName;
	private double courseCost;
	private String courseType;
	private String courseDiscription;
	private boolean isActive;
	public Course() {
		super();
	}
	public Course(int courseId, String courseName, double courseCost, String courseType, String courseDiscription,
			boolean isActive) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseCost = courseCost;
		this.courseType = courseType;
		this.courseDiscription = courseDiscription;
		this.isActive = isActive;
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
	public double getCourseCost() {
		return courseCost;
	}
	public void setCourseCost(double courseCost) {
		this.courseCost = courseCost;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getCourseDiscription() {
		return courseDiscription;
	}
	public void setCourseDiscription(String courseDiscription) {
		this.courseDiscription = courseDiscription;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
