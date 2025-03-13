package com.kh.mfw.member.model.service;

import org.apache.ibatis.session.SqlSession;

// static 메서드 import → 클래스명 안 적고 메서드 호출 가능
import static com.kh.mfw.common.Template.getSqlSession;
import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberService {
	
	// Servlet에서 로그인 메서드 호출
	public MemberDTO login(MemberDTO member) {
		
		// JDBCUtil 클래스로부터
		// static Method로 구현해놓은
		// getConnection 메서드를 호출하여
		// Connection 객체를 반환받았음
		
							 	// Template에 있는 static 메서드 호출
		// SqlSession sqlSession = Template.getSqlSession();
		SqlSession sqlSession = getSqlSession();

		return null;
	}
	
}
