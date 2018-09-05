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
<div id="closeVideo" onclick="closeVideo()"></div>
<article>
  <div class="collection">
  <!--左边导航栏-->
	<div class="leftsidebar">
		<ul>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/ywcz_dacx">档案查询</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/ams_dasq">档案操作</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/pendingList">外部任务列表</a></li>
			<li onclick="change(this);" id="select_left"><a href="${BASE_URL}/webJump/pendingList2">内部任务列表</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/img">大屏设置</a></li>
		</ul>
	</div>
	<!--左边导航栏-->
	<!-- 右边主体 -->
		<!-- 档案操作日志模块开始 -->
	   	<div class="smartlog_info">
            
            <!-- <div class="filter_name">
	   	        <p>筛选条件</p>
	   	    </div> -->
	   	    
	   	    <!-- <div class="operate_name_pending">
	   	        <p>待处理列表</p> 
	   	    </div> -->
            
               

	   	    <!-- 筛选条件filter_conditions开始 -->
	   	    <table class="filter_conditions">
	   	        <tbody>
	   	            <tr>
	   	                <td class="conditions_name">档案位置</td>
	   	                <td><select class="archive_store_room" id="store"></select></td>
	   	                <td><select class="archive_store_area"  id="stroreArea"></td>
	   	                <td class="spacing"></td>
	   	                 <td class="conditions_name">操作类型</td>
	   	                <td><select class="archive_status" id="status">
	   	                <option value="0">待处理</option>
	   	                <option value="1">处理中</option>
	   	                <option value="2">已处理</option>	   	                
	   	                </select></td>
	   	                 <td class="spacing"></td>
	   	                <td class="conditions_name">操作时间</td>
	   	                <td><input id="stnyr" class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',readOnly:true,onpicked:function(dp){console.log($('#nyr').val());showDayInfo(dp.cal.getNewDateStr());},maxDate:'%y-%M-%d'})" /></td>
	   	                <td><input id="ennyr" class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',readOnly:true,onpicked:function(dp){console.log($('#nyr').val());showDayInfo(dp.cal.getNewDateStr());},maxDate:'%y-%M-%d'})" /></td>
	   	                <td class="spacing"></td>
	   	                
	   	            </tr>
	   	        </tbody>
	   	    </table>
	   	    <table class="filter_conditions2">
	   	     <tr> 
	   	        <td class="conditions_name"><span>档案名称</span></td>
	   	                <td colspan="2" clsass="col_span">
	   	                <div class="btn_search_botton">
	   	                <input class="archive_name">
	   	                <img class="btn_search" src="${STATIC_URL}/images/4.2/search-button-normal-little.png">
	   	                </div>
	   	                </td>
	   	            </tr>
	   	    </table>
	   	    </table>
	   	    <!-- 筛选条件filter_conditions结束 -->
	   	    
	   	    <!-- 档案日志操作按钮operate_btn开始 -->
	   	    <div class="operate_btn1">
	   	        <button class="archive_borrow"><span>借档列表</span></button>
	   	        <button class="archive_remand"><span>还档列表</span></button>
	   	        <img id="dy" onclick='toexcel()' style='left: 38px;' src="${STATIC_URL}/images/web/wsd_dc.png"/> 
	   	        <!-- <button class="archive_inventory"><span>盘点操作</span></button> -->
	   	    </div>
	   	    <!-- 档案日志操作按钮operate_btn结束 -->
	   	    
	   	    <!-- 档案操作日志模块开始 -->
	   	    
	   	    <table class="archive_log_results">
	   	    </table>
	   	    
	   	    
	   	</div>
	   	<!-- 档案操作日志模块结束 -->
		    	
		    
    		
    					

</article>
</body>    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</html>
<script src="${STATIC_URL}/modules/web/js/ywcz_pending2.js" type="text/javascript"></script>
<script id="archive-data-list-tmpl" type="text/html">
	   	        <thead>
	   	            <tr>
	   	                <td width="13%">操作类型</td>
	   	                <td width="20%">操作时间</td>
	   	                <td width="24%">档案名称</td>
	   	                <td width="16%">操作地点</td>
	   	                <td width="10%">操作人员</td>
	   	                <td width="*">操作</td>
	   	            </tr>
	   	        </thead>
	   	        <tbody class="inout_operate_lists">
    		 	<!--[for(i=0;i<10;i++){ ]-->
						<!--[if(i<list.length){]-->
						<tr>
					<!--[if(list[i].type==0){]-->
	   	               	 <td>调档操作</td>
					<!--[}else if(list[i].type==1){]-->
 						 <td>还档操作</td>
					<!--[}else{]-->
						 <td>盘点操作</td>
					<!--[}]-->
	   	               	 <td><!--[=list[i].startTime]--></td>
	   	               	 <td><!--[=list[i].title]--></td>
	   	               	 <td><!--[=list[i].storeplace]--></td>
	   	                 <td><!--[=list[i].applyUser]--></td>
	   	            <!--[if(list[i].status==0){]-->
	   	               	 <td><span onclick="jdqr(<!--[=list[i].archivesId]-->,<!--[=list[i].auditInId]-->,1)"><img src="${STATIC_URL}/images/4.2/ams/icon_select.png"/></span>&nbsp;&nbsp;&nbsp;&nbsp;<span onclick="jdsc(<!--[=list[i].archivesId]-->,<!--[=list[i].auditInId]-->,0)"><img src="${STATIC_URL}/images/4.2/ams/icon_close.png"/></span></td>
					<!--[}else if(list[i].status==1){]-->
 						 <td><span onclick="jdmnrfid(<!--[=list[i].archivesId]-->,<!--[=list[i].auditInId]-->,2)"><img src="${STATIC_URL}/images/4.2/ams/icon_select.png"/></span></td>
					<!--[}else{]-->
						 <td></td>
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
        						<span class="arc_b_pr" onclick="prePageAction()"><img src="${STATIC_URL}/images/4.2/previous-page.png"></span>
    						    <input type="text" id="nowpage">
      						    <span class="arc_b_ne" onclick="nextPageAction()"><img src="${STATIC_URL}/images/4.2/next-page.png"></span>
    						   	<a href="#" id="lastpage" onclick="wy()"> 尾页</a> 
 								<a id="pageJump" onclick="enterJump()"> 跳转</a> 
       							<em id="totlePageCount"></em>
      						</h4>
	   	                </td>
	   	            </tr>
	   	        </tfoot>
</script>