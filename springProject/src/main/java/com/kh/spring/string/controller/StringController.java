package com.kh.spring.string.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringController {
	//String 클래스 => 불변 (immutable)
	//문자열은 어느 정도의 공간을 사용해야 하는지 알 수 없음
	
	int num = 1; boolean flag = true; // 크기가 정해져있음 (기본형)
	String a = "1"; String b = "1111111"; //어느정도의 크기를 가질지 알 수 없으므로 크기를 정할 수 없음
	
	//=> 가변형 배열
	/* *** 배열의 특징 => 배열은 논리적인 구조와 물리적인 구조가 동일함 ***
	 * 논리 : 실체가 없는 것
	 * 물리 : 실체가 있는 것 */
	int[] numArr = {1, 2, 3, 4, 5}; 
	int num1 = numArr[0]; //인덱스로 접근 => 논리적 개념으로 배열에 접근 
	//메모리 상에 저장될 때도 순차적으로 저장됨 => 물리적 개념
	
	//하나의 문자열은 연속된 여러개의 캐릭터로 다룰 수 있음
	//자바는 힙 영역을 자체적으로 제어할 수 없음 
	// => 배열의 불변성? : 힙 영역은 운영체제가 관리하므로 다른 프로그램에서 사용하는 영역도 있음 => 데이터가 순처적으로 저장되지 않고 다른 프로그램의 데이터가 공간을 할당받을 수 있으므로 배열의 크기를 늘릴 수 없음
	
	
	/*
	 * String 클래스의 객체 생성 방법
	 * 1. 대입연산자를 통해서 String 리터럴을 대입하는 방법 
	 * 2. 생성자를 호출해서 String 객체로 만들어주는 방법
	 */
	// 2번 방법
	public void constructorString() {
		String str1 = new String("Hello");
		String str2 = new String("Hello");
		
		String[] strArr = {};
		
		System.out.println(str1.toString()); //=> Hello
		System.out.println(str2); //=> Hello
		
		System.out.println(strArr); // => [Ljava.lang.String;@5aaa6d82
		
		// 33 행 : stack 영역에 str1의 공간 생성 => heap 영역에 생성하기 위한 공간과 주소 생성 = > heap 영역에 byte 영역 생성하여 char array에 문자열 할당 + 바이트 영역 주소 생성 
				// => String 데이터의 value로 byte 데이터의 값 할당 => 변수의 value로 String 데이터의 주소 값 할당 => 위치를 몰라도 str1의 값을 불러올 수 있음
		
		// 모든 클래스의 최상위 클래스 => Object 클래스
		// 상속 (extends) : 다중 상속 불가능 => 임의로 상속 클래스를 지정하지 않으면 자동으로 Object 클래스를 상속 받음 (Object 클래스의 메서드를 사용할 수 있음)
		//native => 운영체제에 내장되어 있는 함수를 사용할 수 있도록 지정 (C언어)
		//Object.toString() => 실제 메모리상에 존재하는 주소를 16진수로 반환함
		
		// *** 출력 코드에서 생략된 것? : 스트링 타입의 값이 pnritln의 매개변수로 들어감
		// 객체 타입이 출력 메서드의 매개변수로 들어갈 경우 toString() 메서드를 사용해야함
		// String 클래스도 Object 클래스로부터 상속받으므로 toString()을 사용할 수 있으며, toString()으로 retrun this 하도록 overriding 함.
		// => toString() 메서드를 사용한 값과 사용하지 않은 값이 같은 데이터를 출력함
		
		/*
		 * 1. String 클래스의 toString의 경우
		 * 실제 담겨있는 문자열 리터럴을 반환하게끔 오버라이딩
		 * 
		 */
		
		/*
		 * equals() => Object 클래스로부터 상속
		 * 
		 */
		//매개변수로 Object 타입을 받으므로
		// 주소가 동등한지 비교하는 메서드
		System.out.println(str1.equals(str2)); // =>true
		// str1 == str2? : new 키워드를 통해 새로운 생성자를 생성했으므로 heap 영역에 서로 다른 영역을 생성했는데, 주소를 비교하는 equals() 메서드를 사용했을때 동일하다고 출력됨
		// **** => String은 주소값을 비교하는 Object.equals() 메서드를 사용하는 것이 아니라, 오버라이딩하여 주소값 비교가 아닌 실제 문자열 리터럴 값을 비교 *****
		
		
		/* hashCode() => 암호화 알고리즘 중 하나
		 * 16진수를 알아보기 쉽도록 int형 10진수로 반환
		 */
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		// => 서로 같은 값
		// 주소값을 해시하는 것이 아니라 실제 담긴 문자열을 기반으로 해시코드 값을 만들어서 반환
		
		//+ VO 클래스는 hashCode()가 오버라이딩 되어 있어야 함
		// VO : 값을 담는 것이 목적 / DTO : 값을 전달하는 것이 목적
		// VO =>  데이터에서 전달받은 값을 변경하지 않고 그대로 VO에 담음
		//  ==> 값이 불변이어야 하므로 개념적으로는 setter()가 있어서는 안됨 
		//  ==> setter() 대신 hashCode()와 equals()를 사용
		
		// 실제로 식별할 수 있는 값
		// System.identityHashCode(참조형변수);
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		// => 서로 다른 값!!!
		System.out.println(str1 == str2);
		
		
		
		
		
		
	}
	
	//리터럴 대입방식
	public void assignToString() {
		String str1 = "Hello";
		String str2 = "Hello";
		
		System.out.println(str1);
		System.out.println(str2);
		
		System.out.println(str1.equals(str2)); // =>true
		
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode()); //=> 동일한 값
		
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2)); // => 동일한 값
		
		System.out.println(str1 == str2); //=> true
		/*
		 * A == B => A와 B가 같은지?
		 * A != B
		 * A > B
		 * A < B
		 * 비교연산은 의문형으로 작성 및 해석
		 */
		
		
		

	}
	
	//=> 리터럴 대입 방식을 사용하는 이유
	// 생성자를 생성할때마다 메모리 공간을 차지하므로, 리터럴 값으로 대입해야 함!
	
	
	public void stringPool() {
		String str = "Hello";
		System.out.println(System.identityHashCode(str));
		// 대입연산자를 이용해서 문자열 리터럴값을 대입 하는 경우
		//StringPool 영역에 올라감 (Heap 영역의 constant Pool 내부 (상수들이 저장됨))
		// 136 행 코드 진행 순서 : stringPool() {} 메서드 실행 공간 생성 (Stack) => Stack 내부에 str을 저장하는 공간 생성 => Heap 영역 내부의 StringPool에 "Hello" 문자열이 저장됨 + 주소 생성
		// => str 변수에 해당 주소를 대입
		
		//String newStr = "Hello";
		// 143행 진행순서 => Stack 영역에 newStr을 저장하는 공간 생성 => Heap 영역의 StringPool에 생성하려는 "Hello"가 이미 있으므로, newStr에 해당 문자열의 주소를 대입함
		// *** StringPool : 동일한 내용의 문자열 리터럴이 존재할 수 없음
		// => 메모리 절약 가능
		
		str = "Bye";
		System.out.println(System.identityHashCode(str));
		// 145행 진행 순서 =>  StringPool에 "Bye"라는 문자열이 생성됨 + 주소 생성 => str 변수가 새로 생성된 주소값을 참조하게 됨 ("Hello" 로 저장한 값이 바뀌는 것이 아닌 변수가 참조하는 주소가 바뀜)
		// => 변경 전, 후 str이 갖는 주소값이 다름
		// RC : Reference Count (자신을 참조하는 개수)
		// RC가 0이 되는 데이터는 메모리가 낭비됨 
		/*
		 * 연결이 끊긴 문자열은 가비지 콜렉터가 정리해줌
		 * 객체는 불변, 참조변수는 새로운 주소값을 참조
		 */
		
		//기본자료형인 경우 => 값을 변경하면 stack 영역에서 해당 변수가 갖는 값이 재할당됨
		
		//복합 대입 연산자
		str += "!!";
		
		
		
		
		//
		String a = "a";
		String b = "a";
		System.out.println("결과 : "+ a == b);
		// => false 출력 : 문자열끼리의 산술 연산이 먼저 수행되어 "결과 : a" == "a" 비교연산을 수행하게 됨
		
		log.info("결과 : {}", a == b); //=> "결과 : true" 확인 가능
		// 문자열끼리의 연산과, 메모리(자원) 낭비를 방지하기 위해 문자열 출력이 아닌 log를 출력하여 확인하는 것이 좋음
		
		
		//문자열 연산 시 자원 사용을 줄이기 위해 StringBuffet, StringBuilder 사용
		//StringBuilder를 사용 / 웹 소켓 사용시 buffer 사용 가능
		StringBuffer sb = new StringBuffer();
		sb.append("123");
		System.out.println(sb.toString());
		//StringBuilder는 thread safe
		StringBuilder sr = new StringBuilder();
		sr.append("123123");
		System.out.println(sr.toString());
		
		
		
		
		
		
		
		
		
	}
	
}
