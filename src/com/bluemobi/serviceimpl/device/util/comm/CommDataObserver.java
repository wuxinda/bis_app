package com.bluemobi.serviceimpl.device.util.comm;

import java.util.Observable;
import java.util.Observer;

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
 * @date 2014-11-5
 */
public class CommDataObserver implements Observer {

	private String name;

	public CommDataObserver(String name) {
		this.name = name;
	}

	public void update(Observable o, Object arg) {
		System.out.println("[" + name + "] GetMessage:\n [" + new String((byte[]) arg) + "]");
	}
}
