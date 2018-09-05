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
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/holding" >档案统计</a></li>
		</ul>
	</div>
	<!--左边导航栏-->
    <div class="col_b"> 
	 <p>开始日期:  <input  id="ny" class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM',onpicked:function(dp){console.log($('#ny').val());},maxDate:'%y-{%M}'})" />
	 结束日期:  <input  id="ny" class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM',onpicked:function(dp){console.log($('#ny').val());},maxDate:'%y-{%M}'})" /></p>
	<div class="statistical_chart_archiveTypeCount" id="archiveTypeCount"></div>
	<div class="statistical_chart_storeTypeCount" id="storeTypeCount"></div>
    </div>
    <!--图表-->
	<!-- <div class="statistical_chart"> -->
		
		
		
	<!-- </div> -->
	<!--图表-->
     
  </div>
</article>
    <!-- 引入尾部文件 -->
	<%-- <%@ include file="/WEB-INF/view/web/common/footer.jsp"%> --%>
</body>    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</html>
<script src="${STATIC_URL}/modules/web/js/zntj_ddtj.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function () {
		$(".leftsidebar ul li:nth-child(2)").addClass("datanavclickstyle");
});
function change(obj) {
		var arr = $(".leftsidebar ul li").removeClass("datanavclickstyle");
		$(obj).addClass("datanavclickstyle");
	}
</script>

