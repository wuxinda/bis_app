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
	                <header class="panel-heading">档案操作纪录表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">主键id：</td>
                                <td class="col-sm-4">${amsArchivesActlog.actlogId}</td>
                                <td class="col-sm-2">档案id：</td>
                                <td class="col-sm-4">${amsArchivesActlog.archivesId}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">用户id：</td>
                                <td class="col-sm-4">${amsArchivesActlog.userId}</td>
								<td class="col-sm-2">操作类型：</td>
                                <td class="col-sm-4">
									<c:if test="${amsArchivesActlog.type == 0}">
									入库
									</c:if>
									<c:if test="${amsArchivesActlog.type == 1}">
									借阅
									</c:if>
									<c:if test="${amsArchivesActlog.type == 2}">
									上架
									</c:if>
									<c:if test="${amsArchivesActlog.type == 3}">
									归还		
									</c:if>
								</td>
                            </tr>
                            <tr>  
								<td class="col-sm-2">审核状态：</td>
                                <td class="col-sm-4">
									<c:if test="${amsArchivesActlog.status == 0}">
									待审批
									</c:if>
									<c:if test="${amsArchivesActlog.status == 1}">
									审核通过
									</c:if>
									<c:if test="${amsArchivesActlog.status == 2}">
									审核拒绝		
									</c:if>
								</td>
                                <td class="col-sm-2">创建人：</td>
                                <td class="col-sm-4">${amsArchivesActlog.creator}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">创建时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${amsArchivesActlog.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td class="col-sm-2">修改人：</td>
                                <td class="col-sm-4">${amsArchivesActlog.modifier}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">库房id：</td>
                                <td class="col-sm-4">${amsArchivesActlog.wmsstoreId}</td>
                                <td class="col-sm-2">库区id：</td>
                                <td class="col-sm-4">${amsArchivesActlog.storeareaId}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">最后一次更新时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${amsArchivesActlog.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
<script src="${STATIC_URL}/modules/ams/js/archivesActlog.detail.js" type="text/javascript"></script>
