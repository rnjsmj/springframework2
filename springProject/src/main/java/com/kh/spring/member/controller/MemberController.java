package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@RESTController
//@Controller : 일반적인 방식 + 내부에서 Rest Controller 동작 작성 가능 (범용적 + 설정 필요)
// 컨트롤러 ㅣ 제어하는 코드 

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	// Service를 빈으로 등록해놓은 상태 (@Service) --> 상위 타입 인터페이스로 선언하여 주입
	
	//암호화
	private final BCryptPasswordEncoder pwdEncoder;
	
	/*
	Spring에서 Handler가 요청시 전달값(Parameter)을 받는 방법
	1. HttpServletRequest을 이용하여 전달받기 (기본 JSP/Servlet 방식)
		=> 요청시 생긴 request를 받을 수 있음
		=> getParameter("key") 이용;
		DispatcherServlet이 해당 메서드를 호출할때 request 객체를 전달하여 매개변수로 주입

	 */
//	@RequestMapping("/login.do") //RequestMapping 타입의 애노테이션을 붙임으로서 HandlerMapping 등록
//	public String login(HttpServletRequest request) {
//		String userId = request.getParameter("userId");
//		String userPwd = request.getParameter("userPwd");
//		log.info("입력 아이디 : {}", userId);
//		log.info("입력 비밀번호 : {}", userPwd);
//		return "main";
//		
//	}
	
	/*2. @RequestParam 애노테이션 이용
	 * 	- request.getParameter("key")로 밸류를 가져오는 역할을 대신함
	 *  - name 또는 value의 값으로 jsp에서 작성했던 name 속성값을 호출하면 자동으로 해당 매개변수로 주입함
	 *  - 불러온 값이 비어있다면 defaultValue 속성으로 기본값 지정 가능
	 */
//	@RequestMapping("/login.do") 
//	public String login(@RequestParam(value="userId", defaultValue="aaa") String userId, @RequestParam(value="userPwd") String userPwd) {
//		log.info("입력 아이디 : {}", userId);
//		log.info("입력 비밀번호 : {}", userPwd);
//		return "main";
//	}
	
	/*3.@RequestParam 애노테이션을 생략하는 방법
	 * - 조건 : 매개변수 식별자와 jsp의 name 속성 값(또는 요청 시 전달하는 값의 key값)과 동일하게 작성해야 자동으로 값이 주입
	 * - 단점 : 2번에서 사용했던 defaultValue 사용 불가능
	 */
//	@RequestMapping("/login.do") 
//	public String login(String userId, String userPwd) {
//		log.info("입력 아이디 : {}", userId);
//		log.info("입력 비밀번호 : {}", userPwd);
//		
//		//1. 데이터 가공-> DTO 객체를 이용하여 데이터를 전달할 수 있도록 데이터 지정
//		// ? : DTO를 통해 넘겨야 할 파라미터가 많을 수록 작업량과 실수가 늘어남
//		//   또한 sqlSession의 메서드는 sql 구문의 인자값을 하나만 지정할 수 있으므로, 여러개의 값을 하나의 객체로 전달해야 함 (MyBatis)
//		//Member 타입의 변수를 선언하여 Member 클래스의 기본 생성자를 호출하고 해당 변수에 대입
//		Member member = new Member();
//		member.setUserId(userId);
//		member.setUserPwd(userPwd);
//		
//		// 1.5. 서비스(비즈니스 로직) 호출
//		//memberService.login(id, pwd);
//		
//		
//		// 2. 응답화면 지정
//		return "main";
//	}
	
	/* 4. 커맨드 객체 방식
	 * - 반드시 name속성값과, 담고자 하는 필드명이 동일해야 함 + setter 메서드 있어야 함 + 기본 생성자 있어야 함
	 * - 해당 메서드의 매개변수로 요청 시 전달값을 담고자 하는 클래스의 타입을 지정한 뒤, 요청 시 전달 값의 키 값(name 속성값)을 클래스의 담고자 하는 필드명과 동일하게 작성
	 * => 스프링 컨테이너가 해당 객체를 기본생성자로 생성한 후 내부적으로 setter 메서드를 찾아 요청 시 전달 값을 해당 필드에 담아줌 (=setter injection)
	 *    => setter가 필요하지만 vo 는 일반적으로 setter가 없어야 하므로, DTO에서 사용
	 */
