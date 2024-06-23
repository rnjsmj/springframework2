package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.repository.BoardRepository;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.model.vo.PageInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository boardRepository;
	private final SqlSessionTemplate sqlSession;
	

	@Override
	public int boardCount() {
		return boardRepository.boardCount(sqlSession);
	}

	@Override
	public List<Board> findAll(Map<String, Integer> map) {
		return boardRepository.findAll(sqlSession, map);
	}
	// 두번째 인자값으로 전달할 값이 없고, RowBounds 객체를 넘겨야 할 경우
	// selectList9)의 오버로딩된 형태중 매개변수가 3개인 메서드로 반드시 호출해야 함!!
	// 두 번째 인자값으로 전달할 값이 없으므로 null 값을 넘겨주면 됨

	@Override
	public int searchCount(Map<String, String> map) {
		return boardRepository.searchCount(sqlSession, map);
	}

	@Override
	public List<Board> findByConditionAndKeyword(Map<String, String> map, RowBounds rowBounds) {
		
		
		return boardRepository.findByConditionAndKeyword(sqlSession, map, rowBounds);
	}

	@Override
	public int insert(Board board) {
		return boardRepository.insert(sqlSession, board);
	}

	@Override
	public int increaseCount(int boardNo) {
		return 0;
	}

	@Override
	public Board findById(int boardNo) {
		return null;
	}

	@Override
	public int delete(int boardNo) {
		return 0;
	}

	@Override
	public int update(int boardNo) {
		return 0;
	}
	
	
	
}