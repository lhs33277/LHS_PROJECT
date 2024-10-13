package com.lhs.home.user.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lhs.home.user.domain.UserDTO;
import com.lhs.home.user.service.LoginService;
import com.lhs.home.user.service.SignUpService;
import com.lhs.home.utils.LhsUtils;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SignUpService signUpService;
	

	@RequestMapping("/loginAction.do")
	public String login(HttpServletRequest request) {
		
		return "user/loginForm";
		
	}

	/* 카카오 로그인 api */
	@RequestMapping({ "/kakaoLoginAction.do" })
	public String kakaoLogin(@RequestParam String code, HttpServletRequest request, Model model) {
		
		try {
			
			/* kakao request URL */
			String requestUrl = "https://kauth.kakao.com/oauth/token";
			
			/* kakao parameter */
			String contentType = "application/x-www-form-urlencoded;charset=utf-8";
			String grantType = "authorization_code";
			String clientId = "9321223771b9dee49f2799e3b6d44c7e";
			String redirectURI = "http://localhost:9090/login/kakaoLoginAction.do";
			
			URL url1 = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-type", contentType);
			conn.setDoOutput(true);
			
			String param = "grant_type=" + grantType + "&client_id=" + clientId + "&redirect_uri=" + redirectURI
					+ "&code=" + code;
			
			/* parameter 전송 */
			OutputStream os = conn.getOutputStream();
			os.write(param.getBytes());
			log.info("requestUrl : " + conn.getResponseCode() + "/" + conn.getResponseMessage());
			
			/* kakao 응답 생성 */
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			
			String result = sb.toString();
			
			os.flush();
			os.close();
			br.close();
			
			/* JSON으로 parsing */
			JSONParser parser = new JSONParser();
			JSONObject resultObj = (JSONObject) parser.parse(result);
			
			String access_token = resultObj.get("access_token").toString();
			String id_token = resultObj.get("id_token").toString();
			
			log.info("access_token : " + access_token);
			log.info("id_token : " + id_token);
			
			/* kakao 2차 요청 시작 */
			String requestUrl2 = "https://kapi.kakao.com/v2/user/me";
			String Authorization = "Bearer " + access_token;
			
			URL url2 = new URL(requestUrl2);
			HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
			conn2.setRequestProperty("Authorization", Authorization);
			conn2.setRequestProperty("Content-type", contentType);
			conn2.setDoOutput(true);
			log.info("requestUrl2 : " + conn2.getResponseCode() + "/" + conn2.getResponseMessage());
			BufferedReader br2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
			sb.delete(0, sb.length());
			
			line = "";
			while ((line = br2.readLine()) != null) {
				sb.append(line);
			}
			
			br2.close();
			
			String result2 = sb.toString();
			JSONObject resultObj2 = (JSONObject) parser.parse(result2);
			log.info("JSONObject : " + resultObj2);
			JSONObject kakao_account = (JSONObject) resultObj2.get("kakao_account");
			JSONObject profile = (JSONObject) kakao_account.get("profile");
			
			/* 응답받은 데이터 */
			String id = resultObj2.get("id").toString();
			String birthday = kakao_account.get("birthday").toString();
			String birthyear = kakao_account.get("birthyear").toString();
			String gender = kakao_account.get("gender").toString();
			String ageRange = kakao_account.get("age_range").toString();
			String email = kakao_account.get("email").toString();
			String thumbnailImageUrl = profile.get("thumbnail_image_url").toString();
			String nickname = profile.get("nickname").toString();
			String profileImageUrl = profile.get("profile_image_url").toString();
			String name = kakao_account.get("name").toString();
			String phoneNumber = kakao_account.get("phone_number").toString();
			
			log.info("id : " + id);
			log.info("birthday : " + birthday);
			log.info("birthyear : " + birthyear);
			log.info("gender : " + gender);
			log.info("age_range : " + ageRange);
			log.info("email : " + email);
			log.info("thumbnail_image_url : " + thumbnailImageUrl);
			log.info("nickname : " + nickname);
			log.info("profile_image_url : " + profileImageUrl);
			log.info("name : " + name);
			log.info("phone_number : " + phoneNumber);
			
			/* 생년월일 */
			String birth = "";
			if (birthday != null && !"".equals(birthday) && birthyear != null && !"".equals(birthyear)) {
				birth = String.valueOf(birthyear) + birthday;
				log.info("birth : " + birth);
			}
			
			/* 휴대폰번호 */
			String monileNo = LhsUtils.phone(phoneNumber);
			log.info("monileNo : " + monileNo);
			
			/* 성별 */
			gender = "male".equals(gender) ? "남" : ("female".equals(gender) ? "여" : "");
			
			/* 기존 회원 정보 조회 (휴대폰번호) */
			HttpSession session = request.getSession();
			
			UserDTO userDTO = loginService.selectUserByMobileNo(monileNo);
			
			if(userDTO != null) {
				
				/* 로그인 전처리 */
				userDTO = loginService.updatePrefixLogin(userDTO);
				
				/* 로그인 처리 */
				session.setAttribute("userDTO", userDTO);
					
				return "redirect: /";
				
			} else {
				/* 신규 등록 */
				userDTO = UserDTO.builder()
						.userNo(null)
						.userId(null)
						.userPw(null)
						.authType("KAKAO")
						.kakaoId(id)
						.naverId(null)
						.googleId(null)
						.facebookId(null)
						.userBirth(birth)
						.userName(name)
						.gender(gender)
						.mobileNo(monileNo)
						.email(email)
						.zip(null)
						.address(null)
						.thumbImg(thumbnailImageUrl)
						.userRole("사용자")
						.useYn("Y")
						.lastLoginDt(null)
						.loginCo(1)
						.accept(null)
						.registDt(null)
						.updtDt(null)
						.build();
				
				int res = signUpService.insertUser(userDTO);
				if(res < 1) {
					throw new Exception("안됐음");
				}
				session.setAttribute("userDTO", userDTO);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect: /";
	}

	/* 로그아웃 */
	@RequestMapping({ "/logOutAction.do" })
	public String logOutAction(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
		}
		
		return "redirect: /";
		
	}
}
