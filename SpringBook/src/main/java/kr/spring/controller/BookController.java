package kr.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.mapper.BookMapper;

@Controller
public class BookController {

	@Autowired
	private BookMapper mapper;
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
}
