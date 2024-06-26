package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.template.PageTemplate;
import com.kh.spring.common.model.vo.PageInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService boardService;

	
	@GetMapping("boardlist")
	public String forwarding(@RequestParam(value="page", defaultValue="1") int page, Model model) {
		
		// -- 페이징 처리 -- 
		// RowBounds 사용
		
		
		
		// RowBounds 사용 X
		int listCount; // 게시글 총 개수 => 유동적이기때문에 BOARD 테이블로부터 SELECT COUNT(*) 활용하여 조회
		int currentPage; // 사용자가 요청한 현재 페이지 => GET 요청을 통해 넘긴 매개변수를 통해 받음
		int pageLimit; // 페이징 버튼의 개수 => 10개로 고정
		int boardLimit; //한 페이지에 보여줄 게시글 개수 => 10개로 고정
		
		int maxPage; //가장 마지막 페이지의 숫자 (총 페이지 개수)
		int startPage; //페이징 바에서 시작하는 숫자
		int endPage; //페이징 바에서 마지막 숫자
		
		// * listCount
		listCount = boardService.boardCount();
		
		// * currentPage
		currentPage = page;
											//log.info("게시글 개수 : {}, 요청 페이지 : {}", listCount, currentPage);
		// * pageLimit, boardLimit
		pageLimit = 10;
		boardLimit = 10;
		
		/*
		// * maxPage
		// => listCount / pageList 결과의 소숫점을 올림처리
		// int를 double로 변환하여 연산 => Math.ceil()을 수행한 double 값을 int로 다시 형변환
		maxPage = (int) Math.ceil((double) listCount / boardLimit);
		
		// * startPage
		// startPage = n * pageLimit + 1
		// n = (currentPage - 1) / pageLimit
		// => startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1
		startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;
		
		// * endPage
		// endPage = startPage + pageLimit - 1; 단, maxPage의 영향을 받음
		// => endPage가 maxPage보다 클 수 있으므로 endPage는 maxPage가 됨
		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) endPage = maxPage;
		
		//클래스 생성자 이용
		//@AllArgsConstructor만 사용하는 경우
//		PageInfo pageInfo = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		//@AllArgsConstructor + @Builder를 사용하는 경우 ********** 디자인패턴의 일종
		PageInfo pageInfo = PageInfo.builder()
									.listCount(listCount)
									.currentPage(currentPage)
									.boardLimit(boardLimit)
									.pageLimit(pageLimit)
									.maxPage(maxPage)
									.startPage(startPage)
									.endPage(endPage)
									.build(); */
		
		PageInfo pageInfo = PageTemplate.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		//디자인패턴 종류 : *MVC, 싱글톤, 빌더*, 템플릿, 템플릿메소드, Strategy
		
		// 시작값 = (currentPage - 1) * boardLimit + 1;
		// 끝값 = 시작값 + boardLimit = 1;
		
		Map<String, Integer> map = new HashMap();
		
		int startValue = (currentPage -1 ) * boardLimit + 1;
		int endValue = startValue + boardLimit -1;
		
		map.put("startValue", startValue);
		map.put("endValue", endValue);
		
		List<Board> boardList = boardService.findAll(map);
//		log.info("조회된 게시글 개수 : {}", boardList.size());
//		log.info("===================================");
//		log.info("조회된 게시글 목록 : {}", boardList);
		
		model.addAttribute("list", boardList);
		model.addAttribute("pageInfo", pageInfo);
		
		
		return "board/list";
	}
	
	
	@GetMapping("search")
	public String search(String condition, String keyword, @RequestParam(value="page", defaultValue="1") int page, Model model) {
		
		//컨트롤러의 값 활용 => 경우의 수
		
		// 사용자가 선택한 키워드와 조건을 통해 페이징 처리를 끝낸 수 검색결과를 들고가야함
		Map<String, String> map = new HashMap();
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		int searchCount = boardService.searchCount(map);
		int currentPage = page;
		int pageLimit = 3;
		int boardLimit = 3;
		
		/*
		int maxPage = (int) Math.ceil((double) searchCount / boardLimit);
		int startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = PageInfo.builder()
				.listCount(searchCount)
				.currentPage(currentPage)
				.boardLimit(boardLimit)
				.pageLimit(pageLimit)
				.maxPage(maxPage)
				.startPage(startPage)
				.endPage(endPage)
				.build(); */
		
		PageInfo pageInfo = PageTemplate.getPageInfo(searchCount, currentPage, pageLimit, boardLimit);
	
		
		
		//offset : 전체 조회 결과 중 몇개를 건너뛰고 조회할 것인지
		//limit : 총 몇개를 조회할 것인지
		// => 적은 데이터를 사용하는 경우에 좋음 (offset을 많이 할 수록 성능저하)
		
		//RowBounds : MyBatis에서 제공하는 클래스
		/* (currentPage() - 1) * boardLimit()
		 */
		RowBounds rowBounds = new RowBounds((currentPage - 1) * boardLimit, boardLimit);
		
		List<Board> boardList = boardService.findByConditionAndKeyword(map, rowBounds);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("list", boardList);
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("condition", condition);
		
		return "board/list";
	}
	
	
	@GetMapping("boardForm")
	public String boardFormForwarding() {
		return "board/insertForm";
	}
	
	
	@PostMapping("boardInsert")
	public String boardInsert(Board board, MultipartFile upfile, HttpSession session, Model model) { //MultipartFile[] 여러 개의 파일이 배열로 한 번에 들어옴
		//input type=file 요소의 name속성과 매개변수명이 같아야함 (upfile)
		//커맨드 객체가 매개변수로 들어오므로, 파일을 첨부하지 않아도 MultipartFile 객체는 반드시 생성됨
		// => fileName 필드에 원본명이 존재하는지 / 없는지
		// 전달된 파일이 존재할 경우 => 파일 업로드
		
		if(!upfile.getOriginalFilename().equals("")) {
			/*
			//uploadFiles 디렉토리에 파일 업로드
			//이름 변경 :  KH_년월일시분초_랜덤값.확장자
			String originName = upfile.getOriginalFilename();
			String ext = originName.substring(originName.lastIndexOf("."));
			//랜덤값
			//Math.random(); >> 0.0 ~ 0.999999999999 의 값
			int num = (int) (Math.random() * 900) + 100; // 100 위치 : 범위, 1 위치 : 시작값
			//년월일시분초 
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			//업로드 경로 : 프로그램 전역에서 접근할 수 있는 Application 객체를 사용
			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");	
			
			String changeName = "KH_" + currentTime + "_" + num + ext;
			
			//파일 업로드
			try {
				upfile.transferTo(new File(savePath + changeName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			
			
			
			//Board 객체에 originName + changeName 값 저장 (첨부파일이 존재한다는 조건일 경우의 코드이므로 값 지정해줘야 함!)
			board.setOriginName(upfile.getOriginalFilename());
			board.setChangeName(saveFile(upfile, session));
		} 
		
		
		if (boardService.insert(board)>0) {
			session.setAttribute("alertMsg", "게시글이 등록되었습니다.");
			//리다이렉트 -> 반드시 db에 거쳐서 게시글 목록을 조회해야 하기 때문에 redirect를 수행해야 함
			return "redirect:/boardlist";
			
		} else {
			
			model.addAttribute("errorMsg", "게시글 등록 중 오류가 발생했습니다.");
			return "common/errorMsg";
		}

	}
	
	
	
	// 상세보기
	@GetMapping("board-detail")
	public ModelAndView findById(@RequestParam("boardNo") int boardNo, ModelAndView mv) {
		//ModelAndView의 장점..???
		
		//reqeust로 넘어온 파라미터는 String 값인데 int로 받을 수 있는 이유?
		//String은 참조형이므로 기본형인 int로 변환할 수 없음
		//==> Integer.parseInt() 를 통해서 parsing 수행 ! 
		//Object obj = 123;
		//Object는 참조형이므로 obj의 값은 주소가 들어가야 함
		// int형 데이터가 대입되려면 참조형(Integer)으로 포장되어야 함 : auto boxing
		
		// 서비스 호출
		
		if(boardService.increaseCount(boardNo)>0) {
			//응답화면 지정
			mv.addObject("board", boardService.findById(boardNo)).setViewName("board/detail");
			
		} else {
			
			mv.addObject("errorMsg", "게시글 상세조회에 실패했습니다.").setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	
	// 삭제
	/**
	 * deleteById : Client(게시글 작성자)에게 정수형의 boardNo를 전달받아 BOARD 테이블에 존재하는 STATUS 컬럼의 값을 'N'으로 갱신
	 * 
	 * @param boardNo : 각 행을 식별하기 위한 PK
	 * @param filePath : 요청 처리 성공 시 첨부파일을 제거하기 위한 파일이 저장되어있는 경로 및 파일명 
	 * 
	 * @return : 반환된 View의 논리적인 경로
	 */
	@PostMapping("boardDelete")
	public String deleteById(int boardNo, String filePath, Model model, HttpSession session) {
		if(boardService.deleteById(boardNo)>0) {
			
			if(!"".equals(filePath)) {
				// filePath 매개변수가 post로 넘어온 key값과 매핑되지 않으면 equals() 메서드를 사용하는 경우
				// NullPointerException이 발생할 수 있음
				// => 빈 문자열 리터럴을 기준으로 equals() 메서드를 사용하면 이와 같은 예외 발생 방지
				
				new File(session.getServletContext().getRealPath(filePath)).delete();
			}
			
			session.setAttribute("alertMsg", "게시글이 삭제되었습니다.");
			return "redirect:/boardlist";
			
			
		} else {
			model.addAttribute("errorMsg", "게시글 삭제 실패");
			return "common/errorPage";
		}
		
	}
	
	@PostMapping("boardUpdateForm")
	public ModelAndView boardUpdateForm(int boardNo, ModelAndView mv) {
		
		mv.addObject("board", boardService.findById(boardNo)).setViewName("board/updateForm");
		return mv;
		
	}
	
	@PostMapping("boardUpdate")
	public String updateById(Board board, MultipartFile reupFile, HttpSession session,Model model) {
		
		// 새로운 첨부파일이 발생한 경우 새로운 첨부파일 정보로 수정해야 함
		if (!reupFile.getOriginalFilename().equals("")) {
			new File(session.getServletContext().getRealPath(board.getChangeName())).delete();
			board.setOriginName(reupFile.getOriginalFilename());
			board.setChangeName(saveFile(reupFile, session));
		}
		
		if(boardService.updateByBoard(board) > 0) {
			
			session.setAttribute("alertMsg", "게시글이 수정되었습니다.");
			return "redirect:/board-detail?boardNo=" + board.getBoardNo();
			
		} else {
			
			model.addAttribute("errorMsg", "게시글 수정 중 오류가 발생하였습니다.");
			return "common/errorPage";
		}
		
	}
	
	
	
	
	
	
	
	// 파일 업로드 메서드
	public String saveFile(MultipartFile upfile, HttpSession session) {
		//uploadFiles 디렉토리에 파일 업로드
		//이름 변경 :  KH_년월일시분초_랜덤값.확장자
		String originName = upfile.getOriginalFilename();
		String ext = originName.substring(originName.lastIndexOf("."));
		//랜덤값
		//Math.random(); >> 0.0 ~ 0.999999999999 의 값
		int num = (int) (Math.random() * 900) + 100; // 100 위치 : 범위, 1 위치 : 시작값
		//년월일시분초 
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		//업로드 경로 : 프로그램 전역에서 접근할 수 있는 Application 객체를 사용
		String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");	
		
		String changeName = "KH_" + currentTime + "_" + num + ext;
		
		//파일 업로드
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "resources/uploadFiles/" + changeName;
	}
	
	
	@GetMapping("image-board")
	public String imgaes(Model model) {
		
		List<Board> images = boardService.selectImages();
		model.addAttribute("board", images);
		
		return "board/imageList";
	}
	
	
	@ResponseBody
	@GetMapping(value="reply", produces="application/json; charset=UTF-8")
	public String selectReply(int boardNo) {
		String.valueOf(1.1);
		return new Gson().toJson(boardService.selectReply(boardNo));
		
		
		
	}
	
	
	@ResponseBody
	@PostMapping("reply")
	public String saveReply(Reply reply) {
		
		return boardService.insertReply(reply) > 0 ? "success" : "fail";
	}
	
	@ResponseBody
	@GetMapping("board-reply")
	public Board boardAndReply(int boardNo) {
		
		return boardService.boardAndReply(boardNo);
	}
	
	
	@GetMapping("var")
	public String varFoward() {
		return "common/variable";
	}
}
