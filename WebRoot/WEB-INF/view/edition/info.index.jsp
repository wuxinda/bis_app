<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../wrapper.prefix.jsp"/>
<link rel="stylesheet" href="${STATIC_URL}/modules/edition/css/info.index.css" type="text/css"/>
<section class="hbox stretch">
    <aside>
        <section class="vbox">
            <header class="header bg-white b-b clearfix">
                <div class="row m-t-sm">
                    <div class="col-sm-8 m-b-xs">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-default action-refresh" title="刷新"><i class="fa fa-refresh"></i></button>                           
                        </div>
                        <a href="${BASE_URL}/editionInfo/add" class="btn btn-sm btn-default load-content"><i class="fa fa-plus"></i>添加</a>
                    </div>
                    <form action="${BASE_URL}/editionInfo/page" id="searchForm">
	                    <div class="col-sm-4 m-b-xs">
	                        <div class="input-group">
	                            <input type="text" name="key" class="input-sm form-control" placeholder="搜索条件" />
	                            <span class="input-group-btn">
	                                <button class="btn btn-sm btn-default action-refresh" type="button">搜索</button>
	                            </span>
	                        </div>    
	                    </div>
	                </form>
                </div>
                
            </header>
            <%@include file = "../page.jsp" %>        
        </section>
 
    </aside>
</section> 
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">版本信息</h4>
      </div>
      <div class="modal-body">
         <form>
         
          <div class="form-group">
            <label for="application-level" class="control-label">应用平台:</label>           
            <input type="text" class="form-control" id="application-level" readOnly="true" />
          </div>
          <div class="form-group">
            <label for="application-classify" class="control-label">应用分类:</label>
            <input class="form-control" id="application-classify" readOnly="true"></input>
          </div>
          <div class="form-group">
            <label for="application-level" class="control-label">应用名称:</label>           
            <input type="text" class="form-control" id="application-name" readOnly="true" />
          </div>
          <div class="form-group">
            <label for="edition-number" class="control-label">版本号:</label>
            <input class="form-control" id="edition-number" readOnly="true"></input>
          </div>
          <div class="form-group">
            <label for="remark" class="control-label">备注:</label>
            <textarea class="form-control" id="remark" readOnly="true"></textarea>
          </div>
        </form>  
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" id="submit">确认</button>
      </div>
    </div>
  </div>
</div>   
<!-- 二维码弹出框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div id="qrcode"></div>
    </div>
  </div>
</div>        
<jsp:include page="../wrapper.suffix.jsp"/>
<script type="text/javascript" src="${STATIC_URL}/plugins/qrcode.js"></script>
<script src="${STATIC_URL}/modules/edition/info.index.js" type="text/javascript"></script>