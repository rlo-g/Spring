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
	
	<% pageContext.setAttribute("newLineChar", "\n"); %>
	
	<div class="container">
 	 	<h2>Spring MVC01</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">Board</div>
    		<div class="panel-body">
	    			<table class="table">
	    				<tr>
	    					<td>제목</td>
	    					<td>${vo.title }</td>
	    				</tr>
	    				<tr>
	    					<td>내용</td>
	    					<td>
	    						<!-- taglib으로 불러온 함수 fn -->
	    						<!-- replace : 특정 키워드 발견 시, 키워드를 변경해줌-->
	    						<!-- vo.content를 불러오는데 위에서 설정한 newLineChar이 발견된다면 
	    							그 부분은 <br>로 바꿔준다 -->
	    						${fn:replace(vo.content, newLineChar, "<br>") }
	    					</td>
	    				</tr>
	    				<tr>
	    					<td>작성자</td>
	    					<td>${vo.writer }</td>
	    				</tr>
	    				<tr>
	    					<td>작성일</td>
	    					<!-- split 
	    						: vo.indate를 띄어쓰기 기준으로 잘라내고 (잘린 기준으로 배열 생성) 
	    						그 앞에 있는 0번째 것을 쓰겠다 -->
	    					<td>${fn:split(vo.indate, " ")[0] }</td>
	    				</tr>
	    				<tr>
	    					<td colspan="2" align="center">
	    						<a href="../boardUpdateForm.do/${vo.idx}" class="btn btn-success btn-sm">수정</a>	
	    						<!-- 게시글 삭제후, 게시글 목록으로 이동하기 
	    							: PV 방식 사용 시 한 칸(파일) 더 들어오게 되므로 빠져나가야 함 (../) -->
	    						<a href="../boardDelete.do/${vo.idx}" class="btn btn-warning btn-sm">삭제</a>
	    						<a href="../boardList.do" class="btn btn-info btn-sm">목록</a>
	    					</td>
	    				</tr>   				
	    			</table>
    		</div>
    		<div class="panel-footer">스프링 게시판 - 송은지</div>
  		</div>
	</div>
</body>
</html>