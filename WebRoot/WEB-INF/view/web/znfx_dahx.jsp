<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入资源文件 -->
<%@ include file="/WEB-INF/view/web/common/resource.jsp"%>
<script src="${STATIC_URL}/modules/web/js/znfx_dahx.js" type="text/javascript"></script>
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
			<li onclick="change(this);"><a href="${BASE_URL}/webJump/znfx_dahx">档案画像</a></li>
		</ul>
	</div>
	<!--左边导航栏-->
	<!-- 右边主体 -->
	<div class="datj_right">
		<div class="datj_right_top">
			<div class="datj_right_sarch">
		    	<input class="datj_right_sarch_text" type="text"/>
		    	<input class="datj_right_sarch_submit" type="submit" value=""/>
		    </div>
		    <div class="result_text">
    			<!-- <p>为您找到相关搜索结果<span>23</span>个,共计耗时<span>1.02</span>秒</p> -->
    		</div>
    		<div class="datj_right_top_table">
<%--     			<ul>
    				<li>
	    				<div class="data_main">
	    				<h5>《关于浙江<span>煤矿</span>铁路用地问题的复函》</h5>
	   						<p>档案编号: j02-121-121</p>
	   						<p>档案类型: 文书档案</p>
	   						<p>档案卷数: 4卷</p>
	   						<p>档案位置: 一库房一库区第4列R面第2层第2格</p>
	   						<p>档案简介: 关于浙江煤矿铁路问题的探讨结果</p>
	   					</div>
	   					<div class="commonFloat_znfx_dahx">
	   					<img class="icon_goup" alt="" title=""src="${STATIC_URL}/images/4.2/icon-goup.png"/>
	   					<p>43555</p>
    					
    					</div>
    				</li>
    				<li>
	    				<div class="data_main">
	    				<h5>《关于浙江<span>煤矿</span>铁路用地问题的复函》</h5>
	   						<p>档案编号: j02-121-121</p>
	   						<p>档案类型: 文书档案</p>
	   						<p>档案卷数: 4卷</p>
	   						<p>档案位置: 一库房一库区第4列R面第2层第2格</p>
	   						<p>档案简介: 关于浙江煤矿铁路问题的探讨结果</p>
	   					</div>
	   					<div class="commonFloat_znfx_dahx">
	   					<img class="icon-decline" alt="" title=""src="${STATIC_URL}/images/4.2/icon-decline.png"/>
	   					<p>43255</p>
    					
    					</div>
    				</li>
    				<li>
	    				<div class="data_main">
	    				<h5>《关于浙江<span>煤矿</span>铁路用地问题的复函》</h5>
	   						<p>档案编号: j02-121-121</p>
	   						<p>档案类型: 文书档案</p>
	   						<p>档案卷数: 4卷</p>
	   						<p>档案位置: 一库房一库区第4列R面第2层第2格</p>
	   						<p>档案简介: 关于浙江煤矿铁路问题的探讨结果</p>
	   					</div>
	   					<div class="commonFloat_znfx_dahx">
	   					<img class="icon_goup" alt="" title=""src="${STATIC_URL}/images/4.2/icon-goup.png"/>
	   					<p>43555</p>
    					
    					</div>
    				</li>
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
       							<em id="totlePageCount"></em>
      						</h4>
			</div>		--%>	
    		<div class="archives_particulars_single" style="width:400px;height:500px;display:none; position:absolute;bottom: 300px; 
   right: 450px;background:white;">
    			<p onclick="closePage()">关闭页面</p>
    		</div>
    <!--视频-->
			<div class="ChangeVedio" style="width:400px;height:500px;display:none; position:absolute;bottom: 300px; 
   right: 450px;background:white;">
				<a href="#">
					<!-- <div class="k-tip" style="display:block;" id="divPlugin">  -->
					<div class="video_content_da" id="divPlugin">
				</div>
				</a>

			</div>
	<!--视频-->				
	</div>
    <!-- 右边主体 -->
    
     
  </div>
</article>
    <!-- 引入尾部文件 -->
	<%-- <%@ include file="/WEB-INF/view/web/common/footer.jsp"%> --%>
</body>    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</html>

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
	   					<div class="commonFloat_znfx_dahx">
	   					<img class="icon_goup" alt="" title=""src="${STATIC_URL}/images/4.2/icon-goup.png"/>
	   						<p><!--[=list[i].modifier]--></p>
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
