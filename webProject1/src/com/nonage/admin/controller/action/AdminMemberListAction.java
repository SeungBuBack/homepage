package com.nonage.admin.controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.controller.action.Action;
import com.nonage.dao.MemberDAO;
import com.nonage.dao.iBatis.MemberDAO_iBatis;
import com.nonage.dto.MemberVO;

public class AdminMemberListAction implements Action {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String url = "member/memberList.jsp";
    String key = "";
    if (request.getParameter("key") != null) {
      key = request.getParameter("key");
    }

    MemberDAO memberDAO = MemberDAO_iBatis.getInstance();
    ArrayList<MemberVO> memberList=null;
	try {
		memberList = memberDAO.listMember(key);
	} catch (SQLException e) {
		e.printStackTrace();
	}

    request.setAttribute("memberList", memberList);

    return url;
  }
}
