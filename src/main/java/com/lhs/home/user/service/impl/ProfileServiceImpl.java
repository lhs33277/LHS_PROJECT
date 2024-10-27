package com.lhs.home.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.home.user.domain.UserDTO;
import com.lhs.home.user.mapper.ProfileMapper;
import com.lhs.home.user.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileMapper profileMapper;
	
	/* 사용자 조회 (고객번호) */
	@Override
	public UserDTO selectUserByUserNo(int userNo) {
		return profileMapper.selectUserByUserNo(userNo);
	}
	
	/* 사용자 조회 (ID) */
	@Override
	public UserDTO selectUserByUserId(String userId) {
		return profileMapper.selectUserByUserId(userId);
	}

	/* 사용자 조회 (휴대폰번호) */
	@Override
	public UserDTO selectUserByMobileNo(String monileNo) {

		return profileMapper.selectUserByMobileNo(monileNo);
	}

}
