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
<script src="${STATIC_URL}/modules/web/js/hump.js" type="text/javascript"></script>
<article>
  <div class="temp">
      <div class="subnav">
		   <ul>
		       <li><a href="#">实时环境数据</a></li>
		       <li><a href="#">历史环境数据</a></li>
		    </ul>
	   </div>
	   <div class="temp_rt"> 
	      <h2>
            <a href="javascript:location.href=config.humiture">环境监测</a>
          </h2>
	      <div class="title">
	         <div class="rt_top">
		        <a href="#" class="acti" id="day" >日报表</a>
		        <a href="#" id="mon" > 月报表</a> 
		        <a href="#" id="year" >年报表</a>
		        <div class="dy"> 
		          <img onclick='toexcel()' src="${STATIC_URL}/images/web/wsd_dc.png"/> 
		           <span>
		             <img src="${STATIC_URL}/images/web/wsd_tip.png">
		           </span>
		        </div>
	          </div>
		  <div class="select">
		  	<div class="warehouse">
		      	<label>库房
			      <select id="device"></select>
			      </label>
			 </div>
		       <p>报表日期:  <input id="nyr" class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',readOnly:true,onpicked:function(dp){console.log($('#nyr').val());showDayInfo(dp.cal.getNewDateStr());},maxDate:'%y-%M-%d'})" /></p>
	 <p>报表日期:  <input  id="ny" class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM',onpicked:function(dp){console.log($('#ny').val());showDayInfo(dp.cal.getNewDateStr());},maxDate:'%y-{%M}'})" /></p>
	 <p>报表日期:  <input id="n"  class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy',onpicked:function(dp){console.log($('#n').val());showDayInfo(dp.cal.getNewDateStr());},maxDate:'%y'})"  /></p>
     
		  </div>
		  <table>
	          <thead>
                <tr>
					<th>区域</th>
					<th>时间</th>
					<th>温度(平均)</th>
					<th>湿度(平均)</th>	
				 </tr>
	          </thead>
	          <tbody id="humitureTable">
	          </tbody>
		  </table>
	  </div>	  
   </div>
</article>
</body>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</html>
<script id="humitureTable-data-list-tmpl" type="text/html">
    		 	<!--[for(i=0;i<list.length;i++){ ]-->
				<!--[if(i<1){]-->
						<tr>
	   	               	    <td rowspan="<!--[=list.length]-->"><!--[=storeName]--></td>
	   	               	 <td><!--[=list[i].time]--></td>
	   	               	 <td><!--[=list[i].temperature]--></td>
	   	               	 <td><!--[=list[i].humidity]--></td>
	   	          	  </tr>
					<!--[}else if(i==list.length-1){]-->
						<tr id="last_tr">
	   	               	 <td><!--[=list[i].time]--></td>
	   	               	 <td><!--[=list[i].temperature]--></td>
	   	               	 <td><!--[=list[i].humidity]--></td>
	   	          	  </tr>
					<!--[}else{]-->
						<tr>
	   	               	 <td><!--[=list[i].time]--></td>
	   	               	 <td><!--[=list[i].temperature]--></td>
	   	               	 <td><!--[=list[i].humidity]--></td>
	   	          	  </tr>
					<!--[}]-->
				<!--[}]-->
</script>