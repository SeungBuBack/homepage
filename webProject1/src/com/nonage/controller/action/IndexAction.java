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

public class IndexAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "/index.jsp";

		/* 데이터베이스에서 상품 정보를 얻어오는 비지니스로직 */
		ProductDAO productDAO = ProductDAO_iBatis.getInstance();
		
		ArrayList<ProductVO> newProductList=null;
		ArrayList<ProductVO> bestProductList =null;
		try {
			newProductList = productDAO.listNewProduct();
			bestProductList = productDAO.listBestProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("newProductList", newProductList);
		request.setAttribute("bestProductList", bestProductList);
		
		return url;
	}

}



