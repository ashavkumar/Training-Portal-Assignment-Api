package com.barclays.userservice.service;

import java.util.List;

import com.barclays.userservice.exception.UserNotFoundException;
import com.barclays.userservice.exception.UserRequestNotFoundException;
import com.barclays.userservice.model.Course;
import com.barclays.userservice.model.CourseRequest;
import com.barclays.userservice.model.User;
import com.barclays.userservice.model.UserRequest;
import com.barclays.userservice.response.UserResponse;

public interface UserService {
	
	public UserRequest registerUser(UserRequest userRequest);
	public List<UserRequest> getAllRegisterRequests();
	public UserResponse<UserRequest> approvalForUser(int userRequestId) throws UserRequestNotFoundException;
	
	public void resetPassword(UserRequest userRequest);
	public String approvalForPasswordReset(int userRequestId);
	
	public void updateUserProfile(UserRequest userRequest) throws UserNotFoundException;
	public UserResponse<User> approvalForUpdateUserProfile(int userRequestId);
	
	public UserResponse<User> makeDisableOrEnableUser(int userId) throws UserNotFoundException;
	
	public User getUser(int userId) throws UserNotFoundException;
	public List<User> getAllUsers();
	public void deleteUser(int userId);
	
	public UserResponse<Course> purchaseCourse(CourseRequest courseRequest);
	public List<Course> userWiseSubscription(int userId);
	public List<User> courseWiseSubscription(int courseId);
	public String removeCourseFromCatalogue(int courseId);
	
}
