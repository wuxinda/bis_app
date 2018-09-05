$(document).ready(function() {

	
	
	/**
	 * 单个 授权/解除授权
	 */
	$('input.changeRolePermission').click(function(){
		changeRolePermission($("input[name=roleId]:last").val(),  $(this).val(), $(this).is(':checked'),$(this).attr('pg'));
    });
	
	/**
	 * 单选 授权/解除授权
	 * @param $roleId
	 * @param $permission_id
	 * @param $assign
	 * @param $permission_name
	 */
	function changeRolePermission($roleId, $permissionId, $status, $permissionName) {
		var $menuId = $("input[name=menuId]:last").val();
		$.ajax({
	    	type:'post',
	        url:BASE_URL + '/adminRole/changeRolePermission',
	        data:'roleId=' + $roleId + '&permissionId=' + $permissionId + '&status=' + ($status ? 1 : 0),
	        dataType:'json',
	        timeout:60000,
	        success:function(data){
	        	
	        	var op_title = ($status ? '' : '解除') + '授权 【' + $permissionName+'】';
	        	
	        	if (data.status == 0) {
	    			notice('edit_notice_' + $permissionId, img_done + ' ' + op_title + '成功', true, 5000);
	    		} else {
	    			notice('edit_notice_' + $permissionId, img_delete + ' ' + op_title + '失败。原因：' + data.error, true, 5000);
	    			if ($status) {
	    				$('#pm_' + $permissionId).attr('checked', false);
	    			} else {
	    				$('#pm_' + $permissionId).attr('checked', true);
	    			}
	    		}
	    		return false;
	    	},
	        error:ajaxError
	    });
		
		
	}
	
	
	
	
	
	
	
	
	
	
	/**
     *  全选 授权/解除授权
     */
	$('.select-all').on('click', function(){
		var checked = $(this).prop('checked');
		var permissionIdArr = "";
		$("#permission_content").find('.permission_assign').prop('checked',checked);
		$.each($("#permission_content").find('.permission_assign'),function(index,data){
			permissionIdArr += $(data).val()+",";
		});
		Permission_Assign_All(permissionIdArr.substring(0,permissionIdArr.length - 1),checked);
	});
	
	/**
	 * 单个 授权/解除授权
	 */
	$('input.permission_assign').click(function(){
        Permission_Assign($("input[name=roleId]:last").val(),  $(this).val(), $(this).is(':checked'),$(this).attr('pg'));
    });
});

/**
 * 单选 - 授权权限方法
 * @param $roleId
 * @param $permission_id
 * @param $assign
 * @param $permission_name
 */
function Permission_Assign($roleId, $permission_id, $assign,$permission_name) {
	alert('Permission_Assign');
	var navigationId = $("input[name=navigationId]:last").val();
	$.ajax({
    	type:'post',
        url:BASE_URL + '/adminPermission/changeRolePermission',
        data:'roleId=' + $roleId + '&permissionId=' + $permission_id + '&assign=' + ($assign ? 1 : 0),
        dataType:'json',
        timeout:60000,
        success:function(data){
        	var op_title = ($assign ? '' : '解除') + '授权——权限ID【' + $permission_name+'】';
    		if (data.status == 0) {
    			notice('edit_notice_' + $permission_name, img_done + ' ' + op_title + '成功', true, 5000);
    		} else {
    			notice('edit_notice_' + $permission_name, img_delete + ' ' + op_title + '失败。原因：' + data.error, true, 5000);
    			if ($assign) {
    				$('#pm_' + $permission_id).attr('checked', false);
    			} else {
    				$('#pm_' + $permission_id).attr('checked', true);
    			}
    		}
    		return false;
    	},
        error:ajaxError
    });
	
	
}
	/**
	 * 全选 - 授权权限方法
	 * @param $roleId
	 * @param $permission_id
	 * @param $assign
	 */
	function Permission_Assign_All($permission_ids, $assign) {
		var $roleId = $("input[name=roleId]:last").val();
		$.ajax({
	    	type:'post',
	        url:BASE_URL + '/adminPermission/assignPermissionAll',
	        data:'roleId=' + $roleId + '&permissionIds=' + $permission_ids + '&assign=' + ($assign ? 1 : 0),
	        dataType:'json',
	        timeout:60000,
	        success:function(data){
	        	var op_title_all = ($assign ? '' : '解除') + '授权——';
	    		if (data.status == 0) {
	    			notice('edit_notice', img_done + ' ' + op_title_all + '成功', true, 5000);
	    		} else {
	    			notice('edit_notice', img_delete + ' ' + op_title_all + '失败。原因：' + data.error, true, 5000);
	    			if ($assign) {
	    				$('#pm_' + $permission_id).attr('checked', false);
	    			} else {
	    				$('#pm_' + $permission_id).attr('checked', true);
	    			}
	    		}
	    		return false;
	    	},
	        error:ajaxError
	    });
}
