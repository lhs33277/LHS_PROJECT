package com.lhs.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lhs.home.base.service.FileService;
import com.lhs.home.base.service.impl.FileServiceImpl;
import com.lhs.home.board.sevice.BoardService;
import com.lhs.home.board.sevice.impl.BoardServiceImpl;
import com.lhs.home.user.service.LoginService;
import com.lhs.home.user.service.ProfileService;
import com.lhs.home.user.service.SignUpService;
import com.lhs.home.user.service.impl.LoginServiceImpl;
import com.lhs.home.user.service.impl.ProfileServiceImpl;
import com.lhs.home.user.service.impl.SignUpServiceImpl;

@Configuration
public class ServiceConfig {

	@Bean
	public LoginService loginService() {
		return new LoginServiceImpl();
	}
	
	@Bean
	public SignUpService signUpService() {
		return new SignUpServiceImpl();
	}
	
	@Bean
	public ProfileService profileService() {
		return new ProfileServiceImpl();
	}
	
	@Bean
	public BoardService boardService() {
		return new BoardServiceImpl();
	}
	
	@Bean
	public FileService fileService() {
		return new FileServiceImpl();
	}

}
