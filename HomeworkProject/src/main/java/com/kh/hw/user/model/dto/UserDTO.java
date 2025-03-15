package com.kh.hw.user.model.dto;

import java.sql.Date;

public class UserDTO {
	
//	USER_NO NUMBER,
//	USER_ID VARCHAR2(30) NOT NULL,
//	USER_PW VARCHAR2(30) NOT NULL,
//	USER_NAME VARCHAR2(30) NOT NULL,
//	ENROLL_DATE DATE
	
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private Date enrollDate;
	
	public UserDTO() {
		super();
	}

	public UserDTO(int userNo, String userId, String userPw, String userName, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "UserDTO [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", enrollDate=" + enrollDate + "]";
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
}
