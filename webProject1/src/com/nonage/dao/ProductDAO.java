package com.nonage.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.nonage.dto.ProductVO;

public interface ProductDAO {

	// 신상품 리스트 얻어오기.
	public ArrayList<ProductVO> listNewProduct() throws SQLException;

	// 베스트 상품 리스트 얻어오기
	public ArrayList<ProductVO> listBestProduct() throws SQLException;

	// 상품번호로 상품정보 한개 가져오기
	public ProductVO getProduct(String pseq) throws SQLException;

	// 상품종류별 상품 리스트 얻어오기
	public ArrayList<ProductVO> listKindProduct(String kind)
			throws SQLException;

	/*
	 * 관리자페이지에서 사용되는 메서드
	 */
	public int totalRecord(String product_name) throws SQLException;

	// 페이지 이동을 위한 메소드
	public String pageNumber(int tpage, String name) throws SQLException;
	
	public ArrayList<ProductVO> listProduct(int tpage, String product_name) throws SQLException;
	
	public int insertProduct(ProductVO product) throws SQLException;
	
	public int updateProduct(ProductVO product) throws SQLException;
}
