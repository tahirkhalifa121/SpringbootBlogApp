package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Comments;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourcenotfoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRespository;
import com.springboot.blog.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

	
	private CommentRespository commentRespository;
	
	private PostRepository  postRepository;
	
	private ModelMapper mapper;
	
	
	
	public CommentServiceImpl(CommentRespository commentRespository,PostRepository postRepository,ModelMapper mapper) {
		this.commentRespository = commentRespository;
		this.postRepository = postRepository;
		this.mapper=mapper;
	}



	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {

			Comments comments= mapToEntity(commentDto);
			
			// retrive post entity by id 
			
			Post post=postRepository.findById(postId).orElseThrow(() -> new  ResourcenotfoundException("post","id",postId));
			
			// set post to comment Entity
			
			comments.setPost(post);
			
			// save comment Entity to DB
			
			Comments newComments =commentRespository.save(comments);
		return mapToDto(newComments) ;
	}

	
	private CommentDto mapToDto(Comments comment)
	{
		CommentDto commentDto=mapper.map(comment, CommentDto.class);
		
//		CommentDto commentDto =new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
//		commentDto.setBody(comment.getBody());
		return commentDto;
		
	}
	
	
	private Comments mapToEntity(CommentDto commentDto)
	{
		Comments comments=mapper.map(commentDto, Comments.class);
		
//		Comments comments =new Comments();
//		comments.setId(commentDto.getId());
//		comments.setName(commentDto.getName());
//		comments.setEmail(commentDto.getEmail());
//		comments.setBody(commentDto.getBody());
		return comments;
		
	}



	@Override
	public List<CommentDto> getcommentByPostId(long PostId) {
		// TODO Auto-generated method stub
		// reterive comments by postId

		List<Comments> comments=commentRespository.findByPostId(PostId);
		
		return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
	}



	@Override
	public CommentDto getcommentById(Long post_id, Long comment_id) {
		
		// retrive post entity by id 
		
	Post post=postRepository.findById(post_id).orElseThrow(() -> new  ResourcenotfoundException("post","id",post_id));
	
	// retrive comment by id
	
	Comments comments=commentRespository.findById(comment_id).orElseThrow(() -> new ResourcenotfoundException("comment", "id", comment_id));
	
	if(!comments.getPost().getId().equals(post.getId())) {
		
		throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
	}
		
		return mapToDto(comments);
	}



	@Override
	public CommentDto updateComment(Long post_id, Long comment_id, CommentDto commentDto) {
		
		// retrive post entity by id 
		
		Post post=postRepository.findById(post_id).orElseThrow(() -> new  ResourcenotfoundException("post","id",post_id));
		
		// retrive comment by id
		
		Comments comments=commentRespository.findById(comment_id).orElseThrow(() -> new ResourcenotfoundException("comment", "id", comment_id));
		

		if(!comments.getPost().getId().equals(post.getId())) {
			
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		
		comments.setName(commentDto.getName());
		comments.setEmail(commentDto.getEmail());
		comments.setBody(commentDto.getBody());
		
		Comments updatedcoment =commentRespository.save(comments);
		
		return mapToDto(updatedcoment);
	}



	@Override
	public void deleteComment(Long post_id, Long comment_id) {
		
		// retrive post entity by id 
		
				Post post=postRepository.findById(post_id).orElseThrow(() -> new  ResourcenotfoundException("post","id",post_id));	
	    // retrive comment by id
				
				Comments comments=commentRespository.findById(comment_id).orElseThrow(() -> new ResourcenotfoundException("comment", "id", comment_id));
				
				
				if(!comments.getPost().getId().equals(post.getId())) {
					
					throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
				}
				
				commentRespository.delete(comments);
				
	}
}
