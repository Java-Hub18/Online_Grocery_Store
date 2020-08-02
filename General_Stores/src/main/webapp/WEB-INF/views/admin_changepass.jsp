<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dharmesh General Store | Change Password </title>
	<jsp:include page="layouts/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="layouts/admin_menu.jsp"></jsp:include>
	<!-- banner-2 -->
	<div class="page-head_agile_info_w3l"></div>
	<!-- //banner-2 -->
	<!-- page -->
	<div class="services-breadcrumb">
		<div class="agile_inner_breadcrumb">
			<div class="container">
				<ul class="w3_short">
					<li>
						<a href="/admin/home">Home</a>
						<i>|</i>
					</li>
					<li>Change Password</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- //page -->
<!-- log in -->
	<br>
	<div class="container h-100">
	<c:if test="${not empty success}">
		<div class="alert alert-success alert-dismissible fade show">
        <strong>Change Password!</strong> Password successfully changed.
        Click  <a href="${pageContext.request.contextPath}/admin/" style="text-decoration: underline;" class="title">Here</a> to login.<button type="button" class="close" data-dismiss="alert">&times;</button>
   		</div>
	</c:if>
		<c:if test="${not empty unmatched}">
				<div class="alert alert-danger alert-dismissible fade show">
        <strong>Change Password!</strong> Password &amp; Confirm Password doesn't match.<br>Please try again later.
        <button type="button" class="close" data-dismiss="alert">&times;</button>
   				</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-danger alert-dismissible fade show">
        <strong>Change Password!</strong> Password &amp; Confirm password can't be empty.
        <button type="button" class="close" data-dismiss="alert">&times;</button>
   			</div>
		</c:if>
		<c:if test="${not empty notFound}">
			<div class="alert alert-danger alert-dismissible fade show">
        <strong>Change Password!</strong> Could not found a user with this email.
        <button type="button" class="close" data-dismiss="alert">&times;</button>
   			 </div>
		</c:if>
		<div class="d-flex justify-content-center h-100">
		    <div class="row">
				<div class="col-md-12">
					<form action="${pageContext.request.contextPath}/admin/changeAdminPassword" class="validatedForm" method="post">
						<div class="form-group">
							<input type="password" class="form-control" size="35" placeholder="Password" name="password" id="password" required="required">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" size="35" placeholder="Confirm Password" name="confirm_password" id="confirm_password" required="required">
						</div>
						<div class="right-w3l">
							<input class="form-control btn btn-primary" type="button" value="Submit" onClick="validatePassword();" />
						</div>
					</form>
					</div>
				</div>
				</div>
			</div>
	<jsp:include page="layouts/footer.jsp"></jsp:include>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js" type="text/javascript"></script>
	<script>
    function validatePassword() {
        var validator = $(".validatedForm").validate({
            rules: {
                password: "required",
                confirm_password: {
                    equalTo: "#password"
                }
            },
            messages: {
                password: " Enter Password",
                confirm_password: " Enter Confirm Password Same as Password."
            }
        });
        if (validator.form()) {
            $(".validatedForm").submit();
        }
    }
    </script>
</body>
</html>