package kr.spring.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor // controller가 요청 시 vo로 묶어줌
@Data
@AllArgsConstructor
@ToString
public class Board {

	private int idx;
	private String memID;
	private String memPW;
	private String title;
	private String content;
	private String writer;
	private Date indate;
	private int count;
	
	private int boardGroup;
	private int boardSequence;
	private int boardLevel;
	private int boardAvailable;
}
