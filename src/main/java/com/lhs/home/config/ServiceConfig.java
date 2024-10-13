package com.lhs.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lhs.home.user.service.LoginService;
import com.lhs.home.user.service.SignUpService;
import com.lhs.home.user.service.impl.LoginServiceImpl;
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

}
