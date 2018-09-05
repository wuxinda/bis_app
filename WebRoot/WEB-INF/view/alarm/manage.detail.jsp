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
	                <header class="panel-heading">报警管理表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">主键ID：</td>
                                <td class="col-sm-4">${alarmManage.alarmId}</td>
                                <td class="col-sm-2">设备分类id：</td>
                                <td class="col-sm-4">${alarmManage.categoryId}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">设备id：</td>
                                <td class="col-sm-4">${alarmManage.deviceId}</td>
                                <td class="col-sm-2">所属库房id：</td>
                                <td class="col-sm-4">${alarmManage.storeId}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">所属库区id：</td>
                                <td class="col-sm-4">${alarmManage.stroreAreaId}</td>
                                <td class="col-sm-2">设备名称：</td>
                                <td class="col-sm-4">${alarmManage.deviceName}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">序号：</td>
                                <td class="col-sm-4">${alarmManage.sortOrder}</td>
                                <td class="col-sm-2">报警级别：</td>
                                <td class="col-sm-4">${alarmManage.level}</td>
                            </tr>
                            <tr>  
								<td class="col-sm-2">状态：</td>
                                <td class="col-sm-4">
									<c:if test="${alarmManage.status == 0}">
									未处理
									</c:if>
									<c:if test="${alarmManage.status == 1}">
									已处理		
									</c:if>
								</td>
                                <td class="col-sm-2">报警原因：</td>
                                <td class="col-sm-4">${alarmManage.remark}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">创建人：</td>
                                <td class="col-sm-4">${alarmManage.creator}</td>
                                <td class="col-sm-2">创建时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${alarmManage.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">修改人：</td>
                                <td class="col-sm-4">${alarmManage.modifier}</td>
                                <td class="col-sm-2">最后一次更新时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${alarmManage.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
<script src="${STATIC_URL}/modules/alarm/js/manage.detail.js" type="text/javascript"></script>
