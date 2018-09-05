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
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/ams_dasq">档案操作</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/pendingList">外部任务列表</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/pendingList2">内部任务列表</a></li>
			<li onclick="change(this);" id="select_left"><a href="${BASE_URL}/webJump/img">大屏设置</a></li>
		</ul>
	</div>
	   <div class="temp_rt"> 
	       <h2>
            <a href="javascript:location.href=config.humiture">上传图片</a>
          </h2>
        <div id="stote_box">选择库房&nbsp;&nbsp;
        <select class="archive_store_room" id="store" style="margin-right: 350px"></select> </div>
        <div class="stote_box_right"><div class="personattr">增加图片:</div><div id="logoFile_backgound"><input type="file" name="logoFile" id="logoFile" onchange="setImg(this);"></div></div>
 		<div id="thumburlShow" width="700px" height="700px"></div>
 		 <div class="operate_btn" id="tpsc"></div>
	 	<div id="thumburlShow_list">
 			<p></p>
 		</div>	  
   </div>
   </div>
</article>
</body>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</html>
<script src="${STATIC_URL}/modules/web/js/img.js" type="text/javascript"></script>