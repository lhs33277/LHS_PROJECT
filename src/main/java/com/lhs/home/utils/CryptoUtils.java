package com.lhs.home.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class CryptoUtils {
	
	/* hash 암호화 모듈 [encodedSalt:SHA256(password + salt)] */
	public static String encrytPassword(String password) throws Exception {
		
		/* null exception */
		if(StringUtils.isNull(password)) {
			throw new Exception("비밀번호가 없습니다.");
		}
		
		/* salt 획득 */
		String salt = getSalt();
		
		/* 인코딩한 salt */
		String encodedSalt = encodeBase64(salt);
		
		/* slat 첨가 */
		password += salt;
		
		/* SHA256 */
		String hashedStr = sha256(password);
		
		/* 최종적으로 DB에 insert되는 형식 */
		String format = encodedSalt + ":" + hashedStr;
		
		return format;
	}
	
	/* 암호 일치 확인 모듈 [encodedSalt:SHA256(password + salt)] */
	public static boolean checkPassword(String password, String hashedPwd) throws Exception {
		
		/* null return */
		if(StringUtils.isNull(password) || StringUtils.isNull(hashedPwd)) {
			return false;
		}
		
		/* salt 획득 */
		String[] strArr = hashedPwd.split(":");
		
		if(StringUtils.isNull(strArr) || strArr.length != 2) {
			return false;
		}
		
		String salt = decodeBase64(strArr[0]);
		
		/* SHA256(password + salt) */
		String hashedInputPwd = sha256(password + salt);
		if(!strArr[1].equals(hashedInputPwd)) {
			return false;
		}
		
		return true;
	}

	/* SHA256 해쉬 */
	public static String sha256(String inputStr) {
		
		if(StringUtils.isNull(inputStr)) {
			return null;
		}
		
		MessageDigest md = null;
		byte[] hash = null;
		
		try {
			
			md = MessageDigest.getInstance("SHA-256");
			hash = md.digest(inputStr.getBytes());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
			
		StringBuilder sb = new StringBuilder();
		for(byte b : hash) {
			sb.append(String.format("%02X", b));
		}
		
		return sb.toString();
	}
	
	/* salt */
	public static String getSalt() {
		
		byte[] salt = new byte[16];
		SecureRandom random = new SecureRandom();
		random.nextBytes(salt);
		
		StringBuilder sb = new StringBuilder();
		for(byte b : salt) {
			sb.append(String.format("%02X", b));
		}
		
		return sb.toString();
	}
	
	/* encoding */
	public static String encodeBase64(String iputStr) {
		
		return Base64.getEncoder().encodeToString(iputStr.getBytes());
		
	}
	
	/* decoding */
	public static String decodeBase64(String iputStr) {
		
		byte[] encodedBytes = Base64.getDecoder().decode(iputStr);
		return new String(encodedBytes);
		
	}
}
