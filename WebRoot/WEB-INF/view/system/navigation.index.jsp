<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="vbox">
    <header class="header bg-white b-b clearfix">
        <div class="row m-t-sm">
            <div class="col-sm-8 m-b-xs">
                <div class="btn-group">
                    <button type="button" class="btn btn-sm btn-default action-refresh" title="Refresh"><i class="fa fa-refresh"></i></button>
                </div>
                <a href="${BASE_URL}/systemNavigation/add" data-toggle="ajaxModal" class="btn btn-sm btn-default" id="modal_edit"><i class="fa fa-plus"></i> 添加</a>
            </div>
            
            <form  class="form-inline" action="${BASE_URL}/systemNavigation/page" id="searchForm">
            <div class="col-sm-4 m-b-xs">
	              <select class="form-control" name="navigationId">
	                  <option value="">所有父级菜单</option>
	                  <c:if test="${navigations != null}">
	                  <c:forEach items="${navigations}" var="v">
	                  <option value="${v.navigationId}" <c:if test="${v.navigationId == navigationIdNow}"> selected</c:if>>${v.title}</option>
	                  </c:forEach>
	                  </c:if>
	              </select>
	          </div>
	          </form>
            </div>
    </header>
        
    <section class="scrollable wrapper">
        <section class="panel panel-default">
            <div class="table-responsive">
                <table class="table table-striped m-b-none datagrid" id="navigation_listing">
                    <thead>
                    </thead>
                </table>
            </div>
        </section>
    </section>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/system/js/navigation.index.js"></script>