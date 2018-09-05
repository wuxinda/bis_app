<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${logUserAction!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${logUserAction!=null}">"${BASE_URL}/logUserAction/edit"</c:when><c:otherwise>"${BASE_URL}/logUserAction/add"</c:otherwise></c:choose> method="post">
					<div class="form-group">
				        <label class="col-sm-3 control-label">日志类型</label>
				        <div class="col-sm-4">
				            <select id="logType" name="logType" class="form-control">
				                <option value="0">请选择</option>
				                	<option value="1" <c:if test="${logUserAction.logType == 1}">selected="selected"</c:if>>后台日志</option>
				                	<option value="2" <c:if test="${logUserAction.logType == 2}">selected="selected"</c:if>>手机端日志</option>
				                	<option value="3" <c:if test="${logUserAction.logType == 3}">selected="selected"</c:if>>web端日志</option>
				            </select>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="userid" class="col-sm-3 control-label">用户id</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="userid" name="userid" value="${logUserAction.userid}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="url" class="col-sm-3 control-label">请求url</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="url" name="url" value="${logUserAction.url}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="param" class="col-sm-3 control-label">请求参数</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="param" name="param" value="${logUserAction.param}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="ip" class="col-sm-3 control-label">用户ip</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="ip" name="ip" value="${logUserAction.ip}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="logTime" class="col-sm-3 control-label">日志记录时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="logTime" name="logTime" 
                            value="<fmt:formatDate value="${logUserAction.logTime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择日志记录时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    <input type="hidden" name="logId" value="${logUserAction.logId}" />

                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${logUserAction==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
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
<script src="${STATIC_URL}/modules/log/js/userAction.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->