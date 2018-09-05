package com.bluemobi.service.device.access.util;

/**
 * <p>
 * Title: 基类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: BAYI
 * </p>
 * 
 * @author jianghaidong
 * @version 1.0
 * @date 2014-10-29
 */
public enum ZKDoorFunc {

	OPEN_DOOR("opendoor", 1), OPEN_PART("openpart", 2), CLOSEDOOR("closedoor", 3), CLOSEPART("closepart", 4), QUERY("all", 100);

	public String name;
	public int index;

	private ZKDoorFunc(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	public static boolean exists(ZKDoorFunc event) {
		boolean has = false;
		for (ZKDoorFunc e : ZKDoorFunc.values()) {
			if (e.index == event.index) {
				has = true;
				break;
			}
		}
		return has;
	}

	@Override
	public String toString() {
		return this.index + "_" + this.name;
	}

}
