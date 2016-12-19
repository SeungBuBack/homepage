package com.nonage.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.nonage.dto.QnaVO;

public interface QnaDAO {  

  public ArrayList<QnaVO> listQna(String id) throws SQLException;

  public QnaVO getQna(int seq) throws SQLException;
  public void insertqna(QnaVO qnaVO, String session_id) throws SQLException;

  /* *
   * 관리자 모드에서 필요한 메소드
   */
  public ArrayList<QnaVO> listAllQna() throws SQLException;

  public void updateQna(QnaVO qnaVO) throws SQLException;
}
