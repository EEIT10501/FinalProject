<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css" />
<script type="text/javascript"
	src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>首頁</title>
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

				<section>
					<div>
						<div class="container" style="text-align: center">
							<h2>公司登錄資料</h2>
						</div>
					</div>
				</section>
				<section class="container">
					<div class="row">
						<p>
							<span class='label label-warning'> <img width='600'
								height='200' style="margin: auto"
								src="<c:url value='/getCoverPicture/${company.companyId} '/>" />
							</span>
						</p>
						<p>
							<span class='label label-warning'> <img width='100'
								height='100'
								src="<c:url value='/getLogoPicture/${company.companyId}'/>" /></span>
						</p>

						<div class="col-md-5">
							<br> <strong>公司編號:</strong> ${company.companyId}
							<p></p>
							<strong>公司名稱: </strong>${company.name}<p></p>
							<strong>統一編號: </strong>${company.taxId}<p></p>
							<strong>公司登記營業地址: </strong>${company.address}<p></p>
							<strong>公司是否通過系統檢核: </strong>${company.reviewStatus}<p></p>
							<strong>公司使用者有無未讀訊息: </strong>${company.notificationTimes}<p></p>
							<strong>公司網址: </strong>${company.siteURL}<p></p>
							<%-- 				公司簡介:  <p>${company.description.}</p> --%>
							<p>
								<strong>公司license: </strong> <span class='label label-warning'>
									<img width='150' height='300'
									src="<c:url value='/getLicPicture/${company.companyId}'/>" />
								</span>
							</p>
						</div>
						<div class="col-md-2"></div>
						<div class="col-md-5" style="float: right"></div>
						<div id="clearTable">
							<script type="text/javascript">
								$.noConflict();
								table = $('#example').DataTable();
							</script>
							<table class="table table-hover display" id="example">
								<thead>
									<tr>
										<th>單位</th>
										<th>職缺編號</th>
										<th>職缺</th>
										<th>狀態</th>
										<th>額滿</th>
										<th>職缺內容</th>
										<th>薪水</th>
										<th>刊登截止</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="job" items="${jobs}" varStatus="loop">
										<tr>
											<td>${job.jobCompany.name}</td>
											<td>${job.jobId}</td>
											<td>${job.title}</td>
											<%-- 											<td>${job.applicant }</td> --%>
											<td>${job.reviewStatus}</td>
											<td>${job.isFilled}</td>
											<td><a
												href='<spring:url value="jobProfile?id=${job.jobId}"/>'
												class="btn btn-primary"> <span
													class="glyphicon-info-sigh glyphicon"></span> 詳細資料
											</a></td>
											<td>${job.rateByHour}</td>
											<td>${job.postEndDate}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</section>



			</div>
			<div class="col-sm-2">預留區塊</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter footerbackground">
			<div class="col text-center">Copyright© 2019 趣打工 All rights
				reserved.</div>
		</div>
	</div>
	<!-- 	<script src="https://code.jquery.com/jquery-3.3.1.js" -->
	<!-- 		integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" -->
	<!-- 		crossorigin="anonymous"></script> -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>