<%@page import="org.springframework.web.servlet.support.RequestContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <!-- jstl 가져오기 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 여러 기능이 있는 함수 가져오기 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 부트스트랩 받아온 것 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
	<!-- controller와 resolver를 거쳐 jsp 파일로 오게됨 -->
	
	<div class="container">
 	 	<h2>Spring MVC01</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">Board</div>
    		<div class="panel-body">
				<table class="table table-hover">
				<!-- class="table" -> 부트스트램에 존재하는 테이블 css 
							"table table-borderd" : 각각 선이 생김 -->
					<tr class="active">
						<!-- 색상 변경 : 부트스트랩에 존재하지 않는 걸 쓰려면 css로 들어가야함-->
						<td>번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
					</tr>
					<!-- model 안에 있는 list를 jstl을 활용하여 출력하기
				 		  -> 어떻게 가져오지?
				 		  	  - model은 사실 request 영역 안에 있다!! -->
				 	
				 	<c:forEach items="${list}" var="vo" varStatus="i">
				 		<tr>
				 			<!-- <td>${vo.idx }</td> : 사용 안 함(게시글 고유 번호)-->
				 			<td>${i.count}</td>
				 			
				 			<!-- 쿼리스트링으로 게시글의 고유번호(idx)를 boardContent.do로 보내줌
				 				   -> 쿼리스트링 대신 '/키값'으로 보냄 : PathVariable 방식 (동기 방식)
				 				   	1. 보안 강력
				 				   	2. 네임값을 정하지 않으므로 url 간결해짐 
				 				   	문제점 : 경로문제 발생 (빠져나갈때 ../ ) -->
				 			<td><a href="boardContent.do/${vo.idx}">${vo.title}</a></td>
				 			<td>${vo.writer}</td>
	    					<!-- split 
	    						: vo.indate를 띄어쓰기 기준으로 잘라내고 (잘린 기준으로 배열 생성) 
	    						그 앞에 있는 0번째 것을 쓰겠다 -->
	    					<td>${fn:split(vo.indate, " ")[0] }</td>				 			
				 			<td>${vo.count}</td>
				 		</tr>
				 	</c:forEach>
				</table>
				
				<!-- 부트스트랩 버튼 class="btn 색상 크기작게"  -->
				<a href="boardForm.do" class="btn btn-primary btn-sm">글쓰기</a>
				
				<!-- 스프링은 무조건 ds를 통해 컨트롤러를 들렸다가 jsp로 가야한다 -->
			</div>
    		<div class="panel-footer">스프링 게시판 - 송은지</div>
  		</div>
	</div>
</body>
</html>