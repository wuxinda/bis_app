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
	                <header class="panel-heading">对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">版本id：</td>
                                <td class="col-sm-4">${editionInfo.editionId}</td>
                                <td class="col-sm-2">版本号：</td>
                                <td class="col-sm-4">${editionInfo.editionNumber}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">应用名字：</td>
                                <td class="col-sm-4">${editionInfo.editionName}</td>
                                <td class="col-sm-2">上传文件：</td>
                                <td class="col-sm-4">${editionInfo.editionUrl}</td>
                            </tr>
                            <tr>  
								<td class="col-sm-2">应用平台：</td>
                                <td class="col-sm-4">
									<c:if test="${editionInfo.editionType == 0}">
									IOS
									</c:if>
									<c:if test="${editionInfo.editionType == 1}">
									安卓
									</c:if>
									<c:if test="${editionInfo.editionType == 2}">
									IPAD
									</c:if>
								</td>
								<td class="col-sm-2">是否发布：</td>
                                <td class="col-sm-4">
									<c:if test="${editionInfo.isPublish == 0}">
									是
									</c:if>
									<c:if test="${editionInfo.isPublish == 1}">
									否
									</c:if>
								</td>
							</tr>	
								
                               
                            <tr>  
                  <td class="col-sm-2">备注：</td>
                                <td class="col-sm-4">${editionInfo.remark}</td>
                            </tr>
                         
                        </tbody>
                    </table>
                </section>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/edition/info.detail.js" type="text/javascript"></script>
