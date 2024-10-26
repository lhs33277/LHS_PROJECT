package com.lhs.home.user.service;

import com.lhs.home.user.domain.UserDTO;

public interface SignUpService {

	/* 사용자 ID 중복 조회 */
	public int selectDupicateUserId(String userId);
	
	/* 사용자 등록 */
	public int insertUser(UserDTO loginDTO);
	
	/* 사용자 수정 */
	public int updateUser(UserDTO loginDTO);
}
