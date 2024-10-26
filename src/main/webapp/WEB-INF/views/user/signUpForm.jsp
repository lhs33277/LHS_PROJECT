<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script>
	$(document).ready(function(){
		fn_birthFocusOut();
	});
	
	/* global 변수 */
	var gl_idCheck = false; // id 중복확인 정확성
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
		
		/* id check */
		if(!gl_idCheck) {
			gfn_alert('ID 중복확인을 먼저 진행해주세요.');
			return;
		}
		
		/* 정합성 check */
		gl_validation = false;
		fnValidationCheck();
		if(!gl_validation) {
			return;
		}
		
		/* 생년월일 value 생성 */
		var lc_userBirth = '';
		var lc_firstSufRrn = $('#firstSufRrn').val(); // 주민번호 뒤 첫자리
		if(lc_firstSufRrn == 1 || lc_firstSufRrn == 2 || lc_firstSufRrn == 5 || lc_firstSufRrn == 6) {
			lc_userBirth += '19';
		} else {
			lc_userBirth += '20';
		}
		lc_userBirth += $('#preRrn').val();
		gfn_alert(lc_userBirth);
		$('#userBirth').val(lc_userBirth);
		
		/* gender value 생성 */
		if(lc_firstSufRrn == 1 || lc_firstSufRrn == 3 || lc_firstSufRrn == 5 || lc_firstSufRrn == 7) {
			$('#gender').val('남');
		} else {
			$('#gender').val('여');
		}
		
		/* 휴대폰 value 생성 */
		var lc_mobileNo = '';
		lc_mobileNo += $('#mobileNo1').val();
		lc_mobileNo += $('#mobileNo2').val();
		lc_mobileNo += $('#mobileNo3').val();
		
		$('#mobileNo').val(lc_mobileNo);
		
		/* email value 생성 */
		var lc_email = '';
		lc_email += $('#email1').val();
		lc_email += '@';
		lc_email += $('#email2').val();
		$('#email').val(lc_email);
		
		/* submit */
		$('#signUpForm').prop('method', 'post');
		$('#signUpForm').prop('action', '/signUp/signUpAction.do');
		$('#signUpForm').submit();
			
	}
	
	function fnValidationCheck() {
		
		/* ID 검증 */
		var lc_idCheck = $('#userId').val();
		if( gfn_isNull(lc_idCheck) || gfn_trim(lc_idCheck) == '' ) {
			alert('ID를 입력하세요.');
			return;
		}
		
		var lc_idPattern = /^[a-zA-Z0-9_]{5,15}$/;
		if(!lc_idPattern.test(lc_idCheck)) {
			alert('ID는 5~15자의 영어 대소문자와 숫자, 특수문자(_) 만 사용 가능합니다.');
			return;
		}
		
		/* 비밀번호 검증 */
		var lc_pwCheck = $('#userPw').val();
		if( gfn_isNull(lc_pwCheck) || gfn_trim(lc_pwCheck) == '' ) {
			alert('비밀번호를 입력하세요.');
			return;
		}
		
		var lc_pwPattern = /^[a-zA-Z0-9_!@#]{5,15}$/;
		if(!lc_pwPattern.test(lc_pwCheck)) {
			alert('비밀번호는 5~15자의 영어 대소문자와 숫자, 특수문자(!@#_) 만 사용 가능합니다.');
			return;
		}
		
		if(lc_pwCheck != $('#checkUserPw').val()) {
			alert('비밀번호가 일치하지 않습니다.');
			return;
		}
		
		/* 이름 검증 */
		var lc_nameCheck = $('#userName').val();
		if( gfn_isNull(lc_nameCheck) || gfn_trim(lc_nameCheck) == '' ) {
			alert('성명을 입력하세요.');
			return;
		}
		
		var lc_namePattern = /^[a-zA-Z가-힣]{2,9}$/;
		if(!lc_namePattern.test(lc_nameCheck)) {
			alert('성명은 2~9글자의 한글, 영어 대소문자만 입력 가능합니다.');
			return;
		}
		
		/* 생년월일 */
		if($('#preRrn').val() == '' || $('#firstSufRrn').val() == '') {
			alert('휴대폰 번호를 입력하세요.');
		}
		
		/* 휴대폰 */
		
		/* email */
		if($('#email1').val() == '' || $('#email2').val() == '') {
			gfn_alert('EMAIL을 입력하세요.');
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
		
		/* 최종 true */
		gl_validation = true;
		
	}
	
	/* input event 필터링 */
	function fnInputTextFiltering(inputElement) {
		
		/* 공통 입력불가 */
		
		/* id, 이름 */
		if(inputElement.id == 'userId' || inputElement.id == 'userName' || inputElement.id == 'userName') {
			if(inputElement.value.includes('a')) {
				
			}
		}
		
		if(inputElement.id == 'userPw' || inputElement.id == 'checkUserPw') {
			
			if( $('#checkUserPw').val() != '') {
				
				if($('#userPw').val() != $('#checkUserPw').val()) {
					$('#checkUserPwText').text('* 비밀번호가 일치하지 않습니다.');
					$('#checkUserPwText').removeClass('on');
				} else {
					$('#checkUserPwText').text('* 비밀번호가 일치합니다!');
					$('#checkUserPwText').addClass('on');
				}
				
			} else {
				
				$('#checkUserPwText').text('* 비밀번호를 입력하세요.')
				$('#checkUserPwText').removeClass('on');
			}
			
		}
	}
	
	/* fnSelectChange */
	function fnSelectChange() {
		
		var lc_selectEmail = $('#selectEmail').val();
		if(lc_selectEmail == '직접입력') {
			$('#email2').attr('readonly', false);
			$('#email2').val('');
		} else {
			$('#email2').attr('readonly', true);
			$('#email2').val(lc_selectEmail);
		}
	}
	
	/* focusout 이벤트 */
	function fn_birthFocusOut() {
		
		/* 생년월일 focusout */
		$('#preRrn').on('focusout', function() {
			
			var lc_birthPattern = /^[0-9]{6}$/;
			if(!lc_birthPattern.test($('#preRrn').val()) && $('#preRrn').val() != '') {
				gfn_alert('생년월일을 6자리 숫자로 입력해주세요.');
				$('#preRrn').val('');
				return;
			}
			
		})
		
		/* 주민번호 뒷 첫자리 focusout */
		$('#firstSufRrn').on('focusout', function() {
			
			var lc_sufBirthPattern = /^[1-9]{1}$/;
			if(!lc_sufBirthPattern.test($('#firstSufRrn').val()) && $('#firstSufRrn').val() != '') {
				gfn_alert('1~9의 숫자만 입력 가능합니다.');
				$('#firstSufRrn').val('');
				return;
			}
			
		})
		
		/* 휴대폰번호 focusout */
		var lc_mobilePattern = /^[0-9]{4}$/;
		$('#mobileNo2').on('focusout', function() {
			
			if(!lc_mobilePattern.test($('#mobileNo2').val()) && $('#mobileNo2').val() != '') {
				gfn_alert('휴대폰번호를 xxx-xxxx-xxxx 형식의 숫자로 입력해주세요.');
				$('#mobileNo2').val('');
				return;
			}
			
		})
		
		/* 휴대폰번호 focusout2 */
		$('#mobileNo3').on('focusout', function() {
			
			if(!lc_mobilePattern.test($('#mobileNo3').val()) && $('#mobileNo3').val() != '') {
				gfn_alert('휴대폰번호를 xxx-xxxx-xxxx 형식의 숫자로 입력해주세요.');
				$('#mobileNo3').val('');
				return;
			}
			
		})
		
		/* EMAIL focusout */
		$('#email2').on('focusout', function() {
			
			var lc_emailPattern = /\.[a-zA-Z]{2,}$/;
			if(!lc_emailPattern.test($('#email2').val()) && $('#email2').val() != '') {
				gfn_alert('유효하지 않은 email 형식입니다.');
				$('#email2').val('');
				return;
			}
			
		})
	}
	
	
	
</script>
</head>
<body>

	<form id="signUpForm">

		<div>
			<h3>개인정보 이용동의 (필수)</h3>
			형식이가 이용합니다. <br> 동의하시겠습니까? <input type="checkbox"
				id="personalInfo">

			<h3>광고성 정보 수신동의 (선택)</h3>
			형식이가 광고성 문자나 메일을 보낼 수 있습니다. <br>
			동의하시겠습니까? SMS<input type="checkbox" id="chk_sms">EMAIL<input type="checkbox" id="chk_email">
		</div>

		<hr>

		<div>

			<input type="hidden" id="userBirth" name="userBirth" value="">
			<input type="hidden" id="gender" name="gender" value="">
			<input type="hidden" id="mobileNo" name="mobileNo" value="">
			<input type="hidden" id="email" name="email" value="">
			<input type="hidden" id="accept" name="accept" value="">

			<table class="formTable">
				<colgroup>
					<col width="25%"/>
					<col width="75%"/>
				</colgroup>
				<caption><strong>회원가입</strong></caption>
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
						<td><input type="password" id="userPw" name="userPw" oninput="fnInputTextFiltering(this)"></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" id="checkUserPw" oninput="fnInputTextFiltering(this)">
							<span id="checkUserPwText" class="check">* 비밀번호를 입력하세요.</span>
						</td>
					</tr>
					<tr>
						<th>성명</th>
						<td><input type="text" id="userName" name="userName"></td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td><input type="text" id="preRrn" name="preRrn" maxlength="6"> - <input type="text" id="firstSufRrn" class="firstSufRrn" maxlength="1">******</td>
					</tr>
					<tr>
						<th>휴대전화</th>
						<td>
							<select id="mobileNo1">
								<option value="010" selected>010</option>
								<option value="010">011</option>
								<option value="010">016</option>
								<option value="010">017</option>
								<option value="010">019</option>
							</select> - 
							<input type="text" id="mobileNo2" class="mobileNo2" maxlength="4"> - <input type="text" id="mobileNo3" class="mobileNo3" maxlength="4">
						</td>
					</tr>
					<tr>
						<th>EMAIL</th>
						<td>
							<input type="text" id="email1" class="email1"> @ <input type="text" id="email2" class="email2">
							<select id="selectEmail" onchange="fnSelectChange()">
								<option value="직접입력" selected>직접입력</option>
								<option value="naver.com">naver.com</option>
								<option value="google.com">google.com</option>
								<option value="hanmail.net">hanmail.net</option>
								<option value="nate.com">nate.com</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="div_right">
			<input type="button" id="btn_signUp" class="btn_right" onclick="fnSignUpAction()" value="가입하기">
			<input type="button" id="btn_back" class="btn_right" value="돌아가기">
						
			
		</div>


		

	</form>


</body>
</html>