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
				<c:if test="${empty params}">
				<h3>填寫訂單</h3>
				<form method="POST" action="<c:url value='/orderCheck'/>">
					<div class="form-group">
						<label for="MerchantID">商店名稱</label> 
						<input type="text" class="form-control" id="MerchantID" name="MerchantID" value="2000132" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="MerchantTradeNo">訂單編號</label> 
						<input type="text" class="form-control" id="MerchantTradeNo" name="MerchantTradeNo" value="${params.MerchantTradeNo }">
					</div>
					<div class="form-group">
						<label for="MerchantTradeDate">交易日期</label> 
						<input type="text" class="form-control" id="MerchantTradeDate" placeholder="YYYY/MM/DD HH:MM:SS" name="MerchantTradeDate">
					</div>
					<div class="form-group">
						<label for="TotalAmount">加總金額</label> 
						<input type="text" class="form-control" id="TotalAmount" placeholder="TotalAmount" name="TotalAmount">
					</div>
					<div class="form-group">
						<label for="TradeDesc">商品描述</label> 
						<input type="text" class="form-control" id="TradeDesc" placeholder="TradeDesc" name="TradeDesc">
					</div>
					<div class="form-group">
						<label for="ItemName">商品名稱</label> 
						<input type="text" class="form-control" id="ItemName" placeholder="ItemName" name="ItemName">
					</div>
					<div class="form-group">
						<label for="ReturnURL">傳回網頁</label> 
						<input type="text" class="form-control" id="ReturnURL" name="ReturnURL" value="<c:url value='/orderReturn'/>" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="ChoosePayment">付款方式</label> 
						<input type="text" class="form-control" id="ChoosePayment" name="ChoosePayment" value="Credit" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="PaymentType">付款型態</label> 
						<input type="text" class="form-control" id="PaymentType" name="PaymentType" value="aio" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="CheckMacValue">CheckMacValue</label> 
						<input type="text" class="form-control" id="CheckMacValue" name="CheckMacValue" value="${CheckMacValue}" readonly="readonly">
					</div>
					
					
					<button type="submit" class="btn btn-primary">確認送出</button> 
				</form>
				</c:if>
				<c:if test="${params!=null}">
				<h3>確認訂單</h3>

				<form method="POST"
					action="https://payment-stage.opay.tw/Cashier/AioCheckOut/V5">
					<div class="form-group">
						<label for="MerchantID">商店名稱</label> 
						<input type="text" class="form-control" id="MerchantID" name="MerchantID" value="${params.MerchantID}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="MerchantTradeNo">訂單編號</label> 
						<input type="text" class="form-control" id="MerchantTradeNo" name="MerchantTradeNo" value="${params.MerchantTradeNo}">
					</div>
					<div class="form-group">
						<label for="MerchantTradeDate">交易日期</label> 
						<input type="text" class="form-control" id="MerchantTradeDate" name="MerchantTradeDate" value="${params.MerchantTradeDate}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="TotalAmount">加總金額</label> 
						<input type="text" class="form-control" id="TotalAmount" name="TotalAmount" value="${params.TotalAmount}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="TradeDesc">商品描述</label> 
						<input type="text" class="form-control" id="TradeDesc" name="TradeDesc" value="${params.TradeDesc}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="ItemName">商品名稱</label> 
						<input type="text" class="form-control" id="ItemName" name="ItemName" value="${params.ItemName}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="ReturnURL">傳回網頁</label> 
						<input type="text" class="form-control" id="ReturnURL" name="ReturnURL" value="${params.ReturnURL}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="ChoosePayment">付款方式</label> 
						<input type="text" class="form-control" id="ChoosePayment" name="ChoosePayment" value="${params.ChoosePayment}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="PaymentType">付款型態</label> 
						<input type="text" class="form-control" id="PaymentType" name="PaymentType" value="${params.PaymentType}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="CheckMacValue">CheckMacValue</label> 
						<input type="text" class="form-control" id="CheckMacValue" name="CheckMacValue" value="${CheckMacValue}" readonly="readonly">
					</div>
					
					
					<button type="submit" class="btn btn-primary">確認送出</button> 
				</form>
				</c:if>

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