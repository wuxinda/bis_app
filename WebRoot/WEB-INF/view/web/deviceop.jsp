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
<script type="text/javascript">
    $(document).ready(function(){
    	 var urlArray=new Array();
    	 
    	 $(".device_control_icon li:nth-child(2)").click(function(){
    		 var src = "${STATIC_URL}/images/4.2/door-click.png";    //新图片地址
    		 $(this).find("img").attr("src",src);    //更换图片地址
	  });

	});

</script>

</head>
<body>
<!-- 引入头部文件 -->
	<%@ include file="/WEB-INF/view/web/common/header.jsp"%>
	
	<!--设备操作device_control_access模块开始-->
	<div class="device_control_access">
	 		
	   	<ul class="device_control_icon clearfix">
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/compact-shelving-normal.png"></div><p>智能密集架</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/door-normal.png"></div><p>智能门禁</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/light-normal.png"></div><p>智能灯光</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/environment-normal.png"></div><p>智能环境</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/monitoring-normal.png"></div><p>智能安防</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>数字化档案</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>智能消防</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>公共广播</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>LED综合</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>FRID系统</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/research-normal.png"></div><p>异质备份</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/icon-circumstances-nor.png"></div><p>情景模式</p></a></li>
	   	</ul>
	   	 
	   	<ul class="access_options clearfix">
	   	    <li>库房  <input type="text" placeholder=" 一库房"></li>
	   	    <li>门禁  <input type="text" placeholder=" 门禁一"></li>
	    </ul>
	   	    
		<ul class="access_show">
	   	    <li><img src="${STATIC_URL}/images/4.2/left-door.png"></li>
  	   	    <li><img src="${STATIC_URL}/images/4.2/right-door.png"></li>
	   	</ul>
	   	
	   	<ul class="btn_access_operate clearfix">
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/icon_on.png"></div><p>开启</p></a></li>
	   	    <li><a href="#"><div><img src="${STATIC_URL}/images/4.2/icon_off.png"></div><p>关闭</p></a></li>
	   	</ul>

	</div>
	<!--设备操控device_control——access模块结束-->
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</body>
</html>