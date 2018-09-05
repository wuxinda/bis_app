<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/modules/admin/css/rolePermission.css" rel="stylesheet" type="text/css" />

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r" id="subNav">
        <section class="vbox">
            <header class="b-b header"><p class="h4">模块</p></header>
            <section class="scrollable w-f">
                <ul class="nav nav-pills nav-stacked no-radius">
                	<c:forEach items="${firstMenus}" var="v">
                    <li class="b-b m-t-none-reset <c:if test="${menuId == v.menuId }">active</c:if>">
                        <a href="${BASE_URL}/adminRole/rolePermission?roleId=${adminRole.roleId}&menuId=${v.menuId}" class="load-content">
                            <i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i>
                            <i class="fa fa-fw fa-ellipsis-v"></i>
                            ${v.name}
                        </a>
                    </li>
                    </c:forEach>
                    <input type="hidden" name="menuId" value="${menuId}"/>
                </ul>
            </section>
        </section>
    </aside>
        
    <aside>
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <p>为角色《${adminRole.name}》分配权限</p>
            </header>
                 <table>
                    <tbody>
                    <tr>
                        <td width="80"></td>
                        <td width="270"></td>
                        <td>
                        </td>
                    </tr>
                    </tbody>
                 </table>
            <section class="scrollable wrapper" id="permission_content">
            <span class="edit_notice" id="edit_notice"></span>
            <div style="margin-left:334px;">  全选/反选&nbsp;&nbsp;<input type="checkbox" name="post[]" class="select-all" value=""/></div>
			
			
            <c:forEach items="${menuPermissions}" var="menuPermission">
                <header class="panel-heading" style="font-size: 17px"> ${menuPermission.secondMenuName} </header>
                <section class="panel panel-default">
                    <table class="table table-striped m-b-none text-sm" id="permission_listing">
                        <tbody>
                        	<c:forEach items="${menuPermission.adminPermissionTOs}" var="permission">
                        	<span class="edit-notice" id="edit_notice_${permission.permissionId}"></span>
			                    <tr>
			                        <td width="80">${permission.permissionId}</td>
			                        <td width="300">${permission.permissionName}</td>
			                        <td>
			                            <input type="checkbox" class="changeRolePermission" name="permissionId" 
			                            data-navigation_id="${menuId}" value="${permission.permissionId}" 
			                            id="pm_${data.permissionId}" ut="group" pg="${permission.permissionName}" 
			                            <c:if test="${permission.hasPermission==1}">checked="checked"</c:if> />
			                        </td>
			                    </tr>
			                </c:forEach>
                        </tbody>
                    </table>
                </section>
                </c:forEach>
               <%-- </c:forEach> --%>
            </section>
            <input type="hidden" name="roleId" value="${adminRole.roleId}" />
        </section>
    </aside>
</section>
<div id="content_body">
    <input type="hidden" name="parameter" value="${adminRole.roleId}" />
</div>

<jsp:include page="../wrapper.suffix.jsp"/>
<!-- {literal} -->
<script type="text/javascript">
	/* 下拉菜单 */
	var $drop_button = $("div.drop>div>.drop_button");
	
	$drop_button.hover(function(){
	    $(this).parent().parent().addClass("drop_hover");
	},
	function(){
	    $(this).parent().parent().removeClass("drop_hover");
	});
	
	$drop_button.click(function(){
	    dropMenu($(this));
	    return false;
	});

    /* 弹出/隐藏表单层 */
    $('.stretch-button').click(function(event){
        event.preventDefault();
        
        var sbid = $(this).attr("id");
        var sbid_arr = sbid.split("_");
        var sb_goal_id = "stretch_" + sbid_arr[2];
        
        $("#" + sb_goal_id).slideToggle();
    });
    
    /* tr hover */
    $("tbody>tr").hover(function() {
        $(this).addClass("tr_hover");
    },
    function() {
        $(this).removeClass("tr_hover");
    });
</script>
<!-- {/literal} -->

<script src="${STATIC_URL}/modules/admin/js/rolePermission.js" type="text/javascript"></script>