package com.bluemobi.serviceimpl.device.access;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bluemobi.service.device.access.AccessControlsService;
import com.bluemobi.service.device.access.util.ZKDoorFunc;
import com.bluemobi.service.device.access.util.ZKQueryResponse;
import com.bluemobi.serviceimpl.device.util.comm.HttpXmlClient;
import com.bluemobi.util.JsonUtil;
import com.bluemobi.util.PropertiesUtil;


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
 * @author chenjingwu
 * @version 2.0
 * @date 2015-7-10
 */
@Service("accessControlsService")
public class AccessControlsServiceImpl implements AccessControlsService {

	public ZKQueryResponse open(int id) {
		return getResponse(id, ZKDoorFunc.OPEN_DOOR.name);
	}

	public ZKQueryResponse close(int id) {
		return getResponse(id, ZKDoorFunc.CLOSEDOOR.name);
	}

	public ZKQueryResponse query() {
		return getResponse(ZKDoorFunc.QUERY.name, ZKDoorFunc.QUERY.index);
	}

	private ZKQueryResponse getResponse(int id, String status) {
		StringBuilder url = new StringBuilder(PropertiesUtil.get("ZKACCESS.WEBSERVICE"));
		String userName = PropertiesUtil.get("ZKACCESS.USER");
		String passWord = PropertiesUtil.get("ZKACCESS.PWD");
		Map<String, String> params = new HashMap<String, String>();   
		params.put("username", userName);
		params.put("password", passWord);
		url.append("?func=").append(status).append("&type=part&data=").append(id).append("&open_interval=15&enable_no_tzs=false");
		String json = HttpXmlClient.post(url.toString(), params);
		int start = json.indexOf("[");
		int end = json.lastIndexOf("]");
		String object = json.substring(start + 1, end);
		ZKQueryResponse response = (ZKQueryResponse) JsonUtil.toObject(object,ZKQueryResponse.class);
		return response;
	}
	
	private ZKQueryResponse getResponse(String type, int sum) {
		StringBuilder url = new StringBuilder(PropertiesUtil.get("ZKACCESS.WEBSERVICE"));
		String userName = PropertiesUtil.get("ZKACCESS.USER");
		String passWord = PropertiesUtil.get("ZKACCESS.PWD");
		Map<String, String> params = new HashMap<String, String>();   
		params.put("username", userName);
		params.put("password", passWord);
		url.append("?type=").append(type).append("&logid=0&step=").append(sum);
		String json = HttpXmlClient.post(url.toString(), params);
		ZKQueryResponse response = (ZKQueryResponse) JsonUtil.fromObject(json,ZKQueryResponse.class);
		return response;
	}
}
