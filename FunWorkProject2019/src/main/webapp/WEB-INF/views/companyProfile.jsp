<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>公司</title>
</head>
<body>
	<section>
		<div>
			<div class="container" style="text-align: center">
				<h2>公司登錄資料</h2>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<div class="col-md-5">
				公司名稱: <h3>${company.name}</h3>
				公司系統編號: <p>${company.companyId}</p>
				統一編號: <p>${company.taxId}</p>
				公司登記營業地址: <p>${company.address}</p>
				公司是否通過系統檢核: <p>${company.reviewStatus}</p>
				公司使用者有無未讀訊息: <p>${company.notificationTimes}</p>
				公司網址:  <p>${company.siteURL}</p>
				<p>
					<strong>公司簡介: </strong> <span class='label label-warning'>
						${company.description} </span>
				</p>
				<p>
					<a href="<spring:url value='/manageCompanyPage' />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span>返回
					</a> 
					<a href='#' class='btn btn-warning btn-large'> 
					    <span class='glyphicon-shopping-cart glyphicon'></span>修改內容
					</a>
				</p>
			</div>
		</div>
	</section>
</body>
</html>
