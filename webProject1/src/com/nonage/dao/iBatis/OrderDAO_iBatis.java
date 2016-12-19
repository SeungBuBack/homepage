package com.nonage.dao.iBatis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.connector.Request;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.nonage.dao.OrderDAO;
import com.nonage.db.sqlconfig.IBatisDBConnector;
import com.nonage.dto.CartVO;
import com.nonage.dto.OrderVO;

public class OrderDAO_iBatis implements OrderDAO {
	private SqlMapClient client = IBatisDBConnector.getSqlMapInstance();
	private static OrderDAO_iBatis instance = new OrderDAO_iBatis();

	private OrderDAO_iBatis() {
	}

	public static OrderDAO_iBatis getInstance() {
		return instance;
	}

	@Override
	public int insertOrder(ArrayList<CartVO> cartList, String id)
			throws SQLException {
		int maxOseq = 0;		

		// iBatis Transaction
		Connection conn = null;
		SqlMapSession session = null;
			
			conn=client.getDataSource().getConnection();
			session=client.openSession(conn);
			session.insert("insertOrder", id);			
			maxOseq = (Integer)session.queryForObject("selectMaxOseq", null);
			
			for (CartVO cartVO : cartList) {				
				insertOrderDetail(cartVO, maxOseq);
				
			}
		return maxOseq;
	}

	@Override
	public void insertOrderDetail(CartVO cartVO, int maxOseq)
			throws SQLException {		

		Connection conn = null;
		SqlMapSession session = null;
		try {
			conn = client.getDataSource().getConnection();
			session = client.openSession(conn);

			conn.setAutoCommit(false);
			
			OrderVO oVo=new OrderVO();
			oVo.setOseq(maxOseq);
			oVo.setPseq(cartVO.getPseq());
			oVo.setQuantity(cartVO.getQuantity());
			
			session.insert("insertOrderDetail", oVo);			
			session.update("updateCartResult",cartVO.getCseq());
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		}
	}

	@Override
	public ArrayList<OrderVO> listOrderById(String id, String result, int oseq)
			throws SQLException {
		OrderVO oVo=new OrderVO();
		oVo.setId(id);
		oVo.setResult(result);
		oVo.setOseq(oseq);
		
		ArrayList<OrderVO> orderList=(ArrayList<OrderVO>)client.queryForList("listOrderById",oVo);
		
		return orderList;
	}

	@Override
	public ArrayList<Integer> selectSeqOrderIng(String id) throws SQLException {
		ArrayList<Integer> oseqList = (ArrayList<Integer>)client.queryForList("selectSeqOrderIng", id);
		
		return oseqList;
	}
	@Override
	public ArrayList<Integer> selectSeqOrderTotal(String id)
			throws SQLException {
		ArrayList<Integer> oseqList = (ArrayList<Integer>)client.queryForList("selectSeqOrderTotal", id);
		return oseqList;
	}
	@Override
	public ArrayList<OrderVO> listOrder(String member_name) throws SQLException {
		ArrayList<OrderVO> orderList=(ArrayList<OrderVO>)client.queryForList("listOrder",member_name );
		return orderList;
	}

	@Override
	public void updateOrderResult(String oseq) throws SQLException {
		client.update("updateOrderResult",oseq);
	}	

}
