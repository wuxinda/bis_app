<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${perceptionHcs!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${perceptionHcs!=null}">"${BASE_URL}/perceptionHcs/edit"</c:when><c:otherwise>"${BASE_URL}/perceptionHcs/add"</c:otherwise></c:choose> method="post">
                    <div class="form-group">
                        <label for="deviceId" class="col-sm-3 control-label">设备ID</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="deviceId" name="deviceId" value="${perceptionHcs.deviceId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="temperature" class="col-sm-3 control-label">温度</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="temperature" name="temperature" value="${perceptionHcs.temperature}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="humidity" class="col-sm-3 control-label">湿度</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="humidity" name="humidity" value="${perceptionHcs.humidity}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="airStatus" class="col-sm-3 control-label">空调状态</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="airStatus" name="airStatus" value="${perceptionHcs.airStatus}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="dryingStatus" class="col-sm-3 control-label">除湿状态</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="dryingStatus" name="dryingStatus" value="${perceptionHcs.dryingStatus}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="wettingStatus" class="col-sm-3 control-label">增湿状态</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="wettingStatus" name="wettingStatus" value="${perceptionHcs.wettingStatus}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="ventilationStatus" class="col-sm-3 control-label">通风状态</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="ventilationStatus" name="ventilationStatus" value="${perceptionHcs.ventilationStatus}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="cleansingStatus" class="col-sm-3 control-label">净化状态</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="cleansingStatus" name="cleansingStatus" value="${perceptionHcs.cleansingStatus}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="warningStatus" class="col-sm-3 control-label">警告状态</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="warningStatus" name="warningStatus" value="${perceptionHcs.warningStatus}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="collectDate" class="col-sm-3 control-label">采集时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="collectDate" name="collectDate" 
                            value="<fmt:formatDate value="${perceptionHcs.collectDate}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择采集时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-3 control-label"></label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="remark" name="remark" value="${perceptionHcs.remark}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <input type="hidden" name="hcsId" value="${perceptionHcs.hcsId}" />

                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${perceptionHcs==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
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
<script src="${STATIC_URL}/modules/perception/js/hcs.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->