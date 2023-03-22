package com.springboot.blog.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.springboot.blog.payload.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//handler specific exception
	
	@ExceptionHandler(ResourcenotfoundException.class)
	public ResponseEntity<ErrorDetails> handlerResourcenotfoundException(ResourcenotfoundException exception,
			WebRequest webRequest){
		ErrorDetails errorDetails=new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

}
