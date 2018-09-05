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
	                <header class="panel-heading">请求url，对应的请求名，用于通过url显示用户操作详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">主键：</td>
                                <td class="col-sm-4">${systemUrl.id}</td>
                                <td class="col-sm-2">请求类型：</td>
                                <td class="col-sm-4">
                                <c:if test="${systemUrl.urlType == '1'}">
								 后台url
							    </c:if>
							    <c:if test="${systemUrl.urlType == '2'}">
								 接口url
								</c:if>
								<c:if test="${systemUrl.urlType == '3'}">
								web端url
							    </c:if>
                                </td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">请求url：</td>
                                <td class="col-sm-4">${systemUrl.url}</td>
                                <td class="col-sm-2">请求名：</td>
                                <td class="col-sm-4">${systemUrl.name}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">请求url：</td>
                                <td class="col-sm-4">
                                <c:if test="${systemUrl.status == '0'}">
								 停用
							    </c:if>
							    <c:if test="${systemUrl.status == '1'}">
								 在用
								</c:if>
                                </td>
                                <td class="col-sm-2"></td>
                                <td class="col-sm-4"></td>
                            </tr>
                         
                        </tbody>
                    </table>
                </section>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/system/js/url.detail.js" type="text/javascript"></script>
