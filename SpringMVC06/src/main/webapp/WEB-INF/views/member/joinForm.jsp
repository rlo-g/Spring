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
 	 	<h2>Spring MVC06</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">Board</div>
    		<div class="panel-body">
    		
    		<form action="${contextPath}/join.do" method="post">
    			<!-- 
    				이제는 서버로 단순히 회원가입 정보만 전달하는 것이 아니라
    				서버에서 발행한 토큰도 전달해야 회원가입이 가능함
    			 -->
    			                        <!-- 내부적으로 있는 토큰 사용 -->
    			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    			<!-- db로 넘어갈 비밀번호 input 태그 -->
    			<!-- 두개의 pw1, pw2를 memPW에 담아올 수 없음 > hidden 타입의 태그 생성 value 값이 없음 > 두 개의 비밀번호가 일치했을 때 value 값이 채워짐  -->
    			<input type="hidden" name="memPw" id="memPw" value="">
    			
    			<table class="table table-bordered" style="text-align:center; border:1px solid #dddddd">
    				<tr>
    					<td style="width:110px; vertical-align:middle;">아이디</td>
    					<!-- required="required" : 무조건 입력하도록..(유효성 검사) -->
    					<td><input type="text" required="required" name="memId" id="memId" class = "form-control" maxlength="20" placeholder="아이디를 입력하세요"></td>
    					
    					<td style="width:110px;"><button type="button" onclick="idCheck()" class="btn btn-sm btn-default">중복확인</button></td>
    				</tr>
    				
    				<tr>
    					<td style="width:110px; vertical-align:middle;">비밀번호</td>
    					<td colspan="2"><input required="required" type="password" onkeyup="pwCheck()" name="memPW1" id="memPW1" class = "form-control" maxlength="20" placeholder="비밀번호를 입력하세요"></td>
    				</tr>
    				
    				<tr>
    					<td style="width:110px; vertical-align:middle;">비밀번호 확인</td>
    					<td colspan="2"><input type="password" required="required" onkeyup="pwCheck()" name="memPW2" id="memPW2" class = "form-control" maxlength="20" placeholder="비밀번호를 확인하세요"></td>
    				</tr>
    				
    				<tr>
    					<td style="width:110px; vertical-align:middle;">이름</td>
    					<td colspan="2"><input type="text" required="required" name="memName" id="memName" class = "form-control" maxlength="20" placeholder="이름을 입력하세요"></td>
    				</tr>
    				
    				<tr>
    					<td style="width:110px; vertical-align:middle;">나이</td>
    					<td colspan="2"><input type="number" required="required" name="memAge" id="memAge" class = "form-control" maxlength="20" placeholder="나이를 입력하세요"></td>
    				</tr>
    				
    				<tr>
		               <td style="width: 110px; vertical-align: middle;">성별</td>
		               <td colspan="2">
		                  <div class="form-group" style="text-align: center; margin: 0 auto;">
		                     <div class="btn-group" data-toggle="buttons">
		                        <label class="btn btn-default active">
		                           <input type="radio" id="memGender" name="memGender" autocomplete="off" value="남자" checked="checked"> 남자    
		                        </label>
		                        <label class="btn btn-default">
		                           <input type="radio" id="memGender" name="memGender" autocomplete="off" value="여자" > 여자
		                        </label>
		                     </div>
		                  </div>
		               </td>
		            </tr>
		            
		            <tr>
    					<td style="width:110px; vertical-align:middle;">이메일</td>
    					<td colspan="2"><input type="email" required="required" name="memEmail" id="memEmail" class = "form-control" maxlength="50" placeholder="이메일을 입력하세요"></td>
    				</tr>
					
					<!-- 권한 체크박스 -->
					<tr>
    					<td style="width:110px; vertical-align:middle;">권한</td>
						<td colspan="2">
										<!-- member 테이블 - 권한(list 배열) 0번째에 있는 Auth 테이블의 auth에 값을 넣겠다 
											  :	[MEMBER] - 권한 - [AUTH] - auth  -->
							<input type="checkbox" name="authList[0].auth" value="ROLE_USER"> ROLE_USER
							<input type="checkbox" name="authList[1].auth" value="ROLE_MANAGER"> ROLE_MANAGER
							<input type="checkbox" name="authList[2].auth" value="ROLE_ADMIN"> ROLE_ADMIN
						</td>
					
					</tr>
					
					<tr>
						<td colspan="3" style="position: relative;">
							<span id="pwMessage" style="color:red; position: absolute; top: 50%; transform: translateY(-50%); right: 115px; text-align: right;"></span>
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
	
		function idCheck() {
			
			var memId = $("#memId").val();  // iuput에 쓴 값을 가져옴

			// 아이디 중복 체크, 자동완성 등은 비동기 방식 많이 사용
			// 입력할 때마다 서버로부터 값을 받아와서 새로고침 없이 바로 보여줌
			$.ajax({
				url : "${contextPath}/idCheck.do",  // el, jstl은 java 언어가 아님 (표현식)
				// get 방식 : 데이터베이스에서 값을 select 할 때 사용 (게시글 전체보기, 중복확인 등)
				// insert, update 등은 post 방식 사용
				type : "get",
				data : {"memId":memId},
				success : function(data){
					// url에 보낸 data의 결과값(data)을 받아옴					
					// 중복유무 확인 --> data = 1 - 사용가능, 0 - 사용불가능
					if(data == 1){
						$("#checkMessage").text("사용 가능한 아이디입니다.");   // 선택한 요소에 글자를 넣어줌
						$("#checkType").attr("class", "modal-content panel-success");
					
					}else{
						$("#checkMessage").text("사용할 수 없는 아이디입니다.");
						$("#checkType").attr("class", "modal-content panel-warning");
					}
					$("#myModal").modal("show");
				},
				error : function(){ alert("error"); }
			});
	
		}
	
		function pwCheck() {
			
			var memPW1 = $("#memPW1").val();
			var memPW2 = $("#memPW2").val();
			
			if (memPW1 != memPW2) {
				$("#pwMessage").html("비밀번호 확인이 일치하지 않습니다.");
			
			}else{
				$("#memPw").val(memPW1);   // memPW1과 memPW2 일치 시, hidden input 태그 value안에 비밀번호 저장
				$("#pwMessage").html("");
				
			}
		}
		
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