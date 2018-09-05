<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp" />
<link href="${STATIC_URL}/plugins/datepicker/css/datepicker3.css" rel="stylesheet" type="text/css"/>

<section class="hbox stretch">
	<aside>
		<section class="vbox">
			<header class="header bg-white b-b clearfix bmViewHeader">
				<div class="row m-t-sm">
					<form action="${BASE_URL}/logUserAction/page" id="searchForm">
						<!-- 第一行div，放新增按钮 -->
						<div class="col-sm-12 m-b-xs form-inline text-left p-left-2">
							<div class="btn-group">
								<button type="button"
									class="btn btn-sm btn-default action-refresh" title="刷新">
									<i class="fa fa-refresh"></i>
								</button>
							</div>
							<a href="${BASE_URL}/logUserAction/add"
								class="btn btn-sm btn-default load-content"><i
								class="fa fa-plus"></i>添加</a>
						</div>

						<!-- 第二行div，放查询条件，每行放3到4个条件 -->
						<div class="col-sm-12 m-b-xs form-inline text-left p-left-2">
							<div class="col-sm-3 m-b-xs">
								<div class="bm-nowrap">
									请求url地址：<input type="text" name="actionName"
										onkeydown="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}"
										class="input-sm form-control">
								</div>
							</div>
							<div class="col-sm-3 m-b-xs">
								<div class="bm-nowrap">
									请求类型： <select id="logType" name="logType"
										class="input-sm form-control bm-select">
										<option value="">全部</option>
										<option value="1">后台日志</option>
										<option value="2">手机端日志</option>
										<option value="3">web端日志</option>
									</select>
								</div>
							</div>
							<div class="col-sm-3 m-b-xs">
								<div class="bm-nowrap">
									用户id：<input type="text" name="userid"
										onkeydown="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}"
										class="input-sm form-control">
								</div>
							</div>
						</div>
				        <!-- 第二行div，放查询条件，每行放3到4个条件 -->
                        <div class="col-sm-12 m-b-xs form-inline text-left p-left-2">   					     					
	      					<div class="col-sm-3 m-b-xs">
	      						<div class="bm-nowrap">
                    			纪录时间：
                                <input type="text" value="" class="input-sm input-s form-control" name="startPayTime" id="startDate" placeholder="开始时间" readonly="readonly"> - 
                                <input type="text" value="" class="input-sm input-s form-control" name="endPayTime" id="endDate" placeholder="结束时间" readonly="readonly"   >
                            	</div> 
	      					</div>       					
	      				</div>
						<!-- 最后一行div，放查，每行放3到4个条件 -->
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
<script src="${STATIC_URL}/plugins/datepicker/js/bootstrap-datepicker.js"></script>
<script src="${STATIC_URL}/plugins/datepicker/js/locales/bootstrap-datepicker.zh-CN.time.js" charset="UTF-8"></script>
<script src="${STATIC_URL}/modules/log/js/userAction.index.js" type="text/javascript"></script>