<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside>
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-4 m-b-xs">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-default action-refresh" title="刷新"><i class="fa fa-refresh"></i></button>                           
                        </div>
                        <a href="${BASE_URL}/adminMenu/add" class="btn btn-sm btn-default load-content"><i class="fa fa-plus"></i>添加</a>
                    </div>
                    <form class="form-inline" action="${BASE_URL}/adminMenu/page" id="searchForm">
						
						<!-- <div class="col-sm-3 m-b-xs"></div> -->
						
	                    <!-- <div class="col-sm-3 m-b-xs">
	                    	<div class="form-group">
								菜单类型：
									<select class="form-control" name="grade">
										<option value="0">请选择</option>
										<option value="1">一级菜单</option>
										<option value="2">二级菜单</option>
									</select> 
							</div>
		        		</div> -->
		        		
	                    <div class="col-sm-7 m-b-xs text-right p-right-0">
	                    	<div class="form-group">
								父级菜单：
									<select class="form-control" name="menuId">
										<option value="0">请选择</option>
										<c:if test="${firstMenus != null}">
											<c:forEach items="${firstMenus}" var="firstMenu">
												<option value="${firstMenu.menuId}"
													<c:if test="${firstMenu.menuId == menuIdNow}">selected="selected"</c:if>>
													${firstMenu.name}
												</option>
											</c:forEach>
										</c:if>
									</select> 
							</div>
		        		</div>
		        		
	                    <div class="col-sm-1 m-b-xs">
		                    	<button class="btn btn-sm btn-default action-refresh" type="button">搜索</button>
		        		</div>
						
	                </form>
                </div>
            </header>
            
			<%@include file = "../page.jsp" %>
            
        </section>
    </aside>
</section>
            
<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/admin/js/menu.index.js" type="text/javascript"></script>