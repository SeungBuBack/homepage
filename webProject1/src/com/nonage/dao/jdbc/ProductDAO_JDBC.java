package com.nonage.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mvjsp.jdbc.JdbcUtil;
import mvjsp.jdbc.connection.ConnectionProvider;

import com.nonage.dao.ProductDAO;
import com.nonage.dto.ProductVO;

public class ProductDAO_JDBC implements ProductDAO {
	private static ProductDAO_JDBC instance = new ProductDAO_JDBC();

	private ProductDAO_JDBC() {
	}

	public static ProductDAO_JDBC getInstance() {
		return instance;
	}

	// 신상품 리스트 얻어오기.
	public ArrayList<ProductVO> listNewProduct() throws SQLException {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql = "select * from new_pro_view";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO product = new ProductVO();
				product.setPseq(rs.getInt("pseq"));
				product.setName(rs.getString("name"));
				product.setPrice2(rs.getInt("price2"));
				product.setImage(rs.getString("image"));
				productList.add(product);
			}

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return productList;
	}

	// 베스트 상품 리스트 얻어오기
	public ArrayList<ProductVO> listBestProduct() throws SQLException {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql = "select * from best_pro_view";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO product = new ProductVO();
				product.setPseq(rs.getInt("pseq"));
				product.setName(rs.getString("name"));
				product.setPrice2(rs.getInt("price2"));
				product.setImage(rs.getString("image"));
				productList.add(product);
			}

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return productList;
	}

	// 상품번호로 상품정보 한개 가져오기
	public ProductVO getProduct(String pseq) throws SQLException {
		ProductVO product = null;
		String sql = "select * from product where pseq=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pseq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				product = new ProductVO();
				product.setPseq(rs.getInt("pseq"));
				product.setName(rs.getString("name"));
				product.setKind(rs.getString("kind"));
				product.setPrice1(rs.getInt("price1"));
				product.setPrice2(rs.getInt("price2"));
				product.setPrice3(rs.getInt("price3"));
				product.setContent(rs.getString("content"));
				product.setImage(rs.getString("image"));
				product.setUseyn(rs.getString("useyn"));
				product.setBestyn(rs.getString("bestyn"));
				product.setIndate(rs.getTimestamp("indate"));
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return product;
	}

	// 상품종류별 상품 리스트 얻어오기
	public ArrayList<ProductVO> listKindProduct(String kind)
			throws SQLException {
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql = "select * from product where kind=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kind);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO product = new ProductVO();
				product.setPseq(rs.getInt("pseq"));
				product.setName(rs.getString("name"));
				product.setPrice2(rs.getInt("price2"));
				product.setImage(rs.getString("image"));
				productList.add(product);
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return productList;
	}

	/*
	*관리자페이지에서 사용되는 메서드
	*/
	public int totalRecord(String product_name) throws SQLException{
		int total_pages = 0;
		String sql = "select count(*) from product where name like '%'||?||'%'";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet pageset = null;

		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);

			if (product_name.equals("")) {
				pstmt.setString(1, "%");
			} else {
				pstmt.setString(1, product_name);
			}
			pageset = pstmt.executeQuery();

			if (pageset.next()) {
				total_pages = pageset.getInt(1); // 레코드의 개수
				pageset.close();
			}
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return total_pages;
	}

	static int view_rows = 5; // 페이지의 개수
	static int counts = 5; // 한 페이지에 나타낼 상품의 개수

	// 페이지 이동을 위한 메소드
	public String pageNumber(int tpage, String name) throws SQLException{
		String str = "";

		int total_pages = totalRecord(name);
		int page_count = total_pages / counts + 1;

		if (total_pages % counts == 0) {
			page_count--;
		}
		if (tpage < 1) {
			tpage = 1;
		}

		int start_page = tpage - (tpage % view_rows) + 1;
		int end_page = start_page + (counts - 1);

		if (end_page > page_count) {
			end_page = page_count;
		}
		if (start_page > view_rows) {
			str += "<a href='NonageServlet?command=admin_product_list&tpage=1&key="
					+ name + "'>&lt;&lt;</a>&nbsp;&nbsp;";
			str += "<a href='NonageServlet?command=admin_product_list&tpage="
					+ (start_page - 1);
			str += "&key=<%=product_name%>'>&lt;</a>&nbsp;&nbsp;";
		}

		for (int i = start_page; i <= end_page; i++) {
			if (i == tpage) {
				str += "<font color=red>[" + i + "]&nbsp;&nbsp;</font>";
			} else {
				str += "<a href='NonageServlet?command=admin_product_list&tpage="
						+ i + "&key=" + name + "'>[" + i + "]</a>&nbsp;&nbsp;";
			}
		}

		if (page_count > end_page) {
			str += "<a href='NonageServlet?command=admin_product_list&tpage="
					+ (end_page + 1) + "&key=" + name
					+ "'> &gt; </a>&nbsp;&nbsp;";
			str += "<a href='NonageServlet?command=admin_product_list&tpage="
					+ page_count + "&key=" + name
					+ "'> &gt; &gt; </a>&nbsp;&nbsp;";
		}
		return str;
	}

	public ArrayList<ProductVO> listProduct(int tpage, String product_name) throws SQLException{
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();

		String str = "select pseq, indate, name, price1, price2, useyn, bestyn "
				+ " from product where name like '%'||?||'%' order by pseq desc";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int absolutepage = 1;

		try {
			con = ConnectionProvider.getConnection();
			absolutepage = (tpage - 1) * counts + 1;
			pstmt = con.prepareStatement(str, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			if (product_name.equals("")) {
				pstmt.setString(1, "%");
			} else {
				pstmt.setString(1, product_name);
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				rs.absolute(absolutepage);
				int count = 0;

				while (count < counts) {
					ProductVO product = new ProductVO();
					product.setPseq(rs.getInt(1));
					product.setIndate(rs.getTimestamp(2));
					product.setName(rs.getString(3));
					product.setPrice1(rs.getInt(4));
					product.setPrice2(rs.getInt(5));
					product.setUseyn(rs.getString(6));
					product.setBestyn(rs.getString(7));
					productList.add(product);
					if (rs.isLast()) {
						break;
					}
					rs.next();
					count++;
				}
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return productList;
	}

	public int insertProduct(ProductVO product) throws SQLException{
		int result = 0;

		String sql = "insert into product ("
				+ "pseq, kind, name, price1, price2, price3, content, image) "
				+ "values(product_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product.getKind());
			pstmt.setString(2, product.getName());
			pstmt.setInt(3, product.getPrice1());
			pstmt.setInt(4, product.getPrice2());
			pstmt.setInt(5, product.getPrice3());
			pstmt.setString(6, product.getContent());
			pstmt.setString(7, product.getImage());
			result = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return result;
	}

	public int updateProduct(ProductVO product) throws SQLException{
		int result = -1;
		String sql = "update product set kind=?, useyn=?, name=?"
				+ ", price1=?, price2=?, price3=?, content=?, image=?, bestyn=? "
				+ "where pseq=?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product.getKind());
			pstmt.setString(2, product.getUseyn());
			pstmt.setString(3, product.getName());
			pstmt.setInt(4, product.getPrice1());
			pstmt.setInt(5, product.getPrice2());
			pstmt.setInt(6, product.getPrice3());
			pstmt.setString(7, product.getContent());
			pstmt.setString(8, product.getImage());
			pstmt.setString(9, product.getBestyn());
			pstmt.setInt(10, product.getPseq());
			result = pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		return result;
	}
}