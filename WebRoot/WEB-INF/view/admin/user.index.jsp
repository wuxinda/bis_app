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
                        <a href="${BASE_URL}/adminUser/add?userType=0" class="btn btn-sm btn-default load-content hide bmpms-add"><i class="fa fa-plus"></i>添加后台用户</a>
                        <a href="${BASE_URL}/adminUser/add?userType=1" class="btn btn-sm btn-default load-content hide bmpms-add"><i class="fa fa-plus"></i>添加领导者端用户</a>
                        <a href="${BASE_URL}/adminUser/add?userType=2" class="btn btn-sm btn-default load-content hide bmpms-add"><i class="fa fa-plus"></i>添加操作者端用户</a>
                    </div>
                    <form action="${BASE_URL}/adminUser/page" id="searchForm">
	                    <div class="col-sm-4 m-b-xs">
	                        <div class="input-group">
	                            <input type="text" name="key" class="input-sm form-control" placeholder="搜索条件:名称或联系电话" />
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
<script src="${STATIC_URL}/modules/admin/js/user.index.js" type="text/javascript"></script>