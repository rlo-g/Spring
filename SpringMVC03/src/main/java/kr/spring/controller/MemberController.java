package kr.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.entity.Member;
import kr.spring.mapper.MemberMapper;

@Controller
public class MemberController {
	
	// ** mapper 가져오기 **
	//  - 구현해주는 factorySession을 가져오기 위해서 @Autowired
	@Autowired
	private MemberMapper mapper;
	
	
	// 회원가입 클릭 시 회원가입 페이지 이동
	@RequestMapping("/joinForm.do")
	public String joinForm() {
		// 컨트롤러에서 실행 시(return) views에서 시작
		return "member/joinForm";
	}
	
	@RequestMapping("/idCheck.do")
	public @ResponseBody int idCheck(@RequestParam("memID") String memID) {
		// restController가 아닌데 비동기 방식을 사용하고 싶다면 반드시 @ResponseBody 작성
		// 매개변수 memID 받아오기
		
		Member m = mapper.idCheck(memID);
		// m == null --> 아이디 사용 가능
		// m != null --> 아이디 사용 불가능 (중복)
		if(m != null || memID.equals("")) {
			return 0;
			
		}else {
			return 1;
		}
	}
	
}