<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	$(document).ready(function(){
		
	})
	
	function fnWriteFormPage() {
		if(${sessionScope.userDTO == null}) {
			alert('로그인 후 게시글 작성이 가능합니다.');
			return;
		}
		location.href='/page/writeFormPage.do';
	}
	
</script>
</head>
<body>
	<div style="color:#4d00ff;">
		<c:if test="${boardCode == '1001'}">
			<h1>공지사항</h1>
		</c:if>
		<c:if test="${boardCode == '1002'}">
			<h2>자유게시판</h2>
		</c:if>
	</div>
	
	<hr>
	
	<div>
		<form>
		
			<span style="color:red;font-size:20px;"><strong>※ 조회조건 ※</strong></span>
			
			<br>
			
			<select>
				<option>최근 날짜순</option>
				<option>이전 날짜순</option>
				<option>조회수 많은순</option>
			</select>
			<span>으로</span>
			
			<br>
			
			<span>페이지당</span>
			<select>
				<option>10개</option>
				<option>20개</option>
				<option>30개</option>
			</select>
			<span>씩 보기</span>
			
			<br>
			
			<select>
				<option>제목</option>
				<option>내용</option>
				<option>작성자</option>
			</select>
			<input type="text">
			<input type="button" value="검색">
			
		</form>
	</div>
	
	<hr>
	
	<div>
		총 <span>0</span>개의 게시글이 있습니다.
		<span style="float:right;">총 <span>0</span> / <span>0 </span>페이지</span>
		<table style="margin-top:5px;">
			<colgroup>
				<col style="width:7%">
				<col style="width:50%">
				<col style="width:13%">
				<col style="width:20%">
				<col style="width:10%">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="BoardDTO" items="${boardList}" varStatus="status">
				
					<tr>
						<td class="middle_text">${BoardDTO.boardNo}</td>
						<td style="padding-left:10px">${BoardDTO.title}</td>
						<td class="middle_text">${BoardDTO.userName}</td>
						<td class="middle_text">${BoardDTO.registDt}</td>
						<td class="middle_text">${BoardDTO.hit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div id="div_pager"><jsp:include page="../include/pager.jsp"/></div>
	
	<div style="text-align:right;">
		<input type="button" value="글쓰기" onclick="fnWriteFormPage()">
	</div>

</body>
</html>