<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>班別管理</title>
</head>

<body>
	<div  class="" style="background-color: #FFFFCC">
		<h2>班別管理</h2>
    	<div class="" style="background-color: #CCFFFF">	
			<a href="#" title="新增班表" class="worker span-2 button" id="add_shift"
				onclick="MM_openBrWindow('/FunWorkProject2019/addWorker','Add Worker','width=700,height=300')"
				onKeypress="MM_openBrWindow('/FunWorkProject2019/addWorker','test','width=500,height=300')">新增排班人員</a>
		</div>
		<div class="" style="background-color: #FFCCCC">
			<table class="">
				<thead>
					<tr>
						<th class="span-1">班別代碼</th>
						<th class="span-3">班別名稱</th>
						<th class="span-3">識別色</th>
						<th class="span-2">班別起時</th>
						<th class="span-2">班別訖時</th>
						<th class="span-2">休息時間</th>
						<th class="span-2">班別工時</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>早班</td>
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
						<td>中班</td>
						<td><div width="20" height="20"
								style="width: 20px; height: 20px; border: 1px solid #eee; background-color: #00ffff;">&nbsp;</div></td>
						<td>150</td>
						<td><a
							href="https://shift.ekko.com.tw/group/edit_worker/14118.html"
							title="編輯" class="image edit span-1" id="edit">編輯</a><a
							href="https://shift.ekko.com.tw/group/delete_worker/14118.html"
							title="刪除" class="image delete span-1" id="delete">刪除</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>

</body>
</html>