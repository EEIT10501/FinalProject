<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css" />

<script type="text/javascript"
	src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>



<title>薪資概算</title>

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
<script>
	$(document).ready(function() {
		$.noConflict();
		$('#example').DataTable({
			"bLengthChange": false, //改變每頁顯示數據數量
			"bFilter":false,
		});

	});
</script>

<body>
	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<!-- 複製這裡↓ -->
			<div class="col-sm-2">
				<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp"%>
			</div>
			<!-- 複製這裡 ↑ -->
			<div class="col-sm-8">
				<h2>薪資明細</h2>
				<hr>
				<!--		日期篩選條件			-->
				<form action='<c:url value="/wageManage"/> ' method="post">
					<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
						請輸入欲查詢的月份:<input type="month">
					<input type="submit" value="查詢" >
				</form>
				<!--		薪資資料表			-->
				<section
					style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
						<table class="table table-hover display" id="example">
							<thead>
								<tr>
									<th>姓名</th>
									<th>月工時</th>
									<th>時薪</th>
									<th>小計</th>
									<th>明細</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="company" items="${companys}" varStatus="loop">
									<tr>
										<td><c:out value="${loop.count}" /></td>
										<td>${company.name}</td>
										<td>${company.taxId}</td>
										<td>${company.address}</td>
										<td>${company.reviewStatus}</td>
										<c:choose>
											<c:when test="${company.reviewStatus =='已通過'}">
												<td><a
													href='<spring:url value="addCorpProfile?id=${company.companyId}"/>'
													class="btn btn-info btn-sm"> <span
														class="glyphicon-info-sigh glyphicon"></span> 完成公司建檔
												</a></td>
											</c:when>
											<c:when test="${company.reviewStatus =='公司完成建檔'}">
												<td><a
													href='<spring:url value="company?id=${company.companyId}"/>'
													class="btn btn-success btn-sm"> <span
														class="glyphicon-info-sigh glyphicon"></span> 詳細資料
												</a></td>
											</c:when>
											<c:otherwise>
												<td></td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					<table class="table table-hover display" id="testField">
					</table>
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

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>