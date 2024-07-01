package com.kh.spring.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.noticeApi.model.vo.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// /boards로 시작하는 요청이 들어오면 처리! 
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/boards", produces="application/json; charset=UTF-8")
public class BoardsController {
	
	
	/* Notice Ajax Controller 정의
	 * 
	 * Mapping값 notice로 통일
	 * C: INSERT => @PostMapping
	 * R : SELECT =>  @GetMapping
	 * U : UPDATE => @PutMapping
	 * D : DELETE => @DeleteMapping
	 * 
	 * 상세조회 : @GetMapping + PathVariable (PK)
	 *
	 *=> REST 방식 : 
	 */

	private final BoardService boardService;
	
	@GetMapping
	public List<Board> findTopFiveBoard() {
		
		return boardService.findTopFiveBoard();
		
	}
	
	
	@GetMapping("/{boardNo}")
	public Board findByBoardNo(@PathVariable int boardNo) {
		//log.info("pk : {}", boardNo);
		
		return boardService.findById(boardNo);
		
		
	}
	
	@DeleteMapping("/{boardNo}")
	public ResponseEntity<Message> deleteBoards(@PathVariable int boardNo) {
		
		int result = boardService.deleteById(boardNo);
		
		if(result == 0) {
			return ResponseEntity.status(HttpStatus.OK).body(Message.builder()
																	.message("게시물이 존재하지 않습니다.")
																	.build());	
		}
		
		Message responseMsg = Message.builder().data("삭제 성공")
											   .message("서비스 처리 성공")
											   .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMsg);
	}
	
}
