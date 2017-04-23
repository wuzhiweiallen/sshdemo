<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<style type="text/css">
.Main {
	width: 500px;
	height: 500px;
	margin: 0 auto;
	margin-top: 100px;
}

.Input_Box {
	width: 495px;
	height: 160px;
	border: 1px solid #ccc;
	transition: border linear .2s, box-shadow linear .5s;
	-moz-transition: border linear .2s, -moz-box-shadow linear .5s;
	-webkit-transition: border linear .2s, -webkit-box-shadow linear .5s;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	background-color: #fff;
	overflow: hidden;
	position: absolute;
	-moz-box-shadow: 0 0 5px #ccc;
	-webkit-box-shadow: 0 0 5px #ccc;
	box-shadow: 0 0 5px #ccc;
}

.Input_Box>textarea {
	width: 485px;
	height: 111px;
	padding: 5px;
	outline: none;
	border: 0px solid #fff;
	resize: none;
	font: 13px "微软雅黑", Arial, Helvetica, sans-serif;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
}

.Input_Foot {
	width: 100%;
	height: 35px;
	border-top: 1px solid #ccc;
	background-color: #fff;
	-moz-border-radius: 0 0 5px 5px;
	-webkit-border-radius: 0 0 5px 5px;
	border-radius: 0 0 5px 5px;
	position: absolute;
}

.imgBtn {
	float: left;
	margin-top: 8px;
	margin-left: 10px;
	background-image: url(imgs.png);
	background-repeat: no-repeat;
	background-position: 0 -13px;
	height: 18px;
	width: 20px;
	cursor: pointer
}

.imgBtn:active {
	margin-top: 9px;
}

.imgBtn:hover {
	background-position: 0 -31px
}

.postBtn {
	float: right;
	font: 13px "微软雅黑", Arial, Helvetica, sans-serif;
	color: #808080;
	padding: 9px 20px 7px 20px;
	border-left: 1px solid #ccc;
	cursor: pointer;
	-moz-border-radius: 0 0 5px 0;
	-webkit-border-radius: 0 0 5px 0;
	border-radius: 0 0 5px 0;
}

.postBtn:hover {
	color: #333;
	background-color: #efefef;
}

.postBtn:active {
	padding: 10px 20px 6px 20px;
}

.faceDiv {
	width: 500px;
	height: 120px;
	border-top: 1px solid #ccc;
	position: absolute;
	background-color: #fff;
	-moz-border-radius: 5px 5px 0 0;
	-webkit-border-radius: 5px 5px 0 0;
	border-radius: 5px 5px 0 0;
}

.faceDiv>img {
	border: 1px solid #ccc;
	float: left;
	margin-left: -1px;
	margin-top: -1px;
	position: relative;
	width: 24px;
	height: 24px;
	padding: 3px 3px 3px 3px;
	cursor: pointer;
}

.faceDiv>img:hover {
	background-color: #efefef;
}

