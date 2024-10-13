package com.lhs.home.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.lhs.home.user.domain.UserDTO;

@Mapper
public interface LoginMapper {
	
	/* 사용자 조회 (고객번호) */
	public UserDTO selectUserByUserNo(int userNo);
	
	/* 사용자 조회 (ID) */
	public UserDTO selectUserByUserId(String userId);
	
	/* 사용자 조회 (휴대폰번호) */
	public UserDTO selectUserByMobileNo(String monileNo);
	
	/* 로그인 전처리 */
	public int updatePrefixLogin(UserDTO loginDTO);

}
