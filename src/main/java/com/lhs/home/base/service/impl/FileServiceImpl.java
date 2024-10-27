package com.lhs.home.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.home.base.domain.FileDTO;
import com.lhs.home.base.mapper.FileMapper;
import com.lhs.home.base.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileMapper fileMapper;
	
	/* 다음 파일번호 조회 */
	@Override
	public int selectNextFileNo() {
		return fileMapper.selectNextFileNo();
	}
	
	/* 파일 등록 */
	@Override
	public int insertFile(FileDTO fileDTO) {
		
		return fileMapper.insertFileList(fileDTO);
	}

}
