<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:useBean id="now" class="java.util.Date"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<title>首頁</title>
</head>
<script>
	$(document).ready(function() {
		var text1;
		
		$("example").dataTable();
		
		$("#condit1").change(function() {
			text1 = $("#condit1").find(":selected").text();
		});

		$("#butt1").click(function() {
			// 			alert(text1);
			$.ajax({
				url : 'jobManCond',
				data : {
					condition1 : text1
				},
				type : 'post',
				cache : false,
				success : function(data) {
					$('#content1').text(data);
				}
			});
		});

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
				<section
					style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
					<nav>
						請輸入選擇條件: &nbsp; <select id="condit1">
							<option>發布中</option>
							<option>待審核</option>
							<option>已到期</option>
							<option>草稿</option>
						</select> &nbsp; 或是輸入關鍵字: &nbsp; <input placeholder="please enter">
						<button id="butt1" style="width: auto;">確定送出</button>
						<button id="jobPostBut" style="width: auto;"
							onclick="window.location='postJob'">張貼工作</button>
					</nav>
				</section>
				<div id="content1"></div>
				<table class="table table-hover" id="example">
					<thead>
						<tr>
							<th>公司單位</th>
							<th>職缺編號</th>
							<th>職位</th>
							<th>截止時間</th>
							<th>職缺內容</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="job" items="${jobs}">
							<tr>
								<td>${job.jobCompany.name}</td>
								<td>${job.jobId}</td>
								<td>${job.title}</td>
								<c:if test="${job.postEndDate > now}">
								<td>${job.postEndDate}</td>
								</c:if>
								<td><a href='<spring:url value="jobProfile?id=${job.jobId}"/>'
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
	<script src="https://code.jquery.com/jquery-3.3.1.js"
		integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
		crossorigin="anonymous"></script>
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