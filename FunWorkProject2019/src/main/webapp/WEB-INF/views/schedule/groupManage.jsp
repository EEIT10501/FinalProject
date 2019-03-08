<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/png" href="/favicon.ico" />

<link type="text/css" rel="stylesheet" href="/FunWorkProject2019/css/screen.css" media="screen, projection" />
<!-- <link type="text/css" rel="stylesheet" href="/FunWorkProject2019/css/style.css" media="screen, projection" /> -->
<script type="text/javascript" src='<c:url value="/js/jquery.avgrund.min.js"/>'></script>


<script language="JavaScript" type="text/JavaScript">
function MM_openBrWindow(theURL,winName,features) {
  window.open(theURL,winName,features);
}

$('element').avgrund({
	width: 380, // max is 640px
	height: 280, // max is 350px
	showClose: false, // switch to 'true' for enabling close button
	showCloseText: '', // type your text for close button
	closeByEscape: true, // enables closing popup by 'Esc'..
	closeByDocument: true, // ..and by clicking document itself
	holderClass: '', // lets you name custom class for popin holder..
	overlayClass: '', // ..and overlay block
	enableStackAnimation: false, // another animation type
	onBlurContainer: '', // enables blur filter for specified block
	openOnEvent: true, // set to 'false' to init on load
	setEvent: 'click', // use your event like 'mouseover', 'touchmove', etc.
// 	onLoad: function (elem) { ... }, // set custom call before popin is inited..
// 	onUnload: function (elem) { ... }, // ..and after it was closed
	template: 'Your content goes here..' // or function (elem) { ... }
});

</script>



<meta charset="UTF-8">
<title>群組管理</title>
</head>
<body>

	<div class='fieldset span-22' >
		<h3 class="span-2">組員</h3>
		<div class="last span-2">
				<a href="#" title="新增組員" class="worker span-2 button" id="add_worker"
				onclick="MM_openBrWindow('/FunWorkProject2019/addWorker','Add Worker','width=700,height=300')" 
				onKeypress="MM_openBrWindow('/FunWorkProject2019/addWorker','test','width=500,height=300')">新增排班人員</a>
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
					<td>allen</td>
					<td><div width="20" height="20"
							style="width: 20px; height: 20px; border: 1px solid #eee; background-color: #f1c232;">&nbsp;</div></td>
					<td>150</td>
					<td><a
						href="https://shift.ekko.com.tw/group/edit_worker/14119.html"
						title="編輯" class="image edit span-1" id="edit">編輯</a><a
						href="https://shift.ekko.com.tw/group/delete_worker/14119.html"
						title="刪除" class="image delete span-1" id="delete">刪除</a></td>
				</tr>
				<tr>
					<td>2</td>
					<td>奕晴</td>
					<td><div width="20" height="20"
							style="width: 20px; height: 20px; border: 1px solid #eee; background-color: #00ffff;">&nbsp;</div></td>
					<td>150</td>
					<td><a
						href="https://shift.ekko.com.tw/group/edit_worker/14118.html"
						title="編輯" class="image edit span-1" id="edit">編輯</a><a
						href="https://shift.ekko.com.tw/group/delete_worker/14118.html"
						title="刪除" class="image delete span-1" id="delete">刪除</a></td>
				</tr>
				<tr>
					<td>3</td>
					<td>紫瑄</td>
					<td><div width="20" height="20"
							style="width: 20px; height: 20px; border: 1px solid #eee; background-color: #00ff00;">&nbsp;</div></td>
					<td>150</td>
					<td><a
						href="https://shift.ekko.com.tw/group/edit_worker/14117.html"
						title="編輯" class="image edit span-1" id="edit">編輯</a><a
						href="https://shift.ekko.com.tw/group/delete_worker/14117.html"
						title="刪除" class="image delete span-1" id="delete">刪除</a></td>
				</tr>
				<tr>
					<td>4</td>
					<td>怡安</td>
					<td><div width="20" height="20"
							style="width: 20px; height: 20px; border: 1px solid #eee; background-color: #ff0000;">&nbsp;</div></td>
					<td>150</td>
					<td><a
						href="https://shift.ekko.com.tw/group/edit_worker/14116.html"
						title="編輯" class="image edit span-1" id="edit">編輯</a><a
						href="https://shift.ekko.com.tw/group/delete_worker/14116.html"
						title="刪除" class="image delete span-1" id="delete">刪除</a></td>
				</tr>
				<tr>
					<td>5</td>
					<td>卓賢</td>
					<td><div width="20" height="20"
							style="width: 20px; height: 20px; border: 1px solid #eee; background-color: #0000ff;">&nbsp;</div></td>
					<td>150</td>
					<td><a
						href="https://shift.ekko.com.tw/group/edit_worker/14115.html"
						title="編輯" class="image edit span-1" id="edit">編輯</a><a
						href="https://shift.ekko.com.tw/group/delete_worker/14115.html"
						title="刪除" class="image delete span-1" id="delete">刪除</a></td>
				</tr>

			</tbody>
		</table>
	</div>
	</div>
	<div id= "element"">
	<h1>test</h1>
	</div>
	
	

</body>
</body>

</html>