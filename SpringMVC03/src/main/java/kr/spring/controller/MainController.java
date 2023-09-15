package kr.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 컨트롤러 import
@Controller
public class MainController {

	
	@RequestMapping("/")   // controller 뒤에 "/" 요청 시 메인 페이지로 이동
	public String main() {
		System.out.println("메인페이지 이동");
		
		return "index";
	}
}
