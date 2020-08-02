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
<!-- 					&emsp;&emsp;&emsp;&emsp;<i class="fas fa-phone" style="color:black;font-size:14px;"></i>&nbsp;<a style="font-size:14px;" href="tel:+91 9920887594">+91 992 088 7594 Order Now</a> -->
				</div>
				<!-- //logo -->
				<!-- header-bot -->
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
								<form action="#" method="post" class="last">
									<input type="hidden" name="cmd" value="_cart">
									<input type="hidden" name="display" value="1">
									<button class="btn w3view-cart" type="submit" name="submit" value="">
										<i class="fas fa-cart-arrow-down"></i>
									</button>
								</form>
							</div>
						</div>
						<!-- //cart details -->
					</div>
				</div>
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
						<li class="nav-item mr-lg-2 mb-lg-0 mb-2">
							<a class="nav-link" href="${pageContext.request.contextPath}/customer/login">Login</a>
						</li>	
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.request.contextPath}/customer/register">Register</a>
						</li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
	<!-- //navigation -->