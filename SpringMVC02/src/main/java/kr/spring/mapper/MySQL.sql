-- SQL 문장 작성 --

-- 게시판 --
CREATE TABLE BOARD(
	-- VO에 있는 필드명과 테이블의 컬럼명이 똑같아야함 --
	
	-- NUMBER(oracle) --> int(MySQL) --
	-- AUTO_INCREMENT : 값을 안 넣어도 알아서 1씩 증가해서 들어감 (oracle - SEQUENCE) --
	IDX int NOT NULL AUTO_INCREMENT, 
	TITLE VARCHAR(100) NOT NULL,
	CONTENT VARCHAR(2000) NOT NULL,
	WRITER VARCHAR(30) NOT NULL,
 	-- DATE(oracle) --> DATETIME(MySQL) 디폴트로 INSERT 당시의 시간 값이 들어감 --
	INDATE DATETIME DEFAULT NOW(),
	COUNT int DEFAULT 0,
	
	PRIMARY KEY(IDX)  -- IDX를 PK로 --
	); 
	
	
INSERT INTO BOARD(TITLE, CONTENT, WRITER) VALUES('안녕하세요?','반갑습니다~','햄미');
INSERT INTO BOARD(TITLE, CONTENT, WRITER) VALUES('오늘은 수요일','주말 언제 오지?','멈뭄미');
INSERT INTO BOARD(TITLE, CONTENT, WRITER) VALUES('집가고싶다','ㅈㄱㄴ','애옹');
INSERT INTO BOARD(TITLE, CONTENT, WRITER) VALUES('배고프다','흠...','햄미');
INSERT INTO BOARD(TITLE, CONTENT, WRITER) VALUES('Hihihihi','반갑습니다~','햄미');

SELECT * FROM BOARD;