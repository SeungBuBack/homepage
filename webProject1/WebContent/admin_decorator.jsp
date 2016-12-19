<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 1L);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><decorator:title /></title>
<link rel="stylesheet" href="../admin/css/admin.css">
<script type="text/javascript" src="../admin/product/product.js"></script>
<c:choose>
	<c:when test="${empty workerId}">
		<script type="text/javascript">
			location.href = 'adminLoginForm.do';
		</script>
	</c:when>
</c:choose>
</head>
<body onload="go_ab()">
	<div id="wrap">
		<header>
			<div id="logo">
				<a href="adminLoginForm.do"> <img style="width: 800px"
					src="../admin/images/bar_01.gif"> <img
					src="../admin/images/text.gif">
				</a>
			</div>
			<input class="btn" type="button" value="logout" style="float: right;"
				onClick="location.href='adminLogout.do'">
		</header>
		<div class="clear"></div>

		<decorator:body />

		<div class="clear"></div>

		<footer>
			<hr>
			<div id="copy">
				All contents Copyright 2013 Nonage Inc. all rights reserved<br>
				Contact mail : Nonage@Nonage.com Tel: +82 64 123 4315 Fax +82 64 123
				4321
			</div>
		</footer>
	</div>
</body>
</html>

