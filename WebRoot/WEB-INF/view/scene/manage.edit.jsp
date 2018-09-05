<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${sceneManage!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${sceneManage!=null}">"${BASE_URL}/sceneManage/edit"</c:when><c:otherwise>"${BASE_URL}/sceneManage/add"</c:otherwise></c:choose> method="post">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">场景名称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name" value="${sceneManage.name}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>                   
                    </div>
					<div class="line line-dashed line pull-in"></div>
					<div class="form-group">
				        <label class="col-sm-3 control-label">场景类型</label>
				        <div class="col-sm-4">
				            <select id="type" name="type" class="form-control">
				                <!-- <option value="0">请选择</option> -->
				                	<option value="0" <c:if test="${sceneManage.type == 0}">selected="selected"</c:if>>系统默认</option>
				                	<option value="1" <c:if test="${sceneManage.type == 1}">selected="selected"</c:if>>用户自定义</option>
				            </select>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>
                    <%-- <div class="form-group">
                        <label for="userId" class="col-sm-3 control-label">所属用户id</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="userId" name="userId" value="${sceneManage.userId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div> --%>
                    <div class="form-group">
                     <label for="storeId" class="col-sm-3 control-label">所属库房</label>
                        <div class="col-sm-4">
                        	<select class="form-control col-sm-3" name="stroreId" id="stroreId">
								<option value="">请选择库房</option>
								<c:if test="${wmsStore != null}">
									<c:forEach items="${wmsStore}" var="v">
										<option value="${v.storeId}"
											<c:if test="${ v.storeId == sceneManage.stroreId}">selected</c:if>>${v.name}</option>
									</c:forEach>
								</c:if>
							</select>
							<span class="help-block"></span>
						</div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
					<div class="form-group">
				        <label class="col-sm-3 control-label">状态</label>
				        <div class="col-sm-4">
				            <select id="status" name="status" class="form-control">
				                <!-- <option value="0">请选择</option> -->
				                    <option value="1" <c:if test="${sceneManage.status == 1}">selected="selected"</c:if>>在用</option>
				                	<option value="0" <c:if test="${sceneManage.status == 0}">selected="selected"</c:if>>停用</option>			          
				            </select>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="remark" name="remark" value="${sceneManage.remark}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <input type="hidden" name="sceneId" value="${sceneManage.sceneId}" />

                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${sceneManage==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
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
<script src="${STATIC_URL}/modules/scene/js/manage.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->