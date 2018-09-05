<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
  .app .hbox.stretch {
    height: 99%;
    }
    *{
	   margin:0px;
	   padding:0px;
	}
	ul li{
	  list-style:none;
	}
	#shenfen .nav >li ,#chengshi .nav >li ,#quyu .nav >li,#operate .nav >li{
		  padding: 10px 15px;
		  border-bottom: 1px solid #E4E4E4;
		 
	}
	.linkTip{
		font-size: 14px;
		background-color: rgba(0,0,0,0.05);
		font-weight: 600;
		padding:15px 20px;
	}
	@media (min-width: 768px) {
 
  .hbox {
    display: table;
    table-layout: fixed;
    border-spacing: 0;
    width: 100%;
  }
  .hbox > aside,
  .hbox > section {
    display: table-cell;
    vertical-align: top;
    height: 100%;
    padding: 0;
    float: none;
  }
  .hbox > aside.show,
  .hbox > section.show,
  {
    display: table-cell !important;
  }
  .vbox {
    display: table;
    border-spacing: 0;
    position: relative;
    height: 100%;
    width: 100%;
  }
  .b-b {
  border-bottom: 1px solid #cfcfcf;
}
.b-l {
  border-left: 1px solid #cfcfcf;
}
.b-light {
  border-color: #e4e4e4;
}
  }
</style>
	<body>
		<section class="vbox">
			<section>
				<section class="hbox stretch">
					<section id="content">
						<section class="vbox">
						<header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-8 m-b-xs">
                        <!-- <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-default action-refresh" title="返回"><i class="fa fa-refresh"></i></button>                           
                        </div> -->
                        <div class="btn-group">
                           选择场景模式需要联动的设备
                        </div>
                    </div>
	                <div class="col-sm-4 m-b-xs">
	                    <div class="input-group">
	                        <span class="input-group-btn" id="addDevice">
	                        </span>
	                    </div>
	                </div>
                </div>
            </header>
							<section class="scrollable">
								<section class="hbox stretch">
									<aside class="aside-lg bg-light lter b-r m_zlxg" id="shenfen">
										<p class="wrapper b-b header linkTip" id="category1">请选择库房</p>
										<a href="javascript:;">
										<div class="m_zlxg2">
									    <ul class="nav">
										<c:forEach items="${wmsStore}" var="s">
										<li onclick="categoryName1('${s.storeId}','${s.name}')">${s.name}</li>
										</c:forEach>
											</ul>
										</div>
										</a>
									</aside>
									<aside class="col-lg-4 b-l bg-white m_zlxg" id="chengshi">
										<p class="wrapper b-b header linkTip" id="category2">请选择设备分类</p>
										<a href="javascript:;">
										<div class="m_zlxg2">
									    <ul class="nav" id="categoryName2"></ul>
										</div>
										</a>
									</aside>
									<aside class="col-lg-4 b-l bg-white m_zlxg" id="quyu">
										<p class="wrapper b-b header linkTip" id="category3">请选择设备</p>
									   <a href="javascript:;">
										<div class="m_zlxg2">
											<ul class="nav" id="categoryName3"></ul>
										</div>
										</a>
									</aside>
									<aside class="col-lg-4 b-l bg-white m_zlxg" id="operate">
										<p class="wrapper b-b header linkTip" id="category4">请选择动作</p>
									   <a href="javascript:;">
										<div class="m_zlxg2">
											<ul class="nav" id="categoryName4">
											<!-- <li ><input type="checkbox" name="post[]" id="select_single_p_1" class="select-single" data-type="p" value="1"><span>开</span></li> -->
											<!-- <li ><input type="checkbox" name="post[]" id="select_single_p_2" class="select-single" data-type="p" value="2"><span>关</span></li> -->
											</ul>
										</div>
										</a>
									</aside>
								<!-- 已选中的分类 -->
								<aside class="col-lg-4 b-l bg-white m_zlxg" id="selected_property">
									<section class="vbox">
										<header class="b-b header" >
											<p class="h4">
												已选设备<font class="text-sm">（可拖拽排序）</font>
											</p>
										</header>
										<section class="scrollable w-f">
											<ul class="nav nav-stacked list-group gutter list-group-lg list-group-sp sortable">
												<c:forEach items="${linkageList}" var="v">
													<li class="b-b m-t-none-reset"
														id="li_property_${v.linkageId}" device_id="${v.deviceId}"
														property_id="${v.linkageId}" draggable="true"><a
														href="javascript:;"> <i title="移除该属性"
															class="fa fa-times pull-right m-t-xs fa-remove-brand"></i>
															<i class="fa fa-fw fa-ellipsis-v"></i><font
															class="property-name">${v.deviceName}-${v.actionName}</font>
													</a></li>
												</c:forEach>
											</ul>
										</section>
									</section>
								</aside>
							</section>
							</section>
						</section>
						<a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
					</section>
					<aside class="bg-light lter b-l aside-md hide" id="notes">
						<div class="wrapper">Notification</div>
					</aside>
				</section>
			</section>

        <!-- 隐藏提交表单 -->
		<form class="form-horizontal" id="edit_form" action="${BASE_URL}/sceneManage/editSceneLinkage" method="post">
			<div class="edit-map" id="edit_property"></div>
			<input type="hidden" id="sceneId" name="sceneId" value="${sceneManage.sceneId}" /> 
			<input type="hidden" name="linkageActionIds" id="linkageActionIds" value="${linkageActionIds}" />
		</form>

		<footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
		</section>
		
	</body>

<jsp:include page="../wrapper.suffix.jsp" />
<script src="${STATIC_URL}/plugins/sortable/jquery.sortable.js" type="text/javascript"></script>
<script src="${STATIC_URL}/modules/scene/js/manage.edit.device.js" type="text/javascript"></script>
