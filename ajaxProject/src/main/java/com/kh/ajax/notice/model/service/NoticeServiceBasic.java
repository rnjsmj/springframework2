package com.kh.spring.notice.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;


import com.kh.spring.notice.model.vo.Notice;

public interface NoticeService {
	
	
	//전체 목록 조회
	//공지사항 전체 개수
	int noticeCount();
//	List<Notice> findAll();
	List<Notice> findAll(RowBounds rowBounds);
	
	//공지 작성
	int insert(Notice notice);
	
	//상세조회
	Notice findById(int noticeNo);
	
	//수정
	int updateById(Notice notice);
	
	//삭제
	int deleteById(int noticeNo);
}
