package com.lhs.home.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.home.user.domain.UserDTO;
import com.lhs.home.user.mapper.SignUpMapper;
import com.lhs.home.user.service.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService {
	
	@Autowired
	private SignUpMapper signUpMapper;
	
	/* 사용자 ID 중복 조회 */
	@Override
	public int selectDupicateUserId(String userId) {
		
		return signUpMapper.selectDupicateUserId(userId);
	}
	
	/* 사용자 등록 */
	@Override
	public int insertUser(UserDTO loginDTO) {

		return signUpMapper.insertUser(loginDTO);
	}
	
	/* 사용자 수정 */
	@Override
	public int updateUser(UserDTO loginDTO) {
		
		return signUpMapper.updateUser(loginDTO);
	}

}
