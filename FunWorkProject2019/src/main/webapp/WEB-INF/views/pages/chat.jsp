<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- <link rel="stylesheet" href="/eeit105finalterm/css/bootstrap.min.css"> -->
<title>工作審核</title>
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
		<a class="navbar-brand" href="<c:url value='/'/>"> <img
			src="<c:url value='/image/LOGO.jpg'/>" width=" 30" height="30"
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
					href="<c:url value='/'/>">首頁 <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">想找打工</a></li>
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
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2 asideblock">
				<div class="list-group">
					<a href="<c:url value='#'/>"
						class="list-group-item list-group-item-action">訊息一</a> <a
						href="<c:url value='#'/>"
						class="list-group-item list-group-item-action">訊息二</a>
				</div>
			</div>
			<div class="col-sm-8">
				<h1>訊息</h1>
				<table class="table table-hover">
					<tbody>
						<c:forEach var="oldMessage" items="${oldMessageList}">
							<tr>
								<td><img width="50" height="50" src='<c:url value="/getPicture/${oldMessage.sender.userId}"/>'/></td>
								<td>${oldMessage.sender.userName} : ${oldMessage.content}</td>
								<td><fmt:formatDate value="${oldMessage.time}"
										pattern="yyyy 年 MM 月 dd 日  HH:mm" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="col-lg">
					<div class="input-group">
						<input type="hidden" id="userId" value="1"> <input
							type="hidden" id="toUserId" value="2"> 
							<input type="hidden" id="apId" value="${apId}"> 
							<input type="text"
							class="form-control" placeholder="傳送訊息..." id="message">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button" id="send">發送</button>
						</span>
					</div>
				</div>
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
	<script>
		var userId = $("#userId").attr('value');
		var connWsStr = "ws://127.0.0.1:8080/FunWorkProject2019/chat/" + userId;
		var toUserId = $("#toUserId").attr('value');
		var apId = $("#apId").attr('value');
		
		$(function() {
			var websocket;
			if ('WebSocket' in window) {
				console.log("此瀏覽器支持websocket");
				websocket = new WebSocket(connWsStr);
			} else if ('MozWebSocket' in window) {
				alert("此瀏覽器只支持MozWebSocket");
			} else {
				alert("此瀏覽器只支持SockJS");
			}
			
			websocket.onopen = function(evnt) {
            };
            
			websocket.onmessage = function(evnt) {				
				location.reload();
			};
			websocket.onerror = function(evnt) {
			};
			websocket.onclose = function(evnt) {
			}

			$('#send').click(function() {
				send();
			});

			function send() {
				if (websocket != null) {
					var message = $("#message").val();
					message = message;
					websocket.send(message);
					
					$.ajax({
						url : "/FunWorkProject2019/message/TestWS?userId="
								+ userId + "&toUserId=" + toUserId + "&message=" + message +"&apId=" + apId,
						type : "GET",
						success : function(data) {
							location.reload();
						}
					});
					message = $("#message").val("");

				} else {
					alert('未與Server連線');
				}
			}
		});
	</script>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>