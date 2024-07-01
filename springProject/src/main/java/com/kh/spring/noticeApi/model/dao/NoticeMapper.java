package com.kh.spring.noticeApi.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.noticeApi.model.vo.Notice;

@Mapper
public interface NoticeMapper {

	
	// Mapper Interface? 
	// 기존 repository는 sqlSession을 호출하여 매퍼와 실행할 sql 구문 선택
	// @Mapper Mapper Interface는 이 과정을 MyBatis가 대신 함
	// => sql 구문의 id를 method 명으로 작성 => sqlSession을 자동으로 넘겨줌 
	// MyBatis 3 버전 이상에서만 가능
	
	List<Notice> findAll();
	
	Notice findById(int noticeNo);
	
	int save(Notice notice);
	
	int update(Notice notice);
	
	int deleteById(int noticeNo);
}
