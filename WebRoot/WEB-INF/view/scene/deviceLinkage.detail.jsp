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
	                <header class="panel-heading">场景模式设备联动表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">主键ID：</td>
                                <td class="col-sm-4">${sceneDeviceLinkage.linkageId}</td>
                                <td class="col-sm-2">设备id：</td>
                                <td class="col-sm-4">${sceneDeviceLinkage.deviceId}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">场景id：</td>
                                <td class="col-sm-4">${sceneDeviceLinkage.sceneId}</td>
                                <td class="col-sm-2">设备操作属性id：</td>
                                <td class="col-sm-4">${sceneDeviceLinkage.actionpropertyId}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">执行间隔：</td>
                                <td class="col-sm-4">${sceneDeviceLinkage.execSec}</td>
                                <td class="col-sm-2">：</td>
                                <td class="col-sm-4">${sceneDeviceLinkage.sortOrder}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">创建人：</td>
                                <td class="col-sm-4">${sceneDeviceLinkage.creator}</td>
                                <td class="col-sm-2">创建时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${sceneDeviceLinkage.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">修改人：</td>
                                <td class="col-sm-4">${sceneDeviceLinkage.modifier}</td>
                                <td class="col-sm-2">最后一次更新时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${sceneDeviceLinkage.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
<script src="${STATIC_URL}/modules/scene/js/deviceLinkage.detail.js" type="text/javascript"></script>
