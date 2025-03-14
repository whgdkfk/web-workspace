package com.kh.mfw.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mfw.member.model.dto.MemberDTO;
import com.kh.mfw.member.model.service.MemberService;

@WebServlet("/sign-up")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignUpController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 모든 Controller가 하는 일: 요청 처리 
		// 요청처리 하기 위해 필요한 값 가공하는 과정
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		String email = request.getParameter("email");
		
		MemberDTO member = new MemberDTO(memberId, memberPw, memberName, email, null);
		
		// 요청 → 사용자가 입력한 값들을 멀리 있는 DB Server의 KH_MEMBER 테이블에 한 행 INSERT
		int result = new MemberService().signUp(member);
		
		// 회원가입에 성공할 수도 실패할 수도 있음 
		// → 성공여부/실패여부에 따라서 응답 결과가 다름 
		// → 조건문 사용
		
		//String path = "";
		String path = request.getContextPath();
		/*
		if(result != 0) {
			// 회원가입에 실패했을 경우 → 회원가입 페이지로 이동
			
			// request.getRequestDispatcher("/WEB-INF/views/member/enroll_form2.jsp").forward(request, response);
			// 요청을 join으로 보내면 회원가입에 실패해도 다시 회원가입 페이지 볼 수 있음
			// → 유지보수가 용이해짐
			// response.sendRedirect(request.getContextPath() + "/join");
			path = request.getContextPath() + "/join";
		} else {
			// 회원가입에 성공했을 경우 → 웰컴 페이지로 이동
			// response.sendRedirect(request.getContextPath());
			path = request.getContextPath();
		}
		*/
		// "중복된 아이디가 존재합니다. 다른 아이디를 입력해주세요." => 문자열 값 화면 출력(alert 이용)
		// String msg = "중복된 아이디가 존재합니다. 다른 아이디를 입력해주세요.";
		// System.out.println(msg);
		// request.setAttribute("message", "중복된 아이디가 존재합니다. 다른 아이디를 입력해주세요.");
		if(result == 0) {
			request.getSession().setAttribute("message", "중복된 아이디가 존재합니다. 다른 아이디를 입력해주세요.");
		} 
		// response.sendRedirect(path);
		response.sendRedirect(result != 0 ? path + "/join" : path);
		
		// 변수의 가장 큰 목적은?
		// 값의 재활용
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
