<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside>
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-8 m-b-xs">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-default action-refresh" title="刷新"><i class="fa fa-refresh"></i></button>                           
                        </div>
                        <a href="${BASE_URL}/deviceCategoryBrand/add" class="btn btn-sm btn-default load-content"><i class="fa fa-plus"></i>添加</a>
                    </div>
                    <form action="${BASE_URL}/deviceCategoryBrand/page" id="searchForm">
	                    <div class="col-sm-4 m-b-xs">
	                        <div class="input-group">
	                            <input type="text" name="key" class="input-sm form-control" placeholder="搜索条件" />
	                            <span class="input-group-btn">
	                                <button class="btn btn-sm btn-default action-refresh" type="button">搜索</button>
	                            </span>
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
<script src="${STATIC_URL}/modules/device/js/categoryBrand.index.js" type="text/javascript"></script>