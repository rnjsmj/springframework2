package com.kh.spring.noticeApi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.noticeApi.model.service.NoticeService;
import com.kh.spring.noticeApi.model.vo.Message;
import com.kh.spring.noticeApi.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
	//localhost/spring/notice => 요청 타입에 따라 CRUD 동작 달라짐
	//Get/Post/Put/Delete

	private final NoticeService noticeService;
	
	//전체 목록 조회
	@GetMapping
	public ResponseEntity<Message> findAll() {
		
		//ResponseEntity<Object> : 응답 개체
		//List를 반환하는 경우, List에 포함된 데이터가 없는 경우 어떤 상황인지 알 수 없음
		//상황에 따라 다르게 결과를 알려주기 위해 사용
		
		List<Notice> noticeList = noticeService.findAll();
//		log.info("조회된 공지사항 : {}", noticeList);
		
		if(noticeList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(Message.builder()
										 	  .message("조회결과가 존재하지 않습니다.")
										 	  .build());
		}
		
		Message responseMsg = Message.builder()
									 .data(noticeList)
									 .message("조회 요청 성공했습니다.")
									 .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMsg);
	}
	
	//쿼리스트링으로 파라미터 값을 넘기는 경우 목록조회와 매핑값 같아질 수 있음
	//PathVariable 사용
	@GetMapping("/{id}")
	public ResponseEntity<Message> findById(@PathVariable int id) {
		
		Notice notice = noticeService.findById(id);
		
		if(notice == null) {
			return ResponseEntity.status(HttpStatus.OK)
					             .body(Message.builder()
					            		 	  .message("조회결과가 존재하지 않습니다.")
					            		 	  .build());
		}
		
		Message responseMsg = Message.builder()
									 .message("조회 요청에 성공했습니다.")
									 .data(notice)
									 .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMsg);
		
	}
	
	
	@PostMapping
	public ResponseEntity<Message> save(Notice notice) {
		
		if("".equals(notice.getNoticeTitle()) || "".equals(notice.getNoticeContent()) || "".equals(notice.getNoticeWriter())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder()
																			 .message("서비스 요청실패")
																			 .data("필수 파라미터가 누락되었습니다.")
																			 .build());
		}
		
		int result = noticeService.save(notice);
		
		if(result == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.builder()
																			 .message("글 등록 실패")
																			 .build());
		}
		Message responseMsg = Message.builder().data("글 추가 성공")
											   .message("서비스 요청 성공")
											   .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(responseMsg);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Message> delete(@PathVariable int id) {
		
		int result = noticeService.deleteById(id);
		
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
