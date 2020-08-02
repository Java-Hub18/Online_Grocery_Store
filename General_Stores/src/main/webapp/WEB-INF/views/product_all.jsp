<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dharmesh General Store | Home </title>
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
	<jsp:include page="layouts/banner.jsp"></jsp:include>
	<!-- top Products -->
	<div class="ads-grid py-sm-5 py-4">
		<div class="container-fluid py-xl-4 py-lg-2">
			<div class="row">
				<!-- product left -->
				<div class="agileinfo-ads-display col-lg-12">
					<div class="wrapper">
						<!-- first section -->
						<div class="px-sm-4 px-3 py-sm-5 py-3 mb-4">
							<c:if test="${not empty products}"><h3 class="heading-tittle text-center font-italic">Our Products</h3></c:if>
							<div class="row">
				<c:choose>	
					<c:when test="${not empty products}">
						<c:forEach var="product" items="${products}">
								<div class="col-md-3 product-men mt-5" style="width:100%;">
									<div class="men-pro-item simpleCart_shelfItem">
										<div class="men-thumb-item text-center">
											<img src="${pageContext.request.contextPath}${product.image}" class="img-fluid">
											<div class="men-cart-pro">
												<div class="inner-men-cart-pro" style="margin-top:-55px;">
													<a href="${pageContext.request.contextPath}/product/productdetails?code=${product.code}" class="link-product-add-cart">Quick View</a>
												</div>
											</div>
										</div>
										<div class="item-info-product text-center border-top mt-4">
											<h4 class="pt-1">
												<a href="${pageContext.request.contextPath}/product/productdetails?code=${product.code}">${product.name}</a>
											</h4>
											<div class="info-product-price my-2">
												<span class="item_price">&#x20b9;${product.price}</span>
												<del>&#x20b9;${product.mrpPrice}</del>
											</div>
											<div class="snipcart-details top_brand_home_details item_add single-item hvr-outline-out">
												<form action="#" method="post">
													<fieldset>
														<input type="hidden" name="cmd" value="_cart" />
														<input type="hidden" name="add" value="1" />
														<input type="hidden" name="item_id" value="${product.id}" />
														<input type="hidden" name="business" value=" " />
														<input type="hidden" name="item_name" value="${product.name}" />
														<input type="hidden" name="amount" value="${product.price}" />
														<input type="hidden" name="mrp" value="${product.mrpPrice}" />
														<input type="hidden" name="currency_code" value="INR" />
														<input type="hidden" name="return" value=" " />
														<input type="hidden" name="cancel_return" value=" " />
<div class="row">
   	<div class="col-sm-12 text-center">
         <input type="submit" class="btn btn-primary btn-md center-block" value="Add to Cart" />&nbsp;
         <a class="btn btn-danger btn-md center-block" href="${pageContext.request.contextPath}/product/productdetails?code=${product.code}">Buy Now</a>
     </div>
</div>
													</fieldset>
												</form><br>
											</div> 
										</div>
									</div>
								</div>
								</c:forEach>		
					</c:when>
					<c:otherwise>
						<c:if test="${empty products}"><div class="col-12 text-center"><h1>THERE IS NO ITEMS</h1></div></c:if>
				</c:otherwise>
				</c:choose>
					</div>
				</div>
			</div>
			</div>
			</div>
		</div>
	</div>
	<jsp:include page="layouts/footer.jsp"></jsp:include>
</body>
</html>