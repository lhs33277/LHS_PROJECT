<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script>
	$(document).ready(function(){
		
	});
	
	/* global 변수 */
	var gl_idCheck = false; // id 정합성
	var gl_validation = false; // 종합 정합성
	
	/* id 중복체크 */
	function fnCheckDuplicateId() {
		
		$.ajax({
			url : '/signUp/checkDuplicateId',
			type : 'GET',
			data : {
					userId : $('#userId').val()
					},
			dataType : 'json',
			success : function(obj) {
						if(obj.idCnt > 0) {
							alert('이미 사용중인 ID입니다.');
							gl_idCheck = false;
						} else {
							alert('사용 가능한 ID입니다.');
							gl_idCheck = true;
							$('#userId').prop('readonly', true);	  // id 입력창 readonly
							$('#btn_idCheck').prop('disabled', true); // 중복확인 버튼 disabled
						}
					},
			error : function(xhr, textStatus, errorMsg) {
						alert('오류');
						console.log(textStatus + ',' + errorMsg);
						console.log(xhr.status);
						console.log(xhr.responseText);
					}
			
		})
	}
	
	/* id 다시작성 */
	function fnEnableUserId() {
		
		gl_idCheck = false;
		
		$('#userId').val('');					   // id 입력창 초기화
		$('#userId').prop('readonly', false);      // id 입력창 readonly 해제
		$('#btn_idCheck').prop('disabled', false); // 중복확인 버튼 disabled 해제
		
	}
	
	/* 회원가입 submit */
	function fnSignUpAction() {
		
		/* 정합성 check */
		gl_validation = false;
		fnValidationCheck();
		if(!gl_validation) {
			return;
		}
		
		$('#mobileNo').val();
		
		$('#signUpForm').prop('method', 'post');
		$('#signUpForm').prop('action', '/signUp/signUpAction.do');
		$('#signUpForm').submit();
			
	}
	
	function fnValidationCheck() {
		
		/* 필수값 체크 */
		if($('#userId').val() == '' || $('#userId').val() == undefined) {
			alert('id를 입력하세요.');
			return;
		}
		
		/* 개인정보 이용동의 */
		if(!$('#personalInfo').is(':checked')) {
			alert('개인정보 이용동의 체크하세요.');
			return;
		}
		
		/* 광고성 정보 수신동의 */
		var lc_accept;
		
		if( $('#chk_sms').is(':checked') && $('#chk_email').is(':checked') ) { // 둘 다 체크
			lc_accept = 'ALL';
		} else if( $('#chk_sms').is(':checked') && !$('#chk_email').is(':checked') ) { // SMS만 체크
			lc_accept = 'SMS';
		} else if( !$('#chk_sms').is(':checked') && $('#chk_email').is(':checked') ) { // email만 체크
			lc_accept = 'EMAIL';
		}
		
		$('#accept').val(lc_accept);
		
		/* 휴대폰 */
		var lc_mobileNo = '';
		lc_mobileNo += $('#mobileNo1').val();
		lc_mobileNo += $('#mobileNo2').val();
		lc_mobileNo += $('#mobileNo3').val();
		
		$('#mobileNo').val(lc_mobileNo);
		alert(lc_mobileNo);
		
		gl_validation = true;
		
	}
	
	/* input event 필터링 */
	function fnInputTextFiltering(inputElement) {
		
		/* 공통 입력불가 */
		
		/* id, 이름 */
		if(inputElement.id == 'userId' || inputElement.id == 'userName' || inputElement.id == 'userName') {
			if(inputElement.value.includes('a')) {
				alert('a');
			}
		}
		
		
	}
	
	
	
</script>
</head>
<body>
	
	<form id="signUpForm">

		<div>
			<h3>개인정보 이용동의 (필수)</h3>
			형식이가 이용합니다.
			<br>
			동의하시겠습니까?
			<input type="checkbox" id="personalInfo">
		
			<h3>광고성 정보 수신동의 (선택)</h3>
			이형식이 광고성 문자나 메일을 보낼 수 있습니다.
			<br>
			동의하시겠습니까?
			SMS<input type="checkbox" id="chk_sms">
			EMAIL<input type="checkbox" id="chk_email">
		</div>
		
		<hr>
	
		<div>
			
			<input type="hidden" id="accept" name="accept" value="">
			<input type="hidden" id="mobileNo" name="mobileNo" value="">
			
			<table border="1">
				<caption>회원가입</caption>
				<tbody>
					<tr>
						<th>ID</th>
						<td>
							<input type="text" id="userId" name="userId" oninput="fnInputTextFiltering(this)">
							<input type="button" id="btn_idCheck" onclick="fnCheckDuplicateId()" value="중복확인">
							<input type="button" id="btn_idEnable" onclick="fnEnableUserId()" value="다시작성">
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="text" id="userPw" name="userPw"></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" id="userName" name="userName"></td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td><input type="text" id="userBirth" name="userBirth"></td>
					</tr>
					<tr>
						<th>휴대전화</th>
						<td><input type="text" id="mobileNo1"> - <input type="text" id="mobileNo2"> - <input type="text" id="mobileNo3"></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td><input type="button" id="btn_signUp" onclick="fnSignUpAction()" value="가입하기"><input type="button" id="btn_back" value="돌아가기"></td>
					
					</tr>
				
				</tfoot>
			</table>
			
		</div>
		
	</form>
	
	
</body>
</html>