package com.lhs.home.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lhs.home.utils.JSPUtils;

/*
 * 작성자 : 이형식
 * 작성일 : 2024-10-07
 * 파일명 : MainController.java
 * 기 능 : index page Mapping 작성
 */
@Controller
public class MainController {

	/* 시작 페이지 */
	@RequestMapping("/")
	public String index(Model model) {
		
		return JSPUtils.modelAndJsp(model, "index");
	}
}
