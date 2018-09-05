<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <html> -->
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入资源文件 -->
<%@ include file="/WEB-INF/view/web/common/resource.jsp"%>
<%-- <script src="${STATIC_URL}/modules/web/js/smartlog1.js" type="text/javascript"></script> --%>
<title>智能日志</title>
<style type="text/css">
table.archive_log_results{
	height:817px !important;
}
</style>
</head>
<body>
<!-- 引入头部文件 -->
	<%@ include file="/WEB-INF/view/web/common/header.jsp"%>
	
	<!--智能日志smartlogo_content模块开始-->
	<div class="smartlogo">
	   
	    <!--智能日志左边导航栏smartlog_left_nav模块开始-->
		<div class="smartlog_left_nav">
		    <ul>
		    	<li><a href="javascript:location.href=config.smartlog1">档案操作日志</a></li>
		    	<li><a href="javascript:location.href=config.smartlog2">设备操作日志</a></li>
		    	<li><a href="javascript:location.href=config.alarmError">异常报警日志</a></li>
		    	<!-- <li><a href="javascript:location.href=config.smartlogPending">待处理日志</a></li> -->
<!-- 		    	<li><a href="javascript:location.href=config.smartlog2">设备操作日志</a></li>
		    	<li><a href="#">异常报警日志</a></li> -->
		    </ul>	
		 </div>
	   	<!--智能日志左边导航栏smartlog_left_nav模块结束-->
	   	
	   	<!-- 档案操作日志模块开始 -->
	   	<div class="smartlog_info">
		          
	   	    <!-- 档案日志操作按钮operate_btn开始 -->
	   	    <div class="operate_btn">
	   	        <button class="archive_error"><span>档案异常</span></button>
	   	        <button class="devide_error"><span>设备异常</span></button>
	   	        <button class="environment_error"><span>环境异常</span></button>
	   	        <%-- <img id="dy" onclick='toexcel()' src="${STATIC_URL}/images/web/wsd_dc.png"/>  --%>
	   	    </div>
	   	    <!-- 档案日志操作按钮operate_btn结束 -->
	   	    
	   	    <!-- 档案操作日志模块开始 -->
	   	    
	   	    <table class="archive_log_results">
	   	    </table>
	   	    
	   	    
	   	</div>
	   	<!-- 档案操作日志模块结束 -->
	   		   	
	</div>
	<!--智能日志smartlogo_content模块结束-->
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</body>
</html>
<<script type="text/javascript">
var totalCount = 0;
var currentPage = 1;
var totalPage =1; 

function alarmError(i){
	Ajax.custom({
		url : config.selectAlarmArchives,
		data : {
			pageIndex : i
		},//传递参数
		type : 'GET'
	}, function(data) {
		if (data.data.list != null) {
			$(".archive_log_results").html(template.render("archive-data-list-tmpl", {
				list : data.data.list
			}));
			totalCount = data.data.count;
			if (totalCount % 9 == 0) {
				totalPage = totalCount / 9;
			} else {
				totalPage = parseInt(totalCount / 9) + 1;
			}
			$("#totlePageCount").html(
					"共计" + totalPage + "页&nbsp " + totalCount + "条数据");
			$("#nowpage").val(currentPage);
		}
	});
}

function deviceError(i){
	Ajax.custom({
		url : config.selectAlarmManageByDevice,
		data : {
			pageIndex : i
		},//传递参数
		type : 'GET'
	}, function(data) {
		if (data.data.list != null) {
			$(".archive_log_results").html(template.render("archive-data-list-tmpl_device", {
				list : data.data.list
			}));
			totalCount = data.data.count;
			if (totalCount % 9 == 0) {
				totalPage = totalCount / 9;
			} else {
				totalPage = parseInt(totalCount / 9) + 1;
			}
			$("#totlePageCount").html(
					"共计" + totalPage + "页&nbsp " + totalCount + "条数据");
			$("#nowpage").val(currentPage);
		}
	});
}

