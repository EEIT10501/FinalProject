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

<style type="text/css">

#accordionExample {
background-color:#ecf5ff;
min-height:620px;
height:100%;
max-width:150px;
margin-left:-50px;
font-weight:700;
}

#accordionExample button {
background-color:#ecf5ff;
font-weight:900;
color:#0878A4;

}

#accordionExample button:hover{
background-color:#0878A4;
color:white;

}

#accordionExample div {
background-color:#ecf5ff;
border:none;
}

#accordionExample a{
font-weight:700;
color:#1ECFD6;
}

#accordionExample a:hover{
background-color:white;
color:#F2746B;
text-decoration:none;
border-radius:15px;
}


</style>
</head>
<body>
	<div class="accordion text-center shadow" id="accordionExample">
			<div class="card">
			<div class="card-header" id="heading1">
				<h2 class="mb-0">
					<button class="btn collapsed" type="button" data-toggle="collapse"
						data-target="#collapse1" aria-expanded="true"
						aria-controls="collapse1">會員資訊</button>
				</h2>
			</div>

			<div id="collapse1" class="collapse" aria-labelledby="heading1"
				data-parent="#accordionExample">
				<div class="card-body">
					<a class="btn btn-link" href="<c:url value='/accountSetting'></c:url>">帳戶設定</a>
					<a class="btn btn-link" href="<c:url value='/resume'></c:url>">我的履歷</a>
					<a class="btn btn-link" href="<c:url value='/mainHub'></c:url>">數據資訊</a>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="card-header" id="heading1-1">
				<h2 class="mb-0">
					<button class="btn collapsed" type="button" data-toggle="collapse"
						data-target="#collapse1-1" aria-expanded="true"
						aria-controls="collapse1-1">求職專區</button>
				</h2>
			</div>

			<div id="collapse1-1" class="collapse" aria-labelledby="heading1-1"
				data-parent="#accordionExample">
				<div class="card-body">
					<a class="btn btn-link" href="<c:url value='/jobSeekerInfo'></c:url>">求職資訊</a>
					<a class="btn btn-link" href="<c:url value='/applicatedWork'></c:url>">應徵紀錄</a>
					<a class="btn btn-link" href="<c:url value='/invitationManage'></c:url>">邀約管理</a>
					<a class="btn btn-link" href="<c:url value='/UserSchedule'></c:url>">工作班表</a>
					<a class="btn btn-link" href="<c:url value='/wageStaff'></c:url>">薪資紀錄</a>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="card-header" id="heading2">
				<h2 class="mb-0">
					<button class="btn collapsed" type="button"
						data-toggle="collapse" data-target="#collapse2"
						aria-expanded="false" aria-controls="collapse2">職缺管理</button>
				</h2>
			</div>
			<div id="collapse2" class="collapse" aria-labelledby="heading2"
				data-parent="#accordionExample">
				<div class="card-body">
					<a class="btn btn-link" href="<c:url value='/manageJob'></c:url>">管理職缺</a><br>
					<a class="btn btn-link"
						href="<c:url value='/addJobProfile'></c:url>">新增職缺</a>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="card-header" id="heading3">
				<h2 class="mb-0">
					<button class="btn collapsed" type="button"
						data-toggle="collapse" data-target="#collapse3"
						aria-expanded="false" aria-controls="collapse3">邀約管理</button>
				</h2>
			</div>
			<div id="collapse3" class="collapse" aria-labelledby="heading3"
				data-parent="#accordionExample">
				<div class="card-body">
					<a class="btn btn-link" href="<c:url value='/applicationNInterview'></c:url>">管理邀約</a>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="card-header" id="heading4">
				<h2 class="mb-0">
					<button class="btn collapsed" type="button" data-toggle="collapse"
						data-target="#collapse4" aria-expanded="false"
						aria-controls="collapse4">公司管理</button>
				</h2>
			</div>

			<div id="collapse4" class="collapse" aria-labelledby="heading4"
				data-parent="#accordionExample">
				<div class="card-body">
					<a class="btn btn-link" href="<c:url value='/manageCompanyPage'></c:url>">公司編輯</a><br> <a
						class="btn btn-link" href="<c:url value='/registerCompany'></c:url>">新增公司</a>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="card-header" id="heading5">
				<h2 class="mb-0">
					<button class="btn collapsed" type="button" data-toggle="collapse"
						data-target="#collapse5" aria-controls="collapse5" aria-expanded="false">排班薪資</button>
				</h2>
			</div>

			<div id="collapse5" class="collapse" aria-labelledby="heading5"
				data-parent="#accordionExample">
				<div class="card-body">
