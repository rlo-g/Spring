<%@page import="org.springframework.web.servlet.support.RequestContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <!-- jstl 가져오기 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 여러 기능이 있는 함수 가져오기 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<!-- 비동기 방식
		-> 한 개의 파일에서 모두 해결 가능 -->
		
	<!-- * 동기
		클라이언트가 서버 요청 시, 서버는 dp를 통해서 해당 컨트롤을 찾아서 실행하는데 이 컨트롤러는 매퍼를 통해서 디비를 작동시킨다
		결과값은 jsp에 담아서 클라이언트에게 돌려준다(응답)
		장점 : 순차적으로 진행된다
		단점 : 시간적 손실 (응답을 기다리는 동안 다른 행동을 하지 못함)
		한번에 모든 걸 가져와서 한번에 처리 -> 많은 인원이 접속 시 과부화
				
		* 비동기
		클라이언트가 서버로 요청 했을 시, 동기 방식과 동일한 방식을 사용하지만 클라이언트가 응답을 기다리면서 다른 행위를 할 수 있다
		
				
		ex. 입금 시스템
		비동기 : 100만원 이체하면서 동시에 잔액 확인
		동기: 잔액 확인 후 100만원 이체 -->	
		
		
		
	<!-- 따로 저장해둔 메뉴바 불러오기 -->
	<jsp:include page="../common/header.jsp"></jsp:include>  <!-- ../ 사용하면 헷갈릴 경우 contextPath부터 시작-->
	
	
	<div class="container">
 	 	<h2>Spring MVC03</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">Board</div>
    		
    		<div class="panel-body">
				<table class="table table-hover"  id="boardList">
				<!-- class="table" -> 부트스트램에 존재하는 테이블 css 
							"table table-borderd" : 각각 선이 생김 -->
					<tr class="active">
						<td>번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
					</tr>
					
					<tBody id="view">
					<!-- 비동기 방식으로 가져온 게시물을 나오게 하는 부분 -->										
					</tBody>
					
					<!-- 로그인 한 사람만 글작성 가능 -->
					<c:if test="${not empty mvo}">
						<tr>
							<td colspan="5">
							  <!-- 테이블에 생성된 다섯개의 열 병합 -->
								<button onclick="goForm()" class="btn btn-primary btn-sm">글쓰기</button>
							</td>
						</tr>				 	
					</c:if>
				</table>
			</div>
			
			
			<!-- 글쓰기 폼 -->
			<div class="panel-body" id="wform" style="display:none;">
				<form id="frm">
				<!-- form 태그 요청방식(action..~)은 동기 방식 -->
					<!-- 글작성을 한 회원의 아이디 값도 보내줌 -->
					<input type="hidden" name="memId" value="${mvo.memId}">
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
	    						<!-- readonly 작성불가 -->
	    					<td><input readonly="readonly" type="text" name="writer" class="form-control" value="${mvo.memName}"></td>
	    				</tr>
	    				<tr>
	    					<td colspan="2" align="center">
	    						<!-- type="submit"은 form 태그를 실행시킴 (동기방식) -->
	    						<button type="button" onclick="goInsert()" class="btn btn-success btn-sm">등록</button>
	    						<button type="reset" class="btn btn-warning btn-sm" id="fclear">취소</button>
	    						<button onclick="goList()" class="btn btn-info btn-sm">목록</button>
	    					</td>
	    				</tr>   				
	    			</table>
    			</form>
			</div>
			
    		<div class="panel-footer">스프링 게시판 - 송은지</div>
  		</div>
	</div>
	
	
	
	<!-- JavaScript -->
    <script type="text/javascript">
    	
    
    	// ajax에서도 post 방식으로 데이터를 보내기 위해서는
    	// csrf token 값을 전달해야 한다
    	
    	// 1. token의 이름과 값 가져오기
    	//  - ajax에서 csrf의 이름을 사용할 때는 parameterName이 아니라 headerName 사용
    	var csrfHeaderName = "${_csrf.headerName}";
    	var csrfTokenName = "${_csrf.token}";
    
    
	    $(document).ready(function(){
	    	// html이 다 로딩되고 나서 아래 코드 실행
	    	loadList();
	    });
	    
		<!-- 게시글 목록 로딩 기능 -->
		function loadList(){
			// 비동기 방식으로 게시글 리스트 가져오는 기능
			// ajax - 요청 url, 어떻게 데이터를 받을지, 요청방식 등... -> 객체 형태
			$.ajax({
				url : "board/all",
				type : "get",         //요청방식
				dataType : "json",    // 서버로부터 데이터를 어떤 방식으로 받아 올 것인가 (json : key, value 형태로 나옴)
				success : makeView,   // 비동기방식 통식 성공 시 콜백함수
				error : function() { alert("error"); }				
			});		
		}
		
		<!-- 게시판 화면 -->
		function makeView(data) {
			// loadList() 성공 시 넘어오는 함수
			// console.log(data);  --> 배열의 형태로 리스트가 나오는 것을 알 수 있음  --> 반복문을 통해 리스트 꺼내오기
			var listHtml = "";
			
			// 제이쿼리 반복문 형태
			// $.each(data의 개수만큼 반복하는데, 반복할 때마다 실행시키고 싶은 함수)
			$.each(data, function(index, obj){
				// index : 반복한 횟수를 기억하고 있음 --> 순차적인 번호를 저장하기 위한 변수
				// obj : 리스트 형식인 데이터를 하나씩 꺼내옴 -- 한 게시글에 대한 정보를 모두 가지고 있음
				
				listHtml += "<tr>";
				listHtml += "<td>" + (index+1) + "</td>";
				
				listHtml += "<td id='t" + obj.idx + "'>";
				// 상세보기를 위해 제목에 링크 걸기 (페이지 이동x, 파일 내 js 작동) 
				//   --> goContent()에 제목에 해당되는 idx를 매개변수로 보내주기
				listHtml += "<a href='javascript:goContent(" + obj.idx + ")'>";  
				listHtml += obj.title;
				listHtml += "</a>";				
				listHtml += "</td>";
				
				listHtml += "<td id='w" + obj.idx + "'>" + obj.writer + "</td>";
				listHtml += "<td>" + obj.indate + "</td>";
				listHtml += "<td>" + obj.count + "</td>";
				listHtml += "</tr>";
				
				<!-- 상세보기 화면 -->
				// 제목 클릭 시 해당하는 글의 상세정보만 보여주기 위해 게시글 고유번호 활용(idx)
				listHtml += "<tr id='c" + obj.idx + "' style='display:none'>";  
				listHtml += "<td>내용</td>";
				
				listHtml += "<td colspan='4'>";
				listHtml += "<textarea readonly id='ta" + obj.idx + "' rows='7' class='form-control'>";  // readonly : 읽기모드
				// listHtml += obj.content; <-- 제목 클릭 시 보여줄 때는 불필요 (이미 준비되어 있는 형태라서)
				listHtml += "</textarea>";	
				
				
				<!-- 수정 및 삭제 화면 -->				
				// 해당 작성자만 가능
				//  -- 조건문 안에서 EL식을 쓰려먼 문자열로 감싸줘야한다
				if("${mvo.memId}" == obj.memId){  
										// 해당 게시글에 대한 모든 정보가 들어있는 obj가 가진 memId
					listHtml += "<br>";
					listHtml += "<span id='ub" + obj.idx + "'>";
					listHtml += "<button onclick='goUpdateForm(" + obj.idx + ")' class='btn btn-sm btn-success'>수정화면</button></span> &nbsp"; 
					// &nbsp : 줄 바꿈 없이 띄어쓰기
					listHtml += "<button onclick='goDelete(" + obj.idx + ")' class='btn btn-sm btn-warning'>삭제</button> &nbsp";
					
				}else{
					// 다른 게시글에 수정/삭제 화면이 뜨지만 작동 안 하게 : disabled
					listHtml += "<br>";
					listHtml += "<span id='ub" + obj.idx + "'>";
					listHtml += "<button disabled onclick='goUpdateForm(" + obj.idx + ")' class='btn btn-sm btn-success'>수정화면</button></span> &nbsp"; 
					// &nbsp : 줄 바꿈 없이 띄어쓰기
					listHtml += "<button disabled onclick='goDelete(" + obj.idx + ")' class='btn btn-sm btn-warning'>삭제</button> &nbsp";					
					}
				listHtml += "</td>";	
				listHtml += "</tr>";
			});
			// id가 view인 tBody에 Html 형태로 listHtml을 넣어주기
			$("#view").html(listHtml);
			
			// 게시글 목록 로딩 후 게시글 보여주기
			goList();
		}
		
		<!-- 글쓰기 버튼 -->
		function goForm() {
			// 글쓰기 버튼 기능 - 비동기
			// goForm 함수 생성 후 view는 감추고 wform을 보이게 하기

			$("#boardList").css("display", "none");
			$("#wform").css("display", "block");	
		}
		
		<!-- 게시글 목록 이동 -->
		function goList() {
			// 게시글 목록으로 가는 기능 - 비동기
			$("#boardList").css("display", "table-row");   // block 대신 table-row
			$("#wform").css("display", "none");	
		}
		
		<!-- 게시글 등록 -->
		function goInsert() {
			// 게시글 등록 기능 - 비동기
			// form 태그 안에 들어있는 태그들에 작성된 값을 직렬화 해서 가져오기
			// 직렬화 : title=""&content=""&writer="" 
			var fData = $("#frm").serialize();
			
			$.ajax({
				url : "board/new",   // 데이터를 보낼 주소
				type : "post",		      // 게시글 작성 - post 방식 (데이터를 많이 넣으므로)
				data : fData,   // 보낼 데이터
				beforeSend : function(xhr){
					xhr.setRequestHeader(csrfHeaderName, csrfTokenName)
				},     // get 제외 모든 방식에 csrf 관련 값 넣어주기
				success : loadList,
				error : function(){alert("error!")}
			});
			$("#fclear").trigger("click");   // 클릭 시 취소 버튼 자동실행
		}
		
		<!-- 게시글 조회수 -->
		function goContent(idx) {
			// 클릭한 게시글 제목에 해당되는 idx 값을 받아옴
			
			if($("#c" + idx).css("display") == "none"){
				// 현재 display 안의 값이 "none"과 같다면 보이게 바꿔주기
				
				// 비동기
				// 게시글을 눌렀을 때 해당 게시글 정보를 불러오기
				$.ajax({
					url : "board/" + idx,   // 경로에 값을 보내는 것 - PathVariable 방식
					type : "get",
					dataType : "json",   // 받아오는 데이터 타입
					success : function(data) {
						// data : 성공 시 json(board vo) 받아옴
						$("#ta"+idx).val(data.content);  // 받아온 데이터의 content를 해당 태그에 추가
					},
					error : function() {alert("error");}					
				});
				$("#c" + idx).css("display", "table-row");  // <tr> 태그에는 block 사용 불가능
				
			
			}else{
				$("#c" + idx).css("display", "none"); 
				
				// boardCount.do로 요청해서 조회수 1을 올리고
				// 게시글 다시 불러와서 적용시키기
				$.ajax({
					url : "board/count/" + idx,   // 게시글의 고유 번호를 가지고 경로에 값을 보내는 것
					type : "put",				  //   --> url을 간단하게 하여 클라이언트가 접근을 쉽게하도록 하려고
					// put 방식이지만 data를 json 형태로 보내는 것이 아니기 때문에 contentType, data를 쓰지 않아도 됨
					beforeSend : function(xhr){
						xhr.setRequestHeader(csrfHeaderName, csrfTokenName)
					},
					success : loadList,
					error : function() { alert("error")}
				});
				
			}
		}
		
		<!-- 게시글 삭제 -->
		function goDelete(idx) {
			$.ajax({
				url : "board/" + idx,  // url에 goContent의 url과 동일 
				type: "delete",       // 삭제 상태를 보내는 것. 해당 서버에서만 보낼 수 있음, 다른 서버는 불가능
										// 똑같은 url이지만 상태는 다름 --> rest..
				data : {"idx" : idx},
				beforeSend : function(xhr){
					xhr.setRequestHeader(csrfHeaderName, csrfTokenName)
				},
				success : loadList,
				error : function() { alert("error");}
			});
		}
		
		<!-- 게시글 수정 화면 -->
		function goUpdateForm(idx) {
			// 매개변수로 idx가 넘어옴 
			
			// 해당되는 게시글이 수정될 수 있게 변경해주기
			$("#ta" + idx).attr("readonly", false);
			
			// 제목 수정
			var title = $("#t" + idx).text();
			var newTitle = "<input id='nt" + idx + "' type='text' class='form-control' value='" + title + "'>";
			$("#t" + idx).html(newTitle);
			
			// 작성자 수정
			var writer = $("#w" + idx).text();
			var newWriter = "<input id='nw" + idx + "'type='text' class='form-control' value='" + writer + "'>";
			$("#w" + idx).html(newWriter);
			
			// 수정화면 버튼 --> 수정 버튼
			var newBtn = "<button onclick='goUpdate("+idx+")' class='btn btn-primary btn-sm'>수정</button>";
			$("#ub" + idx).html(newBtn);
		}
		
		<!-- 게시글 수정 -->
		function goUpdate(idx) {
			var title = $("#nt" + idx).val();
			var content = $("#ta" + idx).val();
			var writer = $("#nw" + idx).val();
			
			
			console.log(title+"/"+content+"/"+writer);
			// boardUpdate.do로 요청을 통해 게시글 수정
			// 수정된 게시글을 다시 불러와서 적용하기
			
			$.ajax({
				url : "board/update",
				// 게시글 수정 뿐만 아니라 회원정보 수정도 있다면 ,,, 같은 url을 쓸 때 post 사용 시 보안 취약
				//  -> "put" 사용 시, "post" 타입의 ajax도 생성하여 하나의 url로 두 개의 기능 수행 가능
				type : "put",
				// 데이터를 보낼 경우 : contentType 이용
				contentType : "application/json;charset=utf-8",		
				// put 사용해서 데이터를 보낼 시 json 문자열로 변환시켜서 보내줘야 함 --> JSON.stringify()
				data : JSON.stringify({"idx" : idx, "title" : title, "content" : content, "writer" : writer}),  // 보낼 데이터
				beforeSend : function(xhr){
					xhr.setRequestHeader(csrfHeaderName, csrfTokenName)
				},
				success : loadList,
				erroe : function(){ alert("error!")}
			});
		}
		
	</script>
	
	
</body>
</html>