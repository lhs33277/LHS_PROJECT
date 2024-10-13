package com.lhs.home.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.lhs.home.user.domain.UserDTO;

@Mapper
public interface SignUpMapper {
	
	/* 사용자 ID 중복 조회 */
	public int selectDupicateUserId(String userId);
	
	/* 사용자 등록 */
	public int insertUser(UserDTO loginDTO);

}
