<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<c:if test="${empty email}">
	<c:redirect url="${pageContext.request.contextPath}/user/sign-in"/>  
</c:if>
	<title>Dharmesh General Store | Checkout</title>
	<jsp:include page="layouts/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="layouts/user-navigation.jsp"></jsp:include>
	<jsp:include page="layouts/user-menu.jsp"></jsp:include>
	<!-- banner-2 -->
	<div class="page-head_agile_info_w3l"></div>
	<!-- //banner-2 -->
	<!-- page -->
	<div class="services-breadcrumb">
		<div class="agile_inner_breadcrumb">
			<div class="container">
				<ul class="w3_short">
					<li>
						<a href="${pageContext.request.contextPath}/home">Home</a>
						<i>|</i>
					</li>
					<li>Order</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- //page -->
	<!-- checkout page -->
	<div class="privacy py-sm-5 py-4">
		<div class="container py-xl-4 py-lg-2">
			<!-- tittle heading -->
			<h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
				<span>O</span>rder
			</h3>
		<c:if test="${not empty success}">
			<c:set var="totalAmount" value="${totalAmount}" scope="session"></c:set>
			<c:set var="totalMrp" value="${totalMrp}" scope="session"></c:set>
			<fmt:formatDate var="orderDate" pattern="dd-MMM-yyyy" value="${orderDate}" scope="session" />
			<c:set var="paymentId" value="${paymentId}" scope="session"></c:set>
			<c:set var="pinCode" value="${pinCode}" scope="session"></c:set>
			<c:set var="totalQty" value="${totalQty}" scope="session"></c:set>
			<c:set var="totalSavings" value="${totalSavings}" scope="session"></c:set>
	<h4 class="text-center">Thank you for your order. <a href="${pageContext.request.contextPath}/orders/getPdf">
	click</a> here to send bill to your email.</h4>
		</c:if>
		<c:if test="${not empty pdf}">
			<h4 class="text-center">Email has been sent.<a href="${pageContext.request.contextPath}/orders/my-orders">
	click</a> here to check your orders.</h4>
		</c:if>
		<c:if test="${not empty error_pdf}">
			<h4 class="text-center">Oops! Something Went Wrong While Sending Email.</h4>
		</c:if>
		<c:if test="${not empty error}">
			<h4 class="text-center">Oops! Something Went Wrong.</h4>
		</c:if>
		</div>
	</div>
	<!-- //checkout page -->
	<div>
		<div>
		</div>
	</div>
<jsp:include page="layouts/footer.jsp"></jsp:include>
</body>
</html>