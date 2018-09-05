<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <html> -->
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入资源文件 -->
<%@ include file="/WEB-INF/view/web/common/resource.jsp"%>
<script src="${STATIC_URL}/modules/web/js/smartlog1.js" type="text/javascript"></script>
<title>智能日志</title>
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
            
            <div class="filter_name">
	   	        <p>筛选条件</p>
	   	    </div>
	   	    
	   	    <div class="operate_name">
	   	        <p>快捷操作</p> 
	   	    </div>
		          
               

	   	    <!-- 筛选条件filter_conditions开始 -->
	   	    <table class="filter_conditions">
	   	        <tbody>
	   	            <tr>
	   	                <td class="conditions_name"><span>档案名称</span></td>
	   	                <td colspan="2" clsass="col_span">
	   	                <div class="btn_search_botton">
	   	                <input class="archive_name">
	   	                <img class="btn_search" src="${STATIC_URL}/images/4.2/search-button-normal-little.png">
	   	                </div>
	   	                </td>
	   	                <td class="spacing"></td>
	   	                <td class="conditions_name">操作时间</td>
	   	                <td><input id="stnyr" class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',readOnly:true,onpicked:function(dp){console.log($('#nyr').val());showDayInfo(dp.cal.getNewDateStr());},maxDate:'%y-%M-%d'})" /></td>
	   	                <td><input id="ennyr" class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',readOnly:true,onpicked:function(dp){console.log($('#nyr').val());showDayInfo(dp.cal.getNewDateStr());},maxDate:'%y-%M-%d'})" /></td>
	   	                <td class="spacing"></td>
	   	                <td class="conditions_name">档案类型</td>
	   	                <td><select class="archive_type" id="archivesTypeselect" value=""></td>
	   	            </tr>
	   	            <tr>
	   	                <td class="conditions_name">档案位置</td>
	   	                <td><select class="archive_store_room" id="store"></select></td>
	   	                <td><select class="archive_store_area"  id="stroreArea"></td>
	   	                <td class="spacing"></td>
	   	                <td class="conditions_name">保管年限</td>
	   	                <td><input class="archive_years" ></td>
	   	                <td></td>
	   	                <td class="spacing"></td>
	   	                <td class="conditions_name">档案密级</td>
	   	                <td><select class="archive_rank" id="securityselect">
	   	                	<option value="">选择密级</option>
	   	                	<option value="0">普通</option>
	   	                	<option value="1">秘密</option>
	   	                	<option value="2">机密</option>
	   	                	<option value="3">绝密</option>
	   	                </select></td>
	   	            </tr>
	   	        </tbody>
	   	    </table>
	   	    <!-- 筛选条件filter_conditions结束 -->
	   	    
	   	    <!-- 档案日志操作按钮operate_btn开始 -->
	   	    <div class="operate_btn">
	   	        <button class="archive_borrow"><span>借档操作</span></button>
	   	        <button class="archive_remand"><span>归还操作</span></button>
	   	        <button class="archive_inventory"><span>盘点操作</span></button>
	   	        <img id="dy" onclick='toexcel()' src="${STATIC_URL}/images/web/wsd_dc.png"/> 
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
<script id="archive-data-list-tmpl" type="text/html">
	   	        <thead>
	   	            <tr>
	   	                <td width="13%">操作类型</td>
	   	                <td width="18%">操作时间</td>
	   	                <td width="20%">档案名称</td>
	   	                <td width="24%">操作地点</td>
	   	                <td width="10%">操作人员</td>
	   	                <td width="*">备注</td>
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
	   	               	 <td><div class='dot'><!--[=list[i].tittle]--></div></td>
	   	               	 <td><!--[=list[i].storeplace]--></td>
	   	                 <td><!--[=list[i].username]--></td>
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