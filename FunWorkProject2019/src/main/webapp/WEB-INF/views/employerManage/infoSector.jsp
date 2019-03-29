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
			<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp"%>
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

							<!-- DataTables Example -->
							<div class="card mb-3">
								<div class="card-header">
									<i class="fas fa-table"></i> Data Table Example
								</div>
								<div class="card-body">
									<!-- 										<div class="table-responsive"> -->
									<div>
										<table class="table table-bordered" id="dataTable"
											width="100%" cellspacing="0">
											<thead>
												<tr>
													<th>Name</th>
													<th>Position</th>
													<th>Office</th>
													<th>Age</th>
													<th>Start date</th>
													<th>Salary</th>
												</tr>
											</thead>
											<tfoot>
												<tr>
													<th>Name</th>
													<th>Position</th>
													<th>Office</th>
													<th>Age</th>
													<th>Start date</th>
													<th>Salary</th>
												</tr>
											</tfoot>
											<tbody>
												<tr>
													<td>Tiger Nixon</td>
													<td>System Architect</td>
													<td>Edinburgh</td>
													<td>61</td>
													<td>2011/04/25</td>
													<td>$320,800</td>
												</tr>
												<tr>
													<td>Garrett Winters</td>
													<td>Accountant</td>
													<td>Tokyo</td>
													<td>63</td>
													<td>2011/07/25</td>
													<td>$170,750</td>
												</tr>
												<tr>
													<td>Ashton Cox</td>
													<td>Junior Technical Author</td>
													<td>San Francisco</td>
													<td>66</td>
													<td>2009/01/12</td>
													<td>$86,000</td>
												</tr>
												<tr>
													<td>Jenette Caldwell</td>
													<td>Development Lead</td>
													<td>New York</td>
													<td>30</td>
													<td>2011/09/03</td>
													<td>$345,000</td>
												</tr>
												<tr>
													<td>Yuri Berry</td>
													<td>Chief Marketing Officer (CMO)</td>
													<td>New York</td>
													<td>40</td>
													<td>2009/06/25</td>
													<td>$675,000</td>
												</tr>
												<tr>
													<td>Caesar Vance</td>
													<td>Pre-Sales Support</td>
													<td>New York</td>
													<td>21</td>
													<td>2011/12/12</td>
													<td>$106,450</td>
												</tr>
												<tr>
													<td>Doris Wilder</td>
													<td>Sales Assistant</td>
													<td>Sidney</td>
													<td>23</td>
													<td>2010/09/20</td>
													<td>$85,600</td>
												</tr>
												<tr>
													<td>Angelica Ramos</td>
													<td>Chief Executive Officer (CEO)</td>
													<td>London</td>
													<td>47</td>
													<td>2009/10/09</td>
													<td>$1,200,000</td>
												</tr>
												<tr>
													<td>Gavin Joyce</td>
													<td>Developer</td>
													<td>Edinburgh</td>
													<td>42</td>
													<td>2010/12/22</td>
													<td>$92,575</td>
												</tr>
												<tr>
													<td>Jennifer Chang</td>
													<td>Regional Director</td>
													<td>Singapore</td>
													<td>28</td>
													<td>2010/11/14</td>
													<td>$357,650</td>
												</tr>
												<tr>
													<td>Brenden Wagner</td>
													<td>Software Engineer</td>
													<td>San Francisco</td>
													<td>28</td>
													<td>2011/06/07</td>
													<td>$206,850</td>
												</tr>
												<tr>
													<td>Fiona Green</td>
													<td>Chief Operating Officer (COO)</td>
													<td>San Francisco</td>
													<td>48</td>
													<td>2010/03/11</td>
													<td>$850,000</td>
												</tr>
												<tr>
													<td>Shou Itou</td>
													<td>Regional Marketing</td>
													<td>Tokyo</td>
													<td>20</td>
													<td>2011/08/14</td>
													<td>$163,000</td>
												</tr>
												<tr>
													<td>Michelle House</td>
													<td>Integration Specialist</td>
													<td>Sidney</td>
													<td>37</td>
													<td>2011/06/02</td>
													<td>$95,400</td>
												</tr>
												<tr>
													<td>Olivia Liang</td>
													<td>Support Engineer</td>
													<td>Singapore</td>
													<td>64</td>
													<td>2011/02/03</td>
													<td>$234,500</td>
												</tr>
												<tr>
													<td>Bruno Nash</td>
													<td>Software Engineer</td>
													<td>London</td>
													<td>38</td>
													<td>2011/05/03</td>
													<td>$163,500</td>
												</tr>
												<tr>
													<td>Sakura Yamamoto</td>
													<td>Support Engineer</td>
													<td>Tokyo</td>
													<td>37</td>
													<td>2009/08/19</td>
													<td>$139,575</td>
												</tr>
												<tr>
													<td>Thor Walton</td>
													<td>Developer</td>
													<td>New York</td>
													<td>61</td>
													<td>2013/08/11</td>
													<td>$98,540</td>
												</tr>
												<tr>
													<td>Finn Camacho</td>
													<td>Support Engineer</td>
													<td>San Francisco</td>
													<td>47</td>
													<td>2009/07/07</td>
													<td>$87,500</td>
												</tr>
												<tr>
													<td>Serge Baldwin</td>
													<td>Data Coordinator</td>
													<td>Singapore</td>
													<td>64</td>
													<td>2012/04/09</td>
													<td>$138,575</td>
												</tr>
												<tr>
													<td>Zenaida Frank</td>
													<td>Software Engineer</td>
													<td>New York</td>
													<td>63</td>
													<td>2010/01/04</td>
													<td>$125,250</td>
												</tr>
												<tr>
													<td>Zorita Serrano</td>
													<td>Software Engineer</td>
													<td>San Francisco</td>
													<td>56</td>
													<td>2012/06/01</td>
													<td>$115,000</td>
												</tr>
												<tr>
													<td>Jennifer Acosta</td>
													<td>Junior Javascript Developer</td>
													<td>Edinburgh</td>
													<td>43</td>
													<td>2013/02/01</td>
													<td>$75,650</td>
												</tr>
											</tbody>
										</table>

									</div>
								</div>
								<div class="card-footer small text-muted">Updated
									yesterday at 11:59 PM</div>
							</div>
							<!-- /.container-fluid -->

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
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights
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