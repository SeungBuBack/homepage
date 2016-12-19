package com.nonage.admin.controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.controller.action.Action;
import com.nonage.dao.QnaDAO;
import com.nonage.dao.iBatis.QnaDAO_iBatis;
import com.nonage.dto.QnaVO;

public class AdminQnaListAction implements Action {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String url = "qna/qnaList.jsp";
    
    /*QnaDAO qnaDAO = QnaDAO_JDBC.getInstance();*/
    QnaDAO qnaDAO = QnaDAO_iBatis.getInstance();
    ArrayList<QnaVO> qnaList=null;
	try {
		qnaList = qnaDAO.listAllQna();
	} catch (SQLException e) {
		e.printStackTrace();
	}

    request.setAttribute("qnaList", qnaList);

    return url;
  }
}
