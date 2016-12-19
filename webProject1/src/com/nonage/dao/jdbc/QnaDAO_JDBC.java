package com.nonage.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mvjsp.jdbc.JdbcUtil;
import mvjsp.jdbc.connection.ConnectionProvider;

import com.nonage.dao.QnaDAO;
import com.nonage.dto.QnaVO;

public class QnaDAO_JDBC implements QnaDAO {
	private static QnaDAO_JDBC instance = new QnaDAO_JDBC();

	private QnaDAO_JDBC() {
	}

	public static QnaDAO_JDBC getInstance() {
		return instance;
	}

	@Override
	public ArrayList<QnaVO> listQna(String id) throws SQLException {
		ArrayList<QnaVO> qnaList = new ArrayList<QnaVO>();
		String sql = "select * from qna where id=? order by qseq desc";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnaVO qnaVO = new QnaVO();
				qnaVO.setQseq(rs.getInt("qseq"));
				qnaVO.setSubject(rs.getString("subject"));
				qnaVO.setContent(rs.getString("content"));
				qnaVO.setId(rs.getString("id"));
				qnaVO.setIndate(rs.getTimestamp("indate"));
				qnaVO.setReply(rs.getString("reply"));
				qnaVO.setRep(rs.getString("rep"));
				qnaList.add(qnaVO);
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return qnaList;
	}

	@Override
	public QnaVO getQna(int seq) throws SQLException {
		QnaVO qnaVO = null;
		String sql = "select * from qna where qseq=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				qnaVO = new QnaVO();
				qnaVO.setQseq(seq);
				qnaVO.setSubject(rs.getString("subject"));
				qnaVO.setContent(rs.getString("content"));
				qnaVO.setId(rs.getString("id"));
				qnaVO.setIndate(rs.getTimestamp("indate"));
				qnaVO.setReply(rs.getString("reply"));
				qnaVO.setRep(rs.getString("rep"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return qnaVO;
	}

	@Override
	public void insertqna(QnaVO qnaVO, String session_id) throws SQLException {
		String sql = "insert into qna (qseq, subject, " +
				"content, id) values(qna_seq.nextval, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn=ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnaVO.getSubject());
			pstmt.setString(2, qnaVO.getContent());
			pstmt.setString(3, session_id);
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	/* *
	   * 관리자 모드에서 필요한 메소드
	   */
	  public ArrayList<QnaVO> listAllQna() {
	    ArrayList<QnaVO> qnaList = new ArrayList<QnaVO>();
	    // 게시판의 데이터를 가지고 오는 쿼리 rep:1:게시물 2: 답변
	    String sql = "select * from qna order by indate desc";

	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	      conn = ConnectionProvider.getConnection();
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      while (rs.next()) {
	        QnaVO qnaVO = new QnaVO();
	        qnaVO.setQseq(rs.getInt("qseq"));
	        qnaVO.setSubject(rs.getString("subject"));
	        qnaVO.setContent(rs.getString("content"));
	        qnaVO.setId(rs.getString("id"));
	        qnaVO.setIndate(rs.getTimestamp("indate"));
	        qnaVO.setReply(rs.getString("reply"));
	        qnaVO.setRep(rs.getString("rep"));
	        qnaList.add(qnaVO);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return qnaList;
	  }

	  public void updateQna(QnaVO qnaVO) {
	    String sql = "update qna set reply=?, rep='2' where qseq=?";

	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    try {
	      conn = ConnectionProvider.getConnection();
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, qnaVO.getReply());
	      pstmt.setInt(2, qnaVO.getQseq());      
	      pstmt.executeUpdate();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      JdbcUtil.close(pstmt);
	      JdbcUtil.close(conn);
	    }
	  }
}







