<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dharmesh General Store | About </title>
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
					<li>About Us</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- //page -->
<!-- log in -->
	<br>
<!-- about -->
	<div class="welcome py-sm-5 py-4">
		<div class="container py-xl-4 py-lg-2">
			<!-- tittle heading -->
			<h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
				<span>A</span>bout
				<span>U</span>s</h3>
			<!-- //tittle heading -->
			<div class="row">
				<div class="col-lg-6 welcome-left">
					<h3>About Store</h3>
					<h4 class="my-sm-3 my-2">GrocSMART(Dharmesh General Store) is a low-price online Grocery store, which has many vital ranging products consumed in our everyday Breakfast, Meals & Dinners. We have Fresh Vegetables, Staples, Beverages, Personal care products & many other products needed daily. All the products on our
					 website are distinguished based on their categories, which gives you the hassle-free shopping experience ever with minimum time spent for your shopping.</h4>
				</div>
				
				<div class="col-lg-6 welcome-right-top mt-lg-0 mt-sm-5 mt-4">
					<img src="${pageContext.request.contextPath}/images/store.png" class="img-fluid" alt=" ">
				</div>
				<div class="welcome-left">
					<br><br><h3 class="text-left" style="color:#F45C5D;">&ensp;What We Do</h3>
					<h4 class="my-sm-3" style="margin-left:15px;">We guarantee you the best quality products with the lowest price. Right now we only serve you in Thane. To have a more convenient shopping experience, for you we deliver your selected products at your doorstep at your selected time slot. As in today's era every person sense short in time & also its hard to find if the consumable products are healthy/natural. So to tackle this issue we serve you with the finest & hand-picked products from all the categories, 
					to save your time from the conventional shopping as well as
					 from the problems like getting stuck in long queues, the parking fees to be paid, carrying you heavy shopping bags & etc.</h4>
				</div>
				<div class="welcome-left">
					<br><br><h3 class="text-left" style="color:#F45C5D;">&ensp;Our Vision</h3>
					<h4 class="my-sm-3" style="margin-left:15px;">
					<li>Redefining India's freshest and finest food experience by 2020.</li>
					<li>To provide a brighter customer experience with an assortment of finest local and international foods and highest level of service. </li>
					<li>To spread the joy of food.</li>
					</h4>
				</div>
			</div>
		</div>
	</div>
	<!-- //about -->
	<jsp:include page="layouts/footer.jsp"></jsp:include>
</body>
</html>