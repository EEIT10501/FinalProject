<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2 asideblock">
				<div class="list-group" id="apList"></div>
			</div>
			<div class="col-sm-8">
				<h1>${application.job.title}</h1>
				<c:choose>
					<c:when test="${user.userId == application.user.userId}">
						<p>您於<fmt:formatDate value="${application.applicationTime}" pattern="yyyy/MM/dd" />應徵了此工作</p>
					</c:when>
					<c:otherwise>
						<p>${application.user.userName}於<fmt:formatDate value="${application.applicationTime}" pattern="yyyy/MM/dd" />應徵了此工作</p>
					</c:otherwise>
				</c:choose>
				<table class="table table-hover" id="msg">
				</table>
				<div class="col-lg">
					<div class="input-group">
						<input type="hidden" id="userId" value="${user.userId}"> 
						<input type="hidden" id="toUserId" value="${toUser.userId}"> 
						<input type="hidden" id="apId" value="${application.applicationId}"> 
						<input type="text" class="form-control" placeholder="傳送訊息..." id="message"> 
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
			<div class="col text-center">Copyright© 2019 趣打工 All rights reserved.</div>
		</div>
	</div>
	<script>
		var userId = $("#userId").attr('value');
		var connWsStr = "ws://127.0.0.1:8080/${pageContext.request.contextPath}/chat/" + userId;
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
				for(i=0;i<100000000;i++){			
				}
				$("#msg").empty();
				$("#apList").empty();
				getOldMsg();
			};
			websocket.onerror = function(evnt) {
			};
			websocket.onclose = function(evnt) {
			}

			$('#send').click(function() {
				send();
			});
			
			$('#message').keypress(function(e) {
				code = (e.keyCode ? e.keyCode : e.which);
				if(code == 13){
					send();
				}
			});

			function send() {
				if (websocket != null) {
					var message = $("#message").val();
					message = message;
					websocket.send(message);

					$.ajax({
						url : "${pageContext.request.contextPath}/message/TestWS?userId="
								+ userId + "&toUserId=" + toUserId
								+ "&message=" + message + "&apId=" + apId,
						type : "GET",
						success : function(data) {
						}
					});
					message = $("#message").val("");
				} else {
					alert('未與Server連線');
				}
			}
			
			// 用ajax抓取歷史訊息
			function getOldMsg(){
				$.ajax({
					url : "${pageContext.request.contextPath}/chatJSON?apId=" + apId,
					type : "GET",
					success : function(data) {
						$.each(data, function(index, element) {
							var time = new Date(element.time);
							var min = time.getMinutes();
							if(min < 10){
								min = "0" + min;
							}
							var timeStr = time.getFullYear() + "年"
							+ (time.getMonth() + 1) + "月" + time.getDate()
							+ "日 " + time.getHours() + ":" + min;
							
							var imgTr = $("<td>").html("<img width='50' height='50' src='<c:url value='/getPicture/" + element.sender.userId + "'/>' />")
							
							$("<tr>").appendTo("#msg")
							.append(imgTr)
							.append($("<td>").text(element.sender.userName + "：" + element.content))
							.append($("<td>").text(timeStr));
						});
					}
				});
				
				$.ajax({
					url : "${pageContext.request.contextPath}/apJSON?userId=" + userId,
					type : "GET",
					success : function(data) {
						$.each(data, function(index, element) {
							var time = new Date(element.latestMsgTime);
							var min = time.getMinutes();
							if(min < 10){
								min = "0" + min;
							}
							var timeStr = time.getFullYear() + "年"
							+ (time.getMonth() + 1) + "月" + time.getDate()
							+ "日 " + time.getHours() + ":" + min;
									
							var toUserName = "";
							if(userId != element.user.userId){
								toUserName = element.user.userName;
							}else{
								toUserName = element.job.jobOwner.userName;
							}
									
							var a = $("<a>").attr("href", "<c:url value='/chat/"+ element.applicationId +"'/>").attr("class", "list-group-item list-group-item-action");
							a.html(element.job.title + "<br>" + toUserName + "<br>" + element.latestMsg + "<br>" + timeStr);						
							a.appendTo("#apList");	
						});
					}
				});
			}	
			getOldMsg();
		});
	</script>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>