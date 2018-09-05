package com.bluemobi.service.device.access.util;

import java.util.List;

/**
 * 获取门状态、实时事件的返回值封装实体
* <p></p>
* @date 2014年12月30日
 */
public class ZKQueryResponse {
	private int ret;
	private String type;
	private List door_states;
	private List data;
	private String dev_door_name;
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public String getDev_door_name() {
		return dev_door_name;
	}
	public void setDev_door_name(String dev_door_name) {
		this.dev_door_name = dev_door_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List getDoor_states() {
		return door_states;
	}
	public void setDoor_states(List door_states) {
		this.door_states = door_states;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
	
	
}
