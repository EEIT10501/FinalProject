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


<title>側邊SideBar</title>

<script>
	$(function() {
		$.noConflict();
		$("#accordion").accordion({
			collapsible : true,
			heightStyle : "content"
		});
	});
</script>

</head>
<body>
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
						<li><a href="registerCompany">新增單位</a></li>

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
<!-- 			</div> -->
</body>
</html>