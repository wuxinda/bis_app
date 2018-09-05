<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp" />

<section class="hbox stretch">
	
	<aside>
		<section class="vbox">
			<header class="header bg-white b-b clearfix bmViewHeader">
				<div class="row m-t-sm">
					<form class="form-inline" action="${BASE_URL}/deviceBrand/bindCategory" id="searchForm">

						<!-- 第一行div,放刷新及新增按钮及唯一搜索条件 -->
						<div class="col-sm-12 m-b-xs form-inline text-left p-left-0">
							<div class="col-sm-4 m-b-xs bm-nowrap">
								<button type="button" class="btn btn-sm btn-default action-refresh" title="刷新">
									<i class="fa fa-refresh"></i>
								</button>
							</div>
							<!-- <div class="col-sm-2 m-b-xs">
                        		
                        运营三级分类名称:<input type="text" name="key" class="input-sm form-control" placeholder="模糊" />
                        <span><button class="btn btn-sm btn-default action-refresh" type="button">查询</button></span>
                    </div>
                </div> -->

							<div class="col-sm-7 m-b-xs bm-nowrap text-right p-right-0" id="search">
								<input type="text" name="key" class="input-sm form-control" placeholder="设备名称" />
								<button class="btn btn-sm btn-default" id="action_search" type="button">搜索</button>
							</div>
							<div class="col-sm-2 m-b-xs bm-nowrap"></div>
						</div>
					</form>
				</div>
			</header>

			<section class="scrollable wrapper w-f">
				<form class="form-horizontal" id="edit_form" action="${BASE_URL}/deviceBrand/bindCategory" method="post">
					<%-- <div class="form-group">
                        <label for="labelName" class="col-sm-2 control-label">分类名称</label>
                        <div class="col-sm-4">
                            <p class="form-control-static">${category.name}</p>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div> --%>

					<div class="edit-map" id="edit_property"></div>
					<input type="hidden" id="brandId" name="brandId" value="${brandId}" /> <input type="hidden" name="categoryIds" value="${categoryIds}" />
				</form>
			</section>

			<footer class="footer b-t bg-white-only">
				<div class="m-t-sm">
					<button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
					<button type="button" id="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
					<span id="edit_notice"></span>
				</div>
			</footer>
			<%@include file="../page.jsp"%>
		</section>
	</aside>
	<!-- 已选中的分类 -->
	<aside class="bg-light lter aside-md" id="selected_property">
		<section class="vbox">
			<header class="b-b header" style="margin-top:9px">
			
				<p class="h4">
					已选属性<font class="text-sm" >（可拖拽排序）</font>
				</p>
			</header>
			<section class="scrollable w-f">
				<ul class="nav nav-stacked list-group gutter list-group-lg list-group-sp sortable">
					<c:forEach items="${categories}" var="v">
						<li class="b-b m-t-none-reset" id="li_property_${v.categoryId}" property_id="${v.categoryId}" draggable="true"><a href="javascript:;"> <i title="移除该属性" class="fa fa-times pull-right m-t-xs fa-remove-brand"></i> <i class="fa fa-fw fa-ellipsis-v"></i><font class="property-name">${v.name}</font> </a>
						</li>
					</c:forEach>
				</ul>
			</section>
		</section>
	</aside>
</section>

<jsp:include page="../wrapper.suffix.jsp" />
<script src="${STATIC_URL}/plugins/sortable/jquery.sortable.js" type="text/javascript"></script>
<script src="${STATIC_URL}/modules/device/js/brand.bindCategory.js" type="text/javascript"></script>