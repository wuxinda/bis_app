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
<article>
  <div class="energy">
    <h2> <img alt="" title="" src="${STATIC_URL}/images/web/i_nengh_act_icon.png">库房能耗情况</h2>
    <nav> <a  class="def" href="#"> 月度查询</a> <a href="#"> 年度查询</a> </nav>
    <aside>当前年月：
      <input  id="SearchNy" class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM',onpicked:function(){showchange(1);},maxDate:'%y-{%M}'})" />
    </aside>
    <aside>当前年份：
      <input id="SearchYear"  class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy',onpicked:function(){showchange(2);},maxDate:'%y'})"  />
      </aside>
    <ul>
      <li>
      <!--   <p>本月库房实际耗电量 <em>8521.25 <span>kWh</span> </em></p> -->
      </li>
      <li>
       <!--  <p>本月库房目标耗电量 <em>9000.00 <span>kWh</span> </em></p> -->
      </li>
      <li>
     <!--    <p>目标完成 <em>82.36 <span>%</span> </em></p> -->
      </li>
      <!-- <li>
        <p>同比下降 <s>10.24 <span>%</span></s> </p>
        <p>环比上升<s> 8.55 <span>%</span></s> </p>
      </li> -->
    </ul>
    <div class="n_zhu" id="jqChartColumn"> <%-- <img src="${contentpath}/images/n_pici.png"> --%></div>
    <div class="n_bin"  id="jqChartPie"><%-- <img src="${contentpath}/images/n_pic2.png"> --%></div>
  </div>
</article>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</body>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</html>
<script src="${STATIC_URL}/modules/web/js/ecs.js" type="text/javascript"></script>

