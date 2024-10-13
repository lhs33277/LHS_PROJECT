<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<div>
		로그인하자
		<form>
			id : <input type="text" name="id">
			pw : <input type="text" name="pw">
			<input type="button" id="login" value="로그인">
		</form>
	
	</div>
	
	<div>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=9321223771b9dee49f2799e3b6d44c7e
		&redirect_uri=http://localhost:9090/login/kakaoLoginAction.do&response_type=code">
			<img src="../../resources/images/kakao_login_image.png" alt="카카오로그인">
		</a>
	</div>
</body>
</html>