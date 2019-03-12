
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="icon" type="image/png" href="/favicon.ico" />
        <meta name="keywords" content="線上排班、排班表、派班表" />
        <meta name="description" content="簡單易用線上排班系統，提供滑鼠拖拉設定排班、列印及網頁內崁碼功能。" />
        <!--[if lt IE 8]><link type="text/css" rel="stylesheet" href="/asset/css/ie.css" media="screen, projection" /><![endif]-->
        <link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/screen.css" media="screen, projection" /><link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/print.css" media="print" /><link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/plugin/tabs.css" media="screen, projection" /><link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/style.css" /><link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/redmond/jquery-ui-1.8.17.custom.css" media="screen, projection" /><link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/fullcalendar.css" /><link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/fullcalendar.print.css" media="print" /><link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/jquery.fancybox-1.3.4.css" media="screen, projection" /><link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/style.print.css" media="print" /><link type="text/css" rel="stylesheet" href="https://shift.ekko.com.tw/asset/css/jquery.simple-color-picker.css" media="screen, projection" />        <script type="text/javascript" src="https://shift.ekko.com.tw/asset/js/jquery-1.7.1.min.js"></script><script type="text/javascript" src="https://shift.ekko.com.tw/asset/js/jquery-ui-1.8.17.custom.min.js"></script><script type="text/javascript" src="https://shift.ekko.com.tw/asset/js/fullcalendar.min.js"></script><script type="text/javascript" src="https://shift.ekko.com.tw/asset/js/jquery.fancybox-1.3.4.pack.js"></script><script type="text/javascript" src="https://shift.ekko.com.tw/asset/js/jquery.simple-color-picker.js"></script>        <title>排班網 | 排班</title>
	</head>
	<body>
	<div class="container prepend-top append-bottom">
					<div class="span-24 header">
				<div class="prepend-1 span-11 left prepend-top" id="header-left"> 
	<h1 ><a href="https://shift.ekko.com.tw/" id="site_name">排班網</a> <sub>beta</sub></h1>
</div> 
<div class="span-12 right last" id="header-right">
	<div class='span-12 prepend-top last'>
		<div class="span-2  right"><a href="https://shift.ekko.com.tw/forum.html" class="button span-2">討論區</a></div>
		<div class="span-2 right"><a href="https://shift.ekko.com.tw/demo.html" class="button span-2">線上試用</a></div>
		<!--<div class="span-2 right"><a href="https://shift.ekko.com.tw/price_plan.html" class="button span-2">價格方案</a></div>-->
		<div class="span-2 right"><a href="https://shift.ekko.com.tw/guide.html" class="button span-2">使用手冊</a></div>
		<div class="span-2  right"><a href="https://shift.ekko.com.tw/" class="button span-2">首頁</a></div>
	</div>
</div>			</div>
		 		 		 	<div id="menu" class="span-24 last">
		 		<ul id="menutabs">
<li class="current"><a href="https://shift.ekko.com.tw/shift.html">排班</a></li><li><a href="https://shift.ekko.com.tw/statistics.html">結算統計</a></li><li><a href="https://shift.ekko.com.tw/group.html">群組管理</a></li><li><a href="https://shift.ekko.com.tw/profile.html">個人資訊</a></li></ul>
<div class="append-1 last" style="margin-top:1em;">
	<span>Hello, <strong><a href="https://shift.ekko.com.tw/profile.html">睿</a></strong></span><span>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a href="https://shift.ekko.com.tw/logout.html">登出</a></span></div>		 	</div>
		 		<div id="main" class="span-24">
		        	<div id="breadcrumb" class="span-22 prepend-1 append-1">
                <p id="breadcrumb">
