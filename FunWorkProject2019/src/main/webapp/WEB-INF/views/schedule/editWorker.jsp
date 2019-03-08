
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/png" href="/favicon.ico" />
<meta name="keywords" content="線上排班、排班表、派班表" />
<meta name="description" content="簡單易用線上排班系統，提供滑鼠拖拉設定排班、列印及網頁內崁碼功能。" />
<!--[if lt IE 8]><link type="text/css" rel="stylesheet" href="/asset/css/ie.css" media="screen, projection" /><![endif]-->
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/screen.css"
	media="screen, projection" />
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/print.css" media="print" />
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/plugin/tabs.css"
	media="screen, projection" />
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/style.css" />
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/jquery.fancybox-1.3.4.css"
	media="screen, projection" />
<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/jquery.simple-color-picker.css"
	media="screen, projection" />
<script type="text/javascript"
	src="https://shift.ekko.com.tw/asset/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="https://shift.ekko.com.tw/asset/js/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript"
	src="https://shift.ekko.com.tw/asset/js/jquery.simple-color-picker.js"></script>
<title>排班網 | 組員</title>
</head>
<body>
	<div class="container prepend-top append-bottom">
		<div class="span-24 header">
			<div class="prepend-1 span-11 left prepend-top" id="header-left">
				<h1>
					<a href="https://shift.ekko.com.tw/" id="site_name">排班網</a> <sub>beta</sub>
				</h1>
			</div>
			<div class="span-12 right last" id="header-right">
				<div class='span-12 prepend-top last'>
					<div class="span-2  right">
						<a href="https://shift.ekko.com.tw/forum.html"
							class="button span-2">討論區</a>
					</div>
					<div class="span-2 right">
						<a href="https://shift.ekko.com.tw/demo.html"
							class="button span-2">線上試用</a>
					</div>
					<!--<div class="span-2 right"><a href="https://shift.ekko.com.tw/price_plan.html" class="button span-2">價格方案</a></div>-->
					<div class="span-2 right">
						<a href="https://shift.ekko.com.tw/guide.html"
							class="button span-2">使用手冊</a>
					</div>
					<div class="span-2  right">
						<a href="https://shift.ekko.com.tw/" class="button span-2">首頁</a>
					</div>
				</div>
			</div>
		</div>
		<div id="menu" class="span-24 last">
			<ul id="menutabs">
				<li><a href="https://shift.ekko.com.tw/shift.html">排班</a></li>
				<li><a href="https://shift.ekko.com.tw/statistics.html">結算統計</a></li>
				<li class="current"><a
					href="https://shift.ekko.com.tw/group.html">群組管理</a></li>
				<li><a href="https://shift.ekko.com.tw/profile.html">個人資訊</a></li>
			</ul>
			<div class="append-1 last" style="margin-top: 1em;">
				<span>Hello, <strong><a
						href="https://shift.ekko.com.tw/profile.html">睿</a></strong></span><span>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a
					href="https://shift.ekko.com.tw/logout.html">登出</a></span>
			</div>
		</div>
		<div id="main" class="span-24">
			<div id="breadcrumb" class="span-22 prepend-1 append-1">
				<p id="breadcrumb">
					<a href="https://shift.ekko.com.tw/">首頁</a> > <a
						href="https://shift.ekko.com.tw/group.html">群組管理</a> > <span
						class="current">組員</span>
				</p>
			</div>
			<div id="content" class="span-22 prepend-1 append-1">
				<div class='mainInfo'>
					<ul class='tabs'>
						<li><a href="https://shift.ekko.com.tw/group/profile.html">群組設定</a></li>
						<li><a href="https://shift.ekko.com.tw/group/worker.html"
							class="selected">組員</a></li>
						<li><a href="https://shift.ekko.com.tw/group/embed.html">內崁程式碼</a></li>
					</ul>
					<div class='fieldset span-22'>
						<h3 class="span-2">組員</h3>
						<div class="last span-2">
							<a href="https://shift.ekko.com.tw/group/add_worker.html"
								title="新增組員" class="worker span-2 button" id="add_worker">+組員</a>
						</div>
						<table class="group append-bottom">
							<thead>
								<tr>
									<th class="span-1">項目</th>
									<th class="span-3">名稱</th>
									<th class="span-3">識別色</th>
									<th class="span-2">基本時薪</th>
									<th class="span-2 last">操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>A3</td>
									<td><div width="20" height="20"
											style="width: 20px; height: 20px; border: 1px solid #eee; background-color: #b45f06;">&nbsp;</div></td>
									<td>15</td>
									<td><a
										href="https://shift.ekko.com.tw/group/edit_worker/14138.html"
										title="編輯" class="image edit span-1" id="edit">編輯</a><a
										href="https://shift.ekko.com.tw/group/delete_worker/14138.html"
										title="刪除" class="image delete span-1" id="delete">刪除</a></td>
								</tr>
								<tr>
									<td>2</td>
									<td>A1</td>
									<td><div width="20" height="20"
											style="width: 20px; height: 20px; border: 1px solid #eee; background-color: #b45f06;">&nbsp;</div></td>
									<td>15</td>
									<td><a
										href="https://shift.ekko.com.tw/group/edit_worker/14137.html"
										title="編輯" class="image edit span-1" id="edit">編輯</a><a
										href="https://shift.ekko.com.tw/group/delete_worker/14137.html"
										title="刪除" class="image delete span-1" id="delete">刪除</a></td>
								</tr>
								<tr>
									<td>3</td>
									<td>A</td>
									<td><div width="20" height="20"
											style="width: 20px; height: 20px; border: 1px solid #eee; background-color: #b45f06;">&nbsp;</div></td>
									<td>15</td>
									<td><a
										href="https://shift.ekko.com.tw/group/edit_worker/14136.html"
										title="編輯" class="image edit span-1" id="edit">編輯</a><a
										href="https://shift.ekko.com.tw/group/delete_worker/14136.html"
										title="刪除" class="image delete span-1" id="delete">刪除</a></td>
								</tr>
								<tr>
									<td>4</td>
									<td>123</td>
									<td><div width="20" height="20"
											style="width: 20px; height: 20px; border: 1px solid #eee; background-color: #ff0000;">&nbsp;</div></td>
									<td>1</td>
									<td><a
										href="https://shift.ekko.com.tw/group/edit_worker/14135.html"
										title="編輯" class="image edit span-1" id="edit">編輯</a><a
										href="https://shift.ekko.com.tw/group/delete_worker/14135.html"
										title="刪除" class="image delete span-1" id="delete">刪除</a></td>
								</tr>
								<tr>
									<td>5</td>
									<td>露易莎</td>
									<td><div width="20" height="20"
											style="width: 20px; height: 20px; border: 1px solid #eee; background-color: #6aa84f;">&nbsp;</div></td>
									<td>1</td>
									<td><a
										href="https://shift.ekko.com.tw/group/edit_worker/14123.html"
										title="編輯" class="image edit span-1" id="edit">編輯</a><a
										href="https://shift.ekko.com.tw/group/delete_worker/14123.html"
										title="刪除" class="image delete span-1" id="delete">刪除</a></td>
								</tr>
								<tr>
									<td>6</td>
									<td>范氏金山</td>
									<td><div width="20" height="20"
											style="width: 20px; height: 20px; border: 1px solid #eee; background-color: #0000ff;">&nbsp;</div></td>
									<td>180</td>
									<td><a
										href="https://shift.ekko.com.tw/group/edit_worker/14121.html"
										title="編輯" class="image edit span-1" id="edit">編輯</a><a
										href="https://shift.ekko.com.tw/group/delete_worker/14121.html"
										title="刪除" class="image delete span-1" id="delete">刪除</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<script type="text/javascript">
					$(document).ready(function() {
						$("a#add_worker, a#edit").live('click', function(e) {
							e.preventDefault();
							$.fancybox(this, {
								'scrolling' : 'no',
								'titleShow' : false,
								'centerOnScroll' : true,
								'autoScale' : false,
								'enableEscapeButton' : true,
								'type' : 'inline'
							});
						});
					})
				</script>
			</div>
		</div>

</body>
</html>
