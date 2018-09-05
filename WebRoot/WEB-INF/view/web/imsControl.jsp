<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入资源文件 -->
<link href="${STATIC_URL}/modules/web/css/mainstyle.css" rel="stylesheet" type="text/css"/>
<%@ include file="/WEB-INF/view/web/common/resource.jsp"%>
<script src="${STATIC_URL}/modules/web/js/mainstyle.js" type="text/javascript"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/HKWS/webVideoCtrl.js"></script>
<script type="text/javascript" src="${STATIC_URL}/modules/web/js/hkvideo.js"></script>


<style type="text/css">

article h2{
    text-align: center;
    background-size: contain;
    color: #6d6d6d;
    text-indent: 2em;
    font-size: 25px;
    letter-spacing: 2px;}
</style>

<title>智慧馆库系统</title>
</head>
<body>
	<!-- 引入头部文件 -->
	<%@ include file="/WEB-INF/view/web/common/header.jsp"%>
<!-------------------------------头部结束--------------------------------------->
<article style="height:887px;/* border:rgba(0, 0, 0, 0.12) solid 1px; */">
	<h2>设备控制</h2>
	<div class="wrap">
	
		<div class="a-top-gd" >
			<div  class="crumb" >
				<a class="hddd">
					<select id="store">
					<option value=-1>选择库房</option>
					</select>
				</a>
				<a class="hddd" style="width:130px;">
					<select id="storeArea" style="width:150px;">
					<option value=-1>选择库区</option>
					</select>
				</a>
				<a class="hddd" style="width:130px;">
					<select id="lie" style="width:160px;">
					<option value=-1>选择密集架</option>
					</select>
				</a>
			</div>
		</div>
		<div class="sp" style="margin-left: 100px;">
			<!-- <p id="lie" class="m-lie"></p> -->
			<div class="mjj-l">
				<%-- <div class="m-sp">
					<!-- <object classid="clsid:D5E14042-7BF6-4E24-8B01-2F453E8154D7" id="PlayViewOCX"  width="100%" height="100%" name="ocx" ></object>  -->
					<object style="z-index: 3;"
							classid="CLSID:CAFCF48D-8E34-4490-8154-026191D73924"
							codebase="${STATIC_URL}/plugins/hikvision/NetVideoActiveX23.cab#version=2,3,19,1"
							standby="Waiting..." id="HIKOBJECT0" width="100%" height="100%"
							name="HIKOBJECT0"></object>		
     					
                        <input type="hidden" id="size" value="0" /> <input type="hidden"
					id="jx_video_host_ip" value="192.168.1.210" /> <input
					type="hidden" id="jx_video_host_port" value="8000" /> <input
					type="hidden" id="jx_video_login_name" value="admin" /> <input
					type="hidden" id="jx_video_login_password" value="fxs12345" />
     			</div> --%>
				<!-- <h2>
					<a href="javascript:location.href=config.video"> 视频</a>
				</h2> -->
				<%-- <div class="back_carm" onclick="clickLoginindex()">
					<img src="${STATIC_URL}/images/web/back_shi.png">返回实时监控
				</div>
				<div class="back_carmo" onclick="openvideo()"style="position:absolute;top:-30px;right:42%;color:#FF3B3F;font-size:16px;cursor:pointer;">打开视频</div>
			
				<ul class="fp">
					<li onclick="changeWndNum(1);">一</li>
					<li onclick="changeWndNum(2);">四</li>
				</ul> --%>
				<div class="carm" id="divPlugin" style="height:450px;margin-top:100px;"></div>
				<table cellpadding="0" cellspacing="3" border="0" style="display: none">
					<tr>
						<td class="tt">IP地址</td>
						<td><input id="loginip" type="text" class="txt"
							value="115.231.60.194" /></td>
						<td class="tt">端口号</td>
						<td><input id="port" type="text" class="txt" value="8800" /></td>
					</tr>
					<tr>
						<td class="tt">用户名</td>
						<td><input id="username" type="text" class="txt" value="admin" /></td>
						<td class="tt">密码</td>
						<td><input id="password" type="password" class="txt"
							value="fxs12345" /></td>
					</tr>
					<tr>
						<td class="tt">设备端口</td>
						<td colspan="2"><input id="deviceport" type="text" class="txt"
							value="8000" />（可选参数）</td>
						<td>窗口分割数&nbsp; <select class="sel2"
							onchange="changeWndNum(this.value);">
								<option value="1">1x1</option>
								<option value="2" selected>2x2</option>
								<option value="3">3x3</option>
								<option value="4">4x4</option>
						</select>
						</td>
					</tr>
					<tr>
						<td class="tt">已登录设备</td>
						<td><select id="ip" class="sel" onchange="getChannelInfo();"></select>
						</td>
						<td class="tt">通道列表</td>
						<td><select id="channels" class="sel"></select></td>
						<input id="transstream" style="display: none" type="checkbox"
							class="vtop" type="hidden" />
					</tr>
					<select style="display: none" id="streamtype" class="sel">
						<option value="1">主码流</option>
						<option value="2">子码流</option>
						<option value="3">第三码流</option>
						<option value="4">转码码流</option>
					</select>
			
				</table>				
     		</div>
     		<div class="mjj-rt">
	     		<span id="titleTextId">请选择库房</span> 
	     		<div class="m-zt"> </div>
	 			<!--<a href="#">【在架<em>188</em>卷，下架<s>88</s>卷】</a>-->
 			</div>
 			<div class="mjj-rb" >
 				<div class="mjj-rb-l"> 
 					<p><span>.</span>该列操作</p>  
 					<ul>
 						<li id="zk" onclick="openleft($('#lie').val())"><span></span> 左开</li> 
						<li id="tz" onclick="stop($('#lie').val())"><span></span> 停止</li>  
 						<li id="yk" onclick="openright($('#lie').val())"><span></span>右开</li>
 					</ul>
 				</div>
 				<div class="mjj-rb-r"> 
 					<p><span>.</span>该区操作</p>  
				 	<ul>
				 		<li id="tf" onclick="wind($('#lie').val())"><span></span> 通风</li>
				  		<li  id="hl" onclick="closeshelve($('#lie').val())"><span></span> 合拢</li>
				  	</ul>
				</div>
			</div>
		</div>
	</div>
</article>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</body>
<script src="${STATIC_URL}/modules/web/js/imsControl/imsControl.js"></script>

</html>
