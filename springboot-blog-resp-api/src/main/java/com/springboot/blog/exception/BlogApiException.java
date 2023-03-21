package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {

	private HttpStatus status;
	private String message;
	
	
	public BlogApiException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	
	public BlogApiException(HttpStatus status, String message,String message1) {
		super();
		this.status = status;
		this.message = message1;
		
	}

	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
}
