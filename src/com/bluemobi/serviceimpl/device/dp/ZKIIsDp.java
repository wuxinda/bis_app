package com.bluemobi.serviceimpl.device.dp;

import com.bluemobi.serviceimpl.device.dp.base.AbstractIIsDp;

/**
 * 灯光设备数据感知具体处理类
 * @author Riven
 *
 */
public class ZKIIsDp extends AbstractIIsDp{

	/**
	 * 设备连接
	 */
	public void connect(){
		System.out.println("IIsDp+++++++设备连接");
	}

	/**
	 * 设备感知数据收集
	 */
	public void collect(){
		System.out.println("IIsDp+++++++设备收集");
	}

	/**
	 * 设备感知数据分析，形成报警纪录
	 */
	public void analysis(){
		System.out.println("IIsDp+++++++设备分析");
	}

	/**
	 * 设备感知数据分析后，操控设备，进行智能处置
	 */
	public void handle(){
		System.out.println("IIsDp+++++++设备处理");
	}
}
