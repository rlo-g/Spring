package kr.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.spring.entity.Member;
import kr.spring.entity.MemberUser;
import kr.spring.mapper.MemberMapper;

public class MemberUserDetailsService implements UserDetailsService{
	// Spring Security에서 Mapper를 사용하기 위한 연결 클래스 --> Service
	// 특정 인터페이스 구현
	
	@Autowired
	private MemberMapper mapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Username : id
		//  --> id를 기준으로 로그인 정보를 가져오는(loadUser) 메서드
		// Spring Security가 해당 아이디를 가진 계정을 가져오고 암호화된 비밀번호를 비교해서 로그인을 체크하는 메서드
		// return 타입 UserDetails - User가 UserDetails 상속 받음 - User 형태 (MemberUser)
		
		// 로그인 처리하기
		//  - 이미 알아서 SpringSecurity가 로그인 기능을 다 끝마친 상태
		//  - 이제 개발자는 진짜로 중간에 비밀번호를 알 수 있는 방법이 없다.
		Member mvo = mapper.getMember(username);
		
		// Spring Security 내부 보안 규정 때문에 우리가 직접 만든 클래스 객체(VO)는 바로 담을 수 없다
		//  - 내가 원하는 VO를 담을 수 있도록 변환해주는 User Class 필요 (MemberUser)
		
		if(mvo != null) {
			// 해당 사용자 존재
			return new MemberUser(mvo);		// 로그인 후 MemberUser 리턴 --> Spring Security Context 안에 회원 정보 저장
		}else {
			// 해당 사용자 없음
			throw new UsernameNotFoundException("user with username" + username + "does not exist.");
		}
	}
	
}
