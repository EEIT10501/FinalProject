<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者導覽列</title>
<style>
.footerbackground {
	background: #561E18;
	color: white;
	font-weight:700;
	width:100%;
	margin-top:-16px;
}

.btn {
	margin-right: 5px;
}

.navbarback{ 
 background-color:#561E18; 

 } 

.navbar-brand:hover{
color:#F22F08;
}

.nav-item {
margin-left:5px;
}

.navbar-brand{
color:white;
font-weight:900;
}

.nav-link{
color:white;
font-weight:900;
}

.nav-link:hover{
color:#F22F08;
}

.navbutton{
background-color:white;
border:solid 1px #0878A4;
margin-left:5px;
border-radius:5px;
}

.navbutton:hover{
background-color:#0878A4;
color:white;
}

.navbuttonlogin{
background-color:#594346;
border:solid 1px #594346;
margin-left:5px;
border-radius:5px;
color:#EDD170;
}

</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg fixed-top navbarback">
		<a class="navbar-brand" href="<c:url value='/adminHome'/>"> <img
			src="<c:url value='/image/LOGO.jpg'/>" width="50" height="40" class="d-inline-block align-top"> EEIT趣打工管理
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo03"
			aria-controls="navbarTogglerDemo03" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="<c:url value='/adminHome'/>">首頁 <span class="sr-only"></span></a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value='/jobsReview'/>">職缺審核</a></li>			
				<li class="nav-item"><a class="nav-link" href="<c:url value='/companysReview'/>">公司審核</a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value='/cpsProcess' />">問題回報</a></li>	
				<li class="nav-item"><a class="nav-link" href="<c:url value='/queryMember' />">會員專區</a></li>
			</ul>
			
			<c:if test="${empty loginUser}">
				<span class="navbar-text my-2 my-sm-0" id="loginspan"> <button
					class="nav-link" data-toggle="modal"
					data-target="#loginModal">登入</button>
				</span>
			</c:if>
			<c:if test="${loginUser!=null}">
				<span class="navbar-text my-2 my-sm-0" id="logoutspan"> <button
					class="nav-link navbuttonlogin" data-toggle="modal"
					data-target="#logoutModal"><c:out
							value="${loginUser.userName} : 您好"></c:out></button>
				</span>
			</c:if>
			
		</div>

	</nav>

	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
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
					<form action="#" method="post" id="loginForm">
						<div class="form-group">
							<label for="exampleInputEmail1">電子郵件</label> <input type="email"
								class="form-control" id="exampleInputEmail1"
								aria-describedby="emailHelp" placeholder="Enter email"
								name="email"> <small id="emailHelp"
								class="form-text text-muted"></small>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">輸入密碼</label> <input
								type="password" class="form-control" id="exampleInputPassword1"
								placeholder="Password" name="password">
						</div>
						<div class="form-group form-check">
							<input type="checkbox" class="form-check-input"
								id="exampleCheck1" name="rememberMe"> <label
								class="form-check-label" for="exampleCheck1">請記住我</label><br>
							<label id="loginError"></label>
						</div>
						<button id="login" type="button" class="btn btn-primary"
							style="float: right">確認送出</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">您確定要登出嗎？</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- 彈出視窗：寫程式的地方 -->
					<div style="float:right">
						<span class="navbar-text my-2 my-sm-0" data-dismiss="modal"> <a
						class="nav-link btn btn-outline-secondary" >取消</a>
					</span>
					<span class="navbar-text my-2 my-sm-0"> <a
						class="nav-link btn btn-outline-secondary" href="<c:url value='/logout'></c:url>">確認</a>
					</span>
					</div>

				</div>
			</div>
		</div>
	</div>
	<script>	
		function setCookie(cname, cvalue, exdays) {
			var d = new Date();
			d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
			var expires = "expires=" + d.toGMTString();
			document.cookie = cname + "=" + cvalue + "; " + expires;
		}

		function getCookie(cname) {
			var name = cname + "=";
			var ca = document.cookie.split(';');
			for (var i = 0; i < ca.length; i++) {
				var c = ca[i].trim();
				if (c.indexOf(name) == 0) {
					return c.substring(name.length, c.length);
				}
			}
			return "";
		}

		$(function() {
			if (getCookie("rm") == "true") {
				if (getCookie("user") != "") {
					$("#exampleInputEmail1").val(getCookie("user"));
				}
				if (getCookie("password") != "") {
					$("#exampleInputPassword1").val(getCookie("password"));
				}
				$("#exampleCheck1").prop("checked", true);
			}
		});

		$("#login").click(function() {
			var email = $("#exampleInputEmail1").val();
			var password = $("#exampleInputPassword1").val();
			var rememberMe = $("#exampleCheck1").prop("checked");

			$.ajax({
				url : "${pageContext.request.contextPath}/login",
				type : "POST",
				data : {
					"email" : email,
					"password" : password,
					"rememberMe" : rememberMe
				},
				success : function(data) {
					if (data == "OK") {
						if (rememberMe) {
							setCookie("rm", rememberMe, 7);
							setCookie("user", email, 7);
							setCookie("password", password, 7);
						} else {
							setCookie("rm", rememberMe, 7);
							setCookie("user", "", 0);
							setCookie("password", "", 0);
						}
						location.reload();
						// 					$("#loginspan").text("<a class='nav-link btn btn-outline-secondary'>登入</a>");
					} else if (data == "fail") {
						alert("帳號或密碼錯誤!");
					}
				}
			});	
		});
	</script>
</body>
</html>