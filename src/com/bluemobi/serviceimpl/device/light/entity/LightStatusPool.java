package com.bluemobi.serviceimpl.device.light.entity;

import java.util.ArrayList;
import java.util.List;

public class LightStatusPool {
	//单例模式，用来保存灯光状态数据
	private static LightStatusPool lightStatusPool = null;
	private final static List<VoLight> list = new ArrayList<VoLight>();
	
	private LightStatusPool() {
	}
	
	public static LightStatusPool getLightStatusPool(){
		if (lightStatusPool==null) {
			lightStatusPool = new LightStatusPool();
		}
		return lightStatusPool;
	}
	
	public static void updatePool(VoLight voLight){
		boolean isExit = false;
		for(int i=0; i<list.size(); i++){
			VoLight vo = list.get(i);
			if (vo.getAreaid().equals(voLight.getAreaid()) && vo.getModelId().equals(voLight.getModelId())) {
				//如果有就更新
				list.set(i, voLight);
				isExit = true;
			}
		}
		if(!isExit){
			list.add(voLight);
		}
	}
	
	public VoLight getLightStatus(String areaId, String modelId){
		VoLight voLight = new VoLight();
		for (VoLight vo : list) {
			if (vo.getAreaid().equals(areaId) && vo.getModelId().equals(modelId)) {
				//如果有就更新
				voLight = vo;
			}
		}
		return voLight;
	}

}
