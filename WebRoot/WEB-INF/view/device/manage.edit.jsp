<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp" />
<script type="text/javascript">
var auditStatus='${spu.auditStatus}';
var auditStatuss='${spu.auditStatus}';
</script>
<section class="hbox stretch"> 
<aside class="aside-md bg-white b-r" id="subNav">
		<section class="vbox">
			<header class="b-b header">
				<p class="h4">
					<c:choose>
						<c:when test="${contentId != 0 }">编辑设备</c:when>
						<c:otherwise>添加设备</c:otherwise>
					</c:choose>
				</p>
			</header>
			
			<section class="scrollable w-f">
				<ul class="nav nav-pills nav-stacked no-radius">
					<li class="b-b m-t-none-reset nav-map active" id="nav_base"><a href="javascript:;"> <i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i> <i class="fa fa-fw fa-ellipsis-v"></i> 基本信息 </a></li>
					<li class="b-b m-t-none-reset nav-map" id="nav_link"><a href="javascript:;"> <i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i> <i class="fa fa-fw fa-ellipsis-v"></i> 连接配置 </a></li>
					<c:choose>
						<c:when test="${deviceManage.categoryId == 4 }">
						     <li class="b-b m-t-none-reset nav-map" id="nav_link_alarm"><a href="javascript:;"> <i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i> <i class="fa fa-fw fa-ellipsis-v"></i> 报警配置 </a></li>
						</c:when>
						<c:when test="${categoryId == 4 }">
						     <li class="b-b m-t-none-reset nav-map" id="nav_link_alarm"><a href="javascript:;"> <i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i> <i class="fa fa-fw fa-ellipsis-v"></i> 报警配置 </a></li>
						</c:when>
					</c:choose>
					
					</ul>
			</section>
		</section>
	</aside>

	<aside class="bg-white">
		<section class="vbox">
			<header class="header b-b b-t bg-white-only">
				<div class="m-t-sm">
					<a href="#subNav" data-toggle="class:hide" class="btn btn-sm btn-default active btn-nav-goods" btn_nav_goods_index="0"> <i class="fa fa-caret-right text fa-lg"></i> <i class="fa fa-caret-left text-active fa-lg"></i> </a>
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
				</div>
			</header>

			<section class="scrollable w-f">
                <form class="form-horizontal h-100" id="edit_form" action=<c:choose> <c:when test="${deviceManage!=null}">"${BASE_URL}/deviceManage/edit"</c:when><c:otherwise>"${BASE_URL}/deviceManage/add"</c:otherwise></c:choose> method="post" enctype="multipart/form-data">
					<!-- 基本信息 @start -->
					<jsp:include page="manage.edit.base.jsp" />
					<!-- 基本信息 @end -->
					<!-- 连接配置 @start -->
					<jsp:include page="manage.edit.link.jsp" />
					<!-- 连接配置 @end -->
				    <input type="hidden" id="deviceId" name="deviceId" value="${deviceManage.deviceId}" />
                    <input type="hidden" id="categoryId" name="categoryId" value="${deviceManage.categoryId}" />
                    <input type="hidden" id="brandId" name="brandId" value="${deviceManage.brandId}" />
                    <input type="hidden" id="storeId" name="storeId" value="${deviceManage.storeId}" />
                    <!-- 连接报警配置 @start -->
					<jsp:include page="manage.edit.link.alarm.jsp" />
					<!-- 连接报警配置 @end -->
				</form>
			</section>
			<footer class="footer b-t bg-white-only">
				<div class="m-t-sm">
					<a href="#subNav" data-toggle="class:hide" class="btn btn-sm btn-default active btn-nav-goods" btn_nav_goods_index="1"> <i class="fa fa-caret-right text fa-lg"></i> <i class="fa fa-caret-left text-active fa-lg"></i> </a>
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
				</div>
			</footer>
		</section>
	</aside>
</section>
<jsp:include page="../wrapper.suffix.jsp" />
<script src="${STATIC_URL}/modules/device/js/manage.edit.js" type="text/javascript"></script>

