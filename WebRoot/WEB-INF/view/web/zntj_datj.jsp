<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入资源文件 -->
<%@ include file="/WEB-INF/view/web/common/resource.jsp"%>
<title>智慧馆库系统</title>
</head>
<body>
	<!-- 引入头部文件 -->
	<%@ include file="/WEB-INF/view/web/common/header.jsp"%>
<!-------------------------------头部结束--------------------------------------->
<article>
  <div class="collection">
  <!--左边导航栏-->
	<div class="leftsidebar">
		<ul>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/holding" target="ifream_main">档案统计</a></li>
			<li id="check_echarts"><a href="#">调档统计</a></li>
			<li id="check_rfid"><a href="#">盘点统计</a></li>
		</ul>
	</div>
	<!--左边导航栏-->
    <div class="col_b"> 
	 <p>开始日期:  <input   class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM',onpicked:function(dp){console.log($('#ny').val());chengStartTime(dp.cal.getNewDateStr());},maxDate:'%y-{%M}'})" />
	&nbsp;&nbsp;&nbsp; 结束日期:  <input  class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM',onpicked:function(dp){console.log($('#ny').val());chengEndTime(dp.cal.getNewDateStr());},maxDate:'%y-{%M}'})" /></p>
	 
	 
      <span id="archivesType"><%-- <img  src="${STATIC_URL}/images/web/guan_b3_01.png"> --%></span> 
      <span id="security"><%-- <img  src="${STATIC_URL}/images/web/guan_b3_02.png"> --%></span> 
      <span id="keepyear"><%-- <img  src="${STATIC_URL}/images/web/guan_b3_03.png"> --%></span> 
      <span id="rfid_all"></span> 
      <span id="rfid_result"></span> 
    </div>
    <!--图表-->
	<!-- <div class="statistical_chart"> -->
		
		<div class="statistical_chart_rypd" id="padauser"></div>
		<div class="statistical_chart_pdjg" id="pdsata"></div>
		
		
		<div class="archives_chart_dafl" id="ddtj_dafl"></div>
		<div class="archives_chart_kffl" id="ddtj_kffl"></div>
	<!-- </div> -->
	<!--图表-->
     
  </div>
</article>
    <!-- 引入尾部文件 -->
	<%-- <%@ include file="/WEB-INF/view/web/common/footer.jsp"%> --%>
</body>    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</html>
<script src="${STATIC_URL}/modules/web/js/zntj_datj.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function () {
	$("#ddtj_dafl").hide();
	$("#ddtj_kffl").hide();
	$("#rfid_all").hide();
	$("#rfid_result").hide();
		$(".leftsidebar ul li:nth-child(1)").addClass("datanavclickstyle");
});
function change(obj) {
		var arr = $(".leftsidebar ul li").removeClass("datanavclickstyle");
		$(obj).addClass("datanavclickstyle");
	}
	
	$("#check_echarts").click(function(){
		$("#archivesType").hide();
		$("#security").hide();
		$("#keepyear").hide();
		$("#padauser").hide();
		$("#pdsata").hide();
		$("#rfid_all").hide();
		$("#rfid_result").hide();
		redata();
		ddtj_dafl();
		ddtj_kffl();
		$("#ddtj_dafl").show();
		$("#ddtj_kffl").show();
	});
	$("#check_rfid").click(function(){
		$("#archivesType").hide();
		$("#security").hide();
		$("#keepyear").hide();
		$("#padauser").hide();
		$("#pdsata").hide();
		$("#ddtj_dafl").hide();
		$("#ddtj_kffl").hide();
		
		$("#rfid_all").show();
		$("#rfid_result").show();
	});
</script>

