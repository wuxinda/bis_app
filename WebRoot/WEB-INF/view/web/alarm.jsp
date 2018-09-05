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
  <div class="alarm">
    <h2 style="font-size:24px" > <img alt="" title="" src="${STATIC_URL}/images/web/i_baoj_act_icon.png"  >报警</h2>
    <h3>
      <a class="deft" href="#">日查询</a>
      <a href="#">月度查询</a>
      <a href="#">年度查询</a>
      <p>当前日期：<input id="nyr" class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',readOnly:true,onpicked:function(){console.log($('#nyr').val());initDay(3);},maxDate:'%y-%M-%d'})" /></p>
      <p>当前日期：<input  id="ny" class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM',onpicked:function(){console.log($('#ny').val());initMonth(2);},maxDate:'%y-{%M}'})" /></p>
      <p>当前日期：<input id="n"  class="Wdate my97" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy',onpicked:function(){console.log($('#n').val());initYear(1);},maxDate:'%y'})"  /></p>
    </h3>
    <div class="arm_lf">
      <div class="arm_lf_t" id="status">
        <%-- <span><div ><img src="${STATIC_URL}/images/web/dang_b3.png"/> </div></span>--%>
       
        <p>今日报警总量200次，已处理180次，<em>未处理20次</em></p>
      </div>
      <div class="arm_lf_b" id="type"><%-- <img src="${STATIC_URL}/images/web/arm_biao.png"> --%></div>
    </div>
    <div class="arm_rt">
      <h5>未处理报警信息表</h5>
      <table border="0" cellspacing="0" cellpadding="0" id="alarmList">
    <%-- <tr>
      <th><img  title="" alt="" src="${STATIC_URL}/images/web/arm_icon_01.png">报警时间</th>
      <th><img  title="" alt="" src="${STATIC_URL}/images/web/arm_icon_02.png">设备类型</th>
      <th><img  title="" alt="" src="${STATIC_URL}/images/web/arm_icon_03.png">设备名称</th>
      <th><img  title="" alt="" src="${STATIC_URL}/images/web/arm_icon_04.png">报警原因</th>
      <th>处理</th>
    </tr>
    <tr>
      <td>2015-11-11 &nbsp; 10:20:00</td>
      <td>温湿度</td>
      <td>二号温控</td>
      <td>链接不上设备</td>
      <td></td>
    </tr>
    <tr>
      <td>2015-11-10 &nbsp; 10:08:22</td>
      <td>RFID</td>
      <td>档案二</td>
      <td>非法出库</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>2015-11-10 &nbsp; 10:25:22</td>
      <td>RFID</td>
      <td>aaaaaa</td>
      <td>错架</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>2015-11-10 &nbsp; 10:26:16</td>
      <td>温湿度</td>
      <td>一号温控</td>
      <td>该系统连不上</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>2015-11-11 &nbsp; 10:20:00</td>
      <td>温湿度</td>
      <td>二号温控</td>
      <td>链接不上设备</td>
      <td></td>
    </tr>
    <tr>
      <td>2015-11-10 &nbsp; 10:08:22</td>
      <td>RFID</td>
      <td>档案二</td>
      <td>非法出库</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>2015-11-10 &nbsp; 10:25:22</td>
      <td>RFID</td>
      <td>aaaaaa</td>
      <td>错架</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>2015-11-10 &nbsp; 10:26:16</td>
      <td>温湿度</td>
      <td>一号温控</td>
      <td>该系统连不上</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>2015-11-11 &nbsp; 10:20:00</td>
      <td>温湿度</td>
      <td>二号温控</td>
      <td>链接不上设备</td>
      <td></td>
    </tr>
    <tr>
      <td>2015-11-10 &nbsp; 10:08:22</td>
      <td>RFID</td>
      <td>档案二</td>
      <td>非法出库</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>2015-11-10 &nbsp; 10:25:22</td>
      <td>RFID</td>
      <td>aaaaaa</td>
      <td>错架</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>2015-11-10 &nbsp; 10:26:16</td>
      <td>温湿度</td>
      <td>一号温控</td>
      <td>该系统连不上</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>2015-11-10 &nbsp; 10:08:22</td>
      <td>RFID</td>
      <td>档案二</td>
      <td>非法出库</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>2015-11-10 &nbsp; 10:25:22</td>
      <td>RFID</td>
      <td>aaaaaa</td>
      <td>错架</td>
      <td>&nbsp;</td>
    </tr> --%>
</table>

    </div>
  </div>
</article>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</body>
</html>
<script src="${STATIC_URL}/modules/web/js/alarm.js" type="text/javascript"></script>
<script id="alarm-data" type="text/html">
    <tr>style="display:none;"
      <th><img  title="" alt="" src="${STATIC_URL}/images/web/arm_icon_01.png">报警时间</th>
      <th><img  title="" alt="" src="${STATIC_URL}/images/web/arm_icon_02.png">设备类型</th>
      <th><img  title="" alt="" src="${STATIC_URL}/images/web/arm_icon_03.png">所在库房</th>
      <th><img  title="" alt="" src="${STATIC_URL}/images/web/arm_icon_04.png">报警原因</th>
      <th>处理</th>
    </tr>
<!--[for(var i=0;i<list.length;i++){]-->
   <tr>
      <td style="display:none;"><!--[=list[i].alarmId]--></td>
      <td><!--[=list[i].ctime]--></td>
      <td><!--[=list[i].deviceTypeName]--></td>
      <td><!--[=list[i].storeRoomName]--></td>
      <td><!--[=list[i].remark]--></td>
      <td>&nbsp;</td>
    </tr>
<!--[}]-->
</script>
