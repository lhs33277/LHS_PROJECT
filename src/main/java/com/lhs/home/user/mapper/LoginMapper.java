package com.lhs.home.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.lhs.home.user.domain.UserDTO;

@Mapper
public interface LoginMapper {
	
	/* 로그인 전처리 */
	public int updatePrePareLogin(UserDTO loginDTO);

}
