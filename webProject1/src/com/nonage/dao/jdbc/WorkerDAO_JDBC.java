package com.nonage.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mvjsp.jdbc.JdbcUtil;
import mvjsp.jdbc.connection.ConnectionProvider;

import com.nonage.dao.WorkerDAO;

public class WorkerDAO_JDBC implements WorkerDAO{
	private WorkerDAO_JDBC() {
	}

	private static WorkerDAO_JDBC instance = new WorkerDAO_JDBC();

	public static WorkerDAO_JDBC getInstance() {
		return instance;
	}

	// 사용자 인증을 위한 메소드 : -1:아이디 존재 X
	// 0:아이디 존재하지만 비밀번호 불일치
	// 1:아이디와 비밀번호 모두 일치
	// login.jsp -> workerCheck.jsp
	
	// 아이디를 검색 조건으로 주어서 비밀 번호를 얻어온다.	
	public int workerCheck(String userid, String userpw) {
		String sql = "select pwd from worker where id=?";
		int result = -1;
		// 디비와 연동
		Connection conn = null;
		// 쿼리문(select)을 수행하기 위한 문장 객체
		PreparedStatement pstmt = null;
		// 결과값을 저장할 ResultSet
		ResultSet rs = null;			
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 전달인자로 준 아이디와 일치하는 행이 존재
				result = 0; // 등록된 관리자...
				String dbPwd = rs.getString(1); // 디비 저장된 비밀번호
				// 디비의 비밀번호와 입력한 비밀번호 일치 여부
				if (dbPwd.equals(userpw)) {
					result = 1; // 비밀번호 마저도 일치
				}
			}
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		} catch (Exception e) {
		}
		return result;
	}
	
}// WorkerDAO

