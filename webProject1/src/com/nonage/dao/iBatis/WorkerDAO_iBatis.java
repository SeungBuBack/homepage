package com.nonage.dao.iBatis;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nonage.dao.WorkerDAO;
import com.nonage.db.sqlconfig.IBatisDBConnector;

public class WorkerDAO_iBatis implements WorkerDAO {
	private SqlMapClient client = IBatisDBConnector.getSqlMapInstance();
	private static WorkerDAO_iBatis instance = new WorkerDAO_iBatis();

	private WorkerDAO_iBatis() {
	}

	public static WorkerDAO_iBatis getInstance() {
		return instance;
	}

	// 사용자 인증을 위한 메소드 : -1:아이디 존재 X
	// 0:아이디 존재하지만 비밀번호 불일치
	// 1:아이디와 비밀번호 모두 일치
	// login.jsp -> workerCheck.jsp

	// 아이디를 검색 조건으로 주어서 비밀 번호를 얻어온다.
	public int workerCheck(String userid, String userpw) throws SQLException {
		int result = -1;
		String dbPwd = (String) client.queryForObject("workerCheck", userid); // 디비 저장된 비밀번호
		if (dbPwd == null) { // 전달인자로 준 아이디와 일치하는 행이 존재
			result = 0; // 등록된 관리자...
		} else {
			// 디비의 비밀번호와 입력한 비밀번호 일치 여부
			if (dbPwd.equals(userpw)) {
				result = 1; // 비밀번호 마저도 일치
			}
		}

		return result;
	}

}// WorkerDAO

