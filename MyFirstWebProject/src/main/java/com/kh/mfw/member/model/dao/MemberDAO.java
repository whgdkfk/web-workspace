package com.kh.mfw.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberDAO {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public MemberDTO login(MemberDTO member) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = """
					SELECT
						   MEMBER_ID
						 , MEMBER_PW
						 , MEMBER_NAME
						 , EMAIL
						 , ENROLL_DATE
					  FROM
						   KH_MEMBER
					 WHERE
						   MEMBER_ID = ?
					   AND
						   MEMBER_PW = ?
					 """;
		
		MemberDTO loginMember = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE",
											   "KH24_JAR",
											   "KH1234");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				// 조회한 결과가 한 행 있었다.
				loginMember = new MemberDTO(
					rset.getString("MEMBER_ID"),
					rset.getString("MEMBER_PW"),
					rset.getString("MEMBER_NAME"),
					rset.getString("EMAIL"),
					rset.getDate("ENROLL_DATE")
				);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(rset != null) rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loginMember;
	}
	
	// memberId 있는지 체크
	public int checkId(String memberId) {
		
		String sql = """
					 SELECT COUNT(*)
					   FROM KH_MEMBER
					  WHERE MEMBER_ID = ?
					 """;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE",
											   "KH24_JAR",
											   "KH1234");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			
			/*
			 * case 1: count(*) 그룹 함수를 사용했을 때
			 * 		   무조건 ResultSet이 1행 존재함
			 * 		   컬럼값이 0 또는 1인 것으로 조회 결과 판별
			 */
			// 1행은 돌아오니까 next로 커서를 내림
			// rset.next();
			// 반환타입: int, 서비스에 int로 돌려줌
			// return rset.getInt("COUNT(*)"); 
			
			/*
			 * case 2: MEMBER_ID 컬럼을 조회한 경우
			 *         ResultSet의 결과가 0행이거나 1행이거나
			 */
			// 서비스에 boolean으로 돌려줌
			// return pstmt.executeQuery().next();
			rset = pstmt.executeQuery();
			rset.next();
			result = rset.getInt("COUNT(*)");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset != null) rset.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public void signUp(MemberDTO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = """
					 INSERT
					   INTO KH_MEMBER
					 VALUES (
					  		  ?
					  		, ?
					  		, ?
					  		, ?
					  		, DEFAULT
					  		 )
					 """;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE",
					   						   "KH24_JAR",
					   						   "KH1234");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getEmail());
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
