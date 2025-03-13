package com.kh.hw.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.hw.user.model.dto.UserDTO;
import com.kh.hw.user.model.service.UserService;

// Servlet이란
// 회사에서 만들어야 하는 웹 애플리케이션의 규격을 만들어놓음 = 명세
// Servlet은 명세(외부로 웹 요청받고 응답해주기 위한 규격)일 뿐이다. 
// → 요청 들어오면 init 메서드 호출, get 방식이면 doGet 메서드 호출, post 방식이면 doPost 메서드 호출
//     메서드 안의 코드는 개발자 마음대로
@WebServlet("/sign-in")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 절차
		// 1) GET / POST → 요청 전송 방식이 POST라면 인코딩 작업(request에 인코딩 방식 set)
		// cf) setter: 필드 값을 변경하기 위한 메서드
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청값이 있나 없나? → 있다면 값을 뽑아서 가공
		// request.getParameter("input 요소의 name 속성값");
		//										  100% 무조건 input 요소의 name 속성값을 적는 것은 아님(만드는 사람이 어떻게 만드냐에 따라 다름)
		
		// userId / userPw
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		// 값이 잘 넘어오는지 확인
		// System.out.printf("입력한 아이디값: %s \n입력한 비밀번호값: %s", userId, userPw);
		
		// 3) 값이 두 개 이상일 경우 어딘가(전송용 클래스 = DTO)에 예쁘게 담기
		// DTO가 메모리에 올라가서 공간 생성해야 담을 수 있음 → DTO 객체 생성
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setUserPw(userPw);
		
		// Service에 데이터를 넘기려고 하는데 지금 넘기려고 하는 데이터: UserDTO의 주소값
		// Service를 메모리에 올려져 있어야 주소값 넘길 수 있음 → 객체 생성
		
		// 메모리에 올라가있는 상태
		// new UserService().login(user);
		// 객체간의 상호작용 = 메서드 호출
		// Controller가 Service한테 주소값 넘기고 싶음 → 메서드 호출
		// Service에 있는 login() 메서드 호출하면서 넘겨주고 싶은 user 객체의 주소값을 넘겨줄 수 있다.
		
		// UserService에서 돌아옴
		UserDTO loginUser = new UserService().login(user);
		// case 1. 아이디와 비밀번호 값이 일치했다면
		//			    필드값에 회원정보가 담겨있는 UserDTO 객체의 주소값 반환
		// case 2. 유효성 검증에 통과하지 못했거나, 아이디 또는 비밀번호가 일치하지 않았다면
		// 				null 값 반환
		System.out.println(loginUser);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
