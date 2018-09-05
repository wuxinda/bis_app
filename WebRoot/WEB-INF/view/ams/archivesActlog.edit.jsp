<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${amsArchivesActlog!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${amsArchivesActlog!=null}">"${BASE_URL}/amsArchivesActlog/edit"</c:when><c:otherwise>"${BASE_URL}/amsArchivesActlog/add"</c:otherwise></c:choose> method="post">
                    <div class="form-group">
                        <label for="archivesId" class="col-sm-3 control-label">档案id</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="archivesId" name="archivesId" value="${amsArchivesActlog.archivesId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<%-- <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="userId" class="col-sm-3 control-label">用户id</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="userId" name="userId" value="${amsArchivesActlog.userId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div> --%>
					<div class="line line-dashed line pull-in"></div>
					<div class="form-group">
				        <label class="col-sm-3 control-label">操作类型</label>
				        <div class="col-sm-4">
				            <select id="type" name="type" class="form-control">
				                <option value="">请选择</option>
				                	<option value="0" <c:if test="${amsArchivesActlog.type == 0}">selected="selected"</c:if>>入库</option>
				                	<option value="1" <c:if test="${amsArchivesActlog.type == 1}">selected="selected"</c:if>>借阅</option>
				                	<option value="2" <c:if test="${amsArchivesActlog.type == 2}">selected="selected"</c:if>>上架</option>
				                	<option value="3" <c:if test="${amsArchivesActlog.type == 3}">selected="selected"</c:if>>归还		</option>
				            </select>
				        </div>
				    </div>
				    <%-- <div class="line line-dashed line pull-in"></div>
					<div class="form-group">
				        <label class="col-sm-3 control-label">审核状态</label>
				        <div class="col-sm-4">
				            <select id="status" name="status" class="form-control">
				                <option value="0">请选择</option>
				                	<option value="" <c:if test="${amsArchivesActlog.status == 0}">selected="selected"</c:if>>待审批</option>
				                	<option value="1" <c:if test="${amsArchivesActlog.status == 1}">selected="selected"</c:if>>审核通过</option>
				                	<option value="2" <c:if test="${amsArchivesActlog.status == 2}">selected="selected"</c:if>>审核拒绝		</option>
				            </select>
				        </div>
				    </div> --%>
				    <%-- <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="creator" class="col-sm-3 control-label">创建人</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="creator" name="creator" value="${amsArchivesActlog.creator}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="ctime" class="col-sm-3 control-label">创建时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="ctime" name="ctime" 
                            value="<fmt:formatDate value="${amsArchivesActlog.ctime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择创建时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    <div class="form-group">
                        <label for="modifier" class="col-sm-3 control-label">修改人</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="modifier" name="modifier" value="${amsArchivesActlog.modifier}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="mtime" class="col-sm-3 control-label">最后一次更新时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="mtime" name="mtime" 
                            value="<fmt:formatDate value="${amsArchivesActlog.mtime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择最后一次更新时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div> --%>
                    <input type="hidden" name="actlogId" value="${amsArchivesActlog.actlogId}" />

                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${amsArchivesActlog==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
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
<script src="${STATIC_URL}/modules/ams/js/archivesActlog.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->