.faceDiv>img:active {
	padding: 4px 3px 2px 3px;
}
</style>
<head>
<meta charset="UTF-8">
<title></title>
<link href="css/common.css" type="text/css" rel="stylesheet" />
<link href="css/index.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.css">
<link href="css/lanrenzhijia.css" type="text/css" rel="stylesheet" />
<script src="js/zoom.min.js"></script>

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
			<div class="col-xs-12 col-sm-6 col-md-3" style="visibility: hidden">
				<div class="form-group">
					<label for="name">hidden</label> <input type="text"
						class="form-control">
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-3" style="visibility: hidden">
				<div class="form-group">
					<label for="name">hidden</label> <input type="text"
						class="form-control">
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
			<div class="col-xs-12 col-sm-6 col-md-3" style="visibility: hidden">
				<div class="form-group">
					<label for="name">内容</label> <input type="text"
						class="form-control" id="" name="" value="" placeholder="请输入内容">
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-3" style="visibility: hidden"></div>
			<div class="col-xs-12 col-sm-6 col-md-3">
				<button type="submit" id="searchButton"
					class="btn btn-info btn-search">查询</button>
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
			<div class="boxc_c" id="show1" style="display: none"
				contenteditable="true" name="cententaa"></div>

			<div class="boxc_b">
				<a href="#">发布</a>
			</div>
			<form id="timeLine_save" action="timeLine_save.action" method="post"
				enctype="multipart/form-data">
				<textarea id="aa" class="boxc_c" name="content"></textarea>

				<span class="emotion">表情</span></br>
				<%-- <s:file name="timeLineImage" id="timeLineImage" accept="png/jpg"></s:file> --%>
				<div class="img-box full">
					<section class=" img-section">
						<p class="up-p">
							<span class="up-span">最多可以上传5张图片</span>
						</p>
						<div class="z_photo upimg-div clear">
							<!--<section class="up-section fl">
					<span class="up-span"></span>
					<img src="/img/buyerCenter/a7.png" class="close-upimg">
					<img src="/img/buyerCenter/3c.png" class="type-upimg" alt="添加标签">
					<img src="/img/test2.jpg" class="up-img">
					<p class="img-namep"></p>
					<input id="taglocation" name="taglocation" value="" type="hidden">
					<input id="tags" name="tags" value="" type="hidden">
				</section>-->
							<section class="z_file fl">
								<img src="images/a11.png" class="add-img"> <input
									type="file" name="timeLineImage" id="timeLineImage1"
									class="file" value=""
									accept="image/jpg,image/jpeg,image/png,image/bmp" multiple />
							</section>
							<section class="z_file fl" style="display: none">
								<img src="images/a11.png" class="add-img"> <input
									type="file" name="timeLineImage" id="timeLineImage2"
									class="file" value=""
									accept="image/jpg,image/jpeg,image/png,image/bmp" multiple />
							</section>
							<section class="z_file fl" style="display: none">
								<img src="images/a11.png" class="add-img"> <input
									type="file" name="timeLineImage" id="timeLineImage3"
									class="file" value=""
									accept="image/jpg,image/jpeg,image/png,image/bmp" multiple />
							</section>
							<section class="z_file fl" style="display: none">
								<img src="images/a11.png" class="add-img"> <input
									type="file" name="timeLineImage" id="timeLineImage4"
									class="file" value=""
									accept="image/jpg,image/jpeg,image/png,image/bmp" multiple />
							</section>
							<section class="z_file fl" style="display: none">
								<img src="images/a11.png" class="add-img"> <input
									type="file" name="timeLineImage" id="timeLineImage5"
									class="file" value=""
									accept="image/jpg,image/jpeg,image/png,image/bmp" multiple />
							</section>
						</div>
					</section>
				</div>
				<aside class="mask works-mask">
					<div class="mask-content">
						<p class="del-p ">您确定要删除作品图片吗？</p>
						<p class="check-p">
							<span class="del-com wsdel-ok">确定</span><span class="wsdel-no">取消</span>
						</p>
					</div>
				</aside>

			</form>
			<%-- <div class="comment">
				<div class="com_form">
					<textarea class="input" id="saytext" name="saytext"></textarea>
					<p>
						<input type="button" class="sub_btn" value="提交"> <span
							class="emotion">表情</span>
					</p>
				</div>
			</div> --%>

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
			<a href="javascript:void(0)" class="face" title="表情"></a>
			<!-- <div id="Smohan_FaceBox">
				<a href="javascript:void(0)" class="face" title="表情"></a>
			</div> -->
		</div>
		<div id="Zones"></div>


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
				<s:iterator value="list" status="index">
					<div class='a'>
						<div class="b"></div>
						<span id="time"><s:property value="username" />&nbsp;&nbsp;<s:date
								name="logTime" format="yyyy-MM-dd" />&nbsp;&nbsp;<span
							id="hour"><s:date name="logTime" format="hh:mm:ss" /></span></span> <br>
						<p style="padding: 4px">
						<div class="content">
							<s:property value="content" />
						</div>
						<s:iterator value="fileNames" id="fileName">
							<a href="images/timeline/<s:property value="fileName" />"><img
								width="100" height="100px" alt=""
								src="images/timeline/<s:property value="fileName" />"></a>
						</s:iterator>

						</p>
						<s:iterator value="commentsSet" var="comment">
							<s:property value="#comment.cusername"/> : <s:property value="#comment.content"/> &nbsp;&nbsp; <span id="hour"><s:date
								name="#comment.replytime" format="yyyy-MM-dd" />&nbsp;&nbsp;<span
							id="hour"><s:date name="#comment.replytime" format="hh:mm:ss" /></span></span><br>
						</s:iterator>
						<s:if test="username==#session.user.username">
							<s:a href="timeLine_deleteTimeLineById.action?id=%{id}">
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
						
						<textarea id="reply" class="boxc_c" name="reply"></textarea>
						<button type="button" id="replayButton" onclick="replyTimeLineById(<s:property value="#index.index"/>,<s:property value="id"/>)" class="btn btn-info btn-search">评论</button>
					</div>

				</s:iterator>
			</div>
		</div>
	</div>
