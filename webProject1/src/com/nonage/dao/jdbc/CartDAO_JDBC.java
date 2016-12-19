package com.nonage.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mvjsp.jdbc.JdbcUtil;
import mvjsp.jdbc.connection.ConnectionProvider;

import com.nonage.dao.CartDAO;
import com.nonage.dto.CartVO;

public class CartDAO_JDBC implements CartDAO {
	private static CartDAO_JDBC instance = new CartDAO_JDBC();

	private CartDAO_JDBC() {
	}

	public static CartDAO_JDBC getInstance() {
		return instance;
	}

	@Override
	public void insertCart(CartVO cartVO) throws SQLException {
		String sql = "insert into cart(cseq,id, pseq, quantity)"
				+ " values(cart_seq.nextval,?, ?, ?)";

		// Connection, PreparedStatement 선언..
		Connection conn = null;
		PreparedStatement pstmt = null;

		// conn, pstmt 할당...
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);

			// pstmt에 cartVO 의 변수값으로 세팅..
			pstmt.setString(1, cartVO.getId());
			pstmt.setInt(2, cartVO.getPseq());
			pstmt.setInt(3, cartVO.getQuantity());
			// pstmt.excuteUpdate()
			pstmt.executeUpdate();
		} finally {
			// conn, pstmt close();
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}

	}

	@Override
	public ArrayList<CartVO> listCart(String userId) throws SQLException {
		// ArrayList<CartVO> 생성.
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();

		// String sql 쿼리문 작성..
		String sql = "select * from cart_view where id=? order by cseq desc";
		// select * from cart_view where id=? order by cseq desc

		// conn, pstmt, rs 생성.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// pstmt 세팅
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			// rs=pstmt.executeQuery()
			rs = pstmt.executeQuery();

			// while(rs.next()) list 세팅
			while (rs.next()) {
				CartVO cartVO = new CartVO();
				cartVO.setCseq(rs.getInt("cseq"));
				cartVO.setId(rs.getString("id"));
				cartVO.setPseq(rs.getInt("pseq"));
				cartVO.setMname(rs.getString("mname"));
				cartVO.setPname(rs.getString("pname"));
				cartVO.setQuantity(rs.getInt("quantity"));
				cartVO.setIndate(rs.getTimestamp("indate"));
				cartVO.setPrice2(rs.getInt("price2"));
				cartList.add(cartVO);
			}
		} finally {
			// conn, pstmt, rs close()
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		// list 리턴!!
		return cartList;
	}

	@Override
	public void deleteCart(int cseq) throws SQLException {
		String sql = "delete from cart where cseq=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}

	@Override
	public CartVO getCart(String cseq) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
