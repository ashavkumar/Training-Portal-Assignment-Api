package com.barclays.userservice.service;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.client.HttpClientErrorException;

import com.barclays.userservice.exception.UserNotFoundException;
import com.barclays.userservice.exception.UserRequestNotFoundException;
import com.barclays.userservice.model.Course;
import com.barclays.userservice.model.CourseRequest;
import com.barclays.userservice.model.User;
import com.barclays.userservice.model.UserRequest;
import com.barclays.userservice.request.PasswordResetRequest;
import com.barclays.userservice.response.UserResponse;

public interface UserService {
	
	public UserRequest registerUser(UserRequest userRequest);
	public List<UserRequest> getAllRegisterRequests();
	public UserResponse<UserRequest> approvalForUser(int userRequestId) throws UserRequestNotFoundException;
	
	public UserResponse<PasswordResetRequest> resetPassword(PasswordResetRequest passwordResetRequest);
	public String approvalForPasswordReset(int userRequestId) throws UserRequestNotFoundException;
	
	public UserResponse<UserRequest> updateUserProfile(UserRequest userRequest) throws UserNotFoundException;
	public UserResponse<User> approvalForUpdateUserProfile(int userRequestId) throws UserRequestNotFoundException;
	
	public UserResponse<User> makeUserDisable(int userId) throws UserNotFoundException;
	public UserResponse<User> makeUserEnable(int userId) throws UserNotFoundException;
	
	public User getUser(int userId) throws UserNotFoundException;
	public List<User> getAllUsers();
	public String removeUser(int userId) throws UserNotFoundException;
	
	public UserResponse<Course> purchaseCourse(CourseRequest courseRequest) throws HttpClientErrorException, UserNotFoundException, URISyntaxException;
	public List<Course> userWiseSubscription(int userId) throws UserNotFoundException, HttpClientErrorException, URISyntaxException;
	public List<User> courseWiseSubscription(int courseId) throws HttpClientErrorException, UserNotFoundException, URISyntaxException;
	public String removeCourseFromCatalogue(int courseId) throws HttpClientErrorException, URISyntaxException;
	
	public List<Course> getAllCourseFromCatalogue();
	public Course getCourseFromCatalogue(int courseId);
	
}
