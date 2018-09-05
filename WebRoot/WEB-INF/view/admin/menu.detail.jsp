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
	                <header class="panel-heading">菜单表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">菜单id：</td>
                                <td class="col-sm-4">${adminMenu.menuId}</td>
                                <td class="col-sm-2">菜单名称：</td>
                                <td class="col-sm-4">${adminMenu.name}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">父级菜单id：</td>
                                <td class="col-sm-4">${adminMenu.pid}</td>
								<td class="col-sm-2">菜单级别：</td>
                                <td class="col-sm-4">
									<c:if test="${adminMenu.grade == 1}">
									一级菜单
									</c:if>
									<c:if test="${adminMenu.grade == 2}">
									二级菜单
									</c:if>
								</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">链接：</td>
                                <td class="col-sm-4">${adminMenu.url}</td>
                                <td class="col-sm-2">icon图标：</td>
                                <td class="col-sm-4">${adminMenu.icon}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">icon背景：</td>
                                <td class="col-sm-4">${adminMenu.iconBg}</td>
                                <td class="col-sm-2">序号：</td>
                                <td class="col-sm-4">${adminMenu.sortOrder}</td>
                            </tr>
                            <tr>  
								<td class="col-sm-2">状态：</td>
                                <td class="col-sm-4">
									<c:if test="${adminMenu.status == 0}">
									不可用
									</c:if>
									<c:if test="${adminMenu.status == 1}">
									可用
									</c:if>
								</td>
                                <td class="col-sm-2">创建人：</td>
                                <td class="col-sm-4">${adminMenu.creator}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">创建时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${adminMenu.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td class="col-sm-2">修改人：</td>
                                <td class="col-sm-4">${adminMenu.modifier}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">修改时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${adminMenu.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
<script src="${STATIC_URL}/modules/admin/js/menu.detail.js" type="text/javascript"></script>
