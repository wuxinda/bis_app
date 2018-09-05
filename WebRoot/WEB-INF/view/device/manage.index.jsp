<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside>
        <section class="vbox">
            <header class="header bg-white b-b clearfix bmViewHeader">
                <div class="row m-t-sm">
                    <form action="${BASE_URL}/deviceManage/page" id="searchForm" method="post">                  
					    <!-- 第一行div，放新增按钮 -->
                    	<div class="col-sm-12 m-b-xs form-inline text-left p-left-2">
							<div class="btn-group">
								<button type="button" class="btn btn-sm btn-default action-refresh" title="刷新"><i class="fa fa-refresh"></i></button>
							</div>
                        <a href="${BASE_URL}/deviceManage/editCategory" class="btn btn-sm btn-default load-content"><i class="fa fa-plus"></i>添加</a>
                    	</div>
						
						<!-- 第二行div，放查询条件，每行放3到4个条件 -->
                        <div class="form-inline text-right p-right-0">
                        
	                        <div class="col-sm-3 m-b-xs">
	                            <div class="bm-nowrap">
	                                	设备名称：<input type="text" name="name" onkeydown="if(event.keyCode==13){event.keyCode=0;event.returnValue=false;}" class="input-sm form-control">
	                            </div>
	                        </div>
	                        <div class="col-sm-3 m-b-xs">
	                            <div class="bm-nowrap">
	                                	所属类型：
	                                    <select name="categoryId" id="categoryId" class="input-sm form-control bm-select">
									        <option value="">全部</option>
										<c:forEach items="${deviceCategory}" var="category">
											<option value="${category.categoryId}"
												<c:if test="${ category.categoryId == deviceManage.categoryId}">selected</c:if>>${category.name}</option>
										</c:forEach>
									    </select>
	                            </div>
	                        </div>
	                        <div class="col-sm-3 m-b-xs">
	                            <div class="bm-nowrap">
	                                	所属库房：
	                                	<select name="storeId" id="storeId" class="input-sm form-control bm-select">
									        <option value="">全部</option>
										<c:forEach items="${wmsStore}" var="store">
											<option value="${store.storeId}"
												<c:if test="${ store.storeId == deviceManage.storeId}">selected</c:if>>${store.name}</option>
										</c:forEach>
									    </select>
	                            </div>
	                        </div>                  		                        
	      			<!-- 		<div class="col-sm-3 m-b-xs">
	      						<div class="bm-nowrap">
	                                	状态：
	                             <select id="jopStatus" name="jopStatus" class="input-sm form-control bm-select">
                                	<option value="">全部</option>
                                	<option value="0">停用</option>
                                	<option value="1">在用</option>
                                </select>
	                            </div>
	      					</div> -->
					
	      				</div>     				
	      				<!-- 最后一行div，放查，每行放3到4个条件 -->
		                <div class="col-sm-12 m-b-xs form-inline text-right p-right-2">
		              		<div class="bm-nowrap">
		                		<button class="btn btn-sm btn-default action-refresh" type="button" >搜索</button>
		                 	</div>
		             	</div>
                    </form>
                 </div>
            </header>
            
			<%@include file = "../page.jsp" %>
			
        </section>
    </aside>
</section>
            
<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/device/js/manage.index.js" type="text/javascript"></script>