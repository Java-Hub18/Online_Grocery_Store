<!-- header-bottom-->
	<div class="header-bot">
		<div class="container">
			<div class="row header-bot_inner_wthreeinfo_header_mid">
				<!-- logo -->
				<div class="col-md-3 logo_agile mb-4">
					<h1 class="text-center">
						<a href="${pageContext.request.contextPath}/home" class="font-weight-bold font-italic" style="font-size: 22px;">
							<img src="${pageContext.request.contextPath}/images/logo2.png" class="img-fluid">&nbsp;Dharmesh General Store
						</a>
					</h1>
				</div>
				<div class="col-md-9 header mt-4 mb-md-0 mb-4">
					<div class="row">
						<!-- search -->
						<div class="col-10 agileits_search">
							<form class="form-inline" action="/product/search" method="post">
								<input class="form-control mr-sm-2" type="search" name="keyword" placeholder="Search by product name or Price" aria-label="Search" required>
								<button class="btn my-2 my-sm-0" type="submit">Search</button>
							</form>
						</div>
						<!-- //search -->
						<!-- cart details -->
						<div class="col-2 top_nav_right text-center mt-sm-0 mt-2">
							<div class="wthreecartaits wthreecartaits2 cart cart box_1">
 								<button class="btn w3view-cart" onclick="window.location.href = '${pageContext.request.contextPath}/cart/my-cart'" type="submit" name="submit">
									<i class="fas fa-cart-arrow-down"></i>
								</button>
							</div>
						</div>
						<!-- //cart details -->
					</div>
				</div>
				<!-- //logo -->
				<!-- header-bot -->
			</div>
		</div>
	</div>
	<!-- shop locator (popup) -->
	<!-- //header-bottom -->
<!-- navigation -->
	<div class="navbar-inner">
		<div class="container">
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
				    aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ml-auto text-center mr-xl-5">
						<li class="nav-item active mr-lg-2 mb-lg-0 mb-2">
							<a class="nav-link" href="${pageContext.request.contextPath}/home">Home
								<span class="sr-only">(current)</span>
							</a>
						</li>
						<li class="nav-item mr-lg-2 mb-lg-0 mb-2">
							<a class="nav-link" href="${pageContext.request.contextPath}/product/all">All Products</a>
						</li>
						<li class="nav-item mr-lg-2 mb-lg-0 mb-2">
							<a class="nav-link" href="${pageContext.request.contextPath}/about">About Store</a>
						</li>
						<li class="nav-item">
								<a class="nav-link" href="${pageContext.request.contextPath}/contact">Contact Us</a>
							</li>
						
						<li class="nav-item dropdown mr-lg-2 mb-lg-0 mb-2">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Hi ${name}
							</a>
								<div class="dropdown-menu">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/cart/my-cart">My Cart</a>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/orders/my-orders">My Orders</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/customer/my-account">My Account</a>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/customer/changepassword">Change Password</a>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/customer/logout">Logout</a>
							</div>
						</li>	
						
					</ul>
				</div>
			</nav>
		</div>
	</div>
	<!-- //navigation -->
