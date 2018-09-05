<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../wrapper.prefix.jsp"/>

  
<section class="hbox stretch">
    <aside class="aside-md bg-white b-r">
        <section class="vbox">
            <header class="b-b header">
                <p class="h4"> <c:choose> <c:when test="${deviceCategoryBrand!=null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></p>
            </header>
                
            <section class="scrollable wrapper w-f">
                <form class="form-horizontal" id="edit_form" action=<c:choose> <c:when test="${deviceCategoryBrand!=null}">"${BASE_URL}/deviceCategoryBrand/edit"</c:when><c:otherwise>"${BASE_URL}/deviceCategoryBrand/add"</c:otherwise></c:choose> method="post">
                    <div class="form-group">
                        <label for="categoryId" class="col-sm-3 control-label">设备分类id</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="categoryId" name="categoryId" value="${deviceCategoryBrand.categoryId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <div class="form-group">
                        <label for="brandId" class="col-sm-3 control-label">设备品牌id</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="brandId" name="brandId" value="${deviceCategoryBrand.brandId}" onkeydown="if(event.keyCode==13)return false;" placeholder="请输入" />
                        </div>
                    </div>
					<div class="line line-dashed line pull-in"></div>
                    <input type="hidden" name="categoryBrandId" value="${deviceCategoryBrand.categoryBrandId}" />

                </form>
            </section>
        
            <footer class="footer b-t bg-white-only">
                <div class="m-t-sm">
                    <button type="button" data_submit_type="submit_save_back" class="btn btn-s-md btn-primary btn-sm input-submit">保存</button>
                    <c:if test="${deviceCategoryBrand==null}"><button type="button" data_submit_type="submit_save_continue" class="btn btn-s-md btn-primary btn-sm input-submit">保存并继续添加</button></c:if>
                    <button type="button" data_submit_type="submit_cancel" class="btn btn-danger btn-sm input-submit">取消</button>
                    <span id="edit_notice"></span>
                </div>
            </footer>
        </section>
    </aside>
</section>

<jsp:include page="../wrapper.suffix.jsp"/>
<script src="${STATIC_URL}/modules/device/js/categoryBrand.edit.js" type="text/javascript"></script>
<!-- / modal - 编辑-->