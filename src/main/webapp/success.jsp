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

	<form name="searchForm" id="searchForm"
		action="timeLine_pagination.action" method="post" class="form-group">
		<div class="row">
			<div class="col-xs-12 col-sm-6 col-md-3"></div>
			<div class="col-xs-12 col-sm-6 col-md-3">
				<div class="form-group">

					<label for="name">姓名</label> <select class="form-control"
						name="searchVO.username"
						value="<s:property value="searchVO.username"/>">
						<option value="">请选择姓名</option>
						<s:iterator value="listUsername" id="username">

							<option value="<s:property />"
								<s:if test="#username==#request.searchVO.username">selected="selected"</s:if>><s:property /></option>

						</s:iterator>
					</select>
					<!-- <input type="text" 
						class="form-control" id="name" name="searchVO.username"
						placeholder="请输入名称"> -->
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-3">
				<div class="form-group">
					<label for="name">内容</label> <input type="text"
						class="form-control" id="content" name="searchVO.content"
						value="<s:property value="searchVO.content"/>" placeholder="请输入内容">
				</div>
			</div>
		    <div class="col-xs-12 col-sm-6 col-md-3" style="visibility:hidden">
				<div class="form-group">
					<label for="name">hidden</label> <input type="text"
						class="form-control" >
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-3" style="visibility:hidden">
				<div class="form-group">
					<label for="name">hidden</label> <input type="text"
						class="form-control" >
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-3">
				<div class="form-group">
					<label>选择开始日期：</label>
					<!--指定 date标记-->
					<div class='input-group date'>
						<input type='text' id='datetimeStart'
							value="<s:property value="searchVO.datetimeStart"/>"
							name="searchVO.datetimeStart" class="form-control" /> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
			<div class='col-xs-12 col-sm-6 col-md-3'>
				<div class="form-group">
					<label>选择结束日期：</label>
					<!--指定 date标记-->
					<div class='input-group date'>
						<input type='text' id='datetimeEnd' name="searchVO.datetimeEnd"
							value="<s:property value="searchVO.datetimeEnd"/>"
							class="form-control" /> <span class="input-group-addon">
							<span class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-3" style="visibility:hidden">
				<div class="form-group">
					<label for="name">内容</label> <input type="text"
						class="form-control" id="content" name="searchVO.content"
						value="<s:property value="searchVO.content"/>" placeholder="请输入内容">
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-3" style="visibility:hidden"></div>
			<div class="col-xs-12 col-sm-6 col-md-3">
			    <button type="submit" id="searchButton" class="btn btn-info btn-search">查询</button>
				<%-- <div class="form-group">
					<label for="name">内容</label> <input type="text"
						class="form-control" id="content" name="searchVO.content"
						value="<s:property value="searchVO.content"/>" placeholder="请输入内容">
				</div> --%>
			</div>
		</div>
		
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
					<td colspan="6"><s:a
							href="timeLine_pagination.action?curpage=1">首页</s:a> <s:a
							href="timeLine_pagination.action?curpage=%{#request.prepage}">上页</s:a>
						<s:a
							href="timeLine_pagination.action?curpage=%{#request.nextpage}">下页</s:a>
						<s:a
							href="timeLine_pagination.action?curpage=%{#request.lastpage}">尾页</s:a>
						当前第<s:property value="#request.curpage" />页，总共<s:property
							value="#request.lastpage" />页,<s:property value="#request.total" />条记录</td>
				</tr>
			</table>
		</div>


		<!--时光轴线-->
		<div class="timeline">
			<!-- 	<div class="timeline_t" ></div> -->
			<img class="timeline_t" alt=""
				src="<s:property value="#session.imagepath" />"> <br />&nbsp;
			<s:a action="user_logout.action">
				<button type="button" class="btn btn-info btn-search">注销</button>
			</s:a>
			<br />&nbsp;
			<s:a action="user_toUpdateUserInfoPage.action">
				<button type="button" class="btn btn-info btn-search">更新</button>
			</s:a>
			<!-- <div class="timeline_t" style="background:url('/sshdemo/images/100.png') no-repeat"></div> -->
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
						<span id="time"><s:property value="username" />&nbsp;&nbsp;<s:date
								name="logTime" format="yyyy-MM-dd" />&nbsp;&nbsp;<span
							id="hour"><s:date name="logTime" format="hh:mm:ss" /></span></span> <br>
						<p style="padding: 4px">
						<h4>
							<s:property value="content" />
						</h4>
						</p>
						<s:if test="username==#session.user.username">
							<s:a href="timeLine_deleteTimeLineById.action?userId=%{userId}">
								<%-- <s:property value="username"/>
						<s:property value="#session.user.username"/> --%>
								<br />
								<br />
								<button type="button" id="deleteTimeLine"
									onclick="return window.confirm('这将删除本条宿舍信息，你确定要删除吗？')"
									<s:if test="username!=#session.user.username">disabled="disabled"</s:if>
									class="btn btn-info btn-search">删除</button>
							</s:a>
						</s:if>
						<s:else>
							<br />
							<br />
							<button type="button" disabled="disabled"
								class="btn btn-info btn-search">删除</button>
						</s:else>
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
	/* $("#deleteTimeLine").click(function() {
		$.ajax({
			url : '/sshdemo/timeLine_deleteTimeLineById.action?userId=%{userId}',
			type : 'POST',
			Async : false,
			data : {
				"userId" : userId
			},
			dataType : 'json',
			success : function(data) {
				window.location.href="/sshdemo/timeLine_pagination.action?curpage=1";
			}
		});
	}); */
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

		$(".box")
				.find(".boxc_b")
				.click(
						function() {
							var content = $(".boxc_c").text();//.appendTo("nextbox");
							if (content == "") {
								alert("请输入内容喔！");
								return;
							}
							$
									.ajax({
										url : '/sshdemo/timeLine_save.action',
										type : 'POST',
										Async : false,
										data : {
											"content" : content
										},
										dataType : 'json',
										success : function(data) {
											window.location.href = "/sshdemo/timeLine_pagination.action?curpage=1";
										}
									});

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
							/* $(".boxc_c").text(""); */
						});
		//alert(1);
		$(".boxc_c").keydown(function(event) {
			var len = $(".boxc_c").text().length;
			if (len > 100) {
				alert("够了，你别输入了，哪儿那么多话儿！");
			}
		});

	});
</script>
</html>