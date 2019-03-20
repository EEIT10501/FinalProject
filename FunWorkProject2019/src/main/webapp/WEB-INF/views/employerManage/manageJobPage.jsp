<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
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
<title>首頁</title>
</head>
<script>

	var table;
	function filterSelect() {

		var status = $("#condit1").find(":selected").text();
// 		alert(status);
		if (status == '全部') {
			table.column(3).search("").draw();
		} else {
			table.column(3).search(status).draw();
		}
		$('#filterPath').text(status);
	}
	
	$(document).ready(function() {
		$.noConflict();
		table = $('#example').DataTable();

		var contextPath = $("#contextPath").attr('value');

	});
</script>
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
             <!-- 複製這裡↓ -->
		<div class="col-sm-2">
		<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp" %>
		</div>
		      <!-- 複製這裡 ↑ -->
			<div class="col-sm-8">
				<input type="hidden" id="contextPath"
					value="${pageContext.request.contextPath}">
				<section
					style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
					<nav>
						<strong>目前篩選條件: </strong>
						<span class='label label-warning' id="filterPath"></span><p></p> 
						<strong>請輸入選擇條件: </strong>
						&nbsp; <select id="condit1">
							<option>發布中</option>
							<option>已截止</option>
							<option>待審核</option>
							<option>審核失敗</option>
							<option>下架</option>
							<option>全部</option>
						</select> &nbsp;
						<button id="butt1" style="width: auto;"  onclick="filterSelect()">確定送出</button>
						<button id="jobPostBut" style="width: auto;"
							onclick="window.location='addJobProfile'">張貼工作</button>
					</nav>
				</section>
				<div id="content1"></div>
				<table class="table table-hover display" id="example">
					<thead>
						<tr>
							<th>公司單位</th>
							<th>職缺編號</th>
							<th>職位</th>
							<th>狀態</th>
							<th>刊登截止時間</th>
							<th>職缺內容</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="job" items="${jobs}" varStatus="loop">
							<tr>
								<td>${job.jobCompany.name}</td>
								<td>${job.jobId}</td>
								<td>${job.title}</td>
<%-- 								<c:choose> --%>
<%-- 									<c:when test="${job.postEndDate > now}"> --%>
<!-- 										<td>發布中</td> -->
<%-- 									</c:when> --%>
<%-- 									<c:otherwise> --%>
<!-- 										<td>已截止</td> -->
<%-- 									</c:otherwise> --%>
<%-- 								</c:choose> --%>
								<td>${job.reviewStatus}</td>
								<td><fmt:formatDate type="both" value="${job.postEndDate}"/></td>
								<td><a
									href='<spring:url value="jobProfile?id=${job.jobId}"/>'
									class="btn btn-primary"> <span
										class="glyphicon-info-sigh glyphicon"></span> 詳細資料
								</a></td>
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
			<div class="col text-center">Copyright© 2019 趣打工 All rights
				reserved.</div>
		</div>
	</div>
<!-- 	<script src="https://code.jquery.com/jquery-3.3.1.js" -->
<!-- 		integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" -->
<!-- 		crossorigin="anonymous"> -->
<!-- 	</script> -->
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