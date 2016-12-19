package com.nonage.controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.QnaDAO;
import com.nonage.dao.iBatis.QnaDAO_iBatis;
import com.nonage.dto.MemberVO;
import com.nonage.dto.QnaVO;

public class QnaWriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "qnaList.do";
			
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			url = "loginForm.do";
		} else {
			QnaVO qnaVO = new QnaVO();
			qnaVO.setSubject(request.getParameter("subject"));
			qnaVO.setContent(request.getParameter("content"));
			/* QnaDAO qnaDAO = QnaDAO_JDBC.getInstance(); */
			QnaDAO qnaDAO = QnaDAO_iBatis.getInstance();
			try {
				qnaDAO.insertqna(qnaVO, loginUser.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return url;
	}
}
