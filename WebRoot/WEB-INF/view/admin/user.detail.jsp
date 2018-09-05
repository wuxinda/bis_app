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
	                <header class="panel-heading">管理员用户表详情</header>
		            <table class="table table-striped2 m-b-none text-sm">
                        <tbody>
                        
                            <tr>  
                                <td class="col-sm-2">用户id：</td>
                                <td class="col-sm-4">${adminUser.userId}</td>
                                <td class="col-sm-2">用户名：</td>
                                <td class="col-sm-4">${adminUser.userName}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">密码：</td>
                                <td class="col-sm-4">${adminUser.password}</td>
                                <td class="col-sm-2">混淆码：</td>
                                <td class="col-sm-4">${adminUser.salt}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">电话：</td>
                                <td class="col-sm-4">${adminUser.phone}</td>
                                <td class="col-sm-2">邮箱：</td>
                                <td class="col-sm-4">${adminUser.email}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">全名：</td>
                                <td class="col-sm-4">${adminUser.fullname}</td>
                                <td class="col-sm-2">昵称：</td>
                                <td class="col-sm-4">${adminUser.nickname}</td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">英文名全称：</td>
                                <td class="col-sm-4">${adminUser.enName}</td>
                                <td class="col-sm-2">英文名首字母：</td>
                                <td class="col-sm-4">${adminUser.initial}</td>
                            </tr>
                            <tr>  
								<td class="col-sm-2">性别：</td>
                                <td class="col-sm-4">
									<c:if test="${adminUser.sex == 0}">
									未知
									</c:if>
									<c:if test="${adminUser.sex == 1}">
									男
									</c:if>
									<c:if test="${adminUser.sex == 2}">
									女
									</c:if>
								</td>
								<td class="col-sm-2">状态：</td>
                                <td class="col-sm-4">
									<c:if test="${adminUser.status == 0}">
									未审核
									</c:if>
									<c:if test="${adminUser.status == 1}">
									审核通过
									</c:if>
									<c:if test="${adminUser.status == 2}">
									审核不通过
									</c:if>
								</td>
                            </tr>
                            <tr>  
								<td class="col-sm-2">是否在线：</td>
                                <td class="col-sm-4">
									<c:if test="${adminUser.isOnline == 0}">
									不在线
									</c:if>
									<c:if test="${adminUser.isOnline == 1}">
									在线
									</c:if>
								</td>
                                <td class="col-sm-2">最后登录时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${adminUser.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">创建人：</td>
                                <td class="col-sm-4">${adminUser.creator}</td>
                                <td class="col-sm-2">创建时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${adminUser.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>  
                                <td class="col-sm-2">修改人：</td>
                                <td class="col-sm-4">${adminUser.modifier}</td>
                                <td class="col-sm-2">修改时间：</td>
                                <td class="col-sm-4">
                                    <fmt:formatDate value="${adminUser.mtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                            <tr>  
								<td class="col-sm-2">用户类型：</td>
                                <td class="col-sm-4">
									<c:if test="${adminUser.userType == 1}">
									平台用户
									</c:if>
									<c:if test="${adminUser.userType == 2}">
									商户
									</c:if>
								</td>
                                <td class="col-sm-2">商户id：</td>
                                <td class="col-sm-4">${adminUser.merchantId}</td>
                            </tr>
                         
                        </tbody>
                    </table>
                </section>
            </section>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/admin/js/user.detail.js" type="text/javascript"></script>
