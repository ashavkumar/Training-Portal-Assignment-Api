package com.barclays.trainingsite.service;

import java.util.List;

import com.barclays.trainingsite.exception.UserNotFoundException;
import com.barclays.trainingsite.model.Course;
import com.barclays.trainingsite.model.User;
import com.barclays.trainingsite.model.UserRequest;
import com.barclays.trainingsite.response.UserResponse;

public interface UserService {
	
	public UserRequest registerUser(UserRequest userRequest);
	public List<UserRequest> getAllRegisterRequests();
	public UserResponse<UserRequest> approvalForUser(int userRequestId);
	
	public void resetPassword(UserRequest userRequest);
	public String approvalForPasswordReset(int userRequestId);
	
	public void updateUserProfile(UserRequest userRequest) throws UserNotFoundException;
	public UserResponse<User> approvalForUpdateUserProfile(int userRequestId);
	
	public UserResponse<User> makeDisableOrEnable(int userId) throws UserNotFoundException;
	
	public List<Course> userWiseSubscription(int userId);
	
	public User getUser(int userId) throws UserNotFoundException;
	public List<User> getAllUsers();
	public void deleteUser(int userId);
}
