package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* Lombok  어노테이션
 * 기본 생성자 @NoArgsConstructor ***
 * @AllArgsConstructor -> 잘 사용하지 않음!!
 * @Getter ***
 * @Setter ***
 * @ToString
 * @Builder
 * @Data ***
 * 
 * Lombok 사용 시 주의사항
 * - 시작글자가 외자인 필드명 사용 x => 최소 소문자 두 글자 이상으로 지정
 *   ? pName --> getPName() 생성
 *     ${ pName } == getpName()
 */

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member {
	//테이블의 한 행의 정보를 담을 수 있음
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String gender;
	private String age;
	private String phone;
	private String address;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	
	
}
