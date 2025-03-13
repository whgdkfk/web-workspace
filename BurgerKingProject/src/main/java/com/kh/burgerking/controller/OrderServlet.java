package com.kh.burgerking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order")
// HttpServlet를 확장하는 OrderServlet 클래스
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; // 버전관리

    public OrderServlet() {
        super();
    
    }
    
    // method="GET"
    // 요청 시 전달값 노출됨(입력한 value가 URL에 찍힌다.)
    // http://127.0.0.1/bk/order?num=1
    // http://127.0.0.1/bk/order?num=1&product-name=자유시간
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("/bk/order doGet 메소드 호출됨");
		// 사용자가 입력한 수량X500을 해야함
		
		// Controller 역할
		// 데이터를 받아 가공해서 Service로 넘겨주고
		// 응답받은 데이터를 View로 넘겨주는 역할
		/*
		 * VIEW에서 GET방식으로 요청 시 doGet()가 호출됨
		 * 두 개의 매개변수(request, response)가 존재함
		 * 
		 * 첫 번째 매개변수 HttpServletRequest => 요청 시 전달된 내용들이 담김
		 * => 요청 전송방식(GET), 사용자의 IP주소, 어떤 URL을 통해서 왔는지, 사용자가 입력한 값 등등
		 * 
		 * 두 번째 매개변수 HttpServletResponse => 요청 처리 후 응답할 때 사용
		 */
		
		// String queryString = request.getQueryString();
		// System.out.println(queryString);
		
		// requestURL = num=1 & product-name=%EC%9E%90%EC%9C%A0%EC%8B%9C%EA%B0%84
		// key값에 붙어있는 value값 뽑아서 확인 좀 해봐야 겠다
		int num = Integer.parseInt(request.getParameter("num")); // String이라 int로 형변환
		System.out.println(num);
		String productName = request.getParameter("product-name");
		System.out.println(productName);
		
		/*
		 * 자주 보는 문제 상황
		 * - 404: 파일이나 요청을 받아주는 서블릿을 찾지 못했을 때 발생
		 *        → 오타일 확률이 높음
		 * - 500: 자바 소스코드 상의 오류(예외 발생)
		 */
		
		// DB 잘 다녀옴 → INSERT 했다고 침
		/*
		 * 요청 처리(Service의 메서드를 호출해서 DB와의 상호작용까지 끝난 상태)
		 */
		
		int totalPrice = num * 500;
		
		/*
		 * XXX(사용자가 입력한 제품명)의 총 가격은 XXX(총 결제해야 하는 금액)원입니다.
		 */
		
		// 1단계) 응답 데이터 형식 지정 → 문서 형태의 HTML / 인코딩 방식 UTF-8
		response.setContentType("text/html; charset=UTF-8");
		
		// 2단계) 출력 스트림 생성
		PrintWriter writer = response.getWriter();
		
		// 3단계) 스트림을 통해 HTML 출력
		writer.println("<html>");
		
		writer.println("<head>");
		writer.println("<title>GET 방식 응답</title>");
		writer.println("</head>");
		
		writer.println("<script>");
		writer.println("alert('축하합니다')");
		writer.println("</script>");
		
		writer.printf("%s의 총 가격은 %d원입니다.", productName, totalPrice);
		
		writer.println("</html>");
		// 문제점: 비즈니스 로직과 프레젠테이션 로직이 합쳐져 있음 → 유지보수가 어려움
		// 해결방법: 비즈니스 로직과 프레젠테이션 로직을 분리해 줘야함
	}
	
	// method="POST"
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/bk/order doPost 메소드 호출됨");
	}

}
