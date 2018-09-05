<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- .aside -->

<aside class="bg-dark lter aside-md hidden-print" id="nav">
    <section class="vbox">
		<input type="hidden" id="currentNavId" value="${currentNavId}">
        <section class="w-f scrollable">
            <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="5px" data-color="#333333">
                <!-- nav start -->
                <div class="tab-content">
	                <div id="nav_content" class="tab-pane fade active in nav-content">
		                <nav class="nav-primary hidden-xs">
		                    <ul class="nav" id="left_nav">
								
		                    	<c:forEach items="${adminMenuTOs}" var="firstAdminMenuTO">
			                        <li>
			                            <a href="javascript:;" class="nav-two" >
			                                <i class="fa icon ${firstAdminMenuTO.icon}">
			                                    <b class="${firstAdminMenuTO.iconBg==null?'bg-info':firstAdminMenuTO.iconBg}"></b>
			                                </i>
			                                <!-- <i class="fa icon fa-user">
                                                <b class="bg-info"></b>
                                            </i> -->
			                                <span class="pull-right">
			                                    <i class="fa fa-angle-down text"></i>
			                                    <i class="fa fa-angle-up text-active"></i>
			                                </span>
			                                <span>${firstAdminMenuTO.name}</span>
			                            </a>
			                            <ul class="nav lt nav-three">
			                            	<c:forEach items="${firstAdminMenuTO.subList}" var="secondAdminMenuTO">
				                                <li>
				                               		 <a href="${BASE_URL}${secondAdminMenuTO.url}" class="load-content" id="${secondAdminMenuTO.menuId}" url="${secondAdminMenuTO.url}">
				                                        <i class="fa fa-angle-right"></i>
				                                        <span>${secondAdminMenuTO.name}</span>
				                                    </a>
				                                </li>
			                                </c:forEach>
			                            </ul>
			                        </li>
		                        </c:forEach>
		                        
		                         
		                        <!--生成的静态导航，临时使用，可删除-->
		                        <jsp:include page="temp.jsp"/>
		                        
		                    </ul>
		                </nav>
	                </div>
                </div>
                <!-- nav end -->
            </div>
        </section>

        <footer class="footer lt hidden-xs b-t b-dark">
            <a href="#nav" data-toggle="class:nav-xs" class="pull-right btn btn-sm btn-dark btn-icon">
                <i class="fa fa-angle-left text"></i>
                <i class="fa fa-angle-right text-active"></i>
            </a>
        </footer>
    </section>
</aside>

<!-- /.aside -->