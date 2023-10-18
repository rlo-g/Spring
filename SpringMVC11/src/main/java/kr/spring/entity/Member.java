package kr.spring.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity  // Member VO가 Database Table 만들 때 설정
public class Member {

	@Id // PK 지정
	private String username;  // Spring Security에서는 id가 아닌 username으로 지정
	
	private String password;  // Spring Security에서는 pw가 아닌 password로 지정
	
	@Enumerated(EnumType.STRING)  // @Enumerated -> 열겨형 (권한이 여러개이기 때문에)
	private Role role;  // Spring Security - 권한 정보 필요
	
	private String name;  // 이름
	
	private boolean ebled;  // 계정 활성화/비활성화
}
