package com.lhs.home.utils;

public class StringUtils {

	/* String null check  */
	public static boolean isNull(String inputStr) {
		if(inputStr == null) {
			return true;
		}
		
		return false;
	}
	
	/* Object null check */
	public static boolean isNull(Object inputObject) {
		if(inputObject == null) {
			return true;
		}
		
		return false;
	}
	
	/*  */
	public static boolean isEmpty(String inputStr) {
		if("".equals(inputStr)) {
			return true;
		}
		
		return false;
	}
	
	/* 첫 번 째 인자가 null이면, 2번 째 인자 반환 */
	public static String nvl(String inputStr, String value) throws Exception {
		if(value == null) {
			value = "";
		}
		
		if(inputStr == null) {
			return value;
		}
		
		return inputStr;
	}
	
	/* 휴대폰번호 처리 */
	public static String phone(String bf_phone) throws Exception {
		
		String af_phone = "";
		
		/* null, 빈문자열 처리 */
		if (StringUtils.isNull(bf_phone) || "".equals(bf_phone.trim())) {
			return "";
		}

		/* trim 처리 */
		af_phone = bf_phone.trim();
		
		/* '+', 공백, '-' 처리 */
		af_phone = af_phone.replaceAll("[+\\s-]", "");
		
		/* 한국 국가코드 82 처리 */
		if (af_phone.startsWith("82")) {
			af_phone = af_phone.substring(2);
		}
		
		/* 10으로 시작 처리 */
		if (!af_phone.startsWith("010")) {
			
			if (af_phone.startsWith("1")) {
					af_phone = "0" + af_phone;
			}
			
		return af_phone;
		}
		
		return af_phone;
	}
}
