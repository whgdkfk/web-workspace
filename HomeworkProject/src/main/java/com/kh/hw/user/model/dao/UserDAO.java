package com.kh.hw.user.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.hw.user.model.dto.UserDTO;

// DAO: DB와의 직접적인 상호작용(영속성 작업)
// 영속성 작업: 외부에 있는 영속적으로 저장할 수 있는 저장소에 작업(꼭 DB라는 보장 X)
public class UserDAO {
	
	// Driver 등록은 프로그램 실행 중 딱 한 번만 수행 → static 필드 만들어서 작업
	static {
		try {
			// FullClassName: 패키지 경로부터 클래스 식별자까지
			// 클래스 이름은 중복될 수 있음 
			// → 클래스 이름만으로 UserService 식별 안 됨
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public UserDTO login(UserDTO user) {
		// 영속성 장소가 오라클 DBMS이기 때문에 
		// 만들어놓은 KH_USER 테이블에 영속성 작업해야 함
		// → JDBC 절차 & 객체 필요함
		// → Driver, Connection, PreparedStatement, ResultSet
		// ResultSet이 돌아온다는 것은 100%(SELECT문)
		// ResultSet이 왔을 때 결과는 0행이거나 1행일 수밖에 없다.(조건이 primary key이기 때문에)
		
		// ResultSet에 있는 것을 뽑아서 view로 보내야 함 
		// → 5개(USER_NO, USER_ID, USER_PW, USER_NAME, ENROLL_DATE)
		// 돌아갈 때 들고 갈 수 있는 것 1개 → DTO에 담아서 들고 감
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = """
							SELECT
										  USER_NO
										, USER_ID
										, USER_PW
										, USER_NAME
										, ENROLL_DATE
							  FROM
							  			  KH_USER
							WHERE
										  USER_ID = ?
								 AND
					  					  USER_PW = ?
						    """;
		
		// 지역변수 비어있으면 return 불가
		// → null로 초기화
		UserDTO loginUser = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE", 
																			 "KH24_JAR", "KH1234");
			
			// placeholder에 사용자가 입력한 아이디값과 비밀번호값을 바인딩 해주어야 하기 때문에 
			// 바인딩 하는 작업은 preparedStatement 객체가 가지고 있는 
			// 메서드 setString, setInt, setDate, setDecimal 등을 호출해서 바인딩 할 수 있음
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			
			// 수행 결과는 ResultSet으로 돌아옴 → executeQuery
			// 조회 결과는 0행 또는 1행 (primary key를 조건으로 검사했기 때문에 2행 이상일 수 없음)
			rset = pstmt.executeQuery();
			
			// 중괄호: Block Scope
			if(rset.next()) {
				loginUser = new UserDTO(
																rset.getInt("USER_NO"),
																rset.getString("USER_ID"),
																rset.getString("USER_PW"),
																rset.getString("USER_NAME"),
																rset.getDate("ENROLL_DATE")
															 );
			}
			
		} catch (SQLException e) {
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
		return loginUser;
	}
}
