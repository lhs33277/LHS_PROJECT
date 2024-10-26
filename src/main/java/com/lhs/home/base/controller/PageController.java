package com.lhs.home.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lhs.home.utils.JSPUtils;

/*
 * 작성자 : 이형식
 * 작성일 : 2024-10-07
 * 파일명 : PageController.java
 * 기 능 : 단순 page 이동 Mapping 작성
 */
@Controller
@RequestMapping("/page")
public class PageController {
	
	
	
	/* 로그인 page 이동 */
	@RequestMapping("/loginFormPage.do")
	public String loginFormPage(Model model) {
		return JSPUtils.modelAndJsp(model, "/user/loginForm");
	}
	
	/* 회원가입 page 이동 */
	@RequestMapping("/signUpFormPage.do")
	public String joinFormPage(Model model) {
		return JSPUtils.modelAndJsp(model, "user/signUpForm");
		
	}
	
	/* 공지사항 page 이동 */
	@RequestMapping("/noticePage.do")
	public String noticePage(Model model) {
		return JSPUtils.modelAndJsp(model, "board/notice");
	}
	
	/* 공지사항 게시글 작성 page 이동 */
	@RequestMapping("/writeFormPage.do")
	public String writeFormPage(Model model) {
		return JSPUtils.modelAndJsp(model, "board/writeForm");
	}
}
