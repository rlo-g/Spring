package kr.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.spring.entity.CustomUser;
import kr.spring.entity.Member;
import kr.spring.repository.MemberRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private MemberRepository memberRepository;  // memberMapper 역할
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 로그인
		
		Member member = memberRepository.findById(username).get();  // 해당 회원 id 기준으로 찾아옴
		
		if(member == null) {
			throw new UsernameNotFoundException(username+"없음");
		}
		
		
		return new CustomUser(member); // UserDetails 형태로 반환해야 하므로 CustomerUser로 감싸줌
	}

}
