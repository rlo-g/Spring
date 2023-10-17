<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!-- jstl 가져오기 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 여러 기능이 있는 함수 가져오기 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 날짜 가져오기 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- ContextPath (cpath) : 시작경로를 가져옴 -->
<c:set var="cpath" value="${pageContext.request.contextPath}"/>    
    
    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
 
  
  <div class="card">
    <div class="card-header">
		<div class="jumbotron jumbotron-fluid">
		  <div class="container">
		    <h1>Spring MVC10</h1>
		    <p>Java, HTML, CSS, JavaScript, JSP&Servlet, Spring FrameWork, Spring Boot</p>
		  </div>
		</div>    
    </div>
    <div class="card-body">
    	<div class="row">
    		<div class="col-lg-2">
    			<!-- clo-lg : 비율의 총 합이 12가 되어야 함 -->
    			<div class="card" style="min-height: 500px; max-height: 1000px;">
    				<!-- 카드 형식의 테두리 -->
    				<div class="card-body">
    					<h4 class="card-title" style="text-align:center;">GUEST</h4>
    					<p class="card-text" style="text-align:center;">회원님 Welcome!</p>
    					<form action="">
    						<div class="form-group">
    							<label for="memID" style="font-size: 13px">ID</label>
    							<input type="text" class="form-control" name="memID" id="memID">
    						</div>					<!-- 한줄 다 채움 -->
    						
    						<div class="form-group">
    							<label for="memPwd" style="font-size: 13px">PASSWORD</label>
    							<input type="password" class="form-control" name="memPwd" id="memPwd">
    						</div>
    						
    						<button type="submit" class="form-control btn btn-sm btn-light">Login</button>
    					</form>
    				</div>   			
    			</div>
    		</div>
    		
    		<div class="col-lg-5">
    			<div class="card" style="min-height: 500px; max-height: 1000px;">
    				<div class="card-body">
    					<table class="table table-bordered table-hover" style="font-size:15px">   					
    						<thead>
    							<th>번호</th>
    							<th>제목</th>
    							<th>작성일</th>
    							<th>조회수</th>
    						</thead>
    						<tbody> <!-- tbody 사용 시 제어할 때(js) 편함 -->
    							<c:forEach var="vo" items="${list}" varStatus="i">
    								<tr>
    									<td>${i.count}</td>  <!-- 반복 순서 번호(board 테이블의 count 아님) -->
    									<td><a href="${vo.idx}" style="color:black;">${vo.title}</a></td>
    									<td><fmt:formatDate value="${vo.indate}" pattern="yyyy-MM-dd"/></td>
    									<td>${vo.count}</td>
    								</tr>
    							</c:forEach>   						
    						</tbody>
    					</table>
    				</div>
    			</div>   		
    		</div>
    		
    		<div class="col-lg-5">
    			<div class="card" style="min-height: 500px; max-height: 1000px;">
    				<!-- 카드 형식의 테두리 -->
    				<div class="card-body">
    					<form id="regForm" action="${cpath}/register" method="post">
    						<div class="form-group">
    							<label for="title" style="font-size: 14px">제목</label>
    							<input type="text" class="form-control" name="title" id="title" placeholder="Enter Title">
    						</div>					<!-- 한줄 다 채움 -->
    						
    						<div class="form-group">
    							<label for="content" style="font-size: 14px">내용</label>
    							<textarea class="form-control" name="content" id="content" rows="7" placeholder="Enter Content"></textarea>
    						</div>
    						<div class="form-group">
    							<label for="writer" style="font-size: 14px">작성자</label>
    							<input type="text" class="form-control" name="writer" id="writer" placeholder="Enter Writer">
    						</div>
    						<div style="text-align:center;">
	    						<button type="button" data-oper="register" class="btn btn-sm btn-secondary">등록</button>
	    						<button type="button" data-oper="reset" class="btn btn-sm btn-light">취소</button>  						
    						</div>    						
    					</form>   				
    				</div>
    			</div>    		
    		</div>
    		
    	</div> 
    </div> 
    <div class="card-footer">스프링 - 송은지</div>
  </div>
  
  

  <script type="text/javascript">
	$(document).ready(function(){
		
		// #regForm의 요소 정보 가져오기
		var regForm = $("#regForm");
		
		
		// 모든 button 태그의 클릭을 감지하면 함수 적용
		$("button").on("click", function(){
			
			var oper = $(this).data("oper");  // 클릭한 요소 "oper" 자체의 데이터 값 가져오기
			if(oper == "register") {
				// 등록 기능
				// button 클릭 시 data-oper가 register라면 regForm 내용들을 submit()
				regForm.submit(); 
			}else if(oper == "reset"){
				// 리셋 기능
				regFrom[0].reset();
			}
		});
		
		
		// a 태그 클릭 감지하면 함수 적용
		$("a").on("click", function(e){
			e.preventDefault();  // a태그의 href 적용 안 시킴
			var idx = $(this).attr("href");  // 클릭한 해당 요소의 href 속성값 가져오기
			
			$.ajax({ // 비동기 방식 요청
				url : "${cpath}/get",   // 이동 url
				type : "get",    // 요청방식 : get
				data : {"idx":idx},    // 보내줄 데이터
				dataType : "json",   // 게시글 받아올 방식
				success : printBoard,   // 성공 시 printBoard 함수 실행
				error : function(){alert("error");}
			});
		});
				
	});
	
	
	function printBoard(vo){
		
		var regForm = $("#regForm");
		regForm.find("#title").val(vo.title);   // #regForm 태그 안의 "#title"을 찾아서 value 값 변경
		regForm.find("#content").val(vo.content); 
		regForm.find("#writer").val(vo.writer);  
		
		regForm.find("input").attr("readonly", true); // #regForm 태그 안의 input 태그를 찾아서 속성값 변경(추가)
		regForm.find("textarea").attr("readonly", true);
	}
	
  </script>
</body>
</html>