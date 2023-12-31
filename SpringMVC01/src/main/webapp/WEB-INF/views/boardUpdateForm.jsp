<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <!-- jstl 가져오기 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    		
    			<!-- boardUpdate.do로 게시글의 기준점(idx)를 보내줘야 함
    				사용자가 게시글 번호를 입력하지 않아도 보낼 수 있음 (숨겨서, hidden) 
    				 -> PV 방식 사용 시 한 칸 빠져나가야 함 (../) -->
    			<form action="../boardUpdate.do" method="post">
    				<!-- boardUpdate.do로 요청을 하게 되면,
    				     입력한 정보로 idx와 일치하는 게시글을 수정하고 게시글 목록페이지로 이동시키기  -->
    				<input type="hidden" name="idx" value="${vo.idx }">
	    			<table class="table">
	    				<tr>
	    				<!-- 수정하고 싶은 기존 게시글을 받아옴 (vo로 넘겨진 게시글) -->
	    					<td>제목</td>
	    					<td><input value="${vo.title}" type="text" name="title" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td>내용</td>
	    					<!-- textarea는 value값 입력 불가, 내용란에 공백이 있으면 안 됨!!! -->
	    					<td><textarea class="form-control" name="content" rows="7" >${vo.content}</textarea></td>
	    				</tr>
	    				<tr>
	    					<td>작성자</td>
	    					<td><input value="${vo.writer}" type="text" name="writer" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td colspan="2" align="center">
	    						<button type="submit" class="btn btn-success btn-sm">수정</button>
	    						<button type="reset" class="btn btn-warning btn-sm">취소</button>
	    						<a href="../boardList.do" class="btn btn-info btn-sm">목록</button>
	    					</td>
	    				</tr>   				
	    			</table>
    			</form>
    		</div>
    		<div class="panel-footer">스프링 게시판 - 송은지</div>
  		</div>
	</div>
</body>
</html>