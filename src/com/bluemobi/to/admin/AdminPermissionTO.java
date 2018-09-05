package com.bluemobi.to.admin;

import com.appcore.model.AbstractObject;
/**
 * 权限TO
 * @author haoj 309444359@qq.com
 * @date 2016-9-11 上午1:02:06
 */
public class AdminPermissionTO extends AbstractObject {

	//权限id
	private Integer permissionId;
	//权限名称
	private String permissionName;
	//是否有此权限
	private int hasPermission;
		
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public int getHasPermission() {
		return hasPermission;
	}
	public void setHasPermission(int hasPermission) {
		this.hasPermission = hasPermission;
	}
	
	
	
}