<a href="https://shift.ekko.com.tw/">首頁</a> >
<span class="current">排班</span></p>        	</div>
                        <div id="content" class="span-22 prepend-1 append-1">
        	<div class="span-23">
	<div class="span-2 fieldset prepend-top">
		<div class="span-2">
			<div class="span-2">
			<a href="https://shift.ekko.com.tw/shift/add_worker.html" title="+組員" class="worker span-2 button" id="add_worker">+組員</a>			</div>
			
						<div class='tools span-2'>
			<a href="https://shift.ekko.com.tw/#.html" class="left" title="複製排班" id="shift_copy"><img src="https://shift.ekko.com.tw/asset/img/icons/calendar_copy.png" alt=""/></a>			<a href="https://shift.ekko.com.tw/#.html" class="left" title="列印" id="printer"><img src="https://shift.ekko.com.tw/asset/img/icons/printer.png" alt=""/></a>			<a href="https://shift.ekko.com.tw/shift/ajax_iframe.html" class="left" title="網頁崁入碼" id="iframe"><img src="https://shift.ekko.com.tw/asset/img/icons/script_code.png" alt=""/></a>			<a href="https://shift.ekko.com.tw/#.html" class="left trash" title="刪除" id="trash"><img src="https://shift.ekko.com.tw/asset/img/icons/trash.png" alt=""/></a>			</div>
					</div>
				<hr>
				<div class="span-2 last">
			<div id="workers">
			<a href="https://shift.ekko.com.tw/#.html" class="worker button span-2" id="14140" style="background-color:#ffff00" rel="1">Maxson</a><a href="https://shift.ekko.com.tw/#.html" class="worker button span-2" id="14141" style="background-color:#45818e" rel="1">Sian</a><a href="https://shift.ekko.com.tw/#.html" class="worker button span-2" id="14142" style="background-color:#073763" rel="1">陳姿穎</a><a href="https://shift.ekko.com.tw/#.html" class="worker button span-2" id="14153" style="background-color:#ffff00" rel="1">林瑪莉</a><a href="https://shift.ekko.com.tw/#.html" class="worker button span-2" id="14154" style="background-color:#0000ff" rel="1">d</a><a href="https://shift.ekko.com.tw/#.html" class="worker button span-2" id="14155" style="background-color:#0000ff" rel="1">d2</a><a href="https://shift.ekko.com.tw/#.html" class="worker button span-2" id="14156" style="background-color:#0000ff" rel="1">D3</a><a href="https://shift.ekko.com.tw/#.html" class="worker button span-2" id="14157" style="background-color:#0000ff" rel="1">D4</a>			</div>
		</div>
	</div>
	<div class="span-20 last">
		<div id="calendar"></div>
	</div>
