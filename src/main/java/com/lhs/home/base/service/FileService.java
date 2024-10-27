package com.lhs.home.base.service;

import com.lhs.home.base.domain.FileDTO;

public interface FileService {
	
	/* 다음 파일번호 조회 */
	public int selectNextFileNo();
	
	/* 파일 등록 */
	public int insertFile(FileDTO fileDTO);

}
