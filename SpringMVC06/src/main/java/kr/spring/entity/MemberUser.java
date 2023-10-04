package kr.spring.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;


@Data
public class MemberUser extends User{
	// Spring Security에 Member 객체를 담을 수 있게 해주는 (변환)클래스
	/* ${mvo} > MemberUeser > Member > 각 정보 */
	
	
	private Member member; // MemberUser안에 member를 담아두고 쓰겠다
	
	// 생성자 생성
	public MemberUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		// 컬렉션 : 배열 형태 --> 배열형태로 권한을 받아오려고
		
		// 부모(user)에 있는 생성자
		// MemberUser 객체 생성 시 아이디, 비밀번호 , 권한을 입력받는다
		// - 실제로 우리는 이 생성자를 사용하지 않음
		//  - 하지만 추상 메서드라서 어쩔 수 없이 구현해야됨.. 
		//  - 아래 세개의 정보만 쓸거면 사용해도 됨 (회원 정보 7개)
		super(username, password, authorities);	
	}
	
	
	// 실제로 사용할 생성자
	public MemberUser(Member mvo) {
		// User 클래스(부모)의 생성자를 사용해서 구현한다
		// 생성자 (아아디, 비밀번호, 권한을 넣어준다)
		super(mvo.getMemId(), mvo.getMemPw(),
				/* User 클래스의 생성자에 사용하는 권한정보는 
				 *  Collection<SipmleGrandAuthority>로 변경해서 넣어야함 */
				mvo.getAuthList().stream()  /* 바이트로 변경 */
				/* mvo 안에 있는 권한의 배열을 가져와서 바이트로 바꿔서 컬렉션 형태의 map으로 묶어준다
				 *   - List<Auth> --> Collection 안에 들어갈 수 있게 변경*/
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList()) /* 최종 컬렉션 리스트로 변경*/
				);
		// 내가 가진 필드에 있는 멤버에 멤버를 넣어주겠다
		//  --> 멤버의 나머지 정보들(name, emial ..)은 member에 담아줌
		this.member = mvo;
	}
}
