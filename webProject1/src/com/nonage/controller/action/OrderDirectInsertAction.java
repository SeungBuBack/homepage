package com.nonage.controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.CartDAO;
import com.nonage.dao.OrderDAO;
import com.nonage.dao.iBatis.CartDAO_iBatis;
import com.nonage.dao.iBatis.OrderDAO_iBatis;
import com.nonage.dto.CartVO;
import com.nonage.dto.MemberVO;

public class OrderDirectInsertAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = "orderList.do";

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			url = "loginForm.do";
		} else {
			ArrayList<CartVO> cartList =new ArrayList<CartVO>();
			CartVO cartVO = new CartVO();
			cartVO.setId(loginUser.getId());
			cartVO.setPseq(Integer.parseInt(request.getParameter("pseq")));
			cartVO.setQuantity(Integer.parseInt(request
					.getParameter("quantity")));
			cartList.add(cartVO);
			OrderDAO orderDAO = OrderDAO_iBatis.getInstance();
			try {				 
				int maxOseq = orderDAO.insertOrder(cartList, loginUser.getId());
				url = "orderList.do?oseq=" + maxOseq;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return url;
	}
}
