/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.50
 * Generated at: 2023-09-15 01:47:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.springframework.web.servlet.support.RequestContext;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1612850415545L));
    _jspx_dependants.put("jar:file:/C:/eGovFrame-4.0.0/workspace.edu/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVC03/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("jar:file:/C:/eGovFrame-4.0.0/workspace.edu/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVC03/WEB-INF/lib/jstl-1.2.jar!/META-INF/fn.tld", Long.valueOf(1153352682000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("org.springframework.web.servlet.support.RequestContext");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write(" <!-- jstl 가져오기 -->\r\n");
      out.write("\r\n");
      out.write("<!-- 여러 기능이 있는 함수 가져오기 -->\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("\r\n");
      out.write("<!-- 부트스트랩 받아온 것 -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<!-- 비동기 방식\r\n");
      out.write("\t\t-> 한 개의 파일에서 모두 해결 가능 -->\r\n");
      out.write("\t\t\r\n");
      out.write("\t<!-- * 동기\r\n");
      out.write("\t\t클라이언트가 서버 요청 시, 서버는 dp를 통해서 해당 컨트롤을 찾아서 실행하는데 이 컨트롤러는 매퍼를 통해서 디비를 작동시킨다\r\n");
      out.write("\t\t결과값은 jsp에 담아서 클라이언트에게 돌려준다(응답)\r\n");
      out.write("\t\t장점 : 순차적으로 진행된다\r\n");
      out.write("\t\t단점 : 시간적 손실 (응답을 기다리는 동안 다른 행동을 하지 못함)\r\n");
      out.write("\t\t한번에 모든 걸 가져와서 한번에 처리 -> 많은 인원이 접속 시 과부화\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t* 비동기\r\n");
      out.write("\t\t클라이언트가 서버로 요청 했을 시, 동기 방식과 동일한 방식을 사용하지만 클라이언트가 응답을 기다리면서 다른 행위를 할 수 있다\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\tex. 입금 시스템\r\n");
      out.write("\t\t비동기 : 100만원 이체하면서 동시에 잔액 확인\r\n");
      out.write("\t\t동기: 잔액 확인 후 100만원 이체 -->\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t<!-- 따로 저장해둔 메뉴바 불러오기 -->\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../common/header.jsp", out, false);
      out.write("  <!-- ../ 사용하면 헷갈릴 경우 contextPath부터 시작-->\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"container\">\r\n");
      out.write(" \t \t<h2>Spring MVC03</h2>\r\n");
      out.write("  \t\t<div class=\"panel panel-default\">\r\n");
      out.write("    \t\t<div class=\"panel-heading\">Board</div>\r\n");
      out.write("    \t\t\r\n");
      out.write("    \t\t<div class=\"panel-body\">\r\n");
      out.write("\t\t\t\t<table class=\"table table-hover\"  id=\"boardList\">\r\n");
      out.write("\t\t\t\t<!-- class=\"table\" -> 부트스트램에 존재하는 테이블 css \r\n");
      out.write("\t\t\t\t\t\t\t\"table table-borderd\" : 각각 선이 생김 -->\r\n");
      out.write("\t\t\t\t\t<tr class=\"active\">\r\n");
      out.write("\t\t\t\t\t\t<td>번호</td>\r\n");
      out.write("\t\t\t\t\t\t<td>제목</td>\r\n");
      out.write("\t\t\t\t\t\t<td>작성자</td>\r\n");
      out.write("\t\t\t\t\t\t<td>작성일</td>\r\n");
      out.write("\t\t\t\t\t\t<td>조회수</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<tBody id=\"view\">\r\n");
      out.write("\t\t\t\t\t<!-- 비동기 방식으로 가져온 게시물을 나오게 하는 부분 -->\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t</tBody>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"5\">\r\n");
      out.write("\t\t\t\t\t\t  <!-- 테이블에 생성된 다섯개의 열 병합 -->\r\n");
      out.write("\t\t\t\t\t\t\t<button onclick=\"goForm()\" class=\"btn btn-primary btn-sm\">글쓰기</button>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\t\t\t\t \t\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<!-- 글쓰기 폼 -->\r\n");
      out.write("\t\t\t<div class=\"panel-body\" id=\"wform\" style=\"display:none;\">\r\n");
      out.write("\t\t\t\t<form id=\"frm\">\r\n");
      out.write("\t\t\t\t<!-- form 태그 요청방식(action..~)은 동기 방식 -->\r\n");
      out.write("\t    \t\t\t<table class=\"table\">\r\n");
      out.write("\t    \t\t\t\t<tr>\r\n");
      out.write("\t    \t\t\t\t\t<td>제목</td>\r\n");
      out.write("\t    \t\t\t\t\t<!-- input 사용 시, name을 반드시 내가 쓸 vo(Board)의 필드명과 같게해야한다 -->\r\n");
      out.write("\t    \t\t\t\t\t<!-- class=\"form-control : 크기에 맞게 input 칸이 조정됨 -->\r\n");
      out.write("\t    \t\t\t\t\t<td><input type=\"text\" name=\"title\" class=\"form-control\"></td>\r\n");
      out.write("\t    \t\t\t\t</tr>\r\n");
      out.write("\t    \t\t\t\t<tr>\r\n");
      out.write("\t    \t\t\t\t\t<td>내용</td>\r\n");
      out.write("\t    \t\t\t\t\t<td><textarea class=\"form-control\" name=\"content\" rows=\"7\" cols=\"\"></textarea></td>\r\n");
      out.write("\t    \t\t\t\t</tr>\r\n");
      out.write("\t    \t\t\t\t<tr>\r\n");
      out.write("\t    \t\t\t\t\t<td>작성자</td>\r\n");
      out.write("\t    \t\t\t\t\t<td><input type=\"text\" name=\"writer\" class=\"form-control\"></td>\r\n");
      out.write("\t    \t\t\t\t</tr>\r\n");
      out.write("\t    \t\t\t\t<tr>\r\n");
      out.write("\t    \t\t\t\t\t<td colspan=\"2\" align=\"center\">\r\n");
      out.write("\t    \t\t\t\t\t\t<!-- type=\"submit\"은 form 태그를 실행시킴 (동기방식) -->\r\n");
      out.write("\t    \t\t\t\t\t\t<button type=\"button\" onclick=\"goInsert()\" class=\"btn btn-success btn-sm\">등록</button>\r\n");
      out.write("\t    \t\t\t\t\t\t<button type=\"reset\" class=\"btn btn-warning btn-sm\" id=\"fclear\">취소</button>\r\n");
      out.write("\t    \t\t\t\t\t\t<button onclick=\"goList()\" class=\"btn btn-info btn-sm\">목록</button>\r\n");
      out.write("\t    \t\t\t\t\t</td>\r\n");
      out.write("\t    \t\t\t\t</tr>   \t\t\t\t\r\n");
      out.write("\t    \t\t\t</table>\r\n");
      out.write("    \t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("    \t\t<div class=\"panel-footer\">스프링 게시판 - 송은지</div>\r\n");
      out.write("  \t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- JavaScript -->\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("\t    $(document).ready(function(){\r\n");
      out.write("\t    \t// html이 다 로딩되고 나서 아래 코드 실행\r\n");
      out.write("\t    \tloadList();\r\n");
      out.write("\t    });\r\n");
      out.write("\t    \r\n");
      out.write("\t\t<!-- 게시글 목록 로딩 기능 -->\r\n");
      out.write("\t\tfunction loadList(){\r\n");
      out.write("\t\t\t// 비동기 방식으로 게시글 리스트 가져오는 기능\r\n");
      out.write("\t\t\t// ajax - 요청 url, 어떻게 데이터를 받을지, 요청방식 등... -> 객체 형태\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\turl : \"board/all\",\r\n");
      out.write("\t\t\t\ttype : \"get\",         //요청방식\r\n");
      out.write("\t\t\t\tdataType : \"json\",    // 서버로부터 데이터를 어떤 방식으로 받아 올 것인가 (json : key, value 형태로 나옴)\r\n");
      out.write("\t\t\t\tsuccess : makeView,   // 비동기방식 통식 성공 시 콜백함수\r\n");
      out.write("\t\t\t\terror : function() { alert(\"error\"); }\t\t\t\t\r\n");
      out.write("\t\t\t});\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction makeView(data) {\r\n");
      out.write("\t\t\t// loadList() 성공 시 넘어오는 함수\r\n");
      out.write("\t\t\t// console.log(data);  --> 배열의 형태로 리스트가 나오는 것을 알 수 있음  --> 반복문을 통해 리스트 꺼내오기\r\n");
      out.write("\t\t\tvar listHtml = \"\";\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t// 제이쿼리 반복문 형태\r\n");
      out.write("\t\t\t// $.each(data의 개수만큼 반복하는데, 반복할 때마다 실행시키고 싶은 함수)\r\n");
      out.write("\t\t\t$.each(data, function(index, obj){\r\n");
      out.write("\t\t\t\t// index : 반복한 횟수를 기억하고 있음 --> 순차적인 번호를 저장하기 위한 변수\r\n");
      out.write("\t\t\t\t// obj : 리스트 형식인 데이터를 하나씩 꺼내옴\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tlistHtml += \"<tr>\";\r\n");
      out.write("\t\t\t\tlistHtml += \"<td>\" + (index+1) + \"</td>\";\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tlistHtml += \"<td id='t\" + obj.idx + \"'>\";\r\n");
      out.write("\t\t\t\t// 상세보기를 위해 제목에 링크 걸기 (페이지 이동x, 파일 내 js 작동) \r\n");
      out.write("\t\t\t\t//   --> goContent()에 제목에 해당되는 idx를 매개변수로 보내주기\r\n");
      out.write("\t\t\t\tlistHtml += \"<a href='javascript:goContent(\" + obj.idx + \")'>\";  \r\n");
      out.write("\t\t\t\tlistHtml += obj.title;\r\n");
      out.write("\t\t\t\tlistHtml += \"</a>\";\t\t\t\t\r\n");
      out.write("\t\t\t\tlistHtml += \"</td>\";\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tlistHtml += \"<td id='w\" + obj.idx + \"'>\" + obj.writer + \"</td>\";\r\n");
      out.write("\t\t\t\tlistHtml += \"<td>\" + obj.indate + \"</td>\";\r\n");
      out.write("\t\t\t\tlistHtml += \"<td>\" + obj.count + \"</td>\";\r\n");
      out.write("\t\t\t\tlistHtml += \"</tr>\";\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<!-- 상세보기 화면 -->\r\n");
      out.write("\t\t\t\t// 제목 클릭 시 해당하는 글의 상세정보만 보여주기 위해 게시글 고유번호 활용(idx)\r\n");
      out.write("\t\t\t\tlistHtml += \"<tr id='c\" + obj.idx + \"' style='display:none'>\";  \r\n");
      out.write("\t\t\t\tlistHtml += \"<td>내용</td>\";\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tlistHtml += \"<td colspan='4'>\";\r\n");
      out.write("\t\t\t\tlistHtml += \"<textarea readonly id='ta\" + obj.idx + \"' rows='7' class='form-control'>\";  // readonly : 읽기모드\r\n");
      out.write("\t\t\t\t// listHtml += obj.content; <-- 제목 클릭 시 보여줄 때는 불필요 (이미 준비되어 있는 형태라서)\r\n");
      out.write("\t\t\t\tlistHtml += \"</textarea>\";\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<!-- 수정 및 삭제 화면-->\r\n");
      out.write("\t\t\t\tlistHtml += \"<br>\";\r\n");
      out.write("\t\t\t\tlistHtml += \"<span id='ub\" + obj.idx + \"'>\";\r\n");
      out.write("\t\t\t\tlistHtml += \"<button onclick='goUpdateForm(\" + obj.idx + \")' class='btn btn-sm btn-success'>수정화면</button></span> &nbsp\"; \r\n");
      out.write("\t\t\t\t// &nbsp : 줄 바꿈 없이 띄어쓰기\r\n");
      out.write("\t\t\t\tlistHtml += \"<button onclick='goDelete(\" + obj.idx + \")' class='btn btn-sm btn-warning'>삭제</button> &nbsp\";\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tlistHtml += \"</td>\";\t\r\n");
      out.write("\t\t\t\tlistHtml += \"</tr>\";\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t// id가 view인 tBody에 Html 형태로 listHtml을 넣어주기\r\n");
      out.write("\t\t\t$(\"#view\").html(listHtml);\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t// 게시글 목록 로딩 후 게시글 보여주기\r\n");
      out.write("\t\t\tgoList();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction goForm() {\r\n");
      out.write("\t\t\t// 글쓰기 버튼 기능 - 비동기\r\n");
      out.write("\t\t\t// goForm 함수 생성 후 view는 감추고 wform을 보이게 하기\r\n");
      out.write("\r\n");
      out.write("\t\t\t$(\"#boardList\").css(\"display\", \"none\");\r\n");
      out.write("\t\t\t$(\"#wform\").css(\"display\", \"block\");\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction goList() {\r\n");
      out.write("\t\t\t// 게시글 목록으로 가는 기능 - 비동기\r\n");
      out.write("\t\t\t$(\"#boardList\").css(\"display\", \"table-row\");   // block 대신 table-row\r\n");
      out.write("\t\t\t$(\"#wform\").css(\"display\", \"none\");\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction goInsert() {\r\n");
      out.write("\t\t\t// 게시글 등록 기능 - 비동기\r\n");
      out.write("\t\t\t// form 태그 안에 들어있는 태그들에 작성된 값을 직렬화 해서 가져오기\r\n");
      out.write("\t\t\t// 직렬화 : title=\"\"&content=\"\"&writer=\"\" \r\n");
      out.write("\t\t\tvar fData = $(\"#frm\").serialize();\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\turl : \"board/new\",   // 데이터를 보낼 주소\r\n");
      out.write("\t\t\t\ttype : \"post\",\t\t      // 게시글 작성 - post 방식 (데이터를 많이 넣으므로)\r\n");
      out.write("\t\t\t\tdata : fData,            // 보낼 데이터\r\n");
      out.write("\t\t\t\tsuccess : loadList,\r\n");
      out.write("\t\t\t\terror : function(){alert(\"error!\")}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$(\"#fclear\").trigger(\"click\");   // 클릭 시 취소 버튼 자동실행\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction goContent(idx) {\r\n");
      out.write("\t\t\t// 클릭한 게시글 제목에 해당되는 idx 값을 받아옴\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif($(\"#c\" + idx).css(\"display\") == \"none\"){\r\n");
      out.write("\t\t\t\t// 현재 display 안의 값이 \"none\"과 같다면 보이게 바꿔주기\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t// 비동기\r\n");
      out.write("\t\t\t\t// 게시글을 눌렀을 때 해당 게시글 정보를 불러오기\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\turl : \"board/\" + idx,   // 경로에 값을 보내는 것 - PathVariable 방식\r\n");
      out.write("\t\t\t\t\ttype : \"get\",\r\n");
      out.write("\t\t\t\t\tdataType : \"json\",   // 받아오는 데이터 타입\r\n");
      out.write("\t\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t\t\t// data : 성공 시 json(board vo) 받아옴\r\n");
      out.write("\t\t\t\t\t\t$(\"#ta\"+idx).val(data.content);  // 받아온 데이터의 content를 해당 태그에 추가\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\terror : function() {alert(\"error\");}\t\t\t\t\t\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#c\" + idx).css(\"display\", \"table-row\");  // <tr> 태그에는 block 사용 불가능\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\t$(\"#c\" + idx).css(\"display\", \"none\"); \r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t// boardCount.do로 요청해서 조회수 1을 올리고\r\n");
      out.write("\t\t\t\t// 게시글 다시 불러와서 적용시키기\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\turl : \"board/count/\" + idx,   // 게시글의 고유 번호를 가지고 경로에 값을 보내는 것\r\n");
      out.write("\t\t\t\t\ttype : \"put\",\t\t\t\t  //   --> url을 간단하게 하여 클라이언트가 접근을 쉽게하도록 하려고\r\n");
      out.write("\t\t\t\t\t// put 방식이지만 data를 json 형태로 보내는 것이 아니기 때문에 contentType, data를 쓰지 않아도 됨\r\n");
      out.write("\t\t\t\t\tsuccess : loadList,\r\n");
      out.write("\t\t\t\t\terror : function() { alert(\"error\")}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction goDelete(idx) {\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\turl : \"board/\" + idx,  // url에 goContent의 url과 동일 \r\n");
      out.write("\t\t\t\ttype: \"delete\",       // 삭제 상태를 보내는 것. 해당 서버에서만 보낼 수 있음, 다른 서버는 불가능\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t// 똑같은 url이지만 상태는 다름 --> rest..\r\n");
      out.write("\t\t\t\tdata : {\"idx\" : idx},\r\n");
      out.write("\t\t\t\tsuccess : loadList,\r\n");
      out.write("\t\t\t\terror : function() { alert(\"error\");}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction goUpdateForm(idx) {\r\n");
      out.write("\t\t\t// 매개변수로 idx가 넘어옴 \r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t// 해당되는 게시글이 수정될 수 있게 변경해주기\r\n");
      out.write("\t\t\t$(\"#ta\" + idx).attr(\"readonly\", false);\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t// 제목 수정\r\n");
      out.write("\t\t\tvar title = $(\"#t\" + idx).text();\r\n");
      out.write("\t\t\tvar newTitle = \"<input id='nt\" + idx + \"' type='text' class='form-control' value='\" + title + \"'>\";\r\n");
      out.write("\t\t\t$(\"#t\" + idx).html(newTitle);\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t// 작성자 수정\r\n");
      out.write("\t\t\tvar writer = $(\"#w\" + idx).text();\r\n");
      out.write("\t\t\tvar newWriter = \"<input id='nw\" + idx + \"'type='text' class='form-control' value='\" + writer + \"'>\";\r\n");
      out.write("\t\t\t$(\"#w\" + idx).html(newWriter);\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t// 수정화면 버튼 --> 수정 버튼\r\n");
      out.write("\t\t\tvar newBtn = \"<button onclick='goUpdate(\"+idx+\")' class='btn btn-primary btn-sm'>수정</button>\";\r\n");
      out.write("\t\t\t$(\"#ub\" + idx).html(newBtn);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction goUpdate(idx) {\r\n");
      out.write("\t\t\tvar title = $(\"#nt\" + idx).val();\r\n");
      out.write("\t\t\tvar content = $(\"#ta\" + idx).val();\r\n");
      out.write("\t\t\tvar writer = $(\"#nw\" + idx).val();\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tconsole.log(title+\"/\"+content+\"/\"+writer);\r\n");
      out.write("\t\t\t// boardUpdate.do로 요청을 통해 게시글 수정\r\n");
      out.write("\t\t\t// 수정된 게시글을 다시 불러와서 적용하기\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\turl : \"board/update\",\r\n");
      out.write("\t\t\t\t// 게시글 수정 뿐만 아니라 회원정보 수정도 있다면 ,,, 같은 url을 쓸 때 post 사용 시 보안 취약\r\n");
      out.write("\t\t\t\t//  -> \"put\" 사용 시, \"post\" 타입의 ajax도 생성하여 하나의 url로 두 개의 기능 수행 가능\r\n");
      out.write("\t\t\t\ttype : \"put\",\r\n");
      out.write("\t\t\t\t// 데이터를 보낼 경우 : contentType 이용\r\n");
      out.write("\t\t\t\tcontentType : \"application/json;charset=utf-8\",\t\t\r\n");
      out.write("\t\t\t\t// put 사용해서 데이터를 보낼 시 json 문자열로 변환시켜서 보내줘야 함 --> JSON.stringify()\r\n");
      out.write("\t\t\t\tdata : JSON.stringify({\"idx\" : idx, \"title\" : title, \"content\" : content, \"writer\" : writer}),  // 보낼 데이터\r\n");
      out.write("\t\t\t\tsuccess : loadList,\r\n");
      out.write("\t\t\t\terroe : function(){ alert(\"error!\")}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t</script>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
