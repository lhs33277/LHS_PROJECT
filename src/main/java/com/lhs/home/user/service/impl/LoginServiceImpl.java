package com.lhs.home.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.home.user.domain.UserDTO;
import com.lhs.home.user.mapper.LoginMapper;
import com.lhs.home.user.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	/* 사용자 조회 (고객번호) */
	@Override
	public UserDTO selectUserByUserNo(int userNo) {
		return loginMapper.selectUserByUserNo(userNo);
	}
	
	/* 사용자 조회 (ID) */
	@Override
	public UserDTO selectUserByUserId(String userId) {
		return loginMapper.selectUserByUserId(userId);
	}

	/* 사용자 조회 (휴대폰번호) */
	@Override
	public UserDTO selectUserByMobileNo(String monileNo) {

		return loginMapper.selectUserByMobileNo(monileNo);
	}

	/* 로그인 전처리 */
	@Override
	public UserDTO updatePrefixLogin(UserDTO loginDTO) {
		
		/* lastLoginDt, loginCo 변경. */
		loginMapper.updatePrefixLogin(loginDTO);
		
		/* 재조회 */
		loginDTO = loginMapper.selectUserByMobileNo(loginDTO.getMobileNo());
		
		return loginDTO;
	}
}
