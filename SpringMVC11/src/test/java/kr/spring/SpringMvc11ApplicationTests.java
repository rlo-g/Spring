package kr.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.spring.entity.Member;
import kr.spring.entity.Role;
import kr.spring.repository.MemberRepository;

@SpringBootTest
class SpringMvc11ApplicationTests {

	@Autowired
	private MemberRepository memberRepository;

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void contextLoads() {
		// 회원가입 테스트
		//  --> join 페이지로 넘기면 기능 생성 가능
		Member m = new Member();
		m.setUsername("song");
		m.setPassword(passwordEncoder.encode("1234"));
		m.setName("송송");
		m.setRole(Role.MANAGER);
		m.setEbled(true);
		memberRepository.save(m);
	}

}
