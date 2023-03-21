package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourcenotfoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	private ModelMapper modelMapper;
	
	public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper)
	{
		this.postRepository=postRepository;
		this.modelMapper=modelMapper;
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
		
		PostDto postDto=modelMapper.map(post, PostDto.class);
		
		
//		PostDto postDto =new PostDto();
//		postDto.setId(post.getId());
//		postDto.setContent(post.getContent());
//		postDto.setTitle(post.getTitle());
//		postDto.setDescription(post.getDescription());
		return postDto;
		
	}
	
	
	//convert dto into entity
	private Post mapToEnity(PostDto postDto)
	{
		
		Post post=modelMapper.map(postDto, Post.class);
//		Post post =new Post();
//		post.setId(postDto.getId());
//		post.setContent(postDto.getContent());
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
		return post;
		
	}

	@Override
	public PostResponse getAllposts(int pageNo,int pageSize,String sortBy,String sortDir) {
	
		// create pagable instance
		
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
			Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Post> posts=postRepository.findAll(pageable);
		
		// get content for page object

		List<Post> listofpost=posts.getContent();
		
		List<PostDto> contenet= listofpost.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
	
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(contenet);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalpage(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
		
	}

	@Override
	public PostDto getPostById(long id) {
		// TODO Auto-generated method stub
		
		Post post=postRepository.findById(id).orElseThrow(() -> new ResourcenotfoundException("post", "post_id", id));	
		return mapToDto(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		
		Post post=postRepository.findById(id).orElseThrow(() -> new ResourcenotfoundException("post", "post_id", id));
		
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatedpost=postRepository.save(post);
		
		PostDto updatedpostdto=mapToDto(updatedpost);
		
		return updatedpostdto;
	}
	
	
	public void deletepostById(long id) {
		
		Post post=postRepository.findById(id).orElseThrow(() -> new ResourcenotfoundException("post", "post_id", id));
		postRepository.delete(post);
	}

}
