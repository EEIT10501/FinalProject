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
<title>申訴處理</title>
</head>
<style>

</style>
<body>
	<%@ include file="/WEB-INF/views/includes/adminnavbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2 asideblock">
                <jsp:include page="/WEB-INF/views/includes/adminSideBar.jsp"></jsp:include>
            </div>
			<div class="col-sm-8">
				<h1>申訴處理紀錄</h1>
				<br>
				<table class="table table-hover dataTable" id="cpTable">
					<thead>
						<tr>
							<th>申訴編號</th>
							<th>申訴類別</th>
							<th>申訴職缺</th>
							<th>雇主姓名</th>
							<th>處理時間</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cp" items="${complaintList}">
							<tr>
								<td>${cp.complaintId}</td>
								<td><a href="<c:url value='/cpsHistory/${cp.complaintId}'/>">${cp.type}</a></td>
								<td>${cp.job.title}</td>
								<td>${cp.job.jobOwner.userName}</td>
								<td><fmt:formatDate value="${cp.processTime}" pattern="yyyy/MM/dd HH:mm" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-sm-2">預留區塊</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights reserved.</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$("#cpTable").DataTable();
		});
	</script>
	<script src="<c:url value='/DataTables/datatables.min.js/'/>"></script>
</body>
</html>