<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入资源文件 -->
<%@ include file="/WEB-INF/view/web/common/resource.jsp"%>
<script type="text/javascript" src="${STATIC_URL}/plugins/HKWS/webVideoCtrl.js"></script>
<script type="text/javascript" src="${STATIC_URL}/modules/web/js/common/hkvideo.js"></script>
<title>智慧馆库系统</title>
</head>
<body>
	<!-- 引入头部文件 -->
	<%@ include file="/WEB-INF/view/web/common/header.jsp"%>
	<div id="lay">    
      <div class="qxlay"></div>   
      <div class="article">
        <p>第八库房   A区   第二列 (<span>左侧</span>) —— 档案存储架位图</p> 
        <div class="zy"></div>
        <ul>
          <li> 
             <div class="li-m"> 第一节</div>
             <div class="li-hm"> 第一层</div> 
             <table width="0" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td title="201-1988-01-01">201-1988-01-01</td>
                  <td title="201-1988-01-02">201-1988-01-02</td>
                  <td title="201-1988-01-03">201-1988-01-03</td>
                  <td title="201-1988-01-04">201-1988-01-04</td>
                  <td title="201-1988-01-05">201-1988-01-05</td>
                  <td title="201-1988-01-06">201-1988-01-06</td>
                </tr>
            </table>
          </li> 
          <li> <div class="li-m"> 第二节</div> </li> 
          <li> <div class="li-m"> 第三节</div></li> 
          <li><div class="li-m"> 第四节</div></li> 
          <li><div class="li-hm"> 第二层</div></li> 
          <li></li> <li></li>  <li></li> 
          <li><div class="li-hm"> 第三层</div></li> 
         <li> 
           <table width="0" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>2011</td>
              <td>2</td>
              <td>3</td>
              <td>1</td>
              <td>2</td>
              <td>3</td>
               <td>2</td>
              <td>3</td>
              <td>1</td>
              <td>2</td>
              <td>3</td>
            </tr>
          </table>
        </li> 
        <li></li>
        <li></li> 
        <li><div class="li-hm"> 第四层</div></li> <li></li> <li></li> <li></li>
        <li><div class="li-hm"> 第五层</div></li> <li></li> <li></li> <li></li> 
        <li><div class="li-hm"> 第六层</div></li> <li></li> <li></li> <li></li> 
     </ul>
         <ul>
         <li>
           <div class="li-m"> 第一节</div> 
           <div class="li-hm"> 第一层</div> 
           <table width="0" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>\</td>
              <td>2</td>
              <td>3</td>
              <td>1</td>
              <td>2</td>
              <td>3</td>
              <td>1</td>
              <td>2</td>
              <td>3</td>
            </tr>
          </table>
        </li> 
        <li> <div class="li-m"> 第二节</div> </li> 
        <li> <div class="li-m"> 第三节</div></li> 
        <li><div class="li-m"> 第四节</div></li> 
        <li><div class="li-hm"> 第二层</div></li> <li></li> <li></li> <li></li> 
        <li><div class="li-hm"> 第三层</div></li> 
        <li> 
           <table width="0" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>\</td>
              <td>2</td>
              <td>3</td>
              <td>1</td>
              <td>2</td>
              <td>3</td>
               <td>2</td>
            </tr>
          </table>
        </li> 
        <li></li> <li></li> 
        <li><div class="li-hm"> 第四层</div></li> <li></li> <li></li> <li></li>
        <li><div class="li-hm"> 第五层</div></li> <li></li> <li></li> <li></li> 
        <li><div class="li-hm"> 第六层</div></li> <li></li> <li></li> <li></li> 
      </ul>
    </div>
  </div>
  <input type="hidden" id="size" value="5"/>
