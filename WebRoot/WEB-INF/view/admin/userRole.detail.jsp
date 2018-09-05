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
	                <header class="panel-heading">用户角色表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">主键：</td>
                                <td class="col-sm-4">${adminUserRole.id}</td>
                                <td class="col-sm-2">用户id：</td>
                                <td class="col-sm-4">${adminUserRole.userId}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">角色id：</td>
                                <td class="col-sm-4">${adminUserRole.roleId}</td>
                                <td class="col-sm-2">创建人：</td>
                                <td class="col-sm-4">${adminUserRole.creator}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">创建时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${adminUserRole.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td class="col-sm-2">修改人：</td>
                                <td class="col-sm-4">${adminUserRole.modifier}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">修改时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${adminUserRole.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
								<td class="col-sm-2">用户类型：</td>
                                <td class="col-sm-4">
									<c:if test="${adminUserRole.userType == 1}">
									平台
									</c:if>
									<c:if test="${adminUserRole.userType == 2}">
									商户
									</c:if>
								</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">商户id：</td>
                                <td class="col-sm-4">${adminUserRole.merchantId}</td>
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
<script src="${STATIC_URL}/modules/admin/js/userRole.detail.js" type="text/javascript"></script>
