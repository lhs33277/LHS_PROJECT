<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function fnPlusIconClick() {
		
	}
</script>
</head>
<body>
<div>
	<ul class="depth1">
		<li>
			소개<div class="div_img"><img src="../../resources/images/icon/btn_plus.jpg" class="img_btnPlus"></div>
		</li>
		<li>
			<ul class="depth2">
				<li>홈페이지소개</li>
				<li>이형식소개</li>
			</ul>
		</li>
	</ul>
	<ul class="depth1">
		<li>
			<strong>게시판</strong>
		</li>
		<li>
			<ul class="depth2">
				<li><a href="/board/selectBoardList.do?boardCode=1001">공지사항</a></li>
				<li>자유게시판</li>
			</ul>
		</li>
	</ul>
</div>

</body>
</html>