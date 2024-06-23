package com.kh.spring.member.model.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {
	//Service Interface : 메서드의 사용법을 작성해놔야 구현 시 사용하기 좋음 --> 기획 내용!!!!
	//DB 로직을 구상하여 반환값과 매개변수 값을 지정하는 작업
	
	//로그인 (SELECT)
	Member login(Member member);
	
	//회원가입
	int insert(Member member);
	
	//회원정보 수정
	int update(Member membeR);
	
	//회원 탈퇴
	int delete(String userId);
	
	//아이디 중복 체크
	
	//메일 인증
}
