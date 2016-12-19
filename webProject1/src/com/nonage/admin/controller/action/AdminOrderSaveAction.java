package com.nonage.admin.controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.controller.action.Action;
import com.nonage.dao.OrderDAO;
import com.nonage.dao.iBatis.OrderDAO_iBatis;

public class AdminOrderSaveAction implements Action {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      String url = "adminOrderList.do";
      
      String[] resultArr = request.getParameterValues("result");
      
      for(String oseq:resultArr){
        System.out.println(oseq);
        /*OrderDAO orderDAO = OrderDAO_JDBC.getInstance();*/
        OrderDAO orderDAO = OrderDAO_iBatis.getInstance();
        try {
			orderDAO.updateOrderResult(oseq);
		} catch (SQLException e) {		
			e.printStackTrace();
		}
      }
      return url;
    }
}
