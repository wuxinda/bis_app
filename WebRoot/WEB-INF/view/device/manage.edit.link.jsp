<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>

<section class="edit-map wrapper default-hidden" id="edit_link">
	<section class="panel panel-default">
		<header class="panel-heading font-bold">连接配置</header>
		<div class="panel-body">					
					<c:forEach items="${linkList}" var="v">
				    <div class="form-group m-b-xs">
                        <label for="${v.linkpropertyId}" class="col-sm-3 control-label">${v.name}</label>
                        <div class="col-sm-4">
                            <input type="hidden" name="linkIds" value="${v.linkpropertyId}" />
                            <input type="text" class="form-control"  name="linkIdValues" value="${v.val}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
					</c:forEach>
		</div>
	</section>
</section>