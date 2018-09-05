<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
  
<section class="edit-map wrapper default-hidden" id="edit_link_alarm">
	
        <section class="panel panel-default">
        <header class="panel-heading font-bold">报警配置</header>
            <div class="panel-body">
                    <div class="form-group">
                        <label for="temUp" class="col-sm-3 control-label">温度上限</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="temUp" name="temUp" value="${alarmHcsdeviceConfig.temUp}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="temDown" class="col-sm-3 control-label">温度下限</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="temDown" name="temDown" value="${alarmHcsdeviceConfig.temDown}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="humUp" class="col-sm-3 control-label">湿度上限</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="humUp" name="humUp" value="${alarmHcsdeviceConfig.humUp}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="humDown" class="col-sm-3 control-label">湿度下限</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="humDown" name="humDown" value="${alarmHcsdeviceConfig.humDown}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="alarmStatus" class="col-sm-3 control-label">是否报警</label>
                        <div class="col-sm-4">
				            <select id="alarmStatus" name="alarmStatus" class="form-control">
				                <option value="0">请选择</option>
				                	<option value="0" <c:if test="${alarmHcsdeviceConfig.alarmStatus == 0}">selected="selected"</c:if>>是</option>
				                	<option value="1" <c:if test="${alarmHcsdeviceConfig.alarmStatus == 1}">selected="selected"</c:if>>否</option>
				                	
				            </select>
				        </div>
                    </div>
					<%--<div class="line line-dashed line pull-in"></div>
                     <div class="form-group">
                        <label for="remark" class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="remark_alm" name="remark_alm" value="${alarmHcsdeviceConfig.remark}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div> --%>
                    <div class="line line-dashed line-lg pull-in"></div>
                    <input type="hidden" name="alarmDevconfId" value="${alarmHcsdeviceConfig.alarmDevconfId}" />

            </div>
        </section>
</section>
