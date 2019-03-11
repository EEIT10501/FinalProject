<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>首頁</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'></c:url>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/topNavBar.css'></c:url>">

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
</style>
<body>
<nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
  <a class="navbar-brand" href="#">
<!--   <img src="/FunWorkProject2019/image/LOGO.jpg" width="30" height="30" class="d-inline-block align-top"> -->
  <img src="<c:url value='/image/LOGO.jpg'></c:url>" width="30" height="30" class="d-inline-block align-top">
  EEIT趣打工</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="<c:url value='/'/>">首頁 <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">想找打工</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="employerPortal">想要徵人</a>
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
     <a class="nav-link" onclick="document.getElementById('id01').style.display='block'">登入</a>
    </span>
    <span class="navbar-text">
    <a class="nav-link" href="#">註冊</a>
    </span>
  </div>
</nav>
	<section >
	<div id="id01" class="modal" style="width: 30%; height: 90%;" >
		<form class="modal-content animate" action="/action_page.php">
			<div class="imgcontainer">
				<span onclick="document.getElementById('id01').style.display='none'"
					class="close" title="Close Modal">&times;</span> <img
					src="<c:url value='/image/profile.jpg'></c:url>" alt="Avatar" class="avatar">
			</div>

			<div class="container">
				<label for="uname"><b>請輸入使用者名稱</b></label> <input type="text"
					placeholder="Enter Username" name="uname" required> <label
					for="psw"><b>請輸入密碼</b></label> <input type="password"
					placeholder="Enter Password" name="psw" required>

				<button class="button2" type="submit">登入</button>
				<button type="button"
					onclick="document.getElementById('id01').style.display='none'"
					class="cancelbtn">取消</button>
				<label> 
				<input type="checkbox" checked="checked" name="remember"/> Remember me
				<span class="psw"><a href="#">忘記密碼</a></span>
				</label>
			</div>

		</form>
	</div>
	<script>
			// Get the modal

			var modal2 = document.getElementById('id01');

			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
				if (event.target == modal2) {
					modal2.style.display = "none";
				}
			}
		</script>
	</section>
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="/FunWorkProject2019/image/101.jpg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="/FunWorkProject2019/image/taichong.jpg" class="d-block w-100" alt="...">
    </div>
    <div class="carousel-item">
      <img src="/FunWorkProject2019/image/kaoshong.jpg" class="d-block w-100" alt="...">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
    <div class="container-fluid">
        <div class="row m-3 justify-content-around">
            <div class="col-sm-2">
            <div class="card text-white bg-dark mb-3">
            <img src="/FunWorkProject2019/image/list.jpg" class="card-img-top" alt="...">
            <div class="card-body">
            <h5 class="card-title text-center">簡單明瞭</h5>
            <p class="card-text card-text-size">打工趣簡單明瞭的操作介面獲得無數使用者的稱讚，讓你快速找到想要的打工。</p>
            </div>
            </div>
            </div>
            <div class="col-sm-2">
            <div class="card text-white bg-dark mb-3">
            <img src="/FunWorkProject2019/image/search.jpg" class="card-img-top" alt="...">
            <div class="card-body">
            <h5 class="card-title text-center">快速搜尋</h5>
            <p class="card-text card-text-size">只要 30 秒就可以填好履歷，一鍵應徵，節省寶貴的時間。安全可靠</p>
            </div>
            </div>
            </div>
            <div class="col-sm-2">
            <div class="card text-white bg-dark mb-3">
            <img src="/FunWorkProject2019/image/security.jpg" class="card-img-top" alt="...">
            <div class="card-body">
            <h5 class="card-title text-center">安全可靠</h5>
            <p class="card-text card-text-size">嚴格審查工作來源，拒絕八大行業，你可以放心在趣打工上找到最適合的工作。</p>
            </div>
            </div>
            </div>
            <div class="col-sm-2">
            <div class="card text-white bg-dark mb-3">
            <img src="/FunWorkProject2019/image/cal.jpg" class="card-img-top" alt="...">
            <div class="card-body">
            <h5 class="card-title text-center">精打細算</h5>
            <p class="card-text card-text-size">薪資時數幫您算好好，每月報表輕鬆做！</p>
            </div>
            </div>
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