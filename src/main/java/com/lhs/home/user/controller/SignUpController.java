package com.lhs.home.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lhs.home.user.domain.UserDTO;
import com.lhs.home.user.service.ProfileService;
import com.lhs.home.user.service.SignUpService;
import com.lhs.home.utils.CryptoUtils;
import com.lhs.home.utils.JSPUtils;
import com.lhs.home.utils.LhsException;
import com.lhs.home.utils.StringUtils;

@Controller
@RequestMapping("/signUp")
public class SignUpController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SignUpService signUpService;
	
	@Autowired
	private ProfileService profileService;

	/* 사용자 ID 중복 조회 */
	@ResponseBody
	@RequestMapping("/checkDuplicateId")
	public Map<String, Object> checkDuplicateId(String userId) {
		
		int idCnt = signUpService.selectDupicateUserId(userId);
		
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("idCnt", idCnt);
		
		return returnMap;
	}
	
	/* 회원가입 submit */
	@RequestMapping("/signUpAction.do")
	public String signUpAction(RedirectAttributes redirectAttributes, UserDTO userDTO) throws Exception {
		
		System.out.println(userDTO.toString());
		
		/* 고객정보 조회(휴대전화) */
		UserDTO preUserDTO = profileService.selectUserByMobileNo(userDTO.getMobileNo());
		log.info("휴대전화 기존고객 여부 isNull: " + StringUtils.isNull(preUserDTO));
		
		int res = 0;
		
		/* 1. 기존 데이터가 있는경우 */
		if(!StringUtils.isNull(preUserDTO)) {
			
			System.out.println("111111");
			
			/* 1. ID가 없는 경우 (sns 로그인 했던 사용자) */
			if(StringUtils.isNull(preUserDTO.getUserId())) {
				System.out.println("2222222222");
				/* 정보 덮어쓰기 */
				preUserDTO.setUserId(userDTO.getUserId());
				preUserDTO.setUserPw(CryptoUtils.encrytPassword(userDTO.getUserPw()));
				preUserDTO.setUserName(userDTO.getUserName());
				preUserDTO.setGender(userDTO.getGender());
				preUserDTO.setMobileNo(userDTO.getMobileNo());
				preUserDTO.setEmail(userDTO.getEmail());
				preUserDTO.setUseYn(userDTO.getUseYn());
				preUserDTO.setAccept(userDTO.getAccept());
				
				res = signUpService.updateUser(preUserDTO);
				
				return JSPUtils.alertAndRedirect(redirectAttributes
						, "기존 회원 정보가 존재하여 현재 가입한 정보와 연동되었습니다.\\n로그인 페이지로 돌아갑니다.", "/page/loginFormPage.do");
				
			} else {
				throw new LhsException("1", "이미 기존에 가입한 이력이 있습니다.\\nID찾기를 이용해주세요.");
			}
			
		} else {
			System.out.println("3333333");
			/* 신규 등록 */
			userDTO.setUserPw(CryptoUtils.encrytPassword(userDTO.getUserPw()));
			userDTO.setAuthType("LOCAL");
			userDTO.setUserRole("사용자");
			userDTO.setUseYn("Y");
			
			res = signUpService.insertUser(userDTO);
			return JSPUtils.alertAndRedirect(redirectAttributes, "회원가입이 완료되었습니다.\\n로그인 페이지로 돌아갑니다.", "/page/loginFormPage.do");
		}
		
	}
}
