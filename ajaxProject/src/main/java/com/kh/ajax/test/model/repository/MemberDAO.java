package com.kh.spring.test.model.repository;

import org.mybatis.spring.SqlSessionTemplate;

public class MemberDAO {
	public String isEmailExist(SqlSessionTemplate sqlSession, String email) {

		return sqlSession.selectOne("emailMapper.isEmailExist", email);

	}
}
