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
  <div class="collection">
    <h2> <img alt="" title="" src="${STATIC_URL}/images/web/i_guancl_act_icon.png">馆藏量</h2>
    <ul id="baseNum">
     <!--  <li>
        <p><em>2</em><s>个</s></p>
        <p><span></span>库房</p>
      </li>
       <li>
        <p><em>5</em><s>列</s></p>
        <p><span></span>密集架</p>
      </li>
       <li>
        <p><em>200</em><s>卷（个）</s></p>
        <p><span></span>档案</p>
      </li> -->
    </ul>
    <aside id="wmsStore"> <%-- <img src="${STATIC_URL}/images/web/guan_b1.png"> --%></aside> 
    <aside id="inflag" class="coll_m"><%-- <img src="${STATIC_URL}/images/web/guan_b2.png"> --%></aside>
    
    <div class="col_b"> 
      <span id="archivesType"><%-- <img  src="${STATIC_URL}/images/web/guan_b3_01.png"> --%></span> 
      <span id="security"><%-- <img  src="${STATIC_URL}/images/web/guan_b3_02.png"> --%></span> 
      <span id="keepyear"><%-- <img  src="${STATIC_URL}/images/web/guan_b3_03.png"> --%></span> 
    </div>
     
  </div>
</article>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</body>
</html>
<script src="${STATIC_URL}/modules/web/js/holding.js" type="text/javascript"></script>
<script id="baseNum-data" type="text/html">
		 <li>
        <p><em><!--[=baseData.wmsNum]--></em><s>个</s></p>
        <p><span></span>库房</p>
      </li>
       <li>
        <p><em><!--[=baseData.selNum]--></em><s>列</s></p>
        <p><span></span>密集架</p>
      </li>
       <li>
        <p><em><!--[=baseData.amsNum]--></em><s>卷（个）</s></p>
        <p><span></span>档案</p>
      </li>
</script>
