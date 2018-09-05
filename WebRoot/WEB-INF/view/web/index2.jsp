<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入资源文件 -->
<%@ include file="/WEB-INF/view/web/common/resource.jsp"%>
	
<script src="${STATIC_URL}/plugins/echarts/echarts.min.js"></script>
<style type="text/css">
section.gcl aside{ position:absolute; right:18px; top:18px; width:138px; height:26px; background:url(${STATIC_URL}/images/web/i_bj_icon.png); background-size:cover;}
section.gcl ul{ position:relative; margin:50px 0 0 20px;}
section.gcl ul .cir{ width:20px; height:30px; position:absolute; top:-30px; left:85px;background:url(${STATIC_URL}/images/web/i_bj_line.png);background-size:cover;}
section.gcl ul li{ width:280px; height:75px; background: url(${STATIC_URL}/images/4.2/i_bj_yc_bg.png) no-repeat  right;background-size:contain; overflow:hidden}
section.gcl ul li:hover{ cursor:pointer;}
section.gcl ul li:hover span{ transform: scale(1.1);-ms-transform: scale(1.1);-webkit-transform: scale(1.1);-moz-ansform: scale(1.1); 
transition: all 0.6s ease;-ms-transition: all 0.6s ease; -webkit-transition: all 0.6s ease; -moz-transition: all 0.6s ease;}
section.gcl ul li:hover p:last-child{ transform:translateX(-8px);-moz-transform:translateX(-8px);-ms-transform:translateX(-8px);-webkit-transform:translateX(-8px);transition: all 0.6s ease;-moz-transition: all 0.6s ease;-ms-transition: all 0.6s ease;-webkit-transition: all 0.6s ease;}

