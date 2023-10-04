<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- jstl 가져오기 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 여러 기능이 있는 함수 가져오기 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 
<!-- Context-Path 값을 내장객체 변수로 저장 -->
<!-- controller라는 context-path를 저장하고 있는 내장객체 : pageContext.request.contextPath -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 

<!-- Spring Security에서 제공하는 태그 라이브러리 -->
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!-- Spring Security에서 제공하는 계정정보 (SecurityContext 안에 저장된 회원정보 가져오기) -->
<!--   - 로그인한 계정 정보 (MemberUser mvo) -->
<c:set var="mvo" value="${SPRING_SECURITY_CONTEXT.authentication.principal}" />
 
<!-- 권한 정보 -->
<c:set var="auth" value="${SPRING_SECURITY_CONTEXT.authentication.authorities}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <!-- 여러 페이지들에 공통적으로 들어가는 것(ex. 메뉴바)을 모아둔 파일 : common -->
 
 	<!-- 메뉴바 -->
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	      <a class="navbar-brand" href="${contextPath}/">Spring</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav">
	      <!-- href(url) 작성 시, "/"를 생략하는 순간 앞에 "/controller/"(contextPath)가 생략되어 있는 것과 같음 -->
	      <!-- 
	      	   < href="/" 사용하고 싶을 때...>
	     		1. "/controller/"
	      		2. 내장객체 변수로 저장한 Context-Path 값을 불러옴 ${contextPath}    
	      		  		
	           < contextPath 값을 아는 방법 > 
	      		1. package 세번째 이름 / 
	      		2. Servers 파일 - server.xml 파일에 path로 저장되어 있음   -->
	        <li class="active"><a href="${contextPath}/">메인</a></li>
	        
	        <li><a href="boardMain.do">게시판</a></li>
	      </ul>
	      
	      
	      
	      <!-- 로그인/회원가입 안 했을 시 나타날 메뉴바 -->
	      <!-- 위에서 가져온 security를 통해 판단 -->
	      <security:authorize access="isAnonymous()">
			  <ul class="nav navbar-nav navbar-right">
		           <li><a href="${contextPath}/loginForm.do"><span class="glyphicon glyphicon-log-in"><span style="margin-left: 5px;">로그인</span></span></a></li>
		           <li><a href="${contextPath}/joinForm.do"><sapn class="glyphicon glyphicon-user"><span style="margin-left: 5px;">회원가입</span></sapn></a></li>		          
		      </ul>
	      </security:authorize>

	      
	      <!-- 로그인/회원가입 했을 시 나타날 메뉴바 -->
	      <security:authorize access="isAuthenticated()">
			  <ul class="nav navbar-nav navbar-right">	  
			  	<li>
			  	
			  		<c:if test="${mvo.member.memProfile ne ''}">
			  		<!-- memProfile이 ''가 아닌 경우(비어있지 않는 경우) 해당 프로필 이미지 띄워줌-->
				  		<img src="${contextPath}/resources/upload/${mvo.member.memProfile}" style="width:50px; heigth:50px;" class="img-circle">				  					  		
			  		</c:if>
			  		
			  		<c:if test="${mvo.member.memProfile eq ''}">
			  		<!-- memProfile이 ''일 경우(비어있는 경우) 기본 프로필 이미지를 띄워줌 -->
				  		<img src="${contextPath}/resources/images/default.png" style="width:50px; heigth:50px;" class="img-circle">			  		
			  		</c:if>
			  		
			  		${mvo.member.memName}님 Welcome!
			  		
			  		[
			  			<!-- 권한 정보 띄우기  -->
			  			<!-- 회원이 가진 권한의 리스트만큼 반복 돌면서 꺼내기 -->
<%-- 			  			<c:forEach items="${mvo.authList}" var="auth">
			  				<!-- mvo.authList만큼 반복 돌면서 auth에 담음 -->
			  				<c:choose>
			  					<c:when test="${auth.auth eq 'ROLE_USER'}">
			  					<!-- auth vo 안의 auth를 가져왔는데 권한이 'ROLE_USER'와 같다면 -->
			  						U
			  					</c:when>
			  					<c:when test="${auth.auth eq 'ROLE_MANAGER'}">
			  						M
			  					</c:when>
			  					<c:when test="${auth.auth eq 'ROLE_ADMIN'}">
			  						A
			  					</c:when>
			  				</c:choose>
			  			</c:forEach> --%>
			  			<security:authorize access="hasRole('ROLE_USER')"> 
			  			 <!-- hsaRole 해당 권한을 가지고 있는지 확인하는 메서드 -->
			  				U
			  			</security:authorize>
			  			<security:authorize access="hasRole('ROLE_MANAGER')"> 
			  				M
			  			</security:authorize>
			  			<security:authorize access="hasRole('ROLE_ADMIN')"> 
			  				A
			  			</security:authorize>
			  		]
			  		
			  		
			  	</li>		
		        <li><a href="${contextPath}/updateForm.do"><span class="glyphicon glyphicon-pencil">회원정보수정</span></a></li>
		        <li><a href="${contextPath}/imageForm.do"><span class="glyphicon glyphicon-upload">프로필사진등록</span></a></li>
		        <li><a href="javascript:logout()"><span class="glyphicon glyphicon-log-out">로그아웃</span></a></li>
		      </ul>
	     </security:authorize>
	      
	      
	    </div>
	  </div>
	</nav>
	<!--  보안 1차 : 비밀번호 토큰(csrf)을 주고 받아서 위변조 방지 - 2차 : 비밀번호 암호화 - 3차 : Spring 보안 방식 -->
	
	<script type="text/javascript">
			// csrf 토큰값 가져오기
			var csrfHeaderName = "${_csrf.headerName}";
			var csrfTokenValue = "${_csrf.token}";
			
		function logout() {
				// 비동기 방식 - 페이지 이동 x
			$.ajax({ 
				url : "${contextPath}/logout",
				type : "post", // post 방식 --> 보안 설정 시 만들어놓은 로그아웃 기능이 실행됨
				beforeSend : function(xhr){
					xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
				},
				success : function(){
					location.href="${contextPath}/";  // 비동기 방식에서 페이지 이동하는 방법
				},
				error : function(){
					alert("error!");
				}
			});
		}
	
	
	
	</script>
 

</body>
</html>