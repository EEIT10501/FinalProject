<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link type="text/css" rel="stylesheet"
	href="/FunWorkProject2019/css/jquery.dataTables.min.css"
	media="screen, projection" />

<link type="text/css" rel="stylesheet"
	href="https://shift.ekko.com.tw/asset/css/jquery.fancybox-1.3.4.css"
	media="screen, projection" />

<!-- <link type="text/css" rel="stylesheet" -->
<!-- 	href="/FunWorkProject2019/css/screen.css" -->
<!-- 	media="screen, projection" /> -->
<!-- <link type="text/css" rel="stylesheet" -->
<!-- 	href="/FunWorkProject2019/css/print.css" media="print" /> -->

<script type="text/javascript"
	src='<c:url value="/js/jquery-3.3.1.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/js/jquery.dataTables.min.js"/>'></script>
<script type='text/javascript'>
	var jQuery132 = jQuery.noConflict(true);
</script>
<script type="text/javascript"
	src='<c:url value="/js/jquery-1.7.1.min.js"/>'></script>
<script type="text/javascript"
	src='<c:url value="/js/jquery.fancybox-1.3.4.pack.js"/>'></script>


<script type="text/javascript">
	jQuery132(document).ready(function() {
		jQuery132('#example').DataTable({
			"language" : {
				"lengthMenu" : "Display _MENU_ records per page",
				"zeroRecords" : "Nothing found - sorry",
				"info" : "Showing page _PAGE_ of _PAGES_",
				"infoEmpty" : "No records available",
				"infoFiltered" : "(filtered from _MAX_ total records)"
			}
		});
	});
</script>

<title>班別管理</title>
</head>

<body>

	<div class="" style="background-color: #FFFFCC">
		<h2>班別管理</h2>
		<div class="" style="background-color: #CCFFFF">
			<a href='<c:url value="/addSchedule"/>' title="新增班表"
				class="worker span-2 button" id="addSchedule">新增排班人員</a>
		</div>
	</div>
	<table id="example" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>班別代碼</th>
				<th>班別名稱</th>
				<th>識別色</th>
				<th>起始時間</th>
				<th>結束時間</th>
				<th>休息時間</th>
				<th>工時</th>
				<th>編輯</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>早班</td>
				<td><div width="20" height="20"
						style="width: 20px; height: 20px; border: 1px solid #000000; background-color: #FFCCCC; margin: auto;">&nbsp;</div></td>
				<td>06:00</td>
				<td>14:30</td>
				<td>0.5</td>
				<td>8.0</td>
				<td><a
					href="https://shift.ekko.com.tw/group/edit_worker/14118.html"
					title="編輯" class="image edit span-1" id="edit">編輯</a><a
					href="https://shift.ekko.com.tw/group/delete_worker/14118.html"
					title="刪除" class="image delete span-1" id="delete">刪除</a></td>
			</tr>
			<tr>
				<td>2</td>
				<td>中班</td>
				<td><div width="20" height="20"
						style="width: 20px; height: 20px; border: 1px solid #000000; background-color: #000fff; margin: auto;">&nbsp;</div></td>
				<td>10:00</td>
				<td>18:30</td>
				<td>0.5</td>
				<td>8.0</td>
				<td><a
					href="https://shift.ekko.com.tw/group/edit_worker/14118.html"
					title="編輯" class="image edit span-1" id="edit">編輯</a><a
					href="https://shift.ekko.com.tw/group/delete_worker/14118.html"
					title="刪除" class="image delete span-1" id="delete">刪除</a></td>
			</tr>
			<tr>
				<td>3</td>
				<td>晚班</td>
				<td><div width="20" height="20"
						style="width: 20px; height: 20px; border: 1px solid #000000; background-color: #00f0ff; margin: auto;">&nbsp;</div></td>
				<td>14:30</td>
				<td>21:00</td>
				<td>0.5</td>
				<td>8.0</td>
				<td><a
					href="https://shift.ekko.com.tw/group/edit_worker/14118.html"
					title="編輯" class="image edit span-1" id="edit">編輯</a><a
					href="https://shift.ekko.com.tw/group/delete_worker/14118.html"
					title="刪除" class="image delete span-1" id="delete">刪除</a></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<th>班別代碼</th>
				<th>班別名稱</th>
				<th>識別色</th>
				<th>起始時間</th>
				<th>結束時間</th>
				<th>休息時間</th>
				<th>工時</th>
				<th>編輯</th>
			</tr>
		</tfoot>
	</table>



	<script type="text/javascript">
		$(document).ready(function() {
			$("a#addSchedule").live('click', function(e) {
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
			;
		})
	</script>
</body>

</html>

</body>
</html>