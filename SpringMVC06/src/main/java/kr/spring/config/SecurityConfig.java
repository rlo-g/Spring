package kr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import kr.spring.security.MemberUserDetailsService;

@Configuration  // WebConfig.java에서 SecurityConfig를 읽어오기 위한 어노테이션
@EnableWebSecurity // Web에서 Security를 쓰겠다
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	// Spring Security 환경설정하는 클래스
	// WebConfig가 가져다가 씀
	// WebSecurityConfigurerAdapter - 요청에 대한 보안 설정을 해주는 클래스
	// 보안 설정

	@Bean // 우리가 만들어놓은 MemberUserDetailsServive 메모리로 올려 사용하겠다
	public UserDetailsService memberUserDetailsService() {
		return new MemberUserDetailsService();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 내가 만든 MemberUserDetailsService와 
		// 암호화 및 복호화를 해주는 패스워드 인코더를
		// Spring Security에 등록하는 메서드
		auth.userDetailsService(memberUserDetailsService()).passwordEncoder(passwordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 요청에 대한 보안 설정 하는 곳
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		// http filter 등록 -  csrf 토큰에 적용
		http.addFilterBefore(filter, CsrfFilter.class);
		
		// 클라이언트가 요청을 했을 때 권한 설정
		// 회원 인증
		// 요청이 들어왔을 때 해당되는 보안이 실행됨
		// security에서 내부인증을 통해 보안을 하고 이상이 없을때 이동
		
		http
			.authorizeRequests()     /* 요청에 따른 권한을 처리 */
				.antMatchers("/")    /* 어떠한 경로에 왔을 때 권한 처리를 할 것인지 */
					.permitAll()     /* 누구나 접근 가능하도록 전체 권한 부여 */
						.and()       /* 권한 추가 */
					.formLogin()     /* 로그인 보안기능 추가 */
						.loginPage("/loginForm.do")  /* Spring Security에서 제공하는 로그인 폼이 아닌, 우리가 만든 로그인 폼 사용*/
						.loginProcessingUrl("/login.do")  /* 해당 url로 요청이 들어왔을 때 Spring Security 자체 로그인 기능 수행*/
						.permitAll()  /* 로그인은 누구나 사용해야함 --> 권한 모두에게 부여 */
						.and()
					.logout()        /* 로그아웃 기능 */
						.invalidateHttpSession(true)  /* 세션 만료. Spring Security가 알아서*/
						.logoutSuccessUrl("/")    /* 로그아웃 성공 후 이동할 url 작성 */
						.and()
					.exceptionHandling().accessDeniedPage("/access-denied");  /*로그인 안 하고 특정 페이지 접근 시 특정 url 요청 (막기 위해서) */
														// access-denied.jsp
	}
	
	
	@Bean // 패스워드 인코딩 기능을 메모리에 (객체로) 등록 
	public PasswordEncoder passwordEncoder() {
		// 비밀번호 암호화 메서드
		return new BCryptPasswordEncoder();
	}
	
}