function environmentError(i){
	Ajax.custom({
		url : config.selectAlarmManageByEnvironment,
		data : {
			pageIndex : i
		},//传递参数
		type : 'GET'
	}, function(data) {
		if (data.data.list != null) {
			$(".archive_log_results").html(template.render("archive-data-list-tmpl_device", {
				list : data.data.list
			}));
			totalCount = data.data.count;
			if (totalCount % 9 == 0) {
				totalPage = totalCount / 9;
			} else {
				totalPage = parseInt(totalCount / 9) + 1;
			}
			$("#totlePageCount").html(
					"共计" + totalPage + "页&nbsp " + totalCount + "条数据");
			$("#nowpage").val(currentPage);
		}
	});
}

$(".archive_error").click(function(){
	alarmError(1);
})
$(".devide_error").click(function(){
	deviceError(1);
});
$(".environment_error").click(function(){
	environmentError(1);
});
</script>

<script id="archive-data-list-tmpl" type="text/html">
	   	        <thead>
	   	            <tr>
	   	                <td width="15%">编号</td>
	   	                <td width="28%">题名</td>
	   	                <td width="10%">档案类型</td>
	   	                <td width="25%">位置</td>
	   	                <td width="10%">状态</td>
	   	            </tr>
	   	        </thead>
	   	        <tbody class="inout_operate_lists">
    		 	<!--[for(i=0;i<10;i++){ ]-->
						<!--[if(i<list.length){]-->
						<tr>
					<td><!--[=list[i].archiveno]--></td>
	   	               	 <td><div><!--[=list[i].title]--></div></td>
	   	               	 <td><!--[=list[i].typeName]--></td>
	   	                 <td><!--[=list[i].storeplace]--></td>
					
	   	               	 <td><!--[=list[i].remark]--></td>
	   	               	 
	   	          	  </tr>
					<!--[}else{]-->
						<tr>
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
	   	                   <h4 id="door_yema">
       							<a href="#" id="firstpage" onclick="sy()"> 首页</a>
        						<span class="arc_b_prev" onclick="prePageAction()"><img src="${STATIC_URL}/images/4.2/previous-page.png"></span>
    						    <input type="text" id="nowpage">
      						    <span class="arc_b_next" onclick="nextPageAction()"><img src="${STATIC_URL}/images/4.2/next-page.png"></span>
    						   	<a href="#" id="lastpage" onclick="wy()"> 尾页</a> 
 								<a id="pageJump" onclick="enterJump()"> 跳转</a> 
       							<em id="totlePageCount"></em>
      						</h4>
	   	                </td>
	   	            </tr>
	   	        </tfoot>
</script>

<script id="archive-data-list-tmpl_device" type="text/html">
	   	        <thead>
	   	            <tr>
	   	                <td width="20%">设备名称</td>
	   	                <td width="10%">设备分类</td>
	   	                <td width="15%">所属库房</td>
	   	                <td width="15%">所属库区</td>
	   	                <td width="15%">报警日期</td>
	   	                <td width="10%">状态</td>
	   	            </tr>
	   	        </thead>
	   	        <tbody class="inout_operate_lists">
    		 	<!--[for(i=0;i<10;i++){ ]-->
						<!--[if(i<list.length){]-->
						<tr>
					<td><!--[=list[i].name]--></td>
	   	               	 <td><div><!--[=list[i].categoryName]--></div></td>
	   	               	 <td><!--[=list[i].storeName]--></td>
	   	                 <td><!--[=list[i].storeAreaName]--></td>
					
	   	               	 <td><!--[=list[i].ctime]--></td>

						<!--[if(list[i].status==0){]-->
	   	               	 <td>未处理</td>
						<!--[}else if(list[i].status==1){]-->
 						 <td>已处理</td>
						<!--[}]-->
	   	               	 
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
	   	                   <h4 id="door_yema">
       							<a href="#" id="firstpage" onclick="sy()"> 首页</a>
        						<span class="arc_b_prev" onclick="prePageAction()"><img src="${STATIC_URL}/images/4.2/previous-page.png"></span>
    						    <input type="text" id="nowpage">
      						    <span class="arc_b_next" onclick="nextPageAction()"><img src="${STATIC_URL}/images/4.2/next-page.png"></span>
    						   	<a href="#" id="lastpage" onclick="wy()"> 尾页</a> 
 								<a id="pageJump" onclick="enterJump()"> 跳转</a> 
       							<em id="totlePageCount"></em>
      						</h4>
	   	                </td>
	   	            </tr>
	   	        </tfoot>
</script>