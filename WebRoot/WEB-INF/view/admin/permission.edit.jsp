<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../wrapper.prefix.jsp"/>

  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${adminPermission!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${adminPermission!=null}">"${BASE_URL}/adminPermission/edit"</c:when><c:otherwise>"${BASE_URL}/adminPermission/add"</c:otherwise></c:choose> method="post">
                    
	                <div class="form-group">
						<label class="col-sm-3 control-label"><font class="red">*</font>所属菜单:</label>
						<div class="col-sm-2">
							<select class="form-control col-sm-3" name="firstMenuId">
								<option value="">请选择父级菜单</option>
								<c:if test="${firstMenus != null}">
									<c:forEach items="${firstMenus}" var="v">
										<option value="${v.menuId}"
											<c:if test="${ v.menuId == firstMenuId}">selected</c:if>>${v.name}</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
						<div class="col-sm-2">
							<select class="form-control col-sm-3" name="menuId" id = "menuIdSelect">
								<option value="">请选择子级菜单</option>
								<c:if test="${secondMenus != null}">
									<c:forEach items="${secondMenus}" var="v">
										<option data-link="${v.url}" value="${v.menuId}"
											<c:if test="${ v.menuId == adminPermission.menuId}">selected</c:if>>${v.name}</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
	                <div class="line line-dashed line pull-in"></div>
                
					<div class="form-group ">
						<label class="col-sm-3 control-label">权限代码</label>
						<div class="col-sm-8">
						
							<c:forEach items="${permissionCodeMap}" var="map">
								<label class="checkbox-inline p-left-0"> 
									<input type="radio" name="code" value="${map.key}" <c:if test="${(adminPermission==null && map.key=='index') || adminPermission.code == map.key}">checked="checked"</c:if> />&nbsp;${map.value}
								</label> 
							</c:forEach>
							
						</div>
					</div>
                    <div class="line line-dashed line pull-in"></div>
                    
                      <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">权限名称</label>
                        <div class="col-sm-4">
                            <input type="text" readonly="readonly" class="form-control" id="name" name="name" value="${adminPermission.name}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
                    <div class="line line-dashed line pull-in"></div>
  
                    <div class="form-group">
                        <label for="url" class="col-sm-3 control-label">链接</label>
                        <div class="col-sm-4">
                            <input type="text"  readonly="readonly" class="form-control" id="url" name="url" value="${adminPermission.url}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
					
                    <div class="form-group">
                        <label for="sortOrder" class="col-sm-3 control-label">序号</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="sortOrder" name="sortOrder" value="${adminPermission.sortOrder}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
                    <div class="line line-dashed line pull-in"></div>
                    
					<div class="form-group ">
						<label class="col-sm-3 control-label">状态</label>
						<div class="col-sm-4">
							<label class="checkbox-inline p-left-0"> 
								<input type="radio" name="status" value="0" <c:if test="${adminPermission.status == 0}">checked="checked"</c:if> />&nbsp;不使用
							</label> 
							<label class="checkbox-inline p-left-0"> 
								<input type="radio" name="status" value="1" <c:if test="${adminPermission==null || adminPermission.status == 1}">checked="checked"</c:if> />&nbsp;使用
							</label> 
						</div>
					</div>
                    <div class="line line-dashed line pull-in"></div>
                    
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
				    
                    <input type="hidden" name="permissionId" value="${adminPermission.permissionId}" />
					
                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${adminPermission==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/admin/js/permission.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->