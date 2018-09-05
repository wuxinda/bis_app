<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${wmsStore!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>               
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${wmsStore!=null}">"${BASE_URL}/wmsStore/edit"</c:when><c:otherwise>"${BASE_URL}/wmsStore/add"</c:otherwise></c:choose> method="post">
                    <div class="form-group">
                        <label for="code" class="col-sm-3 control-label">库房代码</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="code" name="code" value="${wmsStore.code}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">库房名称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name" value="${wmsStore.name}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="buildingNo" class="col-sm-3 control-label">所属楼号</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="buildingNo" name="buildingNo" value="${wmsStore.buildingNo}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="floorNo" class="col-sm-3 control-label">所属层数</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="floorNo" name="floorNo" value="${wmsStore.floorNo}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                             <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="sortOrder" class="col-sm-3 control-label">序号</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="sortOrder" name="sortOrder" value="${wmsStore.sortOrder}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
					<div class="form-group">
				        <label class="col-sm-3 control-label">状态</label>
				        <div class="col-sm-4">
				            <select id="status" name="status" class="form-control">
				                <option value="">请选择</option>
				                	<option value="0" <c:if test="${wmsStore.status == 0}">selected="selected"</c:if>>停用</option>
				                	<option value="1" <c:if test="${wmsStore.status == 1}">selected="selected"</c:if>>在用		</option>
				            </select>
				            <span class="help-block"></span>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="remark" name="remark" value="${wmsStore.remark}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<%-- <div class="line line-dashed line pull-in"></div>
                    <div class="form-group" >
                        <label for="creator" class="col-sm-3 control-label">创建人</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="creator" name="creator" value="${wmsStore.creator}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="ctime" class="col-sm-3 control-label">创建时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="ctime" name="ctime" 
                            value="<fmt:formatDate value="${wmsStore.ctime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择创建时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    <div class="form-group">
                        <label for="modifier" class="col-sm-3 control-label">修改人</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="modifier" name="modifier" value="${wmsStore.modifier}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="mtime" class="col-sm-3 control-label">最后一次更新时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="mtime" name="mtime" 
                            value="<fmt:formatDate value="${wmsStore.mtime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择最后一次更新时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div> --%>
                    <input type="hidden" name="storeId" value="${wmsStore.storeId}" />

                </form>
            </section>       
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${wmsStore==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
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
<script src="${STATIC_URL}/modules/wms/js/store.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->