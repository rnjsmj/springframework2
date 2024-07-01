package com.kh.spring.board.model.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String originName; //파일 원본명
	private String changeName; //파일 경로 + 변경명
	private int count;
	private String createDate;
	private String status;
	private List<Reply> replyList;
}
