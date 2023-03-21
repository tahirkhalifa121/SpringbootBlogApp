package com.springboot.blog.service.impl;

import java.util.List;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;

public interface PostService {

	public PostDto createPost(PostDto postDto);

	public PostResponse getAllposts(int pageNo,int pageSize,String sortBy,String sortDir);

	public PostDto getPostById(long id);
	
	public PostDto updatePost(PostDto postDto,Long id);
	
	void deletepostById(long id);
}
