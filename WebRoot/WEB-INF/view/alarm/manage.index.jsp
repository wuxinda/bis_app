<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp" />

<section class="hbox stretch">
	<aside>
		<section class="vbox">
			<header class="header bg-white b-b clearfix bmViewHeader">
				<div class="row m-t-sm">
					<form action="${BASE_URL}/alarmManage/page" id="searchForm">
						<div class="col-sm-12 m-b-xs form-inline text-left p-left-2">
							<div class="btn-group">
								<button type="button"
									class="btn btn-sm btn-default action-refresh" title="刷新">
									<i class="fa fa-refresh"></i>
								</button>
							</div>
							<a href="${BASE_URL}/alarmManage/add"
								class="btn btn-sm btn-default load-content"><i
								class="fa fa-plus"></i>添加</a>
						</div>
						<div class="form-inline text-right p-right-0">
							<div class="col-sm-4 m-b-xs">
								<div class="bm-nowrap">
									设备分类： <select name="categoryId" id="categoryId"
										class="input-sm form-control bm-select">
										<option value="">全部</option>
										<c:forEach items="${categoryId}" var="v">
											<option value="${v.categoryId}"
												<c:if test="${ v.categoryId == alarmManage.categoryId}">selected</c:if>>${v.name}</option>
										</c:forEach>
									</select>
								</div>
						  </div>
						  <div class="col-sm-4 m-b-xs">
								<div class="bm-nowrap">
									所属库房： <select name="storeId" id="storeId"
										class="input-sm form-control bm-select">
										<option value="">全部</option>
										<c:forEach items="${wmsStore}" var="store">
											<option value="${store.storeId}"
												<c:if test="${ store.storeId == alarmManage.storeId}">selected</c:if>>${store.name}</option>
										</c:forEach>
									</select>
								</div>
						  </div>
						  <div class="col-sm-4 m-b-xs">
								<div class="bm-nowrap">
									所属库区： 
									<select class="input-sm form-control bm-select" name="stroreAreaId"
										id="stroreAreaId">
										<option value="">全部</option>
										<c:if test="${wmsStoreArea != null}">
											<c:forEach items="${wmsStoreArea}" var="v">
												<option value="${v.stroreAreaId}"
													<c:if test="${ v.stroreAreaId == alarmManage.stroreAreaId}">selected</c:if>>${v.name}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
						   </div>
						   <div class="col-sm-4 m-b-xs">
								<div class="bm-nowrap"> 
									报警级别： <select id="level" name="level"
										class="input-sm form-control bm-select">
										<option value="">全部</option>
										<option value="0">低</option>
										<option value="1">中</option>
										<option value="1">高</option>
									</select>
								</div>
						    </div>
							<div class="col-sm-4 m-b-xs">
								<div class="bm-nowrap">	
									处理状态： <select id="status" name="status"
										class="input-sm form-control bm-select">
										<option value="">全部</option>
										<option value="0">未处理</option>
										<option value="1">已处理</option>
									</select>
								</div>
							</div>
						</div>
						
						
							<div class="col-sm-12 m-b-xs form-inline text-right p-right-2">
							<div class="bm-nowrap">
								<button class="btn btn-sm btn-default action-refresh"
									type="button">搜索</button>

							</div>
						</div>
						</form>
				</div>
			</header>
			<%@include file="../page.jsp"%>
		</section>
	</aside>
</section>

<jsp:include page="../wrapper.suffix.jsp" />
<script src="${STATIC_URL}/modules/alarm/js/manage.index.js"
	type="text/javascript"></script>