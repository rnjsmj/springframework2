package com.kh.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
	
	/*
	 * 1.HttpServletResponse 객체로 응답데이터를 응답하기 (Stream을 이용한 방식) 
	 */
	/*
	@GetMapping("ajax1")
	public void calSum(String menu, int amount, HttpServletResponse response) throws IOException {
		//amount input에 값을 입력하지 않은 경우 빈 문자열이 전송
		//=> 빈 문자열은 Integer.parseInt("")를 수행할 수 없으므로 NumberFormatException 발생
		
		
		 //System.out.println("사용자가 입력한 메뉴 : " + menu);
		 //System.out.println("사용자가 입력한 수량 : " + amount);
		 
		
		int price = 0;
		switch(menu) {
		case "알밥" : price = 10000; break;
		case "돈까스" : price = 12000; break;
		case "파스타" : price = 19000; break;
		case "햄버거" : price = 9000; break;
		case "떡볶이" : price = 14000; break;
		
		}
		
		price *= amount;
		
//		System.out.println(price);
		
		//데이터 형식 지정 => 출력 전에 지정해야 함 => 반환할 데이터의 형식을 의미
		response.setContentType("text/html; charset=UTF-8");
		//출력
		response.getWriter().print(price); //전송한 데이터를 success의 매개변수로 활용
	} */

	
	/*
	 * 2. 응답할 데이터를 문자열로 반환
	 * => HttpServletResponse를 사용하지 않는 방법
	 * => String 반환하면 포워딩 => 응답 뷰의 경로로 인식해서 View Resolver로 전달됨
	 * 
	 * 따라서 스프링을 사용하여 응답데이터를 반환하기 위해서는
	 * MessageConverter로 이동하게 해야 함 => @ResponseBody 애노테이션
	 * 
	 */
	//produces : 응답 하는 데이터 형식 지정 (인코딩 방식 지정 가능)
	@ResponseBody
	@GetMapping(value="ajax1", produces="text/html; charset=UTF-8")
	public String calSum(String menu, int amount) {
		int price = 0;
		
		switch(menu) {
		case "알밥" : price = 10000; break;
		case "돈까스" : price = 12000; break;
		case "파스타" : price = 19000; break;
		case "햄버거" : price = 9000; break;
		case "떡볶이" : price = 14000; break;
		}
		price *= amount;
		
		return String.valueOf(price);
	}
	
	@GetMapping("responseData")
	public void test(HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		
		response.setContentType("text/html; charset=UTF-8");
		writer.print("<h1>응답</h1> ");
	}
}
