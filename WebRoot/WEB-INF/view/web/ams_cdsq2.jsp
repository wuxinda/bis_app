<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<body>
	<!-- 引入资源文件 -->
<%@ include file="/WEB-INF/view/web/common/resource.jsp"%>
<!-- 引入头部文件 -->
	<%@ include file="/WEB-INF/view/web/common/header.jsp"%>
		<title></title>
	</head><article>
  <div class="collection_cdsq">
  <!--左边导航栏-->
	<div class="leftsidebar">
		<ul>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/ywcz_dacx">档案查询</a></li>
			<li onclick="change(this);" id="select_left"><a href="${BASE_URL}/webJump/ams_dasq">档案操作</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/pendingList">外部任务列表</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/pendingList2">内部任务列表</a></li>
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/img">大屏设置</a></li>
		</ul>
	</div>
	<!--左边导航栏-->
		<!--主体-->
		<div class="datamain_hd">
			<h2 class="datatitle">
			    <span  id="datatitle_left">借档操作</span>
			</h2>
			<!--内容-->
			
				<!--档案查询区域-->
				<form class="file_query">
					<ul class="select_store">
	   	   				 <li class="have_css">库房&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select class="store_select" id="store"></select></li>
	   	   				 <li class="have_css">库区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select class="store_select" id="stroreArea"></select></li>
	   	   				 <li>密集架&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select class="store_select" id="deviceManage"></select></li>
	    			</ul>
	    			<ul class="select_space">
	   	   				 <li class="have_css">密集架方向  <select class="store_select" id="storeLr">
	   	   				 	<option value="">选择方向</option>
	   	   				 	<option value="L">左面</option>
	   	   				 	<option value="R">右面</option>
	   	   				 </select></li>
	   	   				 <li class="have_css">密集架节数  <select class="store_select" id="storeSection">
	   	   				 	<option value="">选择节数</option>
	   	   				 	<option value="1">第1节</option>
	   	   				 	<option value="2">第2节</option>
	   	   				 	<option value="3">第3节</option>
	   	   				 	<option value="4">第4节</option>
	   	   				 	<option value="5">第5节</option>
	   	   				 	<option value="6">第6节</option>
	   	   				 	<option value="7">第7节</option>
	   	   				 	<option value="8">第8节</option>
	   	   				 </select></li>
	   	   				 <li>密集架层数  <select class="store_select" id="storeLayer">
	   	   				 	<option value="">选择层数</option>
	   	   				 	<option value="1">第1层</option>
	   	   				 	<option value="2">第2层</option>
	   	   				 	<option value="3">第3层</option>
	   	   				 	<option value="4">第4层</option>
	   	   				 	<option value="5">第5层</option>
	   	   				 	<option value="6">第6层</option>
	   	   				 </select></li>
	    			</ul>
					<span>档案查询</span>
					<input name="" type="text" placeholder="请输入档案号" class="input-box" />
   					 <img class="button" src="${STATIC_URL}/images/4.2/search-button-normal-little.png">
				</form>
				<!--档案查询区域-->
				
				<!--表格-->
				<table class="table_sbjc_archive_table_thead">
				<thead>
					<tr>
							<td width="50px">ID<i class="decline"></i></td>
							<td width="101px">档案编号<i class="decline"></i></td>
							<td width="110px">档案类型<i class="decline"></i></td>
							<td width="220px">档案名称<i class="decline"></i></td>
							<td width="255px">档案位置<i class="decline"></i></td>
							<td width="40px">密级<i class="decline"></i></td>
							<td width="66px">选择</th>
						</tr>
				</thead>
				</table>
			<div class="table_sbjc_archive_table_overflow">
				<table class="table_sbjc_archive_table">
						
				</table>
				</div>
				<!--表格-->
				</table>
				<form class="check_all">
					<label><input type="checkbox" name="transcoding" value="" id="checkall" />全选</label>
				</form>
				
				<!--档案详情-->
				<div class="camera_particulars" id="device_camera">
					<div class="datatitle">
			    			<span id="datatitle_botton">档案详情</span>
					</div>
				  	<div class="camera_police_particulars_police">
					  <!-- 	<div class="camera_police_left ">
					  		<p>档案名称：<i class="ranking">2016房产档案001-2016年档案</i></p>
						  	<p>保管年限：<i class="ranking">30年</i></p>
						  	<p>RFID号：<i class="ranking">33</i></p>
					  	</div>
				  	    <div class="camera_police_right">
					  		<p>现有数量：<i class="ranking">1</i></p>
						  	<p>总数量：<i class="ranking">4</i></p>
						  	<p>其他相关档案：<i class="ranking">33卷</i></p>
				  		</div> -->
				  	</div>
				</div>
				<!--档案详情-->
			<!--内容-->
		</div>
		<!--主体-->
			
		<!--右边已选档案等-->
	    <div class="sideright">
			<div class="datatitle"  id="datatitle_right">
			    <span id="datatitle_right_text">已选档案</span>
		    </div>
			      	<!--表格-->
			      	<table id="roll_qdsq_top">
						<tr>
							<td width="52px">Id<i class="decline"></i></td>
							<td width="142px">档案类型<i class="decline"></i></td>
							<td width="114px">档案编号<i class="decline"></i></td>
						</tr>
					</table>
			      	<div class="police_list_selected_overflow">
		    		<table class="police_list_selected">
				</table>
				</div>
				<!--表格-->
				<form>
			      		<input type="button" value="申请借档" class="timing_btn">
			      	</form>
	    </div>
	    <!--右边已选档案等-->
	    </div></div></div></div></div></div></article>
	</body>
<script src="${STATIC_URL}/modules/web/js/ams_cdsq2.js" type="text/javascript"></script>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</html>