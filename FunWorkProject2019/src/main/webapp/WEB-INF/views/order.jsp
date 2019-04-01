<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="shortcut icon" href="<c:url value='/image/favicon.ico'/>">
<link rel="icon" href="<c:url value='/image/favicon.ico'/>">

<title>訂單</title>
<style>


</style>
</head>

<body>

	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>

	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2">
			<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp"%>
			</div>
			<div class="col-sm-8">
				<!--             程式寫在這 -->
				
				<c:if test="${order!=null}">
				<h3>付款成功</h3>
				
				<form method="POST"
					action="<c:url value='/orderSave'/>">
					
					<div class="form-group">
						<label for="MerchantTradeNo">訂單編號</label> 
						<input type="text" class="form-control" id="MerchantTradeNo" name="MerchantTradeNo" value="${order.orderTradeNo}">
					</div>
					<div class="form-group">
						<label for="MerchantTradeDate">交易日期</label> 
						<input type="text" class="form-control" id="MerchantTradeDate" name="MerchantTradeDate" value="${order.orderTime}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="TotalAmount">總金額</label> 
						<input type="text" class="form-control" id="TotalAmount" name="TotalAmount" value="${order.price}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="TradeDesc">商品描述</label> 
						<input type="text" class="form-control" id="TradeDesc" name="TradeDesc" value="${order.product.description}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="ItemName">商品名稱</label> 
						<input type="text" class="form-control" id="ItemName" name="ItemName" value="${order.product.productName}" readonly="readonly">
					</div>
					
					<div class="form-group">
						<label for="ChoosePayment">付款結果</label> 
						<c:if test="${order.status==1}">
						<input type="text" class="form-control" id="Payment" name="ePayment" value="付款成功" readonly="readonly">
						</c:if>
						<c:if test="${order.status!=1}">
						<input type="text" class="form-control" style="color:red" id="Payment" name="ePayment" value="付款失敗:${order.status}" readonly="readonly">
						</c:if>
					</div>
					
				</form>
				
				</c:if>
				
				<c:if test="${params!=null}">
				<h3>確認訂單</h3>

				<form method="POST"
					action="<c:url value='/orderSave'/>">
					
					<div class="form-group">
						<label for="MerchantTradeNo">訂單編號</label> 
						<input type="text" class="form-control" id="MerchantTradeNo" name="MerchantTradeNo" value="${params.MerchantTradeNo}">
					</div>
					<div class="form-group">
						<label for="MerchantTradeDate">交易日期</label> 
						<input type="text" class="form-control" id="MerchantTradeDate" name="MerchantTradeDate" value="${params.MerchantTradeDate}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="TotalAmount">總金額</label> 
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
						<label for="ChoosePayment">付款方式</label> 
						<input type="text" class="form-control" id="Payment" name="ePayment" value="${params.Payment}" readonly="readonly">
					</div>
					
					<button type="submit" class="btn btn-primary">確認送出</button> 
				</form>
				</c:if>

			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row no-gutter">
			<div class="col text-center footerbackground">Copyright© 2019 趣打工 All rights
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