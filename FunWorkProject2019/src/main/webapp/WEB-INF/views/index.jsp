<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<style>
.card-text-size {
	font-size: 14px;
}

.fkcard{
border:none;
color:#03353E;
}

.card-body{
margin-top:-20px;
padding:0px;
}

.card-text{
font-weight:800;
}

</style>
</head>

<body>
<%@ include file="/WEB-INF/views/includes/navbar.jsp" %>

	<div id="carouselExampleIndicators" class="carousel slide"
		data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="<c:url value='/images/work04.jpg'></c:url>" class="d-block w-100"
					alt="...">
			</div>
			<div class="carousel-item">
				<img src="<c:url value='/images/work07.jpg'></c:url>"
					class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="<c:url value='/images/work08.jpg'></c:url>"
					class="d-block w-100" alt="...">
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-md-2 col-sm-3">
				<div class="card mb-3 fkcard">
					<img src="/FunWorkProject2019/image/list.jpg" class="card-img-top"
						alt="...">
					<div class="card-body">
						<h5 class="card-title text-center" style="color:#0878A4;font-weight:800">簡單明瞭</h5>
						<p class="card-text card-text-size">打工趣簡單明瞭的操作介面獲得無數使用者的稱讚，讓你快速找到想要的打工。</p>
					</div>
				</div>
			</div>
			<div class="col-md-2 col-sm-3">
				<div class="card mb-3 fkcard">
					<img src="/FunWorkProject2019/image/search.jpg"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title text-center" style="color:#0878A4;font-weight:800">快速搜尋</h5>
						<p class="card-text card-text-size">只要30秒就可以填好履歷，一鍵應徵，節省寶貴的時間。安全可靠</p>
					</div>
				</div>
			</div>
			<div class="col-md-2 col-sm-3">
				<div class="card mb-3 fkcard">
					<img src="/FunWorkProject2019/image/security.jpg"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title text-center" style="color:#0878A4;font-weight:800">安全可靠</h5>
						<p class="card-text card-text-size">嚴格審查工作來源，拒絕八大行業，你可以放心在趣打工上找到最適合的工作。</p>
					</div>
				</div>
			</div>
			<div class="col-md-2 col-sm-3">
				<div class="card mb-3 fkcard">
					<img src="/FunWorkProject2019/image/cal.jpg" class="card-img-top"
						alt="...">
					<div class="card-body">
						<h5 class="card-title text-center" style="color:#0878A4;font-weight:800">精打細算</h5>
						<p class="card-text card-text-size">薪資時數幫您算好好，每月報表輕鬆做！</p>
					</div>
				</div>
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