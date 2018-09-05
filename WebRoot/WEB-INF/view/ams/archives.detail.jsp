<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../wrapper.prefix.jsp" />

<section class="hbox stretch">
	<aside class="bg-white">
		<section class="vbox">
			<header class="header bg-white b-b clearfix">
				<div class="m-t-sm">
					<a href="#subNav" data-toggle="class:hide"
						class="btn btn-sm btn-default active btn-nav-goods"
						btn_nav_goods_index="0"> <i
						class="fa fa-caret-right text fa-lg"></i> <i
						class="fa fa-caret-left text-active fa-lg"></i>
					</a> <a href="javascript:;" class="btn btn-sm btn-default"
						id="button_cancel"><i class="fa fa-reply"></i> 返回</a>
				</div>
			</header>

			<section class="scrollable wrapper">
				<section class="panel panel-default portlet-item">
					<header class="panel-heading">档案详情表详情</header>
					<table class="table table-striped2 m-b-none text-sm">
						<tbody>

							<tr>
								<td class="col-sm-2">主键ID：</td>
								<td class="col-sm-4">${amsArchives.archivesId}</td>
								<td class="col-sm-2">RFID号：</td>
								<td class="col-sm-4">${amsArchives.rfidnum}</td>
							</tr>
							<tr>
								<td class="col-sm-2">档案类型：</td>
								<td class="col-sm-4">${amsArchives.archivesTypeId}</td>
								<td class="col-sm-2">立卷方式：</td>
								<td class="col-sm-4">${amsArchives.filingmethod}</td>
							</tr>
							<tr>
								<td class="col-sm-2">室编档号：</td>
								<td class="col-sm-4">${amsArchives.roomnum}</td>
								<td class="col-sm-2">馆编档号：</td>
								<td class="col-sm-4">${amsArchives.archiveno}</td>
							</tr>
							<tr>
								<td class="col-sm-2">正题名：</td>
								<td class="col-sm-4">${amsArchives.title}</td>
								<td class="col-sm-2">并列标题：</td>
								<td class="col-sm-4">${amsArchives.paratitle}</td>
							</tr>
							<tr>
								<td class="col-sm-2">保管年限：</td>
								<td class="col-sm-4">${amsArchives.keepyear}</td>
								<td class="col-sm-2">密级：</td>
								<td class="col-sm-4">${amsArchives.security}</td>
							</tr>
							<tr>
								<td class="col-sm-2">存址：</td>
								<td class="col-sm-4">${amsArchives.storeplace}</td>
								<td class="col-sm-2">主题词：</td>
								<td class="col-sm-4">${amsArchives.subject}</td>
							</tr>
							<tr>
								<td class="col-sm-2">关键词：</td>
								<td class="col-sm-4">${amsArchives.keyword}</td>
								<td class="col-sm-2">在库状态：</td>
								<td class="col-sm-4">${amsArchives.inflag}</td>
							</tr>
							<tr>
								<td class="col-sm-2">视频回放开始时间：</td>
								<td class="col-sm-4"><fmt:formatDate
										value="${amsArchives.playbackstarttime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="col-sm-2">临时记录出入库人：</td>
								<td class="col-sm-4">${amsArchives.inoutUserId}</td>
							</tr>
							<tr>
								<td class="col-sm-2">盘点状态：</td>
								<td class="col-sm-4">${amsArchives.checkStatus}</td>
								<td class="col-sm-2">所属库房ID：</td>
								<td class="col-sm-4">${amsArchives.stroreId}</td>
							</tr>
							<tr>
								<td class="col-sm-2">所属库区ID：</td>
								<td class="col-sm-4">${amsArchives.stroreAreaId}</td>
								<td class="col-sm-2">所属密集架ID：</td>
								<td class="col-sm-4">${amsArchives.deviceId}</td>
							</tr>
							<tr>
								<td class="col-sm-2">所属列：</td>
								<td class="col-sm-4">${amsArchives.storeColumn}</td>
								<td class="col-sm-2">所属节：</td>
								<td class="col-sm-4">${amsArchives.storeSection}</td>

							</tr>
							<tr>
								<td class="col-sm-2">所属层：</td>
								<td class="col-sm-4">${amsArchives.storeLayer}</td>
								<td class="col-sm-2">方位左右：</td>
								<td class="col-sm-4">${amsArchives.storeLr}</td>

							</tr>
							<tr>
								<td class="col-sm-2">备注：</td>
								<td class="col-sm-4">${amsArchives.remark}</td>
								<td class="col-sm-2">创建人：</td>
								<td class="col-sm-4">${amsArchives.creator}</td>


							</tr>
							<tr>
								<td class="col-sm-2">总库存：</td>
								<td class="col-sm-4">${amsArchives.totalNumber}</td>
								<td class="col-sm-2">现有库存：</td>
								<td class="col-sm-4">${amsArchives.nowNumber}</td>


							</tr>
							<tr>
								<td class="col-sm-2">创建时间：</td>
								<td class="col-sm-4"><fmt:formatDate
										value="${amsArchives.ctime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td class="col-sm-2">修改人：</td>
								<td class="col-sm-4">${amsArchives.modifier}</td>


							</tr>
							<tr>
								<td class="col-sm-2">最后一次更新时间：</td>
								<td class="col-sm-4"><fmt:formatDate
										value="${amsArchives.mtime}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
							</tr>
						</tbody>
					</table>
				</section>
			</section>
		</section>
	</aside>
</section>

<jsp:include page="../wrapper.suffix.jsp" />
<script src="${STATIC_URL}/modules/ams/js/archives.detail.js"
	type="text/javascript"></script>
