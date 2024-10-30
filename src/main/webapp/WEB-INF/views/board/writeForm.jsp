<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	
	var gl_validate = false;
	function fnWriteSubmit() {
		
		fnBoardValidate();
		if(!gl_validate) {
			return;
		}
		
		$('#boardCode').val(1001); // 1001 공지사항
		$('#writeForm').attr('action', '/board/insertBoard.do');
		$('#writeForm').submit();
	}
	
	function fnBoardValidate() {
		console.log($('#title').val());
		if($('#title').val().length < 5) {
			gfn_alert('제목은 5글자 이상 입력해주세요.');
			gl_validate = false;
			return;
		}
		
		if($('#content').val().length < 10) {
			gfn_alert('내용은 10글자 이상 입력해주세요.');
			gl_validate = false;
			return;
		}
		
		gl_validate = true;
	}
	
	function fnFileCheck(obj) {
		
		/* 첨부 규칙 */
		var lc_regExt = /\.(jpg|jpeg|png|gif|pdf|doc|docx|xls|xlsx|txt|zip)$/; // 확장자
		var lc_maxSize = 1024 * 1024; // 최대크기(1MB)
		
		var files = obj.files;
		
		if(!lc_regExt.test(obj.value)) {
			gfn_alert('첨부파일의 확장자는\n'
					+ 'jpg, jpeg, png, gif, pdf, doc, docx, xls, xlsx, txt, zip\n'
					+ '파일만 가능합니다.');
			obj.value = '';
			return;
		}
		
		if(obj.size > lc_maxSize) {
			gfn_alert('파일 크기는 1MB를 넘을 수 없습니다.');
			obj.value = '';
			return;
		}
	}
	
</script>
</head>
<body>
	<div>
		<form id="writeForm" method="post" enctype="multipart/form-data">
			<input type="hidden" id="boardCode" name="boardCode">
			
			<table class="formTable">
				<colgroup>
					<col width="15%"/>
					<col width="85%"/>
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td><input type="text" id="title" name="title" class="txt_title"></td>
					</tr>
					<tr class="tr_content">
						<th>내용</th>
						<td>
							<textarea id="content" name="content" class="txt_content"></textarea>
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<input type="file" id="file1" name="file1" onchange="fnFileCheck(this)">
						</td>
					</tr>
					<tr>
						<th>첨부파일2</th>
						<td>
							<input type="file" id="file2" name="file2" onchange="fnFileCheck(this)">
						</td>
					</tr>
					<tr>
						<th>첨부파일3</th>
						<td>
							<input type="file" id="file3" name="file3" onchange="fnFileCheck(this)">
						</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><span>${sessionScope.userDTO.userName}</span></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	
	<div class="div_right">
		<input type="button" id="btn_write" class="btn_right" onclick="fnWriteSubmit()" value="등록">
		<input type="button" class="btn_right" value="목록">
	</div>
		
	

</body>
</html>