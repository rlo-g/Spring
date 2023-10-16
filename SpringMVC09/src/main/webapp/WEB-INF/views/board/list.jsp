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
 	 	<h2><a href="${cpath}/board/list" style="color: inherit;">Spring MVC09</a></h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">
    			
    		<c:if test="${empty mvo}">
    				<!-- mvo가 비어있을 경우 (로그인 x) 로그인 창 생성 -->
    			<form class="form-inline" action="${cpath}/login/loginProcess" method="post">
    				<div class="form-group">
    					<label for="id">ID: </label>
    					<input type="text" class="form-control" id="id" name="memID">
    				</div>
    				<div class="form-group" style="margin: 0 20px">
    					<label for="pwd">  PASSWORD: </label>
    					<input type="password" class="form-control" id="pwd" name="memPW">
    				</div>
    				<button type="submit" class="btn btn-default btn-sm">로그인</button>
    			</form>
    		</c:if>	
    			
    		<c:if test="${not empty mvo}">
    				<!-- mvo가 채워져 있을 경우 (로그인) -->
    				
    				<form class="form-inline" action="${cpath}/login/logoutProcess" method="post">
					    <div class="text-center"> 
	    					<div class="form-group" style="margin-right:10px">
	    						<label>
					    			${mvo.memName}님 방문을 환영합니다!! 😎 
	    						</label>
	    					</div>
	    					<button type="submit" class="btn btn-default btn-sm">로그아웃</button>
					    </div> 
    				</form>
    		</c:if>
    		
    		</div>
    		
    		<div class="panel-body">
    			<table class="table table-bordered table-hover">
    				<thead>
    					<tr>
    						<th>번호</th>
    						<th>제목</th>
    						<th>작성자</th>
    						<th>작성일</th>
    						<th>조회수</th>
    					</tr>
    				</thead>
    				<tbody>
    					<!-- 게시글 출력 -->
    					<c:forEach items="${list}" var="vo" varStatus="i">
    													<!-- 반복횟수를 기억하고 있는 변수 -->
    						<tr>
	    						<td>${i.count}</td>
	    						
	    						<td> <!-- 게시글 제목 -->
	    						<c:if test="${vo.boardAvailable == 0 }">
		    						<!-- 삭제여부 확인 - 게시글/답글 삭제 시 접근불가 alert으로 알려주기  -->
	    							<a href="javascript:alert('삭제된 게시글입니다')">
	    								<!-- href 이동 불가하도록 클릭 시 alert 띄움 -->
		    						<c:if test="${vo.boardLevel > 0}">
		    							    		<!-- 댓글 달 경우 -->
		    							<c:forEach begin="0" end="${vo.boardLevel}" step="1">
		    								<!-- 댓글: 반복 2번, 대댓글: 3번 -->
		    								<span style="padding-left:8px;"></span>
		    							</c:forEach>
		    							 └ [Re]
		    						</c:if>	    							    							
	    							삭제된 게시물입니다. <!-- 글 제목 변경 -->
	    							</a>	    						
	    						</c:if>
	    						
	    						<!-- 삭제x -->
			                      <c:if test="${vo.boardAvailable > 0}">
			                         <a class="move" href="${vo.idx}">
			                         <c:if test="${vo.boardLevel > 0}">
			                         					<!-- 댓글 달 경우 -->	
			                            <c:forEach begin="0" end="${vo.boardLevel}" step="1">
		    								<!-- 댓글: 반복 2번, 대댓글: 3번 -->
			                               <span style="padding-left: 15px"></span>
			                            </c:forEach>
			                            ㄴ[RE]
			                         </c:if>
	    						
			                         <c:out value="${vo.title }"/>
			                         </a>
			                      </c:if>	    						
	    						</td>
	    						<!-- XSS(Cross Site Scripting) : 웹 사이트에 스크립트 코드를 주입하여 공격하는 기술
		    						   ==> 이를 방지하기 위해서 el식을 바로 쓰지 않음  
		    						   c:out - 문자 그대로 인식해서 출력 -->
	    						<td>${vo.writer}</td>
	    						<td>
	    							<fmt:formatDate value="${vo.indate}" pattern="yyyy-MM-dd"/>
	    						</td>
	    						<td>${vo.count}</td>
    						</tr>
    					</c:forEach>
    				</tbody>
    				
    				<c:if test="${not empty mvo}">
	    				<tr>
	    					<td colspan="5">
	    						<button id="regBtn" class="btn btn-xs btn-default pull-right">글쓰기</button>
	    					</td>
	    				</tr>
    				</c:if>
    			</table>
    			
    			
    			
    			
    			
				<div class="text-center" style="padding-top:0; margin-top:0;">
				  <ul class="pagination">
				  
		              <!-- 이전버튼처리 -->
		              <c:if test="${pageMaker.prev}">
		                 <li class="paginate_button previous">
		                    <a href="${pageMaker.startPage - 1}">◀</a>
		                 </li>
		              </c:if>
		              
		              <!-- 페이지번호 처리 -->
		             <c:forEach var="pageNum" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
		                
		                <c:if test="${pageMaker.cri.page == pageNum }">
		                   <li class="paginate_button active"><a href="${pageNum}">${pageNum}</a></li>
		                   								<!-- B.C의 list 메서드가 get방식을 받으므로 동일하게 보내줌 ==> javascript로 대체 -->
		                </c:if>
		                
		                <c:if test="${pageMaker.cri.page != pageNum }">
		                   <li class="paginate_button"><a href="${pageNum}">${pageNum}</a></li>
		                </c:if>
		             </c:forEach>
		             
		             <!-- 다음버튼처리 -->
		             <c:if test="${pageMaker.next}">
		                 <li class="paginate_button previous">
		                    <a href="${pageMaker.endPage + 1}">▶</a>
		                 </li>
		              </c:if>
		           </ul>
		           

		           <form action="${cpath}/board/list" id="pageFrm">
		              <input type="hidden" id="page" name="page" value="${pageMaker.cri.page}"> <!-- 현재 페이지 번호 -->
		              <input type="hidden" id="perPageNum" name="perPageNum" value="${pageMaker.cri.perPageNum}">
		              <!-- 페이지 변경 시 type, keyword도 함께 넘기기 위한 코드 추가 -->
		              <input type="hidden" name="type" value="${pageMaker.cri.type}">           
		              <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">           
		           </form>

				</div>    			
    		
    		
    		
    			<!-- 검색 메뉴 -->
    			<div>
    				<form class="form-inline" action="${cpath}/board/list" method="post" style="text-align:center">
    					<div class="form-group">
    						<select name="type" class="form-control">
    							<option value="writer" ${pageMaker.cri.type=='writer' ? 'selected':''}>작성자</option>
    							<!-- 저장되어 있는 타입이 writer 작성 값과 같다면 해당 옵션을 선택하겠다 / 다르면 빈칸 -->
    							<option value="title" ${pageMaker.cri.type=='title' ? 'selected':''}>제목</option>
    							<option value="content" ${pageMaker.cri.type=='content' ? 'selected':''}>내용</option>   						
    						</select>
    					</div>
    					<div class="form-group">
    						<input type="text" class="form-control" name="keyword" value="${pageMaker.cri.keyword}">
    					</div>                                                       <!-- 검색한 키워드 유지 -->
    					<button type="submit" class="btn btn-success">검색</button>
    				</form>    			
    			</div>
    			
    			
    		</div>
    		<div class="panel-footer">스프링 게시판 - 송은지</div>
  		</div>
	</div>


  	<!-- Modal -->
	  <div class="modal fade" id="myMessage" role="dialog">
	    <div class="modal-dialog modal-sm">      
	      <div class="modal-content" id="messageType">
	        <div class="modal-header panel-heading">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Modal Header</h4>
	        </div>
	        <div class="modal-body">
	          <!-- modal 내용 들어가는 부분 -->
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div>      
	    </div>
	  </div>
	
	
	<!-- JavaScript -->
	<script type="text/javascript">
		$(document).ready(function(){
			
			
	         // 페이지 번호 클릭 시 이동하기
	         var pageFrm = $("#pageFrm");
	         
	      	// li 태그 안에 a 태그 값 가져와서 form 태그에 적용시켜 페이지 이동하기		         
	         $(".paginate_button a").on("click", function(e){
	        	// e : 현재 클릭한 a 태그 요소 자체
	            e.preventDefault(); // a태그의 href속성 작동 막기
	            var page = $(this).attr("href"); // 클릭한 a태그의 href값 가져오기
	            pageFrm.find("#page").val(page); // 받아온 page 값은 찾은 태그 내에 넣어주기
	            pageFrm.submit();
	         });

			
			// 상세보기 클릭 시 이동
			$(".move").on("click", function(e){
				e.preventDefault();
				var idx = $(this).attr("href");
				var tag = "<input type='hidden' name='idx' value='"+ idx + "'>";
				pageFrm.append(tag);   // tag 요소 추가
				pageFrm.attr("action", "${cpath}/board/get");  // 속성 변경
				pageFrm.submit();
			});
			
			
			
			
			var result = "${result}"; // boardController에서 게시글의 고유번호를 넣어준 result를 불러옴 
			checkModal(result);
			
			// #regBtn 클릭 시 페이지 이동
			$("#regBtn").click(function(){
				location.href="${cpath}/board/register";  // get 방식
			});
		});
		
		function checkModal(result){
							 // 위에서 정의한 result
			if(result == ''){
				// 게시글을 입력하지 않았을 경우
				return;
			}
			if(parseInt(result) > 0) {
				// 게시글을 입력하여 게시글의 고유번호(result)를 받아온 경우
				$(".modal-body").text("게시글 " + result + "번이 등록되었습니다.");
				$("#myMessage").modal("show");  // Modal을 띄워줌
			}
		}
	
	</script>
</body>
</html>