<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../wrapper.prefix.jsp"/>

  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${alarmMessageConfig!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${alarmMessageConfig!=null}">"${BASE_URL}/alarmMessageConfig/edit"</c:when><c:otherwise>"${BASE_URL}/alarmMessageConfig/add"</c:otherwise></c:choose> method="post">
                   <%--  <div class="form-group">
                        <label for="alarmType" class="col-sm-3 control-label">报警类型Id</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="alarmType" name="alarmType" value="${alarmTypeId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div> --%>
                    <div class="form-group">
                        <label for="alarmType" class="col-sm-3 control-label">报警类型</label>
                        <div class="col-sm-4">
                            <input type="text" disabled="disabled" class="form-control" id="alarmTypeName" name="alarmTypeName" value="${alarmTypeName}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
					<div class="form-group">
				        <label class="col-sm-3 control-label">用户类型</label>
				        <div class="col-sm-4">
				            <select id="userType" name="userType" class="form-control">
				                <option value="0">请选择</option>
				                	<option value="0" <c:if test="${alarmMessageConfig.userType == 0}">selected="selected"</c:if>>不限</option>
				                	<option value="1" <c:if test="${alarmMessageConfig.userType == 1}">selected="selected"</c:if>>领导者</option>
				                	<option value="2" <c:if test="${alarmMessageConfig.userType == 2}">selected="selected"</c:if>>操作者</option>
				            </select>
				        </div>
				    </div>
				   <%--  <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="userId" class="col-sm-3 control-label">单个用户Id</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="userId" name="userId" value="${alarmMessageConfig.userId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div> --%>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="intervals" class="col-sm-3 control-label">推送时间间隔</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="intervals" name="intervals" value="${alarmMessageConfig.intervals}" onkeydown="if(event.keyCode==13)return false;" placeholder="每隔几分钟推送一次，请输入分钟数" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
					<div class="form-group">
				        <label class="col-sm-3 control-label">工作日推送</label>
				        <div class="col-sm-4">
				            <select id="iswork" name="iswork" class="form-control">
				                <option value="0">请选择</option>
				                	<option value="0" <c:if test="${alarmMessageConfig.iswork == 0}">selected="selected"</c:if>>是（只在周一到周五推送）</option>
				                	<option value="1" <c:if test="${alarmMessageConfig.iswork == 1}">selected="selected"</c:if>>否（每日推送）</option>
				            </select>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="starpush" class="col-sm-3 control-label">每日时间范围起</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="starpush" name="starpush" value="${alarmMessageConfig.starpush}" onkeydown="if(event.keyCode==13)return false;" placeholder="每天几点开始推送0-24" />
                        </div>
                    </div>
                    <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="endpush" class="col-sm-3 control-label">每日时间范围终</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="endpush" name="endpush" value="${alarmMessageConfig.endpush}" onkeydown="if(event.keyCode==13)return false;" placeholder="每天几点结束推送0-24" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="ispush" class="col-sm-3 control-label">是否推送</label>
                        <div class="col-sm-4">
                        <select id="ispush" name="ispush" class="form-control">
				                <option value="0">请选择</option>
				                	<option value="0" <c:if test="${alarmMessageConfig.ispush == 0}">selected="selected"</c:if>>是</option>
				                	<option value="1" <c:if test="${alarmMessageConfig.ispush == 1}">selected="selected"</c:if>>否</option>
				            </select>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="remark" name="remark" value="${alarmMessageConfig.remark}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <input type="hidden" name="almmsgcfgId" value="${alarmMessageConfig.almmsgcfgId}" />
                    <input type="hidden" id="alarmType" name="alarmType" value="${alarmTypeId}"  />
                        
                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <button type="button" id="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/alarm/js/type.config.js" type="text/javascript"></script>
<!-- / modal - 编辑-->