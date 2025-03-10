<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 문법 배우기</title>
<style>
	a {
		text-decoration: none;
		color: black;
	}
</style>
</head>
<body>
	
	<h1>* EL(Expression Language) 표현 언어</h1>
	
	<p>
		기본 JSP 상에 사용했던 &lt;%= %>(출력식) 같은 경우 사용이 복잡하고, <br>
		컴파일 시 문제가 발생할 수 있기 때문에 <br>
		이 문제를 대체할 수 있는 문법
	</p>
	
	<h3>1. EL구문 기본 학습</h3>
	<!--  
		절대 경로 방식: /bm/sc
		상대 경로 방식: sc
		
		얼핏 보면 상대 경로 방식이 편하지만 계층구조를 잘 생각해야 함
	-->
	<a href="/bm/sc">a태그</a> <br>
	<a href="sc">a태그</a>






</body>
</html>