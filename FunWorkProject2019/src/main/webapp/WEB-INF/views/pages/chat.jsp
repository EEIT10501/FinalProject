<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>訊息</title>
</head>
<style>

.asideblock {
	min-height: 620px;
	height:100%
}

#msg{
margin:0px;
}

#msg img{
width:50px;
height:50px;
}

#msg p{
font-size:5px;
}

.toUserPic{
margin:0px;
padding:0px;
width:60px;
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
				<h1><a href="<c:url value='/jobDetail/${application.job.jobId}' />">${application.job.title}</a></h1>
				<c:choose>
					<c:when test="${user.userId == application.user.userId}">
						<p>您於<fmt:formatDate value="${application.applicationTime}" pattern="yyyy/MM/dd" />應徵了此工作</p>
					</c:when>
					<c:otherwise>
						<p><a href="<c:url value='/applications?id=${application.job.jobId}' />">${application.user.userName}</a>於<fmt:formatDate value="${application.applicationTime}" pattern="yyyy/MM/dd" />應徵了此工作</p>
					</c:otherwise>
				</c:choose>		
				<div style="width:100%;height:470px;overflow:auto" id="myDiv">	
					<table class="table table-hover" id="msgTable">
						<tbody id="msg"></tbody>
					</table>
				</div>
				<div class="input-group">
						<input type="hidden" id="userId" value="${user.userId}"> 
						<input type="hidden" id="toUserId" value="${toUser.userId}"> 
						<input type="hidden" id="apId" value="${application.applicationId}"> 
						<input type="text" class="form-control" placeholder="傳送訊息..." id="message"> 
						<span class="input-group-btn">
							<button class="btn btn-info" type="button" id="send" style="margin-left:5px">發送</button>
						</span>
					</div>			
				
					
				
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights reserved.</div>
		</div>
	</div>
	<script>
		var userId = $("#userId").attr('value');
		var userName = "${user.userName}";
		var connWsStr = "ws://127.0.0.1:8080/${pageContext.request.contextPath}/chat/" + userId;
		var toUserId = $("#toUserId").attr('value');
		var toUserName = "${toUser.userName}";
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
				var timeStr = getTimeStr(new Date());
				createMsg(toUserId,toUserName,evnt.data,timeStr);
		        getChatList();
		        getNewMsgCount();
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
					if(message == ""){
						return;
					}
					message = message;
					websocket.send(message);									
	                var timeStr = getTimeStr(new Date());		            
		            createMsg(userId,userName,message,timeStr);

					$.ajax({
						url : "${pageContext.request.contextPath}/message/connWS?userId="
								+ userId + "&toUserId=" + toUserId
								+ "&message=" + message + "&apId=" + apId,
						type : "GET",
						success : function(data) {
							getChatList();
						}
					});
					message = $("#message").val("");
				} else {
					alert('未與Server連線');
				}
			}
			
			function getOldMsg(){
				$.ajax({
					url : "${pageContext.request.contextPath}/chatJSON?apId=" + apId,
					type : "GET",
					success : function(data) {
						$.each(data, function(index, element) {
							var timeStr = getTimeStr(new Date(element.time));
							createMsg(element.sender.userId,element.sender.userName,element.content,timeStr);
						});
					}
				});
			}
			
			function getChatList(){
				$.ajax({
                    url : "${pageContext.request.contextPath}/apJSON?userId=" + userId,
                    type : "GET",
                    success : function(data) {
                    	$("#apList").empty();
                    	
                        $.each(data, function(index, element) {                          
                            var timeStr = getTimeStr(new Date(element.latestMsgTime));                          
                            var toUserName = "";
                            if(userId != element.user.userId){
                                toUserName = element.user.userName;
                            } else{
                                toUserName = element.job.jobOwner.userName;
                            }
                                    
                            var a = $("<a>").attr("href", "<c:url value='/chat/"+ element.applicationId +"'/>").attr("class", "list-group-item list-group-item-action");
                            a.html(element.job.title + "<br>" + toUserName + "<br>" + element.latestMsg + "<br>" + timeStr);                        
                            a.appendTo("#apList");  
                        });
                    }
                });
			}
				
			function getNewMsgCount(){
				$.ajax({
	                url : "${pageContext.request.contextPath}/newMsg",
	                type : "GET",
	                success : function(data) {
	                    $("#newMsg").html("(" + data + ")");
	                }
	            }); 
			}
			
			function getTimeStr(time){
			  var min = time.getMinutes();
              if(min < 10){
                  min = "0" + min;
              }
              var timeStr = time.getFullYear() + "年"
              + (time.getMonth() + 1) + "月" + time.getDate()
              + "日 " + time.getHours() + ":" + min;
              return timeStr;
            }
			
			function createMsg(userId,name,msg,timeStr){
			    var imgTr = $("<td class='align-middle toUserPic'>").html("<img class='rounded-circle' src='<c:url value='/getPicture/" + userId + "'/>' />")
			    
			    if(userId==toUserId){
			    	 $("<tr>").appendTo("#msg")
		                .append(imgTr)
		                .append($("<td class='align-middle'>").text(name + "：" + msg).append($("<p>").text(timeStr)))
		                
		                .append($("<td class='align-middle'>"));
			    	
			    }else{
			    	$("<tr>").appendTo("#msg")
			    	.append($("<td class='align-middle toUserPic'>"))
			    	.append($("<td class='align-middle'>"))         
	                .append($("<td class='align-middle text-right'>").text(msg).append($("<p>").text(timeStr)))
	                
	                
	                
	                
			    	
			    }
                
                
                  
                $("#myDiv").scrollTop(10000);
			}
			
			getOldMsg();
			getChatList();
			getNewMsgCount();	
		});
	</script>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>