package com.nonage.admin.controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.controller.action.Action;
import com.nonage.dao.ProductDAO;
import com.nonage.dao.iBatis.ProductDAO_iBatis;
import com.nonage.dto.ProductVO;

public class AdminProductSearchAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "product/productList.jsp";

		String key = request.getParameter("key");
		String tpage = request.getParameter("tpage");
		if (key == null) {
			key = "";
		}
		if (tpage == null) {
			tpage = "1"; // 현재 페이지 (default 1)
		} else if (tpage.equals("")) {
			tpage = "1";
		}
		request.setAttribute("key", key);
		request.setAttribute("tpage", tpage);

		/* ProductDAO productDAO = ProductDAO_JDBC.getInstance(); */
		ProductDAO productDAO = ProductDAO_iBatis.getInstance();

		ArrayList<ProductVO> productList = null;
		String paging = null;
		try {
			productList = productDAO.listProduct(Integer.parseInt(tpage), key);
			paging = productDAO.pageNumber(Integer.parseInt(tpage), key);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("productList", productList);
		int n = productList.size();
		request.setAttribute("productListSize", n);
		request.setAttribute("paging", paging);
		
		return url;
	}

}
