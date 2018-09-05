<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${adminUser!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose><c:choose><c:when test="${adminUser.userType==1 || userType==1}">平台用户</c:when><c:otherwise>商户用户</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${adminUser!=null}">"${BASE_URL}/adminUser/edit"</c:when><c:otherwise>"${BASE_URL}/adminUser/add"</c:otherwise></c:choose> method="post">

				    <div class="form-group m-b-xs">
				        <label for="userName" class="col-sm-3 control-label"><c:if test="${adminUser == null}"><font class="red">* </font></c:if>账号</label>
				        <div class="col-sm-4">
				        	<c:choose>
					        	<c:when test="${adminUser != null}">
						            <p class="form-control-static">${adminUser.userName}</p>
						            <input type="hidden" name="userName" id="userName" value="${adminUser.userName}" />
					            </c:when>
					            <c:otherwise>
						            <input type="text" class="form-control" id="userName" name="userName" value="" placeholder="请输入管理员账号" maxlength="14" />
						            <p class="help-block m-b-none">账号可以由字母、数字和下划线“_”组成，长度为3～15位</p>
					            </c:otherwise>
				            </c:choose>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>
				
				    <div class="form-group m-b-xs">
				        <label for="password" class="col-sm-3 control-label"><c:if test="${adminUser == null}"><font class="red">* </font></c:if><c:choose><c:when test="${adminUser != null}">重置</c:when><c:otherwise>设置</c:otherwise></c:choose>密码</label>
				        <div class="col-sm-4">
				            <input type="password" class="form-control" id="password" name="password" value="" placeholder="请输入<c:choose><c:when test="${adminUser != null}">重置</c:when><c:otherwise>管理员</c:otherwise></c:choose>密码" maxlength="20" />
				            <p class="help-block m-b-none">密码可以由字母、数字和下划线“_”组成<c:if test="${adminUser != null}">，不重置请不要填写</c:if></p>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>
				    
				    <c:if test="${adminUser == null}">
					    <div class="form-group m-b-xs">
					        <label for="repassword" class="col-sm-3 control-label"><font class="red">* </font>确认密码</label>
					        <div class="col-sm-4">
					            <input type="password" class="form-control" id="repassword" name="repassword" value="" placeholder="请再次输入管理员密码" maxlength="20" />
					        </div>
					    </div>
					    <div class="line line-dashed line pull-in"></div>
				    </c:if>
					
                    <%-- <div class="form-group">
                        <label for="salt" class="col-sm-3 control-label">混淆码</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="salt" name="salt" value="${adminUser.salt}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div> --%>
                    <div class="form-group">
                        <label for="phone" class="col-sm-3 control-label">电话</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="phone" name="phone" value="${adminUser.phone}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="email" class="col-sm-3 control-label">邮箱</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="email" name="email" value="${adminUser.email}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="fullname" class="col-sm-3 control-label">全名</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="fullname" name="fullname" value="${adminUser.fullname}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <%-- <div class="form-group">
                        <label for="nickname" class="col-sm-3 control-label">昵称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="nickname" name="nickname" value="${adminUser.nickname}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="enName" class="col-sm-3 control-label">英文名全称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="enName" name="enName" value="${adminUser.enName}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="initial" class="col-sm-3 control-label">英文名首字母</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="initial" name="initial" value="${adminUser.initial}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div> --%>
					
					<div class="form-group">
				        <label class="col-sm-3 control-label">性别</label>
				        <div class="col-sm-4">
							<label class="checkbox-inline p-left-0"> 
								<input type="radio" name="sex" value="1" <c:if test="${adminUser.sex == 1}">checked="checked"</c:if> />&nbsp;男
							</label> 
							<label class="checkbox-inline p-left-0"> 
								<input type="radio" name="sex" value="2" <c:if test="${adminUser.sex == 2}">checked="checked"</c:if> />&nbsp;女
							</label> 
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>

					<%-- <div class="form-group">
				        <label class="col-sm-3 control-label">状态</label>
				        <div class="col-sm-4">
				            <select id="status" name="status" class="form-control">
				                <option value="0">请选择</option>
				                	<option value="0" <c:if test="${adminUser.status == 0}">selected="selected"</c:if>>未审核</option>
				                	<option value="1" <c:if test="${adminUser.status == 1}">selected="selected"</c:if>>审核通过</option>
				                	<option value="2" <c:if test="${adminUser.status == 2}">selected="selected"</c:if>>审核不通过</option>
				            </select>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>
					<div class="form-group">
				        <label class="col-sm-3 control-label">是否在线</label>
				        <div class="col-sm-4">
				            <select id="isOnline" name="isOnline" class="form-control">
				                <option value="0">请选择</option>
				                	<option value="0" <c:if test="${adminUser.isOnline == 0}">selected="selected"</c:if>>不在线</option>
				                	<option value="1" <c:if test="${adminUser.isOnline == 1}">selected="selected"</c:if>>在线</option>
				            </select>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="loginTime" class="col-sm-3 control-label">最后登录时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="loginTime" name="loginTime" 
                            value="<fmt:formatDate value="${adminUser.loginTime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择最后登录时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    <div class="form-group">
                        <label for="creator" class="col-sm-3 control-label">创建人</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="creator" name="creator" value="${adminUser.creator}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="ctime" class="col-sm-3 control-label">创建时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="ctime" name="ctime" 
                            value="<fmt:formatDate value="${adminUser.ctime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择创建时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    <div class="form-group">
                        <label for="modifier" class="col-sm-3 control-label">修改人</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="modifier" name="modifier" value="${adminUser.modifier}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="mtime" class="col-sm-3 control-label">修改时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="mtime" name="mtime" 
                            value="<fmt:formatDate value="${adminUser.mtime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择修改时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div> --%>
					<%-- <div class="form-group">
				        <label class="col-sm-3 control-label">用户类型</label>
				        <div class="col-sm-4">
				            <select id="userType" name="userType" class="form-control">
				                <option value="0">请选择</option>
				                	<option value="1" <c:if test="${adminUser.userType == 1}">selected="selected"</c:if>>平台用户</option>
				                	<option value="2" <c:if test="${adminUser.userType == 2}">selected="selected"</c:if>>商户</option>
				            </select>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div> --%>
				    
				    <%-- <div class="form-group ">
						<label class="col-sm-3 control-label">用户类型</label>
						<div class="col-sm-4">
							<label class="checkbox-inline p-left-0"> 
								<input type="radio" name="userType" value="1" <c:if test="${adminPermission==null || adminPermission.userType == 1}">checked="checked"</c:if> />&nbsp;平台
							</label> 
							<label class="checkbox-inline p-left-0"> 
								<input type="radio" name="userType" value="2" <c:if test="${adminPermission.userType == 2}">checked="checked"</c:if> />&nbsp;商户
							</label> 
						</div>
					</div>
				    <div class="line line-dashed line pull-in"></div> --%>
				    
				  	<c:if test="${adminUser.userType==2 || userType==2}">
					    <div class="form-group merchant-showOrhide">
	                        <label for="merchantId" class="col-sm-3 control-label">商户</label>
	                        <div class="col-sm-4">
	                      	  <input type="text" class="form-control input-s inline" id="merchantName" name="merchantName" value="${merchantName}" onkeydown="if(event.keyCode==13)return false;" readonly="readonly" />
	                            <input type="hidden"  id="merchantId" name="merchantId" value="${loggedInUser.merchantId}"  />
	                           </div>
	                    </div>
						<div class="line line-dashed line pull-in merchant-showOrhide"></div>
				  	</c:if>s
                    
				    <div class="form-group">
                        <label class="col-sm-3 control-label">用户角色</label>
                        <div class="col-sm-4">
                            <select name="roleIds" class="form-control" multiple>
                                <c:forEach items="${adminRoles}" var="adminRole">
		                        	<option value="${adminRole.roleId}" <c:forEach items="${userAdminRoles}" var="userAdminRole"><c:if test='${adminRole.roleId == userAdminRole.roleId}'>selected="selected"</c:if></c:forEach>>${adminRole.name}</option>
		                        </c:forEach>
                            </select>
                        </div>
                    </div>
				    <div class="line line-dashed line pull-in"></div>
					<input type="hidden" name="userId" value="${adminUser.userId}" />
					<c:choose> 
						<c:when test="${adminUser!=null}">
							<input type="hidden" name="userType" value="${adminUser.userType}" />
						</c:when>
						<c:otherwise>
							<input type="hidden" name="userType" value="${userType}" />
						</c:otherwise>
					</c:choose>
                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${adminUser==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${STATIC_URL}/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="${STATIC_URL}/modules/admin/js/user.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->