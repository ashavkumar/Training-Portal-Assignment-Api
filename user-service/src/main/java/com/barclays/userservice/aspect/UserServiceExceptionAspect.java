package com.barclays.userservice.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import com.barclays.userservice.exception.UserNotFoundException;
import com.barclays.userservice.exception.UserRequestNotFoundException;
import com.barclays.userservice.response.CustomResponse;

@ControllerAdvice
public class UserServiceExceptionAspect {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<CustomResponse> handleUserNotFoundException(Exception exception){
		CustomResponse response=new CustomResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserRequestNotFoundException.class)
	public ResponseEntity<CustomResponse> handleUserRequestNotFoundException(Exception exception){
		CustomResponse response=new CustomResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<CustomResponse> handleHttpClientErrorException(HttpClientErrorException exception){
		CustomResponse response=new CustomResponse(exception.getStatusCode().value(),exception.getMessage().substring(30,65));
		return new ResponseEntity<>(response,exception.getStatusCode());
	}
}
