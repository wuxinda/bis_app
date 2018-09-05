<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<%@ include file="/WEB-INF/view/web/4.0/common/resource.jsp"%>
		<title></title>
	</head>
	<body>
		<!--主体-->
		<div class="datamain">
			<div class="datatitle">
			    <span>盘点记录</span>
			</div>
			<!--内容-->
				<!--档案查询区域-->
				<form class="file_query">
					<span>盘点查询</span>
					<input name="" type="text" placeholder="请输入查询内容" class="input-box" />
   					 <input name="" type="submit" value="" class="button" />
				</form>
				<!--档案查询区域-->
				
				<!--表格-->
				<table class="table_sbjc inventory_query">
					<thead>
						<tr>
							<th>盘点时间<i class="decline"></i></th>
							<th>盘点总数<i class="decline"></i></th>
							<th>在架数量<i class="decline"></i></th>
							<th>错架数量<i class="decline"></i></th>
							<th>离架数量<i class="decline"></i></th>
							<th>视频</th>
						</tr>
					</thead>
					<tbody id="roll_qdsq">
					</tbody>
					<tfoot>
						<tr>
							<td>
								<form class="page_btn">
									<a href="#">首页</a>
									<button  type="button" class="left_btn"></button>&nbsp;
									<input type="text" name="text"  value="1"  class="figure">&nbsp;
								    <button  type="button" class="right_btn"></button>	
								    <a href="#">尾页</a>
								    <span>共计&nbsp;<i>40</i>&nbsp;页</span>
								    <span>&nbsp;<i>30</i>&nbsp;条数据</span>
								</form>
							</td>	
						</tr>
					</tfoot>
				</table>
				<form class="check_all">
					<label><input type="checkbox" name="transcoding" value="" />全选</label>
				</form>
				<!--表格-->
				
				<!--档案详情-->
				<div class="camera particulars" id="device_camera">
					<div class="datatitle">
			    			<span>档案详情</span>
					</div>
				  	<div class="camera_police particulars_police">
					  	<div class="camera_police_left ">
					  		<p>档案名称：<i class="ranking"></i></p>
						  	<p>档案编号：<i class="ranking"></i></p>
						  	<p>档案ID：<i class="ranking"></i></p>
					  	</div>
				  	    <div class="camera_police_right">
					  		<p>在架状态：<i class="ranking"></i></p>
						  	<p>存址：<i class="ranking"></i></p>
						  	<p>盘点时间：<i class="ranking"></i></p>
				  		</div>
				  	</div>
				</div>
				<!--档案详情-->
			<!--内容-->
		</div>
		<!--主体-->
			
		<!--右边已选档案等-->
	    <div class="sideright">
			<div class="datatitle">
			    <span>盘点详情</span>
		    </div>
		    <!--表格-->
		    <table class="police_list event_back_list">
				<thead>
					<tr>
						<th>盘点id<i class="decline"></i></th>
						<th>档案名称<i class="decline"></i></th>
						<th>在架状态<i class="decline"></i></th>
						<th>是否错架<i class="decline"></i></th>
						<th style="width:12.5%">盘点时间</th>
					</tr>
				</thead>
				<tbody id="amsactlog">
				s
				</tbody>
				<tfoot>
					<tr>
						<td>
							<form class="page_btn  police_page_btn">
								<a href="#">首页</a>
								<button type="button" class="left_btn"></button>
								&nbsp; <input type="text" name="text" value="1" class="figure">&nbsp;
								<button type="button" class="right_btn"></button>
								<a href="#">尾页</a> <span>共计&nbsp;<i>40</i>&nbsp;页
								</span> <span>&nbsp;<i>30</i>&nbsp;条数据
								</span>
							</form>
						</td>
					</tr>
				</tfoot>
			</table>
			<!--表格-->
			      	
				<!--视频-->
			    <div class="camera video_linkage">
			    	    <div class="datatitle">
				    		<span>视频联动</span>
			    		</div>
		    			<div class="video_player" id="divPlugin" ></div>
		        </div> 	
		        <!--视频-->
	    </div>
	    <!--右边已选档案等-->
	        <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
	</body>
<script type="text/javascript" src="${STATIC_URL}/modules/web/4.0/js/ams/ams_dapd.js"/></script>
<script type="text/javascript" src="${STATIC_URL}/modules/web/4.0/js/video/hkvideo.js"></script>
<script id="overview-detail" type="text/html">
				<!--[for(var i=0;i<list.one.length;i++){]-->
						<tr>
							<td><!--[=list.one[i].time]--></td>
							<td><!--[=list.one[i].a]--></td>
							<td><!--[=list.one[i].b]--></td>
							<td><!--[=list.one[i].c]--></td>
							<td><!--[=list.one[i].d]--></td>
							<td>
								<img src="../../images/operation/icon_select.png"  class="select"/>
							</td>
						</tr>
				<!--[}]-->
</script>
</html>
