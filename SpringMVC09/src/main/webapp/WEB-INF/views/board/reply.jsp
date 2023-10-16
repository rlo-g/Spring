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
	
	<div class="container">
 	 	<h2>Spring MVC00</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">Board</div>
    		
    		<div class="panel-body">
    			<form id="frm" method="post">
    			
    				<input type="hidden" name="page" value="${cri.page}">
    				<input type="hidden" name="perPageNum" value="${cri.perPageNum}">

					<!-- 검색 후, 수정한 다음 검색된 페이지 유지를 위해 type, keyword도 함께 넘기기 위한 코드 추가 -->
		            <input type="hidden" name="type" value="${cri.type}">  <!-- 페이징 기법 필요 x : pageMaker 사용 x ==> cri만 -->
		            <input type="hidden" name="keyword" value="${cri.keyword}">     			
    			
    				<input type="hidden" name="memID" id="memID" value="${mvo.memID}">
    				<input type="hidden" name="idx" id="idx" value="${vo.idx}">
	    				<!-- 댓글을 달 게시글(원본 글)의 번호를 넘김 -->
    			
    		
    				<div class="form-group">
    					<label>제목</label>
    							<!-- 기존 게시글 제목 -->
    					<input value="<c:out value='${vo.title}'/>" type="text" name="title" id="title" class="form-control">    					
    				</div>
    				<div class="form-group">
    					<label>답변</label>
    					<textarea name="content" id="content" class="form-control" rows="10" cols=""></textarea>    					
    				</div>
    				<div class="form-group">
    					<label>작성자</label>
    					<input type="text" name="writer" id="writer" class="form-control" value="${mvo.memName}" readonly="readonly">    					
    				</div>
    				<button data-btn="reply" type="button" class="btn btn-default btn-sm ">답글등록</button>
    				<button data-btn="reset" type="button" class="btn btn-default btn-sm ">취소</button>
    				<button data-btn="list" type="button" class="btn btn-default btn-sm ">목록</button>
    			 			

    			</form>    			
    		</div>
    		<div class="panel-footer">스프링 게시판 - 송은지</div>
  		</div>
	</div>
	
	
	<script type="text/javascript">
	   $(document).ready(function(){
		      $("button").on("click", function(e){
		         var formData = $("#frm");
		         var btn = $(this).data("btn");

				 if(btn == "list"){
		            formData.attr("action","${cpath}/board/list");
		            formData.find("#idx").remove();
		            
		            formData.attr("method", "get");  // 목록 버튼 클릭 시 method를 get으로 변경
		            
		            formData.find("#memID").remove();
		            formData.find("#title").remove();
		            formData.find("#content").remove();
		            formData.find("#writer").remove();

		         }else if(btn =="reply"){
		            formData.attr("action","${cpath}/board/reply");
		        	 
		         }else if(btn == "reset"){
		        	 formData[0].reset()
		        	 return;  // submit으로 넘어가지 않고 reset()에서 끝내야하므로
		         }
		         
		         formData.submit();
		         
		      });
		   });

	</script>
</body>
</html>