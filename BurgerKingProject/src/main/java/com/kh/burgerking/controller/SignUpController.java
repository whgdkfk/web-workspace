package com.kh.burgerking.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.burgerking.model.dto.UserDTO;

@WebServlet("/sign-up.do")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUpController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("POST에서 호출");
		
		/*
		 * Controller
		 * 1. request 객체로부터 값을 뽑아서 DTO로 가공
		 * 2. 요청 처리 → 패스
		 * 3. VIEW 처리
		 */
		
		// 글자 깨짐 원인
		// POST 방식일 경우 인코딩 설정 ISO-8859-1 방식으로 바뀌기 때문
		// 값 뽑기 전에 미리 UTF-8 방식으로 변경(Set)해 주어야 함
		request.setCharacterEncoding("UTF-8");
		
		// 1) 값 뽑기
		// request.getParameter("input 요소의 name 속성값");
		String userId = request.getParameter("userId");
		System.out.println(userId);
		String userPw = request.getParameter("userPw");
		System.out.println(userPw);
		String userName = request.getParameter("userName");
		System.out.println(userName);
		
		UserDTO user = new UserDTO(userId, userPw, userName);
		
		// service.insertMember(user);
		// Service → DAO → DB에 잘 다녀왔다고 가정(리턴값: int)
		
		// VIEW 처리
		// 비즈니스 로직 & 프레젠테이션 로직 분리
		// JSP를 이용해서 응답 데이터 만들기
		
		// JSP: Java 기반의 서버 사이드 스크립트 언어
		// ASP, PHP 
		
		// ---------------------------------------------------
		// 응답화면(JSP)에서 필요한 데이터를 request에 담아서 넘겨줄 것
		// Attribute → key:value 세트로 묶어서 값을 담을 수 있음
		request.setAttribute("user", user);
		request.setAttribute("message", "요청 처리에 성공했습니다.");

		// ---------------------------------------------------
		
		// 응답페이지를 JSP에게 위임(배정)
		// RequestDispatcher
															// 요청을 처리할 JSP파일의 경로 및 JSP파일명
		RequestDispatcher view = request.getRequestDispatcher("/views/response_page.jsp");
		
		// view // request, response
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("호출 확인");
		
		// doPost에서 doGet 메서드 호출
		doGet(request, response);
	}

}
