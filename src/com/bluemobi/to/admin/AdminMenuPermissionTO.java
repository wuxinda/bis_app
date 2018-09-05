package com.bluemobi.to.admin;

import java.util.List;

import com.appcore.model.AbstractObject;
/**
 * 二级菜单权限TO
 * @author haoj 309444359@qq.com
 * @date 2016-9-11 上午1:06:06
 */
public class AdminMenuPermissionTO extends AbstractObject {

	//二级菜单名称
	private String secondMenuName;
	//权限TO
	private List<AdminPermissionTO> adminPermissionTOs;
	
	public String getSecondMenuName() {
		return secondMenuName;
	}
	public void setSecondMenuName(String secondMenuName) {
		this.secondMenuName = secondMenuName;
	}
	public List<AdminPermissionTO> getAdminPermissionTOs() {
		return adminPermissionTOs;
	}
	public void setAdminPermissionTOs(List<AdminPermissionTO> adminPermissionTOs) {
		this.adminPermissionTOs = adminPermissionTOs;
	}
	
	
}
