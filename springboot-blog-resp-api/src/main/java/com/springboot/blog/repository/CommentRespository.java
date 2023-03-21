package com.springboot.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.Comments;


public interface CommentRespository extends JpaRepository<Comments, Long> {

	List<Comments> findByPostId(long postId);
}
