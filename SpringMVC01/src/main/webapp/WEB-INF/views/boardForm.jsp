<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <!-- jstl 가져오기 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	<div class="container">
 	 	<h2>Spring MVC01</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">Board</div>
    		<div class="panel-body">
    			<form action="boardInsert.do" method="post">
	    			<table class="table">
	    				<tr>
	    					<td>제목</td>
	    					<!-- input 사용 시, name을 반드시 내가 쓸 vo(Board)의 필드명과 같게해야한다 -->
	    					<!-- class="form-control : 크기에 맞게 input 칸이 조정됨 -->
	    					<td><input type="text" name="title" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td>내용</td>
	    					<td><textarea class="form-control" name="content" rows="7" cols=""></textarea></td>
	    				</tr>
	    				<tr>
	    					<td>작성자</td>
	    					<td><input type="text" name="writer" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td colspan="2" align="center">
	    						<button type="submit" class="btn btn-success btn-sm">등록</button>
	    						<button type="reset" class="btn btn-warning btn-sm">취소</button>
	    						<a href="boardList.do" class="btn btn-info btn-sm">목록</button>
	    					</td>
	    				</tr>   				
	    			</table>
    			</form>
    		</div>
    		<div class="panel-footer">스프링 게시판 - 송은지</div>
  		</div>
	</div>
</body>
</html>