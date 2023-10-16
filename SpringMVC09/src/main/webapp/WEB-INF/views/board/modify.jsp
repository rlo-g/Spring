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
 	 	<h2>Spring MVC09</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">Board</div>
    		
    		<div class="panel-body">
    		  
    		  <form id="frm">
    			<table class="table table-bordered table-hover">
    			
    				<tr>
    					<td>번호</td>
    					<td><input readonly="readonly" value="${vo.idx}" name="idx" type="text" class="form-control" id="idx"></td>
    				</tr>
    				<tr>
    					<td>제목</td>
    					<td><input type="text" name="title" id="title" class="form-control" value="<c:out value = '${vo.title}'/>" ></td>
    				</tr>
    				<tr>
    					<td>내용</td>
    					<td>
    						<textarea name="content" id="content" class="form-control" rows="10" cols=""><c:out value="${vo.content}" /></textarea>
    					</td>
    				</tr>
    				<tr>
    					<td>작성자</td>
    					<td><input readonly="readonly" type="text" name="writer" id="writer" class="form-control" value="${vo.writer }"</td>
    				</tr>
    				
    				<tr>
    					<td colspan="2" style="text-align:center;">
    						<c:if test="${not empty mvo && mvo.memID eq vo.memID}">
    						<!-- mvo : session에 담긴 로그인한 사람의 정보 
    							 로그인 아이디 == 게시글 아이디 -> 수정/삭제 가능  -->
	    						<button data-btn="modify" type="button" class="btn btn-sm btn-default">수정</button>
	    						<!-- form 태그 안에서 button에 type을 주지 않으면 기본적으로 submit으로 인식함 -->
	    						<button data-btn="remove" type="button" class="btn btn-sm btn-default">삭제</button>
    						</c:if>
    						<button type="button" data-btn="list" class="btn btn-sm btn-default">목록</button>
    					</td>
    				</tr>
    			</table>
    		  		  	    		  	    		  	
    				<input type="hidden" name="page" value="${cri.page}" >
    				<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
    				
					<!-- 검색 후, 수정한 다음 검색된 페이지 유지를 위해 type, keyword도 함께 넘기기 위한 코드 추가 -->
		            <input type="hidden" name="type" value="${cri.type}">  <!-- 페이징 기법 필요 x : pageMaker 사용 x ==> cri만 -->
		            <input type="hidden" name="keyword" value="${cri.keyword}">    				    				
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

		         if(btn == "remove"){
		            formData.attr("action","${cpath}/board/remove");
		            formData.attr("method", "get");
		            
		            formData.find("#title").remove();
		            formData.find("#content").remove();
		            formData.find("#writer").remove();
		            
		         }else if(btn == "list"){
		            formData.attr("action","${cpath}/board/list");
		            formData.find("#idx").remove();
		            formData.attr("method", "get");
		            
		            formData.find("#title").remove();
		            formData.find("#content").remove();
		            formData.find("#writer").remove();
		            formData.find("#idx").remove();
		            
		         }else if(btn == "modify"){
		        	formData.attr("action", "${cpath}/board/modify");
		            formData.attr("method", "post");
		            
		         }
		         
		         formData.submit();
		         
		      });
		   });

	</script>
</body>
</html>