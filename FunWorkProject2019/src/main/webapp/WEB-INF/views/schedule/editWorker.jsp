<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link type="text/css" rel="stylesheet" href="/FunWorkProject2019/css/jquery.simple-color-picker.css" media="screen, projection" />
	
<script type="text/javascript" src='<c:url value="/js/jquery-1.7.1.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/jquery.fancybox-1.3.4.pack.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/jquery.simple-color-picker.js"/>'></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("input#name").focus();
						$('input#color').simpleColorPicker(
								{
									onChangeColor : function(color) {
										$('input#color').val(color);
									},
									showEffect : 'fade',
									hideEffect : 'slide',
									enableEscapeButton : true,
									colors : [ '#660000', '#783f04', '#7f6000',
											'#274e13', '#0c343d', '#073763',
											'#20124d', '#4C1130', '#990000',
											'#b45f06', '#bf9000', '#38761d',
											'#134f5c', '#0b5394', '#351c75',
											'#741b47', '#ff0000', '#ff9900',
											'#ffff00', '#00ff00', '#00ffff',
											'#0000ff', '#9900ff', '#ff00ff',
											'#cc0000', '#e69138', '#f1c232',
											'#6aa84f', '#45818e', '#3d85c6',
											'#674ea7', '#a64d79', '#e06666',
											'#f6b26b', '#ffd966', '#93c47d',
											'#76a5af', '#6fa8dc', '#8e7cc3',
											'#c27ba0' ]
								});
						$("form#work_form #message").hide();
						$("form#work_form #name").focus();
						$("form#work_form input[name='submit']")
								.bind(
										'click',
										function(e) {
											e.preventDefault();
											$
													.ajax({
														url : 'https://shift.ekko.com.tw/group/ajax_add_worker.html',
														type : 'POST',
														dataType : 'json',
														data : {
															name : $(
																	"input[name='name']")
																	.val(),
															color : $(
																	"input[name='color']")
																	.val(),
															rate : $(
																	"input[name='rate']")
																	.val(),
															ci_csrf_token : $(
																	"input[name='ci_csrf_token']")
																	.val()
														},
														success : function(data) {
															if (data.status == 'error') {
																$("div#message")
																		.html(
																				data.msg)
																		.show();
															}
															if (data.status == 'success') {
																location
																		.replace('https://shift.ekko.com.tw/group/worker.html');
																/*
																$.ajax({
																	url: 'https://shift.ekko.com.tw/shift/ajax_get_workers.html',
																	type: 'GET',
																	dataType : 'json',
																	success: function(data){
																		$('div#workers').html(data);
																		$('div#workers a').each(function() {
																
																		// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
																		// it doesn't need to have a start or end
																		var eventObject = {
																			id: $(this).attr('id'),
																			title: $.trim($(this).text()), // use the element's text as the event title
																			backgroundColor: $(this).css('backgroundColor')
																		};
																		
																		// store the Event Object in the DOM element so we can get to it later
																		$(this).data('eventObject', eventObject);
																		
																		// make the event draggable using jQuery UI
																		$(this).draggable({
																			helper: 'clone',
																			opacity: 0.5,
																			zIndex: 999,
																			revert: true,      // will cause the event to go back to its
																			revertDuration: 0  //  original position after the drag
																		});
																		
																	});	
																	}
																})
																 */
																$.fancybox
																		.close();

															}
														}
													});
											return false;
										});
						$("input[name='cancel']").bind('click', function(e) {
							e.preventDefault();
							$.fancybox.close();
						});
					});
</script>

<title>Insert title here</title>
</head>
<body>

<div style="padding: 30px; width: 300px; height: auto; background-color: #f7d4a6">
	<div id="message"></div>
	<div class='worker'>
		<form action="https://shift.ekko.com.tw/shift/ajax_add_worker.html"
			method="post" accept-charset="utf-8" class="inline" id="work_form">
			<div style="display: none">
				<input type="hidden" name="ci_csrf_token"
					value="e519e5c522f469a3383c3d5ab450446e" />
			</div>
			<div class="span-7 fieldset last">
				<h3>新增組員</h3>
				<hr>
				<div class="span-7">
					<div class='span-2'>
						<label class="right"><font class="red">*</font>名稱</label>
					</div>
					<div class='span-5 last'>
						<input type="text" name="name" id="name" />
					</div>
				</div>

				<div class="span-7">
					<div class='span-2'>
						<label class="right"><font class="red">*</font>識別色</label>
					</div>
					<div class='span-5 last'>
						<input type="text" name="color" id="color" />
					</div>
				</div>
				<div class="span-7">
					<div class='span-2'>
						<label class="right"><font class="red">*</font>基本時薪</label>
					</div>
					<div class='span-5 last'>
						<input type="text" name="rate" id="rate" value="150" />
					</div>
				</div>
			</div>

			<div class='prepend-2 prepend-top span-5 last append-bottom'>
				<input type="submit" name="submit" value="送出"
					class="button span-2" /> <input type="submit" name="cancel"
					value="取消" class="button span-2" />
			</div>
		</form>
	</div>
</div>

</body>
</html>