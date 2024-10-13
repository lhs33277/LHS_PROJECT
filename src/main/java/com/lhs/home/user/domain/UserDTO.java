package com.lhs.home.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Integer userNo;			// 고객번호

	private String userId;			// ID

	private String userPw;			// 비밀번호

	private String authType;		// 로그인 구분

	private String kakaoId;			// 카카오ID

	private String naverId;			// 네이버ID

	private String googleId;		// 구글ID

	private String facebookId;		// 페이스북ID

	private String userBirth;		// 생년월일

	private String userName;		// 이름

	private String gender;			// 성별

	private String mobileNo;		// 휴대폰번호

	private String email;			// 이메일주소

	private String zip;				// 우편번호

	private String address;			// 주소
	
	private String thumbImg;		// 썸네일 이미지

	private String userRole;		// 사용자 역할

	private String useYn;			// 사용여부

	private String lastLoginDt;		// 최종로그인일시

	private Integer loginCo;		// 로그인 횟수

	private String accept;			// 동의항목

	private String registDt;		// 등록일자

	private String updtDt;			// 수정일자

}