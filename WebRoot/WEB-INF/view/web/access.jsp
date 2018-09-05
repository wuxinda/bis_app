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
  <div class="archives">
    <h2>
      <img alt="" title="" src="${STATIC_URL}/images/web/i_dangacr_act_icon.png">门禁盘点
    </h2>
    
    
    <div class="arc_m">
      <aside>
      	<h5>本日门禁数据</h5>
        <div class="arc_crk_tu" id="day"><%-- <img src="${STATIC_URL}/images/web/dang_b1.png" alt=""/> --%></div>
        <!-- <p><strong>本日</strong>共出操作 <em>86</em> 次</p> -->
      </aside>
      <aside>
      	<h5>本月门禁数据</h5>
        <div class="arc_crk_tu" id ="month"><%-- <img src="${STATIC_URL}/images/web/dang_b2.png"  alt=""/> --%></div>
        <!-- <p><strong>本月</strong>共出入库 <em>862</em> 次</p> -->
      </aside>
      <aside>
      	<h5>本年门禁数据</h5>
        <div class="arc_crk_tu" id = "year"><%-- <img src="${STATIC_URL}/images/web/dang_b2.png"  alt=""/> --%></div>
        <!-- <p><strong>本年</strong>共出入库 <em>2863</em> 次</p> -->
      </aside>
    </div>
    <div class="arc_b">
      <h2>门禁记录
        
      </h2>
      <ul id="auditAms">
        <%-- <li>
          <h5 class="color_8"><img src="${STATIC_URL}/images/web/dang_icon1.png"><br>18:56:24</h5>
          <p class="color_zt_8">超期</p>
          <p><img src="${STATIC_URL}/images/web/dang_icon2.png"><s>201-2000-11-011</s></p>
          <div class="arc_del"></div>
        </li>
        <li>
          <h5 class="color_8"><img src="${STATIC_URL}/images/web/dang_icon1.png"><br>18:56:56</h5>
          <p class="color_zt_8">超期</p>
          <p><img src="${STATIC_URL}/images/web/dang_icon2.png"><s>201-2000-11-011</s></p>
          <div class="arc_del"></div>
        </li>
        <li>
          <h5 class="color_1"><img src="${STATIC_URL}/images/web/dang_icon1.png"><br>01:56:24</h5>
          <p class="color_zt_1"><span>0</span><em>天</em></p>
          <p><img src="${STATIC_URL}/images/web/dang_icon2.png"><s>201-2000-11-011</s></p>
          <div class="arc_del"></div>
        </li>
        <li>
          <h5 class="color_1"><img src="${STATIC_URL}/images/web/dang_icon1.png"><br>18:56:00</h5>
          <p class="color_zt_1"><span>0</span><em>天</em></p>
          <p><img src="${STATIC_URL}/images/web/dang_icon2.png"><s>201-2000-11-011</s></p>
          <div class="arc_del"></div>
        </li>
        <li>
          <h5 class="color_5"><img src="${STATIC_URL}/images/web/dang_icon1.png"><br>22:56:24</h5>
          <p class="color_zt_5"><span>1</span><em>天</em></p>
          <p><img src="${STATIC_URL}/images/web/dang_icon2.png"><s>201-2000-11-011</s></p>
          <div class="arc_del"></div>
        </li>
        <li>
          <h5 class="color_5"><img src="${STATIC_URL}/images/web/dang_icon1.png"><br>18:56:24</h5>
          <p class="color_zt_5"><span>3</span><em>天</em></p>
          <p><img src="${STATIC_URL}/images/web/dang_icon2.png"><s>201-2000-11-011</s></p>
          <div class="arc_del"></div>
        </li>
        <li>
          <h5 class="color_5"><img src="${STATIC_URL}/images/web/dang_icon1.png"><br>18:56:24</h5>
          <p class="color_zt_5"><span>3</span><em>天</em></p>
          <p><img src="${STATIC_URL}/images/web/dang_icon2.png"><s>201-2000-11-011</s></p>
          <div class="arc_del"></div>
        </li>
        <li>
          <h5 class="color_5"><img src="${STATIC_URL}/images/web/dang_icon1.png"><br>18:56:24</h5>
          <p class="color_zt_5"><span>4</span><em>天</em></p>
          <p><img src="${STATIC_URL}/images/web/dang_icon2.png"><s>201-2000-11-011</s></p>
          <div class="arc_del"></div>
        </li>
        <li>
          <h5 class="color_6"><img src="${STATIC_URL}/images/web/dang_icon1.png"><br>18:56:24</h5>
          <p class="color_zt_6"><span>16</span><em>天</em></p>
          <p><img src="${STATIC_URL}/images/web/dang_icon2.png"><s>201-2000-11-011</s></p>
          <div class="arc_del"></div>
        </li> --%>

      </ul>
     
       <h4 id="door_yema">
        <a href="#" id="firstpage"> 首页</a>
        <span class="arc_b_prev"><img src="${STATIC_URL}/images/web/dang_icon_left.png"></span>
        <input type="text" id="nowpage">
        <span class="arc_b_next"><img src="${STATIC_URL}/images/web/dang_icon_right.png"></span>
        <a href="#" id="lastpage"> 尾页</a> 
        <em id="totlePageCount"></em>
       </h4>
    </div>
  </div>
</article>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</body>
</html>
<script src="${STATIC_URL}/modules/web/js/access.js" type="text/javascript"></script>
<script id="auditAms-data" type="text/html">

<!--[for(var i=0;i<list.length;i++){]-->
<li>
<h5 class="color_8"><img src="${STATIC_URL}/images/web/dang_icon1.png"><br><!--[=list[i].mh]--></h5>
<p class="color_zt_7"><img src="${STATIC_URL}/images/web/dang_icon2.png"><s><!--[=list[i].md]-->&nbsp;&nbsp;</p>
<p>设备名称：<!--[=list[i].deviceName]-->&nbsp;&nbsp;<span style="font-size:15px"><!--[=list[i].remark]--></span></s></p>
<div class="arc_del"></div>
</li>
<!--[}]-->
</script>

