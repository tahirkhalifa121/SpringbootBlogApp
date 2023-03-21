package com.springboot.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.impl.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("/posts/{post_id}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable Long post_id,@RequestBody CommentDto commentDto){
		
		return new ResponseEntity<CommentDto>(commentService.createComment(post_id,commentDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{post_id}/comments")
	public List<CommentDto>getCommentsByPostId(@PathVariable Long post_id){
		
		return commentService.getcommentByPostId(post_id);
	}
	
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable Long postId,@PathVariable Long commentId){
		
		return new ResponseEntity<CommentDto>(commentService.getcommentById(postId, commentId), HttpStatus.OK);
	}
	
	
	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable Long postId,@PathVariable Long commentId,@RequestBody CommentDto commentDto){
		
		return new ResponseEntity<CommentDto>(commentService.updateComment(postId, commentId,commentDto), HttpStatus.OK);
	}
	
	
	//delete post by id rest Api
	
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable Long postId,@PathVariable Long commentId){
		
		commentService.deleteComment(postId, commentId);
		
		return new ResponseEntity<>("Comment deleted Successfully!!", HttpStatus.OK);
	}
}
