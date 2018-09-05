<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	<body>
<!-- 引入资源文件 -->
<%@ include file="/WEB-INF/view/web/common/resource.jsp"%>
<!-- 引入头部文件 -->
	<%@ include file="/WEB-INF/view/web/common/header.jsp"%>
		<title></title>
	</head><article>
  <div class="collection">
  <!--左边导航栏-->
	<div class="leftsidebar">
		<ul>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/ywcz_dacx">档案查询</a></li>
			<li onclick="change(this);" id="select_left"><a href="${BASE_URL}/webJump/ams_dasq">档案操作</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/pendingList">外部任务列表</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/pendingList2">内部任务列表</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/img">大屏设置</a></li>
		</ul>
	</div>
	<!--左边导航栏-->
	<!--主体-->
			<div class="datamain archive_main">
				<ul>
					<li>
					<a href="${BASE_URL}/webJump/ams_cdsq3">
						<div class="archive_btn_click">
							<img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_1_bg.png"/>
							<img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_2_bg.png"/>
							<img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_3_bg.png"/>
							<img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_5_bg.png"/>
							<img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_4_bg.png"/>
						    <img src="${STATIC_URL}/images/4.2/ams/icon_export.png"/>
						</div>
						<i>我要借档</i>
					</a>
					</li>
					<li>
					<a href="${BASE_URL}/webJump/ams_cdsq4">
						<div class="archive_btn_click">
							<img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_1_bg.png"/>
							<img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_2_bg.png"/>
							<img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_3_bg.png"/>
							<img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_5_bg.png"/>
							<img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_4_bg.png"/>
						    <img src="${STATIC_URL}/images/4.2/ams/icon_channel.png"/>
						</div>
						<i>我要还档</i>
						</a>
					</li>
				</ul>
			</div>	
			</div>
</article>
	</body>
	    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
<script type="text/javascript" src="${STATIC_URL}/modules/web/js/ams_dasq.js"></script>
</html>