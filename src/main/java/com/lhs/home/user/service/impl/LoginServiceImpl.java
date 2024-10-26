package com.lhs.home.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.home.user.domain.UserDTO;
import com.lhs.home.user.mapper.LoginMapper;
import com.lhs.home.user.mapper.ProfileMapper;
import com.lhs.home.user.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private ProfileMapper profileMapper;

	/* 로그인 전처리 */
	@Override
	public UserDTO updatePrePareLogin(UserDTO loginDTO) {
		
		/* lastLoginDt, loginCo 변경. */
		loginMapper.updatePrePareLogin(loginDTO);
		
		/* 재조회 */
		loginDTO = profileMapper.selectUserByMobileNo(loginDTO.getMobileNo());
		
		return loginDTO;
	}
}
