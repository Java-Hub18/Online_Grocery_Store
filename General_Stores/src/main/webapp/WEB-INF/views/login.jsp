<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dharmesh General Store | Login </title>
	<jsp:include page="layouts/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="layouts/navigation-bar.jsp"></jsp:include>
	<jsp:include page="layouts/menu.jsp"></jsp:include>
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
					<li>Login</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- //page -->
<!-- log in -->
	<br>
	<div class="container h-100">
		<div class="d-flex justify-content-center h-100">
			<div class="row">
				<div class="col-md-12">
				<c:if test="${not empty backUrl}"><center><h4 class="text-center" style="color:red;">Login First</h4><hr></center></c:if>
					<form:form action="${pageContext.request.contextPath}/customer/loginCustomer" modelAttribute="customerLoginForm" method="post" class="validatedForm">
						<div class="contact-form1 form-group">
								<label class="col-form-label">Email</label>
								<form:input type="email" path="email" size="40" class="form-control" id="email" placeholder="Email" name="Email" required="required" />
								<form:errors path="email" cssStyle="color:red;" cssClass="error"/>
							</div>
							<div class="contact-form1 form-group">
								<label class="col-form-label">Password</label>
								<form:password path="password" maxlength="64" size="40" minlength="6" class="form-control" placeholder="Password" name="Password" id="password" required="required" />
								<form:errors path="password" cssStyle="color:red;" cssClass="error"/>
							</div>
							<div class="right-w3l">
								<input type="submit" class="form-control btn btn-primary" value="Login">
							</div>
					</form:form>
					<a href="register" style="color:#F45C5D">Register</a>
					<a href="forgot-password" style="color:#F45C5D;float:right;">Forgot Password?</a>
					</div>
				</div>
			</div>
		</div>
	<jsp:include page="layouts/footer.jsp"></jsp:include>
</body>
</html>