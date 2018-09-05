package com.bluemobi.serviceimpl.device.light;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bluemobi.service.device.light.HWLightService;
import com.bluemobi.serviceimpl.device.light.common.LRMS;
import com.bluemobi.serviceimpl.device.light.entity.LightStatusPool;
import com.bluemobi.serviceimpl.device.light.entity.VoLight;
import com.bluemobi.serviceimpl.device.light.entity.lrms.LRMSDimmerRequest;
import com.bluemobi.serviceimpl.device.light.entity.lrms.LRMSQueryRequest;
import com.bluemobi.serviceimpl.device.light.entity.lrms.LRMSRelayRequest;
import com.bluemobi.serviceimpl.device.util.HardwareError;
import com.bluemobi.serviceimpl.device.util.HardwareResult;
import com.bluemobi.serviceimpl.device.util.comm.StringUtil;

/**
 * 灯光控制，内部调用接口实现
* <p></p>
* @date 2014年12月16日
 */
@Service("hwLightService")
public class HWLightSerivceImpl implements HWLightService {	
	
	/**
	 * 用list保存灯光实时状态信息
	 */
	@SuppressWarnings("unchecked")
	public void updateLightStatus(HardwareResult hardResult){
		
		if(HardwareError.SUCCESS.equals(hardResult.getResultCode())){
			List<VoLight> list = (List<VoLight>) hardResult.getResponse();
			for (VoLight voLight : list) {
				//更新灯光状态数据到数据池
				LightStatusPool.updatePool(voLight);
			}
		}
	}

	/**
	 * 灯光开关
	 */
	public HardwareResult relay(LRMSRelayRequest req) {
		HardwareResult hr = null;
		List<VoLight> voLights;
		//1,参数为空
		if (req==null) {
			hr = new HardwareResult(HardwareError.ERR_NULL, HardwareError.S_ERR_NULL);
		}else{
			//2,ip，port为空
			if (StringUtil.isEmpty(req.getHost())
					|| req.getPort() < 1) {
				hr = new HardwareResult(HardwareError.ERR_UNKNOWN_HOST,
						HardwareError.S_ERR_UNKNOWN_HOST);
				return hr;
			}
			//3，灯光分区，模块，通道号为空
			if ( StringUtil.isEmpty(req.getAreaId()) || StringUtil.isEmpty(req.getModelId()) 
					|| StringUtil.isEmpty(req.getChannelId()) ) {
				hr = new HardwareResult(HardwareError.ERR_NULL,
						HardwareError.S_ERR_NULL);
				return hr;
			}
			LRMS lrms = new LRMS();
			String areaId = req.getAreaId();
			String modelId = req.getModelId();
			String channelId = req.getChannelId();
			boolean open = StringUtil.isEmpty(req.getOpen());			
			hr = lrms.relay(req.getHost(), req.getPort(), areaId, modelId, channelId, open);
			
			//hr的返回值string转成object
			String response = (String) hr.getResponse();
			String[] splits = response.split("\"");
			
			List<String> results = new ArrayList<String>();
			for (int i = 0; i < splits.length; i++) {
				if (splits[i].matches("^\\d{4}\\D{1}:[0-1]{8},\\d{2},\\d{2},\\d{2},\\d{2}$")) {
					results.add(splits[i]);
				}
			}

			voLights = new ArrayList<>();
			for (int i = 0; i < results.size(); i++) {
				voLights.add(new VoLight(results.get(i)));
			}
			hr.setResponse(voLights);
		}
		updateLightStatus(hr);
		return hr;
	}

	/**
	 * 灯光调光
	 */
	public HardwareResult dimmer(LRMSDimmerRequest req) {
		HardwareResult hr = null;
		if (req==null) {
			hr = new HardwareResult(HardwareError.ERR_NULL,
					HardwareError.S_ERR_NULL);
		} else {
			if (req == null || StringUtil.isEmpty(req.getHost())
					|| req.getPort() < 1) {
				hr = new HardwareResult(HardwareError.ERR_UNKNOWN_HOST,
						HardwareError.S_ERR_UNKNOWN_HOST);
				return hr;
			}
			if ( StringUtil.isEmpty(req.getAreaId()) || StringUtil.isEmpty(req.getModelId()) 
					|| StringUtil.isEmpty(req.getChannelId()) ) {
				hr = new HardwareResult(HardwareError.ERR_NULL,
						HardwareError.S_ERR_NULL);
				return hr;
			}
			LRMS lrms = new LRMS();
			String areaId = req.getAreaId();
			String modelId = req.getModelId();
			String channelId = req.getChannelId();
			int dimValue = req.getDimValue();
			hr = lrms.dimmer(req.getHost(), req.getPort(), areaId, modelId, channelId, dimValue);
			
			//hr的返回值string转成object
			String response = (String) hr.getResponse();
			String[] splits = response.split("\"");
			
			List<String> results = new ArrayList<String>();
			for (int i = 0; i < splits.length; i++) {
				if (splits[i].matches("^\\d{4}\\D{1}:[0-1]{8},\\d{2},\\d{2},\\d{2},\\d{2}$")) {
					results.add(splits[i]);
				}
			}
			List<VoLight> voLights = new ArrayList<>();
			for (int i = 0; i < results.size(); i++) {
				voLights.add(new VoLight(results.get(i)));
			}
			hr.setResponse(voLights);
		}
		updateLightStatus(hr);
		return hr;
	}

	/**
	 * 灯光查询
	 */
	public HardwareResult query(LRMSQueryRequest req) {
		HardwareResult hr = null;
		if (req==null) {
			hr = new HardwareResult(HardwareError.ERR_NULL,
					HardwareError.S_ERR_NULL);
		} else {
			if (req == null || StringUtil.isEmpty(req.getHost())
					|| req.getPort() < 1) {
				hr = new HardwareResult(HardwareError.ERR_UNKNOWN_HOST,
						HardwareError.S_ERR_UNKNOWN_HOST);
				return hr;
			}
			if ( StringUtil.isEmpty(req.getAreaId()) ) {
				hr = new HardwareResult(HardwareError.ERR_NULL,
						HardwareError.S_ERR_NULL);
				return hr;
			}
			LRMS lrms = new LRMS();
			String areaId = req.getAreaId();			
			hr = lrms.query(req.getHost(), req.getPort(), areaId);
			
			//hr的返回值string转成object
			String response = (String) hr.getResponse();
			if (hr.isSuccess()) {
				String[] splits = response.split("\"");
				
				List<String> results = new ArrayList<String>();
				for (int i = 0; i < splits.length; i++) {
					if (splits[i].matches("^\\d{4}\\D{1}:[0-1]{8},\\d{2},\\d{2},\\d{2},\\d{2}$")) {
						results.add(splits[i]);
					}
				}
				List<VoLight> voLights = new ArrayList<>();
				for (int i = 0; i < results.size(); i++) {
					voLights.add(new VoLight(results.get(i)));
				}
				hr.setResponse(voLights);
			} else {
				if("输入输出错误".equals(response)) {
					hr.setResponse(null);
				}
			}
			
		}
		updateLightStatus(hr);
		return hr;
	}


}
