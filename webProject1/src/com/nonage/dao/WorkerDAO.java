package com.nonage.dao;

import java.sql.SQLException;

public interface WorkerDAO {
	
	// 사용자 인증을 위한 메소드 : -1:아이디 존재 X
	// 0:아이디 존재하지만 비밀번호 불일치
	// 1:아이디와 비밀번호 모두 일치
	// login.jsp -> workerCheck.jsp
	
	// 아이디를 검색 조건으로 주어서 비밀 번호를 얻어온다.	
	public int workerCheck(String userid, String userpw) throws SQLException;
	
}// WorkerDAO

