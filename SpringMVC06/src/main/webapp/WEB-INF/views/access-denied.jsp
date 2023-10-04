<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 권한이 없으므로 다시 로그인 해서 접근하라고 알려주기 -->
	<h2>Access Denied - You are not authorized to access this resource.</h2>
	<hr>
	<a href="${pageContext.request.contextPath}/">Back to Home Page</a>
</body>
</html>