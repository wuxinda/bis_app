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
	                <header class="panel-heading">档案申请审批表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">申请审批id：</td>
                                <td class="col-sm-4">${amsArchivesAudit.auditId}</td>
                                <td class="col-sm-2">档案id：</td>
                                <td class="col-sm-4">${amsArchivesAudit.archivesId}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">用户id：</td>
                                <td class="col-sm-4">${amsArchivesAudit.userId}</td>
								<td class="col-sm-2">操作类型：</td>
                                <td class="col-sm-4">
									<c:if test="${amsArchivesAudit.type == 0}">
									入库申请
									</c:if>
									<c:if test="${amsArchivesAudit.type == 1}">
									借阅申请		
									</c:if>
								</td>
                            </tr>
                            <tr>  
								<td class="col-sm-2">审核状态：</td>
                                <td class="col-sm-4">
									<c:if test="${amsArchivesAudit.status == 0}">
									待审批
									</c:if>
									<c:if test="${amsArchivesAudit.status == 1}">
									审核通过
									</c:if>
									<c:if test="${amsArchivesAudit.status == 2}">
									审核拒绝		
									</c:if>
								</td>
                                <td class="col-sm-2">备注：</td>
                                <td class="col-sm-4">${amsArchivesAudit.remark}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">创建人：</td>
                                <td class="col-sm-4">${amsArchivesAudit.creator}</td>
                                <td class="col-sm-2">创建时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${amsArchivesAudit.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">修改人：</td>
                                <td class="col-sm-4">${amsArchivesAudit.modifier}</td>
                                <td class="col-sm-2">最后一次更新时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${amsArchivesAudit.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
<script src="${STATIC_URL}/modules/ams/js/archivesAudit.detail.js" type="text/javascript"></script>
