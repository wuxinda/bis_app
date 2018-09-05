<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../wrapper.prefix.jsp" />


<section class="hbox stretch">
	<aside class="aside-md bg-white b-r">
		<section class="vbox">
			<header class="b-b header">
				<p class="h4">分配用户
				</p>
			</header>

			<section class="scrollable wrapper w-f">
				<form class="form-horizontal" id="edit_form"
					action=<c:choose> <c:when test="${wmsUser!=null}">"${BASE_URL}/wmsUser/setWmsUser"</c:when><c:otherwise>"${BASE_URL}/wmsUser/setWmsUser"</c:otherwise></c:choose>
					method="post">
					<div class="form-group">
						<label for="storeId" class="col-sm-3 control-label">所属库房id</label>
						<div class="col-sm-4">
							<input readOnly="true" type="text" class="form-control"
								id="storeId" name="storeId" value="${storeId}"
								onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
						</div>
					</div>
					<div class="line line-dashed line pull-in"></div>
					<div class="form-group">
						<label for="storeId" class="col-sm-3 control-label">所属库房名称</label>
						<div class="col-sm-4">
							<input readOnly="true" type="text" class="form-control" id="storeName"
								name="storeName" value="${storeName.name}"
								onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
						</div>
					</div>
					<div class="line line-dashed line pull-in"></div>
					<div class="form-group">
						<label for="userId" class="col-sm-3 control-label">已分配人员</label>
						<%-- <div class="col-sm-4">
                            <input type="text" class="form-control" id="userId" name="userId" value="${wmsUser.userId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div> --%>
						<div class="col-sm-4">
							<select id="userIds" name="userIds" class="form-control col-sm-4"
								multiple="multiple">
								<c:if test="${allUser != null}">
									<c:forEach items="${allUser}" var="v">
										<option value="${v.userId}"
											<c:forEach items="${wmsUser}" var="v1">
												<c:if test="${ v.userId == v1.userId}">selected</c:if>
									         </c:forEach>>${v.fullname}    ${v.userName}
										</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
					<div class="line line-dashed line pull-in"></div>
				</form>
			</section>

			<footer class="footer b-t bg-white-only">
				<div class="m-t-sm">
					<button type="button" data_submit_type="submit_save_back"
						class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
					<button type="button" data_submit_type="submit_cancel"
						class="btn btn-danger btn-sm input-submit">取消</button>
					<span id="edit_notice"></span>
				</div>
			</footer>
		</section>
	</aside>
</section>

<jsp:include page="../wrapper.suffix.jsp" />
<script src="${STATIC_URL}/modules/wms/js/user.edit.js"
	type="text/javascript"></script>
<!-- / modal - 编辑-->