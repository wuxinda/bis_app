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
	                <header class="panel-heading">用户行为日志表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">日志id：</td>
                                <td class="col-sm-4">${logUserAction.logId}</td>
								<td class="col-sm-2">日志类型：</td>
                                <td class="col-sm-4">
									<c:if test="${logUserAction.logType == 1}">
									后台日志
									</c:if>
									<c:if test="${logUserAction.logType == 2}">
									手机端日志
									</c:if>
									<c:if test="${logUserAction.logType == 3}">
									web端日志
									</c:if>
								</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">用户id：</td>
                                <td class="col-sm-4">${logUserAction.userid}</td>
                                <td class="col-sm-2">请求url：</td>
                                <td class="col-sm-4">${logUserAction.url}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">请求参数：</td>
                                <td class="col-sm-4">${logUserAction.param}</td>
                                <td class="col-sm-2">用户ip：</td>
                                <td class="col-sm-4">${logUserAction.ip}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">日志记录时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${logUserAction.logTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
<script src="${STATIC_URL}/modules/log/js/userAction.detail.js" type="text/javascript"></script>
