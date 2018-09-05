<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${adminMenu!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${adminMenu!=null}">"${BASE_URL}/adminMenu/edit"</c:when><c:otherwise>"${BASE_URL}/adminMenu/add"</c:otherwise></c:choose> method="post">
					
                    <div class="form-group">
                        <label for="pid" class="col-sm-3 control-label">父级菜单</label>
						<div class="col-sm-4">
		                    <select name="pid" id="pid" class="form-control">
	                            	<option value="0">无</option>
	                            <c:forEach items="${adminMenus}" var="menu">
	                            	<option value="${menu.menuId}" <c:if test="${ menu.menuId == adminMenu.pid}">selected</c:if>>${menu.name}</option>
	                        	</c:forEach>
	                        </select>
	              		 </div>
                    </div>
                    <div class="line line-dashed line pull-in"></div>
                    
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">菜单名称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name" value="${adminMenu.name}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
                    <div class="line line-dashed line pull-in"></div>
                    
                    <div class="form-group">
                        <label for="url" class="col-sm-3 control-label">链接</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="url" name="url" value="${adminMenu.url}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
					
                <div class="form-group">
                    <label for="icon" class="col-sm-3 control-label">ICON 图标</label>
                    <div class="col-sm-4">
                        <div class="input-group dropdown combobox" id="myCombobox">
                            <input type="text" name="icon" class="form-control" value="<c:choose><c:when test="${adminMenu != null}">${adminMenu.icon}</c:when><c:otherwise>fa-file-text</c:otherwise></c:choose>" />
                            <div class="input-group-btn">
                                <button data-toggle="dropdown" class="btn btn-default dropdown-toggle" type="button"><i class="caret"></i></button>
                                <ul class="dropdown-menu pull-right">
                                    <li data-value="fa-file-text" <c:if test="${adminMenu.icon == 'fa-file-text'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-file-text"></i><span class="text padder">fa-file-text</span></a></li>
                                    <li data-value="fa-bar-chart-o" <c:if test="${adminMenu.icon == 'fa-bar-chart-o'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-bar-chart-o"></i><span class="text padder">fa-bar-chart-o</span></a></li>
                                    <li data-value="fa-bell" <c:if test="${adminMenu.icon == 'fa-bell'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-bell"></i><span class="text padder">fa-bell</span></a></li>
                                    <li data-value="fa-building-o" <c:if test="${adminMenu.icon == 'fa-building-o'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-building-o"></i><span class="text padder">fa-building-o</span></a></li>
                                    <li data-value="fa-cloud" <c:if test="${adminMenu.icon == 'fa-cloud'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-cloud"></i><span class="text padder">fa-cloud</span></a></li>
                                    <li data-value="fa-comment" <c:if test="${adminMenu.icon == 'fa-comment'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-comment"></i><span class="text padder">fa-comment</span></a></li>
                                    <li data-value="fa-comments" <c:if test="${adminMenu.icon == 'fa-download'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-comments"></i><span class="text padder">fa-comments</span></a></li>
                                    <li data-value="fa-cutlery" <c:if test="${adminMenu.icon == 'fa-download'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-cutlery"></i><span class="text padder">fa-cutlery</span></a></li>
                                    <li data-value="fa-download" <c:if test="${adminMenu.icon == 'fa-download'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-download"></i><span class="text padder">fa-download</span></a></li>
                                    <li data-value="fa-plane" <c:if test="${adminMenu.icon == 'fa-plane'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-plane"></i><span class="text padder">fa-plane</span></a></li>
                                    <li data-value="fa-gavel" <c:if test="${adminMenu.icon == 'fa-gavel'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-gavel"></i><span class="text padder">fa-gavel</span></a></li>
                                    <li data-value="fa-headphones" <c:if test="${adminMenu.icon == 'fa-headphones'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-headphones"></i><span class="text padder">fa-headphones</span></a></li>
                                    <li data-value="fa-lemon-o" <c:if test="${adminMenu.icon == 'fa-lemon-o'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-lemon-o"></i><span class="text padder">fa-lemon-o</span></a></li>
                                    <li data-value="fa-location-arrow" <c:if test="${adminMenu.icon == 'fa-location-arrow'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-location-arrow"></i><span class="text padder">fa-location-arrow</span></a></li>
                                    <li data-value="fa-music" <c:if test="${adminMenu.icon == 'fa-music'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-music"></i><span class="text padder">fa-music</span></a></li>
                                    <li data-value="fa-shopping-cart" <c:if test="${adminMenu.icon == 'fa-shopping-cart'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-shopping-cart"></i><span class="text padder">fa-shopping-cart</span></a></li>
                                    <li data-value="fa-sitemap" <c:if test="${adminMenu.icon == 'fa-truck'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-sitemap"></i><span class="text padder">fa-sitemap</span></a></li>
                                    <li data-value="fa-truck" <c:if test="${adminMenu.icon == 'fa-truck'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-truck"></i><span class="text padder">fa-truck</span></a></li>
                                    <li data-value="fa-video-camera" <c:if test="${adminMenu.icon == 'fa-video-camera'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-video-camera"></i><span class="text padder">fa-video-camera</span></a></li>
                                    <li data-value="fa-gear" <c:if test="${adminMenu.icon == 'fa-gear'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-gear"></i><span class="text padder">fa-gear</span></a></li>
                                    <li data-value="fa-gears" <c:if test="${adminMenu.icon == 'fa-gears'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-gears"></i><span class="text padder">fa-gears</span></a></li>
                                    <li data-value="fa-film" <c:if test="${adminMenu.icon == 'fa-film'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-film"></i><span class="text padder">fa-film</span></a></li>
                                    <li data-value="fa-coffee" <c:if test="${adminMenu.icon == 'fa-coffee'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-coffee"></i><span class="text padder">fa-coffee</span></a></li>
                                    <li data-value="fa-bullhorn" <c:if test="${adminMenu.icon == 'fa-bullhorn'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-bullhorn"></i><span class="text padder">fa-bullhorn</span></a></li>
                                    <li data-value="fa-anchor" <c:if test="${adminMenu.icon == 'fa-anchor'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-anchor"></i><span class="text padder">fa-anchor</span></a></li>
                                    <li data-value="fa-archive" <c:if test="${adminMenu.icon == 'fa-archive'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-archive"></i><span class="text padder">fa-archive</span></a></li>
                                    <li data-value="fa-briefcase" <c:if test="${adminMenu.icon == 'fa-briefcase'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-briefcase"></i><span class="text padder">fa-briefcase</span></a></li>
                                    <li data-value="fa-user" <c:if test="${adminMenu.icon == 'fa-user'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-user"></i><span class="text padder">fa-user</span></a></li>
                                    <li data-value="fa-users" <c:if test="${adminMenu.icon == 'fa-wrench'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-users"></i><span class="text padder">fa-users</span></a></li>
                                    <li data-value="fa-trash-o" <c:if test="${adminMenu.icon == 'fa-wrench'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-trash-o"></i><span class="text padder">fa-trash-o</span></a></li>
                                    <li data-value="fa-wrench" <c:if test="${adminMenu.icon == 'fa-wrench'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-wrench"></i><span class="text padder">fa-wrench</span></a></li>
                                    <li data-value="fa-home" <c:if test="${adminMenu.icon == 'fa-gift'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-home"></i><span class="text padder">fa-home</span></a></li>
                                    <li data-value="fa-gift" <c:if test="${adminMenu.icon == 'fa-gift'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-gift"></i><span class="text padder">fa-gift</span></a></li>
                                    <li data-value="fa-gamepad" <c:if test="${adminMenu.icon == 'fa-gamepad'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-gamepad"></i><span class="text padder">fa-gamepad</span></a></li>
                                    <li data-value="fa-bug" <c:if test="${adminMenu.icon == 'fa-bug'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-bug"></i><span class="text padder">fa-bug</span></a></li>
                                    <li data-value="fa-book" <c:if test="${adminMenu.icon == 'fa-book'}">class="active"</c:if>><a href="javascript:;"><i class="fa fa-book"></i><span class="text padder">fa-book</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="line line-dashed line pull-in"></div>
                
                <div class="form-group">
                    <label class="col-sm-3 control-label">ICON 背景</label>
                    <div class="col-sm-4">
                        <div class="btn-group m-r">
	                        <button class="btn btn-sm btn-default dropdown-toggle" data-toggle="dropdown" id="icon_bg_selected">
	                            <span class="dropdown-label padder <c:choose><c:when test="${adminMenu != null}">${adminMenu.iconBg}</c:when><c:otherwise>bg-white</c:otherwise></c:choose>">
	                                <c:choose><c:when test="${adminMenu != null}">${adminMenu.iconBg}</c:when><c:otherwise>bg-white</c:otherwise></c:choose>
	                            </span> 
	                            <span class="caret"></span>
	                        </button>
	                        <ul class="dropdown-menu dropdown-select" id="icon_bg_ul">
	                            <li <c:if test="${adminMenu.iconBg == 'bg-white'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-white">bg-white
                                        <label class="label bg-white form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${adminMenu.iconBg ==  'bg-light'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-light">bg-light
                                        <label class="label bg-light form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${adminMenu.iconBg ==  'bg-warning'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-warning">bg-warning
                                        <label class="label bg-warning form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${adminMenu.iconBg ==  'bg-danger'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-danger">bg-danger
                                        <label class="label bg-danger form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${adminMenu.iconBg ==  'bg-info'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-info">bg-info
                                        <label class="label bg-info form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${adminMenu.iconBg ==  'bg-success'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-success">bg-success
                                        <label class="label bg-success form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${adminMenu.iconBg ==  'bg-primary'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-primary">bg-primary
                                        <label class="label bg-primary form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${adminMenu.iconBg ==  'bg-dark'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-dark">bg-dark
                                        <label class="label bg-dark form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                            <li <c:if test="${adminMenu.iconBg ==  'bg-black'}">class="active"</c:if>>
	                                <a href="javascript:;"><input type="radio" name="iconBg" value="bg-black">bg-black
                                        <label class="label bg-black form-control-static pos-rlt pull-right m-t-nx col-sm-5">&nbsp;</label>
                                    </a>
	                            </li>
	                        </ul>
                        </div>
                    </div>
                </div>
				<div class="line line-dashed line pull-in"></div>
				
                    <div class="form-group">
                        <label for="sortOrder" class="col-sm-3 control-label">序号</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="sortOrder" name="sortOrder" value="${adminMenu.sortOrder}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
					
					<div class="form-group">
				        <label class="col-sm-3 control-label">状态</label>
				        <div class="col-sm-4">
				            <select id="status" name="status" class="form-control">
				            		<option value="1" <c:if test="${adminMenu.status == 1}">selected="selected"</c:if>>正常</option>
				                	<option value="0" <c:if test="${adminMenu.status == 0}">selected="selected"</c:if>>不显示</option>
				            </select>
				        </div>
				    </div>
                    <div class="line line-dashed line-lg pull-in"></div>
                    
                    <input type="hidden" name="menuId" value="${adminMenu.menuId}" />

                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${adminMenu==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
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
<script src="${STATIC_URL}/modules/admin/js/menu.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->