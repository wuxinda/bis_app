package com.bluemobi.to.admin;

import java.util.List;

import com.appcore.model.AbstractObject;
/**
 * 菜单下面的权限代码TO
 * @author haoj 309444359@qq.com
 * @date 2016-9-21 上午11:27:06
 */
public class AdminMenuCodesTO extends AbstractObject {
	
	// 二级菜单id
    private Integer menuId;
    // 二级菜单下面的权限
	private List<String> codes;
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public List<String> getCodes() {
		return codes;
	}
	public void setCodes(List<String> codes) {
		this.codes = codes;
	}
	
}
