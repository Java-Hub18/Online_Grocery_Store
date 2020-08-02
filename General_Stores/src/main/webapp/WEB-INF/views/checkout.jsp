<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<li>Checkout</li>
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
				<span>C</span>heckout
			</h3>
	<div class="container">
	<table id="example" class="table table-hover">
      <thead>
        <tr>
          <th>Product Name</th>
          <th>Quantity</th>
          <th>Price</th>
          <th>Total Price</th>
        </tr>
      </thead>
      <tbody>
       <c:set var="count" value="1" scope="page"/>
		<tr>
<%--           <td><a href="${pageContext.request.contextPath}/product/productdetails?code=${code}">${itemName}</a></td> --%>
<td><a href="${pageContext.request.contextPath}/product/productdetails?code=${code}" target="_blank">
          <img src="${pageContext.request.contextPath}${image}" width="70" height="90" class="img img-responsiive" /></a>
          <br>${itemName}</td>
          <td>${quantity}</td>
          <td>${price}</td>
          <td>${totalPrice}</td>
        </tr> 
      </tbody>
    </table>
			</div>
		<c:if test="${not empty count}">	
			<div class="checkout-left">
				<div class="address_form_agile mt-sm-5 mt-4">
					<h4 class="mb-sm-4 mb-3">Billing Address</h4>
					<form action="${pageContext.request.contextPath}/order/checkout" method="post" class="creditly-card-form agileinfo_form">
						<div class="creditly-wrapper wthree, w3_agileits_wrapper">
							<div class="information-wrapper">
								<div class="first-row">
									<div class="controls form-group">
									<label class="col-form-label"><b>Your Name</b></label>
										<input class="billing-address-name form-control" type="text" name="customerName" value="${name}" required="required" />
									</div>
									<div class="w3_agileits_card_number_grids">
										<div class="w3_agileits_card_number_grid_left form-group">
											<div class="controls">
											<label class="col-form-label"><b>Phone Number</b></label>
												<input type="text" class="form-control" value="${phone}" maxlength="10" name="customerPhone" id="txtPhone" required="required" />
												<span id="spnPhoneStatus"></span>
											</div>
										</div>
										<div class="w3_agileits_card_number_grid_right form-group">
											<div class="controls">
											<label class="col-form-label"><b>Address</b></label>
												<textarea name="address" class="form-control" rows="4" cols="45" required="required">${address}</textarea>
											</div>
										</div>
										<div class="w3_agileits_card_number_grid_right form-group">
											<div class="controls">
											<label class="col-form-label"><b>Pin Code</b> [As of now product delivery for pin code 400601 is available.]</label>
												<input type="text" class="form-control" value="400601" maxlength="6" id="pinCode" name="pinCode" required="required" />
												<span id="spnPinStatus"></span>
											</div>
										</div>
									</div>
									<div class="controls form-group">
									<label class="col-form-label"><b>Select Address Type</b></label>
										<select class="option-w3ls" name="addressType" required="required">
											<option>Home</option>
											<option>Office</option>
											<option>Commercial</option>
										</select>
									</div>
								</div>
								<div class="col-sm-12 text-left">
         			<input type="submit" id="submit" class="btn btn-success btn-md center-block" value="Buy" style="width: 100px;" />
                 	&emsp;<input type="button" class="btn btn-danger reset" value="Clear" style="width: 100px;" />
     			</div> 								
							</div>
						</div>
					</form>
				</div>
			</div>
			</c:if>
		</div>
	</div>
	<!-- //checkout page -->
	<div>
		<div>
		</div>
	</div>
<jsp:include page="layouts/cart-footer.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		$(".reset").click(function() {
		$(this).closest('form').find("input[type=text], textarea").val("");
		});
	});
		
	$(document).ready(function() {
	    $('#txtPhone').blur(function(e) {
	        if (validatePhone('txtPhone')) {
	            
	        }
	        else {
	            $('#spnPhoneStatus').html('Invalid Phone Number.');
	            $('#spnPhoneStatus').css('color', 'red');
	        }
	    });
	});

	function validatePhone(txtPhone) {
	    var a = document.getElementById(txtPhone).value;
	    var filter = /^\d*(?:\.\d{1,2})?$/;
	    if (filter.test(a)) {
	        return true;
	    }
	    else {
	        return false;
	    }
	}
	
	$(document).ready(function() {
	    $('#pinCode').blur(function(e) {
	        if (validateCode('pinCode')) {
	        }
	        else {
	            $('#spnPinStatus').html('Invalid Pin Code.');
	            $('#spnPinStatus').css('color', 'red');
	        }
	    });
	});

	function validateCode(pinCode) {
	    var p = document.getElementById(pinCode).value;
	    var filter = /^\d*(?:\.\d{1,2})?$/;
	    if (filter.test(p)) {
	        return true;
	    }
	    else {
	        return false;
	    }
	}
	
	$(document).ready(function() {
	    $('#submit').click(function(e) {
	    	var mobileNum = $("#txtPhone").val();
	    	var pinCode = $("#pinCode").val();
	    	var validateMobNum= /^\d*(?:\.\d{1,2})?$/;
	    	if (validateMobNum.test(mobileNum) && mobileNum.length == 10) {
	    	    if (validateMobNum.test(pinCode) && pinCode.length == 6) {
	    	    return true;
	    	}
	    	else {
	    	    $('#spnPinStatus').html('Please Enter Valid Pin Code.');
	            $('#spnPinStatus').css('color', 'red');
	            e.preventDefault();
	            return false;
	    	}
	    	}
	    	else {
	    	    $('#spnPhoneStatus').html('Please Enter Valid Phone Number.');
	            $('#spnPhoneStatus').css('color', 'red');
	            e.preventDefault();
	            return false;
	    	}
	    });
	});
</script>
</body>
</html>