//	@RequestMapping("/login.do") 
//	public String login(Member member) {
//		log.info("가공된 멤버객체 : {}", member);
//		Member loginMember = memberService.login(member); 
//		
//		//member : Member 객체의 주소
//		//loginMember : Member 타입 객체의 주소 (memberService.login()을 통해 반환된 값을 대입함)
//		log.info("로그인 정보 : {}", loginMember); //Member 객체의 toString() 메서드의 호출 결과
//		
//		return "main";
//	}
	
	/*
	//REST 방식의 URL
	//localhost/spring/member/user123
	//@PathVariable로 선언한 변수 명과, Mapping으로 받을 매개 변수의 이름이 일치해야 함
	@GetMapping("/member/{id}")
	public void restTest(@PathVariable String id) {
		log.info("앞 단에서 넘긴 값 : {}", id);
	}*/
	
	
	/* 요청 처리 후 응답 데이터를 담고 응답페이지로 포워딩 또는 리다이렉트 하는 방법
	 * 
	 * 1. 스프링에서 제공하는 Model 객체를 사용하는 방법
	 *  - forwarding 할 응답 뷰로 전달하고자 하는 데이터를 Key-Value 형태로 담을 수 있는 영역
	 *  - Model 객체는 request Scope
	 *  - forwarding :  요청하여 매핑된 주소의 값이 브라우저의 url에 그대로 남아있음
	 *   => redirect를 이용하여 새로고침 시 오류가 발생하지 않도록 처리
	 * 
	 * 
	 */
	/*
	@PostMapping("login.do")
	public String login(Member member, Model model, HttpSession session) {
		Member loginUser = memberService.login(member); 
		if (loginUser == null) { //로그인 실패 => 에러문구를 requestScope에 담아서 에러페이지로 포워딩
			model.addAttribute("errorMsg", "로그인 실패");
			return "common/errorPage";
		} else { //로그인 성공 => loginUser를 sessinoScope에 담아 응답화면으로
			session.setAttribute("loginUser", loginUser);
			
			//redirect 방식 -> /
			return "redirect:/";
		}
	}*/
	
	
	/* 2. Spring에서 제공하는 ModelAndView 타입을 사용하는 방법
	 * Model은 데이터를 Key-Value 세트로 담을 수 있는 공간
	 * View는 응답 뷰에 대한 정보를 담을 수 있는 공간
	 * 
	 * ModelAndView : Model 객체와 View가 결합된 형태의 객체 (View는 단독으로 사용할 수 없음)
	 * 
	 * 
	 */
	
	@PostMapping("login.do")
	public ModelAndView login(Member member, ModelAndView mv, HttpSession session) {
		Member loginUser = memberService.login(member); 
		
		// MEMBER 테이블에 사용자가 입력한 userId값이 존재하고, STATUS 컬럼의 값이 'Y'와 일치하면
		// loginUser 변수에 조회된 ResultSet 컬럼의 값이 필드에 담긴 Member 객체의 주소값이 들어감
		// => loginUser 필드 : DB에 기록된 암호화된 비밀번호
				
		//BCryptPasswordEncoder 객체의 matches()
		// matches(평문, 암호문) => 암호문의 데이터가 갖고있는 솔트값, 알고리즘, 반복횟수를 판단하여 입력된 평문을 암호화 하여 두 값이 같은 지 비교
		
		//pwdEncoder.matches(member.getUserPwd(), loginUser.getUserPwd()) && loginUser != null 조건을 사용하는 경우
		// => NullPointerException (loginUser 가 null값인데 getUserPwd()를 수행하여 발생 ) === UnCheckedException (컴파일러가 체크하지 않는 예외)
		// RuntimeException 후손 클래스의 공통점 : 컴파일 시점에서는 예외가 발생할 지 모름 ===> 개발자가 직접 예외 처리 해야함
		
		// loginUser != null && pwdEncoder.matches(member.getUserPwd(), loginUser.getUserPwd()) 으로 수정
		// 숏 서킷 연산을 통해 비교연산자의 앞에 작성된 조건이 false이면 뒤에 작성한 조건과 상관 없이 false로 처리하므로 if문을 빠져나옴
		
		
		if ( loginUser != null && pwdEncoder.matches(member.getUserPwd(), loginUser.getUserPwd())) { 
			//로그인 성공 => loginUser를 sessinoScope에 담아 응답화면으로
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/");
			
		} else { 
			mv.addObject("errorMsg", "로그인 실패").setViewName("common/errorPage");
		}
		return mv;
		
	}
	
	@GetMapping("logout.do")
	public String logout(HttpSession session) {
		
		// sessionScope에 존재하는 loginUser 제거
		// session.invalidate(); //Session 초기화 -> 세션 내의 모든 정보가 제거됨
		// 선택한 정보만 지우기 위해서는 removeAttribute()
		session.removeAttribute("loginUser");
		session.removeAttribute("alertMsg");
		
		return "redirect:/";
	}
	
	@GetMapping("enroll.do")
	public String enrollForm() {
		return "member/enrollform";
	}
	
	@PostMapping("insert.do")
	public String insert(Member member, Model model) {
		
		log.info("회원가입 객체 : {}", member);
		
		// 필요한 기능
		// 1. 한글 인코딩
		// 2. 비밀번호 값이 사용자가 입력한 평문(plain text)이 전달됨
		
		//log.info("평문 비밀번호 : {}", member.getUserPwd());
		String encPwd = pwdEncoder.encode(member.getUserPwd());
		//log.info("암호문 : {}", encPwd );
		member.setUserPwd(encPwd);
		//Insert 할 데이터가 필드에 담긴 Member 객체의 userPwd가 평문이 아닌 암호문을 담아서 DB에 전송
		
		String viewName = "";
		if(memberService.insert(member) > 0) { // 성공
			
			viewName = "redirect:/";
			
		} else { //실패
			
			model.addAttribute("errorMsg", "회원가입 실패");
			
			viewName = "common/errorPage";
		}
	
		return viewName;
	}
	
	@GetMapping("mypage.do")
	public String mypage() {
		return "member/mypage";
	}
	
	@PostMapping("update.do")
	public String update(Member member, HttpSession session, Model model) {
		
		log.info("수정 요청 멤버 : {}", member);
		if(memberService.update(member) > 0) { //데이터베이스에서 수정에 성공
			//응답화면 지정
			//1.forwarding
				//return "member/mypage"
				//mypage.do의 응답화면과 중복되어 mypage가 다른 파일명으로 변경되었을 경우 변경할 코드가 많아짐 => 유지보수어려움
			//2.redirect
				//mypage에서 sessionScope의 데이터를 EL로 불러와 value로 지정했으므로, update를 수행해도 테이블만 변경될뿐 mypage(View)의 정보가 바뀌지 않음
				//==> DB로부터 수정된 회원정보를 다시 조회하여  session 정보를 갱신해야 mypage에서 변경된 데이터를 가져올 수 있음
				// ====> *** sessionScope에 loginUser라는 키값으로 덮어씌워야 함
			session.setAttribute("loginUser", memberService.login(member));
			
			// 성공 메시지 담아서 전달
			// 1. model에 저장 <= 할 수 없음
			// requestScope -> 다른 페이지로 redirect 했으므로 받아올 수 없음
			// 2. session에 저장
			session.setAttribute("alertMsg", "회원 정보가 수정되었습니다.");
			
			
			return "redirect:/mypage.do";
			
		} else {
			model.addAttribute("errorMsg", "정보 수정에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	@PostMapping("delete.do")
	public String delete(Member member, HttpSession session, Model model) {
		
		//member 객체를 통해 아이디와 비밀번호를 가져옴 (사용자가 입력한 평문 비밀번호)
		//비밀번호 값 검증
		//1. session의 값 사용 / 2. memberService.login(member) 이용
		//1. session의 loginUser 키 값으로 지정되어있는 Member 객체의 userPwd 필드 값
		
		String plinPwd = member.getUserPwd() ;
		String encPwd = ((Member) session.getAttribute("loginUser")).getUserPwd();
		//session객체에서 getAttribute를 하면 반환값은 Object이므로, 
		//Member 타입으로 형변환을 수행해야 그 후에 Member 클래스의 메서드(getUserPwd())를 사용할 수 있음!!
		//setAttribute()메서드도 마찬가지로 value는 항상 Object 타입이므로, value로 지정한 값을 자동으로 Object로 형변환 하여 session에 저장하는 것
		// ~ Object 클래스는 모든 클래스의 상위 클래스이므로 가능함 !!!
		// 왜왜왜왜왱왜왜왜!!!! 항상 왜???? 를 생각하귀!!~!~~!~!
		
		if(pwdEncoder.matches(plinPwd, encPwd)) {
			if(memberService.delete(member.getUserId()) > 0) {
				session.setAttribute("alertMsg", "정상적으로 탈퇴되었습니다.");
				session.removeAttribute("loginUser");
				return "redirect:/";
			} else {
				model.addAttribute("errorMsg", "회원 탈퇴 중 오류가 발생했습니다.");
				return "common/errorPage";
			}
			
		} else {
			session.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
			return "redirect:/mypage.do";
		}
		
	}
	
	@ResponseBody
	@GetMapping("idcheck")
	public String checkId(String checkId) {
		
		//log.info(checkId);
		
		//int result = memberService.idCheck(checkId);
		
		return  memberService.idCheck(checkId) > 0 ? "NNNNN" : "NNNNY";
	}
	
	@ResponseBody
	@PostMapping(value="change-pwd", produces="text/html; charset=UTF-8")
	public String changePwd(Member member, String changePwd) {
		//전달 받은 사용자 아이디와 기존 비밀번호를 Member 객체로, 변경할 비밀번호를 changePwd로 받음
		Member loginUser = memberService.login(member);
		// 회원 정보가 일치하는 경우 변경할 비밀번호를 암호화 하여 member 객체에 저장하고,
		// 해당 member 객체를 Service에 전달하여 비밀번호 변경 구문 수행
		// 데이터 변경에 성공하면 Y, 성공하지 못하면 N 응답
		// 만약 회원 정보가 일치하지 않은 경우 "WrongPwd" 응답
		if(pwdEncoder.matches(member.getUserPwd(), loginUser.getUserPwd())) { 
//			log.info("변경 비밀번호 암호문 : {}", pwdEncoder.encode(changePwd));
			member.setUserPwd(pwdEncoder.encode(changePwd));
			return memberService.changePwd(member) > 0 ? "Y" : "N"; 
		} else { 
			return "WrongPwd";
		}
	}
	
	
	
	
}



/*
요청 하나에 서블릿(컨트롤러) 하나로 작업하면 중복 코드가 너무 많이 생김

@Controller 어노테이션을 통해 어떻게 controller 로 인식하는지?

*/

