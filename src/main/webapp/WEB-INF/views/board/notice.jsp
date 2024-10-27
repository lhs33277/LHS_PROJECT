<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function fnWriteFormPage() {
		location.href='/page/writeFormPage.do';
	}
</script>
</head>
<body>

	<div>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>이름</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="BoardDTO" items="${BoardList}" varStatus="status">
					<tr>
						<td>${BoardDTO.boardNo}</td>
						<td>${BoardDTO.title}</td>
						<td>${BoardDTO.userName}</td>
						<td>${BoardDTO.registDt}</td>
						<td>${BoardDTO.hit}</td>
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