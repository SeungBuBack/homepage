package com.nonage.admin.controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.controller.action.Action;
import com.nonage.dao.ProductDAO;
import com.nonage.dao.iBatis.ProductDAO_iBatis;
import com.nonage.dto.ProductVO;

public class AdminProductUpdateFormAction implements Action {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String url = "product/productUpdate.jsp";

    String pseq = request.getParameter("pseq").trim();

    /*ProductDAO productDAO = ProductDAO_JDBC.getInstance();*/
    ProductDAO productDAO = ProductDAO_iBatis.getInstance();
    ProductVO productVO=null;
	try {
		productVO = productDAO.getProduct(pseq);
	} catch (SQLException e) {
		e.printStackTrace();
	}

    request.setAttribute("productVO", productVO);

    // 상품 리스트(product_list.jsp) 페이지에서 쿼리 스트링으로 넘겨준 현재 페이지를 얻어온다.
    String tpage = "1";
    if (request.getParameter("tpage") != null) {
      tpage = request.getParameter("tpage");
    }
    request.setAttribute("tpage", tpage);
    
    String kindList[] = { "Heels", "Boots", "Sandals", "Slipers",
        "Shcakers", "Sale" };    
    request.setAttribute("kindList", kindList);
    
    return url;
  }
}
