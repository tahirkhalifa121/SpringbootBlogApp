package com.springboot.blog.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
	
	
	@ExceptionHandler(BlogApiException.class)
	public ResponseEntity<ErrorDetails> handlerBlogApiException(ResourcenotfoundException exception,
			WebRequest webRequest){
		ErrorDetails errorDetails=new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	// handler all exception  
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> commonException(ResourcenotfoundException exception,
			WebRequest webRequest){
		ErrorDetails errorDetails=new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	
	// first approch for custome fild validation exception 
	// extend ResponseEntityExceptionHandler class
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		// TODO Auto-generated method stub
//		
//		Map<String, String> errors=new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach((error) -> {
//			String fieldName=((FieldError)error).getField();
//			String message=error.getDefaultMessage();
//			errors.put(fieldName, message);
//		});
//		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	}
	
	
	// second approch for custome fild validation exception
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception,
			WebRequest webRequest){
		Map<String, String> errors=new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		
		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception,
			WebRequest webRequest){
ErrorDetails errorDetails=new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handlerAccessDeniedException(AccessDeniedException exception,
			WebRequest webRequest){
ErrorDetails errorDetails=new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
		
	}
	
}
