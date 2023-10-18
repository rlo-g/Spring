package kr.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/*")  // /member/로 들어오는 것들을 처리
public class MemberController {

	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
}
