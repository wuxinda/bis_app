package com.bluemobi.serviceimpl.device.light.entity.lrms;


/**
 * 对灯光返回值进行解析
* <p></p>
* @date 2014年12月16日
 */
public class LRMSResponseUtil {
	
	
	/*
	 开关Relay、调光Dimmer、RQ 返回码："010FA:11001110,00,00,00,16"  01红色为分区号 0F蓝色为分区中的模块ID号 其他按照原定义。
        【11001110】  代表  8路Relay的状态数据 【1代表开 0代表关】
        【00,00,00,16】代表  4路的调光值 【0-10V接口】
	 */
	
	/**
	 * 根据灯光分区解析返回码
	 * 
	 *@param result
	 *@return
	 */
//	public static List<VoLight>  queryResponseToVo(HardwareResult result){
//		List<VoLight> lightList = new ArrayList<VoLight>();
//		String response = (String) result.getResponse();
//		//解析返回码,按分区
//		String areaid = response.substring(0, 1);
//		String modelIds = response.substring(response.indexOf(":")+1, response.indexOf(":")+9); //灯光状态码
//		
//		IlsDeviceService ilsDeviceServie = new IlsDeviceServiceImpl();
//		List<IlsDevice> ilsDeviceList = ilsDeviceServie.findByAreaid(areaid);
//		
//		for (IlsDevice ilsDevice : ilsDeviceList) {
//			
//			VoLight voLight = new VoLight();
//			if (ilsDevice.getChannelType().equals("R")) {  //如果是开关设备
//				int channelId = Integer.parseInt(ilsDevice.getChannelId());
//				voLight.setIlsDevice(ilsDevice);
//				voLight.setIsOn((int)(modelIds.charAt(channelId-1)));
//			}
//			if (ilsDevice.getChannelType().equals("D")) { //如果调光设备
//				//设置调光值
//				String[] split = response.split(",");
//				int channelId = Integer.parseInt(ilsDevice.getChannelId());
//				voLight.setDimValue(Integer.parseInt(split[channelId]));
//			}
//			lightList.add(voLight);
//		}
//		
//		return lightList;
//	}
//	
//	public static void main(String[] args) {
//		String response = "010FA:11001110,00,00,00,16";
//		String modelIds = response.substring(response.indexOf(":")+1, response.indexOf(":")+9); //灯光状态码
//		
//		System.out.println(modelIds.charAt(1));
//	}
//	
	
}
