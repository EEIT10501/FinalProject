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

<script>
// 	 	$(document).ready(function() {
// 	 			$('#example').DataTable();
// 	 	});
	$(function() {
		$("#accordion").accordion({
			collapsible : true,
			heightStyle : "content"
		});
	});
</script>

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
// 		$.noConflict();
// 		$('#example').DataTable();
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
					   if($('#example').exists()){
					   	$('#example').hide();
					   }
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
					  "<td><a href='<spring:url value='company?id="+element.companyId+"'/> class='btn btn-info btn-sm'>"+
					  "<span class='glyphicon-info-sigh glyphicon'></span>詳細資料</a></td></tr>";
					    	tableContent += dataRow;
					   	});
					   var myTable = rowHead + tableContent+'</tbody>';
					   	$('#testField').html(myTable);
// 						$('#testField').DataTable();
				},
				error : function(xhr) {
					alert("failure");
				}
			});
		});
	});
</script>
<body>
	<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
		<a class="navbar-brand" href="#"> <img
			src="/FunWorkProject2019/image/LOGO.jpg" width="30" height="30"
			class="d-inline-block align-top"> EEIT趣打工
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo03"
			aria-controls="navbarTogglerDemo03" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="/FunWorkProject2019/">首頁 <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">想找打工</a></li>
				<li class="nav-item"><a class="nav-link" href="employerPortal">想要徵人</a></li>
				<li class="nav-item"><a class="nav-link" href="#">聯絡我們</a></li>
			</ul>
			<form class="form-inline">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
			<span class="navbar-text"> <a class="nav-link" href="#">登入</a>
			</span> <span class="navbar-text"> <a class="nav-link" href="#">註冊</a>
			</span>
		</div>
	</nav>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm2 asideblock">
				<div id="accordion"  class="list-group-item list-group-item-action">
					<a href="#" class="list-group-item list-group-item-action">雇主後台資訊</a>
					<ul>
						<li><a href="#">會員資訊</a></li>
						<li><a href="mainHub">數據資訊</a></li>
						<li><a href="#">圖形表單</a></li>

					</ul>
					<a href="#" class="list-group-item list-group-item-action">職缺釋放管理</a>
					<ul>
						<li><a href="manageJob">管理職缺</a></li>
						<li><a href="addJobProfile">新增職缺</a></li>

					</ul>
					<a href="#" class="list-group-item list-group-item-action">邀約面試管理</a>
					<ul type="disc">
						<li >管理邀約</li>
						<li>管理面試</li>
						<li>智慧招募</li>
					</ul>
					<a href="#" class="list-group-item list-group-item-action">公司單位管理</a>
					<ul>
						<li><a href="manageCompanyPage">管理單位</a></li>
						<li><a href="addCorpProfile">新增單位</a></li>

					</ul>
					<a href="#" class="list-group-item list-group-item-action">時間排程管理</a>
					<ul>
						<li>管理排程</li>
						<li>新增排程</li>

					</ul>
					<a href="#" class="list-group-item list-group-item-action">加值服務</a>
					<a href="#" class="list-group-item list-group-item-action">進階會員</a>
					<a href="#" class="list-group-item list-group-item-action">訂單管理</a>
					<a href="#" class="list-group-item list-group-item-action">優惠卷兌換</a>
				</div>
			</div>
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
							<option>草稿</option>
						</select> 或是輸入關鍵字: &nbsp; <input placeholder="please enter">
						<button id="butt1" style="width: auto">確定送出</button>

						<button id="jobPostBut" style="width: auto;"
							onclick="window.location='registerCompany'">建立公司</button>
						<div class="list-group2"></div>
						<hr>
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
												class="glyphicon-info-sigh glyphicon"></span> 完成建檔
										</a></td>
										</c:when >
										<c:when test="${company.reviewStatus =='公司完成建檔'}">
										<td><a
											href='<spring:url value="company?id=${company.companyId}"/>'
											class="btn btn-success btn-sm"> <span
												class="glyphicon-info-sigh glyphicon"></span> 查詢
										</a></td>
										</c:when>
										<c:otherwise>
										</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<table class="table table-hover display" id="testField">
						</table>
						</nav>
				</section>
				<!-- 				<div id="content1"></div> -->
				<!--             程式寫在這 -->

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