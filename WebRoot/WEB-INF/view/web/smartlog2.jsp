<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <html> -->
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入资源文件 -->
<style>
	#datj_right_bottom_page #door_yema .arc_b_prev{
		left:155px ! important;
		top:15px ! important;
	}
	#datj_right_bottom_page #door_yema .arc_b_next{
    left: 255px ! important;
    top: 15px ! important;
}
</style>
<%@ include file="/WEB-INF/view/web/common/resource.jsp"%>
<title>智能日志</title>


<script src="${STATIC_URL}/modules/web/js/smartlog2.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	
    	 $(".device_operate_icon li:nth-child(2)").click(function(){
    		 var src = "${STATIC_URL}/images/4.2/door-click.png";    //新图片地址
    		 $(this).find("img").attr("src",src);    //更换图片地址
	  });
	});
</script>
</head>
<body>
<!-- 引入头部文件 -->
	<%@ include file="/WEB-INF/view/web/common/header.jsp"%>
	<!--智能日志smartlogo_content模块开始-->
	<div class="smartlogo">
	    <!--智能日志左边导航栏smartlog_left_nav模块开始-->
		<div class="smartlog_left_nav">
		    <ul>
		    	<li class="actived"><a href="javascript:location.href=config.smartlog1">档案操作日志</a></li>
				<!--<li><a href="javascript:location.href=config.devicelog">设备操作日志</a></li> -->
		    	<li><a href="javascript:location.href=config.smartlog2">设备操作日志</a></li>
		    	<li><a href="javascript:location.href=config.alarmError">异常报警日志</a></li>
		    </ul>	
		 </div>
	   	<!--智能日志左边导航栏smartlog_left_nav模块结束-->
	   	<!-- 设备操作日志模块开始 -->
	   	<div class="device_operate_log">
	   	    <ul class="device_operate_icon">
	   	        <li><img src="${STATIC_URL}/images/4.2/compact-shelving-normal.png"></li>
	   	        <li><img src="${STATIC_URL}/images/4.2/door-normal.png"></li>
	   	        <li><img src="${STATIC_URL}/images/4.2/light-normal.png"></li>
	   	        <li><img src="${STATIC_URL}/images/4.2/environment-normal.png"></li>
	   	        <li><img src="${STATIC_URL}/images/4.2/monitoring-normal.png"></li>
	   	        <li><img src="${STATIC_URL}/images/4.2/research-normal.png"></li>
	   	        <li><img src="${STATIC_URL}/images/4.2/research-normal.png"></li>
	   	        <li><img src="${STATIC_URL}/images/4.2/research-normal.png"></li>
	   	        <li><img src="${STATIC_URL}/images/4.2/research-normal.png"></li>
	   	        <li><img src="${STATIC_URL}/images/4.2/research-normal.png"></li>
	   	        <li><img src="${STATIC_URL}/images/4.2/research-normal.png"></li>
	   	    </ul>
	   	    <ul class="device_operate_name">
	   	        <li>智能密集架</li>
	   	        <li>智能门禁</li>
	   	        <li>智能灯光</li>
	   	        <li>智能环境</li>
	   	        <li>智能安防</li>
	   	        <li>数字化档案</li>
	   	        <li>智能消防</li>
	   	        <li>公共广播</li>
	   	        <li>LED综合</li>
	   	        <li>FRID系统</li>
	   	        <li>异质备份</li>
	   	    </ul>
	   	    <ul class="operate_options">
	   	  	    <li>操作人员  <select id="userName"></select></li>
	   	   		<li>设备列表  <select id="device"></select></li>
	   	   	    <li>操作日期   <select id="time"></select></li>
	   	        <li>操作来源   <select><option>智慧馆库平台系统</option></select></li>
	   	    </ul>
	   	    <div class="device_operate_list">
	   	    </div>  
	   	</div>
	   	<!-- 设备操作日志模块结束 -->
	</div>
	<!--智能日志smartlogo_content模块结束-->
</body>
</html>
<script id="inout-data-list-tmpl" type="text/html">
	   	        <table class="device_log_results">
	   	        <thead>
	   	            <tr>
	   	                <td width="13%">操作类型</td>
	   	                <td width="20%">操作时间</td>
	   	                <td width="24%">档案名称</td>
	   	                <td width="16%">操作地点</td>
	   	                <td width="10%">操作人员</td>
	   	                <td width="*">备注</td>
	   	            </tr>
	   	        </thead>
	   	        <tbody class="inout_operate_lists">
    		 	<!--[for(i=0;i<10;i++){ ]-->
				<!--[if(i<list.length){]-->
						<tr>
	   	               	 <!--[if(list[i].type=='0'){]-->
	   	               	 <td>调档操作</td>
					<!--[}else if(list[i].type=='1'){]-->
 						 <td>还档操作</td>
					<!--[}else{]-->
						 <td>盘点操作</td>
					<!--[}]-->
	   	               	 <td><!--[=list[i].ctime]--></td>
	   	               	 <td><!--[=list[i].tittle]--></td>
	   	               	 <td><!--[=list[i].storeplace]--></td>
	   	                 <td><!--[=list[i].creator]--></td>
	   	               	 <td><!--[=list[i].remark]--></td>
	   	          	  </tr>
					<!--[}else{]-->
						<tr>
	   	               	 <td></td>
	   	               	 <td></td>
	   	               	 <td></td>
	   	               	 <td></td>
	   	                 <td></td>
	   	               	 <td></td>
	   	          	  </tr>
					<!--[}]-->
				<!--[}]-->
	   	        </tbody>		
	   	        <tfoot>
	   	            <tr>
	   	                <td colspan="6">
	   	                   <div id="datj_right_bottom_page">
			   	            <h4 id="door_yema">
       							<a href="#" id="firstpage" onclick="sy()"> 首页</a>
        						<span class="arc_b_prev" onclick="prePageAction()"><img src="${STATIC_URL}/images/4.2/previous-page.png"></span>
    						    <input type="text" id="nowpage">
      						    <span class="arc_b_next" onclick="nextPageAction()"><img src="${STATIC_URL}/images/4.2/next-page.png"></span>
    						    <a href="#" id="lastpage" onclick="wy()"> 尾页</a> 
       							<em id="totlePageCount"></em>
      						</h4>
						</div>		
	   	                </td>
	   	            </tr>
	   	        </tfoot>
                </table>
</script>