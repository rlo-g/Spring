package kr.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public @ResponseBody int idCheck(@RequestParam("memId") String memId) {
		// restController가 아닌데 비동기 방식을 사용하고 싶다면 반드시 @ResponseBody 작성
		// 매개변수 memID 받아오기
		
		Member m = mapper.idCheck(memId);
		// m == null --> 아이디 사용 가능
		// m != null --> 아이디 사용 불가능 (중복)
		if(m != null || memId.equals("")) {
			return 0;
			
		}else {
			return 1;
		}
	}
	
	@RequestMapping("/join.do")
	public String join(Member vo, RedirectAttributes rttr, HttpSession session) {
		// vo에 회원가입 시 받은 정보들이 들어있음(id, pw, nick, name, age, gender, email)
		System.out.println("회원가입 기능 요청");
		
		// 유효성 검사 (1차)
		if(vo.getMemId() == null || vo.getMemId().equals("") ||
				vo.getMemPw() == null || vo.getMemPw().equals("") ||
				vo.getMemName() == null || vo.getMemName().equals("") ||
				vo.getMemAge() == 0 ||
				vo.getMemEmail() == null || vo.getMemEmail().equals("")) {
			// 하나의 매개변수라도 이름이 다르거나 값이 비어있는 경우
			// 회원가입을 할 수 없다
			
			// 실패 시, joinForm.do로 msgType과 msg의 내용을 보내줌
			// msgType : 실패메세지,  msg : "모든 내용을 입력하세요"
			//   - model 유지는 forwarding 방식만 가능하므로 model에 저장해서 보낼 수 없음
			//   - < RedirectAttributes >
			//      - redirect 방식으로 이동할 때 보낼 데이터를 저장하는 객체 (=모델, 일회성)  
			
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요.");
			return "redirect:/joinForm.do";
			
		}else {
			// 회원가입을 시도할 수 있는 부분
			
			vo.setMemProfile("");   // 프로필 값 null로 지정해두기
			int cnt = mapper.join(vo);  // 회원가입 성공 여부 기록 변수 cnt
			
			if(cnt == 1) {
				System.out.println("회원가입 성공!");
				rttr.addFlashAttribute("msgType", "성공 메세지");
				rttr.addFlashAttribute("msg", "회원가입에 성공했습니다!");		
				
				// 회원가입 성공 시 로그인 기능까지 실행하기
				//  - 회원가입에 대한 정보를 session에 넣어줌
				session.setAttribute("mvo", vo);
				
				// 회원가입 후, index.jsp ("/")로 이동
				return "redirect:/";
				
			}else {
				System.out.println("회원가입 실패...");
				rttr.addFlashAttribute("msgType", "실패 메세지");
				rttr.addFlashAttribute("msg", "회원가입에 실패했습니다.");				
				return "redirect:joinForm.do";			
			}
		}	
	}
	
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		
		// sisson에 들어있는 로그 정보 없애기
		session.invalidate();
		
		return "redirect:/";
	}

	
	@RequestMapping("/loginForm.do")
	public String loginForm() {	
		// 로그인 버튼 클릭 시, 로그인 페이지로 이동
		return "member/loginForm";   // loginForm.jsp
	}
	
	
	@RequestMapping("/login.do")
	public String login(Member vo, RedirectAttributes rttr, HttpSession session) {
		
		// 문제
		// mapper에 login이라는 메서드 이름으로 로그인 기능 수행하기
		// 단, 로그인 성공 시 index.jsp 이동 후 "로그인에 성공했습니다" 모달창 띄우기
		// 로그인 실패 시 login.jsp 이동 후 "로그인에 실패했습니다" 모달창 띄우기
		
		Member mvo = mapper.login(vo);   // 로그인 후 해당 회원의 정보를 모두 받아와야 하므로 Member 객체에 저장
		
		if(mvo != null) {
			// 로그인 성공
			System.out.println("로그인 성공!");
			
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "로그인에 성공했습니다!");		
			
			session.setAttribute("mvo", mvo);  // session에 로그인 정보 저장	
			return "redirect:/";
			
		}else {
			// mvo == null : 로그인 실패
			System.out.println("로그인 실패...");
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "로그인에 실패했습니다.");				
			return "redirect:loginForm.do";			
		}
	}
	
	
	@RequestMapping("/updateForm.do")
	public String updateForm() {
		return "member/updateForm";
	}
	
	
	
	@RequestMapping("/update.do")
	public String update(Member member, RedirectAttributes rttr, HttpSession session) {
		System.out.println(member.toString());
				
		// 문제
		// mapper의 update 메서드를 통해 해당 회원의 정보 수정하기
		
		// 조건 1. 하나라도 입력하지 않은 데이터가 있으면 updateForm.jsp로 다시 돌려보내면서 "모든 내용을 입력하세요"모달창 띄우기
		if(member.getMemPw() == null || member.getMemPw().equals("") ||
				member.getMemName() == null || member.getMemName().equals("") ||
				member.getMemAge() == 0 ||
				member.getMemEmail() == null || member.getMemEmail().equals("")) {
		
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요.");
			return "redirect:/updateForm.do";
			
		}else {
		  // 유효성 검사 성공
			
			member.setMemProfile("");
			int cnt = mapper.update(member);
					
			if(cnt != 1) {
				// 조건 2. 회원수정 실패 시 updateForm.jsp로 다시 돌려 보내면서 "회원 정보 수정이 실패했습니다" 모달창 띄우기
				System.out.println("회원수정 실패");
				rttr.addFlashAttribute("msgType", "실패 메세지");
				rttr.addFlashAttribute("msg", "회원정보 수정에 실패했습니다.");				
				return "redirect:/updateForm.do";			
				
			}else {
				// 조건 3. 회원수정 성공 시 index.jsp로 보내고 "회원정보 수정에 성공했습니다" 모달창 띄우기
				System.out.println("회원수정 성공");
				rttr.addFlashAttribute("msgType", "성공 메세지");
				rttr.addFlashAttribute("msg", "회원정보 수정에 성공했습니다.");				
				
				// 로그인한 정보는 session에서 유지되고 있음 -> 회원정보 수정 시 session도 업데이트 해줘야함
				session.setAttribute("mvo", member);	

				return "redirect:/";
				
			
		} }
	}
	
}
