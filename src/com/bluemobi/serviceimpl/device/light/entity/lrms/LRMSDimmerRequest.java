package com.bluemobi.serviceimpl.device.light.entity.lrms;

/**
 * <p>
 * Title: LRMS Relay请求
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
 * @date 2014-10-21
 */
public class LRMSDimmerRequest {

	private String host;
	private int port;
	private String areaId;// 分区号
	private String modelId;// 模块ID号
	private String channelId;// R01（Relay 1-8 Channel ID）
	private int dimValue;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public int getDimValue() {
		return dimValue;
	}

	public void setDimValue(int dimValue) {
		this.dimValue = dimValue;
	}
}
