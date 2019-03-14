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

fieldset {
	border: solid green;
	text-align: center;
	width: default;
	background-position: center;
}
</style>
</head>


<body>

	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-5 justify-content-around">
			<!-- 			<div class="col-sm-2 asideblock"> -->
			<!-- 				<div class="list-group"> -->
			<!-- 					<a href="#" class="list-group-item list-group-item-action">基本資訊</a> -->
			<!-- 					<a href="#" class="list-group-item list-group-item-action">工作管理</a> -->
			<!-- 					<a href="#" class="list-group-item list-group-item-action">邀約管理</a> -->
			<!-- 					<a href="#" class="list-group-item list-group-item-action">公司管理</a> -->
			<!-- 					<a href="#" class="list-group-item list-group-item-action">加值服務</a> -->
			<!-- 					<a href="#" class="list-group-item list-group-item-action">黃金會員</a> -->
			<!-- 					<a href="#" class="list-group-item list-group-item-action">訂單管理</a> -->
			<!-- 					<a href="#" class="list-group-item list-group-item-action">優惠兌換</a> -->
			<!-- 				</div> -->
			<!-- 			</div> -->

			<div class="col-sm-8">
				<!-- 程式寫在這 -->
				<!--如果要insert多筆資料可以用form:form直接傳物件 -->
				<!-- onsubmit= "return formCheck() " +在form標籤中可以用(js)檢測密碼是否輸入相同-->
				<form method="post"
					action="${pageContext.request.contextPath}/register">
					<fieldset>
						<legend>
							<em>註冊會員</em>
						</legend>
						<p>註冊成功請至信箱進行帳號驗證</p>


						<div class="form-group row">
							<label for="inputEmail3" class="col-sm-2 col-form-label">Email:</label>
							<div class="col-sm">
								<input type="email" name="email" class="form-control"
									id="inputEmail3" autocomplete="on"
									required="required" title="請輸入email" style="width: 230px;">
								<font color="red" size="-1">${Msg.errorEmail}${Msg.errorIDExs}
								</font>
							</div>
						</div>

						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-2 col-form-label">姓名:</label>
							<div class="col-sm">
								<input type="text" name="name" class="form-control"
									id="inputPassword3"  required pattern="{1,15}"
									title="請輸入姓名" style="width: 200px;"> <font color="red"
									size="-1">${Msg.errEmailEmpty}</font>
							</div>
						</div>

						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-2 col-form-label">密碼：</label>
							<div class="col-sm">
								<input type="password" name="password" class="form-control"
									id="pwd1" placeholder="6~12英文數字組合" required
									pattern="(?=^[A-Za-z0-9]{6,12}$)((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))^.*$"
									title="密碼：6~12英數字組合，至少有一個大寫、小寫英文字母及數字，如 A12Rd6"
									style="width: 200px;"> <font color="red" size="-1">${Msg.errPdEmpty}</font>
							</div>
						</div>

						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-2 col-form-label">密碼確認：</label>
							<div class="col-sm">
								<input type="password" name="password2" class="form-control"
									id="pwd2" required="required" style="width: 200px;"> <font
									color="red" size="-1">${Msg.errPd2Empty}</font>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-sm">
								<input type="submit" name="submit" class="btn btn-primary"
									id="submit" value="註冊" /> <input type="reset" name="cancel"
									class="btn btn-primary" id="cancel" value="重填">
							</div>
						</div>

					</fieldset>
				</form>

			</div>
		</div>
		<div class="col-sm-2">預留區塊</div>
	</div>

	<div class="container-fluid">
		<div class="row no-gutter footerbackground">
			<div class="col text-center">Copyright© 2019 趣打工 All rights
				reserved.</div>
		</div>
	</div>
	<script type="text/javascript">
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