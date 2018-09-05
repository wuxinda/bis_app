<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="${STATIC_URL}/modules/system/css/navigation.css" rel="stylesheet" type="text/css" />

<!-- modal - 编辑导航 -->
<div class="modal-dialog" id="modal_edit">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="modal_title"><c:choose><c:when test="${navigation != null}">编辑导航</c:when><c:otherwise>添加导航</c:otherwise></c:choose></h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal" action="${BASE_URL}/systemNavigation/add" method="post" id="edit_form">
                <div class="form-group">
                    <label class="col-sm-3 control-label">导航</label>
                    <div class="col-sm-6">
	                    <select name="parentId" id="parentId" class="form-control">
                            <option value="0">Root Category [ID:0]</option>
                             <c:forEach items="${navigations}" var="v">
                            <option value="${v.navigationId}" <c:if test="${ v.navigationId == navigation.parentId}">selected</c:if>>${v.strPadding}${v.title}</option>
                        	</c:forEach>
                        </select>
	                </div>
	                <div class="col-sm-3">
	                    <button type="button" id="resetParentNavigation" class="btn btn-sm input-submit">设置为顶级导航</button>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="title" class="col-sm-3 control-label"><font class="red">*</font>导航名称</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="title" id="title" value="${navigation.title}" placeholder="请输入导航名称" />
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="link" class="col-sm-3 control-label">链接地址</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="link" id="link" value="${navigation.link}" placeholder="请输入链接地址" />
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="sort_order" class="col-sm-3 control-label">序号</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="sortOrder" id="sortOrder" value="<c:choose><c:when test="${navigation != null}">${navigation.sortOrder}</c:when><c:otherwise>255</c:otherwise></c:choose>" />
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="icon" class="col-sm-3 control-label">ICON 图标</label>
                    <div class="col-sm-7">
                        <div class="input-group dropdown combobox" id="myCombobox">
                            <input type="text" name="icon" class="form-control" value="<c:choose><c:when test="${navigation != null}">${navigation.icon}</c:when><c:otherwise>fa-file-text</c:otherwise></c:choose>" />
                            <div class="input-group-btn">
                                <button data-toggle="dropdown" class="btn btn-default dropdown-toggle" type="button"><i class="caret"></i></button>
                                <ul class="dropdown-menu pull-right">
                                    <li data-value="fa-file-text" <c:if test="${navigation.icon == 'fa-file-text'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-file-text"></i><span class="text padder">fa-file-text</span></a></li>
                                    <li data-value="fa-bar-chart-o" <c:if test="${navigation.icon == 'fa-bar-chart-o'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-bar-chart-o"></i><span class="text padder">fa-bar-chart-o</span></a></li>
                                    <li data-value="fa-bell" <c:if test="${navigation.icon == 'fa-bell'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-bell"></i><span class="text padder">fa-bell</span></a></li>
                                    <li data-value="fa-building-o" <c:if test="${navigation.icon == 'fa-building-o'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-building-o"></i><span class="text padder">fa-building-o</span></a></li>
                                    <li data-value="fa-cloud" <c:if test="${navigation.icon == 'fa-cloud'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-cloud"></i><span class="text padder">fa-cloud</span></a></li>
                                    <li data-value="fa-comment" <c:if test="${navigation.icon == 'fa-comment'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-comment"></i><span class="text padder">fa-comment</span></a></li>
                                    <li data-value="fa-comments" <c:if test="${navigation.icon == 'fa-download'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-comments"></i><span class="text padder">fa-comments</span></a></li>
                                    <li data-value="fa-cutlery" <c:if test="${navigation.icon == 'fa-download'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-cutlery"></i><span class="text padder">fa-cutlery</span></a></li>
                                    <li data-value="fa-download" <c:if test="${navigation.icon == 'fa-download'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-download"></i><span class="text padder">fa-download</span></a></li>
                                    <li data-value="fa-plane" <c:if test="${navigation.icon == 'fa-plane'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-plane"></i><span class="text padder">fa-plane</span></a></li>
                                    <li data-value="fa-gavel" <c:if test="${navigation.icon == 'fa-gavel'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-gavel"></i><span class="text padder">fa-gavel</span></a></li>
                                    <li data-value="fa-headphones" <c:if test="${navigation.icon == 'fa-headphones'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-headphones"></i><span class="text padder">fa-headphones</span></a></li>
                                    <li data-value="fa-lemon-o" <c:if test="${navigation.icon == 'fa-lemon-o'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-lemon-o"></i><span class="text padder">fa-lemon-o</span></a></li>
                                    <li data-value="fa-location-arrow" <c:if test="${navigation.icon == 'fa-location-arrow'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-location-arrow"></i><span class="text padder">fa-location-arrow</span></a></li>
                                    <li data-value="fa-music" <c:if test="${navigation.icon == 'fa-music'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-music"></i><span class="text padder">fa-music</span></a></li>
                                    <li data-value="fa-shopping-cart" <c:if test="${navigation.icon == 'fa-shopping-cart'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-shopping-cart"></i><span class="text padder">fa-shopping-cart</span></a></li>
                                    <li data-value="fa-sitemap" <c:if test="${navigation.icon == 'fa-truck'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-sitemap"></i><span class="text padder">fa-sitemap</span></a></li>
                                    <li data-value="fa-truck" <c:if test="${navigation.icon == 'fa-truck'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-truck"></i><span class="text padder">fa-truck</span></a></li>
                                    <li data-value="fa-video-camera" <c:if test="${navigation.icon == 'fa-video-camera'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-video-camera"></i><span class="text padder">fa-video-camera</span></a></li>
                                    <li data-value="fa-gear" <c:if test="${navigation.icon == 'fa-gear'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-gear"></i><span class="text padder">fa-gear</span></a></li>
                                    <li data-value="fa-gears" <c:if test="${navigation.icon == 'fa-gears'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-gears"></i><span class="text padder">fa-gears</span></a></li>
                                    <li data-value="fa-film" <c:if test="${navigation.icon == 'fa-film'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-film"></i><span class="text padder">fa-film</span></a></li>
                                    <li data-value="fa-coffee" <c:if test="${navigation.icon == 'fa-coffee'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-coffee"></i><span class="text padder">fa-coffee</span></a></li>
                                    <li data-value="fa-bullhorn" <c:if test="${navigation.icon == 'fa-bullhorn'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-bullhorn"></i><span class="text padder">fa-bullhorn</span></a></li>
                                    <li data-value="fa-anchor" <c:if test="${navigation.icon == 'fa-anchor'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-anchor"></i><span class="text padder">fa-anchor</span></a></li>
                                    <li data-value="fa-archive" <c:if test="${navigation.icon == 'fa-archive'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-archive"></i><span class="text padder">fa-archive</span></a></li>
                                    <li data-value="fa-briefcase" <c:if test="${navigation.icon == 'fa-briefcase'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-briefcase"></i><span class="text padder">fa-briefcase</span></a></li>
                                    <li data-value="fa-user" <c:if test="${navigation.icon == 'fa-user'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-user"></i><span class="text padder">fa-user</span></a></li>
                                    <li data-value="fa-users" <c:if test="${navigation.icon == 'fa-wrench'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-users"></i><span class="text padder">fa-users</span></a></li>
                                    <li data-value="fa-trash-o" <c:if test="${navigation.icon == 'fa-wrench'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-trash-o"></i><span class="text padder">fa-trash-o</span></a></li>
                                    <li data-value="fa-wrench" <c:if test="${navigation.icon == 'fa-wrench'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-wrench"></i><span class="text padder">fa-wrench</span></a></li>
                                    <li data-value="fa-home" <c:if test="${navigation.icon == 'fa-gift'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-home"></i><span class="text padder">fa-home</span></a></li>
                                    <li data-value="fa-gift" <c:if test="${navigation.icon == 'fa-gift'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-gift"></i><span class="text padder">fa-gift</span></a></li>
                                    <li data-value="fa-gamepad" <c:if test="${navigation.icon == 'fa-gamepad'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-gamepad"></i><span class="text padder">fa-gamepad</span></a></li>
                                    <li data-value="fa-bug" <c:if test="${navigation.icon == 'fa-bug'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-bug"></i><span class="text padder">fa-bug</span></a></li>
                                    <li data-value="fa-book" <c:if test="${navigation.icon == 'fa-book'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-book"></i><span class="text padder">fa-book</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-3 control-label">ICON 背景</label>
                    <div class="col-sm-7">
                        <div class="btn-group m-r">
	                        <button class="btn btn-sm btn-default dropdown-toggle" data-toggle="dropdown" id="icon_bg_selected">
	                            <span class="dropdown-label padder <c:choose><c:when test="${navigation != null}">${navigation.iconBg}</c:when><c:otherwise>bg-white</c:otherwise></c:choose>">
	                                <c:choose><c:when test="${navigation != null}">${navigation.iconBg}</c:when><c:otherwise>bg-white</c:otherwise></c:choose>
	                            </span> 
	                            <span class="caret"></span>
	                        </button>
	                        <ul class="dropdown-menu dropdown-select" id="icon_bg_ul">
	                            <li <c:if test="${navigation.iconBg == 'bg-white'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-white">bg-white
                                        <label class="label bg-white form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${navigation.iconBg ==  'bg-light'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-light">bg-light
                                        <label class="label bg-light form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${navigation.iconBg ==  'bg-warning'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-warning">bg-warning
                                        <label class="label bg-warning form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${navigation.iconBg ==  'bg-danger'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-danger">bg-danger
                                        <label class="label bg-danger form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${navigation.iconBg ==  'bg-info'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-info">bg-info
                                        <label class="label bg-info form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${navigation.iconBg ==  'bg-success'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-success">bg-success
                                        <label class="label bg-success form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${navigation.iconBg ==  'bg-primary'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-primary">bg-primary
                                        <label class="label bg-primary form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${navigation.iconBg ==  'bg-dark'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-dark">bg-dark
                                        <label class="label bg-dark form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${navigation.iconBg ==  'bg-black'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-black">bg-black
                                        <label class="label bg-black form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                        </ul>
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-sm-3 control-label">是否显示</label>
                    <div class="col-sm-7">
                        <label class="checkbox-inline p-left-0">
                            <input type="radio" name="status" value="1" <c:if test="${navigation.status == 1 || ! $navigation.status}">checked</c:if> /> 显示
                        </label>
                        <label class="checkbox-inline ">
                            <input type="radio" name="status" value="0" <c:if test="${navigation.status == 0}">checked</c:if> /> 隐藏
                        </label>
                    </div>
                </div>
                
                <input type="hidden" name="navigationId" value="${navigation.navigationId}" />
                <%-- <input type="hidden" name="ctime" value="${navigation.ctime}" /> --%>
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" id="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存并返回列表管理</button>
            <c:if test="${navigation != null}"><button type="button" id="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
            <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal">取消</button>
        </div>
    </div>
</div>
<!-- / modal - 编辑导航 -->

<script src="${STATIC_URL}/modules/system/js/navigation.edit.js"></script>
<!-- {literal} -->
<script type="text/javascript">
    /* 重置父导航 */
    $("#resetParentNavigation").click(function() {
        $('#parent_id').get(0).selectedIndex = 0;
    });
</script>
<!-- {/literal} -->