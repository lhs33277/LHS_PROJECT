package com.lhs.home.test;

import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class Test {
	
	private static ServletContext servletContext;
	
	public Test(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

	public static void main(String[] args) {
		HttpServletRequest request;
		//System.out.println(abc(request));
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(calendar.YEAR);
		System.out.println(servletContext.getRealPath("/"));

	}
	
	public static String abc(HttpServletRequest request) {
		ServletContext servletContext = request.getServletContext();
		return servletContext.getRealPath("/resources/");
	}

}
