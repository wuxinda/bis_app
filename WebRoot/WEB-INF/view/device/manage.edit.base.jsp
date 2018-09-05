<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>

<section class="edit-map wrapper" id="edit_base">
	<section class="panel panel-default">
		<header class="panel-heading font-bold">基本信息</header>
		<span>${StoreArea}</span>
		<input name="spuId" type="hidden" value="${spu.spuId}"/>
		<div class="panel-body">
			<div class="form-group m-b-xs">
                        <label for="categoryName" class="col-sm-3 control-label">设备类型</label>
                        <div class="col-sm-4">
                            <input type="text" readonly="readonly" class="form-control" id="categoryName" name="categoryName" value="${deviceManage.categoryName}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="brandName" class="col-sm-3 control-label">所属品牌</label>
                        <div class="col-sm-4">
                            <input type="text" readonly="readonly" class="form-control" id="brandName" name="brandName" value="${deviceManage.brandName}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="storeName" class="col-sm-3 control-label">所属库房</label>
                        <div class="col-sm-4">
                            <input type="text" readonly="readonly" class="form-control" id="storeName" name="storeName" value="${deviceManage.storeName}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
                  <%--   
                    <div class="line line-dashed line pull-in"></div>
                 <div class="form-group">
                        <label for="storeAreaId" class="col-sm-3 control-label">所属库区</label>
                        <div class="col-sm-4">
                            <input type="text" readonly="readonly" class="form-control" id="storeAreaName" name="storeAreaName" value="${wmsStoreArea.name}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>  --%>
                  <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="stroreAreaId" class="col-sm-3 control-label">所属库区</label>
                        <div class="col-sm-4">                      
                        	<select   name="stroreAreaId" id="stroreAreaId"
										class="form-control">
									<!-- 	<option value="">请选择库区</option> -->
										<c:forEach items="${wmsStoreArea}" var="v">
											<option value="${v.stroreAreaId}"
												<c:if test="${ v.stroreAreaId == deviceManage.stroreAreaId}">selected</c:if>>${v.name}</option>
										</c:forEach>
							</select> 
								<!-- 	<span class="help-block"></span> -->
                        </div>
                    </div> 
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">设备名称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name" value="${deviceManage.name}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="sortOrder" class="col-sm-3 control-label">序号</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="sortOrder" name="sortOrder" value="${deviceManage.sortOrder}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
					<div class="form-group">
				        <label class="col-sm-3 control-label">状态</label>
				        <div class="col-sm-4">
				            <select id="status" name="status" class="form-control">
				                    <option value="1" <c:if test="${deviceManage.status == 1}">selected="selected"</c:if>>在用		</option>
				                	<option value="0" <c:if test="${deviceManage.status == 0}">selected="selected"</c:if>>停用</option>
				            </select>
				        </div>
				    </div>
				    <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="remark" name="remark" value="${deviceManage.remark}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
		</div>
	</section>
</section>