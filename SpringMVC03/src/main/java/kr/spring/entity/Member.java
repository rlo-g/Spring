package kr.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// ROMBOK
// getter/setter을 사용하기 위한 API
// 전체생성자/기본생성자(매개변수가 없는 생성자)
// 데이터 확인을 위한 toString
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
	// MemberVO
	// 반드시 sql에서 생성한 테이블 컬럼명과 동일하게 할 것
	private int memIdx;
	private String memId;
	private String memPw;
	private String memName;
	private int memAge;
	private String memGender;
	private String memEmail;
	private String memProfile;
	
	
}
