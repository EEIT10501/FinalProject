<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>公司詳細資料頁面</title>
</head>
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
<body>
	<%@ include file="/WEB-INF/views/includes/navbar.jsp" %>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<%@ include file="/WEB-INF/views/includes/sideNavBar.jsp"%>
			<div class="col-sm-8">
				<form:form modelAttribute="newJobPost" method="POST">			
						<div class="form-group">
							<h1>張貼工作</h1>
						</div>
						<hr>
						<div class="form-group row">
						    <label for="type" class="col-sm-2 col-form-label">工作類型</label>
						    <div class="col-sm-10">
						    <form:select path="industry" id="type" class="form-control">
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
					    </div>
						<div class="form-group row">
							<label for="title" class="col-sm-2 col-form-label">標題</label>
						    <div class="col-sm-10">
						      <form:input type="text" path="title" class="form-control" id="title" placeholder="ex:徵餐廳外場服務生" required="required" />
						    </div>
						</div>
						<div class="form-group row">
							<label for="description" class="col-sm-2 col-form-label">描述</label>
						    <div class="col-sm-10">
						      <form:textarea path="description" rows="5" class="form-control" id="description" placeholder="請填寫工作要做的事項" required="required"/>
						    </div>
						</div>
						<div class="form-group row">
							<label for="no" class="col-sm-2 col-form-label">需求人數</label>
						    <div class="col-sm-10">
						      <form:input type="number" path="positionNum" class="form-control" id="no" placeholder="請輸入你要應徵的人數"  required="required"/>
						    </div>
						</div>
						<div class="form-group row">
							<label for="otherQ" class="col-sm-2 col-form-label">額外問題</label>
						    <div class="col-sm-10">
						      <form:input type="text" path="other" class="form-control" id="otherQ" placeholder="請輸入應徵者應徵時需要回答的問題，如果沒有，請留空" />
						    </div>
						</div>
						<div class="form-group row">
							<label for="endDate" class="col-sm-2 col-form-label">停止刊登日期</label>
						    <div class="col-sm-10">
						    	<form:input type="date" path="postEndDate" class="form-control" id="endDate" value="" min="" max="" />
						    	<small>免費會員只能設定為 7 天， PREMIUM 會員可以設定為 7 至 365 天</small>
						    </div>
						</div>
						<div class="form-group row">
							<label for="workDate" class="col-sm-2 col-form-label">工作時間</label>
						    <div class="col-sm-10">
						    	<form:input type="text" path="workDate" class="form-control" id="workDate" placeholder="ex:早上8:00~下午16:00，中間休息一小時" required="required"/>
						    </div>
						</div>
						<div class="form-group row">
						    <div class="col-sm-10">
						      <button type="submit" class="btn btn-primary">送出</button>
						    </div>
						</div>			
				</form:form>
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
		function GetDateStr(AddDayCount) {  
		    var dd = new Date();  
		    dd.setDate(dd.getDate() + AddDayCount);
		    var y = dd.getFullYear();  
		    var m = (dd.getMonth()+1)<10 ? ('0'+(dd.getMonth()+1)) : (dd.getMonth()+1);  
		    var d = dd.getDate() <10 ? ('0'+ dd.getDate()) :dd.getDate();  
		    return y+"-"+m+"-"+d;  
		}
	
		var userJobPostPeriod = ${sessionScope.loginUser.jobPostPeriod};
		var mindate = GetDateStr(7);
		var maxdate = GetDateStr(userJobPostPeriod);
		
		$(function() {
			$("#endDate").attr({"value":mindate,"min":mindate,"max":maxdate});
		});
	</script>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>