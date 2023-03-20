package com.springboot.blog.service.impl;

import java.util.List;

import com.springboot.blog.payload.PostDto;

public interface PostService {

	public PostDto createPost(PostDto postDto);

	public List<PostDto> getAllposts();

	public PostDto getPostById(long id);
}
