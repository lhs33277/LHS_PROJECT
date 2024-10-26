package com.lhs.home.user.service;

import com.lhs.home.user.domain.UserDTO;

public interface LoginService {
	
	/* 로그인 전처리 */
	public UserDTO updatePrePareLogin(UserDTO loginDTO);

}
