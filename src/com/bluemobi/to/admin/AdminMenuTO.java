package com.bluemobi.to.admin;

import java.util.List;

import com.appcore.model.AbstractObject;
/**
 * 一级菜单TO
 * @author haoj 309444359@qq.com
 * @date 2016-9-11 上午1:06:06
 */
public class AdminMenuTO extends AbstractObject {
	
	// 菜单id
    private Integer menuId;
    // 菜单名称
    private String name;
    // 链接
    private String url;
    // icon图标
    private String icon;
    // icon背景
    private String iconBg;
    // 子集菜单
	private List<AdminMenuTO> subList;
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIconBg() {
		return iconBg;
	}
	public void setIconBg(String iconBg) {
		this.iconBg = iconBg;
	}
	public List<AdminMenuTO> getSubList() {
		return subList;
	}
	public void setSubList(List<AdminMenuTO> subList) {
		this.subList = subList;
	}
	
	
	
}
