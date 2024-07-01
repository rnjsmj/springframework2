package com.kh.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.ajax.Menu;

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
	
	
	@ResponseBody
	@GetMapping(value="ajax2", produces="application/json; charset=UTF-8")
	public String selectMenu(int menuNumber) {
		
		// 데이터 응답
		
		/* 가상 메뉴 테이블
		 * ----------------------------------------
		 * | 메뉴번호 | 메뉴이름 |  가격  |  재료  |
		 * ----------------------------------------
		 * |  1     | 열무국수 | 8000  | 열무  |
		 * ----------------------------------------
		 * 
		 * 
		 * JSON(Java Script Object Notation) : 자바 스크립트 객체 표기법
		 * => [], {} 형태
		 */
		
		//VO 필드를 통해 StringBuilder로 JSON 형태 문자열 생성하여 반환하는 방법
		// => 
		
		
		 Menu menu = new Menu(1, "순두부찌개", 8000, "순두부");
		 /* 
		 * StringBuilder sb = new StringBuilder(); sb.append("{");
		 * sb.append("menuNumber : '" + menu.getMenuNumber());
		 * sb.append(", 'menuName: '" + menu.getMenuName());
		 * sb.append(", 'price : '" + menu.getPrice()); 
		 * sb.append(", 'material: '" + menu.getMaterial() + "'");
		 * 
		 * //생성한 문자열을 텍스트 형식이 아닌 JSON 형태로 보내야 함 => produces의 형태를 application/json으로 변경!
		 * 
		 * 
		 * 
		 * 
		 * return sb.toString();
		 */ 
		 //=> 정상적으로 json으로 파싱하지 못함
		 // => JAVA에서 key-value 형태로 전송할 수 있는 것은 Map 밖에 없음
		 // => JSON도 Map 형태!@!@!@!@!@!@
		
		//******자바스크립트 객체 형태의 문자열로 만들어서 전송******* 
		 // javascript에서 해석할 수 있는 객체로 전달하기 위해
		 // HashMap을 상속받은 JSONObject 라이브러리를 이용해 
		 // put을 통해 key-value 값을 추가하고, 
		 // AJAX 요청에 대해 응답할 때 JSONObjcet.toString()을 호추랗여 StringBuffer를 통해 문자열 데이터로 전송 가능
		 //해당 문자열 데이터를 객체로 해석시키기 위해 produces 속성의 값을 json 형태로 전송하도록 지정
		 
		 
		JSONObject jObj = new JSONObject();
		jObj.put("menuNumber", menu.getMenuNumber());
		jObj.put("menuName", menu.getMenuName());
		jObj.put("price", menu.getPrice());
		jObj.put("material", menu.getMaterial());
		
		return jObj.toJSONString();
		
		
	}
	
	/*
	@ResponseBody
	@GetMapping(value="ajax3", produces="application/json; charset=UTF-8")
	public String ajax3Method(int menuNumber) {
		
		Menu menu = new Menu(menuNumber, "순두부찌개", 8000, "순두부");
		
		return new Gson().toJson(menu);
	}*/
	
	@ResponseBody
	@GetMapping(value="ajax3", produces="application/json; charset=UTF-8")
	public Menu ajax3Method(int menuNumber) {
		
		Menu menu = new Menu(menuNumber, "순두부찌개", 8000, "순두부");
		
		return menu;
	}
	
	
	@ResponseBody
	@GetMapping(value="find", produces="application/json; charset=UTF-8")
	public String findAll() {
		
		List<Menu> menus = new ArrayList();
		menus.add(new Menu(1, "마파두부", 15000, "두부"));
		menus.add(new Menu(2, "돈까스", 9000, "돼지고기"));
		menus.add(new Menu(3, "열무국수", 8500, "열무김치"));
		
		//selectList 조회 결과 => menus
		//List<menu> list = menuService.findAll();
		
		// 각각 하나의 메뉴 정보가 객체로 담겨야 함
		// => 하나의 객체 배열로 전송
		// [{ 마파두부 ... }, { 돈까스 ... }, { 열무국수 ... }]
		
		/*
		JSONObject jObj1 = new JSONObject();
		jObj1.put("menuNumber", menus.get(0).getMenuNumber());
		jObj1.put("menuName", menus.get(0).getMenuName());
		jObj1.put("price", menus.get(0).getPrice());
		jObj1.put("material", menus.get(0).getMaterial());*/
		// .... 위와 같은 같은 방식으로 JSONObject를 List의 Menu 객체 수 만큼 생성해야 함
		// 여러개의 객체를 add할 수 있는 ArrayList를 상속받은 JSONArray 클래스로 JSON Array 객체 생성
		// => 반복문을 사용하여 JSONArray에 Menu 객체 추가
		
		JSONArray jArr = new JSONArray();
		/*
		for(int i=0; i < menus.size(); i++) {
			JSONObject jObj = new JSONObject();
			jObj.put("menuNumber", menus.get(i).getMenuNumber());
			jObj.put("menuName", menus.get(i).getMenuName());
			jObj.put("price", menus.get(i).getPrice());
			jObj.put("material", menus.get(i).getMaterial());
			
			jArr.add(jObj);
		}*/ 
		//위와 같이 반복문을 사용하여 JSONArray에 객체를 추가하는 과정을 Gson을 사용하여 한번에 수행
		
		return new Gson().toJson(menus);
	}
	
}
