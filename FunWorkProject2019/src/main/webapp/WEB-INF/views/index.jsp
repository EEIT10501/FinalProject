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

.btn {
	margin-right: 5px;
}
</style>
<body>
	<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
		<a class="navbar-brand" href="#"> <!--   <img src="/FunWorkProject2019/image/LOGO.jpg" width="30" height="30" class="d-inline-block align-top"> -->
			<img src="<c:url value='/image/LOGO.jpg'></c:url>" width="30"
			height="30" class="d-inline-block align-top"> EEIT趣打工
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo03"
			aria-controls="navbarTogglerDemo03" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="">首頁
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="jobs">想找打工</a></li>
				<li class="nav-item"><a class="nav-link" href="employerPortal">想要徵人</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">聯絡我們</a></li>
			</ul>
			<form class="form-inline">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
			<span class="navbar-text my-2 my-sm-0"> <a
				class="nav-link btn btn-outline-secondary" data-toggle="modal"
				data-target="#exampleModal" href="#">登入</a>
			</span> <span class="navbar-text my-2 my-sm-0"> <a
				class="nav-link btn btn-outline-secondary" href="#">註冊</a>
			</span>
		</div>

	</nav>

	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">請選擇登入方式</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- 彈出視窗：寫程式的地方 -->
					<form action="#" method="post">
						<div class="form-group">
							<label for="exampleInputEmail1">電子郵件</label> <input type="email"
								class="form-control" id="exampleInputEmail1"
								aria-describedby="emailHelp" placeholder="Enter email">
							<small id="emailHelp" class="form-text text-muted"></small>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">輸入密碼</label> <input
								type="password" class="form-control" id="exampleInputPassword1"
								placeholder="Password">
						</div>
						<div class="form-group form-check">
							<input type="checkbox" class="form-check-input"
								id="exampleCheck1"> <label class="form-check-label"
								for="exampleCheck1">請記住我</label>
						</div>
						<button type="submit" class="btn btn-primary" style="float:right">確認送出</button>
					</form>

				</div>
			</div>
		</div>
	</div>

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
				<div class="card text-white bg-dark mb-3">
					<img src="/FunWorkProject2019/image/list.jpg" class="card-img-top"
						alt="...">
					<div class="card-body">
						<h5 class="card-title text-center">簡單明瞭</h5>
						<p class="card-text card-text-size">打工趣簡單明瞭的操作介面獲得無數使用者的稱讚，讓你快速找到想要的打工。</p>
					</div>
				</div>
			</div>
			<div class="col-md-2 col-sm-3">
				<div class="card text-white bg-dark mb-3">
					<img src="/FunWorkProject2019/image/search.jpg"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title text-center">快速搜尋</h5>
						<p class="card-text card-text-size">只要 30
							秒就可以填好履歷，一鍵應徵，節省寶貴的時間。安全可靠</p>
					</div>
				</div>
			</div>
			<div class="col-md-2 col-sm-3">
				<div class="card text-white bg-dark mb-3">
					<img src="/FunWorkProject2019/image/security.jpg"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title text-center">安全可靠</h5>
						<p class="card-text card-text-size">嚴格審查工作來源，拒絕八大行業，你可以放心在趣打工上找到最適合的工作。</p>
					</div>
				</div>
			</div>
			<div class="col-md-2 col-sm-3">
				<div class="card text-white bg-dark mb-3">
					<img src="/FunWorkProject2019/image/cal.jpg" class="card-img-top"
						alt="...">
					<div class="card-body">
						<h5 class="card-title text-center">精打細算</h5>
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