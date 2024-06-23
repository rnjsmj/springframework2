package com.kh.spring.string.hash.model.vo;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
	//@Data : hashCode(), equals() override
	// => VO는 기본적으로 Setter()를 사용하지 않으므로 @Data를 권장하지 않음
	//@AllArgsConstructor 사용 => @Buidler도 같이 선언해야 함!
	
	
//	//hashCode() : 각각의 필드값을 검증해서 비교해야함 
//	// return (+ 연산 값).hashCode()를 하는 경우 => int의 값에 따라 15 + 10 == 150 + 0 과 같은 결과가 나오므로 잘못된 방법
//	@Override
//	public int hashCode() {
//		
//		return 0;
//	}
//	
//	//equals()
//	//내가 가진 필드와 매개변수로 전달받은 객체의 필드 값을 비교
//	@Override
//	public boolean equals(Object obj) {
//		Student other = (Student) obj;
//		if (!other.name.equals(name) || other.age != this.age || other.score != this.score) {
//			return false;
//		}
//		return true;
//		
//	}
	
	//기본 생성 Source
	private String name;
	private int age;
	private int score;
	@Override
	public int hashCode() {
		return Objects.hash(age, name, score);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return age == other.age && Objects.equals(name, other.name) && score == other.score;
	}

	
}
