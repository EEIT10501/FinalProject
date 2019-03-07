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

.btn {
	margin-right: 5px;
}

.showjobdetail h5 {
	font-weight:bolder;
}

.showjobdetail h3 {
	font-weight:900;
}
</style>
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
				<li class="nav-item"><a class="nav-link" href="<c:url value='/jobs'/>">想找打工</a></li>
				<li class="nav-item"><a class="nav-link" href="#">想要徵人</a></li>
				<li class="nav-item"><a class="nav-link" href="#">聯絡我們</a></li>
			</ul>
			<form class="form-inline">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
			<span class="navbar-text my-2 my-sm-0"> <a
				class="nav-link btn btn-outline-secondary" href="#">登入</a>
			</span> <span class="navbar-text my-2 my-sm-0"> <a
				class="nav-link btn btn-outline-secondary" href="#">註冊</a>
			</span>
		</div>
	</nav>
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
			<div class="col-sm-6 showjobdetail">
				<!--             程式寫在這 -->
				<div class="row">
					<div class="col-sm-12">
						<h3>${jobBean.title}</h3>
						<p>時薪：${jobBean.rateByHour}</p>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3"><h5>工作內容</h5></div>
					<div class="col-sm-5">${jobBean.description}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3"><h5>工作時間</h5></div>
					<div class="col-sm-5">
						<c:forEach var="schedules" items="${schedules}">
							<h6>${schedules.workDate}</h6>
				${schedules.startTime}-${schedules.endTime}
				</c:forEach>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3"><h5>需求人數</h5></div>
					<div class="col-sm-5">${jobBean.positionNum}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3"><h5>給薪日期</h5></div>
					<div class="col-sm-5">${jobBean.paidDate}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3"><h5>其他條件</h5></div>
					<div class="col-sm-5">${jobBean.other}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3"><h5>工作地點</h5></div>
					<div class="col-sm-5">${jobBean.city.cityName}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3"><h5>詳細地址</h5></div>
					<div class="col-sm-5">${jobBean.address}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3"><h5>地點說明</h5></div>
					<div class="col-sm-5">${jobBean.addresssup}</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3"><h5>聯絡資訊</h5></div>
					<div class="col-sm-5">
						<p>聯絡人：${jobBean.contact}</p>
						<p>聯絡人：${jobBean.jobPhone}</p>
						<p>聯絡人：${jobBean.jobEmail}</p>
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-sm-3"><h5>所屬公司</h5></div>
					<div class="col-sm-5">${jobBean.jobCompany.name}</div>
				</div>


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