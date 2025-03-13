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
}
