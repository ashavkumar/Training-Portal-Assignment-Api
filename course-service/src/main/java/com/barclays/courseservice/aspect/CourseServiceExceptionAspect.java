package com.barclays.courseservice.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.barclays.courseservice.exception.CourseNotFoundException;
import com.barclays.courseservice.response.CustomResponse;

@ControllerAdvice
public class CourseServiceExceptionAspect {
	
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<CustomResponse> handleCourseNotFoundException(Exception exception){
		CustomResponse response=new CustomResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
}
