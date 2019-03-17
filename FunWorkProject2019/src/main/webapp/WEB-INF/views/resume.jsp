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

</style>
</head>

<body>
	
	<%@ include file="/WEB-INF/views/includes/navbar.jsp" %>
	
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
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
<!-- 				請重這裡開始 -->
		<form>		
			
			
		
		 <div class="form-group row">
			   <label for="validationServer01">姓名</label>
			   <input type="text" class="form-control is-valid" id="validationServer01" placeholder="請填真實名"  required>
	     </div>
	     	
		 <div class="form-group row">
		  <label for="gender">性別</label>
		    <select class="custom-select" id="gender" required>
		       <option value="">請選擇性別</option>
		       <option value="1">男</option>
		       <option value="2">女</option>
		    </select>
		 </div>
		
		<div class="form-group row">
			<label for="mp">手機 (<samp>+886</samp>)</label>
			<input type="tel" id="mp" name="mp" maxlength="12" placeholder="0987 654 321" 
					pattern="09[1-8][0-9]([\-|\s]?)[0-9]{3}\1[0-9]{3}">
		</div>
		
		<div class="form-group row">
		 <label for="birth">生日</label>	   
			<div class="input-append date form_datetime">
			    <input size="16" type="text" id="birth" value="" readonly>
			    <span class="add-on"><i class="icon-th"></i></span>
			</div>
		</div>
		
		<div class="form-group row">
		<label for="education">教育程度</label>
		   <select class="custom-select" required>
		      <option value="">請選擇教育程度</option>
		      <option value="1">國小</option>
		      <option value="2">國中</option>
		      <option value="3">高中</option>
		      <option value="4">大學</option>
		      <option value="5">碩士</option>
		      <option value="6">博士</option>
		   </select>
		</div>
		
		<div class="form-group row">
		<label for="education">工作經驗</label>
		   <select class="custom-select" required>
		      <option value="">請選擇工作類別</option>
		      <option value="1">服務</option>
		      <option value="2">辦工</option>
		      <option value="3">活動</option>
		      <option value="4">餐飲</option>
		      <option value="5">勞力</option>
		      <option value="6">補教</option>
		      <option value="6">作業員</option>
		      <option value="6">問卷調查</option>
		   </select>
		   <input type="text"  name="" placeholder="工作名稱">
		</div>
		
		
		<div class="form-group row">
		    <label for="exampleFormControlTextarea1">簡單自我介紹</label>
		    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
	  </div>
		
		<div class="form-group row">
				<div class="col-sm">
					<input type="submit" name="submit" class="btn btn-primary"
						id="submit" value="更新履歷" /> <input type="reset" name="cancel"
						class="btn btn-primary" id="cancel" value="重填">
				</div>
		</div>
		  
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
		<!--生日 :日期 -->
	<script type="text/javascript">
	    $(".form_datetime").datetimepicker({
	        format: "dd MM yyyy"
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