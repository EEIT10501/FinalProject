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

<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>

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
				<!--             程式寫在這 -->

				<form class=""
					action="${pageContext.request.contextPath}/accountSetting"
					method="post" onsubmit="return formCheck() ">
					<fieldset>
						<legend>帳號設定</legend>

						<label for="inputEmail3" class="col-sm-2 col-form-label">Email:</label>
						<div class="col-sm">
							<input type="email" name="email" class="form-control" value="${Users.email}"
								id="email"  required="required"
								title="請輸入email" />
								<font color="green" size="-1">${Ms.acutOK}</font>
								<font color="red" size="-1">${Msg.errorIDExs}</font>
						</div>

						<label for="pwd1" class="col-sm-2 col-form-label">密碼:</label>
						<div class="col-sm">
							<input type="password" name="password" value="" id="pwd1"
								placeholder="如有需要再更改" required="required"
								pattern="(?=^[A-Za-z0-9]{6,12}$)((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))^.*$"
								title="密碼：6~12英數字組合，至少有一個大寫、小寫英文字母及數字，如 A12Rd6"
								class="form-control" />
								<font color="green" size="-1">${Ms.pswOK}</font>
						</div>

						<label for="pwd2" class="col-sm-2 col-form-label">再輸入一次密碼:</label>
						<div class="col-sm">
							<input type="password" name="password2" value=""
								required="required"
								pattern="(?=^[A-Za-z0-9]{6,12}$)((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))^.*$"
								placeholder="如有需要再更改" id="pwd2" class="form-control" />
						</div>

						<div class="form-group row">
							<div class="col-sm-7">
								<label class="col align-self-center"> </label> <input
									type="submit" name="submit" class="btn btn-primary" id="submit"
									value="更新" /> <input type="reset" name="cancel"
									class="btn btn-primary" id="cancel" value="重填">
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
			<div class="col text-center">Copyright© 2019 趣打工 All rights
				reserved.</div>
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