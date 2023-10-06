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
 	 	<h2>Spring MVC07</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">Board</div>
    		
    		<div class="panel-body">
    			<table class="table table-bordered table-hover">
    				<tr>
    					<td>제목</td>
    					<td>${vo.title }</td>
    				</tr>
    				<tr>
    					<td>내용</td>
    					<td>
    						<textarea class="form-control" readonly="readonly" rows="10" cols="">${vo.content}</textarea>
    					</td>
    				</tr>
    				<tr>
    					<td>작성자</td>
    					<td>${vo.writer }</td>
    				</tr>
    				
    				<tr>
    					<td colspan="2" style="text-align:center;">
    						<c:if test="${not empty mvo}">
    									<!-- 로그인 한 상황 -->
    							<button onclick="location.href='${cpath}/board/reply?idx=${vo.idx}'" class="btn btn-sm btn-default">댓글</button>    						
    						</c:if>
    						<c:if test="${mvo.memID eq vo.memID}">
    						  										<!-- 해당 게시글의 idx를 넘겨준다 (get방식) -->
	    						<button onclick="location.href='${cpath}/board/modify?idx=${vo.idx}'" class="btn btn-sm btn-default">수정</button>
	    					</c:if>
    						<button onclick="location.href='${cpath}/board/list'" class="btn btn-sm btn-default">목록</button>
    					</td>
    				</tr>
    			</table>
    		</div>
    		<div class="panel-footer">스프링 게시판 - 송은지</div>
  		</div>
	</div>
	
	
	<script type="text/javascript">

	</script>
</body>
</html>