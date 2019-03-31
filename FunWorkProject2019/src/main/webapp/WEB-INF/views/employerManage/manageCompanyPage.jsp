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


<title>首頁</title>


</head>

<style>

</style>
<script>

	var table;
	
	function filterSelect() {
			
			var status = $("#condit1").find(":selected").text();
			if(status == '全部'){
				table.column(3).search('').draw();
			}
			else{
				table.column(3).search(status).draw();
			alert("uuuuuuu");
			}
			$('#filterPath').text(status);
	}
	
	$(document).ready(function() {
		$.noConflict();
		table = $('#example').DataTable();
		var status = $("#condit1").find(":selected").text();
		$('#filterPath').text(status);
		
		var contextPath = $("#contextPath").attr('value');
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
				<input type="hidden" id="contextPath"
					value="${pageContext.request.contextPath}">
				<section
					style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
					<nav>
						<strong>目前篩選條件: </strong> 
						<span class='label label-warning' id="filterPath"></span><p></p>
						<strong>請輸入選擇條件: </strong> 
						&nbsp; <select id="condit1">
							<option>待審核</option>
							<option>審核失敗</option>
							<option>審核通過</option>
							<option  selected="selected">全部</option>
						</select> 
						<button id="butt1" style="width: auto" onclick="filterSelect()">確定送出</button>

						<button id="jobPostBut" style="width: auto;"
							onclick="window.location='registerCompany'">建立公司</button>
						<div class="list-group2"></div>
						<hr>
						<div id="clearTable">
							<table class="table table-hover display" id="example">
								<thead>
									<tr>
										<th>名稱</th>
										<th>統編</th>
										<th>地址</th>
										<th>狀態</th>
										<th>公司專頁</th>
										<th>功能</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="company" items="${companys}" varStatus="loop">
										<tr>
											<td>${company.name}</td>
											<td>${company.taxId}</td>
											<td>${company.address}</td>
											<td>${company.reviewStatus}</td>
											
											<c:choose>
												<c:when test="${company.reviewStatus =='審核通過'}">
													<td><a
														href='<spring:url value="/addCorpProfile/${company.companyId}"/>'
														class="btn btn-info btn-sm"> <span
															class="glyphicon-info-sigh glyphicon"></span> 完成公司建檔
													</a></td>
												</c:when>
												<c:when test="${company.reviewStatus =='完成公司建檔'}">
													<td><a
														href='<spring:url value="/company?id=${company.companyId}"/>'
														class="btn btn-info btn-sm"> <span
															class="glyphicon-info-sigh glyphicon"></span> 公司專頁
													</a></td>
												</c:when>
												<c:otherwise>
													<td></td>
												</c:otherwise>
											</c:choose>
											<c:choose>
											<c:when test="${company.reviewStatus =='公司完成建檔'}">
											<td><a href='<spring:url value="/addCorpProfile/${company.companyId}"/>'
														class="btn btn-warning btn-sm"> <span
															class="glyphicon-info-sigh glyphicon"></span> 編輯
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
						</div>

						<table class="table table-hover display" id="testField">
						</table>
					</nav>
				</section>

			</div>
			<div class="col-sm-2">預留區塊</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights
				reserved.</div>
		</div>
	</div>
	<!-- 			<script src="https://code.jquery.com/jquery-3.3.1.js" -->
	<!-- 				integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" -->
	<!-- 				crossorigin="anonymous"></script> -->
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