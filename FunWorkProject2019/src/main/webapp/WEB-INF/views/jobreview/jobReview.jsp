<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- <link rel="stylesheet" href="/eeit105finalterm/css/bootstrap.min.css"> -->
<title>工作審核</title>
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
</style>
<body>
	<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
		<a class="navbar-brand" href="<c:url value='/'/>"> <img
			src="<c:url value='/image/LOGO.jpg'/>" width=" 30" height="30"
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
					href="<c:url value='/'/>">首頁 <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">想找打工</a></li>
				<li class="nav-item"><a class="nav-link" href="#">想要徵人</a></li>
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
			<div class="col-sm-2 asideblock">
				<div class="list-group">
					<a href="<c:url value='/jobsReview'/>"
						class="list-group-item list-group-item-action">工作審核</a>
				</div>
			</div>
			<div class="col-sm-8">
				<h1>工作審核</h1>
				<hr>
				<p>職缺編號 : ${jobBean.jobId}</p>
				<p>職缺名稱 : ${jobBean.title}</p>
				<p>工作類型 : ${jobBean.industry}</p>
				<p>工作內容 : ${jobBean.description}</p>
				<p>工作地址 : ${jobBean.address}</p>
				<p>地址補充說明 : ${jobBean.addresssup}</p>
				<p>時薪 : ${jobBean.rateByHour}</p>
				<p>發薪日期 : ${jobBean.paidDate}</p>
				<p>需求人數 : ${jobBean.positionNum}</p>
				<p>詢問應徵者的問題 : ${jobBean.other}</p>
				<p>聯絡人 : ${jobBean.contact}</p>
				<p>聯絡電話 : ${jobBean.jobPhone}</p>
				<p>聯絡信箱 : ${jobBean.jobEmail}</p>
				<p>
					張貼期限 :
					<fmt:formatDate value="${jobBean.postEndDate}"
						pattern="yyyy/MM/dd HH:mm" />
				</p>
				<p>
					提交時間 :
					<fmt:formatDate value="${jobBean.submitTime}"
						pattern="yyyy/MM/dd HH:mm" />
				</p>
				<p>雇主姓名 : ${jobBean.jobOwner.userName}</p>
				<p>公司名稱 : ${jobBean.jobCompany.name}</p>
				<p>審核備註 : ${jobBean.comment}</p>
				<form action="<c:url value='/jobReview/${jobBean.jobId}'/>" method="post" id="isPassForm">
					<input type="hidden" id="isPass" name="isPass" value=""> 
					<input type="button" class="btn btn-info btn-lg" id="pass" value="審核通過" />
					<button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#myModal">審核失敗</button>
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">請輸入審核失敗原因</h4>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>
								<div class="modal-body">
									<p>審核失敗原因</p>
									<input type="text" name="failReason" class="form-control">
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-info" data-dismiss="modal" id="fail">送出</button>
									<button type="button" class="btn btn-danger" data-dismiss="modal">關閉</button>
								</div>
							</div>
						</div>
					</div>
				</form>
				<script>
					$("#pass").click(function() {
						$("#isPass").attr("value", "pass");
						$("#isPassForm").submit();
					});
					$("#fail").click(function() {
						$("#isPass").attr("value", "fail");
						$("#isPassForm").submit();
					});
				</script>
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
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>