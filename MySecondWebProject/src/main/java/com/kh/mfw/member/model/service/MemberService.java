package com.kh.mfw.member.model.service;

import org.apache.ibatis.session.SqlSession;

// static 메서드 import → 클래스명 안 적고 메서드 호출 가능
import static com.kh.mfw.common.Template.getSqlSession;

import com.kh.mfw.member.model.dao.MemberDAO;
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
		// Service에서 Connection 객체 역할 = SqlSession
		
		// 유효성 검증 패스(원래 해야됨)
		MemberDTO loginMember = new MemberDAO().login(sqlSession, member);
		sqlSession.close();
		return loginMember;
	}
	
	public int signUp(MemberDTO member) {
		// 3차 유효성 검증(Java) 
		// 4차 데이터 무결성을 위한 제약조건(DBMS)
		// 아이디 중복 검사
		SqlSession sqlSession = getSqlSession();
		
		// boolean result = new MemberDAO().checkId(sqlSession, member.getMemberId());
		
		if(new MemberDAO().checkId(sqlSession, member.getMemberId())) {
			sqlSession.close();
			return 0;
		}
		
		int result = new MemberDAO().signUp(sqlSession, member);
		
		sqlSession.commit();
		sqlSession.close();

		return result;
	}
	
}
