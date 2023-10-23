package kr.spring.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class CustomUser extends User{
	// 우리가 만든 회원정보 Member를 Spring Context Holder에 저장하기 위해서
	// User 형태로 변환해서 넣어줘야됨
	//  --> 행하는 클래스 : CustomUser 클래스 (SpringMVC06)
	// <==> authentication
	private Member member;
	
	public CustomUser(Member member) {
		super(member.getUsername(), member.getPassword(),
				AuthorityUtils.createAuthorityList("ROLE_"+member.getRole().toString()));  // toString을 통해 role 형태를 문자열로 변환
		
		this.member = member;
	}
}
