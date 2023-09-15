<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- jstl 가져오기 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 여러 기능이 있는 함수 가져오기 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 
<!-- Context-Path 값을 내장객체 변수로 저장 -->
<!-- controller라는 context-path를 저장하고 있는 내장객체 : pageContext.request.contextPath -->
<c:set var="contextPath" value="${pageContext.request.contextPath }" /> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <!-- 여러 페이지들에 공통적으로 들어가는 것(ex. 메뉴바)을 모아둔 파일 : common -->
 
 	<!-- 메뉴바 -->
	<nav class="navbar navbar-inverse">
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
	      
		  <ul class="nav navbar-nav navbar-right">
	         <li class="dropdown">
	            <a class="dropdown-toggle" data-toggle="dropdown" href="#">접속하기 <span class="caret"></span></a>
	            <ul class="dropdown-menu">
	              <li><a href="#">로그인</a></li>
	              <li><a href="${contextPath}/joinForm.do">회원가입</a></li>
	            </ul>
	         </li>
	      </ul>
	      
	      
	      
	    </div>
	  </div>
	</nav>
 

</body>
</html>