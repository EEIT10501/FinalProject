<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<!-- <link rel="stylesheet" href="/eeit105finalterm/css/bootstrap.min.css"> -->
<title>公司詳細資料頁面</title>
</head>
<script>
	$(document).ready(function() {
		var text1;

		$("#condit1").change(function() {
			text1 = $("#condit1").find(":selected").text();
		});

		$("#butt1").click(function() {

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
		 <%@ include file="/WEB-INF/views/includes/sideNavBar.jsp" %>
			<div class="col-sm-10">

				<form:form class='form-horizontal' modelAttribute="companyBean"
					enctype="multipart/form-data" method="POST">
					<fieldset>
						<section
							style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
							<!-- demo page started -->
							<div class="col-sm-3" style="float: right">預留區塊</div>
							<div class="col-md-12 col-lg-8 mb-5 bg-light">
								<div class="container">
									<div class="row">
										<div class="col-md-12 col-lg-8 mb-5">
											<div class="row form-group mb-3">
												<div class="col-md-12 mb-3 mb-md-0 ">
													<label class="font-weight-bold" for="companyId">公司序號:</label>
													<form:input type="text" path="companyId" id="companyId" class="form-control" readonly="true"/>
												</div>
											</div>
											<div class="row form-group mb-3">
												<div class="col-md-12 mb-3 mb-md-0">
													<label class="font-weight-bold" for="name">公司名稱:</label>
													<form:input type="text" path="name" paid="name" class="form-control"
														placeholder="新科技資訊公司"  readonly="true"/>
												</div>
											</div>
											<div class="row form-group mb-3">
												<div class="col-md-12 mb-3 mb-md-0">
													<label class="font-weight-bold" for="taxId">統一編號:</label> 
													<form:input type="text" path="taxId" id="taxId" class="form-control"
														placeholder="八位阿拉伯數字" readonly="true"/>
												</div>
											</div>
											<div class="row form-group mb-3">
												<div class="col-md-12 mb-3 mb-md-0 ">
													<label class="font-weight-bold" for="address">營業地址:</label>
													<form:input type="text" path="address" id="address" class="form-control"
														placeholder="行政區 縣市 路名 門牌號碼 郵遞區號"  readonly="true"/>
												</div>
											</div>

											<div class="row form-group mb-3">
												<div class="col-md-12 mb-3 mb-md-0">
													<label class="font-weight-bold" for="reviewStatus">公司審核狀態:
													</label> <form:input path="reviewStatus" type="text" id="reviewStatus" class="form-control" readonly="true"/>
												</div>
											</div>
<!-- 											<div class="row form-group mb-3"> -->
<!-- 												<div class="col-md-12 mb-3 mb-md-0"> -->
<!-- 													<label class="font-weight-bold" for="description">公司簡介:</label> -->
<%-- 													<form:textarea  type="text" path="description" id="description" class="form-control" --%>
<%--  														placeholder="請填入公司簡單介紹 "/> --%>
<!-- 												</div> -->
<!-- 											</div> -->

											<div class="row form-group mb-3">
												<div class="col-md-12 mb-3 mb-md-0">
													<label class="font-weight-bold" for="siteURL">公司網頁:
													</label> <form:input type="text" path="siteURL" id="siteURL" class="form-control"
														placeholder="www.goodBusinessInc.com"/>
												</div>
											</div>
											<div class="row form-group mb-3">
												<div class="col-md-12 mb-3 mb-md-0">
													<label class="font-weight-bold" for="user">公司管理者:</label>
													<form:input type="text" path="user" id="user" class="form-control"
														placeholder="王瑜仙"/> 
												</div>
											</div>
											<div class="row form-group mb-3">
												<div class="col-md-12 mb-3 mb-md-0">
													<label class="font-weight-bold" for="companyLogo">公司LOGO:</label>
													<form:input type="file" path="companyLogo" id="companyLogo" class="form-control"/>
												</div>
											</div>
											<div class="row form-group mb-3">
												<div class="col-md-12 mb-3 mb-md-0">
													<label class="font-weight-bold" for="companyCoverPic">公司coverPic:</label>
													<form:input type="file" path="companyCoverPic" id="companyCoverPic" class="form-control"/>
												</div>
											</div>
											<div class="form-group">
												<div class='col-lg-offset-2 col-lg-10'>
												<input id="btnAdd" type='submit' class='btn btn-primary'
											value="<spring:message code='spring.addProduct.form.submit.label'/>" />
								</div>
							</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 							</div> -->
						</section>
					</fieldset>
				</form:form>
			</div>

			<!-- demo page started -->
		</div>
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