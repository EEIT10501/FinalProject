<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<title>訂單</title>
<style>
.card-text-size {
	font-size: 14px;
}

.footerbackground {
	background: #343a40;
	color: white;
}

.asideblock {
	height: 600px;
}
</style>
</head>

<body>

	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>

	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2 asideblock">
				<div class="list-group">
					<a href="#" class="list-group-item list-group-item-action">基本資訊</a>
					<a href="#" class="list-group-item list-group-item-action">工作管理</a>
					<a href="#" class="list-group-item list-group-item-action">邀約管理</a>
					<a href="#" class="list-group-item list-group-item-action">公司管理</a>
					<a href="#" class="list-group-item list-group-item-action">加值服務</a>
					<a href="#" class="list-group-item list-group-item-action">黃金會員</a>
					<a href="#" class="list-group-item list-group-item-action">訂單管理</a>
					<a href="#" class="list-group-item list-group-item-action">優惠兌換</a>
				</div>
			</div>
			<div class="col-sm-8">
				<!--             程式寫在這 -->
				<h1>確認訂單</h1>

				<form method="POST"
					action="https://payment-stage.opay.tw/Cashier/AioCheckOut/V5">

					<table>
						<tr>
							<td><input type="text" name="MerchantID" value="2000132" /></td>
						</tr>
						<tr>
							<td><input type="text" name="MerchantTradeNo" value="201903130449001" /></td>
						</tr>
						<tr>
							<td><input type="text" name="MerchantTradeDate" value="2019/03/13 17:50:00" /></td>
						</tr>
						<tr>
							<td><input type="text" name="TotalAmount" value="100" /></td>
						</tr>
						<tr>
							<td><input type="text" name="TradeDesc" value="測試歐付寶" /></td>
						</tr>
						<tr>
							<td><input type="text" name="ItemName" value="測試商品" /></td>
						</tr>
						<tr>
							<td><input type="text" name="ReturnURL" value="http://localhost:8081/FunWorkProject2019/" /></td>
						</tr>
						<tr>
							<td><input type="text" name="ChoosePayment" value="Credit" /></td>
						</tr>
						<tr>
							<td><input type="text" name="PaymentType" value="aio" /></td>
						</tr>
						<tr>
							<td><input type="text" name="PaymentType" value="aio" /></td>
						</tr>
						<tr>
							<td><input type="text" name="CheckMacValue" value="${cattest2}" /></td>
						</tr>
						
						<tr>
							<td><input type="submit" value="確認付款" /></td>
						</tr>
					</table>
				</form>

				<form method="GET" action="<%=request.getContextPath()%>">
					<table>
						<tr>
							<td><input type="submit" value="back to Home" /></td>
						</tr>
					</table>
				</form>
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