<input type="hidden" id="jx_video_host_ip" value="192.168.1.210" /> 
<input type="hidden" id="jx_video_host_port" value="8000" /> 
<input type="hidden" id="jx_video_login_name" value="admin" />
<input type="hidden" id="jx_video_login_password" value="fxs12345" />
<article>
  <div class="house">
    <h2> <img alt="" title="" src="${STATIC_URL}/images/web/i_kuft_act_icon.png">库房布局图</h2>
    
     <div class="kf"><img class="kf-bg" src="${STATIC_URL}/images/web/111.png" />
     
     
     
     <table cellpadding="0" cellspacing="3" border="0" style="display:none">
			<tr>
				<td class="tt">IP地址</td>
				<td><input id="loginip" type="text" class="txt" value="192.168.1.210" /></td>
				<td class="tt">端口号</td>
				<td><input id="port" type="text" class="txt" value="80" /></td>
			</tr>
			<tr>
				<td class="tt">用户名</td>
				<td><input id="username" type="text" class="txt" value="admin" /></td>
				<td class="tt">密码</td>
				<td><input id="password" type="password" class="txt" value="fxs12345" /></td>
			</tr>
			<tr>
				<td class="tt">设备端口</td>
				<td colspan="2"><input id="deviceport" type="text" class="txt" value="8000" />（可选参数）</td>
			</tr>
			<tr>
				<td class="tt">已登录设备</td>
				<td>
					<select id="ip" class="sel" onchange="getChannelInfo();"></select>
				</td>
				<td class="tt">通道列表</td>
				<td>
					<select id="channels" class="sel"></select>
				</td>
				<input id="transstream" type="checkbox" class="vtop" style="display:none" type="hidden" />
			</tr>
			 <select style="display:none" id="streamtype" class="sel">
						<option value="1">主码流</option>
						<option value="2">子码流</option>
						<option value="3">第三码流</option>
						<option value="4">转码码流</option>
					</select>
					 
		</table>
        <div class="xst1 hd">
					<a href="#">
						<div class="k-tip" style="display:block;" id="divPlugin"> 
					</div>
					</a>
				</div>
				<div class="xst1 z1">
					<a href="#">
						<div class="k-tip" style="display:none">
						</div></a>
				</div>

				<div class="xst1 z2">
					<a href="#">
						<div class="k-tip" style="display:none">
						</div> </a>
				</div>
				<div class="xst1 y1">
					<a href="#">
						<div class="k-tip" style="display:none">
						</div> </a>
				</div>
				<div class="xst2">
					<a href="#">
						 <div class="k-tip" style="display:none">
						</div></a>
				</div>
			
			 <div class="miTd" >
			<div class="miTd_del"></div>
				<div class="miTd_sp" style="display:none" >
				</div>
			</div> 
<!-- ------------------------------------摄像头结束------------------------------------------------->
        <div class="mj1 m0">
          <a href="#">
            <div class="k-tip"> 
              <%-- <img src="${STATIC_URL}/images/web/mj-tip-g.png"/> 
              <span><img src="${STATIC_URL}/images/web/mj-tip-kg-g.png"/></span> --%>
            </div>
          </a>
        </div>
        <div class="mj1 m1">
          <a href="#">
            <div class="k-tip">
              <%-- <img src="${STATIC_URL}/images/web/mj-tip-k.png"/>
              <span><img src="${STATIC_URL}/images/web/mj-tip-kg-k.png"/></span> --%>
            </div>
          </a>
        </div>
        <div class="mj1 m2">
          <a href="#">
            <div class="k-tip">
              <%-- <img src="${STATIC_URL}/images/web/mj-tip-g.png"/>
              <span><img src="${STATIC_URL}/images/web/mj-tip-kg-g.png"/></span> --%>
            </div>
          </a>
       </div>
       <div class="mj1 m3">
         <a href="#">
           <div class="k-tip">
             <%-- <img src="${STATIC_URL}/images/web/mj-tip-k.png"/>
             <span><img src="${STATIC_URL}/images/web/mj-tip-kg-k.png"/></span> --%>
           </div>
         </a>
       </div>
       <div class="mj1 m4">
         <a href="#">
           <div class="k-tip">
             <%-- <img src="${STATIC_URL}/images/web/mj-tip-g.png"/>
             <span><img src="${STATIC_URL}/images/web/mj-tip-kg-g.png"/></span> --%>
           </div>
         </a>
       </div>
       <div class="mj1 m5">
         <a href="#">
           <div class="k-tip">
             <%-- <img src="${STATIC_URL}/images/web/mj-tip-k.png"/>
             <span><img src="${STATIC_URL}/images/web/mj-tip-kg-k.png"/></span> --%>
           </div>
         </a>
       </div>
<!-- ------------------------------------门禁结束------------------------------------------------->       
       <div class="dg">
         <a href="#">
           <div class="k-tip"> 
             <%-- <img src="${STATIC_URL}/images/web/dg-tip-k.png"/> 
             <span class="kg"> <img src="${STATIC_URL}/images/web/dg-tip-kg-k.png"/></span>  --%>
           </div>
         </a>
       </div>
<!-- ------------------------------------灯光结束------------------------------------------------->
       <div class="wsd">
         <a class="nohd" href="#">
           <div class="k-tip">  
             <!-- <span> <em>40.2</em>℃</span>  
             <span> <em>38</em>%</span> -->
           </div>
         </a>
       </div>
       <div class="wsd wsd1">
         <a class="nohd" href="#">
           <div class="k-tip">
             <!-- <span> <em>36.8</em>℃</span> 
             <span> <em>45.6</em>%</span> -->
           </div>
         </a>
       </div>
<!-- ------------------------------------温湿度结束------------------------------------------------->
       <div class="rfid">
         <a href="#">
           <div class="k-tip">
             <table  cellspacing="0" cellpadding="0">
                <tr>
                  <th>序号</th>
                  <th>档号</th>
                  <th>详细信息</th>
                </tr>
                <tr>
                  <td>01</td>
                  <td>201-2009-11-001</td>
                  <td>非法带出</td>
                </tr>
                  <tr>
                  <td>02</td>
                  <td>201-2009-11-001</td>
                  <td>非法带出</td>
                </tr>
                  <tr>
                  <td colspan="3">更多</td>
                  </tr>
              </table> 
              <div class="arrow"></div>
           </div> 
         </a>
       </div>
