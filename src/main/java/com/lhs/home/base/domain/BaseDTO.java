package com.lhs.home.base.domain;

import java.util.Map;

import com.lhs.home.utils.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 파일명 : BaseDTO.java
 * 기 능 : DTO 내에 중복 필드를 줄이기 위해
 * 		  모든 DTO는 BaseDTO를 상속받는다.
 * 		  @Builder 어노테이션은 상속이 불가하여 하위 클래스의 필드만 선언한다.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO {
	
	/* search 처리 */
	private String searchGbn; // 조회구분
	
	private String searchGbn2; // 조회구분2
	
	private String searchGbn3; // 조회구분3
	
	private String searchQuery; // 검색쿼리
	
	
	
	/* paging 처리 */
	private Integer totalCount;
	
	private Integer rowPerPage;
	
	private Integer totalPage;
	
	private Integer pagePerPage;
	
	private Integer startNo;
	
	private Integer endNo;
	
	private Integer currPage;
	
	
	
	/* 자율 parameter Map */
	private Map<String, Object> paramMap;
	
	public void addParamMap(String key, Object object) {
		
		if(StringUtils.isNull(key) || StringUtils.isEmpty(key)) {
			return;
		}
		
		paramMap.put(key, object);
	}
	
	public Object getParamMap(String key) {
		
		if(StringUtils.isNull(key) || StringUtils.isEmpty(key)) {
			return "";
		}
		
		return paramMap.get(key);
	}
	
}
