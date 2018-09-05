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
	                <header class="panel-heading">详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">主键ID：</td>
                                <td class="col-sm-4">${rfidInty.rfidIntyId}</td>
                                <td class="col-sm-2">档案id：</td>
                                <td class="col-sm-4">${rfidInty.archiveId}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">档案号：</td>
                                <td class="col-sm-4">${rfidInty.archiveno}</td>
                                <td class="col-sm-2">档案标题：</td>
                                <td class="col-sm-4">${rfidInty.tittle}</td>
                            </tr>
                            <tr>  
								<td class="col-sm-2">盘点状态：</td>
                                <td class="col-sm-4">
									<c:if test="${rfidInty.type == 0}">
									在架
									</c:if>
									<c:if test="${rfidInty.type == 1}">
									离架
									</c:if>
									<c:if test="${rfidInty.type == 2}">
									错架
									</c:if>
								</td>
                                <td class="col-sm-2">存址：</td>
                                <td class="col-sm-4">${rfidInty.storeplace}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">用户id：</td>
                                <td class="col-sm-4">${rfidInty.userId}</td>
                                <td class="col-sm-2">盘点人员：</td>
                                <td class="col-sm-4">${rfidInty.username}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">创建人：</td>
                                <td class="col-sm-4">${rfidInty.creator}</td>
                                <td class="col-sm-2">创建时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${rfidInty.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">修改人：</td>
                                <td class="col-sm-4">${rfidInty.modifier}</td>
                                <td class="col-sm-2">最后一次更新时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${rfidInty.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">备注：</td>
                                <td class="col-sm-4">${rfidInty.remark}</td>
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
<script src="${STATIC_URL}/modules/rfid/js/inty.detail.js" type="text/javascript"></script>
