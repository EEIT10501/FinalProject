<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>navbar</title>
<style>
.nav-item:hover {
	background-color: gray;
	border-radius: 15px;
}

.btn {
	margin-right: 5px;
}
</style>
</head>
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
				<li class="nav-item active"><a class="nav-link" href="<c:url value='/'></c:url>">首頁
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value='/jobs'></c:url>">想找打工</a></li>
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
						<button type="submit" class="btn btn-primary" style="float: right">確認送出</button>
					</form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>