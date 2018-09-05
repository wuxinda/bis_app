package com.bluemobi.serviceimpl.device.light.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 灯光设备VO实体类,封装灯光查询得到的数据
 * 开关Relay、调光Dimmer、RQ 返回码："010FA:11001110,00,00,00,16"  红色为分区号  蓝色为分区中的模块ID号 其他按照原定义。
        【11001110】  代表  8路Relay的状态数据 【1代表开 0代表关】
        【00,00,00,16】代表  4路的调光值 【0-10V接口】
* <p></p>
* @date 2014年12月16日
 */
public class VoLight {
	
	private String modelId; //模块号
	
	private String areaid; //灯光分区号
	
	private String relay1;
	private String relay2;
	private String relay3;
	private String relay4;
	private String relay5;
	private String relay6;
	private String relay7;
	private String relay8;
	
	private String dim1;
	private String dim2;
	private String dim3;
	private String dim4;
	
	public VoLight(){}
	
	public VoLight(String response) {
		
		String areaid = response.substring(0, 2);
		String modelId = response.substring(2,4);
		String relay1 = response.substring(6,7);
		String relay2 = response.substring(7,8);
		String relay3 = response.substring(8,9);
		String relay4 = response.substring(9,10);
		String relay5 = response.substring(10,11);
		String relay6 = response.substring(11,12);
		String relay7 = response.substring(12,13);
		String relay8 = response.substring(13,14);
		
		String dim1 = response.substring(15,17);
		String dim2 = response.substring(18,20);
		String dim3 = response.substring(21,23);
		String dim4 = response.substring(24,26);
		
		this.areaid = areaid;
		this.modelId = modelId;
		this.relay1 = relay1;
		this.relay2 = relay2;
		this.relay3 = relay3;
		this.relay4 = relay4;
		this.relay5 = relay5;
		this.relay6 = relay6;
		this.relay7 = relay7;
		this.relay8 = relay8;
		this.dim1 = dim1;
		this.dim2 = dim2;
		this.dim3 = dim3;
		this.dim4 = dim4;
	}
	
	public String getByChannelId(String channelType,String channelId){
		String status = null;
		if (channelType.equals("R")) {
			switch (channelId) {
			case "01":
				status =  getRelay1();
				break;
			case "02":
				status =  getRelay2();
				break;
			case "03":
				status =  getRelay3();
				break;
			case "04":
				status =  getRelay4();
				break;
			case "05":
				status =  getRelay5();
				break;
			case "06":
				status =  getRelay6();
				break;
			case "07":
				status =  getRelay7();
				break;
			case "08":
				status =  getRelay8();
				break;
			}
		}
		else if(channelType.equals("D")) {
			switch (channelId) {
			case "01":
				status =  getDim1();
				break;
			case "02":
				status =  getDim2();
				break;
			case "03":
				status =  getDim3();
				break;
			case "04":
				status =  getDim4();
				break;
			}
		}
		else{
			status =  "没有此种灯光类型";
		}
		return status;
	}
	

	@Override
	public String toString() {
		return "VoLight [areaid=" + areaid + ", modelId=" + modelId
				+ ", relay1=" + relay1 + ", relay2=" + relay2 + ", relay3="
				+ relay3 + ", relay4=" + relay4 + ", relay5=" + relay5
				+ ", relay6=" + relay6 + ", relay7=" + relay7 + ", relay8="
				+ relay8 + ", dim1=" + dim1 + ", dim2=" + dim2 + ", dim3="
				+ dim3 + ", dim4=" + dim4 + "]";
	}


	public static void main(String[] args) {
		//String response = "RECEIVE...LRMS_Server=online\"0101A:00010000,00,00,00,00\"\"0102A:00000000,00,00,00,00\"\"0103A:10000010,00,00,00,00\"\"0104A:01010010,00,20,00,20\"";
		String response = "RECEIVE...LRMS_Server=online\"0101A:00010000,00,00,00,00\"\"0102A:00000000,00,00,00,00\"\"0103A:10000010,00,00,00,00\"\"0104A:01010010,00,20,00,20\"[12/24/14 12:56:18]No.01 Area_ID Error Code:1201((11K7PU))";
		//hardwareResult.setResultCode("1001");
		String[] splits = response.split("\"");
		
		List<String> results = new ArrayList<String>();
		for (int i = 0; i < splits.length; i++) {
			if (splits[i].matches("^\\d{4}\\D{1}:[0-1]{8},\\d{2},\\d{2},\\d{2},\\d{2}$")) {
				results.add(splits[i]);
			}
		}
		
		for (String string : results) {
			System.out.println(string);
		}
		
//		VoLight light = new VoLight(response);
//		System.out.println(light.toString());
//		String string = light.getByChannelId("D04");
//		System.out.println(string);
		
	}


	public String getAreaid() {
		return areaid;
	}


	public String getModelId() {
		return modelId;
	}


	public String getRelay1() {
		return relay1;
	}


	public String getRelay2() {
		return relay2;
	}


	public String getRelay3() {
		return relay3;
	}


	public String getRelay4() {
		return relay4;
	}


	public String getRelay5() {
		return relay5;
	}


	public String getRelay6() {
		return relay6;
	}


	public String getRelay7() {
		return relay7;
	}


	public String getRelay8() {
		return relay8;
	}


	public String getDim1() {
		return dim1;
	}


	public String getDim2() {
		return dim2;
	}


	public String getDim3() {
		return dim3;
	}


	public String getDim4() {
		return dim4;
	}
	
	
	
	
	
	
	
	

}
