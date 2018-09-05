<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp"/>

<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4">修改密码</p>
            </header>
            <section class="scrollable wrapper w-f">
            	<form class="form-horizontal" id="edit_form" action="${BASE_URL}/adminUser/changepassword" method="post">
            		<div class="form-group">
                		<label for="name" class="col-sm-3 control-label"><span class="red">*</span>原密码</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="oldpass" name="oldpass" value="" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                	</div>
                	<div class="form-group">
                		<label for="name" class="col-sm-3 control-label"><span class="red">*</span>新密码</label>
                        <div class="col-sm-4">
                            <input type="password" class="form-control" id="newpass" name="newpass" value="" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                	</div>
                	<div class="form-group">
                		<label for="name" class="col-sm-3 control-label"><span class="red">*</span>确认密码</label>
                        <div class="col-sm-4">
                            <input type="password" class="form-control" id="conformpass" name="conformpass" value="" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                	</div>
                	<div class="from-group"></div>
                	<input type="hidden" id="userId" name="userId" value="${userId}" />
            	</form>
            </section>
            <footer class="footer b-t bg-white-only">
            	<div class="m-t-sm">
            		<button type="button" data_submit_type="submit_save_submit" class="btn btn-s-md btn-primary btn-sm input-submit">确定修改</button>
            		<button type="button" data_submit_type="submit_cancel" class="btn btn-s-md btn-primary btn-sm input-submit">取消</button>
            		<span id="edit_notice"></span>
            	</div>
            </footer>
        </section>
    </aside>
</section>
<jsp:include page="../wrapper.suffix.jsp"/>

<script src="${STATIC_URL}/modules/admin/js/changePassword.js" type="text/javascript"></script>