package com.lhs.home.base.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
	
	private Integer fileNo;

	private Integer fileSn;
	
	private String fileName;
	
	private String originalFileName;
	
	private String filePath;
	
	private Long fileSize;
	
	private String fileCntn;
	
	private Integer registUserNo;
	
	private String registDt;
	
	
}
