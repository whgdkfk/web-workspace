package com.kh.mfw.member.model.dao;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.SqlSession;

import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberDAO {
	
	public MemberDTO login(SqlSession sqlSession, MemberDTO member) {
		
		/*
		 * MemberDTO loginMember = null;
		 * PreparedStatement pstmt = null;
		 * ResultSet rset = null;
		 * 
		 * String sql = "SELECT MEMBER_ID, MEMBER_PW..
		 * 				   FROM KH_MEMBER 
		 *                WHERE MEMBER_ID = ? AND MEMBER_PW = ?"
		 *                
		 * try {
		 * 	pstmt = sqlSession.preparedStatement(sql);
		 * 	pstmt.setString(1, member.getMemberId());
		 * 	pstmt.setString(2, member.getMemberPw());
		 * 
		 * 	rset = pstmt.executeQuery();
		 * 
		 * 	if(rset.next()) {
		 * 		loginMember = new MemberDTO();
		 * 		loginMember.setMemberId(rset.getString("MEMBER_ID");
		 * 		loginMember.setMemberPw(rset.getString("MEMBER_PW");
		 * 		...
		 * 	}
		 * } catch(SQLException e) {
		 * 		e.printStackTrace();
		 * } finally {
		 * 
		 * }
		 * 
		 * return loginMember;
		 */
		
		// SqlSession: Connection, PreparedStatement의 역할도 함
		// selectOne: 한 행만 조회
		// selectList: 여러 행 조회
		
		// SqlSession이 제공하는 메소드를 통해 
		// SQL문을 찾아서 실행하고 결과를 받을 수 있음
		// sqlSession.sql문 종류에 맞는 메소드("mapper파일의 namespace.SQL문의 id 속성값");
		// MemberDTO loginMember = sqlSession.selectOne("memberMapper.login", member);
		// System.out.println(loginMember.toString());
		// return null;
		return sqlSession.selectOne("memberMapper.login", member);
	}
	
	public boolean checkId(SqlSession sqlSession, String memberId) {
		/*
		int result = sqlSession.selectOne("memberMapper.checkId", memberId);
		
		if(result > 0) {
			return true;
		} else {
			return false;
		}
		*/

		return (Integer)sqlSession.selectOne("memberMapper.checkId", memberId) > 0 ? true : false;
	}
	
	
	// 의사 결정 코드
	public int signUp(SqlSession sqlSession, MemberDTO member) {
							   // namespace 속성값					
		return sqlSession.insert("memberMapper.signUp", member);
	}
	
	
	
}