<%-- 					<a class="btn btn-link" href="<c:url value='/scheduleManage'></c:url>">新增排班</a><br> --%>
					<a class="btn btn-link" href="<c:url value='/ScheduleCalendar'></c:url>">新增班表</a>
					<a class="btn btn-link" href="<c:url value='/wageManage'></c:url>">薪資概算</a>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="card-header" id="heading6">
				<h2 class="mb-0">
					<button class="btn collapsed" type="button" data-toggle="collapse"
						data-target="#collapse6" aria-controls="collapse6" aria-expanded="false">黃金會員</button>
				</h2>
			</div>

			<div id="collapse6" class="collapse" aria-labelledby="heading6"
				data-parent="#accordionExample">
				<div class="card-body">
					<a class="btn btn-link" href="<c:url value='/product'></c:url>">加值服務</a><br>
					<a class="btn btn-link" href="<c:url value='/memberOrder'></c:url>">訂單查詢</a><br>
				</div>
			</div>
		</div>
	</div>
	<!-- 做錯的 -->
<!-- 	<button class="btn btn-primary" type="button" data-toggle="collapse" -->
<!-- 		data-target="#nav01" aria-expanded="false" -->
<!-- 		aria-controls="collapseExample">後台資訊</button> -->
<!-- 	<div class="collapse" id="nav01"> -->
<%-- 		<a class="btn btn-link" href="<c:url value='/#'></c:url>">會員資訊</a> <a --%>
<%-- 			class="btn btn-link" href="<c:url value='/mainHub'></c:url>">數據資訊</a> --%>
<%-- 		<a class="btn btn-link" href="<c:url value='#'></c:url>">圖形表單</a> --%>
<!-- 	</div> -->

<!-- 	<button class="btn btn-primary" type="button" data-toggle="collapse" -->
<!-- 		data-target="#nav02" aria-expanded="false" -->
<!-- 		aria-controls="collapseExample">職缺管理</button> -->
<!-- 	<div class="collapse" id="nav02"> -->
<%-- 		<a class="btn btn-link" href="<c:url value='/manageJob'></c:url>">管理職缺</a> --%>
<%-- 		<a class="btn btn-link" href="<c:url value='/addJobProfile'></c:url>">新增職缺</a> --%>
<!-- 	</div> -->

<!-- 	<button class="btn btn-primary" type="button" data-toggle="collapse" -->
<!-- 		data-target="#nav03" aria-expanded="false" -->
<!-- 		aria-controls="collapseExample">邀約面試</button> -->
<!-- 	<div class="collapse" id="nav03"> -->
<%-- 		<a class="btn btn-link" href="<c:url value='#'></c:url>">管理邀約</a> <a --%>
<%-- 			class="btn btn-link" href="<c:url value='#'></c:url>">管理面試</a> --%>
<!-- 	</div> -->

<!-- 	<button class="btn btn-primary" type="button" data-toggle="collapse" -->
<!-- 		data-target="#nav04" aria-expanded="false" -->
<!-- 		aria-controls="collapseExample">公司管理</button> -->
<!-- 	<div class="collapse" id="nav04"> -->
<%-- 		<a class="btn btn-link" href="<c:url value='#'></c:url>">公司編輯</a> <a --%>
<%-- 			class="btn btn-link" href="<c:url value='#'></c:url>">新增公司</a> --%>
<!-- 	</div> -->

<!-- 	<button class="btn btn-primary" type="button" data-toggle="collapse" -->
<!-- 		data-target="#nav05" aria-expanded="false" -->
<!-- 		aria-controls="collapseExample">排班管理</button> -->
<!-- 	<div class="collapse" id="nav05"> -->
<%-- 		<a class="btn btn-link" href="<c:url value='#'></c:url>">新增排程</a> <a --%>
<%-- 			class="btn btn-link" href="<c:url value='#'></c:url>">管理排程</a> --%>
<!-- 	</div> -->

<!-- 	<button class="btn btn-primary" type="button" data-toggle="collapse" -->
<!-- 		data-target="#nav06" aria-expanded="false" -->
<!-- 		aria-controls="collapseExample">黃金會員</button> -->
<!-- 	<div class="collapse" id="nav06"> -->
<%-- 		<a class="btn btn-link" href="<c:url value='#'></c:url>">加值服務</a> <a --%>
<%-- 			class="btn btn-link" href="<c:url value='#'></c:url>">訂單管理</a> <a --%>
<%-- 			class="btn btn-link" href="<c:url value='#'></c:url>">優惠券兌換</a> --%>
<!-- 	</div> -->

</body>
</html>