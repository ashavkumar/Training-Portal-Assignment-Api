package com.barclays.courseservice.service;

import java.util.List;

import com.barclays.courseservice.exception.CourseNotFoundException;
import com.barclays.courseservice.model.Course;

public interface CourseService {
	
	public Course registerCourse(Course course);
	public Course getCourse(int cId) throws CourseNotFoundException;
	public List<Course> getAllCourses();
	public void removeCourse(int courseId);
	public void updateCourse(int courseId,Course course) throws CourseNotFoundException;
}
