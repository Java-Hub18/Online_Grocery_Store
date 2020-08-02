<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dharmesh General Store | Forgot Password </title>
	<jsp:include page="layouts/header.jsp"></jsp:include>
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
					<li>Forgot Password</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- //page -->
<!-- log in -->
	<br>
	<div class="container h-100">
	<c:if test="${not empty success}">
								<div class="alert alert-primary alert-dismissible fade show">
        <strong>Forgot Password!</strong> You should soon receive an email allowing you 
        to reset your password.<br>Please make sure to check your spam and trash if you 
        can't find the email.<button type="button" class="close" data-dismiss="alert">&times;</button>
   								 </div>
							</c:if>
							<c:if test="${not empty error}">
								<div class="alert alert-danger alert-dismissible fade show">
        <strong>Forgot Password!</strong> Something went wrong.<br>Please try again later.
        <button type="button" class="close" data-dismiss="alert">&times;</button>
   								 </div>
							</c:if>
		<div class="d-flex justify-content-center h-100">
			<div class="row">
				<div class="col-md-12">
					<form:form action="${pageContext.request.contextPath}/customer/forgotPassword" modelAttribute="forgot-password" method="post">
						<div class="form-group">
							<label class="col-form-label">Email</label>
							<form:input type="email" path="email" class="form-control" placeholder="Email" name="email" required="required" />
							<form:errors path="email" cssStyle="color:red;" cssClass="error"/>
						</div>	
						<div class="right-w3l">
							<input type="submit" class="form-control" value="Submit">
						</div>
						<p class="text-center dont-do mt-3">Don't have an account?
							<a href="login">Register Now</a>
						</p>
					</form:form>
					</div>
				</div>
				</div>
			</div>
	<jsp:include page="layouts/footer.jsp"></jsp:include>
</body>
</html>