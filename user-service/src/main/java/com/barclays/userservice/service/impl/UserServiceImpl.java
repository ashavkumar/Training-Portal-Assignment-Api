package com.barclays.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.barclays.userservice.dao.CourseRequestRepository;
import com.barclays.userservice.dao.UserCourseRepository;
import com.barclays.userservice.dao.UserRepository;
import com.barclays.userservice.dao.UserRequestRepository;
import com.barclays.userservice.exception.UserNotFoundException;
import com.barclays.userservice.model.Course;
import com.barclays.userservice.model.CourseRequest;
import com.barclays.userservice.model.User;
import com.barclays.userservice.model.UserCourse;
import com.barclays.userservice.model.UserRequest;
import com.barclays.userservice.response.UserResponse;
import com.barclays.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRequestRepository userRequestRepository;
	@Autowired
	private UserCourseRepository userCourseRepository;
	@Autowired
	private CourseRequestRepository courseRequestRepository;
	@Autowired
	private RestTemplate restTemplate;
	
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
			user.setRole(userRequest.getRole());
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
	
	@Override
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
	
	@Override
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
			//Course course=courseRepository.findById(i).get();
			Course course=restTemplate.getForObject("http://localhost:9002/course/get/{courseId}", Course.class, i);
			listOfCourse.add(course);
		}
		return listOfCourse;
	}
	
	@Override
	public User getUser(int userId) throws UserNotFoundException{
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Invalid user id "+userId));
		return user;
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public void deleteUser(int userId) {
		User user=userRepository.findById(userId).get();
		user.setActive(false);
		userRepository.save(user);
	}
	
	/*
	 * @Override public UserResponse<Course> purchaseCourse(UserCourse userCourse) {
	 * User user=userRepository.findById(userCourse.getUserId()).get(); String
	 * uri="http://localhost:9002/course/get/"+userCourse.getCourseId(); //Course
	 * course=restTemplate.getForObject(uri, Course.class); Course course=new
	 * Course("J2EE", 199, "Classroom", "less expensive", true);
	 * System.out.println(course.toString()); Set<Course> set=user.getSetOfCourse();
	 * if(set.add(course)==true && course.isActive()==true) {
	 * user.setSetOfCourse(set); System.out.println(user);
	 * userRepository.save(user); userCourse.setStatus("APPROVED");
	 * userCourseRepository.save(userCourse); return new
	 * UserResponse<>("You purchased this course successfully!!!",course); } else {
	 * return new UserResponse<>("Already you have purchased it!!!",null); } }
	 */
	
	@Override
	public UserResponse<Course> purchaseCourse(CourseRequest courseRequest) {
		User user=userRepository.findById(courseRequest.getUserId()).get();
		String uri="http://localhost:9002/course/get/"+courseRequest.getCourseId();
		Course course=restTemplate.getForObject(uri, Course.class);
		Set<Integer> set=user.getCourses();
		if(set.add(courseRequest.getCourseId())==true  && course.isActive()==true) {
			user.setCourse(set);
			userRepository.save(user);
			
			courseRequest.setStatus("Approved");
			courseRequestRepository.save(courseRequest);
			
			UserCourse userCourse=new UserCourse();
			userCourse.setCousreId(courseRequest.getCourseId());
			userCourse.setCourseName(courseRequest.getCourseName());
			userCourse.setUserId(courseRequest.getUserId());
			userCourseRepository.save(userCourse);
			
			return new UserResponse<>("You purchased this course successfully!!!",course);
		
		  } else { 
			  	courseRequest.setStatus("Request Cancelled");
				courseRequestRepository.save(courseRequest);
			  return new UserResponse<>("Already you have purchased it!!!",null);
		  }
		 
	}
	
	@Override
	public List<User> courseWiseSubscription(int courseId){
		List<User> listOfUser=new ArrayList<>();
		List<Integer> listOfUserId=userCourseRepository.courseWiseSubscription(courseId);
		for(int i:listOfUserId) {
			User user=userRepository.findById(i).get();
			listOfUser.add(user);
		}
		return listOfUser;
	}
}
