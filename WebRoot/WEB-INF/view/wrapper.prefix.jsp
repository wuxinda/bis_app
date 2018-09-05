<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<c:if test="${!isAjax}">

<!DOCTYPE html>
<html lang="zh-CN" class="app">
<head>
<title>管理员后台</title>

<meta charset="utf-8" />
<meta name="description" content="zeeddemo" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="${STATIC_URL}/plugins/bootstrap/3.1.0/css/bootstrap.min.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/plugins/bootstrap/3.1.0/css/bootstrap-multiselect.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/css/animate.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/css/font-awesome.min.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/css/apps.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/plugins/fuelux/fuelux.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/css/reset.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/plugins/jquery-pnotify/pnotify.custom.min.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/css/sessionTimeout.css" type="text/css"/>
<link rel="stylesheet" href="${STATIC_URL}/css/bm.index.css" type="text/css"/>
<script src="${STATIC_URL}/plugins/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" language="javaScript">
	// 动态获得地址配置
   	var BASE_URL = '${BASE_URL}';
    var STATIC_URL = '${STATIC_URL}';
    var IMG_URL = '${IMG_URL}';
    var TEMP_IMG_URL = '${TEMP_IMG_URL}';
    var USER_TYPE='${loggedInUser.userType}';
</script>
</head>

<body>
<div class="hide">
	
</div>
<!-- .vbox -->
<section class="vbox">
	<!-- top head start-->
    <header class="bg-dark dk header navbar navbar-fixed-top-xs">
	    <div class="navbar-header aside-md">
	        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">
	            <i class="fa fa-bars"></i>
	        </a>
	        <a href="javascript:;" class="navbar-brand" data-toggle="fullscreen">
	            <img src="${STATIC_URL}/images/logo.jpeg" class="m-r img-circle">${SITE_NAME}
	        </a>
	        <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".nav-user">
	            <i class="fa fa-cog"></i>
	        </a>
	    </div>
	    <ul class="nav navbar-nav hidden-xs">
	        <li class="dropdown">
	            <a href="${BASE_URL}/" class="dropdown-toggle dker">
	                <i class="fa fa-building-o"></i> 
	                <span class="font-bold">返回前台</span>
	            </a>
	        </li>
	    </ul>
	    <%-- <ul class="nav navbar-nav hidden-xs">
	        <li class="dropdown">
	            <a href="#" class="dropdown-toggle dker" data-toggle="dropdown">
	                <i class="fa fa-building-o"></i> 
	                <span class="font-bold">面板</span>
	            </a>
	            <section class="dropdown-menu aside-xl on animated fadeInLeft no-borders lt">
	                <div class="wrapper lter m-t-n-xs">
	                    <a href="#" class="thumb pull-left m-r">
	                        <img src="${STATIC_URL}/images/avatar.jpg" class="img-circle">
	                    </a>
	                    <div class="clear"><!-- @{$loggedInUser.fullname} -->
	                        <a href="javascript:;"><span class="text-white font-bold">${loggedInUser.userName}</span></a>
	                        <small class="block">超级管理员</small>
	                    </div>
	                </div>
	            </section>
	        </li>
	    </ul> --%>
	     
	    <ul class="nav navbar-nav navbar-right hidden-xs nav-user">
	        <li class="dropdown">
	            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	                <span class="thumb-sm avatar pull-left">
	                    <img src="${STATIC_URL}/images/avatar_default.jpg">
	                </span>
	                ${loggedInUser.userName}<b class="caret"></b>
	            </a>
	            <ul class="dropdown-menu animated fadeInRight">
	                <li>
	                    <a href="${BASE_URL}/adminLogin/logout" class="sign-out">注销</a>
	                    <a href="${BASE_URL}/adminUser/changepassword?user=${loggedInUser.userId}" class="sign-changepassword">修改密码</a>
	                </li>
	            </ul>
	        </li>
	    </ul>      
	</header>
    <!-- top head end-->
    
    <!-- left  start-->
    <section>
        <section class="hbox stretch">
			<jsp:include page="menu.jsp"/>
            <section id="content">
</c:if>

<script type="text/javascript" language="javaScript">
    <% 
    	//对比用户是否有此权限，如果没有，就删除按钮
    	List<String> codeList = (ArrayList)request.getAttribute("codeList");
    	Map<String,Boolean> permissionCodeMap = (HashMap)application.getAttribute("permissionCodeMap");
    	
    	out.println("function refreshPermission(){");
    	for(String code:permissionCodeMap.keySet()){
    		if(!codeList.contains(code)){
    			out.println("$('.bmpms-"+code+"').remove();" );
    		}else{
    			out.println("$('.bmpms-"+code+"').removeClass('hide');" );
    		}
    	} 
    	out.println("};" );
    %>
</script>

