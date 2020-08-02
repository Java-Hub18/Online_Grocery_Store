<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dharmesh General Store | Page 404 </title>
	<jsp:include page="layouts/header.jsp"></jsp:include>
</head>
<body>
	<c:choose>
	<c:when test="${not empty email}">
		<jsp:include page="layouts/user-navigation.jsp"></jsp:include>
		<jsp:include page="layouts/user-menu.jsp"></jsp:include>
	</c:when>
	<c:otherwise>
		<jsp:include page="layouts/navigation-bar.jsp"></jsp:include>
		<jsp:include page="layouts/menu.jsp"></jsp:include>
	</c:otherwise>
</c:choose>
	<!-- banner-2 -->
	<div class="page-head_agile_info_w3l">

	</div>
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
					<li>Page 404</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- //page -->
<br><!-- 404 page start -->
<div class="fourzerofour">
	<div class="container">
		<div class="row">
			<div class="col-12 text-center">
				<div class="img">
					<img class="img-fluid" src="${pageContext.request.contextPath}/images/404.png" alt="">
				</div><br>
				<div class="text-center dont-do mt-3">
		<a href="${pageContext.request.contextPath}/home">Go back to Home</a>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 404 page End -->
	<jsp:include page="layouts/footer.jsp"></jsp:include>
</body>
</html>