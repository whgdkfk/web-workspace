<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL이란</h1>
	
	<p>
		JSP Standard Tag Library의 약어 <br>
		JSP에서 사용할 수 있는 커스텀 액션 태그 <br>
		공통적으로 사용해야 하는 코드들을 보다 쉽게 사용할 수 있도록 태그화해서 표준으로 제공하는 라이브러리
	</p>
	
	<hr>
	
	<p>
		JSTL을 사용하기 위해선 먼저 라이브러리를 추가해준 후 <br>
		JSTL을 사용하고자 하는 JSP페이지 상단에 선언해 주어야 함 <br>
	</p>
	
	<% if(true) { %>
		ABCDE
	<% } %>
	
	<h2>Core 라이브러리 실전 압축 핵심 요약 빠르게 배우고 넘어가기</h2>
	
	<p>
		if라는 태그를 작성하여 조건문을 만들어낼 수 있음 <br>
		- Java에서의 단일 if문과 똑같은 역할을 할 수 있는 태그 <br>
		- 조건식 test라는 속성에 작성 <br>
		- 조건식 만들 때 반드시 EL구문으로 작성하기 <br>
	</p>
	
	<!-- XML기반 -->
	<c:if test="${ 1 lt 2 }">
		<strong>1이 2보다 작습니다.</strong> <br>
	</c:if>
	
	<!-- false이기 때문에 화면 상에 출력X -->
	<c:if test="${ 1 gt 2 }">
		<strong>1이 2보다 큽니다.</strong>
	</c:if>
	
	<c:if test="${ '안녕하세요' ne '반갑습니다' }">
		<mark>안녕하세요와 반갑습니다는 같지 않습니다.</mark>
	</c:if>
	
	<hr>
	
	<h3>2) choose, when, otherwise</h3>
	
	<!-- point라는 Key값으로 Value가 넘어옴 -->
	<!--  
		포인트 사용량이 100 이하라면 일반회원이라고 출력
		포인트 사용량이 300 이하라면 우수회원이라고 출력
		이 앞에 두 케이스에 걸리지 않을 시 최우수 회원이라고 출력
	-->
	
	<!-- choose 자식요소로 when과 otherwise만 들어갈 수 있다. -->
	<c:choose>
		<c:when test="${ point le 100 }">
			일반회원 <br>
		</c:when>
		<c:when test="${ point le 300 }">
			<label style="color:gold">우수회원</label> <br>
		</c:when>
		<c:otherwise>
			<mark style="color:white; background:pink">최우수회원</mark> <br>
		</c:otherwise>
	</c:choose>
	
	<hr>
	
	<h3>3) 반복문 - forEach</h3>
	
	<pre>
		[자바스크립트 반복문]
		for(let i of list) {
		}
		
		aaa.map( e => { 
		
		});
		
		aaa.filter(e => {
		
		});
		
		[표현법]
		for loop문
		&lt;c:forEach var="속성명" begin="초기값" end="끝값" step="증가시킬값(생략가능)" &gt;
		&lt;c/forEach&gt;
		
		&lt;c:forEach var="속성명" items="순차적으로 접근할 배열 또는 컬렉션" &gt;
			반복적으로 실행할 내용
		&lt;c:/forEach&gt;
	</pre>
	
	<c:forEach var="i" begin="0" end="9">
		${ i }		
	</c:forEach>
	
	<br>
	
	<c:forEach var="i" begin="1" end="6">
		<h${i}>이런 것도 가능</h${i}>
	</c:forEach>
	
	<hr>
	
	<!--  
	for(String color : colors) {
		System.out.println(color);
	}
	-->
	
	<ul>
	<c:forEach var="c" items="${ colors }">
		<li style="color:${c}">${ c }</li>
	</c:forEach>
	</ul>
	
	<hr>
	
	<h3>회원목록</h3>
	
	<!--  
		caption, 표 제목
		thead, tbody, tfoot 표의 구조
		tr 행
		th, td 
	-->
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>아이디</th>
				<th>가입일</th>
			</tr>
		</thead>
		<tbody>
			<!-- 조회 결과가 있는지 없는지 검증 -->
			<!-- 조건식 만들 때 필요한 것: 최소 한 개 이상의 값 -->
			<c:choose>
				<c:when test="${ empty requestScope.users }">
					<!-- 조건식을 만족한다면 List 비어있음 -->
					<tr>
						<td colspan="4">조회 결과가 존재하지 않습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<!-- 최소 1개 이상 있다. 단, 몇 개일지 모름 → 반복분 -->
					<c:forEach items="${ requestScope.users }" var="user">
						<tr>
							<td>${ user.userNo }</td>
							<td>${ user.userName }</td>
							<td>${ user.userId }</td>
							<td>${ user.enrollDate }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>
</html>