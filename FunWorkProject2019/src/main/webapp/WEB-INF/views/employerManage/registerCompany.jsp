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
				<form:form class='form-horizontal' modelAttribute="companyBean"
					method="POST" enctype="multipart/form-data">
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
									
									<c:if test="${empty errors}">
									<span></span>
									</c:if>
									<c:if test="${errors != null}">
									<span><c:out value="${errors.error_TaxId}"/></span>
									</c:if>
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
<!-- 										value="Send" /> -->
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