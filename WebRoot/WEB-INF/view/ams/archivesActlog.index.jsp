<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp" />

<section class="hbox stretch">
	<aside>
		<section class="vbox">
			<header class="header bg-white b-b clearfix bmViewHeader">
				<div class="row m-t-sm">
				<form action="${BASE_URL}/amsArchivesActlog/page" id="searchForm">
					<div class="col-sm-12 m-b-xs form-inline text-left p-left-2">
						<div class="btn-group">
							<button type="button"
								class="btn btn-sm btn-default action-refresh" title="刷新">
								<i class="fa fa-refresh"></i>
							</button>
						</div>
						<a href="${BASE_URL}/amsArchivesActlog/add"
							class="btn btn-sm btn-default load-content"><i
							class="fa fa-plus"></i>添加</a>
					</div>
						<!-- 第二行div，放查询条件，每行放3到4个条件 -->
						<div class="form-inline text-right p-right-0">
							<div class="col-sm-4 m-b-xs">
								<div class="bm-nowrap">
									操作类型： <select name="type" id="type"
										class="input-sm form-control bm-select">
										<option value="">全部</option>
										<option value="0">入库</option>
										<option value="1">借阅</option>
										<option value="2">上架</option>
										<option value="3">归还</option>
									</select>
								</div>
							</div>
							<div class="col-sm-4 m-b-xs">
								<div class="bm-nowrap">
									审核状态： <select name="status" id="status"
										class="input-sm form-control bm-select">
										<option value="">全部</option>
										<option value="0">待审批</option>
										<option value="1">审核通过</option>
										<option value="2">审核拒绝</option>
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
<script src="${STATIC_URL}/modules/ams/js/archivesActlog.index.js"
	type="text/javascript"></script>