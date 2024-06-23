package com.kh.spring.notice.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.notice.model.vo.Notice;

@Repository
public class NoticeRepository {
	
	public int noticeCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("noticeMapper.noticeCount");
	}
	
	/*
	public List<Notice> findAll(SqlSessionTemplate sqlSession) {
		return sqlSession.selectList("noticeMapper.findAll");
	}*/
	
	public List<Notice> findAll(SqlSessionTemplate sqlSession,RowBounds rowBounds) {
		return sqlSession.selectList("noticeMapper.findAll", null, rowBounds);
	}
	
	public int insert(SqlSessionTemplate sqlSession, Notice notice) {
		return sqlSession.insert("noticeMapper.insert", notice);
	}
	
}