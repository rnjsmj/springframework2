package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;

public interface BoardService {
	
	//===== 게시글 =====
	
	// 게시글 전체 조회 + 페이징
	// 현재 Board 테이블의 총 행 개수
	int boardCount();
	
	
	// 게시글 목록 조회
	List<Board> findAll(Map<String, Integer> map);
	
	// 게시글 검색 결과 개수 조회
	int searchCount(Map<String, String> map);
	
	//게시글 검색 목록 조회
	List<Board> findByConditionAndKeyword(Map<String, String> map, RowBounds rowBounds);
	
	// 게시글 작성
	int insert(Board board);
	
	// 게시글 상세보기
	// 조회수 증가 : 성공할 수도, 실패할 수도 있음 ==> 상세조회보다 우선적으로 실행되어야 함
	int increaseCount(int boardNo);
	
	//게시글 상세 조회
	Board findById(int boardNo);
	
	// 게시글 삭제
	int deleteById(int boardNo);
	
	// 게시글 수정
	int updateByBoard(Board board);
	
	
	// 이미지 게시판 목록 조회
	List<Board> selectImages();


	
	
	//===== 댓글 =====
	
	// 1. AJAX를 활용한 댓글 목록 조회 => 2. MyBatis를 이용한 댓글 조회
	List<Reply> selectReply(int boardNo);

	// 댓글 작성
	int insertReply(Reply reply);

	// 하나의 Board에 달린 댓글과 함께 조회
	Board boardAndReply(int boardNo);


	
	// ===== Top-M =====
	List<Board> findTopFiveBoard();
}
