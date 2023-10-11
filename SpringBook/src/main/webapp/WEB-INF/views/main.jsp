<%@page import="org.springframework.web.servlet.support.RequestContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
	
	<div class="container">
 	 	<h2>Spring Book</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">Book</div>
    		
    		<div class="panel-body">
				<table class="table table-hover"  id="bookList">
				<!-- class="table" -> 부트스트램에 존재하는 테이블 css 
							"table table-borderd" : 각각 선이 생김 -->
					<tr class="active">
						<td>번호</td>
						<td>제목</td>
						<td>작가</td>
						<td>출판사</td>
						<td>isbn</td>
						<td>보유도서수</td>
						<td>삭제</td>
						<td>수정</td>
					</tr>
					
					<tBody id="view">
						<!-- 도서 목록 로딩 -->									
					</tBody>
					
					<tr>
						<td colspan=6">
							<button onclick="goForm()" class="btn btn-primary btn-sm">도서등록</button>
						</td>
					</tr>				 	
				</table>
			</div>
			
			
			<!-- 도서등록 -->
			<div class="panel-body" id="wform" style="display:none;">
				<form id="frm">
	    			<table class="table">	    			
	    				<tr>
	    					<td>제목</td>
	    					<td><input type="text" name="title" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td>작가</td>
	    					<td><input type="text" name="author" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td>출판사</td>
	    					<td><input type="text" name="company" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td>ISBN</td>
	    					<td><input type="text" name="isbn" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td>보유도서수</td>
	    					<td><input type="text" name="count" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td colspan="2" align="center">
	    						<button type="button" onclick="goInsert()" class="btn btn-success btn-sm">등록</button>
	    						<button type="reset" class="btn btn-warning btn-sm" id="fclear">취소</button>
	    						<button onclick="goList()" class="btn btn-info btn-sm">리스트로가기</button>
	    					</td>
	    				</tr>   				
	    			</table>
    			</form>
			</div>
			
			<!-- 도서 수정 -->
			<div class="panel-body" id="uform" style="display:none;">
				<form id="frm">
	    			<table class="table">
						<tBody id="bookView">
							<!-- 해당 도서 목록 로딩 -->									
						</tBody>	    			
	    				<tr>
	    					<td>제목</td>
	    					<td><input id="t" readonly type="text" name="title" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td>작가</td>
	    					<td><input id="w" readonly type="text" name="author" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td>출판사</td>
	    					<td><input id="c" readonly type="text" name="company" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td>ISBN</td>
	    					<td><input id="isbn" readonly type="text" name="isbn" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td>보유도서수</td>
	    					<td><input id="cnt" type="text" name="count" class="form-control"></td>
	    				</tr>
	    				<tr>
	    					<td colspan="2" align="center">
	    						<button type="button" id="ub" onclick="goInsert()" class="btn btn-success btn-sm">수정</button>
	    						<button type="reset" class="btn btn-warning btn-sm" id="fclear">취소</button>
	    						<button onclick="goList()" class="btn btn-info btn-sm">리스트로가기</button>
	    					</td>
	    				</tr>   				
	    			</table>
    			</form>
			</div>
			

			
    		<div class="panel-footer">스프링 도서관 - 송은지</div>
  		</div>
	</div>
	
	
	
	<!-- JavaScript -->
    <script type="text/javascript">
	    $(document).ready(function(){
	    	// html이 다 로딩되고 나서 아래 코드 실행
	    	loadList();
	    });
	    
	    
		<!-- 도서 목록 로딩 -->
		function loadList(){
			$.ajax({
				url : "board/all",
				type : "get",       
				dataType : "json",    
				success : makeView, 
				error : function() { alert("error"); }				
			});		
		}
		function makeView(data) {
			// loadList() 성공 시 넘어오는 함수
			console.log(data);
			var listHtml = "";
			
			$.each(data, function(index, obj){

				listHtml += "<tr>";
				listHtml += "<td>" + (index+1) + "</td>";
				
				listHtml += "<td id='t" + obj.num + "'>";
				listHtml += obj.title;			
				listHtml += "</td>";
				
				listHtml += "<td + obj.num + "'>" + obj.author + "</td>";
				listHtml += "<td>" + obj.company + "</td>";
				listHtml += "<td>" + obj.isbn + "</td>";
				listHtml += "<td>" + obj.count + "</td>";
				listHtml += "<td><button type='button' onclick='goDelete(" + obj.num + ")' class='btn btn-warning btn-sm'>삭제</button></td>"
				listHtml += "<td id='ub'><button onclick='goUpdateForm(" + obj.num + ")' class='btn btn-sm btn-success'>수정</button></td>"; 
				listHtml += "</tr>";
				
									
			});
	
			$("#view").html(listHtml);
			
			// 게시글 목록 로딩 후 게시글 보여주기
			goList();
		}
		
		
		function goForm() {
			// 도서 등록
			$("#bookList").css("display", "none");
			$("#wform").css("display", "block");	
		}
		
		
		function goList() {
			// 도서 목록
			$("#bookList").css("display", "table-row"); 
			$("#wform").css("display", "none");	
		}
		
		function goInsert() {
			// 도서 등록
			var fData = $("#frm").serialize();
			
			$.ajax({
				url : "board/new",  
				type : "post",		
				data : fData,            
				success : loadList,
				error : function(){alert("error!")}
			});
			$("#fclear").trigger("click"); 
		}
		
		
		
		function goDelete(num) {
			// 도서 삭제
			$.ajax({
				url : "board/" + num,
				type: "delete",       
				data : {"num" : num},
				success : loadList,
				error : function() { alert("error");}
			});
		}
		
		
		function goUpdateForm(num) {
			// 도서 수정
			
			$("#bookList").css("display", "none");
			$("#uform").css("display", "block");
		
			var listHtml = "";
			
			$.each(data, function(index, obj){
				<!-- 수정 도서 화면 -->

	    		listHtml += "<td>제목</td>"
	    		listHtml += "<td>" + obj.title + "</td>"
				
			});
			
			$("#bookView").html(listHtml);
			
			// 보유 도서 수 수정
			var count = $("#cnt" + num).text();
			var newCount = "<input id='nc" + num + "'type='text' class='form-control' value='" + count + "'>";
			$("#cnt" + num).html(newCount);
			
			// 수정 버튼
			var newBtn = "<button onclick='goUpdate("+num+")' class='btn btn-primary btn-sm'>수정</button>";
			$("#ub" + num).html(newBtn);
		}
		
		
		function goUpdate(num) {
			var count = $("#nc" + num).val();
			
			
			
			$.ajax({
				url : "board/update",
				type : "put",
				contentType : "application/json;charset=utf-8",		
				data : JSON.stringify({"count":count}),
				success : loadList,
				erroe : function(){ alert("error!")}
			});
		}
		
	</script>
	
	
</body>
</html>