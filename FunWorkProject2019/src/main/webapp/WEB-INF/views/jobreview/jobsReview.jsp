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
<title>工作審核</title>
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
					<a href="<c:url value='/jobsReview'/>" class="list-group-item list-group-item-action">工作審核</a> 
					<a href="<c:url value='/jobsReviewHistory'/>" class="list-group-item list-group-item-action">審核紀錄</a>
				</div>
			</div>
			<div class="col-sm-8">
				<h1>待審核工作</h1>
				<table class="table table-hover dataTable" id="jobTable">
					<thead>
						<tr>
							<th>職缺編號</th>
							<th>職缺名稱</th>
							<th>職缺類型</th>
							<th>時薪</th>
							<th>雇主姓名</th>
							<th>提交時間</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="job" items="${jobList}">
							<tr>
								<td>${job.jobId}</td>
								<td><a href="<c:url value='/jobReview/${job.jobId}'/>">${job.title}</a></td>
								<td>${job.industry}</td>
								<td>$${job.rateByHour}</td>
								<td>${job.jobOwner.userName}</td>
								<td><fmt:formatDate value="${job.submitTime}" pattern="yyyy/MM/dd HH:mm" /></td>
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
			$("#jobTable").DataTable();
		});
	</script>
	<script src="<c:url value='/DataTables/datatables.min.js/'/>"></script>
</body>
</html>