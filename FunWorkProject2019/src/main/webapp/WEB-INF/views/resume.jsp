<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>履歷</title>
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
				<form:form method="POST" modelAttribute="resume" enctype="multipart/form-data">
				    <h1>履歷資料</h1>
					<form:input path="resumeId" type="hidden" />
					<div class="form-group row">
						<label class="col-sm-2 col-form-label" for='profilePart'>大頭照：
						  <img width='50' height='50' 
						       src="<c:url value='/getPicture/${sessionScope.loginUser.userId}'/>">
	                    </label>
	                    <div class="col-sm-10">
						  <form:input type="file" path="profilePart" id="profilePart" class="form-control"
						              accept="image/gif, image/jpeg, image/png" />
					    </div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label" for="name">姓名 :</label>
						<div class="col-sm-10">
						  <form:input type="text" path="name" id="name" class="form-control" placeholder="請填真實姓名 " 
						              required="required" />
                        </div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label" for="gender">性別 :</label>
						<div class="col-sm-10">
							<form:select class="form-control" id="gender" path="gender" required="required">
								<form:option value="">請選擇性別</form:option>
								<form:option value="男">男</form:option>
								<form:option value="女">女</form:option>
							</form:select>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label" for="phoneNum">手機 : </label>
						<div class="col-sm-10">
						  <form:input type="tel" path="phoneNum" maxlength="10" 
						              placeholder="0987654321" id="phoneNum" class="form-control" 
						              pattern="09[1-8][0-9]([\-|\s]?)[0-9]{3}\1[0-9]{3}" />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label" for="birth">生日: </label>
						<div class="col-sm-10">
							<form:input type="date" path="birth" id="date"  class="form-control" required="required"/>
                        </div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label" for="educationLevel">教育程度:</label>
						<div class="col-sm-10">
							<form:select class="form-control" path="educationLevel" id="educationLevel" required="required">
								<form:option value="">請選擇教育程度</form:option>
								<form:option value="國小">國小</form:option>
								<form:option value="國中">國中</form:option>
								<form:option value="高中">高中</form:option>
								<form:option value="大學">大學</form:option>
								<form:option value="碩士">碩士</form:option>
								<form:option value="博士">博士</form:option>
							</form:select>
						</div>
					</div>
					<div class="form-group row">
						<label for="type1" class="col-sm-2 col-form-label">工作經驗 : </label>					
							<div class="col-sm-6">
								<form:select path="type1" class="form-control" id="type1">
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
							<div class="col-sm-2">
								<form:input type="text" path="position1" class="form-control"
									        placeholder="工作名稱 " id="position1"/>
							</div>
							<div class="col-sm-2">
								<form:input type="text" path="term1" class="form-control"
									        placeholder="工作年資 " id="term1"/>
							</div>
					</div>
					<div class="form-group row">
						<label for="type2" class="col-sm-2 col-form-label">工作經驗 : </label>
						<div class="col-sm-6">
							<form:select path="type2" class="form-control" id="type2">
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
						<div class="col-sm-2">
							<form:input type="text" path="position2" class="form-control" id="position2"
								        placeholder="工作名稱 " />
						</div>
						<div class="col-sm-2">
							<form:input type="text" path="term2" class="form-control" id="term2"
								        placeholder="工作年資 " />
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label" for="selfIntro">簡單自我介紹:</label>
						<div class="col-sm-10">
						  <form:textarea class="form-control" path="selfIntro" rows="3" id="MY"></form:textarea>
					    </div>
					</div>
					<div class="form-group row">
						<div class="col-sm">
							<input type="submit" class="btn btn-success" value="更新履歷" /> 
							<button type="button" class="btn btn-primary"  onclick="keyinAll()">一鍵登入</button>
						</div>
						
					</div>
				</form:form>
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
			//一鍵登入
			function keyinAll(){
				document.getElementById("profilePart").value="";
				document.getElementById("name").value="主克伯";
				document.getElementById("gender").value="男";
				document.getElementById("phoneNum").value="0931331330";
				document.getElementById("educationLevel").value="大學";
				document.getElementById("type1").value="服務";
				document.getElementById("position1").value="業務經理";
				document.getElementById("term1").value="1";
				document.getElementById("type2").value="勞力";
				document.getElementById("position2").value="勞工";
				document.getElementById("term2").value="2";
				document.getElementById("MY").value="我叫蕭義樺，今年26歲，出生於新竹縣竹東鎮。父母從事封裝測試的品管工作，目前皆已退休。";	
				
				
			}

	

	</script>
	
	
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>