package com.lhs.home.board.domain;

import com.lhs.home.base.domain.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BoardDTO extends BaseDTO{
	
	public Integer boardCode; // 게시판코드
	
	public Integer boardNo; // 게시글번호
	
	public Integer userNo; // 고객번호
	
	public String title; // 제목
	
	public String content; // 내용
	
	public Integer fileNo; // 파일번호
	
	public Integer hit; // 조회수
	
	public String delYn; // 삭제여부
	
	public String delCntn; // 삭제사유
	
	public String registDt; // 등록일자
	
	public String updtDt; // 수정일자
	
	public Integer updtUserNo; // 수정고객번호
	
	public String userName; // 고객명
	
	

}
