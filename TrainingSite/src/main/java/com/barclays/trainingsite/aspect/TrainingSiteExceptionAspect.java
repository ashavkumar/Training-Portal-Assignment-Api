package com.barclays.trainingsite.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.barclays.trainingsite.exception.UserNotFoundException;
import com.barclays.trainingsite.response.CustomResponse;

@ControllerAdvice
public class TrainingSiteExceptionAspect {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<CustomResponse> handleUserNotFoundException(Exception exception){
		CustomResponse response=new CustomResponse(HttpStatus.EXPECTATION_FAILED.value(),"Invalid Id");
		return new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
	}
}
