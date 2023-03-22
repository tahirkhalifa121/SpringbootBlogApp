package com.springboot.blog.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.impl.PostService;
import com.springboot.blog.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	
	// create blog posts Rest Api
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createPost")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
		
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
	} 
	
	// Get All blog posts Rest Api
	@GetMapping("/getallposts")
	public PostResponse getAllPosts(
			@RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false)String sortDir
			){
		return postService.getAllposts(pageNo,pageSize,sortBy,sortDir); 
	}
	
	
	// Get posts by id Rest Api
		@GetMapping("/getpostbyid/{id}")
		public ResponseEntity<PostDto> getPostById(@PathVariable long id){
			return ResponseEntity.ok(postService.getPostById(id));
		}
		
		 
		//update post by id rest Api
		@PreAuthorize("hasRole('ADMIN')")
		@PutMapping("/updatePost/{id}")
		public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable Long id){
			
			return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
		}
	
		//delete post by id rest Api
		
		@DeleteMapping("/deletePost/{id}")
		public ResponseEntity<String> deletePost(@PathVariable Long id){
			
			postService.deletepostById(id);
			
			return new ResponseEntity<>("Post deleted Successfully!!", HttpStatus.OK);
		}

}
