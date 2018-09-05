<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp" />

<section class="hbox stretch">
	<aside>
		<section class="vbox">
			<header class="header bg-white b-b clearfix bmViewHeader">
				<div class="row m-t-sm">
					<form action="${BASE_URL}/amsArchives/page" id="searchForm">
						<div class="col-sm-12 m-b-xs form-inline text-left p-left-2">
							<div class="btn-group">
								<button type="button"
									class="btn btn-sm btn-default action-refresh" title="刷新">
									<i class="fa fa-refresh"></i>
								</button>
							</div>
							<a href="${BASE_URL}/amsArchives/add"
								class="btn btn-sm btn-default load-content"><i
								class="fa fa-plus"></i>添加</a>
						</div>

						<!-- 第二行div，放查询条件，每行放3到4个条件 -->
						<div class="form-inline text-right p-right-0">
							<div class="col-sm-4 m-b-xs">
								<div class="bm-nowrap">
									正题名：<input type="text" name="title"
										onkeydown="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}"
										class="input-sm form-control">
								</div>
							</div>
							<div class="col-sm-4 m-b-xs">
								<div class="bm-nowrap">
									档案类型： <select name="archivesTypeId" id="archivesTypeId"
										class="input-sm form-control bm-select">
										<option value="">全部</option>
										<c:forEach items="${AmsArchivesType}" var="v">
											<option value="${v.archivesTypeId}"
												<c:if test="${ v.archivesTypeId == amsArchives.archivesTypeId}">selected</c:if>>${v.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>							
							<div class="col-sm-4 m-b-xs">
								<div class="bm-nowrap">
									所属库房： <select name="stroreId" id="stroreId"
										class="input-sm form-control bm-select">
										<option value="">全部</option>
										<c:forEach items="${wmsStore}" var="store">
											<option value="${store.storeId}"
												<c:if test="${ store.storeId == amsArchives.storeId}">selected</c:if>>${store.name}</option>
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
													<c:if test="${ v.stroreAreaId == amsArchives.stroreAreaId}">selected</c:if>>${v.name}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
							</div>
							<div class="col-sm-4 m-b-xs">
								<div class="bm-nowrap">
									在库状态： <select id="inflag" name="inflag"
										class="input-sm form-control bm-select">
										<option value="">全部</option>
										<option value="0">在库</option>
										<option value="1">出库</option>
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
<script src="${STATIC_URL}/modules/ams/js/archives.index.js"
	type="text/javascript"></script>