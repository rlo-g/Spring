package kr.spring.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;


import kr.spring.entity.Board;
import kr.spring.entity.Member;

@Mapper  // MyBatis가 이 interface를 찾을 수 있도록 달아주는 부분
public interface MemberMapper {

	public Member idCheck(String memId);

	public int join(Member vo);

	public Member login(Member vo);
	
	

	
	

}
