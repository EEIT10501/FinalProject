<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>註冊</title>
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
		<div class="row m-5 justify-content-around">
			<div class="col-sm-2"></div>
			<div class="col-sm-8 align-self-center">
				<form class="was-validated" novalidate method="post" onsubmit="return formCheck()"
					  action="${pageContext.request.contextPath}/register">
					<fieldset>
						<legend>註冊會員</legend>
						<p style="color: red">註冊成功請至信箱進行帳號驗證</p>
						<div class="form-group row">
							<label for="email" class="col-sm-2 col-form-label">Email:</label>
							<div class="col-sm">
								<input type="email" name="email" class="form-control" id="email"
									   autocomplete="on" required="required" style="width: 230px;"
									   value="${email}" > 
							    <font color="red" size="-1">${errorMsg.emailEmpty}${errorMsg.emailExist}</font>
							</div>
						</div>
						<div class="form-group row">
							<label for="name" class="col-sm-2 col-form-label">姓名:</label>
							<div class="col-sm">
								<input type="text" name="name" class="form-control" style="width: 230px;"
									   required id="name" value="${name}"> 
							    <font color="red" size="-1">${errorMsg.nameEmpty}</font>
							</div>
						</div>
						<div class="form-group row">
							<label for="pwd1" class="col-sm-2 col-form-label">密碼：</label>
							<div class="col-sm">
								<input type="password" name="password" class="form-control" id="pwd1"
									   placeholder="6~12英文數字組合" required="required" style="width: 230px;"
									   pattern="(?=^[A-Za-z0-9]{6,12}$)((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))^.*$"
									   title="密碼：6~12英數字組合，至少有一個大寫、小寫英文字母及數字，如 A12Rd6" > 
								<font color="red" size="-1">${errorMsg.pwdEmpty}</font>
							</div>
						</div>
						<div class="form-group row">
							<label for="pwd2" class="col-sm-2 col-form-label">密碼確認：</label>
							<div class="col-sm">
								<input type="password" name="password2" class="form-control"
									   id="pwd2" required="required" style="width: 230px;"
									   pattern="(?=^[A-Za-z0-9]{6,12}$)((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))^.*$" > 
								<font color="red" size="-1">${errorMsg.pwd2Empty}</font>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-sm">
								<input type="submit" class="btn btn-primary" id="submit" value="註冊" /> 
								<input type="reset" class="btn btn-danger" id="cancel" value="重填">
							</div>
						</div>
					</fieldset>
				</form>
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
		    function formCheck() {
				var pwd1 = document.getElementById("pwd1").value;
				var pwd2 = document.getElementById("pwd2").value;
				if (pwd1 != pwd2) {
					alert("密碼輸入不一致,請重新輸入");
					return false;
				}
			return true;
		}
	</script>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>