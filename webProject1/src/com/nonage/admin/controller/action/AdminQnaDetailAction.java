package com.nonage.admin.controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.controller.action.Action;
import com.nonage.dao.QnaDAO;
import com.nonage.dao.iBatis.QnaDAO_iBatis;
import com.nonage.dto.QnaVO;

public class AdminQnaDetailAction implements Action {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String url = "qna/qnaDetail.jsp";

    String qseq = request.getParameter("qseq").trim();

    /*QnaDAO qnaDAO = QnaDAO_JDBC.getInstance();*/
    QnaDAO qnaDAO = QnaDAO_iBatis.getInstance();
    QnaVO qnaVO=null;
	try {
		qnaVO = qnaDAO.getQna(Integer.parseInt(qseq));
	} catch (NumberFormatException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}

    request.setAttribute("qnaVO", qnaVO);
        
    return url;
  }
}
