package com.kh.spring.notice.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.spring.notice.model.repository.NoticeRepository;
import com.kh.spring.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServcieImpl implements NoticeService {

	private final NoticeRepository noticeRepository;
	private final SqlSessionTemplate sqlSession;
	
	@Override
	public int noticeCount() {
		return noticeRepository.noticeCount(sqlSession);
	}
	
	/*
	@Override
	public List<Notice> findAll() {
		return noticeRepository.findAll(sqlSession);
	}*/
	
	@Override
	public List<Notice> findAll(RowBounds rowBounds) {
		return noticeRepository.findAll(sqlSession, rowBounds);
	}

	@Override
	public int insert(Notice notice) {
		return noticeRepository.insert(sqlSession, notice);
	}

	@Override
	public Notice findById(int noticeNo) {
		return noticeRepository.findById(sqlSession, noticeNo);
	}

	@Override
	public int updateById(Notice notice) {
		return noticeRepository.updateById(sqlSession, notice);
	}

	@Override
	public int deleteById(int noticeNo) {
		return noticeRepository.deleteById(sqlSession, noticeNo);
	}

	
}
