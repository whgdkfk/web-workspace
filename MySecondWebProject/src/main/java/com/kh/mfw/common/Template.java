package com.kh.mfw.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	/*
	 * JDBC Util
	 * 
	 * public static Connection getConnection() {
	 * 	Connection conn = null;
	 * 	try {
	 * 		conn = driverManager.getConnection("URL", "USERNAME", "PASSWORD");
	 * 	} catch(SQLException e) {
	 * 		e.printStackTrace();
	 * 	}
	 * 	return conn;
	 * }
	 */
	
	// MyBatis Framework Version
	public static SqlSession getSqlSession() {
		
		SqlSession sqlSession = null;
		
		// 프로그램 밖에서 불러오는 것이기 때문에 예외 발생 가능성 有
		try {
			InputStream stream = Resources.getResourceAsStream("/mybatis-config.xml");
			
			// 1단계: SqlSessionFactoryBuilder 객체 생성
			// 		 그냥 생성자 호출하면 됨
			// 2단계: SqlSessionFactory 객체 생성
			//		 build(입력스트림) 스트림으로부터 
			//		 환경설정 파일의 값을 읽어오면서 SqlSessionFactory 객체 생성
			// 3단계: SqlSession 객체 생성
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return sqlSession;
	}
	
	// classes: 최상위 폴더
	// mybatis-config.xml: classes 바로 밑에
	
}
