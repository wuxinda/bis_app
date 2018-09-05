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
	                <header class="panel-heading">基本信息</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">设备ID：</td>
                                <td class="col-sm-4">${deviceManage.deviceId}</td>
                                <td class="col-sm-2">所属分类：</td>
                                <td class="col-sm-4">${deviceManage.categoryName}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">所属品牌：</td>
                                <td class="col-sm-4">${deviceManage.brandName}</td>
                                <td class="col-sm-2">所属库房：</td>
                                <td class="col-sm-4">${deviceManage.storeName}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">设备名称：</td>
                                <td class="col-sm-4">${deviceManage.name}</td>
                                <td class="col-sm-2">序号：</td>
                                <td class="col-sm-4">${deviceManage.sortOrder}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">备注：</td>
                                <td class="col-sm-4">${deviceManage.remark}</td>
                                <td class="col-sm-2">创建人：</td>
                                <td class="col-sm-4">${deviceManage.creator}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">创建时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${deviceManage.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td class="col-sm-2">修改人：</td>
                                <td class="col-sm-4">${deviceManage.modifier}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">最后一次更新时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${deviceManage.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td class="col-sm-2">状态：</td>
                                <td class="col-sm-4">
									<c:if test="${deviceManage.status == 0}">
									停用
									</c:if>
									<c:if test="${deviceManage.status == 1}">
									在用		
									</c:if>
								</td>
                            </tr>
                         
                        </tbody>
                    </table>
                </section>
                <section class="panel panel-default portlet-item">
	                <header class="panel-heading">连接属性</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        <c:forEach items="${linkList}" var="v" varStatus="obj">
                            <tr>  
                                <td class="col-sm-2">${v.name}：</td>
                                <td class="col-sm-4">${v.val}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </section>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/device/js/manage.detail.js" type="text/javascript"></script>
