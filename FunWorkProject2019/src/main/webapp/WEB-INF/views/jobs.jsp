<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css" />

<script type="text/javascript"
	src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>

<title>找工作</title>
<script>
	$(document).ready(function() {
		$('#example').DataTable();

	});
</script>
<style>
.footerbackground {
	background: #343a40;
	color: white;
}

.asideblock {
	height: 600px;
}

.btn-group {
	margin-bottom: 5px;
}
</style>
</head>

<body>
	
	<%@ include file="/WEB-INF/views/includes/navbar.jsp" %>
	
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2 asideblock">
				<div class="list-group">
					<a href="#" class="list-group-item list-group-item-action">基本資訊</a>
					<a href="#" class="list-group-item list-group-item-action">工作管理</a>
					<a href="#" class="list-group-item list-group-item-action">邀約管理</a>
					<a href="#" class="list-group-item list-group-item-action">公司管理</a>
					<a href="#" class="list-group-item list-group-item-action">加值服務</a>
					<a href="#" class="list-group-item list-group-item-action">黃金會員</a>
					<a href="#" class="list-group-item list-group-item-action">訂單管理</a>
					<a href="#" class="list-group-item list-group-item-action">優惠兌換</a>
				</div>
			</div>
			<div class="col-sm-8">
				<!--             程式寫在這 -->

				<div class="btn-group">
					<button type="button" class="btn btn-secondary dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						選擇縣市</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="<c:url value='/cityArea/5'/>">台北市</a>
						<a class="dropdown-item" href="<c:url value='/cityArea/15'/>">新北市</a>

					</div>
				</div>

				<div class="btn-group">
					<button type="button" class="btn btn-secondary dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						選擇行政區</button>
					<div class="dropdown-menu">
						<c:forEach var="city" items="${citys}">
							<a class="dropdown-item"
								href="<c:url value='/cityName/${city.cityId}'/>">${city.cityName}</a>
						</c:forEach>
					</div>
				</div>

				<c:if test="${empty jobs}">
					<h3 style="color: black">該區域目前無工作，請選擇其他區域</h3>
				</c:if>

				<table class="table table-hover display" id="example">
					<thead>
						<tr>
							<th>職缺名稱</th>
							<th>所在地區</th>
							<th>所屬公司</th>
							<th>是否額滿</th>
							<th>詳細內容</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="job" items="${jobs}">
							<tr>
								<td>${job.title}</td>
								<td>${job.city.cityName}</td>
								<td>${job.jobCompany.name}</td>
								<td>${job.isFilled}</td>
								<td><a href="<c:url value='/jobDetail/${job.jobId}'/>"
									class="btn btn-primary"><span
										class="glyphicon-info-sigh glyphicon"></span> 詳細資料 </a></td>
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