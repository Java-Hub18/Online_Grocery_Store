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
	<jsp:include page="layouts/user-navigation.jsp"></jsp:include>
<!-- log in -->
	<br><br>
	<div class="container h-100">
		<div class="d-flex justify-content-center h-100">
			<div class="row">
				<div class="col-md-12">
				<h2 class="text-center">Admin Login</h2><hr>
					<form:form action="${pageContext.request.contextPath}/admin/home" modelAttribute="AdminLoginForm" method="post">
						<div class="form-group">
							<label class="col-form-label">Email</label>
							<form:input type="email" path="email" class="form-control" size="30" placeholder="Email" name="email" required="required" />
							<form:errors path="email" cssStyle="color:red;" cssClass="error"/>
						</div>	
						<div class="form-group">
							<label class="col-form-label">Password</label>
							<form:password path="password" size="30" class="form-control" placeholder="Password" name="Password" id="password" required="required" />
							<form:errors path="password" cssStyle="color:red;" cssClass="error"/>
						</div>
						<div class="right-w3l">
							<input type="submit" class="form-control" value="Login">
							<a href="/admin/forgot-password" style="color:#F45C5D">Forgot Password?</a>
						</div>
					</form:form>
					</div>
				</div>
				</div>
			</div>
	<jsp:include page="layouts/footer.jsp"></jsp:include>
</body>
</html>