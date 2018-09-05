$(document).ready(function() {
	loadDataGridContent(columnsDefined(), 'formatData');
	
	/**
	 * 下拉选择触发处理
	 */
	$("select[name=navigationId]").change(function(){
		$('#navigation_listing').datagrid('reload');
    	return false;
	});
    
    /**
     * 刷新搜索
     */
    $(".action-refresh,#action_search").on('click',function(){
    	$('#category_listing').datagrid('reload');
    	return false;
    });
    
    /**
	 * 删除 - 单条
	 */
    $("#navigation_listing").delegate('.operate-delete', 'click', function(){
		var navigationId = $(this).attr("navigationId");
		var $tr = $(this).parent().parent();
		doDelete(navigationId, $tr);
	});
    
    /**
     * 审核
     */
    $('#navigation_listing').delegate('.switch-sm', 'click', function(){
    	var navigationId = $(this).attr('navigationId');
    	publishNavigation(navigationId);
    	return false;
    });
});


/**
 * 更改导航状态
 */
function publishNavigation(id) {
	$.ajax({
    	type:'post',
        url:BASE_URL + '/systemNavigation/publish',
        data:'navigationId=' + id,
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
    			var d = data.data;
    			if (d.status == 1) {
    				$('#navigation_' + id).prop('checked', true);
    			} else {
    				$('#navigation_' + id).prop('checked', false);
    			}
    			$('#navigation_listing').datagrid('reload');
    		} else {
    			alert(data.msg);
    		}
    		return false;
    	}
    });
}

/**
 * 删除
 */
function doDelete(id, $tr) {
	var del = confirm('确定要删除所选导航吗？');
	if (! del) {return false;}
	
	/* 执行删除 */
	$.ajax({
    	type:'post',
        url:BASE_URL + '/systemNavigation/delete',
        data:'navigationId=' + id,
        dataType:'json',
        timeout:60000,
        success:function(data){
    		if (data.status == 0) {
    			$tr.remove();
    		} else {
    			alert(data.msg);
    		}
    		return false;
    	}
    });
}

function columnsDefined() {
	return [
            {
                property: 'navigationId',
                label: 'ID',
                sortable: true
            },
            {
            	property: 'title',
            	label: '导航名称',
            	sortable: false
            },
            {
            	property: 'link',
            	label: '链接地址',
            	sortable: false
            },
            {
            	property: 'sortOrder',
            	label: '序号',
            	sortable: false
            },
            {
            	property: 'status',
            	label: '状态',
            	sortable: false
            },
            {
            	property: 'operate',
                label: '操作'
            }
        ];
}

function formatData(items) {
	$.each(items, function (index, item) {
    	item.title = item.strPadding + item.title;
    	var is_publish = item.status == 1 ? 'checked="checked"' : '';
        item.status = '<label class="switch-sm" navigationId="' + item.navigationId + '">' + 
        		'<input type="checkbox" id="navigation_' + item.navigationId + '" ' + is_publish + ' />' + 
        		'<span></span></label>';
        
    	item.operate = '<a href="' + BASE_URL + '/systemNavigation/edit/?navigationId=' + item.navigationId + '" data-toggle="ajaxModal" class="operate-edit" title="编辑"><i class="fa fa-pencil"></i></a>&nbsp;&nbsp;' + 
				'<a href="javascript:;" class="operate-delete" navigationId="' + item.navigationId + '" title="删除"><i class="fa fa-times"></i></a>';
    });
}