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
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <!-- css 연결 -->
  <link rel="stylesheet" href="${cpath}/resources/css/style.css">
</head>
<body>
	<div class="wrapper fadeInDown">
	  <div id="formContent">
	    <!-- Tabs Titles -->
	
	    <!-- Icon -->
	    <div class="fadeIn first" style="margin-bottom: 70px;">
	      <!-- <img src="http://danielzawadzki.com/codepen/01/icon.svg" id="icon" alt="User Icon" /> -->
	    </div>
	
	    <!-- Login Form -->
	    <form action="${cpath}/member/login" method="post">
	    				<!-- spring security에서 get 방식은 보안 x -->
	      <input type="text" id="username" class="fadeIn second" name="username" placeholder="login">
	      <input type="text" id="password" class="fadeIn third" name="password" placeholder="password">
	      <input type="submit" class="fadeIn fourth" value="Log In">
	    </form>
	
	    <!-- Remind Passowrd -->
	    <div id="formFooter">
	      <a class="underlineHover" href="#">Forgot Password?</a>
	    </div>
	
	  </div>
	</div>
</body>
</html>