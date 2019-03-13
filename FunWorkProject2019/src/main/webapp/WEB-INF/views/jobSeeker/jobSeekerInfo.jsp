<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- <link rel="stylesheet" href="/eeit105finalterm/css/bootstrap.min.css"> -->
<title>公司詳細資料頁面</title>
</head>
<script>
	$(document).ready(function() {
		var text1;

		$("#condit1").change(function() {
			text1 = $("#condit1").find(":selected").text();
		});

		$("#butt1").click(function() {

			$.ajax({
				url : 'jobManCond',
				data : {
					condition1 : text1
				},
				type : 'post',
				cache : false,
				success : function(data) {
					$('#content1').text(data);
				}
			});
		});

	});
</script>
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
	<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
		<a class="navbar-brand" href="#"> <img
			src="/FunWorkProject2019/image/LOGO.jpg" width="30" height="30"
			class="d-inline-block align-top"> EEIT趣打工
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo03"
			aria-controls="navbarTogglerDemo03" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="/FunWorkProject2019/">首頁 <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">想找打工</a></li>
				<li class="nav-item"><a class="nav-link" href="#">求職者專區</a></li>
				<li class="nav-item"><a class="nav-link" href="#">想要徵人</a></li>
				<li class="nav-item"><a class="nav-link" href="#">聯絡我們</a></li>
			</ul>
			<form class="form-inline">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
			<span class="navbar-text"> <a class="nav-link" href="#">登入</a>
			</span> <span class="navbar-text"> <a class="nav-link" href="#">註冊</a>
			</span>
		</div>
	</nav>
	<div style="height: 4rem"></div>
	<div class="container-fluid">
		<div class="row m-3 justify-content-around">
			<div class="col-sm-2 asideblock">
				<div class="list-group">
					<a href="#" class="list-group-item list-group-item-action">基本資訊</a>
					<a href="#" class="list-group-item list-group-item-action">工作管理</a>
					<a href="#" class="list-group-item list-group-item-action">邀約管理</a>
					<a href="#" class="list-group-item list-group-item-action">公司單位管理</a>
					<a href="#" class="list-group-item list-group-item-action">加值服務</a>
					<a href="#" class="list-group-item list-group-item-action">PREMIUM會員</a>
					<a href="#" class="list-group-item list-group-item-action">訂單管理</a>
					<a href="#" class="list-group-item list-group-item-action">優惠卷兌換</a>
				</div>
			</div>
			<div class="col-sm-8">

				<form:form class='form-horizontal' modelAttribute="companyBean"
					method="POST">
					<!--  enctype="multipart/form-data" -->
					<fieldset>
						<section
							style="padding: 2px; width: 100%; height: auto; float: left; margin: 10px;">
							<!-- demo page inserted -->
							
							<div id="content-wrapper">

      <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item active">近期信用評價</li>
        </ol>

        <!-- Icon Cards-->
        <a>當你應徵一份工作時，雇主可以看到你近三個月的信用評價，提升應約次數並減少缺席次數，有助於提升你的錄取機率。</a><nobr><a href="">了解更多</a></nobr>
        <div class="row">
          <div class="col-xl-3 col-sm-6 mb-3">
            <div class="card text-white bg-danger o-hidden h-100">
              <div class="card-body">
                <div class="card-body-icon">
                  <i class="fas fa-fw fa-life-ring"></i>
                </div>
                <h2>${loginUser.abscence}</h2>
              </div>
              <a class="card-footer text-white clearfix small z-1" href="#">
                <span class="float-left">近期邀約缺席次數</span>
                <span class="float-right">
                  <i class="fas fa-angle-right"></i>
                </span>
              </a>
            </div>
          </div>
        </div>

        <!-- Area Chart Example-->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fas fa-table"></i>
       		    最近邀約</div>
          <div class="card-body">
            <div class="table-responsive">
            <c:if test="${empty applicatioList}">
			<a>你目前沒有任何邀約，請繼續尋找打工！</a>
			</c:if>
            <c:if test="${!empty applicatioList}">
              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                  <tr>
                    <th>工作</th>
                    <th>薪資</th>
                    <th>給僱主的話</th>
                    <th>狀態</th>
                    <th>應徵時間</th>
                    <th>聯絡雇主</th>
                  </tr>
                </thead>
                <tfoot>
                  <tr>
                    <th>工作</th>
                    <th>薪資</th>
                    <th>給僱主的話</th>
                    <th>狀態</th>
                    <th>應徵時間</th>
                    <th>聯絡雇主</th>
                  </tr>
                </tfoot>
                <tbody>
                <c:forEach var="applicatioList" items="${applicatioList}">
                  <tr>
                    <td>${applicatioList.job.title}</td>
                    <td>${applicatioList.job.rateByHour}</td>
                    <td>${applicatioList.answer}</td>
                    <td>${applicatioList.appliedStatus}</td>
                    <td>${applicatioList.applicationTime}</td>
                    <td>${applicatioList.latestMsg}</td>
                  </tr>
                 </c:forEach>
                </tbody>
              </table>
              </c:if>
            </div>
          </div>
          <div class="card-footer small text-muted">
          <
          </div>
        </div>

        <!-- DataTables Example -->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fas fa-table"></i>
       		    最近應徵</div>
          <div class="card-body">
            <div class="table-responsive">
            <c:if test="${empty applicatioList}">
			<a>你目前沒有應徵任何工作，請繼續尋找打工！</a>
			</c:if>
            <c:if test="${!empty applicatioList}">
              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                  <tr>
                    <th>工作</th>
                    <th>薪資</th>
                    <th>給僱主的話</th>
                    <th>狀態</th>
                    <th>應徵時間</th>
                    <th>聯絡雇主</th>
                  </tr>
                </thead>
                <tfoot>
                  <tr>
                    <th>工作</th>
                    <th>薪資</th>
                    <th>給僱主的話</th>
                    <th>狀態</th>
                    <th>應徵時間</th>
                    <th>聯絡雇主</th>
                  </tr>
                </tfoot>
                <tbody>
                <c:forEach var="applicatioList" items="${applicatioList}">
                  <tr>
                    <td>${applicatioList.job.title}</td>
                    <td>${applicatioList.job.rateByHour}</td>
                    <td>${applicatioList.answer}</td>
                    <td>${applicatioList.appliedStatus}</td>
                    <td>${applicatioList.applicationTime}</td>
                    <td>${applicatioList.latestMsg}</td>
                  </tr>
                 </c:forEach>
                </tbody>
              </table>
              </c:if>
            </div>
          </div>
          <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
        </div>
      <!-- /.container-fluid -->

      <!-- Sticky Footer -->
      <footer class="sticky-footer">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright © Your Website 2019</span>
          </div>
        </div>
      </footer>

    </div>
    <!-- /.content-wrapper -->
							
							
							<!-- demo page ended -->
						</section>
					</fieldset>
				</form:form>
				<div id="content1"></div>
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