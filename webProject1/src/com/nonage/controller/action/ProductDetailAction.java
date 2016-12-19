package com.nonage.controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.ProductDAO;
import com.nonage.dao.iBatis.ProductDAO_iBatis;
import com.nonage.dto.ProductVO;

public class ProductDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url="product/productDetail.jsp";
		
		String pseq=request.getParameter("pseq").trim();
		
		/*ProductDAO productDAO=ProductDAO_JDBC.getInstance();*/
		ProductDAO productDAO=ProductDAO_iBatis.getInstance();
		ProductVO productVO=null;
		HttpSession session = request.getSession();
		
		try {
			productVO= productDAO.getProduct(pseq);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		request.setAttribute("productVO", productVO);
		
		return url;
	}

}
