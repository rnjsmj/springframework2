package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.repository.MemberRepository;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;


//Service : 비즈니스(도메인) 로직 작성
// 수행해야 하는 sql문 호출 (respository --> 하나의 메서드가 하나의 sql문을 수행하므로 필요한 메서드 호출)
// 다중 dml의 경우 transaction 필요 --> service에서 수행할 수 있도록 !!!
// =>  transaction 처리 위해서는 sqlSessionTemplate 객체를 service에서 선언해야 함.... => repository로 전달 
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final SqlSessionTemplate sqlSession;
	private final MemberRepository memberRepository;
	
	
	@Override
	public Member login(Member member) {
		
		//수행해야 하는 sql문 호출
		return memberRepository.login(sqlSession, member);
		
	}

	//단순 CRUD 작업
	// 1. DAO 호출 | [2. 트랜잭션 처리] | 3. 컨트롤러로 결과 반환
	@Override
	public int insert(Member member) {
		
		return memberRepository.insert(sqlSession, member);
	}

	@Override
	public int update(Member member) {
		return memberRepository.update(sqlSession, member);
	}

	@Override
	public int delete(String userId) {
		
		return memberRepository.delete(sqlSession, userId);
	}

	@Override
	public int idCheck(String checkId) {
		return memberRepository.idCheck(sqlSession, checkId);
	}

	@Override
	public int changePwd(Member member) {
		return memberRepository.changePwd(sqlSession, member);
	}
	
}
