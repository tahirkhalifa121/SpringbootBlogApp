package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourcenotfoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String filedName;
	private Long filedValue;
	
	public ResourcenotfoundException(String resourceName, String filedName, Long filedValue) {
		super(String.format("%s not found with %s : '%s'", resourceName,filedName,filedValue));
		this.resourceName = resourceName;
		this.filedName = filedName;
		this.filedValue = filedValue;
	};
	
	public String getResourceName() {
		return resourceName;
	}
	
	public String getFiledName() {
		return filedName;
	}
	
	public Long getFiledValue() {
		return filedValue;
	}

}
