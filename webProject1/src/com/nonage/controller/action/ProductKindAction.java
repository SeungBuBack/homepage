package com.nonage.controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.dao.ProductDAO;
import com.nonage.dao.iBatis.ProductDAO_iBatis;
import com.nonage.dto.ProductVO;

public class ProductKindAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "product/productKind.jsp";

		String kind = request.getParameter("kind").trim();

		/*ProductDAO productDAO = ProductDAO_JDBC.getInstance();*/
		ProductDAO productDAO = ProductDAO_iBatis.getInstance();
		ArrayList<ProductVO> productKindList=null;
		try {
			productKindList = productDAO.listKindProduct(kind);
		} catch (SQLException e) {			
			e.printStackTrace();
		}

		request.setAttribute("productKindList", productKindList);

		return url;

	}

}

