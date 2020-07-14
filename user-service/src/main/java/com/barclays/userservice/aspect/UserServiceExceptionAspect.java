package com.barclays.userservice.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.barclays.userservice.exception.CourseNotFoundException;
import com.barclays.userservice.exception.UserNotFoundException;
import com.barclays.userservice.response.CustomResponse;

@ControllerAdvice
public class UserServiceExceptionAspect {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<CustomResponse> handleUserNotFoundException(Exception exception){
		CustomResponse response=new CustomResponse(HttpStatus.EXPECTATION_FAILED.value(),"Invalid Id");
		return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<CustomResponse> handleCourseNotFoundException(Exception exception){
		CustomResponse response=new CustomResponse(HttpStatus.EXPECTATION_FAILED.value(),"Invalid Id");
		return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
	}
}
