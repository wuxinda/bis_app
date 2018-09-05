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
	<article>
  <div class="temp">
    <h2> <img alt="" title="" src="${STATIC_URL}/images/web/i_wengsd_act_icon.png"> 温湿度</h2>
    <div class="temp_lf">
      <h4>当前库房温湿度状况</h4>
      <div class="gundong">
      <table   border="0" cellspacing="0" cellpadding="0">
      <tbody id="newHumiture" >
      </tbody>
    </table>
</div>
    </div>
    
    <div class="temp_rt"> 
      <div class="rt_top">
        <a href="#" class="acti" >日报表</a>
        <a href="#" > 月报表</a> 
        <a href="#">年报表</a>
        <div class="dy"> <img onclick='toexcel()' src="${STATIC_URL}/images/web/wsd_dc.png"> <span><img src="${STATIC_URL}/images/web/wsd_tip.png"></span></div>
      </div>
      <p>报表日期:  <input id="nyr" class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',readOnly:true,onpicked:function(dp){console.log($('#nyr').val());showDayInfo(dp.cal.getNewDateStr());},maxDate:'%y-%M-%d'})" /></p>
	 <p>报表日期:  <input  id="ny" class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM',onpicked:function(dp){console.log($('#ny').val());showDayInfo(dp.cal.getNewDateStr());},maxDate:'%y-{%M}'})" /></p>
	 <p>报表日期:  <input id="n"  class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy',onpicked:function(dp){console.log($('#n').val());showDayInfo(dp.cal.getNewDateStr());},maxDate:'%y'})"  /></p>
      <table border="0" cellspacing="0" cellpadding="0">
  <tbody id="humitureTable">
  </tbody>
</table>
    </div>
  </div>
</article>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</body>
</html>
<script src="${STATIC_URL}/modules/web/js/humiture.js" type="text/javascript"></script>
<script id="humiture-data" type="text/html">
		<tr>
          <td>区域</td>
<!--[for(var i =0;i<humiture.length;i++){]-->
         <td><!--[=humiture[i].storeName]--></td>
<!--[}]-->
        </tr>
        <tr>
          <td>温度</td>
<!--[for(var i =0;i<humiture.length;i++){]-->
         <td class="tab_col"><!--[=humiture[i].avgtem]--><span>℃</span></td>
<!--[}]-->
        </tr>
        <tr>
          <td>湿度</td>
<!--[for(var i =0;i<humiture.length;i++){]-->
         <td class="tab_col"><!--[=humiture[i].avghum]--><span>%Rh</span></td>
<!--[}]-->   
        </tr>
        <tr>
          <td>空调</td>
<!--[for(var i =0;i<humiture.length;i++){]-->
          <td><img title="" alt="" src="${STATIC_URL}/images/web/
		  <!--[if(humiture[i].airStatus==0){]-->
			w-kt.gif
          <!--[}else if(humiture[i].airStatus==1){]-->
			w-kt-guan.png
		  <!--[}else{]-->
            w-kt-guan.png
		  <!--[}]-->
         "/></td>
<!--[}]-->
        </tr>
        <tr>
          <td>增湿</td>
<!--[for(var i =0;i<humiture.length;i++){]-->
          <td><img title="" alt="" src="${STATIC_URL}/images/web/
			<!--[if(humiture[i].wettingStatus==0){]-->
			w-zs.png
          <!--[}else{]-->
			w-act-zs.gif
		  <!--[}]-->
			"/></td>
<!--[}]-->
        </tr>
        <tr>
          <td>去湿</td>
<!--[for(var i =0;i<humiture.length;i++){]-->
<td><img title="" alt="" src="${STATIC_URL}/images/web/
			<!--[if(humiture[i].dryingStatus==0){]-->
			w-qs.png
          <!--[}else{]-->
			w-act-qs.gif
		  <!--[}]-->
			"/></td>
<!--[}]-->
        </tr>
        <tr>
          <td>通风</td>
<!--[for(var i =0;i<humiture.length;i++){]-->
<td><img title="" alt="" src="${STATIC_URL}/images/web/
			<!--[if(humiture[i].ventilationStatus==3){]-->
			w-tf.png
          <!--[}else{]-->
			w-act-tf.gif
		  <!--[}]-->
			"/></td>
<!--[}]-->
        </tr>
        <tr>
          <td>净化</td>
<!--[for(var i =0;i<humiture.length;i++){]-->
<td><img title="" alt="" src="${STATIC_URL}/images/web/
			<!--[if(humiture[i].cleansingStatus==0){]-->
			w-xd.png
          <!--[}else{]-->
			w-act-xd.gif
		  <!--[}]-->
			"/></td>
<!--[}]-->
        </tr>
        <tr>
          <td>警告</td>
<!--[for(var i =0;i<humiture.length;i++){]-->
<td><img title="" alt="" src="${STATIC_URL}/images/web/
			<!--[if(humiture[i].warningStatus==1){]-->
			w-jh.png
          <!--[}else if(humiture[i].warningStatus==0){]-->
			w-kt-guan.png
		  <!--[}]-->
			"/></td>
<!--[}]-->
        </tr>

</script>
<script id="humiture-table" type="text/html">
<tr>
<td class="w130">库房</td>
<!--[for(var i=0;i<HumitureData.wmslist.length;i++){]-->
<td colspan="2">
<!--[=HumitureData.wmslist[i].name]-->
</td>
<!--[}]-->
</tr>

<tr>
      <td>时间
<!--[if(Defaulttime==24){]-->
(小时)
<!--[}else if(Defaulttime==31){]-->
(天数)
<!--[}else{]-->
(月份)
<!--[}]-->
</td>
<!--[for(var i=0;i<HumitureData.wmslist.length;i++){]-->
<td class="w118">温度(平均)</td>
<td class="w118">湿度(平均)</td>
<!--[}]-->
</tr>
<!--[var i = 1;if(Defaulttime==24){i=0;Defaulttime=(Defaulttime-1)}]-->
<!--[for(i;i<=Defaulttime;i++){]-->
<tr><td><!--[=i]--></td>
<!--[for(var x=0;x<HumitureData.wmslist.length;x++){]-->
<!--[var flag = 0;]-->
<!--[for(var y=0;y<HumitureData.data.length;y++){]-->
<!--[if(HumitureData.wmslist[x].storeId==HumitureData.data[y].storeId&&HumitureData.data[y].date==i){]-->
<!--[flag = 1;]-->
<td><!--[=HumitureData.data[y].tems]-->℃</td>
<td><!--[=HumitureData.data[y].hums]-->%Rh</td>
<!--[}]-->
<!--[}]-->
<!--[if(flag == 0){]-->
<td>无</td>
<td>无</td>
<!--[}]-->
<!--[}]-->
</tr>
<!--[}]-->
</script>