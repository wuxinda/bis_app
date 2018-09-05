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
	                <header class="panel-heading">地区表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">地区id：</td>
                                <td class="col-sm-4">${systemRegion.id}</td>
                                <td class="col-sm-2">地区名称：</td>
                                <td class="col-sm-4">${systemRegion.name}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">父级地区id：</td>
                                <td class="col-sm-4">${systemRegion.pid}</td>
								<td class="col-sm-2">地区级别：</td>
                                <td class="col-sm-4">
									<c:if test="${systemRegion.grade == 1}">
									省
									</c:if>
									<c:if test="${systemRegion.grade == 2}">
									市
									</c:if>
									<c:if test="${systemRegion.grade == 3}">
									区县
									</c:if>
								</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">序号：</td>
                                <td class="col-sm-4">${systemRegion.sortOrder}</td>
								<td class="col-sm-2">状态：</td>
                                <td class="col-sm-4">
									<c:if test="${systemRegion.status == 0}">
									未使用
									</c:if>
									<c:if test="${systemRegion.status == 1}">
									使用
									</c:if>
								</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">英文名全称：</td>
                                <td class="col-sm-4">${systemRegion.enName}</td>
                                <td class="col-sm-2">英文名首字母：</td>
                                <td class="col-sm-4">${systemRegion.initial}</td>
                            </tr>
                         
                        </tbody>
                    </table>
                </section>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/system/js/region.detail.js" type="text/javascript"></script>
