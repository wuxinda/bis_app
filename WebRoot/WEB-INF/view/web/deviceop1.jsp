<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <html> -->
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入资源文件 -->
<%@ include file="/WEB-INF/view/web/common/resource.jsp"%>
<title>设备操控</title>
<script src="${STATIC_URL}/modules/web/js/deviceop1.js" type="text/javascript"></script>
</head>
<body>
<!-- 引入头部文件 -->
	<%@ include file="/WEB-INF/view/web/common/header.jsp"%>
	<article>
	<!--设备操控device_control模块开始-->
	<div class="device_control">
	   	<ul class="device_control_icon clearfix">
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/compact-shelving-normal.png"></div><p>智能密集架</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>智能门禁</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>智能灯光</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>智能环境</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>智能安防</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>数字化档案</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>智能消防</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>公共广播</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>LED综合</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>RFID系统</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>异质备份</p></a></li>
	   	</ul>
	   	    
	   	<ul class="shelves_options clearfix">
	   	    <li>库房  <select id="store"></select></li>
	   	    <li>库区  <select id="stroreArea"></select></li>
	   	    <li>密集架   <select id="deviceManage"></select></li>
	    </ul>
	   	    
		<ul class="shelves_show clearfix">
	   	    <li><img src="${STATIC_URL}/images/4.2/move.png"></li>
  	   	    <li><img src="${STATIC_URL}/images/4.2/move.png"></li>
	   	    <li><img src="${STATIC_URL}/images/4.2/immobilization.png"></li>
  	   	    <li><img src="${STATIC_URL}/images/4.2/move.png"></li>
  	   	    <li><img src="${STATIC_URL}/images/4.2/move.png"></li>
	   	</ul>
	   	
	   	<ul class="btn_shelves_operate clearfix">
   	   		<li onclick="openleft()"><a><div><img src="${STATIC_URL}/images/4.2/icon_left_nor.png"></div><p>左开</p></a></li>
	   	    <li onclick="openright()"><a><div><img src="${STATIC_URL}/images/4.2/icon_right_nor.png"></div><p>右开</p></a></li>
	   	    <li onclick="wind()"><a><div><img src="${STATIC_URL}/images/4.2/icon-ventilate-nor.png"></div><p>通风</p></a></li>
	   	    <li onclick="closeshelve()"><a><div><img src="${STATIC_URL}/images/4.2/icon-fold-nor.png"></div><p>合拢</p></a></li>
	   	    <li onclick="stop()"><a><div><img src="${STATIC_URL}/images/4.2/icon_stop_nor.png"></div><p>停止</p></a></li>   
	   	</ul>
	</div>
	<!--设备操控device_control模块结束-->
 </article>
</body>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</html>