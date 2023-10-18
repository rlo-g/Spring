package kr.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 환경설정파일 설정
public class SecurityConfiguration {
	
	@Autowired
	private UserDetailsServiceImpl userService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// 비밀번호 인코딩 기능
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		// 보안 적용
		http.csrf().disable();  // csrf 인증 토큰 비활성화
		http.authorizeHttpRequests()   // 사용자 요청 핸들링
			.antMatchers("/","/member/**").permitAll()  // "/", "member" 하위 모든 접근을 허용
			.antMatchers("/board/**").authenticated()   // board로 접근하는 모든 경우는 인증된(로그인한) 사용자만 허용
			.and() // 추가
			.formLogin()  // 별도의 로그인 폼을 사용 (우리가 만든 것)
			.loginPage("/member/login")  // 로그인 페이지는 member/login에서 하겠다
			.defaultSuccessUrl("/board/list")  // 로그인 성공 시 board/list로 이동
			.and()
			.logout()  // Spring Security에서 제공하는 기본 로그아웃 사용
			.logoutUrl("/member/logout")  // 로그아웃 실행 원할 시 member/logout으로 요청
			.logoutSuccessUrl("/");  // 로그아웃 후 /로 이동  
			
		
		http.userDetailsService(userService);  // 등록
		
		
		
		return http.build();
	}
}
