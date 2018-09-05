<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<%-- <link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/> --%>
  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${messageInfo!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${messageInfo!=null}">"${BASE_URL}/messageInfo/edit"</c:when><c:otherwise>"${BASE_URL}/messageInfo/add"</c:otherwise></c:choose> method="post">
					<div class="form-group">
				        <label class="col-sm-3 control-label">消息类型</label>
				        <div class="col-sm-4">
				            <select id="type" name="type" class="form-control">
				               
				                	<option value="1" <c:if test="${messageInfo.type == 1}">selected="selected"</c:if>>系统消息</option>
				                	<option value="2" <c:if test="${messageInfo.type == 2}">selected="selected"</c:if>>报警消息</option>
				                	<option value="3" <c:if test="${messageInfo.type == 3}">selected="selected"</c:if>>审批消息</option>
				            </select>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="title" class="col-sm-3 control-label">标题</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="title" name="title" value="${messageInfo.title}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>    
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="content" class="col-sm-3 control-label">内容</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="contentText" name="content" value="${messageInfo.content}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>                      
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="isPush" class="col-sm-3 control-label">是否推送</label>
                        <div class="col-sm-4">
                         <select id="isPush" name="isPush" class="form-control">
				               
				                	<option value="0" <c:if test="${messageInfo.isPush == 0}">selected="selected"</c:if>>否</option>
				                	<option value="1" <c:if test="${messageInfo.isPush == 1}">selected="selected"</c:if>>是</option>
				            </select>
                            </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
					<div class="form-group">
				        <label class="col-sm-3 control-label">删除标示</label>
				        <div class="col-sm-4">
				            <select id="isDel" name="isDel" class="form-control">
				             
				                	<option value="0" <c:if test="${messageInfo.isDel == 0}">selected="selected"</c:if>>否</option>
				                	<option value="1" <c:if test="${messageInfo.isDel == 1}">selected="selected"</c:if>>是</option>
				            </select>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="creator" class="col-sm-3 control-label">创建人</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="creator" name="creator" value="${messageInfo.creator}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="ctime" class="col-sm-3 control-label">创建时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="ctime" name="ctime" 
                            value="<fmt:formatDate value="${messageInfo.ctime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择创建时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    <div class="form-group">
                        <label for="modifier" class="col-sm-3 control-label">修改人</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="modifier" name="modifier" value="${messageInfo.modifier}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="mtime" class="col-sm-3 control-label">最后一次更新时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="mtime" name="mtime" 
                            value="<fmt:formatDate value="${messageInfo.mtime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择最后一次更新时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    <input type="hidden" name="messageId" value="${messageInfo.messageId}" />

                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${messageInfo==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
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
<script src="${STATIC_URL}/modules/message/js/info.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->