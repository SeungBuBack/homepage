package com.nonage.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mvjsp.jdbc.JdbcUtil;
import mvjsp.jdbc.connection.ConnectionProvider;

import com.nonage.dao.AddressDAO;
import com.nonage.dto.AddressVO;

public class AddressDAO_JDBC implements AddressDAO {
	private static AddressDAO_JDBC instance=new AddressDAO_JDBC();
	private AddressDAO_JDBC(){}
	public static AddressDAO_JDBC getInstance(){
		return instance;
	}
	
	@Override
	public ArrayList<AddressVO> selectAddressByDong(String dong) throws SQLException{
		ArrayList<AddressVO> list = new ArrayList<AddressVO>();
		String sql = "select * from address where dong like '%'||?||'%'";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dong);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AddressVO addressVO = new AddressVO();
				addressVO.setZipNum(rs.getString("zip_num"));
				addressVO.setSido(rs.getString("sido"));
				addressVO.setGugun(rs.getString("gugun"));
				addressVO.setDong(rs.getString("dong"));
				addressVO.setZipCode(rs.getString("zip_code"));
				addressVO.setBunji(rs.getString("bunji"));
				list.add(addressVO);
			}
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		} 
		return list;

	}

}
