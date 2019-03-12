<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- <link rel="stylesheet" href="/eeit105finalterm/css/bootstrap.min.css"> -->
<title>首頁</title>
</head>
<style>
.card-text-size{
font-size:14px;
}
.footerbackground{
background:#343a40;
color:white;
}
.nav-item:hover{
background-color:gray;
border-radius: 15px;
}
.asideblock{
height:600px;
}
</style>
<body>
<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
  <a class="navbar-brand" href="#">
  <img src="/FunWorkProject2019/image/LOGO.jpg" width="30" height="30" class="d-inline-block align-top">
  EEIT趣打工</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/FunWorkProject2019/">首頁 <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">想找打工</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">想要徵人</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">聯絡我們</a>
      </li>
    </ul>
    <form class="form-inline">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
     <span class="navbar-text">
     <a class="nav-link" href="#">登入</a>
    </span>
    <span class="navbar-text">
    <a class="nav-link" href="#">註冊</a>
    </span>
  </div>
</nav>
<div style="height:4rem"></div>
    <div class="container-fluid">
        <div class="row m-3 justify-content-around">
            <%@ include file="/WEB-INF/views/includes/sideNavBar.jsp" %>
            <div class="col-sm-8">

	<section>
		<div>
			<div class="container" style="text-align: center">
				<strong><h1>工作職缺內容</h1></strong>
			</div>
		</div>
	</section>
	<div style="height:30px;"></div>
	<section class="container">
		<div class="col-sm-12" >
      <div class="row">
        <div class="col-sm-12">
          <div class="panel panel-default text-left">
            <div class="panel-body">
              	 <h3><strong><a href='<spring:url value="applications?id=${job.jobId}"/>'>${job.title}</a></strong></h3> <p></p>
                 <strong>單位名稱:</strong> ${job.jobCompany.name}　<p>
            	 <strong>單位地址:</strong> ${job.address}　<p>
            	 <strong>截止時間:</strong> ${job.postEndDate}　<p>
            	 <strong>職缺點擊次數:</strong> ${job.viewTimes}　<p>
            <hr>
              <button type="button" class="btn btn btn-primary btn-sm">
                <span class="glyphicon glyphicon-thumbs-up"></span> 至頂曝光
              </button>     
              <button type="button" class="btn btn btn-primary btn-sm">
                <span class="glyphicon glyphicon-thumbs-up"></span> 編輯
              </button>     
              <button type="button" class="btn btn btn-primary btn-sm">
                <span class="glyphicon glyphicon-thumbs-up"></span> 複製
              </button>     
   		 		<button type="button" class="btn btn-warning btn-sm" style="float: right">
                <span class="glyphicon glyphicon-thumbs-up"></span> 張貼工作
         		</button>     
            </div>
                <p>
          </div>
        </div>
      </div>
     				
		</div>
	</section>
			

            
            </div>
            <div class="col-sm-2">
            預留區塊
            
            </div>
        </div>   
    </div>
    <div class="container-fluid">
    <div class="row no-gutter footerbackground">
    <div class="col text-center">Copyright© 2019 趣打工 All rights reserved.</div>
    </div>
    </div>
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>