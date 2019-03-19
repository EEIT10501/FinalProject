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
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- <link rel="stylesheet" href="/eeit105finalterm/css/bootstrap.min.css"> -->
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
				<li class="nav-item"><a class="nav-link" href="#">求職者專區</a></li>
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
>>>>>>> branch 'master' of https://github.com/EEIT10501/FinalProject.git
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
             <!-- 複製這裡↓ -->
		<div class="col-sm-2">
		<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp" %>
		</div>
		      <!-- 複製這裡 ↑ -->
			<div class="col-sm-8">
				<form:form method='POST' modelAttribute="companyBean"
					class='form-horizontal' enctype="multipart/form-data">
					<fieldset>
						<section
							style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
							<h1>
								<spring:message
									code="spring.registerCompany.form.registerCompanyData.label" />
							</h1>
							<hr>
							<div class="form-group">
								<label class="control-label col-lg-2 col-lg-2" for='name'>
									<spring:message code='spring.registerCompany.form.name.label' />
								</label>
								<div class="col-lg-6">
									<form:input id="name" path="name" type='text'
										class='form:input-large' />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-lg-2 col-lg-2" for='taxId'>
									<spring:message code='spring.registerCompany.form.taxId.label' />
								</label>
								<div class="col-lg-10">
									<form:input id="taxId" path="taxId" type='text'
										class='form:input-large' />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-lg-2 col-lg-2" for='address'>
									<spring:message
										code='spring.registerCompany.form.address.label' />
								</label>
								<div class="col-lg-10">
									<form:input id="address" path="address" type='text'
										class='form:input-large' />
								</div>
							</div>
							<h4>身份驗證</h4>
							<hr>

							<h6>為防止張貼者假冒其他公司名義張貼工作，請擇一提供以下證明文件，證明你屬於此公司</h6>
							<ul>
								<li>含有本人名字的公司名片
								<li>政府核可的營業登記文件 如你的帳號的 Email 包含公司網址，則可略過此步驟
							</ul>
							<div class="form-group">
								<label class="control-label col-lg-2 col-lg-2"
									for='companyLicensureImage'> <spring:message
										code='spring.registerCompany.form.companyLicensureImage.label' />
								</label>
								<form:input id="companyLicensureImage"
									path="companyLicensureImage" type='file'
									class='form:input-large' />
							</div>
							<div class="form-group">
								<div class='col-lg-offset-2 col-lg-10'>
									<input id="btnAdd" type='submit' class='btn btn-primary'
										value="<spring:message code='spring.addProduct.form.submit.label'/>" />
								</div>
							</div>
						</section>
					</fieldset>
				</form:form>
				<div id="content1"></div>
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