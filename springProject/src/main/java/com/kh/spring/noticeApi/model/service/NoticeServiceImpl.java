package com.kh.spring.noticeApi.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.spring.noticeApi.model.dao.NoticeMapper;
import com.kh.spring.noticeApi.model.vo.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final NoticeMapper noticeMapper;
	
	@Override
	public List<Notice> findAll() {
		return noticeMapper.findAll();
	}

	@Override
	public Notice findById(int noticeNo) {
		return noticeMapper.findById(noticeNo);
	}

	@Override
	public int save(Notice notice) {
		return noticeMapper.save(notice);
	}

	@Override
	public int update(Notice notice) {
		return 0;
	}

	@Override
	public int deleteById(int noticeNo) {
		return noticeMapper.deleteById(noticeNo);
	}

}
