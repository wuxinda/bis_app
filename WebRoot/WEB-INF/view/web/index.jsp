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
	
<script src="${STATIC_URL}/plugins/echarts/echarts.min.js"></script>

<title>智慧馆库系统</title>
</head>
<body>
	<!-- 引入头部文件 -->
	<%@ include file="/WEB-INF/view/web/common/header.jsp"%>
	<article> <section class="gcl">
	  <!--  <div class="gclp"> -->
		<h2>
			<a href="javascript:location.href=config.zntj_datj">档案详情</a>
		</h2>
		<div id="holding">
			<%-- <img alt="" title="" src="${STATIC_URL}/images/web/i_gcl_bg.png"> --%>
		</div>
		<div class="gclp_text">
			<p id="frist_tatal">
			    <i></i>总馆藏量：&nbsp;&nbsp;&nbsp;&nbsp;<span id="tatal1" class="tatal">0</span>&nbsp;卷
			</p>
			<p>
			    总馆空置量：&nbsp;&nbsp;&nbsp;&nbsp;<span id="tatal2" class="tatal">0</span>&nbsp;
			</p>
			<p>
			    馆藏增长率：&nbsp;&nbsp;&nbsp;&nbsp;<span id="tatal3" class="tatal">100%</span>&nbsp;
			</p>
			<p>
			    档案利用率：&nbsp;&nbsp;&nbsp;&nbsp;<span id="tatal4" class="tatal">32%</span>&nbsp;
			</p>
			<p>
			    总借阅次数：&nbsp;&nbsp;&nbsp;&nbsp;<span id="tatal6" class="tatal">0</span>&nbsp;卷
			</p>
			<p>
			    总归还次数：&nbsp;&nbsp;&nbsp;&nbsp;<span id="tatal7" class="tatal">0</span>&nbsp;卷
			</p>
		</div>
	   <!--  </div> -->
	</section> <!-------------------------------馆藏量--------------------------------------->
	<section class="kf">
	<h2>
		<a href="javascript:location.href=config.layout"> 3D平面图</a>
	</h2>
	<div class="main_main_lb_3d" style="padding-top: 64px;padding-left: 20px;"><img style="width: 737px;" src="${STATIC_URL}/images/web/3dstorepic.png"/></div>
	<div id="humiture" class="wsdp" style="height:350px;display:none;">
	</div>
	</section> <!-------------------------------库房布局图--------------------------------------->
	<%-- <section class="nh">
	<h2>
		<a href="javascript:location.href=config.deviceop1">设备控制</a>
	</h2>
	<div>
		<img src="${STATIC_URL}/images/4.2/imscontrol1.gif"/>
	</div>
	</section> --%>
	
	<section class="pending">
	<h2>
		<a href="javascript:location.href=config.pendingList">待处理任务</a>
	</h2>
	<ul id="archive">
	</ul>
	</section>
	<!-------------------------------设备控制--------------------------------------->
	<section class="da">
	
	<h2>
		<a href="javascript:location.href=config.archive">安全监测</a>
	</h2>
	<div class="main_right_table">
						<table>
						<!-- border-bottom:1px solid red; -->
							<tr style="border-bottom:1px solid #00f9ff;">
								<td>设备类型</td><td>巡检状态</td><td>巡检结果</td>
							</tr>
							
							<tr>
								<td><img src="${STATIC_URL}/images/web/4.2/index/archives40.png"/></td>
								<td><img class="trueIcon" src="${STATIC_URL}/images/web/4.2/index/true.png"/></td>
								<td>巡检一切正常</td>
							</tr>
							<tr>
								<td><img src="${STATIC_URL}/images/web/4.2/index/environment38.png"/></td>
								<td><img class="trueIcon" src="${STATIC_URL}/images/web/4.2/index/true.png"/></td>
								<td class="humDateResult">巡检一切正常</td>
							</tr>
							<tr>
								<td><img src="${STATIC_URL}/images/web/4.2/index/device47.png" onclick="jump(7)"/></td>
								<td><img id="false" src="${STATIC_URL}/images/web/4.2/index/false.png"/></td>
								<td class="testred">发现两处异常</td>
							</tr>
						</table>
					</div>
	
	</ul>
	<!-- </section> --> <!-------------------------------档案出入库--------------------------------------->
	</section> <section class="sp">
	<h2>
		<a href="javascript:location.href=config.video"> 视频</a>
	</h2>
	<%-- <div class="back_carm" onclick="clickLoginindex()">
		<img src="${STATIC_URL}/images/web/back_shi.png">返回实时监控
	</div> --%>
	<!-- <div class="back_carmo" onclick="openvideo()">打开视频</div> -->

	<ul class="fp">
		<li onclick="changeWndNum(1);">一</li>
		<li onclick="changeWndNum(2);">四</li>
	</ul>
	<div class="carm" id="divPlugin"></div>
	</section> <!-------------------------------视频--------------------------------------->
	<section class="bj">
	<h2>
		<a href="javascript:location.href=config.alarm">环境数据</a>
	</h2>
	
	<div class="main_main_hj_chart_index">
						<div id="hj_chart" class="main_main_hj_chart_chart" style="height:250px;"></div>
						<div class="main_main_hj_left">
						<ul>
							<li><img class="float_left"
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_temperature.png"
								height="45" width="45" /><span>26℃</span><img
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_protection.png"
								height="25" width="25" /></li>
							<li><img class="float_left"
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_humidity.png"
								height="45" width="45" /><span>69%RH</span><img
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_risk.png"
								height="25" width="25" /></li>
							<li><img class="float_left"
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_tvoc.png"
								height="45" width="45" /><span>1.4mg/m3</span><img
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_protection.png"
								height="25" width="25" /></li>
							<li><img class="float_left"
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_bacteria.png"
								height="45" width="45" /><span>1.4mg/m3</span><img
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_protection.png"
								height="25" width="25" /></li>
						</ul>
					</div>
					<div class="main_main_hj_right">
						<ul>
							<li><img
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_protection.png"
								height="25" width="25" /><span>1.4mg/m3</span><img
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_formaldehyde.png"
								height="45" width="45" /></li>
							<li><img
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_protection.png"
								height="25" width="25" /><span>1.4mg/m3</span><img
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_pm.png"
								height="45" width="45" /></li>
							<li><img
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_protection.png"
								height="25" width="25" /><span>1.4mg/m3</span><img
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_eco.png"
								height="45" width="45" /></li>
							<li><img
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_protection.png"
								height="25" width="25" /><span>1.4mg/m3</span><img
								src="${STATIC_URL}/images/web/4.2/index/bottom_icon_noxious.png"
								height="45" width="45" /></li>
						</ul>
					</div>
	<!-- <ul id="alarm">
	</ul> -->
	</section><!-------------------------------报警--------------------------------------->
	</article>
