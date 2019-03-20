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
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>履歷</title>
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
				<form:form method="POST" modelAttribute="resume"
					enctype="multipart/form-data">
					<fieldset>
						<legend>基本資料+履歷</legend>
						<form:input path="resumeId" type="hidden" />
						<label class="control-label col-lg-2 col-lg-2" for='profilePart'>大頭貼圖片</label>

						<img width='50' height='50'
							src="<c:url value='/getPicture/${sessionScope.loginUser.userId}'/>">
						<form:input type="file" path="profilePart" id="ProfilePic"
							accept="image/gif, image/jpeg, image/png"
							class='form:input-large' />

						<div class="form-group row">
							<label for="name">姓名 :</label>

							<form:input type="text" path="name" class="form-control is-valid"
								id="name" placeholder="請填真實姓名 " required="required" />

						</div>
						<div class="form-group row">
							<label for="gender">性別 :</label>
							<form:select class="custom-select" path="gender" id="gender"
								required="required">
								<form:option value="-1">請選擇性別</form:option>
								<form:option value="男">男</form:option>
								<form:option value="女">女</form:option>
							</form:select>
						</div>
						<div class="form-group row">
							<label for="phoneNum">手機 : </label>
							<form:input type="tel" id="phoneNum" path="phoneNum"
								maxlength="12" placeholder="0987 654 321" class="form-control"
								pattern="09[1-8][0-9]([\-|\s]?)[0-9]{3}\1[0-9]{3}" />
						</div>
						<div class="form-group row">
							<label for="birth">生日: </label>
							<form:input size="16" type="date" name="bday" path="birth"
								id="birth" class="form-control" />
							<span class="add-on"><i class="icon-th"></i></span>

						</div>
						<div class="form-group row">
							<label for="educationLevel">教育程度:</label>

							<form:select class="form-control" path="educationLevel"
								required="required">
								<form:option value="-1">請選擇教育程度</form:option>
								<form:option value="國小">國小</form:option>
								<form:option value="國中">國中</form:option>
								<form:option value="高中">高中</form:option>
								<form:option value="大學">大學</form:option>
								<form:option value="碩士">碩士</form:option>
								<form:option value="博士">博士</form:option>
							</form:select>
						</div>
						<div class="form-group row">
							<label for="type1" class="col-sm-12">工作經驗 :
							</label>
							<div class="col-sm-4">
								<form:select path="type1" id="type1" class="form-control">
									<form:option value="">請選擇工作類型</form:option>
									<form:option value="餐飲">餐飲(餐廳、飲料店、小吃攤販)</form:option>
									<form:option value="服務">服務(收銀員、促銷)</form:option>
									<form:option value="銷售">銷售(賣場販賣、推銷)</form:option>
									<form:option value="辦公">辦公(文書處理、行政、設計)</form:option>
									<form:option value="活動">活動(演唱會、展場、家庭日)</form:option>
									<form:option value="作業員">作業員(零件加工、包裝)</form:option>
									<form:option value="勞力">勞力(搬運貨物、理貨、保全、清潔、佈/撤展)</form:option>
									<form:option value="問券調查">問券調查(座談會、問券填寫)</form:option>
									<form:option value="補教">補教(家教、補習班職務)</form:option>
								</form:select>
							</div>
							<div class="col-sm-3">
								<form:input type="text" path="position1" class="form-control"
									id="position1" placeholder="工作名稱 " />
							</div>
							<div class="col-sm-5">
								<form:input type="text" path="term1" class="form-control"
									id="term1" placeholder="工作年資 " />
							</div>
						</div>
						<div class="form-group row">
							<label for="type2" class="col-sm-12">工作經驗 : </label>
							<div class="col-sm-4">
								<form:select path="type2" id="type2" class="form-control">
									<form:option value="">請選擇工作類型</form:option>
									<form:option value="餐飲">餐飲(餐廳、飲料店、小吃攤販)</form:option>
									<form:option value="服務">服務(收銀員、促銷)</form:option>
									<form:option value="銷售">銷售(賣場販賣、推銷)</form:option>
									<form:option value="辦公">辦公(文書處理、行政、設計)</form:option>
									<form:option value="活動">活動(演唱會、展場、家庭日)</form:option>
									<form:option value="作業員">作業員(零件加工、包裝)</form:option>
									<form:option value="勞力">勞力(搬運貨物、理貨、保全、清潔、佈/撤展)</form:option>
									<form:option value="問券調查">問券調查(座談會、問券填寫)</form:option>
									<form:option value="補教">補教(家教、補習班職務)</form:option>
									<form:option value="其他">其他</form:option>
								</form:select>
							</div>
							<div class="col-sm-3">
								<form:input type="text" path="position2" class="form-control"
									id="position2" placeholder="工作名稱 " />
							</div>
							<div class="col-sm-5">
								<form:input type="text" path="term2" class="form-control"
									id="term2" placeholder="工作年資 " />
							</div>
						</div>
						<div class="form-group row ">
							<label for="selfIntro">簡單自我介紹:</label>
							<form:textarea class="form-control" id="selfIntro"
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

	<div class="container-fluid">
		<div class="row no-gutter footerbackground">
			<div class="col text-center">Copyright© 2019 趣打工 All rights
				reserved.</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>