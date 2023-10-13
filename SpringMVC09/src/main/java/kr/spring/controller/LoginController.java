package kr.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.entity.Member;
import kr.spring.service.BoardService;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	// fm대로 한다면 memberService를 따로 만들어야 하지만 로그인 기능만 수행할 것이라서 따로 안 나눔
	@Autowired
	private BoardService service;

	@RequestMapping("/loginProcess")
	public String login(Member vo, HttpSession session) {
		
		Member mvo = service.login(vo);
		
		if(mvo != null) {
			// mvo가 null이 아닌경우 (로그인 성공) - 세션에 mvo로 로그인 정보 저장
			session.setAttribute("mvo", mvo);
		}
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/logoutProcess")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "redirect:/board/list";
	}
}
