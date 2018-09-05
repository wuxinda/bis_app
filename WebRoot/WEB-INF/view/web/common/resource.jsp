<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${STATIC_URL}/plugins/pagenav/css/simplePagination.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/plugins/zoom/css/imagebig.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/modules/web/css/style.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/plugins/jquery-jcDate/css/jcDate.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/plugins/simpleAlert/css/simpleAlert.css" type="text/css"/>

<link rel="stylesheet" href="${STATIC_URL}/plugins/jqchart/css/jquery.jqChart.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/plugins/jqchart/css/jquery.jqRangeSlider.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/plugins/jqchart/themes/smoothness/jquery-ui-1.10.4.css" type="text/css"/>
<!-- 首页样式 -->
<link rel="stylesheet" href="${STATIC_URL}/modules/web/4.2/css/index.css">


<script type="text/javascript" src="${STATIC_URL}/plugins/echarts/echarts.min.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery/1.11.2/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/zoom/js/content_zoom.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery-form/3.03/jquery.form.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/template/template.js"></script>
<script type="text/javascript" src="${STATIC_URL}/modules/web/js/common/global.js"></script>
<script type="text/javascript" src="${STATIC_URL}/modules/web/js/common/config.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/pagenav/js/pagenav.min.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/jquery-jcDate/js/jQuery-jcDate.js"></script>

<script type="text/javascript" src="${STATIC_URL}/plugins/jqchart/js/jquery.jqChart.min.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/jqchart/js/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/jqchart/js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/simpleAlert/js/simpleAlert.js"></script>

<script src="${STATIC_URL}/modules/web/js/common/style.js"></script>
<script src="${STATIC_URL}/modules/web/js/common/common.js"></script>
<script src="${STATIC_URL}/plugins/jquery.SuperSlide.2.1.1.js" ></script>
<!--[if lte IE 8]>
<script type="text/javascript" src="${STATIC_URL}/plugins/html5/html5.js"></script>
<![endif]-->

<table cellpadding="0" cellspacing="3" border="0"
				style="display: none">
				<tr>
					<td class="tt">IP地址</td>
					<td><input id="loginip" type="text" class="txt"
						value="192.168.1.210" /></td>
					<td class="tt">端口号</td>
					<td><input id="port" type="text" class="txt" value="80" /></td>
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
					<input id="transstream" style="display: none"
						class="vtop" type="hidden" />
				</tr>
				<select style="display: none" id="streamtype" class="sel">
					<option value="1">主码流</option>
					<option value="2">子码流</option>
					<option value="3">第三码流</option>
					<option value="4">转码码流</option>
				</select>
			</table>



