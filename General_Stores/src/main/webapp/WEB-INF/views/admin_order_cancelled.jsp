<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<c:if test="${empty aemail}">
	<c:redirect url="${pageContext.request.contextPath}/user/sign-in"/>  
</c:if>
	<title>Dharmesh General Store | Cancelled Orders </title>
	<jsp:include page="layouts/header.jsp"></jsp:include>
	<link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet">
<style type="text/css">
@media only screen and (max-width: 760px),
    (min-device-width: 768px) and (max-device-width: 1024px)  {
    	p#mb {
    		display: none;
    		visibility: hidden;
    	}
    }
@media(max-width:623px) {
	p#mb {
		color:#ee2d50;
		font-weight:bold;
		align-content:center;
		display: unset;
		visibility:visible;
	}
table {
    display: block;
    overflow-x: auto;
}
}
	</style>
</head>
<body>
	<jsp:include page="layouts/admin_menu.jsp"></jsp:include>
	<div class="page-head_agile_info_w3l"></div>
	<!-- page -->
	<div class="services-breadcrumb">
		<div class="agile_inner_breadcrumb">
			<div class="container">
				<ul class="w3_short">
					<li>
						<a href="${pageContext.request.contextPath}/admin/home">Home</a>
						<i>|</i>
					</li>
					<li>Cancelled Orders</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- //page -->
<div class="contact py-sm-5 py-4">
<div class="container-fluid py-xl-4 py-lg-2">
<c:if test="${not empty delete}">
				<div class="alert alert-success alert-dismissible fade show text-center">
					Order removed successfully.
					<button type="button" class="close" data-dismiss="alert">&times;</button>
				</div>	
</c:if>
<c:if test="${not empty delivered}">
				<div class="alert alert-success alert-dismissible fade show text-center">
					Order Status Changed to Delivered.
					<button type="button" class="close" data-dismiss="alert">&times;</button>
				</div>	
</c:if>
<c:if test="${not empty pendings}">
				<div class="alert alert-success alert-dismissible fade show text-center">
					Order Status Changed to Pending.
					<button type="button" class="close" data-dismiss="alert">&times;</button>
				</div>	
</c:if>
<p id="mb">Slide Right to see full table.</p>
<h3 class="text-center">Cancelled Orders</h3>
	<table id="example" class="table table-hover table-bordered text-center">
				<thead>
					<tr>
						<th>SR. No.</th>
						<th>Order No.</th>
						<th>Customer Details</th>
						<th>Item</th>
						<th>Qty.</th>
						<th>Total Amount (&#x20b9;)</th>
						<th>Status</th>
						<th>Date</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="count" value="0" scope="page"></c:set>
					<c:forEach var="orders" items="${cancel}">
					<c:set var="count" value="${count + 1}" scope="page"></c:set>
						<tr>
							<td>${count}</td>
							<td>${orders.paymentId}</td>
							<td>${orders.order.customerName} | 
							${orders.order.customerEmail} | 
							${orders.order.customerPhone} | 
							${orders.order.customerAddress} | 
							${orders.order.customerAddressType} | 
							${orders.order.pinCode}</td>
							<td><a href="${pageContext.request.contextPath}/product/productdetails?code=${orders.product.code}" target="_blank">
          <img src="${pageContext.request.contextPath}${orders.product.image}" width="70" height="90" class="img img-responsiive" /></a>
          <br>${orders.product.name}</td>
          					<td>${orders.quantity}</td>
							<td>${orders.amount}</td>
							<td><span class="badge badge-info">${orders.orderStatus}</span></td>
							<td><fmt:formatDate pattern="dd-MMM-yyyy hh:mm:ss" value="${orders.order.orderDate}" /></td>
							<td>
						<a href="${pageContext.request.contextPath}/admin/order/changeStatus/Delivered/${orders.id}" class="btn btn-success" onclick="return confirm('Change Order Status to Delivered?');">Deliver</a>
						 <hr> <a href="${pageContext.request.contextPath}/admin/order/changeStatus/Pending/${orders.id}" class="btn btn-warning" onclick="return confirm('Change Order Status to Pending?');">Pending</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="layouts/cart-footer.jsp"></jsp:include>
	<!-- Bootstrap core JavaScript-->
<!-- Page level plugin JavaScript-->
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
<script>
	$(document).ready(function() {
	    $('#example').DataTable();
	} );
	</script>
</body>
</html>
