package com.bluemobi.service.device.light;


import com.bluemobi.serviceimpl.device.light.entity.lrms.LRMSDimmerRequest;
import com.bluemobi.serviceimpl.device.light.entity.lrms.LRMSQueryRequest;
import com.bluemobi.serviceimpl.device.light.entity.lrms.LRMSRelayRequest;
import com.bluemobi.serviceimpl.device.util.HardwareResult;

/*
 * 灯光控制内部调用service
 */
public interface HWLightService  {
	
	
	/**
	 * 
	 * 开关
	 *@param LRMSRelayRequest request
	 *@return
	 */
	public HardwareResult relay(LRMSRelayRequest request);
	
	/**
	 * 
	 * 调光
	 *@param LRMSDimmerRequest request
	 *@return
	 */
	public HardwareResult dimmer(LRMSDimmerRequest request);
	
	/**
	 * 
	 * 查询状态
	 *@param LRMSQueryRequest request
	 *@return
	 */
	public HardwareResult query(LRMSQueryRequest request);

}
