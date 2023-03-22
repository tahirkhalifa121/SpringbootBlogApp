package com.springboot.blog.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table( name = "blog_posts",uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "title",nullable = false)
	private String title;
	@Column(name = "description",nullable = false)
	private String description;
	@Column(name = "content",nullable = false)
	private String content;
	
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<Comments> comments =new HashSet<>();
	
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
	
	
	public Post() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
