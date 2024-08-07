package com.kh.spring.member.model.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

//프로그램 외부와 입출력하는 역할을 수행하는 클래스 = DAO = Repository
//실제로 만든 클래스는 빈으로 등록할 때 annotation으로 등록
// Repository : 저장소
// 영속성 작업 : DB CRUD 작업 (다른 작업이 들어가서는 안되며, CRUD 작업을 수행하는 SQL 구문만 실행해야 함)
// 기존 java에서 메모리에 등록 => ram에 등록됨 (휘발성 메모리) => 프로그램이 종료되면 메모리에서 사라짐 => 이것을 방지하기 위해 영속성 작업을 수행
@Repository
public class MemberRepository {
	
	//로그인
	public Member login(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.selectOne("memberMapper.login", member);
	}

	public int insert(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.insert("memberMapper.insert", member);
	}

	public int update(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.update("memberMapper.update", member);
	}

	public int delete(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.update("memberMapper.delete", userId);
	}

	public int idCheck(SqlSessionTemplate sqlSession, String checkId) {
		return sqlSession.selectOne("memberMapper.idCheck", checkId);
	}

	public int changePwd(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.update("memberMapper.changePwd", member);
	}
	
}
