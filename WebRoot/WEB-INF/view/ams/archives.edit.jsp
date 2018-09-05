<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${amsArchives!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${amsArchives!=null}">"${BASE_URL}/amsArchives/edit"</c:when><c:otherwise>"${BASE_URL}/amsArchives/add"</c:otherwise></c:choose> method="post">
                    <div class="form-group">
                        <label for="rfidnum" class="col-sm-3 control-label">RFID号</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="rfidnum" name="rfidnum" value="${amsArchives.rfidnum}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="archivesTypeId" class="col-sm-3 control-label">档案类型</label>
                        <div class="col-sm-4">
                        	<select class="form-control col-sm-3" name="archivesTypeId" id="archiveType">
								<option value="">请选择类型</option>
								<c:if test="${amsArchivesType != null}">
									<c:forEach items="${amsArchivesType}" var="v">
										<option value="${v.archivesTypeId}"
											<c:if test="${ v.archivesTypeId == amsArchives.archivesTypeId}">selected</c:if>>${v.name}</option>
									</c:forEach>
								</c:if>
							</select>
							<span class="help-block"></span>
                         </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="filingmethod" class="col-sm-3 control-label">立卷方式</label>
                        <div class="col-sm-4">
                        <select class="form-control col-sm-3" name="filingmethod" id="filingmethod">
								<option value="">请选择立卷方式</option>
								<c:if test="${filingmethod != null}">
									<c:forEach items="${filingmethod}" var="v">
										<option value="${v.archivesFilingmethodId}"
											<c:if test="${ v.archivesFilingmethodId == amsArchives.filingmethod}">selected</c:if>>${v.name}</option>
									</c:forEach>
								</c:if>
							</select>
				            <span class="help-block"></span>
                         </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="roomnum" class="col-sm-3 control-label">室编档号</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="roomnum" name="roomnum" value="${amsArchives.roomnum}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="archiveno" class="col-sm-3 control-label">档案号</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="archiveno" name="archiveno" value="${amsArchives.archiveno}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="title" class="col-sm-3 control-label">正题名</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="title" name="title" value="${amsArchives.title}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                    
                            <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="paratitle" class="col-sm-3 control-label">并列标题</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="paratitle" name="paratitle" value="${amsArchives.paratitle}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="keepyear" class="col-sm-3 control-label">保管年限</label>
                        <div class="col-sm-4">
                        <select class="form-control col-sm-3" name="keepyear" id="keepyear">
								<option value="">请选择保管年限</option>
								<c:if test="${keepyear != null}">
									<c:forEach items="${keepyear}" var="v">
										<option value="${v.amsArchivesKeepyear}"
											<c:if test="${ v.amsArchivesKeepyear == amsArchives.keepyear}">selected</c:if>>${v.name}</option>
									</c:forEach>
								</c:if>
							</select>
				            <span class="help-block"></span>
                         </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="security" class="col-sm-3 control-label">密级</label>
                        <div class="col-sm-4">
                        	<select id="security" name="security" class="form-control">
				                <option value="">请选择</option>
				                	<option value="0" <c:if test="${amsArchives.security == 0}">selected="selected"</c:if>>普通</option>
				                	<option value="1" <c:if test="${amsArchives.security == 1}">selected="selected"</c:if>>秘密</option>
				                	<option value="2" <c:if test="${amsArchives.security == 2}">selected="selected"</c:if>>机密</option>
				                	<option value="3" <c:if test="${amsArchives.security == 3}">selected="selected"</c:if>>绝密</option>
				            </select>
				            <span class="help-block"></span>
                        </div>
                    </div>
					<%-- <div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="storeplace" class="col-sm-3 control-label">存址</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="storeplace" name="storeplace" value="${amsArchives.storeplace}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>
                    </div> --%>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="subject" class="col-sm-3 control-label">主题词</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="subject" name="subject" value="${amsArchives.subject}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="keyword" class="col-sm-3 control-label">关键词</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="keyword" name="keyword" value="${amsArchives.keyword}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                     <div class="form-group">
                        <label for="inflag" class="col-sm-3 control-label">在库状态</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="inflag" name="inflag" value="${amsArchives.inflag}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>
                    </div> 
					<div class="line line-dashed line pull-in"></div>
                     <div class="form-group">
                        <label for="nowNumber" class="col-sm-3 control-label">现有库存</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="nowNumber" name="nowNumber" value="${amsArchives.nowNumber}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>
                    </div> 
					<div class="line line-dashed line pull-in"></div>
                     <div class="form-group">
                        <label for="totalNumber" class="col-sm-3 control-label">总库存</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="totalNumber" name="totalNumber" value="${amsArchives.totalNumber}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>
                    </div> 
					<div class="line line-dashed line pull-in"></div>
                    <%--<div class="form-group m-b-xs">
                        <label for="playbackstarttime" class="col-sm-3 control-label">视频回放开始时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="playbackstarttime" name="playbackstarttime" 
                            value="<fmt:formatDate value="${amsArchives.playbackstarttime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择视频回放开始时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    <div class="form-group">
                        <label for="inoutUserId" class="col-sm-3 control-label">临时记录出入库人</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="inoutUserId" name="inoutUserId" value="${amsArchives.inoutUserId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>--%>
                    <div class="form-group">
                        <label for="checkStatus" class="col-sm-3 control-label">盘点状态</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="checkStatus" name="checkStatus" value="${amsArchives.checkStatus}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                            <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="stroreId" class="col-sm-3 control-label">所属库房</label>
                        <div class="col-sm-4">
                        	<select class="form-control col-sm-3" name="stroreId" id="storeID">
								<option value="">请选择库房</option>
								<c:if test="${wmsStore != null}">
									<c:forEach items="${wmsStore}" var="v">
										<option value="${v.storeId}"
											<c:if test="${ v.storeId == amsArchives.stroreId}">selected</c:if>>${v.name}</option>
									</c:forEach>
								</c:if>
							</select>
							  <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="stroreAreaId" class="col-sm-3 control-label">所属库区</label>
                        <div class="col-sm-4">
                        	<select class="form-control col-sm-3" name="stroreAreaId" id="stroreAreaId" >
								<option value="">请选择库区</option>
								<c:if test="${wmsStoreArea != null}">
									<c:forEach items="${wmsStoreArea}" var="v">
										<option value="${v.stroreAreaId}"
											<c:if test="${ v.stroreAreaId == amsArchives.stroreAreaId}">selected</c:if>>${v.name}</option>
									</c:forEach>
								</c:if>
							</select>
							  <span class="help-block"></span>
                         </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="storeColumn" class="col-sm-3 control-label">所属密集架</label>
                        <div class="col-sm-4">
                        <select class="form-control col-sm-3" name="deviceId" id="deviceId" >
								<option value="">请选择密集架</option>
								<c:if test="${storeColumns != null}">
									<c:forEach items="${storeColumns}" var="v">
										<option value="${v.deviceId}"
											<c:if test="${ v.deviceId == amsArchives.deviceId}">selected</c:if>>${v.name}</option>
									</c:forEach>
								</c:if>
							</select>
							  <span class="help-block"></span>
                            </div>
                    </div>
				<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="storeSection" class="col-sm-3 control-label">密集架列号</label>
                         <div class="col-sm-4">
                         <input type="text" class="form-control" id="storeColumn" name="storeColumn" value="${amsArchives.storeColumn}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                           <span class="help-block"></span>
                         </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="storeSection" class="col-sm-3 control-label">所属节</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="storeSection" name="storeSection" value="${amsArchives.storeSection}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                              <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="storeLayer" class="col-sm-3 control-label">所属层</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="storeLayer" name="storeLayer" value="${amsArchives.storeLayer}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                              <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="storeLr" class="col-sm-3 control-label">方位左右</label>
                        <div class="col-sm-4">
                        <select class="form-control col-sm-3" name="storeLr" id="storeLr" >
								<option value="">请选择所属面</option>
								<option value="L" <c:if test="${ 'L' == amsArchives.storeLr}">selected</c:if>>L</option>
								<option value="R" <c:if test="${ 'R' == amsArchives.storeLr}">selected</c:if>>R</option>
							</select>
							  <span class="help-block"></span>
                         </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="remark" name="remark" value="${amsArchives.remark}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                              <span class="help-block"></span>
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                   <%--  <div class="form-group">
                        <label for="creator" class="col-sm-3 control-label">创建人</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="creator" name="creator" value="${amsArchives.creator}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="ctime" class="col-sm-3 control-label">创建时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="ctime" name="ctime" 
                            value="<fmt:formatDate value="${amsArchives.ctime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择创建时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    <div class="form-group">
                        <label for="modifier" class="col-sm-3 control-label">修改人</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="modifier" name="modifier" value="${amsArchives.modifier}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group m-b-xs">
                        <label for="mtime" class="col-sm-3 control-label">最后一次更新时间</label>
                        <div class="col-sm-4">
                            <input type="text" class="input-sm input-s datetimepicker-input form-control" id="mtime" name="mtime" 
                            value="<fmt:formatDate value="${amsArchives.mtime}" pattern="yyyy-MM-dd HH:mm"/>" placeholder="请选择最后一次更新时间时间" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="line line-dashed line-lg pull-in"></div> --%>
                    <input type="hidden" name="archivesId" value="${amsArchives.archivesId}" />

                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${amsArchives==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
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
<script src="${STATIC_URL}/modules/ams/js/archives.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->