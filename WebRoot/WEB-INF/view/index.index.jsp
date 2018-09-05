<%@ page contentType="text/html;charset=UTF-8"%>

<jsp:include page="wrapper.prefix.jsp" />

<section class="vbox">
	<header class="header bg-white b-b clearfix">
		<p class="h4">Welcome, ${loggedInUser.userName}</p>
	</header>

	<section class="scrollable wrapper w-f">
		<div class="clearfix">
			<div class="row m-b-xl padder">
				<div class="col-sm-3 animated fadeInLeftBig">
					<section class="panel b-light text-center m-t">
						<header class="panel-heading bg-white b-light">
							<div class="row m-l-none m-r-none bg-light lter">
								<div class="padder-v b-light">
									<span class="fa-stack fa-2x pull-left m-r-sm m-t-sm">
										<i class="fa fa-circle fa-stack-2x text-info"></i>
										<i class="fa fa-file-text-o fa-stack-1x text-white"></i>
									</span>
									<a class="clear text-left padder-l-md" href="${BASE_URL}/casuser/index">
										<span class="h3 block m-t-xs"><strong>${totalOrder}</strong></span>
										<small class="text-muted text-uc">档案管理</small>
									</a>
								</div>
							</div>
						</header>
						<ul class="list-group">
							<li class="list-group-item">在库：<a href="${BASE_URL}/casuser/index" class="text-danger font-bold">${sendOrder}</a>个</li>
							<li class="list-group-item">出库：<a href="${BASE_URL}/casuser/index" class="text-danger font-bold">${backOrder}</a>个</li>
							<li class="list-group-item">&nbsp;</li>
						</ul>
						<footer class="panel-footer">
							<a class="btn btn-primary m-t m-b" href="${BASE_URL}/casuser/index">查看详细档案信息</a>
						</footer>
					</section>
				</div>

				<div class="col-sm-3 animated fadeInUp">
					<section class="panel b-light text-center m-t">
						<header class="panel-heading bg-white b-light">
							<div class="row m-l-none m-r-none">
								<div class="padder-v b-light">
									<span class="fa-stack fa-2x pull-left m-r-sm m-t-sm">
										<i class="fa fa-circle fa-stack-2x text-danger"></i>
										<i class="fa fa-phone fa-stack-1x text-white"></i>
									</span>
									<a class="clear text-left padder-l-md" href="${BASE_URL}/order/index">
										<span class="h3 block m-t-xs"><strong>${totalComplaint}</strong></span>
										<small class="text-muted text-uc">联系人</small>
									</a>
								</div>
							</div>
						</header>
						<ul class="list-group">
							<li class="list-group-item">领导者：<a href="${BASE_URL}/order/index" class="text-danger font-bold">${replyComplaint}</a>个</li>
							<li class="list-group-item">操作者：<a href="${BASE_URL}/order/index" class="text-danger font-bold">${replyComplaint}</a>个</li>
							<li class="list-group-item">&nbsp;</li>
						</ul>
						<footer class="panel-footer">
							<a class="btn btn-primary m-t m-b" href="${BASE_URL}/order/index">查看全部联系人信息</a>
						</footer>
					</section>
				</div>

				<div class="col-sm-3 animated fadeInDown">
					<section class="panel b-light text-center m-t">
						<header class="panel-heading bg-white b-light">
							<div class="row m-l-none m-r-none">
								<div class="padder-v b-light">
									<span class="fa-stack fa-2x pull-left m-r-sm m-t-sm">
										<i class="fa fa-circle fa-stack-2x text-success"></i>
										<i class="fa fa-envelope-o fa-stack-1x text-white"></i>
									</span>
									<a class="clear text-left padder-l-md" href="${BASE_URL}/goodsContent/index">
										<span class="h3 block m-t-xs"><strong>${totalMessage}</strong></span>
										<small class="text-muted text-uc">消息管理</small>
									</a>
								</div>
							</div>
						</header>
						<ul class="list-group">
							<li class="list-group-item">未读消息：<a href="${BASE_URL}/goodsContent/index" class="text-danger font-bold">${unreadMessage}</a>个</li>
							<li class="list-group-item">已读消息：<a href="${BASE_URL}/goodsContent/index" class="text-danger font-bold">${unreadMessage}</a>个</li>
							<li class="list-group-item">&nbsp;</li>
						</ul>
						<footer class="panel-footer">
							<a class="btn btn-primary m-t m-b" href="${BASE_URL}/goodsContent/index">查看全部消息</a>
						</footer>
					</section>
				</div>
			</div>
		</div>

		<div class="row padder">
			<div class="col-sm-3 animated fadeInDown">
				<section class="panel b-light text-center m-t">
					<header class="panel-heading bg-white b-light">
						<div class="row m-l-none m-r-none">
							<div class="padder-v b-light">
								<span class="fa-stack fa-2x pull-left m-r-sm m-t-sm">
									<i class="fa fa-circle fa-stack-2x text-primary"></i>
									<i class="fa fa-file-o fa-stack-1x text-white"></i>
								</span>
								<a class="clear text-left padder-l-md" href="${BASE_URL}/goodsContent/index">
									<span class="h3 block m-t-xs"><strong>${totalGoods}</strong></span>
									<small class="text-muted text-uc">环境管理</small>
								</a>
							</div>
						</div>
					</header>
					<ul class="list-group">
							<li class="list-group-item">温度：<a href="${BASE_URL}/goodsContent/index" class="text-danger font-bold"><%-- ${unreadMessage} --%></a>正常</li>
							<li class="list-group-item">湿度：<a href="${BASE_URL}/goodsContent/index" class="text-danger font-bold"><%-- ${unreadMessage} --%></a>正常</li>
							<li class="list-group-item">&nbsp;</li>
						</ul>
						<footer class="panel-footer">
							<a class="btn btn-primary m-t m-b" href="${BASE_URL}/goodsContent/index">查看环境</a>
						</footer>
				</section>
			</div>
			<div class="col-sm-3 animated fadeInDown">
				<section class="panel b-light text-center m-t">
					<header class="panel-heading bg-white b-light">
						<div class="row m-l-none m-r-none">
							<div class="padder-v b-light">
								<span class="fa-stack fa-2x pull-left m-r-sm m-t-sm">
									<i class="fa fa-circle fa-stack-2x text-primary"></i>
									<i class="fa fa-user fa-stack-1x text-white"></i>
								</span>
								<a class="clear text-left padder-l-md" href="${BASE_URL}/goodsContent/index">
									<span class="h3 block m-t-xs"><strong>${totalMerchant}</strong></span> 
									<small class="text-muted text-uc">报警</small>
								</a>
							</div>
						</div>
					</header>
					<ul class="list-group">
							<li class="list-group-item">未处理报警：<a href="${BASE_URL}/goodsContent/index" class="text-danger font-bold">${unreadMessage}</a>个</li>
							<li class="list-group-item">已处理报警：<a href="${BASE_URL}/goodsContent/index" class="text-danger font-bold">${unreadMessage}</a>个</li>
							<li class="list-group-item">&nbsp;</li>
						</ul>
						<footer class="panel-footer">
							<a class="btn btn-primary m-t m-b" href="${BASE_URL}/goodsContent/index">查看全部报警</a>
						</footer>
				</section>
			</div>
			<div class="col-sm-3 animated fadeInDown">
				<section class="panel b-light text-center m-t">
					<header class="panel-heading bg-white b-light">
						<div class="row m-l-none m-r-none">
							<div class="padder-v b-light">
								<span class="fa-stack fa-2x pull-left m-r-sm m-t-sm">
									<i class="fa fa-circle fa-stack-2x text-primary"></i>
									<i class="fa fa-user fa-stack-1x text-white"></i>
								</span>
								<a class="clear text-left padder-l-md" href="${BASE_URL}/goodsContent/index">
									<span class="h3 block m-t-xs"><strong>${totalMerchant}</strong></span> 
									<small class="text-muted text-uc">库存</small>
								</a>
							</div>
						</div>
					</header>
					<ul class="list-group">
							<li class="list-group-item">已用库存：<a href="${BASE_URL}/goodsContent/index" class="text-danger font-bold">${unreadMessage}</a>个</li>
							<li class="list-group-item">剩余库存：<a href="${BASE_URL}/goodsContent/index" class="text-danger font-bold">${unreadMessage}</a>个</li>
							<li class="list-group-item">&nbsp;</li>
						</ul>
						<footer class="panel-footer">
							<a class="btn btn-primary m-t m-b" href="${BASE_URL}/goodsContent/index">查看库存详情</a>
						</footer>
				</section>
			</div>
		</div>
	</section>
</section>

<jsp:include page="wrapper.suffix.jsp" />
