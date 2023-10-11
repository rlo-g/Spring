package kr.spring.mapper;

import java.util.List;

import kr.spring.entity.BookVO;

public interface BookMapper {

	List<BookVO> getLists();

	void bookInsert(BookVO book);

	void bookDelete(int num);

	void bookUpdate(BookVO vo);

	BookVO bookContent(int num);

}
