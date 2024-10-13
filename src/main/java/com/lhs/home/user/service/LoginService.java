package com.lhs.home.user.service;

import com.lhs.home.user.domain.UserDTO;

public interface LoginService {
	
	/* 사용자 조회 (고객번호) */
	public UserDTO selectUserByUserNo(int userNo);
	
	/* 사용자 조회 (ID) */
	public UserDTO selectUserByUserId(String userId);

	/* 사용자 조회 (휴대폰번호) */
	public UserDTO selectUserByMobileNo(String monileNo);
	
	/* 로그인 전처리 */
	public UserDTO updatePrefixLogin(UserDTO loginDTO);

}
