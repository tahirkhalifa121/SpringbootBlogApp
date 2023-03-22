package com.springboot.blog.payload;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PostDto {
	
	private Long id;
	
	
	@NotEmpty
	@Size(min = 2,message = "Post title should have at least 2 character")
	private String title;
	
	@NotEmpty
	@Size(min = 10,message = "Post description at least 10 charachter")
	private String description;
	
	
	@NotBlank
	private String content;
	private Set<CommentDto> comments;  
	
	

	
	public PostDto(Long id, String title, String description, String content, Set<CommentDto> comments) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.comments = comments;
	}
	public Set<CommentDto> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	public PostDto() {}
	
	

}
