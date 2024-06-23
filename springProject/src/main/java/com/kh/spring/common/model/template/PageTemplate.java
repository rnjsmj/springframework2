package com.kh.spring.common.model.template;

import com.kh.spring.common.model.vo.PageInfo;

public class PageTemplate {
	
	public static PageInfo getPageInfo(int listCount, int currentPage, int pageLimit, int boardLimit) {
		// 페이징마다 사용해야 하는 연산식
		// 중복코드 발생을 방지하기위해 템플릿 클래스를 만들어서 사용
		
		//listCount : 게시글 총 개수 => 유동적이기때문에 BOARD 테이블로부터 SELECT COUNT(*) 활용하여 조회
		//currentPage: 사용자가 요청한 현재 페이지 => GET 요청을 통해 넘긴 매개변수를 통해 받음
		//pageLimit : 페이징 버튼의 개수 => 10개로 고정
		//boardLimit : 한 페이지에 보여줄 게시글 개수 => 10개로 고정
		
		int maxPage = (int) Math.ceil((double) listCount / boardLimit);
		int startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		if(endPage > maxPage) endPage = maxPage;
		
		
		return PageInfo.builder().listCount(listCount)
							 	 .currentPage(currentPage)
								 .boardLimit(boardLimit)
								 .pageLimit(pageLimit)
								 .maxPage(maxPage)
								 .startPage(startPage)
								 .endPage(endPage)
								 .build();
	}
}
