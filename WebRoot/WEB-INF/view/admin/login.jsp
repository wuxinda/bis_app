<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="zh-CN" class="bg-dark js no-touch no-android no-chrome firefox no-iemobile no-ie no-ie10 no-ie11 no-ios">
<head>
	<meta charset="utf-8" />
	<title>${SITE_NAME}</title>
	<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  
    <link rel="stylesheet" href="${STATIC_URL}/plugins/bootstrap/3.1.0/css/bootstrap.min.css" type="text/css"/>
	<link rel="stylesheet" href="${STATIC_URL}/css/animate.css" type="text/css"/>
	<link rel="stylesheet" href="${STATIC_URL}/css/apps.css" type="text/css"/>
	<link rel="stylesheet" href="${STATIC_URL}/modules/admin/css/login.css" type="text/css"/>
	
	
    <script type="text/javascript">
	    // 动态获得地址配置
	    var BASE_URL = '${BASE_URL}';
	    var STATIC_URL = '${STATIC_URL}';
    </script>
</head>

<body>
<!-- 主页请求跳转 -->
<a class="navbar-brand block" href="/admin">${SITE_NAME}</a>
<div>
<section id="aui_iwrapper" class="animated fadeInUp aui-iwrapper">
    <section class="panel panel-default bg-white m-t-lg">
        <header class="panel-heading text-center">
            <strong>登录</strong>
        </header>
        <form action="${BASE_URL}/adminLogin/login" id="form_sign"  class="panel-body wrapper-lg" method="post" title="登录" data-validate="parsley">
            <div class="form-group">
                <label class="control-label">用户名</label>
                <input type="text" name="username" class="form-control" id="username" placeholder="用户名" data-maxlength="20" data-minlength="3" data-required="true" data-trigger="change" value="${login_username}" />
                <span class="notice notice-username"></span>
            </div>
            <div class="form-group">
                <label class="control-label">密码</label>
                <input type="password" name="password" class="form-control" id="inputPassword" placeholder="密码" data-required="true" data-trigger="change" />
                <span class="notice notice-password"></span>
            </div>
            <div class="form-group">
                <label class="control-label">验证码</label>	
                <input type="text" name="captcha" class="captcha form-control" id="login-captcha" placeholder="验证码" data-maxlength="6"  data-required="true" data-trigger="change" autocomplete="off" />
                <span class="notice notice-captcha"></span>
            </div>
            <div class="form-group">
                <img src="${BASE_URL}/adminLogin/code" alt="点击换一张" id="vcodeimg" title="看不清楚，换一张" style="cursor:pointer;" onclick="this.src='${BASE_URL}/adminLogin/code?t='+Math.random();" >
            </div>
            <button type="submit" id="sign_submit" class="btn btn-primary">登录</button>
            <button type="reset" class="btn btn-info">重置</button>
            <input type="hidden" name="urlContinue" value="${urlContinue}" />
            <div class="line line-dashed"></div>
            <h4 class="text text-danger notice-back"></h4>
        </form>
    </section>
</section>

<!-- footer -->
<footer id="footer">
    <div class="text-center padder">
        <p>
            <small>上海物佳网络科技有限公司<br>&copy; 2017</small>
        </p>    </div>
</footer>
</div>
</body>
<script src="${STATIC_URL}/plugins/jquery/1.11.0/jquery.min.js"></script>
<script src="${STATIC_URL}/plugins/jquery-form/3.03/jquery.form.js"></script>
<script src="${STATIC_URL}/modules/admin/js/login.js"></script>
</html>