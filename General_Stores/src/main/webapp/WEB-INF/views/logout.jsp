<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dharmesh General Store | Logout </title>
	<jsp:include page="layouts/header.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/js/backNoWork.js"></script>
</head>
<body>
	<jsp:include page="layouts/navigation-bar.jsp"></jsp:include>
	<jsp:include page="layouts/menu.jsp"></jsp:include>
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
					<li>Logout</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- //page -->
<br>
	<div class="col-12 text-center">
			<div>
				<h5 class="subtitle">You have been logged-out successfully.</h5>
			</div>
		</div>
	<jsp:include page="layouts/footer.jsp"></jsp:include>
</body>
</html>