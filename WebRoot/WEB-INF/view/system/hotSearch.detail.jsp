<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="bg-white">
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="m-t-sm">
                    <a href="#subNav" data-toggle="class:hide" class="btn btn-sm btn-default active btn-nav-goods" btn_nav_goods_index="0">
                        <i class="fa fa-caret-right text fa-lg"></i>
                        <i class="fa fa-caret-left text-active fa-lg"></i>
                    </a>
                        <a href="javascript:;" class="btn btn-sm btn-default" id="button_cancel"><i class="fa fa-reply"></i> 返回</a>
                </div>
            </header>
              
            <section class="scrollable wrapper">
                <section class="panel panel-default portlet-item">
	                <header class="panel-heading">热门搜索表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">主键：</td>
                                <td class="col-sm-4">${systemHotSearch.id}</td>
								<td class="col-sm-2">搜索类型：</td>
                                <td class="col-sm-4">
									<c:if test="${systemHotSearch.type == 0}">
									首页
									</c:if>
									<c:if test="${systemHotSearch.type == 1}">
									分期专区
									</c:if>
									<c:if test="${systemHotSearch.type == 2}">
									积分专区
									</c:if>
									<c:if test="${systemHotSearch.type == 3}">
									权益专区
									</c:if>
								</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">搜索关键字：</td>
                                <td class="col-sm-4">${systemHotSearch.searchKey}</td>
                                <td class="col-sm-2">搜索次数：</td>
                                <td class="col-sm-4">${systemHotSearch.count}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">序号：</td>
                                <td class="col-sm-4">${systemHotSearch.sortOrder}</td>
								<td class="col-sm-2">状态：</td>
                                <td class="col-sm-4">
									<c:if test="${systemHotSearch.status == 0}">
									可用
									</c:if>
									<c:if test="${systemHotSearch.status == 1}">
									不可用
									</c:if>
								</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">创建人：</td>
                                <td class="col-sm-4">${systemHotSearch.creator}</td>
                                <td class="col-sm-2">创建时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${systemHotSearch.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">修改人：</td>
                                <td class="col-sm-4">${systemHotSearch.modifier}</td>
                                <td class="col-sm-2">修改时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${systemHotSearch.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                         
                        </tbody>
                    </table>
                </section>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/system/js/hotSearch.detail.js" type="text/javascript"></script>
