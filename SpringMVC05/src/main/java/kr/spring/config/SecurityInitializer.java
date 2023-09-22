package kr.spring.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer{
	// AbstractSecurityWebApplicationInitializer
	// 상속받으면 자동으로 보안 관련 객체들이 생성되어
	// Spring Container (메모리공간)으로 올라간다
	// 필요할 때마자 가져다가 사용할 수 있음
}
