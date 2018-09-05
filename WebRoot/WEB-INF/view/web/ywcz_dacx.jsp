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
<script type="text/javascript"
	src="${STATIC_URL}/plugins/HKWS/webVideoCtrl.js"></script>
<script type="text/javascript"
	src="${STATIC_URL}/modules/web/js/common/hkvideo.js"></script>
<!-------------------------------头部结束--------------------------------------->
<div id="closeVideo" onclick="closeVideo()"></div>
<article>
  <div class="collection">
  <!--左边导航栏-->
	<div class="leftsidebar">
		<ul>
			<li onclick="change(this);" id="select_left"><a href="${BASE_URL}/webJump/ywcz_dacx">档案查询</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/ams_dasq">档案操作</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/pendingList">外部任务列表</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/pendingList2">内部任务列表</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/img">大屏设置</a></li>
		</ul>
	</div>
	<!--左边导航栏-->
	<!-- 右边主体 -->
	<div class="datj_right">
		<div class="datj_right_top">
			<div class="datj_right_sarch">
		    	<input class="datj_right_sarch_text" type="text" value="请输入档案名称/档案编号/档案类型" onclick="textClear();"/>
		    	<input class="datj_right_sarch_submit" type="submit" value=""/>
		    	<span>为您找到相关搜索结果</span><span id="resultCount">0</span><span>个，共计耗时<span id="resultTime">0</span>秒</span>
		    </div>
		    <div class="result_text">
    		</div>
    		<div class="datj_right_top_table">
    		</div>
    		<div class="archives_particulars_single" style="width:400px;height:500px;display:none; position:absolute;bottom: 300px; 
   right: 450px;background:white;" onclick="closePage()">
    			<p>关闭页面</p>
    		</div>
			<div class="ChangeVedio" id="divPlugin" style="width:600px;height:610px;display:none; position:absolute;bottom: 150px; 
   right: 450px;"></div>
	<!--视频-->				

</article>
</body>
</html>    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
<script src="${STATIC_URL}/modules/web/js/ywcz_dacx.js" type="text/javascript"></script>
<script type="text/javascript">
	function textClear(){
		$(".datj_right_sarch_text").val("");
	}
</script>
<script id="archives-data-list-tmpl" type="text/html">
    		<ul>
				<!--[for(i=0;i<list.length;i++){ ]-->
    				<li>
	    				<div class="data_main">
	    				<h5 class="data_main_title"><!--[=list[i].title]--></h5>
	   						<p class="data_main_p">档案编号:<!--[=list[i].archiveno]--></p>
	   						<p class="data_main_p">档案类型:<!--[=list[i].archivesTypeId]--></p>
	   						<p class="data_main_p">档案卷数:<!--[=list[i].totalNumber]--></p>
	   						<p class="data_main_p">档案位置:<!--[=list[i].storeplace]--></p>
	   						<p class="data_main_p">档案简介:<!--[=list[i].paratitle]--></p>
	   					</div>
	   					<div class="commonFloat">
	   					<img style="display:none;" class="particulars" onclick="particulars()" alt="" title=""src="${STATIC_URL}/images/4.2/particulars.png"/>
	   					<p style="display:none;">详情</p>
    					<img class="open" onclick="openvideo(<!--[=list[i].stroreId]-->,<!--[=list[i].stroreAreaId]-->,<!--[=list[i].storeColumn]-->,'<!--[=list[i].storeLr]-->')" alt="" title=""src="${STATIC_URL}/images/4.2/open.png"/>
    					<p id="p_open">开架</p>
    					</div>
    				</li>
				<!--[}]-->
    		</ul>
		</div>
		</div>
			<div id="datj_right_bottom_page">
			   	            <h4 id="door_yema">
       							<a href="#" id="firstpage" onclick="sy()"> 首页</a>
        						<span class="arc_b_prev" onclick="prePageAction()"><img src="${STATIC_URL}/images/4.2/previous-page.png"></span>
    						    <input type="text" id="nowpage">
      						    <span class="arc_b_next" onclick="nextPageAction()"><img src="${STATIC_URL}/images/4.2/next-page.png"></span>
    						    <a href="#" id="lastpage" onclick="wy()"> 尾页</a> 
								<a id="pageJump" onclick="enterJump()"> 跳转</a> 
       							<em id="totlePageCount"></em>
      						</h4>
		</div>		
</script>