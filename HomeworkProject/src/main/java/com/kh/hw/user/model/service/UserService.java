package com.kh.hw.user.model.service;

import com.kh.hw.user.model.dao.UserDAO;
import com.kh.hw.user.model.dto.UserDTO;

public class UserService {
	
	// 클래스 외부에서 불러야 하니 public
	// 사용자가 입력한 아이디 값과 비밀번호 값을 담은 UserDTO의 주소값 필요
	// UserDTO의 주소값을 갖기 위해 공간이 필요
	// 어떤 값이 들어올지 모르나 자료형은 앎 → 변수로 선언해 놓음(UserDTO user)
	public UserDTO login(UserDTO user) {
		
		// 로그인
		// DB에 가서 SELECT절에다가 사용자가 입력한 아이디 값과 비밀번호 값을 
		// 조건절에 사용해서 조회를 하는 절차를 의미
		
		// 로그인에 실패하는 경우
		// 아이디가 틀리거나 비밀번호가 틀리는 경우
		
		// USER_ID VARCHAR2(30)
		// user.getUserId().length() > 30 → DB에 갈 필요 없음
		// user.getUserId().equals("") → DB에 갈 필요 없음
		// DAO에 갈 수 있나 없나 유효성 검증하는 코드 → 유효성 검증 클래스로 빠짐(단일 책임의 원칙)
		// DB에 가는 것 자체가 자원 낭비
		
		// DB에 가서 무언가를 한다는 것은 내 프로그램 외부로 가서 작업한다는 것을 의미
		// 프로그램 외부로 간다는 것은 IOException이 발생할 수 있음을 의미
		
		// DB에 Connection 개수가 정해져 있기 때문에 
		// 사람들이 가면 갈수록 Connection을 많이 사용하게 돼서 
		// 다음 사람이 기다려야 하는 일이 발생할 수 있음
		// → 애초에 로그인이 성립할 수 없는 값이 들어왔다면 DB에 갈 필요 없음
		// → 이 값이 DB에 갈 수 있는 값인지 아닌지 검증하는 과정을 거쳐야 함
		// → Service에서 함
		// 쓸 수 없는 값이면 DAO로 보내면 안 된다.
		// == Service단에서 유효성 검사(Validation) 하기
		
		// public void login(UserDTO user) { }
		// DAO야, user 줄 테니까 DB가서 SELECT한 결과 좀 돌려줄래? 요청 해야 함
		// DAO와의 상호작용이 있어야 로그인이 되는지 안 되는지 결과를 다룰 수 있음
		// DAO를 메모리에 올림 == 객체 생성
		// new UserDAO();
		
		// UserDAO에서 user 가지고 돌아옴
		UserDTO loginUser = new UserDAO().login(user);
		// 받아서 Controller로 보내줌
		return loginUser;
	}
}
