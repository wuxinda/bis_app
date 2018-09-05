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
	                <header class="panel-heading">银行基础数据，银行编号，银行名称详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">主键：</td>
                                <td class="col-sm-4">${systemBank.id}</td>
                                <td class="col-sm-2">父级编号：</td>
                                <td class="col-sm-4">${systemBank.pno}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">银行编号：</td>
                                <td class="col-sm-4">${systemBank.bankNo}</td>
                                <td class="col-sm-2">银行名称：</td>
                                <td class="col-sm-4">${systemBank.bankName}</td>
                            </tr>
                            <tr>  
								<td class="col-sm-2">银行级别：</td>
                                <td class="col-sm-4">
									<c:if test="${systemBank.grade == 1}">
									主行
									</c:if>
									<c:if test="${systemBank.grade == 2}">
									分行
									</c:if>
								</td>
								<td class="col-sm-2">状态：</td>
                                <td class="col-sm-4">
									<c:if test="${systemBank.status == 0}">
									不可用
									</c:if>
									<c:if test="${systemBank.status == 1}">
									可用
									</c:if>
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
<script src="${STATIC_URL}/modules/system/js/bank.detail.js" type="text/javascript"></script>
