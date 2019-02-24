<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="page-header" id="tou">WebSocket聊天測試</div>
	
	<div class="well" id="msg">${oldMessage}</div>
	<div class="col-lg">
		<div class="input-group">
			<input type="hidden" id="userId" value="12345">
			<input type="hidden" id="toUserId" value="54321">
			<input type="text" class="form-control" placeholder="傳送訊息..." id="message">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button" id="send">發送</button>
			</span>
		</div>
	</div>
	<script>
	var userId = $("#userId").attr('value');
	var connWsStr = "ws://127.0.0.1:8080/FunWorkProject2019/chat/" + userId;
	var toUserId = $("#toUserId").attr('value');
	var dateTimeNow =  new Date();
	var dateTimeNowStr = dateTimeNow.getFullYear()+ " 年 " + (dateTimeNow.getMonth()+1) + " 月 " + dateTimeNow.getDate() + " 日 "

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
			$("#tou").html("Server連線成功")
		};
		websocket.onmessage = function(evnt) {
			$("#msg").html($("#msg").html() + "<br/>" + evnt.data);
		};
		websocket.onerror = function(evnt) {
		};
		websocket.onclose = function(evnt) {
			$("#tou").html("Server連線中斷")
		}

		$('#send').click(function() {
			send();
		});

		function send() {
			if (websocket != null) {
				var message = $("#message").val();
				message = userId + " : " + message + " ( " + dateTimeNowStr + ")";
				websocket.send(message);
						
				$.ajax({
					url:"/FunWorkProject2019/message/TestWS?userId=" + toUserId + "&message=" + message,
					type:"GET",
					success:function(data){
					}
				});
				
				message = $("#message").val("");
				
			} else {
				alert('未與Server連線');
			}
		}
	});
</script>
</body>
</html>