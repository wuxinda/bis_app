<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../wrapper.prefix.jsp"/>

<link href="${STATIC_URL}/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
  .app .hbox.stretch {
    height: 99%;
    }
    *{
	   margin:0px;
	   padding:0px;
	}
	ul li{
	  list-style:none;
	}
	#shenfen .nav >li ,#chengshi .nav >li ,#quyu .nav >li{
		  padding: 10px 15px;
		  border-bottom: 1px solid #E4E4E4;
	}
	.linkTip{
		font-size: 14px;
		background-color: rgba(0,0,0,0.05);
		font-weight: 600;
		padding:15px 20px;
	}
	@media (min-width: 768px) {
 
  .hbox {
    display: table;
    table-layout: fixed;
    border-spacing: 0;
    width: 100%;
  }
  .hbox > aside,
  .hbox > section {
    display: table-cell;
    vertical-align: top;
    height: 100%;
    padding: 0;
    float: none;
  }
  .hbox > aside.show,
  .hbox > section.show,
  {
    display: table-cell !important;
  }
  .vbox {
    display: table;
    border-spacing: 0;
    position: relative;
    height: 100%;
    width: 100%;
  }
  .b-b {
  border-bottom: 1px solid #cfcfcf;
}
.b-l {
  border-left: 1px solid #cfcfcf;
}
.b-light {
  border-color: #e4e4e4;
}
  }
</style>
	<body>
		<section class="vbox">
			<section>
				<section class="hbox stretch">
					<section id="content">
						<section class="vbox">
		  <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-8 m-b-xs">
                        <!-- <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-default action-refresh" title="返回"><i class="fa fa-refresh"></i></button>                           
                        </div> -->
                        <div class="btn-group">
                                                                                                    选择库房，设备分类及品牌
                        </div>
                    </div>
	                <div class="col-sm-4 m-b-xs">
	                    <div class="input-group">
	                        <span class="input-group-btn" id="addDevice">
	                        </span>
	                    </div>
	                </div>
                </div>
            </header>
							<section class="scrollable">
								<section class="hbox stretch">
									<aside class="aside-lg bg-light lter b-r m_zlxg" id="shenfen">
										<p class="wrapper b-b header linkTip" id="category1">请选择库房</p>
										<a href="javascript:;">
										<div class="m_zlxg2">
									    <ul class="nav">
										<c:forEach items="${wmsStore}" var="s">
										<li onclick="categoryName1('${s.storeId}','${s.name}')">${s.name}</li>
										</c:forEach>
											</ul>
										</div>
										</a>
									</aside>
									<aside class="col-lg-4 b-l bg-white m_zlxg" id="quyu">
										<p class="wrapper b-b header linkTip" id="category4">请选择库区</p>
									    <a href="javascript:;">
										<div class="m_zlxg2">
											<ul class="nav" id="categoryName4"></ul>
										</div>
										</a>
									</aside>
									<aside class="col-lg-4 b-l bg-white m_zlxg" id="chengshi">
										<p class="wrapper b-b header linkTip" id="category2">请选择设备分类</p>
										<a href="javascript:;">
										<div class="m_zlxg2">
									    <ul class="nav">
										<c:forEach items="${deviceCategory}" var="c">
										<li onclick="categoryName2('${c.categoryId}','${c.name}')">${c.name}</li>
										</c:forEach>
											</ul>
										</div>
										</a>
									</aside>
									<aside class="col-lg-4 b-l bg-white m_zlxg" id="quyu">
										<p class="wrapper b-b header linkTip" id="category3">请选择品牌</p>
									   <a href="javascript:;">
										<div class="m_zlxg2">
											<ul class="nav" id="categoryName3"></ul>
										</div>
										</a>
									</aside>

								</section>
							</section>
						</section>
						<a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
					</section>
					<aside class="bg-light lter b-l aside-md hide" id="notes">
						<div class="wrapper">Notification</div>
					</aside>
				</section>
			</section>
		</section>
	</body>

<jsp:include page="../wrapper.suffix.jsp" />
<script src="${STATIC_URL}/modules/device/js/manage.edit.category.js" type="text/javascript"></script>