</div>
<script type='text/javascript'>
	$(document).ready(function() {	
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
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
			
			var color = $(this).css('backgroundColor');
			
			$(this).live('click', function(event){
				event.preventDefault();
		    	if ($(this).attr("rel")== 1 ) {
		    		$(this).attr("rel", 0); 	
		    	} else {
		    		$(this).attr("rel", 1);
		    	}
		    	if ($(this).attr("rel") == 1){
		    		$('#calendar').fullCalendar('addEventSource', {
		   				url: "https://shift.ekko.com.tw/shift/ajax_get_worklogs/"+ $(this).attr('id') + ".html",
		   				color: color,    // an option!
			            textColor: '#FFFFFF'  // an option!
		   			});
		   			$(this).css('background-color', color);
		   			$(this).draggable('enable'); 
		    	} else {
		    		$('#calendar').fullCalendar('removeEventSource', "https://shift.ekko.com.tw/shift/ajax_get_worklogs/" + $(this).attr('id')  + ".html");
		    		$(this).css('background-color', '#96968B');
		    		$(this).draggable('disable');
		    	}
			});
			// make the event draggable using jQuery UI
			$(this).draggable({
				helper: 'clone',
				opacity: 0.5,
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});
			
		});

			
		$('#calendar').fullCalendar({
			weekMode:　'liquid',
			timeFormat: 'H:mm{ - H:mm}', // uppercase H for 24-hour clock
			allDaySlot: false,
			contentHeight: 960,
			defaultView: 'month',
			slotMinutes: 30,
			maxTime: 22,
			minTime: 0,
			theme: false,
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			editable: true,
						buttonText : { today: "今日", month: "月", week:"週", day:"日" },
			dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
			dayNamesShort: ['日','一','二','三','四','五','六'],
		    monthNamesShort: ['1月', '2月', '3月', '4月', '5月', '6月', '7月',
	    '8月', '9月', '10月', '11月', '12月'],
		    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
						columnFormat: {
                month: 'dddd',    // Monday, Wednesday, etc
                week: 'ddd M/d', // Monday 9/7
                day: 'dddd M/d'  // Monday 9/7
            },
			droppable: true, // this allows things to be dropped onto the calendar !!!
			drop: function(date) { // this function is called when something is dropped
			
				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');
				
				// we need to copy it, so that multiple events don't have a reference to the same object
				var copiedEventObject = $.extend({}, originalEventObject);
				
				// assign it the date that was reported
				
				copiedEventObject.start = date;
				copiedEventObject.end    = (date.getTime() + 4*1800000)/1000; // put your desired end time here
       			copiedEventObject.allDay = false;
       			
				// render the event on the calendar
				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
				$.ajax({
					url: 'https://shift.ekko.com.tw/shift/ajax_add_worklog.html',
					type: 'GET',
					dataType : 'json',
					data: { 
							id: copiedEventObject.id,
							start: date.getTime()/1000,
							end:  copiedEventObject.end 
					},
					success: function(data){
						if (data.status == 'success'){
							//copiedEventObject.id = data.id;
							//$('#calendar').fullCalendar('renderEvent', copiedEventObject, false);
							$('#calendar').fullCalendar('refetchEvents');
						}
						else {
							//console.log(copiedEventObject);
							alert('『' + copiedEventObject.title + '』' + data.msg);
						}
					}
				});
			},
			eventResize: function(event,dayDelta,minuteDelta,revertFunc) {
				$.ajax({
					url: 'https://shift.ekko.com.tw/shift/ajax_update_worklog.html',
					type: 'GET',
					dataType : 'json',
					data: { 
							id: event.id,
							start: event.start.getTime()/1000,
							end:  event.end.getTime()/1000
					},
					success: function(data){
						if (data.status != 'success'){
							revertFunc();
							alert('『' + event.title + '』' + data.msg);
						}
					}
				});
			},
			eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {
				$.ajax({
					url: 'https://shift.ekko.com.tw/shift/ajax_update_worklog.html',
					type: 'GET',
					dataType : 'json',
					data: { 
							id: event.id,
							start: event.start.getTime()/1000,
							end:  event.end.getTime()/1000
					},
					success: function(data){
						if (data.status != 'success'){
							revertFunc();
							alert('『' + event.title + '』' + data.msg);
						}
					}
				});
			},
			eventClick: function(event, jsEvent, view) {
				$.ajax({
					url: 'https://shift.ekko.com.tw/shift/ajax_delete_worklog.html',
					type: 'GET',
					dataType : 'json',
					data: { 
							id: event.id,
					},
					success: function(data){
						if (data.status != 'success'){
							revertFunc();
							alert(data.msg);
						} else {
							$('#calendar').fullCalendar("removeEvents", event.id);
						}
					}
				});
		    },
			// event source
			eventSources: [{
			            url: "https://shift.ekko.com.tw/shift/ajax_get_worklogs/14140.html", 
			            color: "#ffff00"    // an option!
			
			        },{
			            url: "https://shift.ekko.com.tw/shift/ajax_get_worklogs/14141.html", 
			            color: "#45818e"    // an option!
			
			        },{
			            url: "https://shift.ekko.com.tw/shift/ajax_get_worklogs/14142.html", 
			            color: "#073763"    // an option!
			
			        },{
			            url: "https://shift.ekko.com.tw/shift/ajax_get_worklogs/14153.html", 
			            color: "#ffff00"    // an option!
			
			        },{
			            url: "https://shift.ekko.com.tw/shift/ajax_get_worklogs/14154.html", 
			            color: "#0000ff"    // an option!
			
			        },{
			            url: "https://shift.ekko.com.tw/shift/ajax_get_worklogs/14155.html", 
			            color: "#0000ff"    // an option!
			
			        },{
			            url: "https://shift.ekko.com.tw/shift/ajax_get_worklogs/14156.html", 
			            color: "#0000ff"    // an option!
			
			        },{
			            url: "https://shift.ekko.com.tw/shift/ajax_get_worklogs/14157.html", 
			            color: "#0000ff"    // an option!
			
			        }]		});
			
		$("a#add_worker").live('click', function(e){
			e.preventDefault();
			$.fancybox(this, {
				'scrolling'		: 'no',
				'titleShow'		: false,
				'centerOnScroll': true,
				'autoScale'		: false,
				'enableEscapeButton': true, 	
				'type'			: 'inline'
			});
		});
		
		$("a#iframe").live('click', function(e){
			e.preventDefault();
			var worker_ids = [];
			var view = $('#calendar').fullCalendar('getView');
			$('div#workers a').each(function(){
				if ($(this).attr('rel') == 1){
					worker_ids.push($(this).attr('id'));
				}
			});
			$.ajax({
				url: 'https://shift.ekko.com.tw/shift/ajax_iframe.html',
				type: 'GET',
				dataType : 'json',
				data: { 
						worker_ids: worker_ids, calendar_defaultview: view.name
				},
				success: function(data){
					$.fancybox(data.code);
				}
			});
		});
		
		$( "a.trash" ).droppable({
			 drop: function(event, ui) {
			 	console.log(ui.draggable.text());
			 	if (confirm("確定刪除 『"+ui.draggable.text() + "』 ?")){
					$.ajax({
						url: 'https://shift.ekko.com.tw/shift/ajax_delete_worker.html',
						type: 'GET',
						dataType : 'json',
						data: { 
								id: ui.draggable.attr('id')
						},
						success: function(data){
							if (data.status != 'success'){
								alert(data.msg);
							} else {
								location.replace('https://shift.ekko.com.tw/shift.html');
							}
						}
					});
			 	}
			 }
		});
		
		$('a#shift_copy').bind('click', function (e){
			e.preventDefault();
			var view = $('#calendar').fullCalendar('getView');
			var start = view.start.getTime()/1000;
			var end = view.end.getTime()/1000;
			var type = view.name;
			var worker_ids = [];
			$('div#workers a').each(function(){
				if ($(this).attr('rel') == 1){
					worker_ids.push($(this).attr('id'));
				}
			});
			$.ajax({
				url: 'https://shift.ekko.com.tw/shift/ajax_copy_shift.html',
				type: 'GET',
				dataType : 'json',
				data: { 
						type: type, start: start, end: end, worker_ids: worker_ids
				},
				success: function(data){
					if (data.status == 'success'){
						$('#calendar').fullCalendar('refetchEvents');
						alert(data.msg);
					} else {
						alert(data.msg);
					}
				}
			});
		});
		
		$('a#trash').bind('click', function(e){
			e.preventDefault();
		});
		$('a#printer').bind('click', function(e){
			window.print();
			return false;
		});
		
		$('div#main').ajaxStart(function(){
			$.fancybox.showActivity();
		}).ajaxStop(function(){
			$.fancybox.hideActivity();
		});
	});
</script>		</div>
		</div>
		
					<div class="span-24" id="footer">
					<p>請使用Internet Explorer 90, Firefox 3x, Chrome 10x 以上版本之瀏覽器，螢幕解析度1024x768以上，以達使用完整功能及最佳顯示效果。</p>
  <p id="links">
  <a href="https://shift.ekko.com.tw/about_us.html">關於樸式設計</a> | 
  <a href="https://shift.ekko.com.tw/agreement.html">服務及聲明條款</a> | 
  <a href="https://shift.ekko.com.tw/privacy.html">隱私權保護政策</a> | 
  <a href="https://shift.ekko.com.tw/contact.html">聯絡我們</a>  Copyright &copy; 2011~2012 permastyle studio. All Rights Reserved</p>			</div>
		    </div>
    <script type="text/javascript">
    $(document).ready(function() {
		if($("div#status_message").length > 0){
			$("div#status_message").delay(4000).slideUp(1000);
		}
    });
    </script>
	</body>
</html>
