package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourcenotfoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository)
	{
		this.postRepository=postRepository;
	}
	
	public PostDto createPost(PostDto postDto) {
		
		// convert dto to entity
		
		Post post=mapToEnity(postDto);
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post newpost=postRepository.save(post);
		
		//convert entity to dto
		
		PostDto postresponse=mapToDto(newpost);
		return  postresponse;
	}
	
	
	//convert entity into dto
	@SuppressWarnings("unused")
	private PostDto mapToDto(Post post)
	{
		PostDto postDto =new PostDto();
		postDto.setId(post.getId());
		postDto.setContent(post.getContent());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
		return postDto;
		
	}
	
	
	//convert dto into entity
	private Post mapToEnity(PostDto postDto)
	{
		Post post =new Post();
		post.setId(postDto.getId());
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		return post;
		
	}

	@Override
	public List<PostDto> getAllposts() {
	
		List<Post> listpost=postRepository.findAll();
		return listpost.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
	}

	@Override
	public PostDto getPostById(long id) {
		// TODO Auto-generated method stub
		
		Post post=postRepository.findById(id).orElseThrow(() -> new ResourcenotfoundException("post", "post_id", id));	
		return mapToDto(post);
	}

}
