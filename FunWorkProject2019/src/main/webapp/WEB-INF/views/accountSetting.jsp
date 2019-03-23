<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<title>首頁</title>
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
				<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp"%>
			</div>
			<div class="col-sm-8">
				<form action="${pageContext.request.contextPath}/accountSetting"
					  method="post" onsubmit="return formCheck() ">
					<fieldset>
						<legend>帳戶設定</legend>
						<font color="green" size="-1">${rightMsg.updateOK}</font>
						<div class="form-group row">
							<label for="inputEmail3" class="col-sm-2 col-form-label">Email：</label>
							<div class="col-sm">
								<input type="email" name="email" class="form-control" title="請輸入email"
									   value="${sessionScope.loginUser.email}" id="email" required="required"
									   readonly="readonly" /> 
							</div>
						</div>
						<div class="form-group row">
						<label for="pwd1" class="col-sm-2 col-form-label">密碼：</label>
							<div class="col-sm">
								<input type="password" name="password" id="pwd1" class="form-control"
									   placeholder="如有需要再更改" required="required"
									   pattern="(?=^[A-Za-z0-9]{6,12}$)((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))^.*$"
									   title="密碼：6~12英數字組合，至少有一個大寫、小寫英文字母及數字，如 A12Rd6" /> 
							</div>
						</div>
						<div class="form-group row">
							<label for="pwd2" class="col-sm-2 col-form-label">密碼確認：</label>
							<div class="col-sm">
								<input type="password" name="password2" required="required"
									   pattern="(?=^[A-Za-z0-9]{6,12}$)((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))^.*$"
									   placeholder="如有需要再更改" id="pwd2" class="form-control" />
							</div>
						</div>
						<div class="form-group row">
							<div class="col-sm-7">
								<input type="submit" class="btn btn-primary" id="submit" value="更新" /> 
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
				alert("密碼輸入不一致，請重新輸入。");
				return false;
			}
			return true;
		}
	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" ></script>
</body>
</html>