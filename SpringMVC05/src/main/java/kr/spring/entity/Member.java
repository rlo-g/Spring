package kr.spring.entity;

import java.util.List;

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
	
	// 자신의 권한 정보를 저장할 변수
	// - 권한은 여러개가 될 수 있으므로 list로 받는다
	private List<Auth> authList;
}
