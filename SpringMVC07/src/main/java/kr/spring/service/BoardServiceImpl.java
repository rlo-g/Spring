package kr.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.entity.Board;
import kr.spring.entity.Member;
import kr.spring.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	// BoardService에서 만들어 놓은 추상메서드를 구현해주는 곳
	// controller의 역할은 단순하게 요청/조종 
	//   --> 해당되는 요청이 들어오면 기능은 service에서 행함 (Mapper를 통해서.. - Mapper에게 요청하여 db에 값을 넣거나 꺼내옴)

	@Autowired
	private BoardMapper mapper;
	
	
	@Override
	public List<Board> getList() {
		// interface에서 생성한 메서드를 구현
		// 게시글 전체목록 가져오기 기능
		
		List<Board> list = mapper.getList();
		
		return list;
	}


	@Override
	public Member login(Member vo) {
		// BoardService에서 생성한 메서드 구현
		//  - mapper에게 로그인 기능 요청 (DB 연결)
		Member mvo = mapper.login(vo);
		
		return mvo; // 받아온 데이터를 controller에게 돌려줌
	}


	@Override
	public void register(Board vo) {
		// 게시글 작성
		// 돌려줄 게 없으므로 타입 void
		mapper.insertSelectKey(vo);

	}


	@Override
	public Board get(int idx) {
		// mapper에게 해당 idx의 게시글을 요청
		Board vo = mapper.read(idx);
		return vo;
	}


	@Override
	public void modify(Board vo) {
		// 게시글 수정
		mapper.update(vo);
		
	}


	@Override
	public void remove(int idx) {
		// 게시글 삭제
		mapper.delete(idx);
	
	}


	@Override
	public void reply(Board vo) {
		// 댓글 만들기
		//  vo : 부모글 번호, 로그인 id, 제목, 답글 작성자 이름
		
		// 부모글 정보 가져오기
		Board parent = mapper.read(vo.getIdx()); // vo가 가지고 있는 idx를 통해 부모(원본)글의 정보를 가져옴
		
		// 부모글의 boardGroup 값을 답글 정보(vo)에 저장하기
		vo.setBoardGroup(parent.getBoardGroup());
		
		// vo가 갖게될 시퀀스와 레벨은 부모 글의 시퀀스, 레벨 값에서 +1
		vo.setBoardSequence(parent.getBoardSequence()+ 1);
		vo.setBoardLevel(parent.getBoardLevel()+1);
		
		// 현재 작성하는 댓글을 제외하고 기존 같은 그룹에 있는 댓글의 시퀀스 값을 1씩 올려줘야 한다
		mapper.replySeqUpdate(parent);
		
		// 답변 저장
		mapper.replyInsert(vo);
		
	}

	
	
}
