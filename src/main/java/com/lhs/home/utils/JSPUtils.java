package com.lhs.home.utils;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class JSPUtils {
	
	private final static String subfix = "..";
	private final static String subfixSlash = "../";

	/**
	 * 공통 레이아웃(header, content, footer) 등을 관리하기 위해
	 * 이동하려는 page는 model로 전달하여 main-layout.jsp의 content 영역에 include 한다.
	 * 실제 이동하는 page는 항상 include/main-layout.jsp를 return 한다.
	 * @param model : 선언만 해서 전달한다.
	 * @param pageContent : 이동하려는 jsp page. user/login, /user/login 둘 다 가능하다.
	 */
	public static String modelAndJsp(Model model, String pageContent) {
		
		if(pageContent == null) {
			return null;
		}
		
		/* 전달받은 page가 '/'로 시작하면 '..'를 붙여주고, 아니면 '../'를 붙여준다. */
		if(pageContent.startsWith("/")) {
			pageContent = subfix + pageContent;
		} else {
			pageContent = subfixSlash + pageContent;
		}
		
		/* 이동하려는 page를 model에 담아서 content 영역에 뿌린다. */
		model.addAttribute("pageContent", pageContent);
		
		/* 실제 return은 레이아웃 page. */
		return "include/main-layout";
		
	}
	
	/**
	 * 요청이 처리되었습니다. 회원가입이 완료되었습니다. 등 alert 메세지와 함께 redirect 이동한다.
	 * alert 메세지는 main-layout.jsp에서 호출한다.
	 * @param model : 선언만 해서 전달한다.
	 * @param redirectAttributes : 선언만 해서 전달한다.
	 * @param alertMsg : alert 메세지를 전달한다.
	 * @param pageContent : redirect 이동하려는 controller url.
	 */
	public static String alertAndRedirect(RedirectAttributes redirectAttributes, String alertMsg, String pageContent) {
		
		if(pageContent == null) {
			redirectAttributes.addFlashAttribute("alertMsg", "잘못된 redirect url입니다.");
			/* index page로 redirect */
			return "redirect:/";
		}
		
		//modelAndJsp(model, pageContent);
		
		redirectAttributes.addFlashAttribute("alertMsg", alertMsg);
		return "redirect:" + pageContent;
	}
}
