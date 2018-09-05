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
	                <header class="panel-heading">报警类型详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">：</td>
                                <td class="col-sm-4">${alarmType.alarmTypeId}</td>
                                <td class="col-sm-2">报警名称：</td>
                                <td class="col-sm-4">${alarmType.alarmTypeName}</td>
                            </tr>
                            <tr>  
								<td class="col-sm-2">报警级别：</td>
                                <td class="col-sm-4">
									<c:if test="${alarmType.alarmLevel == 0}">
									低
									</c:if>
									<c:if test="${alarmType.alarmLevel == 1}">
									中
									</c:if>
									<c:if test="${alarmType.alarmLevel == 2}">
									高
									</c:if>
								</td>
                                <td class="col-sm-2">排序号：</td>
                                <td class="col-sm-4">${alarmType.sortOrder}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">备注：</td>
                                <td class="col-sm-4">${alarmType.remark}</td>
                                <td class="col-sm-2">创建人：</td>
                                <td class="col-sm-4">${alarmType.creator}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">创建时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${alarmType.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td class="col-sm-2">修改人：</td>
                                <td class="col-sm-4">${alarmType.modifier}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">最后修改时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${alarmType.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
<script src="${STATIC_URL}/modules/alarm/js/type.detail.js" type="text/javascript"></script>
