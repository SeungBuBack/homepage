package com.nonage.dao.iBatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mvjsp.jdbc.JdbcUtil;
import mvjsp.jdbc.connection.ConnectionProvider;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nonage.dao.QnaDAO;
import com.nonage.db.sqlconfig.IBatisDBConnector;
import com.nonage.dto.QnaVO;

public class QnaDAO_iBatis implements QnaDAO {
	private SqlMapClient client = IBatisDBConnector.getSqlMapInstance();
	private static QnaDAO_iBatis instance = new QnaDAO_iBatis();

	private QnaDAO_iBatis() {
	}

	public static QnaDAO_iBatis getInstance() {
		return instance;
	}

	public ArrayList<QnaVO> listQna(String id) throws SQLException {
		ArrayList<QnaVO> qnaList = new ArrayList<QnaVO>();
		qnaList = (ArrayList<QnaVO>) client.queryForList("listQna", id);
		return qnaList;
	}

	public QnaVO getQna(int seq) throws SQLException {
		QnaVO qnaVO=(QnaVO)client.queryForObject("getQna",seq);
		return qnaVO;
	}

	public void insertqna(QnaVO qnaVO, String session_id) throws SQLException{		
		qnaVO.setId(session_id);
		client.insert("insertqna",qnaVO);
	}

	/* *
	 * 관리자 모드에서 필요한 메소드
	 */
	public ArrayList<QnaVO> listAllQna() throws SQLException {
		ArrayList<QnaVO> qnaList = new ArrayList<QnaVO>();
		// 게시판의 데이터를 가지고 오는 쿼리 rep:1:게시물 2: 답변
		qnaList=(ArrayList<QnaVO>)client.queryForList("listAllQna",null);
		return qnaList;
	}

	public void updateQna(QnaVO qnaVO) throws SQLException{
		client.update("updateQna",qnaVO);
	}
}
