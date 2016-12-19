package com.nonage.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.OrderDAO;
import com.nonage.dao.iBatis.OrderDAO_iBatis;
import com.nonage.dto.MemberVO;
import com.nonage.dto.OrderVO;

public class MyPageAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "mypage/mypage.jsp";

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			url = "loginForm.do";
		} else {
			/*OrderDAO orderDAO = OrderDAO_iBatis.getInstance();*/
			OrderDAO orderDAO = OrderDAO_iBatis.getInstance();
			ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
			ArrayList<Integer> oseqList = null;
			ArrayList<OrderVO> orderListIng = null;

			try {
				oseqList = orderDAO.selectSeqOrderIng(loginUser.getId());

				for (int oseq : oseqList) {
					orderListIng = orderDAO.listOrderById(loginUser.getId(), "1", oseq);

					OrderVO orderVO = orderListIng.get(0);
					orderVO.setPname(orderVO.getPname() + " 외 " + orderListIng.size() + "건");

					int totalPrice = 0;
					for (OrderVO ovo : orderListIng) {
						totalPrice += ovo.getPrice2() * ovo.getQuantity();
					}
					orderVO.setPrice2(totalPrice);
					orderList.add(orderVO);
				}
			} catch (Exception e) {
			}

			request.setAttribute("title", "진행 중인 주문 내역");
			request.setAttribute("orderList", orderList);
		}
		return url;
	}
}
