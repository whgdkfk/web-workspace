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
				// 조회한 결과가 한 행 있었다
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
}
