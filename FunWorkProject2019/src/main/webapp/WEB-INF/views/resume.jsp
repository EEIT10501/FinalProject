<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
</style>
</head>

<body>

	<%@ include file="/WEB-INF/views/includes/navbar.jsp"%>

	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">

			<div class="col-sm-8">
				<!-- 				請重這裡開始 -->
				<form:form method="POST" modelAttribute="Resume"
					enctype="multipart/form-data">
					<fieldset>
						<legend>基本資料+履歷</legend>

						<label class="control-label col-lg-2 col-lg-2" for='profilePart'>大頭貼圖片</label>
						<form:input type="file" path="profilePart" id="ProfilePic"
							accept="image/gif, image/jpeg, image/png"
							class='form:input-large' />



						<div class="form-group row">
							<label for="name">姓名 :</label>
							<form:input type="text" path="name" class="form-control is-valid"
								value="${d}" id="name" placeholder="請填真實名 " required="required" />
						</div>

						<div class="form-group row">
							<label for="gender">性別 :</label>
							<form:select class="custom-select" path="gender" id="gender"
								required="required">
								<form:option value="${d}">請選擇性別</form:option>
								<form:option value="男">男</form:option>
								<form:option value="女">女</form:option>
							</form:select>
						</div>

						<div class="form-group row ">
							<label for="phoneNum">手機 : </label>
							<form:input type="tel" id="phoneNum" path="phoneNum" value="${d}"
								maxlength="12" placeholder="0987 654 321" class="col-sm "
								pattern="09[1-8][0-9]([\-|\s]?)[0-9]{3}\1[0-9]{3}" />
						</div>

						<div class="form-group row ">
							<label for="birth">生日: </label>
							<div class="input-append date form_datetime">
								<form:input size="16" type="date" name="bday" path="birth"
									id="birth" value="${d}" class="col-sm " />
								<span class="add-on"><i class="icon-th"></i></span>
							</div>
						</div>

						<div class="form-group row ">
							<label for="educationLevel">教育程度:</label>
							<form:select class="custom-select" path="educationLevel"
								required="required">
								<form:option value="${d}">請選擇教育程度</form:option>
								<form:option value="國小">國小</form:option>
								<form:option value="國中">國中</form:option>
								<form:option value="高中">高中</form:option>
								<form:option value="大學">大學</form:option>
								<form:option value="碩士">碩士</form:option>
								<form:option value="博士">博士</form:option>
							</form:select>
						</div>

						<div class="col-sm-auto row ">
							<label for="position">工作經驗 : </label> <input type="text"
								name="position" id="position" placeholder="工作職稱"
								class="col-sm-auto " /> <input type="text" name="company"
								id="company" placeholder="公司名稱" class="col-sm-auto" /> <input
								type="text" name="term" id="term" placeholder="工作幾年"
								class="col-sm-auto" />
						</div>
						<div class="col-sm-auto row ">
							<label for="position">工作經驗 : </label> <input type="text"
								name="position" id="position" placeholder="工作職稱"
								class="col-sm-auto " /> <input type="text" name="company"
								id="company" placeholder="公司名稱" class="col-sm-auto" /> <input
								type="text" name="term" id="term" placeholder="工作幾年"
								class="col-sm-auto" />
						</div>

						<div class="form-group row ">
							<label for="selfIntro">簡單自我介紹:</label>
							<form:textarea class="form-control" id="selfIntro" value="${d}"
								path="selfIntro" rows="3"></form:textarea>
						</div>

						<div class="form-group row">
							<div class="col-sm">
								<input type="submit" name="submit" class="btn btn-primary"
									id="submit" value="更新履歷" /> <input type="reset" name="cancel"
									class="btn btn-primary" id="cancel" value="重填" />
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
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
	<!--生日 :日期 -->
	<script type="text/javascript">
		$(".form_datetime").datetimepicker({
			format : "yyyy MM dd  "
		});
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