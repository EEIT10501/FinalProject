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
<title>首頁</title>
</head>
<style>

</style>
<body>
	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>
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
							<h3><b>
								<spring:message
									code="spring.registerCompany.form.registerCompanyData.label" /></b>	
							</h3>
							<p style="color:red">${taxIdExist}</p>
							<hr>
							<div class="form-group row">
								<label class="col-sm-2 col-form-label" for='name'>
									<strong><spring:message code='spring.registerCompany.form.name.label' /></strong>
								</label>
								<div class="col-sm-10">
									<form:input id="name" path="name" type='text'
										class='form-control' placeholder="ex:大有技術公司" required="required"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-2 col-form-label" for='taxId'>
									<strong><spring:message code='spring.registerCompany.form.taxId.label' /></strong>
								</label>
								<div class="col-sm-10">
									<form:input id="taxId" path="taxId" type='number' placeholder='12345678'
										class='form-control' min="10000000" max="99999999" required="required"/>
								</div>
							</div>
							<div class="form-group row">
								<label class="control-label col-lg-2 col-lg-2" for='address'>
									<strong><spring:message
										code='spring.registerCompany.form.address.label' /></strong>
								</label>
								<div class="col-sm-10">
									<form:input id="address" path="address" type='text' length="50"
										placeholder="ex:新北市永和區成功路123號" class='form-control' required="required"/>
								</div>
							</div>
							<p></p>
							<h4><strong>身份驗證</strong></h4>
							<hr>

							<h6>為防止張貼者假冒其他公司名義張貼工作，請擇一提供以下證明文件，證明你屬於此公司</h6><p></p>
							<ul>
								<li>含有本人名字的公司名片
								<li>政府核可的營業登記文件 如你的帳號的 Email 包含公司網址，則可略過此步驟
							</ul>
							<div class="form-group row">
							<label class="control-label col-lg-2 col-lg-2"
								for='companyLicensureImage'> 
							<strong><spring:message code='spring.registerCompany.form.companyLicensureImage.label' /></strong>
							</label>
							<div class="col-sm-10">
								<form:input id="companyLicensureImage"
									path="companyLicensureImage" type='file'
									class='form-control' />
							</div>
							</div>
							<p></p><p></p><p></p>
							<div class="form-group">
								<div class='col-lg-offset-2 col-lg-10'>
									<input id="btnAdd" type='submit' class='btn btn-primary'
																				value="<spring:message code='spring.addProduct.form.submit.label'/>" />
								</div>
							</div>
				</form:form>
				<div id="content1"></div>
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
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>