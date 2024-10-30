package com.lhs.home.utils;

import com.lhs.home.base.domain.BaseDTO;

/**
 * 파일명 : PageUtils.java
 * 기 능 : 페이징 처리를 위한 Util 클래스.
 */
public class PageUtils {
	
	private static Integer totalCount;	// 총 row 개수
	
	private static Integer rowPerPage;	// page당 row 개수
	
	private static Integer totalPage;	// 총 page 개수
	
	private static Integer pagePerPage; // 화면 page 개수
	
	private static Integer currPage;	// 현재 page
	
	private static Integer startNo;		// 시작 번호
	
	private static Integer endNo;		// 마지막 번호
	
	private static Integer startPage;	// 시작 페이지
	
	private static Integer endPage;		// 마지막 페이지
	
	/**
	 * 메서드 : getPageInfo
	 * 기 능 : 모든 DTO 클래스의 상위 클래스인 BaseDTO의 페이징 관련 필드를 set 하여 return 한다.
	 * @param BaseDTO  : page 정보만 필요할 경우 BaseDTO를, 객체의 정보가 같이 필요할 경우 해당 DTO를 전달한다.
	 * @return BaseDTO : 전달받은 DTO 객체에 page 정보를 set 하여 return 한다.
	 */
	public static BaseDTO getPageInfo(BaseDTO baseDTO) {
		
		/* 기본 필드 set */
		totalCount = baseDTO.getTotalCount();
		rowPerPage = (StringUtils.isNull(baseDTO.getRowPerPage())) ? 10 : baseDTO.getRowPerPage();
		currPage = (StringUtils.isNull(baseDTO.getCurrPage())) ? 1 : baseDTO.getCurrPage();
		pagePerPage = (StringUtils.isNull(baseDTO.getPagePerPage())) ? 5 : baseDTO.getPagePerPage();
		
		/* 계산 필드 set */
		totalPage = getTotalPage(totalCount, rowPerPage);
		startNo = (currPage - 1) * rowPerPage + 1;
		endNo = startNo + rowPerPage - 1;
		startPage = ((currPage - 1) / pagePerPage) * pagePerPage + 1;
		endPage = startPage + pagePerPage - 1;
		
		/* return DTO set */
		baseDTO.setRowPerPage(rowPerPage);
		baseDTO.setCurrPage(currPage);
		baseDTO.setPagePerPage(pagePerPage);
		baseDTO.setTotalPage(totalPage);
		baseDTO.setStartNo(startNo);
		baseDTO.setEndNo(endNo);
		System.out.println(baseDTO.toString());
		return baseDTO;
	}
	
	public static int getTotalPage(int totalCount, int rowPerPage) {
		
		if(totalCount % rowPerPage > 0) {
			totalPage = (totalCount / rowPerPage) + 1;
		} else {
			totalPage = totalCount / rowPerPage;
		}
		
		return totalPage;
	
	}

}
