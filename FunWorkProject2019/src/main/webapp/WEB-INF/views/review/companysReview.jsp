<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="<c:url value='/DataTables/datatables.min.css/' />">
<title>職缺審核</title>
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
	<%@ include file="/WEB-INF/views/includes/adminnavbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2 asideblock">
				<div class="list-group">
					<a href="<c:url value='/companysReview'/>" class="list-group-item list-group-item-action">公司審核</a> 
					<a href="<c:url value='#'/>" class="list-group-item list-group-item-action">審核紀錄</a>
				</div>
			</div>
			<div class="col-sm-8">
				<h1>待審核公司</h1>
				<table class="table table-hover dataTable" id="companyTable">
					<thead>
						<tr>
							<th>公司編號</th>
							<th>公司名稱</th>
							<th>公司統一編號</th>
							<th>公司登記地址</th>
							<th>雇主姓名</th>
							<th>提交時間</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="company" items="${companyList}">
							<tr>
								<td>${company.companyId}</td>
								<td><a href="<c:url value='/companyReview/${company.companyId}'/>">${company.name}</a></td>
								<td>${company.taxId}</td>
								<td>${company.address}</td>
								<td>${company.user.userName}</td>
								<td><fmt:formatDate value="${company.submitTime}" pattern="yyyy/MM/dd HH:mm" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-sm-2">預留區塊</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter footerbackground">
			<div class="col text-center">Copyright© 2019 趣打工 All rights reserved.</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$("#companyTable").DataTable();
		});
	</script>
	<script src="<c:url value='/DataTables/datatables.min.js/'/>"></script>
</body>
</html>