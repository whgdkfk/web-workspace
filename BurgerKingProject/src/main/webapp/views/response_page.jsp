<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.burgerking.model.dto.UserDTO" %>  
<!-- JSP상에서 Java 코드 쓰는 방법 -->    
<%
	// 자바코드 쓸 수 있음
	String message = (String)request.getAttribute("message");
	UserDTO user = (UserDTO)request.getAttribute("user");
	String userId = user.getUserId();
	String userName = user.getUserName();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답페이지</title>
</head>
<body>
	<!-- 
		 JSP: 
		 Java 기반의 서버 사이드 스크립트 언어
		 java코드와 HTML 코드를 분리하기 위한 기술 
		 HTML 파일로 보이지만 Servlet으로 변환됨
	-->
	<!--  
		"사용자가 입력한 userName"님
		회원가입에 성공하셨습니다.
	-->
	
	<h1><%= userName %>님</h1>	
	<p>
		<!-- request.getAttribute("message") -->
		<%-- <%= message %> --%> <br>
		회원가입에 성공하셨습니다. <br><br>
		가입한 아이디: <%-- <%= userId %> --%> ${ user.userId }
	</p>
	
</body>
</html>