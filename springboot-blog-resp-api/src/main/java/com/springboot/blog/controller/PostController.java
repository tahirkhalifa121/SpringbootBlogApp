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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.impl.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	
	// create blog posts Rest Api
	@PostMapping("/createPost")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
		
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
	} 
	
	// Get All blog posts Rest Api
	@GetMapping("/getallposts")
	public List<PostDto> getAllPosts(){
		return postService.getAllposts();
	}
	
	
	// Get posts by id Rest Api
		@GetMapping("/getpostbyid/{id}")
		public ResponseEntity<PostDto> getPostById(@PathVariable long id){
			return ResponseEntity.ok(postService.getPostById(id));
		}
		
		 
		//update post by id rest Api
		
		@PutMapping("/updatePost/{id}")
		public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Long id){
			
			return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
		}
	
		//delete post by id rest Api
		
		@DeleteMapping("/deletePost/{id}")
		public ResponseEntity<String> deletePost(@PathVariable Long id){
			
			postService.deletepostById(id);
			
			return new ResponseEntity<>("Post deleted Successfully!!", HttpStatus.OK);
		}

}
