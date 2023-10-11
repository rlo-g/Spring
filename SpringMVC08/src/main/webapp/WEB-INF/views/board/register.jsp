<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <!-- jstl 가져오기 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 여러 기능이 있는 함수 가져오기 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 부트스트랩 받아온 것 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- ContextPath (cpath) : 시작경로를 가져옴 -->
<c:set var="cpath" value="${pageContext.request.contextPath}"/>

</head>
<body>
	<!-- controller와 resolver를 거쳐 jsp 파일로 오게됨 -->
	
	<div class="container">
 	 	<h2>Spring MVC08</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">Board</div>
    		
    		<div class="panel-body">
    			<form action="${cpath}/board/register" method="post">
    			
    			  <!--  
    			  		문제
    			  		post 방식의 register요청이 들어왔을 때
    			  		BoardController에서 BoardService의 regisger 메서드를 통해
    			  		게시글 입력기능을 수행하고 list.jsp로 이동 
    			  		 ==> jsp -> Controller -> Seivice -> Mapper          -->
    			
    			
    			
    				 <!-- 로그인한 회원의 정보(session에 저장되어 있음)를 숨겨서 보내줌 -->
    				<input type="hidden" name="memID" value="${mvo.memID}">
    			
    				<div class="form-group">
    					<label>제목</label>
    					<input type="text" name="title" class="form-control">    					
    				</div>
    				<div class="form-group">
    					<label>내용</label>
    					<textarea name="content" class="form-control" rows="10" cols=""></textarea>    					
    				</div>
    				<div class="form-group">
    					<label>작성자</label>
    					<input type="text" name="writer" class="form-control" value="${mvo.memName}" readonly="readonly">    					
    				</div>
    				<button type="reset" class="btn btn-default btn-sm pull-right">취소</button>
    				<button type="submit" class="btn btn-default btn-sm pull-right">등록</button>
    				<button type="button" onclick="location.href='${cpath}/board/list'" class="btn btn-sm btn-default">목록</button>
    			</form>
    		</div>
    		<div class="panel-footer">스프링 게시판 - 송은지</div>
  		</div>
	</div>
	
	
	<script type="text/javascript">

	</script>
</body>
</html>