</body>
<script src="js/jquery.js"></script>
<script src="js/imgUp.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.qqFace.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/locales/bootstrap-datetimepicker.zh-CN.js"
	type="text/javascript" /></script>
<script src="js/jquery.imgbox.pack.js"></script>
<script type="text/javascript">
	/* function uploadImg(){
	
	 $(".file:visible").change(function(){	
	
	 });
	 };	 */
	/* $(function() {
		$(".a").imgbox();
		$("#example2").imgbox({
			'speedIn' : 0,
			'speedOut' : 0,
			'alignment' : 'center',
			'overlayShow' : true,
			'allowMultiple' : false
		});
	}); */
	$(function($) {
		var a = $(".content");
		for (var i = 0; i < a.length; i++) {
			var str = a[i].innerText;
			$(".content").eq(i).html(replace_em(a[i].innerText));
		}

	});
	$(function() {

		$('.emotion').qqFace({

			id : 'facebox',

			assign : 'aa',

			path : 'images/face/' //表情存放的路径

		});
	});
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
	function replyTimeLineById(i,id){
		var content = $("#reply").eq(i).val();
		if(content == ""){
			alert("请输入内容");
			return;
		}
		$.ajax({
			url : '/sshdemo/timeLine_saveReply.action',
			type : 'POST',
			Async : false,
			data : {
				"id":id,
				"content":content,
			},
			success : function(data) {
				window.location.href = "/sshdemo/timeLine_pagination.action?curpage=1";
			},
			error :  function(XMLHttpRequest, textStatus, errorThrown){
		          alert("评论出错");
		       }
		});
	}
	$(function() {
		$(".box")
				.find(".boxc_b")
				.click(
						function() {
							var content = $("#aa").val();//.appendTo("nextbox");
							if (content == "") {
								alert("请输入内容喔！");
								return;
							}
							/* var imgPath = $("#timeLineImage").val();
							//判断上传文件的后缀名
							var strExtension = imgPath.substr(imgPath
									.lastIndexOf('.') + 1);
							if (strExtension != 'jpg' && strExtension != 'gif'
									&& strExtension != 'png'
									&& strExtension != 'bmp') {
								alert("请选择正确的图片文件");
								return;
							} */
							$("#timeLine_save").submit();
							setTimeout(
									function fresh() {
										window.location.href = "/sshdemo/timeLine_pagination.action?curpage=1";
									}, 2000);

							/* $
									.ajax({
										url : '/sshdemo/timeLine_save.action',
										type : 'POST',
										Async : false,
										data : {
											"content" : content,
											"timeLineImage":timeLineImage
										},
										dataType : 'json',
										success : function(data) {
											window.location.href = "/sshdemo/timeLine_pagination.action?curpage=1";
										}
									}); */
						});
		$(".boxc_c").keydown(function(event) {
			var len = $(".boxc_c").text().length;
			if (len > 100) {
				alert("够了，你别输入了，哪儿那么多话儿！");
			}
		});

	});
</script>
</html>