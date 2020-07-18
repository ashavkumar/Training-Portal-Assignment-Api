package com.barclays.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.barclays.userservice.dao.CourseRequestRepository;
import com.barclays.userservice.dao.UserCourseRepository;
import com.barclays.userservice.dao.UserRepository;
import com.barclays.userservice.dao.UserRequestRepository;
import com.barclays.userservice.exception.UserNotFoundException;
import com.barclays.userservice.exception.UserRequestNotFoundException;
import com.barclays.userservice.model.Course;
import com.barclays.userservice.model.CourseRequest;
import com.barclays.userservice.model.User;
import com.barclays.userservice.model.UserCourse;
import com.barclays.userservice.model.UserRequest;
import com.barclays.userservice.request.PasswordResetRequest;
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
		userRequest.setStatus("APPLIED-New User Request.");
		return userRequestRepository.save(userRequest);
	}
	@Override
	public List<UserRequest> getAllRegisterRequests(){
		return userRequestRepository.findAll();
	}
	
	@Override
	public UserResponse<UserRequest> approvalForUser(int userRequestId) throws UserRequestNotFoundException {
		UserRequest userRequest=userRequestRepository.findById(userRequestId).orElseThrow(()->new UserRequestNotFoundException("No Request has raised yet!!! Inappropriate action"+userRequestId));
		Optional<UserRequest> checkNull =  Optional.ofNullable(userRequest);
		if(checkNull.isPresent()) {
			if(userRequest.getStatus().startsWith("APPLIED")) {
				if(userRequest.getUsername()==null || userRequest.getPassword()==null || userRequest.getFirstname()==null || userRequest.getLastname()==null) {
					userRequest.setStatus("REJECTED-Incomplete details given.");
					userRequest=userRequestRepository.save(userRequest);
					return new UserResponse<>("Please fill all the details & Try again!!!",userRequest);
				}
				else if(userRequest.getUsername().length()<5) {
					userRequest.setStatus("REJECTED-Username length was less than 5");
					userRequest=userRequestRepository.save(userRequest);
					return new UserResponse<>("Please insert username of atleast 5 length!!!",userRequest);
				}
				else if( userRequest.getPassword().matches("[A-Z][a-z]{3,}[0-9]{1,}[!@#$%&*]")==false) {
					userRequest.setStatus("REJECTED-Password was not following standards");
					userRequest=userRequestRepository.save(userRequest);
					return new UserResponse<>("Passwaord should be consists of 1 uppercase,1 digit & 1 special character!!!",userRequest);
				}
				else {
					User user=new User();
					user.setUserName(userRequest.getUsername());
					user.setFirstName(userRequest.getFirstname());
					user.setLastName(userRequest.getLastname());
					user.setPassword(userRequest.getPassword());
					user.setRole("ROLE_USER");
					user.setActive(true);
					userRepository.save(user);
					
					userRequest.setStatus("APPROVED-Every thing was appropriate.");
					userRequest=userRequestRepository.save(userRequest);
					return new UserResponse<UserRequest>("Registered Successfully",userRequest);
				}
			}
			else {
				return new UserResponse<UserRequest>("Request has been declined or approved, Invalid Request!!!",userRequest);
			}
		}
		else {
			return new UserResponse<UserRequest>("No Pending request, Invalid request!!!",userRequest);
		}
	}
	
	@Override
	public UserResponse<PasswordResetRequest> resetPassword(PasswordResetRequest passwordResetRequest) {
		if(passwordResetRequest.getUsername()!=null && passwordResetRequest.getOldPassword()!=null 
				&& passwordResetRequest.getNewPassword()!=null && passwordResetRequest.getSecurityQuestion()!=null
				&& passwordResetRequest.getSecurityAnswer()!=null) {
			User user=userRepository.findByUserName(passwordResetRequest.getUsername());
			if(user.getPassword().equals(passwordResetRequest.getOldPassword())==true) {
				if(passwordResetRequest.getOldPassword().equals(passwordResetRequest.getNewPassword())==false) {
					UserRequest userRequest=new UserRequest();
					userRequest.setUsername(passwordResetRequest.getUsername());
					userRequest.setPassword(passwordResetRequest.getNewPassword());
					userRequest.setStatus("APPLIED-Password Reset");
					userRequest=userRequestRepository.save(userRequest);
					return new UserResponse<PasswordResetRequest>("Request for  reset password has raised successfully!!! Request Id: "+userRequest.getId(),passwordResetRequest);
				}
				else {
					return new UserResponse<PasswordResetRequest>("New password must be different from old password, Try again!!!",passwordResetRequest);
				}
			}
			else {
				return new UserResponse<PasswordResetRequest>("Incorrect password, Try again!!!",passwordResetRequest);
			}
		}
		else {
			return new UserResponse<PasswordResetRequest>("Incomplete details given, Try again!!!",passwordResetRequest);
		}		
	}
	
	@Override
	public String approvalForPasswordReset(int userRequestId) throws UserRequestNotFoundException{
		UserRequest userRequest=userRequestRepository.findById(userRequestId).orElseThrow(()->new UserRequestNotFoundException("No Request has raised yet!!! Inappropriate action "+userRequestId));
		Optional<UserRequest> checkNull =  Optional.ofNullable(userRequest);
		if(checkNull.isPresent()) {
			if(userRequest.getStatus().startsWith("APPLIED")) {
				if(userRequest.getPassword().matches("[A-Z][a-z]{3,}[0-9]{1,}[!@#$%&*]")) {
					User user=userRepository.findByUserName(userRequest.getUsername());
					user.setPassword(userRequest.getPassword());
					userRepository.save(user);
					userRequest.setStatus("Approved for the password reset request");
					userRequestRepository.save(userRequest);
					return "Password has been reset successfully";
				}
				else {
					userRequest.setStatus("REJECTED-Password was not following given standards.");
					userRequestRepository.save(userRequest);
					return "Please make sure that passsword is of correct pattern!!!";
				}
			}
			else {
				return "Request has been declined or approved, Invalid Request!!!";
			}
		}
		else {
			return "No Pending request, Invalid request!!!";
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
		return new UserResponse<>("Profile has been updated successfully!!!",user);
	}
	
	@Override
	public UserResponse<User> makeUserDisable(int userId) throws UserNotFoundException {
		
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Invalid User Id.Please try again!!!"+userId));
		if(user.isActive()==true) {
			user.setActive(false);
			user=userRepository.save(user);
			return new UserResponse<>("User has been disabled successfully!!!",user);
		}
		return new UserResponse<>("This user already disabled!!!",user);
	}
	
	@Override
	public UserResponse<User> makeUserEnable(int userId) throws UserNotFoundException {
		
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Invalid User Id.Please try again!!!"+userId));
		if(user.isActive()==false) {
			user.setActive(true);
			user=userRepository.save(user);
			return new UserResponse<>("User has been enabled successfully!!!",user);
		}
		return new UserResponse<>("This user already enabled!!!",user);
	}
	
	@Override
	public User getUser(int userId) throws UserNotFoundException{
		return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Invalid User Id, User doesn't exist!!! "+userId));
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public String removeUser(int userId) throws UserNotFoundException {
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Invalid userId,User dosn't exist!!! "+userId));
		if(user.isActive()==true) {
			user.setActive(false);
			userRepository.save(user);
			return "The User has removed successfully!!!";
		}
		return "This user already removed!!!";
	}
	
	@Override
	public UserResponse<Course> purchaseCourse(CourseRequest courseRequest) throws HttpClientErrorException ,UserNotFoundException{
		Course course=null;
		User user=userRepository.findById(courseRequest.getUserId()).orElseThrow(()->new UserNotFoundException("Invalid UserId, User doesn't exist!!! "+courseRequest.getUserId()));
		
		String uri="http://localhost:9002/course/get/"+courseRequest.getCourseId();
		course=restTemplate.getForObject(uri,Course.class);
		
		Set<Integer> set=user.getCourses();
		if(course.isActive()==false) {
			courseRequest.setStatus("Request Cancelled- Course for which user was requesting isn't available.");
			courseRequestRepository.save(courseRequest);
			return new UserResponse<>("Course isn't available!!!",course);
		}
		else if(set.add(courseRequest.getCourseId())==true) {
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
			courseRequest.setStatus("Request Cancelled- Already purchased.");
			courseRequestRepository.save(courseRequest);
			return new UserResponse<>("Already you have purchased it! Please go through your subscription!!!",course);
		  }
	}
	
	@Override
	public List<Course> userWiseSubscription(int userId) throws UserNotFoundException,HttpClientErrorException {
		
		  getUser(userId);
		  
		  List<Course> listOfCourse=new ArrayList<>(); 
		  List<Integer> listOfCourseId=userCourseRepository.userWiseSubscription(userId);
		  
		  Optional<List<Integer>> checkNull = Optional.ofNullable(listOfCourseId);
		  
		  if(checkNull.isPresent()) { 
			  for(int i:listOfCourseId) {
				  String uri="http://localhost:9002/course/get/"+i;
				  Course course=restTemplate.getForObject(uri,Course.class);
				  listOfCourse.add(course); 
				 }
			  return listOfCourse;
		  }
		  else { 
			  return listOfCourse;
		  } 
	}

	@Override
	public List<User> courseWiseSubscription(int courseId) throws HttpClientErrorException , UserNotFoundException{
		
		String uri="http://localhost:9002/course/get/"+courseId;
		restTemplate.getForObject(uri, Course.class);
		
		List<User> listOfUser=new ArrayList<>();
		List<Integer> listOfUserId=userCourseRepository.courseWiseSubscription(courseId);
		Optional<List<Integer>> checkNull = Optional.ofNullable(listOfUserId);
		if(checkNull.isPresent()) {
			for(int i:listOfUserId) {
				User user=userRepository.findById(i).orElseThrow(()->new UserNotFoundException("Invalid User Id, User doesn't exist!!! "+i));
				listOfUser.add(user);
			}
			return listOfUser;
		}
		else {
			return listOfUser;
		}
	}
	
	@Override
	public String removeCourseFromCatalogue(int courseId) throws HttpClientErrorException{
		String uri="http://localhost:9002/course/removecourse/"+courseId;
		String responseMsg=restTemplate.getForObject(uri, String.class);
		return responseMsg;
	}

}
