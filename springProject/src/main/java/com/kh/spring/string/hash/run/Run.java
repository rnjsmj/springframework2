package com.kh.spring.string.hash.run;

import java.util.HashSet;
import java.util.Set;

import com.kh.spring.string.hash.model.vo.Student;

public class Run {

	public static void main(String[] args) {

		/*
		 * HashSet
		 * Value값만 저장, index가 존재하지 않음 => 순서보장 x, 중복 x (Set의 특징)
		 * HashSet 특징
		 * 
		 * 
		 */
		
		//문자열만 담을 수 있는 HashSet
		//Generic 사용 ? : 개발자의 편의를 위해 사용
		//	=> 실수 방지(의도치 않은 차입의 값이 저장되지 않도록), 개발 편의성
		// 자료 저장 방식 *** List란? *** 
		HashSet<String> set = new HashSet();
		
		/*
		List<String> list = new ArrayList<>();
		List l = new ArrayList();
		
		l.add("Hello");
//		System.out.println(l.get(0).charAt(0)); //=> l.get(0)은 Object형이므로 charAt(0) 불가능
		System.out.println(((String)l.get(0)).charAt(0)); // => 형 변환하여 charAt(0)
		
		list.add("Hello");
		System.out.println(list.get(0).charAt(0)); //=> 강제 형변환 할 필요 없음*/
		
		System.out.println(set);
		//set 인스턴스를 출력하는 경우 주소가 아닌 배열값이 나옴 => 주소를 Object.toString() 메서드가 오버라이딩 됨!
		//AbstractCollection.toString() : StringBuilder를 이용하여 메모리를 낭비하지 않고 Set을 문자열로 "+" 연산을 수행하여 출력
		// ? HashMap 클래스가 아닌 AbstractCollection에 구현 : Collection의 출력형식은 모두 같기 때문에 상위 클래스에서 구현함.
		
		//요소 추가 : add()
		
		set.add("안녕하세요");
		set.add(new String("안녕하세요")); //=> set은 중복을 허용하지 않으므로 추가되지 않음
		System.out.println(set);
		
		System.out.println("\n\n\n\n\n");
		//Student
		Set<Student> students = new HashSet();
		students.add(new Student("1번", 10, 30));
		students.add(new Student("2번", 10, 30));
		students.add(new Student("3번", 10, 30));
		students.add(new Student("1번", 10, 30));
		
		System.out.println(students);
		
		// => sysout : toString() 결과를 자동으로 호출
		//생성한 Student 객체의 toString()을 실행하여 반환한 String값이 출력됨 (override 하지 않았으므로 Object.toString() 호출)
		
		//String 클래스 : equals(), hashCode() 오버라이딩 되어있음
		// => 주소값을 비교하지 않고, 리터럴 값이 같으면 똑같다고 판단하므로 리터럴 값이 같은 데이터는 무조건 저장되지 않음
		//  ? HashSet의 특징!!!!! : 새로 생성한 객체는 갖고있는 데이터 값이 같더라도 동일 객체로 판단하지 않음
		// => **** 요소가 새롭게 추가될 때마다 equals() 와 hashCode()로 비교 후 둘 다 결과가 true인 경우 동일 객체 판단 ***
		
		/*
		//equals() :현재 객체의 주소값을 비교하여 결과를 반환 => boolean
		// hashCode() : 현재 객체의 주소값을 해싱알고리즘을 돌려 10진수로 변환 => int
		*/
		// => 갖고 있는 데이터가 같은 경우 중복으로 판단하게 하기 위해서는 위 두개 메서드를 overriding 해야 함
		
		// 채팅과 같이 순서 상관 없고 중복되면 안되는 데이터 이용하기
		// set 중 thread safe 가 있는 것 : CopyOnWriteArraySet<E>
		
		//기능 구현 초점
		// 1. 값 + 타입, 2. 값 저장소
		// 기획 중요 !!!!!! 쌉중요 . 
	}

}
