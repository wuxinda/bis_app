<%@ page contentType="text/html;charset=UTF-8" %>
<section class="scrollable wrapper">
	<section class="panel panel-default">
	    <div class="table-responsive">
	        <table class="table table-striped m-b-sm datagrid">
	            <thead>
	            </thead>
	            <tfoot>
	                <tr>
	                    <th class="row">
	                        <div class="datagrid-footer-left col-sm-6 text-center-xs m-l-n">
	                            <div class="grid-controls m-t-sm">
	                                    <span>
	                                        <span class="grid-start"></span> -
	                                        <span class="grid-end"></span> （共
	                                        <span class="grid-count"></span>）
	                                    </span>
	
	                                <div class="select grid-pagesize" data-resize="auto">
	                                    <button data-toggle="dropdown"
	                                            class="btn btn-sm btn-default dropdown-toggle">
	                                        <span class="dropdown-label"></span>
	                                        <span class="caret"></span>
	                                    </button>
	                                    <ul class="dropdown-menu">
	                                        <li data-value="5"><a href="#">5</a></li>
	                                        <li data-value="15" data-selected="true"><a href="#">15</a></li>
	                                        <li data-value="20"><a href="#">20</a></li>
	                                        <li data-value="50"><a href="#">50</a></li>
	                                        <li data-value="100"><a href="#">100</a></li>
	                                    </ul>
	                                </div>
	                                <span>/页</span>
	                            </div>
	                        </div>
	
	                        <div class="datagrid-footer-right col-sm-6 text-right text-center-xs">
	                            <div class="grid-pager m-r-n">
	                                <button type="button" class="btn btn-sm btn-default grid-prevpage"><i
	                                        class="fa fa-chevron-left"></i></button>
                                    <div class="inline">
                                        <div class="input-group dropdown combobox">
                                            <input class="input-sm form-control" type="text">

                                            <div class="input-group-btn dropdown">
                                                <button class="btn btn-sm btn-default" data-toggle="dropdown"><i
                                                        class="caret"></i></button>
                                                <ul class="dropdown-menu pull-right"></ul>
                                            </div>
                                        </div>
                                    </div>
                                    <span>/ <span class="grid-pages"></span></span>
                                    <button type="button" class="btn btn-sm btn-default grid-nextpage"><i
                                            class="fa fa-chevron-right"></i></button>
                                </div>
                            </div>
                        </th>
                    </tr>
                </tfoot>
            </table>
        </div>
    </section>
</section>