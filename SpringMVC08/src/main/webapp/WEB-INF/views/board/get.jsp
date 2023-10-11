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
    			<table class="table table-bordered table-hover">
    				<tr>
    					<td>번호</td>
    					<td>${vo.idx}</td>
    				</tr>
    				<tr>
    					<td>제목</td>
    					<td><c:out value="${vo.title}" /></td>
    				</tr>
    				<tr>
    					<td>내용</td>
    					<td>
    						<textarea class="form-control" readonly="readonly" rows="10" cols=""><c:out value="${vo.content}" /></textarea>
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
    							<button data-btn="reply" class="btn btn-sm btn-default">답글</button>    						
    						</c:if>
    						<c:if test="${mvo.memID eq vo.memID}">
    						  										<!-- 해당 게시글의 idx를 넘겨준다 (get방식) -->
	    						<button data-btn="modify" class="btn btn-sm btn-default">수정</button>
	    					</c:if>
    						<button data-btn="list" class="btn btn-sm btn-default">목록</button>
    					</td>
    				</tr>
    			</table>
    			
    			<form id="frm" action="" method="get">
    				<input type="hidden" name="idx" value="${vo.idx}" id="idx">
    			</form>
    			
    		</div>
    		<div class="panel-footer">스프링 게시판 - 송은지</div>
  		</div>
	</div>
	
	
	<script type="text/javascript">
		// oneclick 대신 넣은 data-btn / form 태그에 대한 링크처리
		
		// 로딩이 될 때까지 기다렸다가 함수 처리
		$(document).ready(function(){
			$("button").on("click", function(e){
				// button 태그 클릭 시 함수 작동
				
				// #list인 버튼 클릭 시 현재 비어있는 action 값이 cpath/controller/board/list로 이동
				// #reply인 클릭 시 action 값이 cpath/controller/board/reply로 이동					
				var formData = $("#frm"); // form 태그에 대한 정보를 가짐
				// 현재 클릭한 버튼 요소의 값을 가져옴 (ex. reply, modify, list...)
				var btn = $(this).data("btn");
	
				if(btn == "reply"){
					// 답글 버튼 클릭
					formData.attr("action", "${cpath}/board/reply")  // action 값에 해당 경로를 넣는다
				}else if(btn == "modify"){
					formData.attr("action", "${cpath}/board/modify")  // action 값에 해당 경로를 넣는다					
				}else if(btn == "list"){
					formData.attr("action", "${cpath}/board/list")  // action 값에 해당 경로를 넣는다					
					formData.find("#idx").remove();  // formdata가 가진 정보 안에서 #idx를 찾아서 삭제
				}
				formData.submit();  // formData가 가진 form 태그의 submit을 작동시킴
			});  
			
		});  
		
	</script>
</body>
</html>