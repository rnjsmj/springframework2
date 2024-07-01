package com.kh.spring.test.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.test.model.service.EmailService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/emailCheck")
@RequiredArgsConstructor
@Controller
public class CheckEmail {
	private final EmailService emailService;
	
	@GetMapping
	public void checkEmail(String email, HttpServletResponse response) {
		
		String result= emailService.checkEmail(email);
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter resp = response.getWriter();
		
		try {
		if(result != null) {

			resp.print("중복된 이메일이 존재합니다.");

		} else {

			resp.print("사용가능한 이메일입니다.");

		}

	} catch(Exception e) {

			e.printStackTrace();
			resp.print("조회하는 도중 오류가 발생했습니다.");

	}
}
}
