package com.springboot.blog.service.impl;

import java.util.List;

import com.springboot.blog.payload.CommentDto;

public interface CommentService {

	
	public CommentDto createComment(Long postId,CommentDto commentDto);
	
	List<CommentDto> getcommentByPostId(long PostId);
	
	
	public CommentDto getcommentById(Long post_id,Long comment_id);
	
	CommentDto updateComment(Long post_id,Long comment_id,CommentDto commentDto);
	
	void deleteComment(Long post_id,Long comment_id);
}
