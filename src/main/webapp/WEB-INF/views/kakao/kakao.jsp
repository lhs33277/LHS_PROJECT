<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
<script>

	$(document).ready(function(){
		
		var grant_type = 'authorization_code';
		var redirect_uri = 'http://localhost:9090/user/redirect.do';
		var code = '${code}';
		
		$('#grant_type').value = grant_type;
		$('#client_id').value = client_id;
		$('#redirect_uri').value = redirect_uri;
		$('#code').value = code;
		
		$('#kakaoForm').action = 'https://kauth.kakao.com/oauth/token';
		$('#kakaoForm').submit();
		
	});
	
	
		
	
</script>
</head>
<body>
	<form id="kakaoForm" method="post" enctype="application/x-www-form-urlencoded">
		<input type="hidden" id="grant_type" name="grant_type">
		<input type="hidden" id="client_id" name="client_id">
		<input type="hidden" id="redirect_uri" name="redirect_uri">
		<input type="hidden" id="code" name="code">
	</form>

</body>
</html>