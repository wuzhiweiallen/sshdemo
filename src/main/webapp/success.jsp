<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery QQ时间轴代码</title>

<style>
* {
	margin: 0;
	padding: 0
}

body {
	font-size: 14px;
	font-family: "微软雅黑";
	background: url("images/2.jpg") top no-repeat;
	background-attachment: fixed;
	z-index: 0;
	background-size: 100%;
}

.box {
	width: 960px;
	height: 2000px;
	margin: 100px auto;
	position: relative;
	z-index: 1;
}

/*内容发布区域*/
.box .boxcenter {
	width: 500px;
	height: 200px;
	background: #fff;
	position: absolute;
	top: 80px;
	left: 180px;
	border: 1px solid green;
	border-radius: 6px;
}

.box .boxcenter .boxc_t {
	height: 30px;
	line-height: 30px;
}

.box .boxcenter .boxc_t h4 {
	padding-left: 20px;
	background: #3BBFB4;
	border-top-left-radius: 6px;
	border-top-right-radius: 6px;
}

.box .boxcenter .boxc_c {
	width: 460px;
	height: 100px;
	border: 1px solid #A6C8FF;
	margin: 8px auto;
	text-indent: 10px;
	box-shadow: 0 0 4px #A6C8FF;
}

.box .boxcenter .boxc_b {
	width: 80px;
	height: 30px;
	position: absolute;
	right: 10px;
	bottom: 8px;
}

.box .boxcenter .boxc_b a {
	font-size: 14px;
	color: #fff;
	line-height: 30px;
	background: #3bbfb4;
	border-radius: 6px;
	display: block;
	text-align: center;
	text-decoration: none;
}

.box .boxcenter .boxc_b a:hover {
	background: #2C8E86;
}

.box .timeline {
	width: 60px;
	height: 100%;
	position: absolute;
	top: 100px;
	left: 80px;
	border-right: 2px solid #5d7895;
} /**/
.timeline .timeline_t {
	width: 50px;
	height: 50px;
	border-radius: 50%;
	border: 1px solid #fff;
	background: url("images/100.png") no-repeat;
	background-size: 100%;
}

.timeline .nextbox {
	width: 380px;
	height: 80px;
	position: absolute;
	top: 260px;
	left: 60px;
}

.a {
	width: 380px;
	height: 80px;
	background: #fff;
	border-radius: 6px;
	margin-top: 30px;
	font-size: 16px;
	line-height: 20px;
	text-indent: 20px;
	word-break: break-all;
	position: relative;
	left: 6px;
}

.a .b {
	width: 6px;
	height: 6px;
	border-radius: 50%;
	border: 2px solid #fff;
	background: #9DCFE1;
	position: absolute;
	top: 35px;
	left: -10px;
}

#time {
	font-size: 20px;
	color: #ababab;
}

#hour {
	font-size: 12px;
	color: #92CADE;
}
</style>

</head>


<body>
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
		</div>

		<!--时光轴线-->
		<div class="timeline">
			<div class="timeline_t"></div>
			<div class="nextbox">
				<s:iterator value="timeLineList">

					<div class='a'>
						<div class="b"></div>
						<span id="time">
						<s:property value="logTime" />&nbsp;&nbsp;<span
							id="hour">17:50</span></span><br>
						<p style="padding: 4px">
							<s:property value="content" />
						</p>
					</div>
				</s:iterator>
			</div>
		</div>

	</div>

	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".box").find(".boxc_b").click(
					function() {
						var content = $(".boxc_c").text();//.appendTo("nextbox");
						if (content == "") {
							alert("请输入内容喔！");
							return;
						}
						$.ajax({  
				            url:'/sshdemo/timeLine_save.action',  
				            type:'POST', 
				            Async:false,
				            data:{"content":content},  
				            dataType:'json',  
				            success:function (data) {  
				            	alert("1");
				               /*  $("#allUser").append("输出了：id:" + data.hello); */  
				            }  
				        });  
						window.location.reload()
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
	</script>
	<div style="text-align: center;"></div>
</body>
</html>