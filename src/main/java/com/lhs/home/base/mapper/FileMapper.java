package com.lhs.home.base.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.lhs.home.base.domain.FileDTO;

@Mapper
public interface FileMapper {
	
	/* 다음 파일번호 조회 */
	public int selectNextFileNo();
	
	/* 파일 등록 */
	public int insertFileList(FileDTO fileDTO);
	
	

}
