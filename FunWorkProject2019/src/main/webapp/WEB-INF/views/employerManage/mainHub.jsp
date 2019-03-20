<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<title>公司詳細資料頁面</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>

<style>
.card-text-size {
	font-size: 14px;
}

.footerbackground {
	background: #343a40;
	color: white;
}

.nav-item:hover {
	background-color: gray;
	border-radius: 15px;
}

.asideblock {
	height: 600px;
}
</style>
<body>
	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
		<div class="col-sm-2">
			<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp"%>
			</div>
			<div class="col-sm-8">
				<section class="container">
					<div class="col-sm-12">
						<!-- demo page inserted -->

						<div id="content-wrapper">
							<!-- Breadcrumbs-->
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
								<li class="breadcrumb-item active">Overview</li>
							</ol>

							<!-- Icon Cards-->
							<div class="row">
								<div class="col-sm-6 mb-3">
									<div class="card bg-light mb-2" style="width: 20rem;">
										<div class="card-header">
											免費會員 &nbsp<small>目前會員等級</small>
										</div>
										<div class="card-body">
											<label>每月張貼工作額度: &nbsp</label><span>3 則 </span><br> <label>張貼工作期限:
												&nbsp</label><span> 7 天</span><br> <label>邀請應徵功能: &nbsp</label><span>
											</span><br> <label>職缺審核排序: &nbsp</label><span> 正常</span><br>
										</div>
									</div>
								</div>
								<div class="col-sm-6 mb-3">
									<div class="card text-white bg-warning mb-3"
										style="width: 20rem;"">
										<div class="card-header">Header</div>
										<div class="card-body">
											<h5 class="card-title">Warning card title</h5>
											<p class="card-text">Some quick example text to build on
												the card title and make up the bulk of the card's content.</p>
										</div>
									</div>
								</div>
							</div>
							<!-- Breadcrumbs-->
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
								<li class="breadcrumb-item active">Overview</li>
							</ol>
							<div class="row">
								<div class="col-xl-3 col-sm-6 mb-3">
									<div class="card text-white bg-primary o-hidden h-100">
										<div class="card-body">
											<div class="card-body-icon">
												<i class="fas fa-fw fa-comments"></i>
											</div>
											<div class="mr-5">26 New Messages!</div>
										</div>
										<a class="card-footer text-white clearfix small z-1" href="#">
											<span class="float-left">View Details</span> <span
											class="float-right"> <i class="fas fa-angle-right"></i>
										</span>
										</a>
									</div>
								</div>
								<div class="col-xl-3 col-sm-6 mb-3">
									<div class="card text-white bg-warning o-hidden h-100">
										<div class="card-body">
											<div class="card-body-icon">
												<i class="fas fa-fw fa-list"></i>
											</div>
											<div class="mr-5">11 New Tasks!</div>
										</div>
										<a class="card-footer text-white clearfix small z-1" href="#">
											<span class="float-left">View Details</span> <span
											class="float-right"> <i class="fas fa-angle-right"></i>
										</span>
										</a>
									</div>
								</div>
								<div class="col-xl-3 col-sm-6 mb-3">
									<div class="card text-white bg-success o-hidden h-100">
										<div class="card-body">
											<div class="card-body-icon">
												<i class="fas fa-fw fa-shopping-cart"></i>
											</div>
											<div class="mr-5">123 New Orders!</div>
										</div>
										<a class="card-footer text-white clearfix small z-1" href="#">
											<span class="float-left">View Details</span> <span
											class="float-right"> <i class="fas fa-angle-right"></i>
										</span>
										</a>
									</div>
								</div>
								<div class="col-xl-3 col-sm-6 mb-3">
									<div class="card text-white bg-danger o-hidden h-100">
										<div class="card-body">
											<div class="card-body-icon">
												<i class="fas fa-fw fa-life-ring"></i>
											</div>
											<div class="mr-5">13 New Tickets!</div>
										</div>
										<a class="card-footer text-white clearfix small z-1" href="#">
											<span class="float-left">View Details</span> <span
											class="float-right"> <i class="fas fa-angle-right"></i>
										</span>
										</a>
									</div>
								</div>
							</div>
							<!-- Breadcrumbs-->
							<ol class="breadcrumb">
								<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
								<li class="breadcrumb-item active">Overview</li>
							</ol>

							<!-- Area Chart Example-->
							<div class="row">
								<div class="card mb-3">
									<div class="card-header">
										<i class="fas fa-chart-area"></i> Area Chart Example
									</div>
									<div class="card-body">
										<canvas id="myAreaChart" width="100%" height="30"></canvas>
									</div>
									<div class="card-footer small text-muted">Updated
										yesterday at 11:59 PM</div>
								</div>
							</div>

														<!-- Sticky Footer -->
							<footer class="sticky-footer">
								<div class="container my-auto">
									<div class="copyright text-center my-auto">
										<span>Copyright © Your Website 2019</span>
									</div>
								</div>
							</footer>

						</div>
						<!-- /.content-wrapper -->
						<!-- demo page ended -->
					</div>
				</section>
			</div>

			<div class="col-sm-2" style="float: right">預留區塊</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter footerbackground">
			<div class="col text-center">Copyright© 2019 趣打工 All rights
				reserved.</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>