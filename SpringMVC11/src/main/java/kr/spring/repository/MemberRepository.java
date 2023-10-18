package kr.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.spring.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	// PK - 회원 아이디 --> String
}
