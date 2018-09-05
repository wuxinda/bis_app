<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${adminRole!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose><c:choose><c:when test="${userType==1}">平台角色</c:when><c:otherwise>商户角色</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${adminRole!=null}">"${BASE_URL}/adminRole/edit"</c:when><c:otherwise>"${BASE_URL}/adminRole/add"</c:otherwise></c:choose> method="post">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label"><font class="red">*</font> 角色名</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name" value="${adminRole.name}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"> </span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="description" class="col-sm-3 control-label">描述</label>
                        <div class="col-sm-4">
                        	<textarea name="description" class="form-control" rows="3" placeholder="请输入描述">${adminRole.description}</textarea>
                        </div>
                    </div>
<%-- 
                    <div class="line line-dashed line pull-in"></div>
					<div class="form-group ">
						<label class="col-sm-3 control-label"><font class="red">*</font> 状态</label>
						<div class="col-sm-4">
							<label class="checkbox-inline p-left-0"> 
								<input type="radio" name="status" value="0" <c:if test="${adminPermission.status == 0}">checked="checked"</c:if> />&nbsp;不使用
							</label> 
							<label class="checkbox-inline p-left-0"> 
								<input type="radio" name="status" value="1" <c:if test="${adminPermission.status == 1}">checked="checked"</c:if> />&nbsp;使用
							</label> 
						</div>
					</div>
 --%>
 
 
                    <%-- <div class="line line-dashed line pull-in"></div>
					<div class="form-group ">
						<label class="col-sm-3 control-label"><font class="red">*</font> 用户类型</label>
						<div class="col-sm-4">
							<label class="checkbox-inline p-left-0"> 
								<input type="radio" name="userType" value="1" <c:if test="${adminPermission==null || adminPermission.userType == 1}">checked="checked"</c:if> />&nbsp;平台
							</label> 
							<label class="checkbox-inline p-left-0"> 
								<input type="radio" name="userType" value="2" <c:if test="${adminPermission.userType == 2}">checked="checked"</c:if> />&nbsp;商户
							</label> 
						</div>
					</div> --%>


<%-- 				    <div class="line line-dashed line pull-in" name="merchantIdDiv"></div>
                    <div class="form-group" name="merchantIdDiv">
                        <label for="merchantId" class="col-sm-3 control-label">商户id</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="merchantId" name="merchantId" value="${adminRole.merchantId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div> --%>
                    
					<div class="line line-dashed line pull-in"></div>
                    <input type="hidden" name="roleId" value="${adminRole.roleId}" />
					<c:choose> 
						<c:when test="${adminRole!=null}">
							<input type="hidden" name="userType" value="${adminRole.userType}" />
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
                    <c:if test="${adminRole==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
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
<script src="${STATIC_URL}/modules/admin/js/role.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->