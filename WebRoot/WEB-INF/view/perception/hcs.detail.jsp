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
	                <header class="panel-heading">温湿度感知数据收集表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">主键id：</td>
                                <td class="col-sm-4">${perceptionHcs.hcsId}</td>
                                <td class="col-sm-2">设备ID：</td>
                                <td class="col-sm-4">${perceptionHcs.deviceId}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">温度：</td>
                                <td class="col-sm-4">${perceptionHcs.temperature}</td>
                                <td class="col-sm-2">湿度：</td>
                                <td class="col-sm-4">${perceptionHcs.humidity}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">空调状态：</td>
                                <td class="col-sm-4">${perceptionHcs.airStatus}</td>
                                <td class="col-sm-2">除湿状态：</td>
                                <td class="col-sm-4">${perceptionHcs.dryingStatus}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">增湿状态：</td>
                                <td class="col-sm-4">${perceptionHcs.wettingStatus}</td>
                                <td class="col-sm-2">通风状态：</td>
                                <td class="col-sm-4">${perceptionHcs.ventilationStatus}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">净化状态：</td>
                                <td class="col-sm-4">${perceptionHcs.cleansingStatus}</td>
                                <td class="col-sm-2">警告状态：</td>
                                <td class="col-sm-4">${perceptionHcs.warningStatus}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">采集时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${perceptionHcs.collectDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td class="col-sm-2">：</td>
                                <td class="col-sm-4">${perceptionHcs.remark}</td>
                            </tr>
                         
                        </tbody>
                    </table>
                </section>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/perception/js/hcs.detail.js" type="text/javascript"></script>
