package com.barclays.trainingsite.service;

import java.util.List;

import com.barclays.trainingsite.exception.CourseNotFoundException;
import com.barclays.trainingsite.model.Course;
import com.barclays.trainingsite.model.User;
import com.barclays.trainingsite.model.UserCourse;
import com.barclays.trainingsite.response.UserResponse;

public interface CourseService {
	
	public Course registerCourse(Course course);
	public Course getCourse(int cId) throws CourseNotFoundException;
	public List<Course> getAllCourses();
	public UserResponse<Course> purchaseCourse(UserCourse userCourse);
	
	public List<User> courseWiseSubscription(int courseId);
	
	public void deleteCourse(int courseId);
	public void updateCourse(int courseId,Course course) throws CourseNotFoundException;
}
