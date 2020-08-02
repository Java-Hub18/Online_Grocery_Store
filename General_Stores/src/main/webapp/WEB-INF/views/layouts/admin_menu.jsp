<!-- navigation -->
	<div class="navbar-inner">
		<div class="container">
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<h1 class="text-left mr-12">
				<a href="${pageContext.request.contextPath}/admin/home" class="font-weight-bold font-italic" style="font-size: 22px;color:#F45C5D;">
					<img src="${pageContext.request.contextPath}/images/logo2.png" class="img-fluid">&nbsp;Dharmesh General Store
				</a>
			</h1>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
				    aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ml-auto text-center mr-xl-5">
						<li class="nav-item active mr-lg-2 mb-lg-0 mb-2">
							<a class="nav-link" href="${pageContext.request.contextPath}/admin/home">Home
								<span class="sr-only">(current)</span>
							</a>
						</li>
						<li class="nav-item dropdown mr-lg-2 mb-lg-0 mb-2">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Product
							</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/product/view">View Products</a>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/product/add">Add Product</a>
							</div>
						</li>
						<li class="nav-item dropdown mr-lg-2 mb-lg-0 mb-2">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Order
							</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/order/view">View All Orders</a>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/order/pending-orders">View Pending Orders</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/order/delivered-orders">View Delivered Orders</a>
<%-- 								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/order/cancelled-orders">Cancelled Orders</a> --%>
							</div>
						</li>
						<li class="nav-item dropdown mr-lg-2 mb-lg-0 mb-2">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Customer
							</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/customer/view">View Customers</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/contact/view">View Contacts</a>
							</div>
						</li>
						
						<li class="nav-item dropdown mr-lg-2 mb-lg-0 mb-2">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Hi ${aname}
							</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/my-account">My Account</a>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/changepassword">Change Password</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/logout">Logout</a>
							</div>
						</li>	
						
					</ul>
				</div>
			</nav>
		</div>
	</div>
	<!-- //navigation -->
