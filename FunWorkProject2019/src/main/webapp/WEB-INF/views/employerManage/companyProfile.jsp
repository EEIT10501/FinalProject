<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- <link rel="stylesheet" href="/eeit105finalterm/css/bootstrap.min.css"> -->
<title>首頁</title>
</head>
<style>
.card-text-size{
font-size:14px;
}
.footerbackground{
background:#343a40;
color:white;
}
.nav-item:hover{
background-color:gray;
border-radius: 15px;
}
.asideblock{
height:600px;
}
</style>
<body>
<%@ include file="/WEB-INF/views/includes/adminnavbar.jsp"%>
<div style="height:4rem"></div>
    <div class="container-fluid">
        <div class="row m-3 justify-content-around">
            <div class="col-sm-2 asideblock">
            <div class="list-group">
  <a href="#" class="list-group-item list-group-item-action">基本資訊</a>
  <a href="manageJob" class="list-group-item list-group-item-action">工作管理</a>
  <a href="#" class="list-group-item list-group-item-action">邀約管理</a>
  <a href="manageCompanyPage" class="list-group-item list-group-item-action">公司單位管理</a>
  <a href="#" class="list-group-item list-group-item-action">加值服務</a>
  <a href="#" class="list-group-item list-group-item-action">PREMIUM會員</a>
  <a href="#" class="list-group-item list-group-item-action">訂單管理</a>
  <a href="#" class="list-group-item list-group-item-action">優惠卷兌換</a>
  </div>
            </div>
            <div class="col-sm-8">

			<section>
		<div>
			<div class="container" style="text-align: center">
				<h2>公司登錄資料</h2>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
				<p>
					<span class='label label-warning'>
					<img width='845' height='400' style="margin: auto"
                    src="<c:url value='/getCoverPicture/${company.companyId} '/>"/>
						</span>
				</p>
			<div class="col-md-5"><br>
				<strong>公司編號:</strong> ${company.companyId}<p></p>
				<strong>公司名稱: </strong>${company.name}<p></p>
				<strong>統一編號: </strong>${company.taxId}<p></p>
				<strong>公司登記營業地址: </strong>${company.address}<p></p>
				<strong>公司是否通過系統檢核: </strong>${company.reviewStatus}<p></p>
				<strong>公司使用者有無未讀訊息: </strong>${company.notificationTimes}<p></p>
				<strong>公司網址:  </strong>${company.siteURL}<p></p>
<%-- 				公司簡介:  <p>${company.description.}</p> --%>
				<p>
					<strong>公司license: </strong> <span class='label label-warning'>
					<img width='300' height='500' 
                    src="<c:url value='/getLicPicture/${company.companyId}'/>"/>
						</span>
				</p>
				<p>
					<a href="<spring:url value='/manageCompanyPage' />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span>返回
					</a> 
					<a href='<spring:url value="addCorpProfile?id=${company.companyId}"/>'
					class='btn btn-warning btn-large'> 
					    <span class='glyphicon-shopping-cart glyphicon'></span>修改內容
					</a>
				</p>
			</div>
				<div class="col-md-2"></div>
				<div class="col-md-5" style="float: right">
				<p>
					<span class='label label-warning'>
					<img width='300' height='300' 
                    src="<c:url value='/getLogoPicture/${company.companyId}'/>"/></span>
				</p>
				
				</div>
		</div>
	</section>
			

            
            </div>
            <div class="col-sm-2">
            預留區塊
            
            </div>
        </div>   
    </div>
    <div class="container-fluid">
    <div class="row no-gutter footerbackground">
    <div class="col text-center">Copyright© 2019 趣打工 All rights reserved.</div>
    </div>
    </div>
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>