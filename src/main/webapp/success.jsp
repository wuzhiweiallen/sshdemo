<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.css">

</head>
<body>
	<%-- <form action="user_login" class="login" method="post">
		<div class="input-group col-md-3">
			<div class="form-group">
				<label for="name">名称</label> <input type="text" class="form-control"
					id="name" placeholder="请输入名称">
			</div>
			<span class="input-group-btn">
				<button class="btn btn-info btn-search">查找</button>
			</span>
		</div>
	</form> --%>

	<form name="form1" action="timeLine_searchTimeLine.action"
		method="post" class="form-group">
		<div class="row">
			<div class="col-xs-12 col-sm-6 col-md-3">
				<div class="form-group">
					<label for="name">姓名</label> <input type="text"
						class="form-control" id="name" name="searchVO.username"
						placeholder="请输入名称">
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-3">
				<div class="form-group">
					<label for="name">内容</label> <input type="text"
						class="form-control" id="content" name="searchVO.content"
						placeholder="请输入内容">
				</div>
			</div>
			<div class="row">
				<div class='col-xs-12 col-sm-6 col-md-3'>
					<div class="form-group">
						<label>选择日期：</label>
						<!--指定 date标记-->
						<div class='input-group date'>
							<input type='text' id='datetimeStart'
								name="searchVO.datetimeStart" class="form-control" /> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<div class='col-xs-12 col-sm-6 col-md-3'>
					<div class="form-group">
						<label>选择日期+时间：</label>
						<!--指定 date标记-->
						<div class='input-group date'>
							<input type='text' id='datetimeEnd' name="searchVO.datetimeEnd"
								class="form-control" /> <span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<button type="submit" class="btn btn-info btn-search">查询</button>
	</form>

	<div class="box">

		<!--内容发布区域-->
		<div class="boxcenter">
			<div class="boxc_t">
				<h4>随便说点什么吧...</h4>
			</div>
			<div class="boxc_c" contenteditable="true" id="aa"></div>
			<div class="boxc_b">
				<a href="#">发布</a>
			</div>
			<table>
				<tr align="center">
					<td>
					<td colspan="6"><s:a href="timeLine_pagination.action?curpage=1">首页</s:a>
						<s:a href="timeLine_pagination.action?curpage=%{#request.prepage}">上页</s:a> <s:a
						href="timeLine_pagination.action?curpage=%{#request.nextpage}">下页</s:a> <s:a
						href="timeLine_pagination.action?curpage=%{#request.lastpage}">尾页</s:a> 当前第<s:property
							value="#request.curpage" />页，总共<s:property value="#request.total" />条记录</td>
				</tr>
			</table>
		</div>


		<!--时光轴线-->
		<div class="timeline">
			<div class="timeline_t"></div>
			<%-- <div class="nextbox">
				<s:iterator value="list">
				<div class="b"></div>
				    <div class="boxcenter">
			<div class="boxc_t">
				<h4><s:property value="logTime" /></h4>
			</div>
			<div class="boxc_c" contenteditable="true" id="aa"><s:property value="content" /></div>
			<div class="boxc_b">
				<a href="#">删除</a>
			</div>
		    </div> --%>
			<div class="nextbox">
				<s:iterator value="list">
					<div class='a'>
						<div class="b"></div>
						<span id="time"> <s:property value="logTime" />&nbsp;&nbsp;<%-- <span
							id="hour">17:50</span> --%></span><br>
						<p style="padding: 4px">
							<h4>
							<s:property value="content" />
						</h4>
						</p>
						<s:a href="timeLine_deleteTimeLineById.action?userId=%{userId}">
							<button type="button" id="deleteTimeLine"
								class="btn btn-info btn-search">删除</button>
						</s:a>
					</div>

				</s:iterator>
			</div>
		</div>
	</div>
	<div style="text-align: center;"></div>
</body>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/locales/bootstrap-datetimepicker.zh-CN.js"
	type="text/javascript" /></script>
<script type="text/javascript">
	$('#datetimeStart').datetimepicker({
		minView : "month", //选择日期后，不会再跳转去选择时分秒 
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		todayBtn : 1,
		autoclose : 1,
	});
	$('#datetimeEnd').datetimepicker({
		minView : "month", //选择日期后，不会再跳转去选择时分秒 
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		todayBtn : 1,
		autoclose : 1,
	});
	$(function() {
		$(".box").find(".boxc_b").click(function() {
			var content = $(".boxc_c").text();//.appendTo("nextbox");
			if (content == "") {
				alert("请输入内容喔！");
				return;
			}
			$.ajax({
				url : '/sshdemo/timeLine_save.action',
				type : 'POST',
				Async : false,
				data : {
					"content" : content
				},
				dataType : 'json',
				success : function(data) {

				}
			});
			window.location.reload();
			/* var dateDom = new Date();
			//获取本地时间，年月日时分
			var year = dateDom.getFullYear();
			var month = dateDom.getMonth() + 1;
			var day = dateDom.getDate();
			var hour = dateDom.getHours();
			var min = dateDom.getMinutes();
			$(".nextbox").prepend(
					"<div class='a'>" + "<div class='b'></div>"
							+ "<span id='time'>" + year + "-"
							+ month + "-" + day + "&nbsp;&nbsp;"
							+ "<span id='hour'>" + hour + ":" + min
							+ "</span>" + "</span>" + "<br>"
							+ "<p style='padding:4px'>" + center
							+ "</p>" + "</div>"); */
			$(".boxc_c").text("");
		});
		//alert(1);
		$(".boxc_c").keydown(function(event) {
			var len = $(".boxc_c").text().length;
			if (len > 70) {
				alert("够了，你别输入了，哪儿那么多话儿！");
			}
		});

	});
</script></
						html>