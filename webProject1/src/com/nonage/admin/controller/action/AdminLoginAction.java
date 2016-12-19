package com.nonage.admin.controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.controller.action.Action;
import com.nonage.dao.WorkerDAO;
import com.nonage.dao.iBatis.WorkerDAO_iBatis;

public class AdminLoginAction implements Action {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String url = "adminLoginForm.do";
    String msg = "";
    String workerId = request.getParameter("workerId").trim();
    String workerPwd = request.getParameter("workerPwd").trim();

    WorkerDAO workerDAO = WorkerDAO_iBatis.getInstance();

    int result=-1;
	try {
		result = workerDAO.workerCheck(workerId, workerPwd);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    if (result == 1) {// 로그인 성공
      HttpSession session = request.getSession();
      session.setAttribute("workerId", workerId);
      url = "adminProductList.do";
    } else if (result == 0) {
      msg = "비밀번호를 확인하세요.";
    } else if (result == -1) {
      msg = "아이디를 확인하세요.";
    }
    request.setAttribute("message", msg);
    return url;
  }
}
