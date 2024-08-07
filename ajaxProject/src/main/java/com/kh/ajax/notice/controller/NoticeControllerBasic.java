package com.kh.spring.notice.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.common.model.template.PageTemplate;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.member.model.vo.Member;
import com.kh.spring.notice.model.service.NoticeService;
import com.kh.spring.notice.model.vo.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {
		
	private final NoticeService noticeService;
	
	/* 기본 전체 목록 조회
	@GetMapping("noticelist")
	public String forwarding(Model model) {
		
		List<Notice> noticeList = noticeService.findAll();
		model.addAttribute("list", noticeList);
		
		log.info("공지사항 행 개수 : {}", noticeList.size());
		log.info("공지사항 : {}", noticeList);
		
		return "notice/list";
	}*/
	
	//페이징 기능 추가
	@GetMapping("noticelist")
	public String forwarding(@RequestParam(value="page", defaultValue="1") int page, Model model) {
		
		//pageInfo 클래스 객체 생성
		int listCount = noticeService.noticeCount();
		int currentPage = page;
		int pageLimit = 5;
		int boardLimit = 5;
		//생성된 PageInfo 객체를 통해 현재 보여줄 레코드와, 최대 페이지를 지정하여 화면에 출력할 수 있음
		PageInfo pi = PageTemplate.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		//RowBounds 객체 생성 (offset, limit)
		RowBounds rowBounds = new RowBounds((currentPage - 1) * boardLimit , boardLimit);
		List<Notice> noticeList = noticeService.findAll(rowBounds);
		
		model.addAttribute("pageInfo", pi);
		model.addAttribute("list", noticeList);
		
		
		return "notice/list";
	}
	
	
	// 글 작성 페이지 이동
	@GetMapping("noticeForm")
	public String noticeForm(HttpSession session) {
		
		if(session.getAttribute("loginUser") == null || !((Member) session.getAttribute("loginUser")).getUserId().equals("admin")) {
			return "redirect:/";
		}
		
		return "notice/insertForm";
	}
	
	
	
	//공지사항 insert
	@PostMapping("noticeInsert")
	public String noticeInsert(Notice notice, HttpSession session, Model model) {
		
		if(noticeService.insert(notice) > 0) {
			session.setAttribute("alertMsg", "공지사항이 등록되었습니다.");
			return "redirect:/noticelist";
		} else {
			model.addAttribute("errorMsg", "공지사항 들록 중 오류가 발생했습니다.");
			return "common/errorPage";
		}
		
	}
	
	//공지사항 상세보기
	@GetMapping("noticeDetail")
	public String noticeDetail(@RequestParam("noticeNo") int noticeNo, Model model) {
		
		model.addAttribute("notice", noticeService.findById(noticeNo));
		return "notice/detail";
	}
	
	//공지사항 수정 페이지로 이동
	@PostMapping("noticeUpdateForm")
	public String noticeUpdateForm(int noticeNo, Model model, HttpSession session) {
		
//		if( session.getAttribute("loginUser") == null || ((Member) session.getAttribute("loginUser")).getUserId() != "admin") {
//			return "redirect:/";
//		} 
		
		model.addAttribute("notice", noticeService.findById(noticeNo));
		return "notice/updateForm";
	}
	
	
	//공지사항 수정
	@PostMapping("noticeUpdate")
	public String noticeUpdate(Notice notice, Model model, HttpSession session) {
		
		if(noticeService.updateById(notice)>0) {
			session.setAttribute("alertMsg", "공지사항이 수정되었습니다.");
			return "redirect:/noticeDetail?noticeNo=" + notice.getNoticeNo();
		} else {
			model.addAttribute("errorMsg", "공지사항 수정 중 오류가 발생했습니다.");
			return "common/errorPage";
		}
	}
	
	
	//공지사항 삭제
	@PostMapping("noticeDelete")
	public String noticeDelete(int noticeNo, Model model, HttpSession session) {
		
//		if( session.getAttribute("loginUser") == null || ((Member) session.getAttribute("loginUser")).getUserId() != "admin") {
//			return "redirect:/";
//		}
		
		if(noticeService.deleteById(noticeNo)>0) {
			session.setAttribute("alertMsg", "공지사항이 삭제되었습니다.");
			return "redirect:/noticelist";
		} else {
			model.addAttribute("errorMsg", "공지사항 삭제 중 오류가 발생했습니다.");
			return "common/errorPage";
		}
		
	}
}
