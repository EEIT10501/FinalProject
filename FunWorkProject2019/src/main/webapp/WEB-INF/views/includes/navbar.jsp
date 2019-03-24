<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id" 
      content="784516300990-g9mc0al77s74lmmi0q6hb9777k3om0qj.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
		<a class="navbar-brand" href="<c:url value='/'/>"> 
			<img src="<c:url value='/image/LOGO.jpg'/>" width="30" height="30" 
			     class="d-inline-block align-top">EEIT趣打工
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" 
		        data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" 
		        aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="<c:url value='/' />">首頁
					   <span class="sr-only">(current)</span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value='/jobs' />">想找打工</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value='/addJobProfile' />">想要徵人</a>
				</li>
				<c:if test="${loginUser!=null}">
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/mainHub'></c:url>">會員專區</a>
					</li>
				</c:if>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value='/qapage'/>">常見問題</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">聯絡我們</a>
				</li>
				<c:if test="${loginUser!=null}">
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/chat'/>">我的訊息
						  <span id="newMsg"></span>
						</a>
					</li>
				</c:if>
			</ul>
			<form class="form-inline" action="<c:url value='/searchJob' />">
				<input class="form-control mr-sm-2" name="searchStr" type="search" placeholder="Search" 
				       aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
			<c:if test="${empty loginUser}">
				<span class="navbar-text my-2 my-sm-0" id="loginspan"> 
					<a class="nav-link btn btn-outline-secondary" data-toggle="modal" 
					   data-target="#loginModal">登入</a>
				</span>
			</c:if>
			<c:if test="${loginUser!=null}">
				<span class="navbar-text my-2 my-sm-0" id="logoutspan"> 
					<a class="nav-link btn btn-outline-secondary" data-toggle="modal" 
					   data-target="#logoutModal">
						<c:out value="${loginUser.userName} : 您好"></c:out>
					</a>
				</span>
			</c:if>
			<c:if test="${loginUser==null}">
			<span class="navbar-text my-2 my-sm-0"> 
				<a class="nav-link btn btn-outline-secondary" href="register">註冊</a>
			</span>
			</c:if>
		</div>
	</nav>
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" 
	     aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">請選擇登入方式</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- 彈出視窗：寫程式的地方 -->
					<form action="#" method="post" id="loginForm">
						<div class="form-group">
							<label for="exampleInputEmail1">電子郵件</label> 
								<input type="email" class="form-control" id="exampleInputEmail1" 
								       aria-describedby="emailHelp" placeholder="Enter email" name="email"> 
								<small id="emailHelp" class="form-text text-muted"></small>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">輸入密碼</label> 
							<input type="password" class="form-control" id="exampleInputPassword1" 
						           placeholder="Password" name="password">
						</div>
						<div class="form-group form-check">
							<input type="checkbox" class="form-check-input" id="exampleCheck1" 
							       name="rememberMe"> 
							<label class="form-check-label" for="exampleCheck1">請記住我</label><br>
							<label id="loginError"></label>
						</div>
						<button id="login" type="button" class="btn btn-primary" 
						        style="float: right">確認送出</button>
						<div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" 
	     aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">您確定要登出嗎？</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- 彈出視窗：寫程式的地方 -->
					<div style="float: right">
						<span class="navbar-text my-2 my-sm-0" data-dismiss="modal">
							<a class="nav-link btn btn-outline-secondary">取消</a>
						</span> 
						<span class="navbar-text my-2 my-sm-0"> 
							<a class="nav-link btn btn-outline-secondary" onclick="signOut();" 
							   href="<c:url value='/logout'></c:url>">確認</a>
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
						document.location.href="${pageContext.request.contextPath}/";
					} else if (data == "fail") {
						alert("帳號或密碼錯誤!");
					} else if (data == "notOpen") {
						alert("請至您註冊的Email驗證帳號");
					}
				}
			});
		});

		$(function() {
			$.ajax({
				url : "${pageContext.request.contextPath}/newMsg",
				type : "GET",
				success : function(data) {
					$("#newMsg").html("(" + data + ")");
				}
			});
		});

		function onSignIn(googleUser) {
	        var id_token = googleUser.getAuthResponse().id_token;
	        $.ajax({
				url : "${pageContext.request.contextPath}/googleLogin",
				type : "POST",
				headers : { "Content-Type" : "application/x-www-form-urlencoded" },
				data : { "idtoken" : id_token },
				success : function(data) {
					if(data=="OK"){
						$('#loginModal').modal('hide');
					} else if(data=="Fail"){
						alert("Google帳戶驗證失敗");
					}
				}
			});
		};
	  function signOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	      console.log('User signed out.');
	    });
	  }
	</script>
</body>
</html>