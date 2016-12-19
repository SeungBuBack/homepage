<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- 헤더 파일 포함하기 -->
<%-- <%@ include file="header.jsp"%> --%>

<!-- 메인 페이지 들어가는 곳 -->
<!-- 메인 이미지 배너 삽입 -->

<div class="clear"></div>
<div id="main_img">
	<img src="images/main_img.jpg">
</div>
<!--메인 이미지 들어가는 곳 끝--->
<div class="clear"></div>
<div id="front">
	<h2>New Item</h2>
	<div id="bestProduct">
		<c:forEach items="${newProductList }" var="productVO">
			<div id="item">
				<a
					href="productDetail.do?pseq=${productVO.pseq}">
					<img src="product_images/${productVO.image}" />
					<h3>${productVO.name}</h3>
					<p>${productVO.price2}</p>
				</a>
			</div>
		</c:forEach>
	</div>
	<div class="clear"></div>
	<h2>Best Item</h2>
	<div id="bestProduct">
		<c:forEach items="${bestProductList}" var="productVO">
			<div id="item">
				<a
					href="productDetail.do?pseq=${productVO.pseq}">
					<img src="product_images/${productVO.image}" />
					<h3>${productVO.name}</h3>
					<p>${productVO.price2}</p>
				</a>
			</div>
		</c:forEach>
	</div>
	<div class="clear"></div>
</div>


<!-- 풋터 파일 포함하기 -->
<%-- <%@ include file="footer.jsp"%> --%>
