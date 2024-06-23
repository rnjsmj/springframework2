package com.kh.spring.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class PageInfo {
	private int listCount; // 게시글 총 개수 => 유동적이기때문에 BOARD 테이블로부터 SELECT COUNT(*) 활용하여 조회
	private int currentPage; // 사용자가 요청한 현재 페이지 => GET 요청을 통해 넘긴 매개변수를 통해 받음
	private int pageLimit; // 페이징 버튼의 개수 => 10개로 고정
	private int boardLimit; //한 페이지에 보여줄 게시글 개수 => 10개로 고정
	
	private int maxPage; //가장 마지막 페이지의 숫자 (총 페이지 개수)
	private int startPage; //페이징 바에서 시작하는 숫자
	private int endPage; //페이징 바에서 마지막 숫자
	
	//해당 필드를 클래스로 선언했을 때와 Map을 사용했을 때의 장점
	//Map을 사용하는 경우 : Key와 Value로 이루어져있기 때문에 무슨 값인지 알 수 있음
	
}