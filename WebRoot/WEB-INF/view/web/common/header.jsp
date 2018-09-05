<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>智慧馆库系统</title>
</head>
<header>
<!--头部-->
  <div class="wrap">
		<!--logo-->
		<div class="logo">
			<a href="javascript:location.href=config.index2"><img src="${STATIC_URL}/images/4.2/ams/bayi_logo (3).png"></a>
			<a href="javascript:location.href=config.service"><img src="${STATIC_URL}/images/4.2/ams/bayi_logo (4).png"></a>
		</div>
		<!--logo-->
		
		<!--主导航-->
		<div class="nav">
			<ul>
				<li onclick="chang1()" id="index"><a href="javascript:location.href=config.index">首页</a></li>
				<li onclick="chang2()" id="znfx_dahx"><a href="javascript:location.href=config.znfx_dahx">智能分析</a></li>
				<li onclick="chang3()" id="zntj_datj"><a href="javascript:location.href=config.zntj_datj">智能统计</a></li>
				<li onclick="chang4()" id="smartlog1"><a href="javascript:location.href=config.smartlog1">智能日志</a></li>
				<li onclick="chang5()" id="ywcz_dacx"><a href="javascript:location.href=config.ywcz_dacx">业务操作</a></li>
				<li onclick="chang6()" id="deviceop1"><a href="javascript:location.href=config.deviceop1">设备操控</a></li>
			</ul>
		</div>
		<!--主导航-->
				
		<!--健康指数-->
		<div class="health">
			<div class="data_content">
				 <span>档案馆健康指数</span>
				 <span>96%</span>
				 <span>非常健康</span>
			</div>
		</div>
		<!--健康指数-->
  </div>
 <!--头部-->
 </header>
</html>