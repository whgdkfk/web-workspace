package com.kh.mfw.member.model.service;

import com.kh.mfw.member.model.dao.MemberDAO;
import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberService {
	
	public MemberDTO login(MemberDTO member) {
		/*
		 로그인
		 DB에 가서 SELECT절에다가 
		 사용자가 입력한 아이디값과 비밀번호값을 조건절에 사용해서 
		 조회하는 절차
		 ID가 틀리거나 PW가 틀려서 로그인에 실패할 수 있는 가능성이 있음
		 member.getMemberId().length > 10
		 member.getMemberId().equals("")
		 member.getMemberPw().equals("")
		 → DB에 갈 필요 없음
		 서비스단에서 유효성 검사하기(Validation)
		 */
		
		// DAO가 메모리에 올라감
		MemberDTO loginMember = new MemberDAO().login(member);
		return loginMember;
	}
	
	// 하나의 DAO 메서드는 하나의 SQL문만 수행한다.
	// → Service에서 수행
	public int signUp(MemberDTO member) {
		// DAO 객체 생성하여 checkId 메서드 호출
		int result = new MemberDAO().checkId(member.getMemberId());
		
		// 0: 조회 결과가 없음, 1: 조회 결과 있음
		if(result > 0) {
			return result;
		}
		
		new MemberDAO().signUp(member);
		return result;
	}
}
