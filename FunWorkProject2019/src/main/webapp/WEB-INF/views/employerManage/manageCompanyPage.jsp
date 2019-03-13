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



/* section:after { */
/* 	content: ""; */
/* 	display: table; */
/* 	clear: both; */
/* } */
</style>
<script>
	
		$(document).ready(function() {
		$.noConflict();
		$('#example').DataTable();
		var contextPath = $("#contextPath").attr('value');

		var status = $("#condit1").find(":selected").text();

		$("#condit1").change(function() {
			status = $("#condit1").find(":selected").text();
		});

		$("#butt1").click(function() {
			
// 			$('#testField').DataTable({
// 				'ajax':{
// 			        'url': contextPath +  "/resultCorStatsJSON/"+status,
// 			        'type': "POST",
// 			        'data': { 'qstr': status },
// 			        'dataSrc': 'history'
// 				},
// 			    'autoWidth': false,
// 			    'lengthChange': false,
// 			    'ordering': false,
// 			    'pageLength': 50
// 		});
			
			$.ajax({
				url : contextPath +  "/resultCorStatsJSON/"+status,
				cache : false,
				type : "GET",
				dataType: 'json',
				success: function(json) {
					   console.log(JSON.stringify(json));
					   jQuery.fn.exists = function(){ return this.length > 0; }
// 					   if($('#example_wrapper').exists()){
// 					   	$('#example_wrapper').hide();
// 					   }
					   $('#clearTable').hide();
					   var rowHead  = '<thead><tr><th>筆數 </th>'+
							'<th>名稱 </th>'+
							'<th>統編 </th>'+
							'<th>地址 </th>'+
							'<th>狀態 </th>'+
							'<th>資料 </th></tr></thead><tbody>';
						var tableContent ="";
					   $.each(json, function(index, element){
						 var idx = parseInt(index);
						 var n = parseInt(1);
					     var dataRow = '<tr><td>'+ (idx+n) +'</td>'+
					    		 		'<td>'+element.name+'</td>'+
					    		 		'<td>'+element.taxId+'</td>'+
					    		 		'<td>'+element.address+'</td>'+
					    		 		'<td>'+element.reviewStatus+'</td>'+
					  "<td><a href='<spring:url value='company?id="+element.companyId+"'/>' class='btn btn-info btn-sm'>"+
					  "<span class='glyphicon-info-sigh glyphicon'></span>詳細資料</a></td></tr>";
					    	tableContent += dataRow;
					   	});
					   var myTable = rowHead + tableContent+'</tbody>';
					   	$('#testField').html(myTable);
						$('#testField').DataTable();
				},
				error : function(xhr) {
					alert("failure");
				}
			});
		});
	});
</script>
<body>
	<%@ include file="/WEB-INF/views/includes/adminnavbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
             <!-- 複製這裡↓ -->
		<div class="col-sm-2">
		<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp" %>
		</div>
		      <!-- 複製這裡 ↑ -->
			<div class="col-sm-8">
<!-- 				<h1>公司單位管理</h1> -->
				<input type="hidden" id="contextPath"
					value="${pageContext.request.contextPath}">
				<section
					style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
					<nav>
						<strong>目前篩選條件: 全部</strong> <span class='label label-warning'>
						</span><br>請輸入選擇條件: &nbsp; <select id="condit1">
							<option>審查中</option>
							<option>已通過</option>
							<option>未通過</option>
							<option>全部</option>
							<option>公司完成建檔</option>
						</select> 或是輸入關鍵字: &nbsp; <input placeholder="please enter">
						<button id="butt1" style="width: auto">確定送出</button>

						<button id="jobPostBut" style="width: auto;"
							onclick="window.location='registerCompany'">建立公司</button>
						<div class="list-group2"></div>
						<hr>
						<div id="clearTable">
						<table class="table table-hover display" id="example">
							<thead>
								<tr>
									<th>筆數</th>
									<th>名稱</th>
									<th>統編</th>
									<th>地址</th>
									<th>狀態</th>
									<th>資料</th>
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
										</c:when >
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
		<div class="row no-gutter footerbackground">
			<div class="col text-center">Copyright© 2019 趣打工 All rights
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