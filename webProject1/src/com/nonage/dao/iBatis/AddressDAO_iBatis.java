package com.nonage.dao.iBatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mvjsp.jdbc.JdbcUtil;
import mvjsp.jdbc.connection.ConnectionProvider;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nonage.dao.AddressDAO;
import com.nonage.db.sqlconfig.IBatisDBConnector;
import com.nonage.dto.AddressVO;

public class AddressDAO_iBatis implements AddressDAO {
	private SqlMapClient client = IBatisDBConnector.getSqlMapInstance();
	private static AddressDAO_iBatis instance = new AddressDAO_iBatis();

	private AddressDAO_iBatis() {
	}

	public static AddressDAO_iBatis getInstance() {
		return instance;
	}
	
	@Override
	public ArrayList<AddressVO> selectAddressByDong(String dong) throws SQLException{
		ArrayList<AddressVO> addressList=(ArrayList<AddressVO>)client.queryForList("selectAddressByDong",dong);
		return addressList;

	}

}
