package com.barclays.trainingsite.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.trainingsite.dao.CourseRepository;
import com.barclays.trainingsite.dao.UserCourseRepository;
import com.barclays.trainingsite.dao.UserRepository;
import com.barclays.trainingsite.dao.UserRequestRepository;
import com.barclays.trainingsite.exception.UserNotFoundException;
import com.barclays.trainingsite.model.Course;
import com.barclays.trainingsite.model.User;
import com.barclays.trainingsite.model.UserRequest;
import com.barclays.trainingsite.response.UserResponse;
import com.barclays.trainingsite.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private UserRequestRepository userRequestRepository;
	@Autowired
	private UserCourseRepository userCourseRepository;
	
	@Override
	public UserRequest registerUser(UserRequest userRequest) {
		userRequest.setStatus("APPLIED");
		return userRequestRepository.save(userRequest);
	}
	@Override
	public List<UserRequest> getAllRegisterRequests(){
		return userRequestRepository.findAll();
	}
	@Override
	public UserResponse<UserRequest> approvalForUser(int userRequestId) {
		UserRequest userRequest=userRequestRepository.findById(userRequestId).get();
		if(userRequest.getUsername().length()<5) {
			userRequest.setStatus("REJECTED");
			userRequest=userRequestRepository.save(userRequest);
			return new UserResponse<>("Please insert username of atleast 5 length",userRequest);
		}
		else {
			User user=new User();
			user.setUserName(userRequest.getUsername());
			user.setFirstName(userRequest.getFirstname());
			user.setLastName(userRequest.getLastname());
			user.setPassword(userRequest.getPassword());
			user.setActive(true);
			userRepository.save(user);
			userRequest.setStatus("APPROVED");
			userRequest=userRequestRepository.save(userRequest);
			return new UserResponse<UserRequest>("Registered Successfully",userRequest);
		}
	}
	
	@Override
	public void resetPassword(UserRequest userRequest) {
		userRequest.setStatus("APPLIED");
		userRequestRepository.save(userRequest);
	}
	
	public String approvalForPasswordReset(int userRequestId) {
		UserRequest userRequest=userRequestRepository.findById(userRequestId).get();
		if(userRequest.getPassword().matches("([A-Z][a-z]{3,}[0-9]{0,})\\w")) {
			User user=userRepository.findByUserName(userRequest.getUsername());
			user.setPassword(userRequest.getPassword());
			userRepository.save(user);
			userRequest.setStatus("APPROVED");
			userRequestRepository.save(userRequest);
			return "Password has been reset successfully";
		}
		else {
			userRequest.setStatus("REJECTED");
			userRequestRepository.save(userRequest);
			return "Please make sure that passsword is of correct pattern!!!";
		}
	}
	@Override
	public void updateUserProfile(UserRequest userRequest) throws UserNotFoundException {
		userRequest.setStatus("APPLIED");
		userRequestRepository.save(userRequest);
	}
	
	@Override
	public UserResponse<User> approvalForUpdateUserProfile(int userRequestId) {
		UserRequest userRequest=userRequestRepository.findById(userRequestId).get();
		User user=userRepository.findByUserName(userRequest.getUsername());
		if(userRequest.getFirstname()!=null)
			user.setFirstName(userRequest.getFirstname());
		if(userRequest.getLastname()!=null)
			user.setLastName(userRequest.getLastname());
		user=userRepository.save(user);
		return new UserResponse<>("Your profile has been updated successfully",user);
	}
	
	public UserResponse<User> makeDisableOrEnable(int userId) throws UserNotFoundException {
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Invalid User Id"+userId));
		if(user.isActive()==true) {
			user.setActive(false);
			user=userRepository.save(user);
			return new UserResponse<>("Now user has been disabled",user);
		}
		else {
			user.setActive(true);
			user=userRepository.save(user);
			return new UserResponse<>("Now user has been enabled",user);
		}
	}	
	@Override
	public List<Course> userWiseSubscription(int userId) {
		List<Course> listOfCourse=new ArrayList<>();
		List<Integer> listOfCourseId=userCourseRepository.userWiseSubscription(userId);
		for(int i:listOfCourseId) {
			Course course=courseRepository.findById(i).get();
			listOfCourse.add(course);
		}
		return listOfCourse;
	}
	@Override
	public User getUser(int userId) throws UserNotFoundException{
		//System.out.println(userId);
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Invalid user id "+userId));
		//System.out.println(user.toString());
		return user;
	}
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	@Override
	public void deleteUser(int userId) {
		userRepository.deleteById(userId);
	}
	/*
	 * @Override public String forgotPassword(int userId) throws
	 * UserNotFoundException { User
	 * user=userRepository.findById(userId).orElseThrow(()->new
	 * UserNotFoundException("Invalid User Id"+userId)); return user.getPassword();
	 * }
	 */	
	/*
	 * @Override public List<UserRequest> getAllRequests() { return
	 * userRequestRepository.findAll(); }
	 */
}
