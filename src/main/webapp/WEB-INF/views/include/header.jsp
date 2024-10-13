<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<script>
	
	/* 로그인 page 이동 */
	function fnLoginFormPage() {
		location.href = "/page/loginFormPage.do";
	}
	
	/* 회원가입 page 이동 */
	function fnSignUpFormPage() {
		location.href = "/page/signUpFormPage.do";
	}
	
	/* 로그아웃 */
	function fnLogOutAction() {
		if(confirm('정말 로그아웃 하시겠습니까?')) {
			location.href = "/login/logOutAction.do";
		}
		
	}
	
</script>
</head>
<body>
	<div class="div_inner_header">
		
		<c:if test="${sessionScope.userDTO != null}">
		
			<img src="${sessionScope.userDTO.thumbImg}" class="thumb_img"/>
			<span class="span_right">${sessionScope.userDTO.userName}님 환영합니다.</span>
			<input type="button" id="btn_logOut" class="btn_right" value="로그아웃" onclick="fnLogOutAction()">
		
		</c:if>
		
		<c:if test="${sessionScope.userDTO == null}">
			<input type="button" id="btn_signIn" class="btn_right" value="로그인하러가기" onclick="fnLoginFormPage()">
			<input type="button" id="btn_signUp" class="btn_right" value="회원가입" onclick="fnSignUpFormPage()">
		</c:if>
	</div>
	
</body>
</html>