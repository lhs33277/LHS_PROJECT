package com.lhs.home.utils;

public class LhsUtils {
	public static String phone(String bf_phone) {
		
		try {
			
			if (bf_phone == null || "".equals(bf_phone.trim())) {
				return "";
			}
			
			String af_phone = "";
			af_phone = bf_phone.trim();
			af_phone = af_phone.replaceAll("[+\\s-]", "");
			
			if (af_phone.startsWith("82")) {
				af_phone = af_phone.substring(2);
			}
			
			if (!af_phone.startsWith("010")) {
				if (af_phone.startsWith("1")) {
					af_phone = "0" + af_phone;
				}
			}
			
			return af_phone;
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
