package com.nonage.controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.MemberDAO;
import com.nonage.dao.iBatis.MemberDAO_iBatis;
import com.nonage.dto.MemberVO;

public class JoinAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String url = "member/login.jsp";
		
		HttpSession session = request.getSession();
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setId(request.getParameter("id"));
		memberVO.setPwd(request.getParameter("pwd"));
		memberVO.setName(request.getParameter("name"));
		memberVO.setEmail(request.getParameter("email"));
		memberVO.setZipNum(request.getParameter("zipNum"));
		memberVO.setAddress(request.getParameter("addr1") +
		request.getParameter("addr2"));
		memberVO.setPhone(request.getParameter("phone"));		
	
		//MemberDAO memberDAO = MemberDAO_JDBC.getInstance();
		MemberDAO memberDAO = MemberDAO_iBatis.getInstance();
		try {
			memberDAO.insertMember(memberVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		session.setAttribute("id", request.getParameter("id"));		
		return url;
	}

}
