package com.nonage.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.nonage.dto.AddressVO;

public interface AddressDAO {
	public ArrayList<AddressVO> selectAddressByDong(String dong) throws SQLException;
}
