package kr.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Auth {
	// 권한 정보를 저장할 클래스
	private int no; // 일련번호
	private String memId; // 회원 아이디
	private String auth; // 회원 권한 (ROLE_USER, ROLE_MENAGER, ROLE_ADMIN)


}