section.gcl ul li.zc{ background: url(${STATIC_URL}/images/4.2/i_bj_zc_bg.png) no-repeat  right;background-size:contain;}
section.gcl ul li.zc p:first-child,section.bj ul li.zc p s{ color:#ff7d68;}
section.gcl ul li.zc p,section.bj ul li.zc p em{ color:#fff1ef;}

section.gcl ul li p{font-size:13px; color:#999; float:left; display:block; margin-top:18px; line-height:20px;}
section.gcl ul li p:first-child{ text-align:right;}

section.gcl ul li p s{ text-decoration:none; font-size:16px;  color:#aaa; font-family:"Microsoft Yahei";}
section.gcl ul li p em{ font-size:16px;  color:#fff; overflow:hidden;width:146px;display:block;height:20px;}
section.gcl ul li span{ display:block; width:30px; height:30px;float:left;margin:24px 16px 0 12px; position:relative; text-align:center;}
section.gcl ul li span em{ position:absolute; line-height:30px; left:7px; font-size:16px; font-weight:bold; color:#FFFFFF;}
section.da .da_text { height: 60px;}
section.da .da_text p {line-height: 60px;    margin-left: 70px;    font-size: 15px;}
section.da .da_text p i {top: -18px;}
section.da .da_new_text p { height: 35px;    font-size: 15px;    line-height: 35px; margin-left: 16px;}
section.da .da_new_text i {    top: 105px;}
section.da .da_new_text .da_new_text_tap {  height: 70px;   line-height: 66px;   margin-left: 33px;}
section.da .da_new_text {  height: 70px;  background-size: 100% 100%;}
section.da .da_new_rfid{width: 92%;height:115px;margin-top:8px;margin-left:17px;background:url(${STATIC_URL}/images/4.2/web/copy-writer-bg.png);    border: 2px solid #00b3ff;}
section.da .da_new_rfid p{width: 235px; height: 18px;font-family: MicrosoftYaHei;font-size: 18px;color: #ffffff;margin:auto;line-height: 115px;margin-left:100px;}
section.da .da_new_rfid p i{margin-top: 94px;display: inline-block;width: 20px;height: 20px;background:url(${STATIC_URL}/images/4.2/web/icon-remind.png)no-repeat;position:absolute;top:12px;left:90px;}
section.da .da_new_rfid { height: 60px;}
section.da .da_new_rfid p {line-height: 60px;margin-left: 70px;font-size: 15px;}
section.da .da_new_rfid p i {top: 54px;left: 41px}
section.audit{ width:327px; height:194px;margin-top:0;}
section.audit h2 a{background-size:contain;}
section.audit:hover h2 a{background-size:contain;}
section.audit{ width:332px; height:543px;margin-top:-242px;background:url(${STATIC_URL}/images/4.2/body-right-bottom-border.png);}
section.audit ul{width:90%;margin:45px auto; }
section.audit li{ height:64px; width:100%; /* border-bottom:1px solid #d6d6d6; */ position:relative; cursor:pointer;background:url(${STATIC_URL}/images/4.2/left_bottom45.png)no-repeat;background-size: 100% 100%; }
section.audit li:hover{ background:url(${STATIC_URL}/images/4.2/web/Rectangle4Copy.png)no-repeat;background-size:100% 100%;}
section.audit li:hover p{ color:#ffffff;}
section.audit li:hover p em{ color:#ffffff;}
section.audit li .cr{ width:64px; height:52px;float:left; margin-top:8px; border-right:2px solid #00b3ff;}
section.audit li .cr img{ margin:14px 0 0 24px; display:block;}
section.audit li p{ width:190px; height:45px;float:left; margin-top:13px;margin-left:15px;  font-size:13px; /* color:#878787; */color:#ffffff; line-height:22px; overflow:hidden;}
section.audit li p em{ text-decoration:none; font-size:16px;/* color:#636363; */color:#ffffff;}
section.audit li a{ display:block; width:28px; height:28px; float:left;background: url(${STATIC_URL}/images/web/i_danga_huif_icon.png) no-repeat; margin-top:22px;margin-left:16px;background-size:contain;}
section.audit li a:hover{ background: url(${STATIC_URL}/images/web/i_danga_huif_act_icon.png) no-repeat; background-size:contain;}
section.audit li .new{ position:absolute; right:0; width:32px; height:32px; background:url(${STATIC_URL}/images/web/i_danga_new_icon.png);background-size:contain;}
section.gcl{ width:330px; height:632px;background:url(${STATIC_URL}/images/4.2/left.png) no-repeat;background-size:contain;}
.datj_right_sarch { width: 586px;height: 52px;float: left;display: block;position: relative;    margin-top: 56px;  margin-left: 99px;}
.datj_right_sarch .datj_right_sarch_text { width: 570px; height: 29px; border-radius: 5px; border: 1px solid #1ec9fc;  padding-left: 2px; color: #fff;}
.datj_right_sarch .datj_right_sarch_submit, .datj_right_sarch .datj_right_sarch_submit:hover {background: url(${STATIC_URL}/images/4.2/search-button-normal-little.png)no-repeat;position: absolute;width: 60px;height: 30px;border: none;top: 0px;right: 13px;border-radius: 5px;cursor: pointer;}
#searchArchive{
    float: left;
    list-style: none;
    color: #fff;
    margin-left: 78px;
    width: 652px;
    font-size: 14px;
}
#searchArchive li {
    background: url(${STATIC_URL}/images/4.2/border.png) no-repeat 0px 110px;
    width: 652px;
    height: 125px;
}
.data_main_right {
    float: left;
        width: 300px;
        text-indent:10px
}
.data_main_left{
    float: left;
        width: 300px;
        text-indent:10px
}
.commonFloat {
    float: left;
    width: 36px;
    height: 20px;
}
.open {
    width: 22px;
    height: 22px;
    top: 0;
    left: 0;
}
#p_open {
    font-size: 13px;
    left: 0;
    top: 0;
}
.commonFloat p {
    left: 5px;
    color: rgba(30, 201, 252, 0.5);
}
.data_main_title{
    font-size: 110%;
    height: 65px;
    line-height: 65px;
}
#datj_right_bottom_page {
    float: left;
    height: 51px;
    width: 519px;
    position: relative;
    left: 165px;
    top: 17px;
}
#datj_right_bottom_page #door_yema .arc_b_prev {
    position: absolute;
    left: 39px;
    top: 4px;
}
#datj_right_bottom_page #door_yema .arc_b_next {
    position: absolute;
    left: 137px;
    top: 4px;
}
ul.borrow2back{
    width: 75%;
    height: 60%;
    margin: 8% auto; 
    position: relative;
}

ul.borrow2back li:nth-child(1){
    float: left;
}

ul.borrow2back li:nth-child(2){
    float: right;
}

ul.borrow2back li div{
    position: relative;
    width: calc((402px * 6 / 10));
    height: calc((402px * 6 / 10));
    
}

ul.borrow2back li div img:nth-child(1) {
  width: calc((394px * 6 / 10));
  height: calc((394px * 6 / 10));
  position: absolute;
  left: 3px;
  
}

ul.borrow2back li div img:nth-child(2) {
  width: calc((314px * 6 / 10));
  height: calc((314px * 6 / 10));
  position: absolute;
  
  top: calc(43px * 6 / 10);
    left: calc(38px * 6 / 10);  
}

ul.borrow2back li div img:nth-child(3) {
  width: calc((402px * 6 / 10));
  height: calc((402px * 6 / 10));
  position: absolute;
  
}

ul.borrow2back li div img:nth-child(4) {
  width: calc((258px * 6 / 10));
  height: calc((258px * 6 / 10));
  position: absolute;
  top: calc(73px * 6 / 10); 
    left: calc(67px * 6 / 10);  
}

ul.borrow2back li div img:nth-child(5) {
  width: calc((193px * 6 / 10));
  height: calc((193px * 6 / 10));
  position: absolute;
  top: calc(103px * 6 / 10);
    left: calc(99px * 6 / 10);  
}
ul.borrow2back li div img:nth-child(6) {
  width: calc((100px * 6 / 10));
  height: calc((80px * 6 / 10));
  position: absolute;
  top: calc(153px * 6 / 10);
    left: calc(146px * 6 / 10);  
}

ul.borrow2back li a i{
    display: inline-block;
    width: 100%;
    text-align: center;
    color: #1EC9FC;
    font-size: 20px;
    text-shadow: 0px -2px 10px #1EC9FC;
    margin-top: 25px;
}
.archive_inventory{
    width: 140px;
    height: 100%;
    opacity: 0.8;
    font-size: 18px;
    color: #fff;
    border-radius: 5px;
    border: none;
    display: inline-block;
    background: url(${STATIC_URL}/images/4.2/button-normal-2.png) no-repeat;
    background-size: 100% 100%;
    float: left;
    margin-left: 262px;
    margin-top: 12px;
}
.archive_inventory:hover{
	background: url(${STATIC_URL}/images/4.2/button-click-2.png) no-repeat;
	background-size: 100% 100%;
}
</style>
<title>智慧馆库系统</title>
</head>
<body>
	<!-- 引入头部文件 -->
	<%@ include file="/WEB-INF/view/web/common/header.jsp"%>
	<article> <section class="gcl">
	  <!--  <div class="gclp"> -->
		<h2>
			<a href="javascript:location.href=config.zntj_datj">温湿度</a>
		</h2>
			<div id="humiture" class="wsdp" style="height:300px;">
	</div>
	<h2>
		<a href="javascript:location.href=config.alarm"> 报警</a>
	</h2>
	<ul id="alarm">
	</ul>
	</section> <!-------------------------------馆藏量--------------------------------------->
	<section class="kf">
	<h2>
		<a href="javascript:location.href=config.hump">内部调档</a>
	</h2>
  <ul class="borrow2back">
    <li ><a href="${BASE_URL}/webJump/ams_cdsq2">
        <div class="inside_archive_btn_click">
          <img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_1_bg.png" />
          <img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_2_bg.png" />
          <img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_3_bg.png" />
          <img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_5_bg.png" />
          <img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_4_bg.png" />
          <img src="${STATIC_URL}/images/4.2/ams/icon_export.png" />
        </div> <i>我要借档</i>
    </a></li>
    <li><a href="${BASE_URL}/webJump/ams_cdsq">
        <div class="inside_archive_btn_click">
          <img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_1_bg.png" />
          <img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_2_bg.png" />
          <img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_3_bg.png" />
          <img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_5_bg.png" />
          <img src="${STATIC_URL}/images/4.2/ams/archive_btn_click_4_bg.png" />
          <img src="${STATIC_URL}/images/4.2/ams/icon_channel.png" />
        </div> <i>我要还档</i>
    </a></li>
  </ul>
	</section> <!-------------------------------库房布局图--------------------------------------->
	<section class="pending">
	<h2>
		<a href="javascript:location.href=config.pendingList">外部待处理</a>
	</h2>
	<ul id="archive">
	</ul>
	</section>
	<!-------------------------------设备控制--------------------------------------->
	<section class="da">
	<h2>
		<a href="javascript:location.href=config.archive">出入库盘点</a>
	</h2>
	<div class="da_text">
		<p><i></i>当日出入库任务条数：<span id="count_inout_today">0</span>条</p>
	</div>
		<div class="da_new_rfid">
		<p><i></i>当日盘点错架条数：<span id="count_inty_today">0</span>条</p>
	</div>
	<div class="da_new_text" style="margin-top: 10px;">
		<p class="da_new_text_tap">已处理</p>
		<p>借档：<span id="count_inout_archives">0</span>份档案</p><i></i>
		<p>申请人员：<span id="inout_name">无</span></p>
	</div>

	</ul>
	</section><!-------------------------------档案出入库--------------------------------------->
	<section class="sp">
	<h2>
		<a href="javascript:location.href=config.ywcz_dacx"> 档案详情</a>
	</h2>
		<div class="datj_right_sarch">
		   <input class="datj_right_sarch_text" type="text"/>
		   <input class="datj_right_sarch_submit" type="submit" value=""/>
		</div>
		<ul id="searchArchive">
			
		</ul>
	</section> <!-------------------------------视频--------------------------------------->
	<section class="audit">
	<h2>
		<a href="javascript:location.href=config.alarm">内部待处理</a>
	</h2>
	<ul id="auditIn">
	</ul>
	</section><!-------------------------------报警--------------------------------------->
	</article>
</body>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</html>
<!-- 最新报警纪录 <div class="cir"></div> -->
<script id="alarm-data-list-tmpl" type="text/html">
		
    <!--[for(i=0;i<3;i++){]-->
      <!--[if(i==0){]-->
		<li class="zc" >
      <!--[}else{]-->
		<li>  
      <!--[}]-->
			<p>
				<!--[=list[i].day]--><br>
				<s><!--[=list[i].dayHms]--></s>
			</p> <span>&nbsp;&nbsp;&nbsp;</span>
			<p>
				<em><!--[=list[i].remark]--></em><!--[=list[i].deviceName]-->
			</p>
		</li>
    <!--[}]-->
</script>
<!-- 档案操作 -->        
<script id="archive-data-list-tmpl" type="text/html">
		<!--[for(i=0;i<AmsActLogList.length;i++){ ]-->
        <!--[ if(i==0){ ]-->
            <li><span class="cr"><img class="jb2" title="" alt="" src="${STATIC_URL}/images/4.2/web/
            icon-remind.png
            "></span><p class="jb1">
		<!--[=AmsActLogList[i].day]-->
				<br><em class="jb1">
		<!--[=AmsActLogList[i].archivesName]-->
             </em></p> <img style="display:none" id="a<!--[=i]-->"/></a> <span class="new"></span></li>
        <!--[}else if(i<5&&i!=0){]-->
            <li><span class="cr"><img title="" alt="" src="${STATIC_URL}/images/4.2/web/
           icon-remind.png
			"></span><p>
        <!--[=AmsActLogList[i].day]--> 
				<br><em>
	    <!--[=AmsActLogList[i].archivesName]-->
				</em></p> <img style="display:none" id="a<!--[=i]-->"/></a></li>
        <!--[}]-->
        <!--[}]-->
</script>
<!-- 回放小按钮 --> 
<script id="auditIn-data-list-tmpl" type="text/html">
		<!--[for(i=0;i<AmsActLogList.length;i++){ ]-->
        <!--[ if(i==0){ ]-->
            <li><span class="cr"><img class="jb2" title="" alt="" src="${STATIC_URL}/images/4.2/web/
            icon-remind.png
            "></span><p class="jb1">
		<!--[=AmsActLogList[i].day]-->
				<br><em class="jb1">
		<!--[=AmsActLogList[i].title]-->
             </em></p> <img style="display:none" id="a<!--[=i]-->"/></a> <span class="new"></span></li>
        <!--[}else if(i<7&&i!=0){]-->
            <li><span class="cr"><img title="" alt="" src="${STATIC_URL}/images/4.2/web/
           icon-remind.png
			"></span><p>
        <!--[=AmsActLogList[i].day]--> 
				<br><em>
	    <!--[=AmsActLogList[i].title]-->
				</em></p> <img style="display:none" id="a<!--[=i]-->"/></a></li>
        <!--[}else{]-->
		<!--[}]-->
        <!--[}]-->
</script>
<!-- 门禁盘点-->
<script id="device_data-list-tmpl" type="text/html">
		    <tr>
				<td class="device_table_icon">
					<img src="${STATIC_URL}/images/4.2/web/icon-record.png"/><p>环境检测</p>
				</td>
				<td><img src="${STATIC_URL}/images/4.2/web/icon-correct.png"/></td>
				<td>巡检一切正常</td>
			</tr>
			<tr>
				<td class="device_table_icon">
				<img src="${STATIC_URL}/images/4.2/web/icon-environment.png"/><p>档案检测</p>
				</td>
				<td><img src="${STATIC_URL}/images/4.2/web/icon-correct.png"/></td>
				<td>巡检一切正常</td>
			</tr>
			<tr>
				<td class="device_table_icon">
				<img src="${STATIC_URL}/images/4.2/web/icon-facility.png"/><p>设备检测</p>
				</td>
				<td><img src="${STATIC_URL}/images/4.2/web/icon-seek.png"/></td>
				<td>发现两处异常</td>
			</tr>
</script>
<script id="archives-data-list-tmpl" type="text/html">
    		<ul>
				<!--[for(i=0;i<list.length;i++){ ]-->
    				<li>
	    				<h5 class="data_main_title"><!--[=list[i].title]--></h5>
	    				<div class="data_main_left">
	   						<p class="data_main_p">档案编号:<!--[=list[i].archiveno]--></p>
	   						<p class="data_main_p">档案类型:<!--[=list[i].archivesTypeId]--></p>
	   					</div>
						<div class="data_main_right">
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
			<!--[if(cou>3){ ]-->
					<button class='archive_inventory'onclick="webJump()">查看更多</button>
			<!--[}]-->
</script>
<script src="${STATIC_URL}/modules/web/js/index/index2.js"
	type="text/javascript"></script>

