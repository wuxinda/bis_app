<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入资源文件 -->
<%@ include file="/WEB-INF/view/web/common/resource.jsp"%>
<script type="text/javascript"
	src="${STATIC_URL}/plugins/HKWS/webVideoCtrl.js"></script>
<script type="text/javascript"
	src="${STATIC_URL}/modules/web/js/common/hkvideo.js"></script>

<title>智慧馆库系统</title>
</head>
<body>
	<!-- 引入头部文件 -->
	<%@ include file="/WEB-INF/view/web/common/header.jsp"%>
	<article>
	<div class="video">
		<h2>
			<img alt="" title=""
				src="${STATIC_URL}/images/web/i_ship_act_icon.png">视频
		</h2>
		<div class="vid_lf">
			<h2>视频列表</h2>
			<div class="menu_list" id="videoList"></div>

		</div>
		<div class="vid_rt">
			<ul class="fp">
				<li onclick="changeWndNums(1);">一</li>
				<li onclick="changeWndNums(2);">四</li>
				<li onclick="changeWndNums(3);">九</li>
				<li onclick="changeWndNums(4);">十六</li>
			</ul>
			<div id="divPlugin" class="plugin"></div>
		</div>
		<div class="vid_lf" id="control" style="display:none">
			<h2>视频控制</h2>
			<div class="menu_list" id="control">
				<!-- 预览控制-->
				<p class="con_head cur">预览控制</p>
				<div class="menu_body">
					<table cellpadding="0" cellspacing="2" border="1">
						<tr>
							<td>码流类型:</td>
							<td><select id="streamtype" class="sel">
									<option value="1">主码流</option>
									<option value="2">子码流</option>
									<option value="3">第三码流</option>
									<option value="4">转码码流</option>
							</select></td>
						<tr>
							<td>开始预览:</td>
							<td><input type="button" class="btn" value="开始预览"
								onclick="clickStartRealPlay();" />
						</tr>
						<tr>
							<td>停止预览:</td>
							<td><input type="button" class="btn" value="停止预览"
								onclick="clickStopRealPlay();" /></td>
						</tr>
						<tr>
							<td>音量设置:</td>
							<td><input type="text" id="volume" class="txt" value="50"
								maxlength="3" />
						</tr>
						<tr>
							<td>音量范围:</td>
							<td>（范围：0~100）</td>
						</tr>
						<tr>
							<td>设置:</td>
							<td><input type="button" class="btn" value="设置"
								onclick="clickSetVolume();" /></td>
						</tr>
						<tr>
							<td>打开声音:</td>
							<td><input type="button" class="btn" value="打开声音"
								onclick="clickOpenSound();" /></td>
						</tr>
						<tr>
							<td>关闭声音:</td>
							<td><input type="button" class="btn" value="关闭声音"
								onclick="clickCloseSound();" /></td>
						</tr>
						<tr>
							<td>获取通道:</td>
							<td><select id="audiochannels" class="sel"></select></td>
						</tr>
						<tr>
							<td>开始对讲:</td>
							<td><input type="button" class="btn" value="开始对讲"
								onclick="clickStartVoiceTalk();" /></td>
						</tr>
						<tr>
							<td>停止对讲:</td>
							<td><input type="button" class="btn" value="停止对讲"
								onclick="clickStopVoiceTalk();" /></td>
						</tr>
						<tr>
							<td>抓图:</td>
							<td><input type="button" class="btn" value="抓图"
								onclick="clickCapturePic();" /></td>
						</tr>
						<tr>
							<td>开始录像:</td>
							<td><input type="button" class="btn" value="开始录像"
								onclick="clickStartRecord();" /></td>
						</tr>
						<tr>
							<td>停止录像:</td>
							<td><input type="button" class="btn" value="停止录像"
								onclick="clickStopRecord();" /></td>
						</tr>
						<tr>
							<td>电子放大:</td>
							<td><input type="button" class="btn2" value="启用电子放大"
								onclick="clickEnableEZoom();" /></td>
						</tr>
						<tr>
							<td>电子放大:</td>
							<td><input type="button" class="btn2" value="禁用电子放大"
								onclick="clickDisableEZoom();" /></td>
						</tr>
						<tr>
							<td>3D放大:</td>
							<td><input type="button" class="btn2" value="启用3D放大"
								onclick="clickEnable3DZoom();" /></td>
						</tr>
						<tr>
							<td>3D放大:</td>
							<td><input type="button" class="btn2" value="禁用3D放大"
								onclick="clickDisable3DZoom();" /></td>
						</tr>
						<tr>
							<td>全屏:</td>
							<td><input type="button" class="btn" value="全屏"
								onclick="clickFullScreen();" /></td>
						</tr>
					</table>
				</div>
				<!-- 回放控制-->
				<p class="con_head">回放控制</p>
				<div class="menu_body">
					<table width="100%" border="1">
						<tr>
							<td>开始时间:</td>
							<td><input id="starttime" type="text" class="txt"
								value="2013-12-10 00:00:00" /></td>
						</tr>
						<!-- <tr>
							<td>时间格式:</td>
							<td>2013-11-11</br>12:34:56</td>
						</tr> -->

						<tr>
							<td>结束时间:</td>

							<td><input id="endtime" type="text" class="txt"
								value="2013-12-11 23:59:59" /></td>
						</tr>
						<tr>
							<td>搜索:</td>

							<td><input type="button" class="btn" value="搜索"
								onclick="clickRecordSearch(0);" /></td>
						</tr>
						<tr>
							<td>选择列表:</td>
							<td colspan="2">
								<div class="searchdiv">
									<table id="searchlist" class="searchlist" cellpadding="0"
										cellspacing="0" border="0"></table>
								</div>
							</td>
						</tr>
						<tr>
							<td>开始回放:</td>
							<td><input type="button" class="btn2" value="开始回放"
								onclick="clickStartPlayback();" /></td>
						</tr>
						<tr>
							<td>停止回放:</td>
							<td><input type="button" class="btn2" value="停止回放"
								onclick="clickStopPlayback();" /></td>
						</tr>
						<tr>
							<td>倒放:</td>
							<td><input type="button" class="btn" value="倒放"
								onclick="clickReversePlayback();" /></td>
						</tr>
						<tr>
							<td>单帧:</td>
							<td><input type="button" class="btn" value="单帧"
								onclick="clickFrame();" /></td>
						</tr>
						<tr>
							<td>启用转码:</td>
							<td><input id="transstream" type="checkbox" class="vtop" />
							</td>
						</tr>
						<tr>
							<td>暂停:</td>
							<td><input type="button" class="btn" value="暂停"
								onclick="clickPause();" /></td>
						</tr>
						<tr>
							<td>恢复:</td>
							<td><input type="button" class="btn" value="恢复"
								onclick="clickResume();" /></td>
						</tr>
						<tr>
							<td>慢放:</td>
							<td><input type="button" class="btn" value="慢放"
								onclick="clickPlaySlow();" /></td>
						</tr>
						<tr>
							<td>快放:</td>
							<td><input type="button" class="btn" value="快放"
								onclick="clickPlayFast();" /></td>
						</tr>
						<tr>
							<td>抓图:</td>
							<td><input type="button" class="btn" value="抓图"
								onclick="clickCapturePic();" /></td>
						</tr>
						<tr>
							<td>开始剪辑:</td>
							<td><input type="button" class="btn2" value="开始剪辑"
								onclick="clickStartRecord();" /></td>
						</tr>
						<tr>
							<td>停止剪辑:</td>
							<td><input type="button" class="btn2" value="停止剪辑"
								onclick="clickStopRecord();" /></td>
						</tr>
						<tr>
							<td>OSD时间:</td>
							<td><input type="button" class="btn2" value="OSD时间"
								onclick="clickGetOSDTime();" /></td>
						</tr>
						<tr>
							<td>时间:</td>
							<td><input id="osdtime" type="text" class="txt" readonly /></td>
						</tr>
					</table>
				</div>
				<!-- 云台控制-->
				<p class="con_head">云台控制</p>
				<div class="menu_body">
					<table cellpadding="0" cellspacing="3" border="0" class="left">
						<tr>
							<td><input type="button" class="btn" value="左上"
								onmousedown="mouseDownPTZControl(5);"
								onmouseup="mouseUpPTZControl();" /></td>
							<td><input type="button" class="btn" value="上"
								onmousedown="mouseDownPTZControl(1);"
								onmouseup="mouseUpPTZControl();" /></td>
						</tr>
						<tr>
							<td><input type="button" class="btn" value="右上"
								onmousedown="mouseDownPTZControl(7);"
								onmouseup="mouseUpPTZControl();" /></td>
							<td><input type="button" class="btn" value="左"
								onmousedown="mouseDownPTZControl(3);"
								onmouseup="mouseUpPTZControl();" /></td>
						</tr>
						<tr>
							<td><input type="button" class="btn" value="自动"
								onclick="mouseDownPTZControl(9);" /></td>
							<td><input type="button" class="btn" value="右"
								onmousedown="mouseDownPTZControl(4);"
								onmouseup="mouseUpPTZControl();" /></td>
						</tr>
						<tr>
							<td><input type="button" class="btn" value="左下"
								onmousedown="mouseDownPTZControl(6);"
								onmouseup="mouseUpPTZControl();" /></td>
							<td> <input type="button"
								class="btn" value="下" onmousedown="mouseDownPTZControl(2);"
								onmouseup="mouseUpPTZControl();" /> </td>
						</tr>
						<tr>
							<td><input type="button"
								class="btn" value="右下" onmousedown="mouseDownPTZControl(8);"
								onmouseup="mouseUpPTZControl();" /></td>
						</tr>
					</table>
					<tbody cellpadding="0" cellspacing="3" border="0" class="left">
						<tr>

							<td>云台速度</td>

							<td><select id="ptzspeed" class="sel">
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option selected>4</option>
									<option>5</option>
									<option>6</option>
									<option>7</option>
							</select></td>
						</tr>
						<tr>

							<td>预置点号</td>

							<td><input id="preset" type="text" class="txt" value="1" /></td>
						</tr>
						<tr>
							<td colspan="2"><input type="button" class="btn" value="设置"
								onclick="clickSetPreset();" /> <input type="button" class="btn"
								value="调用" onclick="clickGoPreset();" /></td>
						</tr>
					</tbody>
					<tbody cellpadding="0" cellspacing="3" border="0" class="left">
						<tr>
							<td><input type="button" class="btn2" value="变倍+"
								onmousedown="PTZZoomIn()" onmouseup="PTZZoomStop()"></td>
							<td><input type="button" class="btn2" value="变倍-"
								onmousedown="PTZZoomout()" onmouseup="PTZZoomStop()"></td>
						</tr>
						<tr>
							<td><input type="button" class="btn2" value="变焦+"
								onmousedown="PTZFocusIn()" onmouseup="PTZFoucusStop()"></td>
							<td><input type="button" class="btn2" value="变焦-"
								onmousedown="PTZFoucusOut()" onmouseup="PTZFoucusStop()"></td>
						</tr>
						<tr>
							<td><input type="button" class="btn2" value="光圈+"
								onmousedown="PTZIrisIn()" onmouseup="PTZIrisStop()"></td>
							<td><input type="button" class="btn2" value="光圈-"
								onmousedown="PTZIrisOut()" onmouseup="PTZIrisStop()"></td>
						</tr>
					</tbody>
				</div>
				<!--  登录控制-->
				<p class="con_head">登录控制</p>
				<div class="menu_body">
					<tbody>
						<tr>
							<td>IP地址:</td>

							<td><input id="loginip" type="text" class="txt"
								value="115.231.60.194"></td>

							<td>端口号:</td>

							<td><input id="port" type="text" class="txt" value="8800"></td>
						</tr>

						<tr>
							<td>用户名:</td>

							<td><input id="username" type="text" class="txt"
								value="admin"></td>

							<td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>

							<td><input id="password" type="password" class="txt"
								value="fxs12345"></td>

						</tr>
						<tr>
							<td>设备端口（可选参数）:</td>

							<td colspan="2"><input id="deviceport" type="text"
								class="txt" value="8000"></td>

						</tr>
						<tr>
							<td colspan="4">
								<p>登录</p> <input type="button" class="btn" value="登录"
								onclick="clickLogin();">
								<p>退出</p> <input type="button" class="btn" value="退出"
								onclick="clickLogout();">
								<p>获取基本信息</p> <input type="button" class="btn2" value="获取基本信息"
								onclick="clickGetDeviceInfo();">
							</td>
						</tr>
						<tr>
							<td>已登录设备</td>

							<td><select id="ip" class="sel" onchange="getChannelInfo();"></select>
							</td>
							<td>通道列表</td>

							<td><select id="channels" class="sel"></select></td>
						</tr>
					</tbody>
				</div>
				<!--设备ip解析  -->
				<p class="con_head">设备IP解析</p>
				<div class="menu_body">
					<tbody cellpadding="0" cellspacing="3" border="0">
						<tr>
							<td>模式</td>
							<td colspan="3"><select id="devicemode" class="sel"
								onchange="changeIPMode(this.value);">
									<option value="1">IPServer</option>
									<option value="2">HiDDNS</option>
							</select></td>
						</tr>
						<tr>
							<td>服务器地址</td>
							<td><input id="serveraddress" type="text" class="txt"
								value="" /></td>
							<td>端口号</td>
							<td><input id="serverport" type="text" class="txt"
								value="7071" /></td>
						</tr>
						<tr>
							<td>设备标识</td>
							<td><input id="deviceid" type="text" class="txt" value="" /></td>
							<td>&nbsp;</td>
							<td><input type="button" class="btn" value="获取设备IP"
								onclick="clickGetDeviceIP();" /></td>
						</tr>
					</tbody>
				</div>
				<!-- 本地配置-->
				<p class="con_head">本地配置</p>
				<div class="menu_body">
					<tbody cellpadding="0" cellspacing="3" border="0">
						<tr>
							<td>播放性能</td>
							<td><select id="netsPreach" name="netsPreach" class="sel">
									<option value="0">最短延时</option>
									<option value="1">实时性好</option>
									<option value="2">均衡</option>
									<option value="3">流畅性好</option>
							</select></td>
							<td>图像尺寸</td>
							<td><select id="wndSize" name="wndSize" class="sel">
									<option value="0">充满</option>
									<option value="1">4:3</option>
									<option value="2">16:9</option>
							</select></td>
						</tr>
						<tr>
							<td>规则信息</td>
							<td><select id="rulesInfo" name="rulesInfo" class="sel">
									<option value="1">启用</option>
									<option value="0">禁用</option>
							</select></td>
							<td>抓图文件格式</td>
							<td><select id="captureFileFormat" name="captureFileFormat"
								class="sel">
									<option value="0">JPEG</option>
									<option value="1">BMP</option>
							</select></td>
						</tr>
						<tr>
							<td>录像文件打包大小</td>
							<td><select id="packSize" name="packSize" class="sel">
									<option value="0">256M</option>
									<option value="1">512M</option>
									<option value="2">1G</option>
							</select></td>
							<td>协议类型</td>
							<td><select id="protocolType" name="protocolType"
								class="sel">
									<option value="0">TCP</option>
									<option value="2">UDP</option>
							</select></td>
						</tr>
						<tr>
							<td>录像文件保存路径</td>
							<td colspan="3"><input id="recordPath" type="text"
								class="txt" />&nbsp;<input type="button" class="btn" value="浏览"
								onclick="clickOpenFileDlg('recordPath', 0);" /></td>
						</tr>
						<tr>
							<td>回放下载保存路径</td>
							<td colspan="3"><input id="downloadPath" type="text"
								class="txt" />&nbsp;<input type="button" class="btn" value="浏览"
								onclick="clickOpenFileDlg('downloadPath', 0);" /></td>
						</tr>
						<tr>
							<td>预览抓图保存路径</td>
							<td colspan="3"><input id="previewPicPath" type="text"
								class="txt" />&nbsp;<input type="button" class="btn" value="浏览"
								onclick="clickOpenFileDlg('previewPicPath', 0);" /></td>
						</tr>
						<tr>
							<td>回放抓图保存路径</td>
							<td colspan="3"><input id="playbackPicPath" type="text"
								class="txt" />&nbsp;<input type="button" class="btn" value="浏览"
								onclick="clickOpenFileDlg('playbackPicPath', 0);" /></td>
						</tr>
						<tr>
							<td>回放剪辑保存路径</td>
							<td colspan="3"><input id="playbackFilePath" type="text"
								class="txt" />&nbsp;<input type="button" class="btn" value="浏览"
								onclick="clickOpenFileDlg('playbackFilePath', 0);" /></td>
						</tr>
						<tr>
							<td colspan="4"><input type="button" class="btn" value="获取"
								onclick="clickGetLocalCfg();" />&nbsp;<input type="button"
								class="btn" value="设置" onclick="clickSetLocalCfg();" /></td>
						</tr>
					</tbody>
				</div>

				<!-- 系统维护-->
				<p class="con_head">系统维护</p>
				<div class="menu_body">
					<tbody width="100%" cellpadding="0" cellspacing="3" border="0">
						<tr>
							<td><input type="button" class="btn2" value="导出配置文件"
								onclick="clickExportDeviceConfig();" /> <input type="button"
								class="btn2" value="检查插件版本" onclick="clickCheckPluginVersion();" />
								<input type="button" class="btn2" value="远程配置库"
								onclick="clickRemoteConfig();" /> <input type="button"
								class="btn2" value="恢复默认参数" onclick="clickRestoreDefault();" />
							</td>
						</tr>
						<tr>
							<td><input id="configFile" type="text" class="txt" />&nbsp;<input
								type="button" class="btn" value="浏览"
								onclick="clickOpenFileDlg('configFile', 1);" />&nbsp;<input
								type="button" class="btn2" value="导入配置文件"
								onclick="clickImportDeviceConfig();" /></td>
						</tr>
						<tr>
							<td><input id="upgradeFile" type="text" class="txt" />&nbsp;<input
								type="button" class="btn" value="浏览"
								onclick="clickOpenFileDlg('upgradeFile', 1);" />&nbsp;<input
								type="button" class="btn2" value="升级"
								onclick="clickStartUpgrade();" /></td>
						</tr>
					</tbody>
				</div>

				<!--  回调信息-->
				<p class="con_head">回调信息</p>
				<div id="cbinfo" class="menu_body"></div>
				<!--  操作信息-->
				<p class="con_head">操作信息</p>
				<div id="opinfo" class="menu_body"></div>
			</div>
		</div>
	</div>
	</article>
</body>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</html>
<script src="${STATIC_URL}/modules/web/js/video.js"
	type="text/javascript"></script>
<script id="baseNum-data" type="text/html">
<!--[for(var i=0;i<baseData.store.length;i++){]-->
<p class="menu_head"><!--[=baseData.store[i].name]--> <span>

<!--[for(var x=0;x<baseData.count.length;x++){]-->
<!--[if(baseData.count[x].storeId==baseData.store[i].storeId){]-->
<!--[=baseData.count[x].count]-->
<!--[}]-->
<!--[}]-->

</span></p>
<div class="menu_body" >
<!--[for(var j=0;j<baseData.video.length;j++){]-->
<!--[if(baseData.video[j].storeId==baseData.store[i].storeId){]-->
<a onclick='startRealPlay(
<!--[for(var x=0;x<baseData.channel.length;x++){]-->
<!--[if(baseData.channel[x].deviceId==baseData.video[j].deviceId){]-->
<!--[=baseData.channel[x].threeId]-->
<!--[}]-->
<!--[}]-->
)'><!--[=baseData.video[j].name]--></a>
<!--[}]-->
<!--[}]-->
</div>
<!--[}]-->  
</script>