</body>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</html>
<!-- 最新报警纪录 <div class="cir"></div> -->
<script id="alarm-data-list-tmpl" type="text/html">
		
    <!--[for(i=0;i<6;i++){]-->
      <!--[if(i==0){]-->
		<li class="zc" >
      <!--[}else{]-->
		<li>  
      <!--[}]-->
			<p>
				<!--[=list[i].day]--><br>
				<s><!--[=list[i].dayHms]--></s>
			</p> <span>&nbsp;&nbsp;&nbsp;</span>
			<p>
				<em><!--[=list[i].remark]--></em><!--[=list[i].deviceName]-->
			</p>
		</li>
    <!--[}]-->
</script>
<!-- 档案操作 -->
<!-- 回放小按钮 -->         
<script id="archive-data-list-tmpl" type="text/html">
		<!--[for(i=0;i<5;i++){ ]-->
        <!--[ if(i==0){ ]-->
            <li><span class="cr"><img class="jb2" title="" alt="" src="${STATIC_URL}/images/4.2/web/
            icon-remind.png
            "></span><p class="jb1">
		<!--[=AmsActLogList[i].day]-->
				<br><em class="jb1">
		<!--[=AmsActLogList[i].archivesName]-->
             </em></p> <img style="display:none" id="a<!--[=i]-->"/></a> <span class="new"></span></li>
        <!--[}else{]-->
            <li><span class="cr"><img title="" alt="" src="${STATIC_URL}/images/4.2/web/
           icon-remind.png
			"></span><p>
        <!--[=AmsActLogList[i].day]--> 
				<br><em>
	    <!--[=AmsActLogList[i].archivesName]-->
				</em></p> <img style="display:none" id="a<!--[=i]-->"/></a></li>
        <!--[}]-->
        <!--[}]-->
</script>
<!-- 门禁盘点-->
<script id="device_data-list-tmpl" type="text/html">
		    <tr>
				<td class="device_table_icon">
					<img src="${STATIC_URL}/images/4.2/web/icon-record.png"/><p>环境检测</p>
				</td>
				<td><img src="${STATIC_URL}/images/4.2/web/icon-correct.png"/></td>
				<td>巡检一切正常</td>
			</tr>
			<tr>
				<td class="device_table_icon">
				<img src="${STATIC_URL}/images/4.2/web/icon-environment.png"/><p>档案检测</p>
				</td>
				<td><img src="${STATIC_URL}/images/4.2/web/icon-correct.png"/></td>
				<td>巡检一切正常</td>
			</tr>
			<tr>
				<td class="device_table_icon">
				<img src="${STATIC_URL}/images/4.2/web/icon-facility.png"/><p>设备检测</p>
				</td>
				<td><img src="${STATIC_URL}/images/4.2/web/icon-seek.png"/></td>
				<td>发现两处异常</td>
			</tr>			
</script>
<script src="${STATIC_URL}/modules/web/js/index/index.js"
	type="text/javascript"></script>
	<script type="text/javascript"
	src="${STATIC_URL}/modules/web/4.2/js/index.js"></script>

