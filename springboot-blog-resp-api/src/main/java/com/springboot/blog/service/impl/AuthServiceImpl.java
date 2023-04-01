package com.springboot.blog.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.springboot.blog.payload.LoginDto;


@Service
public class AuthServiceImpl implements AuthService {

	
	
	private AuthenticationManager authenticationManager;
	
	
	public AuthServiceImpl(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	@Override
	public String login(LoginDto loginDto) {
		Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		return "SucessFully !!!!";
	}

}
