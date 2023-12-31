<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <!-- jstl 가져오기 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 여러 기능이 있는 함수 가져오기 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- contextPath 저장 -->
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

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
	
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container">
 	 	<h2>Spring MVC03</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">Board</div>
    		<div class="panel-body">
    		
    	   <!-- 파일 업로드 시, image/video/pdf 등의 파일을 보내기 위해서 인코딩 타입 변경 -->
    		<form action="${contextPath}/imageUpdate.do" method="post" enctype="multipart/form-data">
    			
    			<table class="table table-bordered" style="text-align:center; border:1px solid #dddddd">
    				<tr>
    					<td style="width:110px; vertical-align:middle;">아이디</td>
    					<!-- 로그인한 회원의 아이디 출력하기 -->
    					<td>${mvo.memId}</td>
    					
    				</tr>
    				
    				<tr>
    					<td style="width:110px; vertical-align:middle;">프로필 사진<br>업로드</td>
    					<td colspan="2">
    						<span class="btn btn-default">
    							이미지를 업로드하세요.
    							<input type="file" name="memProfile">
    						</span>
    					</td>
    				</tr>
    				
					
					<tr>
						<td colspan="2" style="position: relative;">
														
							<input type="submit" class ="btn btn-default btn-sm pull-right" value="취소">
							<input type="submit" class ="btn btn-default btn-sm pull-right" value="등록">
						</td>
					</tr>
    				
    			</table>    		
    		</form>
    		</div>
    		<div class="panel-footer">스프링 게시판 - 송은지</div>
  		</div>
	</div>
	
	
	
  	<!-- Modal (idCheck) -->
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog modal-sm">
	      
	      <div class="modal-content" id="checkType">
	        <div class="modal-header panel-heading">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">메세지 확인</h4>
	        </div>
	        <div class="modal-body">
	          <p id="checkMessage"></p>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div>      
	    </div>
	  </div>	
	  
  	<!-- Modal (join error)-->
	  <div class="modal fade" id="myMessage" role="dialog">
	    <div class="modal-dialog modal-sm">
	      
	      <div class="modal-content" id="messageType">
	        <div class="modal-header panel-heading">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <!--  memberController에서 회원가입 실패시 msgType과 msg를 보냄  -->
	          <h4 class="modal-title">${msgType}</h4>
	        </div>
	        <div class="modal-body">
	          <p id="">${msg}</p>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div>      
	    </div>
	  </div>	
	
	
	
	
	<script type="text/javascript">	
		$(document).ready(function(){
			// .ready - 페이지가 다 로딩될 때까지 기다렸다가 안에 있는 기능을 실행하겠다
			
			if(${not empty msgType}){
				// msgType이 비어있지 않을 경우 (회원가입 실패) - el식
				if(${msgType eq "실패 메세지"}) {
					// msgType이 "실패 메세지"일 경우
					$("#messageType").attr("class", "modal-content panel-warning");
				}
				$("#myMessage").modal("show");
			}
		});  
		
		
		
		
	</script>
	
	
	
</body>
</html>