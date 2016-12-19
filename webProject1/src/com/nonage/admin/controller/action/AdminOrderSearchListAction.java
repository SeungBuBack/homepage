package com.nonage.admin.controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.controller.action.Action;
import com.nonage.dao.OrderDAO;
import com.nonage.dao.iBatis.OrderDAO_iBatis;
import com.nonage.dto.OrderVO;

public class AdminOrderSearchListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "order/orderList.jsp";
		String key = request.getParameter("key");
		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
		}

		/*OrderDAO orderDAO = OrderDAO_JDBC.getInstance();*/
		OrderDAO orderDAO = OrderDAO_iBatis.getInstance();
		ArrayList<OrderVO> orderList=null;
		try {
			orderList = orderDAO.listOrder(key);
		} catch (SQLException e) {		
			e.printStackTrace();
		}

		request.setAttribute("orderList", orderList);

		return url;
	}

}
