package com.lhs.home.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lhs.home.user.domain.UserDTO;
import com.lhs.home.user.service.SignUpService;
import com.lhs.home.utils.JSPUtils;

@Controller
@RequestMapping("/signUp")
public class SignUpController {
	
	@Autowired
	private SignUpService signUpService;

	/* 사용자 ID 중복 조회 */
	@ResponseBody
	@RequestMapping("/checkDuplicateId")
	public Map<String, Object> checkDuplicateId(String userId) {
		
		int idCnt = signUpService.selectDupicateUserId(userId);
		
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("idCnt", idCnt);
		
		return returnMap;
	}
	
	/* 회원가입 */
	@RequestMapping("/signUpAction.do")
	public String signUpAction(RedirectAttributes redirectAttributes, UserDTO userDTO) {
		
		System.out.println(userDTO.toString());
		
		userDTO.setGender(null);
		userDTO.setEmail(null);
		userDTO.setZip(null);
		userDTO.setUserRole("사용자");
		userDTO.setUserBirth("Y");
		userDTO.setLoginCo(1);
		
		signUpService.insertUser(userDTO);
		
		
		return JSPUtils.alertAndRedirect(redirectAttributes, "회원가입이 완료되었습니다.", "/page/loginFormPage.do");
	}
}
