package com.barclays.trainingsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.trainingsite.exception.UserNotFoundException;
import com.barclays.trainingsite.model.Course;
import com.barclays.trainingsite.model.User;
import com.barclays.trainingsite.model.UserRequest;
import com.barclays.trainingsite.response.UserResponse;
import com.barclays.trainingsite.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<UserRequest> registerUser(@RequestBody UserRequest userRequest){
		userRequest=userService.registerUser(userRequest);
		return new ResponseEntity<UserRequest>(userRequest,HttpStatus.OK);
	}
	
	@RequestMapping(value="/get/registerrequests", method = RequestMethod.GET)
	public ResponseEntity<List<UserRequest>> getAllRegisterRequests(){
		List<UserRequest> userRequests=userService.getAllRegisterRequests();
		return new ResponseEntity<List<UserRequest>>(userRequests,HttpStatus.OK);
	}
	
	@RequestMapping(value="/actionforregistration/{userRequestId}", method = RequestMethod.GET)
	public ResponseEntity<UserResponse<UserRequest>> approvalForUser(@PathVariable("userRequestId") int userRequestId){
		UserResponse<UserRequest> userResponse= userService.approvalForUser(userRequestId);
		return new ResponseEntity<UserResponse<UserRequest>>(userResponse,HttpStatus.OK);
	}
	
	@RequestMapping(value="/reset/password",method=RequestMethod.POST)
	public ResponseEntity<String> resetPassword(@RequestBody UserRequest userRequest){
		userService.resetPassword(userRequest);
		return new ResponseEntity<String>("Your request to reset password has been raised successfully",HttpStatus.OK);
	}
	
	@RequestMapping(value="/actionforpasswordreset/{userRequestId}", method = RequestMethod.GET)
	public ResponseEntity<String> approvalForPasswordReset(@PathVariable("userRequestId") int userRequestId){
		String responseMsg=userService.approvalForPasswordReset(userRequestId);
		return new ResponseEntity<String>(responseMsg,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateprofile",method=RequestMethod.POST)
	public ResponseEntity<String> updateUserProfile(@RequestBody UserRequest userRequest) throws UserNotFoundException{
		userService.updateUserProfile(userRequest);
		return new ResponseEntity<String>("Your request for updating profile has been raised successfully",HttpStatus.OK);	
	}
	
	@RequestMapping(value="/updateapprovalforprofile/{userRequestId}",method=RequestMethod.PUT)
	public ResponseEntity<UserResponse<User>> approvalForUpdateUserProfile(@PathVariable("userRequestId") int userRequestId) throws UserNotFoundException{
		UserResponse<User> userResponse=userService.approvalForUpdateUserProfile(userRequestId);
		return new ResponseEntity<UserResponse<User>>(userResponse,HttpStatus.OK);	
	}
	
	public ResponseEntity<UserResponse<User>> makeDisableOrEnable(@PathVariable("userId") int userId) throws UserNotFoundException{
		UserResponse<User> userResponse=userService.makeDisableOrEnable(userId);
		return new ResponseEntity<UserResponse<User>>(userResponse,HttpStatus.OK);
	}
	@RequestMapping(value="/userwisesubscription/{userId}",method=RequestMethod.GET)
	public ResponseEntity<List<Course>> userWiseSubscription(@PathVariable("userId") int userId){
		List<Course> listOfCourse=userService.userWiseSubscription(userId);
		return new ResponseEntity<List<Course>>(listOfCourse,HttpStatus.OK);
	}
	@RequestMapping(value="/get/{userId}",method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("userId") int uId) throws UserNotFoundException{
		User user=userService.getUser(uId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/get/users",method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users=userService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	@RequestMapping(value="/delete/{userId}",method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable("userId") int uId){
		userService.deleteUser(uId);
		return new ResponseEntity<String>("Successfully deleted!!!",HttpStatus.OK);
	}
}
