<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${editionInfo!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${editionInfo!=null}">"${BASE_URL}/editionInfo/edit"</c:when><c:otherwise>"${BASE_URL}/editionInfo/add"</c:otherwise></c:choose> method="post">
                    <div class="form-group">
                        <label for="editionNumber" class="col-sm-3 control-label">版本号</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="editionNumber" name="editionNumber" value="${editionInfo.editionNumber}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>                       
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="editionName" class="col-sm-3 control-label">应用名称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="editionName" name="editionName" value="${editionInfo.editionName}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="line line-dashed line pull-in"></div>
					<div class="form-group">
				        <label class="col-sm-3 control-label">应用平台</label>
				        <div class="col-sm-4">
				            <select id="editionType" name="editionType" class="form-control">
				                <option value="">请选择</option>
				                	<option value="0" <c:if test="${editionInfo.editionType == 0}">selected="selected"</c:if>>IOS</option>
				                	<option value="1" <c:if test="${editionInfo.editionType == 1}">selected="selected"</c:if>>安卓</option>
				                	<option value="2" <c:if test="${editionInfo.editionType == 2}">selected="selected"</c:if>>IPAD</option>
				            </select>
				            <span class="help-block"></span>
				        </div>
				    </div>
				     <div class="line line-dashed line pull-in"></div>
					<div class="form-group">
				        <label class="col-sm-3 control-label">应用分类</label>
				        <div class="col-sm-4">
				            <select id="sunType" name="sunType" class="form-control">
				                <option value="">请选择</option>
				                <option value="001" <c:if test="${editionInfo.sunType == 001}">selected="selected"</c:if>><c:if test="${editionInfo.editionType == 0}">Iphone手机</c:if><c:if test="${editionInfo.editionType == 1}">安卓手机</c:if><c:if test="${editionInfo.editionType == 2}">Ipad</c:if></option>
				                <option value="002" <c:if test="${editionInfo.sunType == 002}">selected="selected"</c:if>>固定列</option>
				                <option value="003" <c:if test="${editionInfo.sunType == 003}">selected="selected"</c:if>>移动列</option>
				            
				            </select>
				            <span class="help-block"></span>
				        </div>
				    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="editionUrl" class="col-sm-3 control-label">上传应用</label>
                        <div class="col-sm-4">
                            <input type="file" class="form-control" id="editionUrl" name="editionUrl" value="${editionInfo.editionUrl}" onkeydown="if(event.keyCode==13)return false;" placeholder="请选择文件" />
                        </div>
                    </div>					
				    <div class="line line-dashed line pull-in"></div>
					<div class="form-group">
				        <label class="col-sm-3 control-label">是否发布</label>
				        <div class="col-sm-4">
				            <select id="isPublish" name="isPublish" class="form-control">
				                <option value="1">请选择,默认不发布</option>
				                	<option value="0" <c:if test="${editionInfo.isPublish == 0}">selected="selected"</c:if>>是</option>
				                	<option value="1" <c:if test="${editionInfo.isPublish == 1}">selected="selected"</c:if>>否</option>
				                	</select>
				        </div>
				    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="remark" name="remark" value="${editionInfo.remark}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <input type="hidden" name="editionId" value="${editionInfo.editionId}" />

                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${editionInfo==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
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
<script src="${STATIC_URL}/modules/edition/info.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->