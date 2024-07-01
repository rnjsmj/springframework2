package com.kh.spring.test.model.service;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.spring.test.model.repository.MemberDAO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailService {
	
	private final MemberDAO memberDAO;
	
	private final SqlSessionTemplate sqlSession;

	public String checkEmail(String email) {
		return memberDAO.isEmailExist(sqlSession, email);
	}
	
}
