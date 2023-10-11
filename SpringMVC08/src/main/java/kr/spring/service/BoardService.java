package kr.spring.service;

import java.util.List;

import kr.spring.entity.Board;
import kr.spring.entity.Member;

public interface BoardService {
	// Service 클래스에서 사용할 기능의 이름을 정의하는 인터페이스

	
	// 게시글 전체 목록 보기 기능
	// controller가 게시글 목록 보기 기능을 요청하면 service는 이를 수행 후 controller에게 data(게시글 목록)를 돌려줘야 함
	public List<Board> getList();

	public Member login(Member vo);
	
	public void register(Board vo);

	public Board get(int idx);

	public void modify(Board vo);

	public void remove(int idx);

	public void reply(Board vo);


}
