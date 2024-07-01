package com.kh.spring.board.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;

@Repository
public class BoardRepository {

	public int boardCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("boardMapper.boardCount");
	}

	public List<Board> findAll(SqlSessionTemplate sqlSession, Map<String, Integer> map) {
		return sqlSession.selectList("boardMapper.findAll", map);
	}

	public int searchCount(SqlSessionTemplate sqlSession, Map<String, String> map) {
		return sqlSession.selectOne("boardMapper.searchCount", map);
	}

	public List<Board> findByConditionAndKeyword(SqlSessionTemplate sqlSession, Map<String, String> map, RowBounds rowBounds) {
		
		return sqlSession.selectList("boardMapper.findByConditionAndKeyword", map, rowBounds);
	}

	public int insert(SqlSessionTemplate sqlSession, Board board) {
		return sqlSession.insert("boardMapper.insert", board);
	}
	
	public int increaseCount(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	}

	public Board findById(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.findById", boardNo);
	}

	public int deleteById(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.update("boardMapper.deleteById", boardNo);
		
	}

	public int updateByBoard(SqlSessionTemplate sqlSession, Board board) {
		return sqlSession.update("boardMapper.updateByBoard", board);
	}
	
	public List<Board> selectImages(SqlSessionTemplate sqlSession) {
		return sqlSession.selectList("boardMapper.selectImages");
	}

	public List<Reply> selectReply(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectList("boardMapper.selectReply", boardNo);
	}

	public int insertReply(SqlSessionTemplate sqlSession, Reply reply) {
		return sqlSession.insert("boardMapper.insertReply", reply);
	}

	public Board boardAndReply(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.boardAndReply", boardNo);
	}

	public List<Board> findTopBoard(SqlSessionTemplate sqlSession) {
		return sqlSession.selectList("boardMapper.findTopBoard");
	}

}
