<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>Nonage Shop</title>
<link href="css/shopping.css" rel="stylesheet">
<script type="text/javascript" src="product/product.js"></script>
<script type="text/javascript" src="member/member.js"></script>
<script type="text/javascript" src="mypage/mypage.js"></script>
</head>

<body>
	<div id="wrap">
		<!--헤더파일 들어가는 곳 시작 -->
		<header>
			<!--로고 들어가는 곳 시작--->
			<div id="logo">
				<a href="index.do"> <img
					src="images/logo.gif" width="180" height="100" alt="nonageshop">
				</a>
			</div>
			<!--로고 들어가는 곳 끝-->
			<nav id="catagory_menu">
				<ul>
					<c:choose>
						<c:when test="${empty sessionScope.loginUser}">
							<li><a href="loginForm.do"
								style="width: 110px;">LOGIN&nbsp;(CUSTOMER</a> <a
								href="admin/LoginForm.do">| admin)</a></li>
							<li>/</li>
							<li><a href="contract.do">JOIN</a></li>
						</c:when>
						<c:otherwise>
							<li style="color: orange">
								${sessionScope.loginUser.name}(${sessionScope.loginUser.id})</li>
							<li><a href="logout.do">LOGOUT</a></li>
						</c:otherwise>
					</c:choose>
					<li>/</li>
					<li><a href="cartList.do">CART</a></li>
					<li>/</li>
					<li><a href="mypage.do">MY PAGE</a></li>
					<li>/</li>
					<li><a href="qnaList.do">Q&amp;A(1:1)</a>
					</li>
				</ul>
			</nav>

			<nav id="top_menu">
				<ul>
					<li><a href="catagory.do?kind=1">Heels</a>
					</li>
					<li><a href="catagory.do?kind=2">Boots</a>
					</li>
					<li><a href="catagory.do?kind=3">Sandals</a>
					</li>
					<li><a href="catagory.do?kind=4">Sneakers</a>
					</li>
					<li><a href="catagory.do?kind=5">On
							Sale</a></li>
				</ul>
			</nav>
			<div class="clear"></div>
			<hr>
		</header>
		<!--헤더파일 들어가는 곳 끝 -->