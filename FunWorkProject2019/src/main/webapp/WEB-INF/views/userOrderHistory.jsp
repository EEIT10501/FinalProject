<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="<c:url value='/DataTables/datatables.min.css/' />">
<link rel="shortcut icon" href="<c:url value='/image/favicon.ico'/>">
<link rel="icon" href="<c:url value='/image/favicon.ico'/>">
<title>加值紀錄</title>
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
                <jsp:include page="/WEB-INF/views/includes/sideNavBar.jsp"></jsp:include>
            </div>
			<div class="col-sm-8">
				<h1>會員加值紀錄</h1>
				<table class="table table-hover dataTable" id="orderTable">
					<thead>
						<tr>
							<th>訂單編號</th>
							<th>商品名稱</th>
							<th>價錢</th>
							<th>到期日</th>
							<th>交易狀態</th>						
							<th>訂購時間</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" items="${orderlist}">
							<tr>
								<td>${order.orderTradeNo}</td>
								<td>${order.product.productName}黃金會員</td>
								<td>$${order.price}</td>
								<c:choose>
								<c:when test="${order.status == 1}">
								<td><fmt:formatDate value="${order.user.vipEndDate}" pattern="yyyy/MM/dd HH:mm" /></td>
								</c:when>
								<c:when test="${order.status != 1}">
								<td>-</td>
								</c:when>
								</c:choose>
								<c:choose>
								<c:when test="${order.status == 1}">
								<td>成功</td>
								</c:when>
								<c:when test="${order.status != 1}">
                                <td>失敗</td>
                                </c:when>
								</c:choose>
								<td><fmt:formatDate value="${order.orderTime}" pattern="yyyy/MM/dd HH:mm" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-sm-2">預留區塊</div>
		</div>
	</div> 
	<div class="container-fluid">
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights reserved.</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$("#orderTable").DataTable();
		});
	</script>
	<script src="<c:url value='/DataTables/datatables.min.js/'/>"></script>
</body>
</html>