<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/DataTables/datatables.min.css/' />">
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="shortcut icon" href="<c:url value='/image/favicon.ico'/>">
<link rel="icon" href="<c:url value='/image/favicon.ico'/>">
<title>首頁</title>
</head>
<style>

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
				<section style="padding: 2px; width: 100%; height: auto; float: left; 
				         margin-bottom: 10px;">
					<nav>
						<strong>請輸入選擇條件: </strong>&nbsp;<select id="condit1">
							<option>發布中</option>
							<option>已截止</option>
							<option>待審核</option>
							<option>審核失敗</option>
							<option>下架</option>
							<option selected="selected">全部</option>
						</select> &nbsp;
						<button id="butt1" style="width: auto;" onclick="filterSelect()">送出</button>
						<strong>目前篩選條件: </strong> 
						<span class='label label-warning' id="filterPath"></span>
						<button id="jobPostBut" style="width: auto; float: right;"
							    onclick="window.location='addJobProfile'">張貼新工作</button>
					</nav>
				</section>
				<table class="table table-hover display" id="example">
					<thead>
						<tr>
							<th>職缺編號</th>
							<th>職位</th>
							<th>狀態</th>
							<th>刊登截止時間</th>
							<th>應徵人數</th>
							<th>職缺內容</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="job" items="${jobs}" varStatus="loop">
							<tr>
								<td>${job.jobId}</td>
								<td>${job.title}</td>
								<td>${job.reviewStatus}</td>
								<td><fmt:formatDate type="both" value="${job.postEndDate}" /></td>
								<c:choose>
									<c:when test="${(job.reviewStatus == '發布中')||(job.reviewStatus == '已截止')}">
										<td>${job.appsList}</td>
									</c:when>
									<c:otherwise>
										<td>N/A</td>
									</c:otherwise>
								</c:choose>
								<td>
								    <a href='<spring:url value="jobProfile?id=${job.jobId}"/>' class="btn btn-primary btn-sm"> 
								        <span class="glyphicon-info-sigh glyphicon"></span>查詢
								    </a>
								</td>
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
		var table;
	    function filterSelect() {
	        var status = $("#condit1").find(":selected").text();
	        if (status == '全部') {
	            table.column(2).search('').draw();
	        } else {
	            table.column(2).search(status).draw();
	        }
	        $('#filterPath').text(status);
	    }
	    $(document).ready(function() {
	        $.noConflict();
	        table = $('#example').DataTable();
	        var status = $("#condit1").find(":selected").text();
	        $('#filterPath').text(status);
	    });
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>