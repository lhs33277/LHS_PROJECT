<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main-layout</title>
<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="/resources/css/content.css">
<script src="../resources/js/jquery-3.6.0.js"></script>
<script src="../resources/js/main.js"></script>
<script>

	/* main-layout에 공통 onload 함수들을 선언한다. */
	$(document).ready(function() {
		
		/* 팝업 처리 */
		
		/* alertMsg 처리 */
		var alertMsg = '${alertMsg}';
		if('${alertMsg}' != 'null' && '${alertMsg}' != "") {
			alert(alertMsg);
		}
		
	});

</script>
</head>
<body>
	<div class="div_header">
		<jsp:include page="header.jsp"/>
	</div>
	<div class="container">
		<div class="div_side">
			<jsp:include page="side.jsp"/>
		</div>
		
		<div class="div_content">
			<jsp:include page="${pageContent}.jsp"/>
		</div>
	</div>
	
	<div class="div_footer">
		<jsp:include page="footer.jsp"/>
	</div>

</body>
</html>