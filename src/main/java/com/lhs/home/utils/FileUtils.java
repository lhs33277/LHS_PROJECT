package com.lhs.home.utils;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	/* 파일 upload */
	public static File fileUpload(MultipartFile multipartFile) throws Exception {
		
		if(StringUtils.isNull(multipartFile)) {
			return null;
		}
		
		File dir = new File(FileUtils.getTodayPath());
		
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		File realFile = new File(dir, getUUID(multipartFile.getOriginalFilename()));

		multipartFile.transferTo(realFile);
		
		return realFile;
		
	}
	
	/* 경로 확인 */
	public static String getTodayPath() {
		
		Calendar calendar = Calendar.getInstance();
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		String separator = File.separator;
		
		return "C:" + separator + "study" + separator + "upload" + separator + year + separator + month + separator + day;
	
	}
	
	/* 파일명 UUID 변환 */
	public static String getUUID(String fileName) {
		
		if(StringUtils.isNull(fileName) || StringUtils.isEmpty(fileName)) {
			return fileName;
		}
		
		String uniqueFileName = UUID.randomUUID().toString().replaceAll("\\-", "") + "_" + fileName;
		
		return uniqueFileName;
	}

}
