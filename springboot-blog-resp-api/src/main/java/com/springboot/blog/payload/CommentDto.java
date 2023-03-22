package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CommentDto {

private long id;

	@NotEmpty(message = "Name Should not be Null or Empty")
	private String name;
	@NotEmpty(message = "Email Should not be Null or Empty")
	@Email
	private String email;
	
	@NotEmpty
	@Size(min = 10,message = "comment body must be 10 charachter")
	private String body;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public CommentDto(long id, String name, String email, String body) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.body = body;
	}
	public CommentDto() {
		super();
	}
	
	
	
	
}
