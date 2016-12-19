<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@ include file="../header.jsp" %> --%>

<%@ include file="sub_img.jsp"%>
<%@ include file="sub_menu.jsp"%>

<article>
	<h2>Item</h2>
	<c:forEach items="${productKindList }" var="productVO">
		<div id="item">
			<a href="productDetail.do?pseq=${productVO.pseq}"> <img
				src="product_images/${productVO.image}" />
				<h3>${productVO.name}</h3>
				<p>${productVO.price2}</p>
			</a>
		</div>
	</c:forEach>
	<div class="clear"></div>
</article>


<%-- <%@ include file="../footer.jsp" %>     --%>