<!-- ------------------------------------RFID结束------------------------------------------------->
       <ul class="mjj">
         <li>
           <a href="#">
              <div class="k-tip"> 
                <div class="ck" onclick="findDaxx(1)"></div>    
                <p>第一列</p> 
                <ol class="lie">
                <li onclick="openleft(10);"></li>
                 <li onclick="stop(10);"></li> 
                 <li onclick="openright(10);"></li></ol>
                <p style="margin-top:10px;float:left;">该区域</p> 
                <ol class="quyu"> 
                <li onclick="wind(10);"></li> 
                <li class="tip-def1" onclick="closeshelve(10);"></li></ol>
               </div>
            </a>
         </li>
         <li>
           <a href="#"> 
             <div class="k-tip"> 
               <div class="ck" onclick="findDaxx(11)"></div>
               <p>第二列</p> 
               <ol class="lie">	
               <li onclick="openleft(11);"></li>
				<li onclick="stop(11);"></li>
				<li onclick="openright(11);"></li>
				</ol>
               <p style="margin-top:10px;float:left;">该区域</p> 
               <ol class="quyu">
               <li onclick="wind(11);"></li>
				<li class="tip-def1" onclick="closeshelve(11);"></li>
				</ol>
             </div>
           </a>
        </li>
        <li>
          <a href="#">
            <div class="k-tip">    
              <div class="ck" onclick="findDaxx(3)"></div>
              <p> 第三列</p> 
              <ol class="lie">	
             	 <li onclick="openleft(12);"></li>
				<li onclick="stop(12);"></li>
				<li onclick="openright(12);"></li></ol>
              <p style="margin-top:10px;float:left;">该区域</p>  
              <ol class="quyu">
              	<li onclick="wind(12);"></li>
				<li class="tip-def1" onclick="closeshelve(12);"></li>
				</ol>
            </div>
          </a> 
        </li>
        <li>
          <a href="#">
            <div class="k-tip"> 
              <div class="ck" onclick="findDaxx(14)"></div>
              <p>  第四列</p> 
              <ol class="lie"><li onclick="openleft(14);"></li>
									<li onclick="stop(14);"></li>
									<li onclick="openright(14);"></li></ol>
              <p style="margin-top:10px;float:left;">该区域</p>  
              <ol class="quyu"><li onclick="wind(14);"></li>
									<li class="tip-def1" onclick="closeshelve(14);"></ol>
            </div>
          </a> 
        </li>
        <li>
          <a href="#"> 
            <div class="k-tip">  
              <div class="ck" onclick="findDaxx(15)"></div>
              <p>第五列</p> 
              <ol class="lie"><li onclick="openleft(15);"></li>
									<li onclick="stop(15);"></li>
									<li onclick="openright(15);"></li></ol>
              <p style="margin-top:10px;float:left;">该区域</p>  
              <ol class="quyu"><li onclick="wind(15);"></li>
									<li class="tip-def1" onclick="closeshelve(15);"></ol>
            </div>
          </a>
        </li>
      </ul>
      <%-- <div class="miTd"><div class="miTd_del"><img src="${STATIC_URL}/images/web/dang_icon_act_del.png"></div></div>
      <div class="kf_light"></div> --%>
      
<!-- ------------------------------------密集架结束------------------------------------------------->      
    </div>
    <aside>
      <a href="#" onclick="optionAsc('17',-1,7)">玻璃门</a>
      <a href="#" onclick="optionAsc('19',-1,9)">库房门</a>  
      <a href="#"  onclick="optionIls('1',-1,10);">灯光</a>
      <a href="#"  onclick="openright1(10,3);">通道1</a> 
      <a href="#"  onclick="openright1(11,4);">通道2</a>
      <a href="#"  onclick="openright1(12,5);">通道3</a> 
      <a href="#" onclick="openright1(14,6);">通道4</a>
      <a href="#"  onclick="closeshelve1(10);">合拢</a> 
      <a href="#" onclick="wind1(10);">通风</a>
    </aside>
   
    <aside>
      <a  class="active b2" href="#" onclick="optionIls('1','0',10);">灯全开</a> 
      <a class="b2" href="#" onclick="optionIls('1','1',10);">灯全关</a>
    </aside>
 <!------------------- aside数量-------------------------------->
    <hr/>
    
    <nav>
      <a class="activ" href="#">库房区</a>
      <a href="#">其它</a>
    </nav>
   <!------------------- nav a数量--------------------------------> 
  </div>
</article>
    <!-- 引入尾部文件 -->
	<%@ include file="/WEB-INF/view/web/common/footer.jsp"%>
</body>
</html>
<script src="${STATIC_URL}/modules/web/js/layout.js" type="text/javascript"></script>
<script type="text/javascript" src="${STATIC_URL}/modules/web/js/hkvideo.js"></script>
<script type="text/javascript" src="${STATIC_URL}/plugins/HKWS/webVideoCtrl.js"></script>
