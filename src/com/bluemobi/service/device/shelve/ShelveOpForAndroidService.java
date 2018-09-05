package com.bluemobi.service.device.shelve;

/**
 * v6密集架安卓操作接口
 * <p>版权所有：上海物佳网络科技有限公司</p>
 * <p>项目名称：智慧管库平台2.2 </p>
 * @ClassName: ShelveOpForAndroidService 
 * @Description: 
 * @author samual
 * @version 2.2
 * @date 2016-7-19 上午10:46:53
 */
public interface ShelveOpForAndroidService {
	
	/**
	 * 密集架列左开
	 * @Title: open 
	 * @Description:
	 * @param ip 固定列ip
	 * @param port 固定列端口
	 * @param channel 列号
	 * @return void 返回类型 
	 * @throws
	 */
	public boolean leftOpen(String ip, int port, int channel);
	
	/**
	 * 密集架列右开
	 * @Title: open 
	 * @Description:
	 * @param ip 固定列ip
	 * @param port 固定列端口
	 * @param channel 列号
	 * @return void 返回类型 
	 * @throws
	 */
	public boolean rightOpen(String ip, int port, int channel);

	/**
	 * 密集架通风
	 * @Title: vent 
	 * @Description: 
	 * @param ip 固定列ip
	 * @param port 固定列端口
	 * @return void 返回类型 
	 * @throws
	 */
	public boolean vent(String ip, int port);
	
	/**
	 * 密集架合拢
	 * @Title: close 
	 * @Description: 
	 * @param ip 固定列ip
	 * @param port 固定列端口
	 * @return void 返回类型 
	 * @throws
	 */
	public boolean close(String ip, int port);
	
	/**
	 * 密集架停止
	 * @Title: stop 
	 * @Description: 
	 * @param ip 固定列ip
	 * @param port 固定列端口
	 * @return void 返回类型 
	 * @throws
	 */
	public boolean stop(String ip, int port);
	/**
	 * 密集架通电
	 * @Title: stop 
	 * @Description: 
	 * @param ip 固定列ip
	 * @param port 固定列端口
	 * @throws
	 */
	public boolean openPws(String ip, int port);
	/**
	 * 密集架通电
	 * @Title: stop 
	 * @Description: 
	 * @param ip 固定列ip
	 * @param port 固定列端口
	 * @throws
	 */
	public boolean closePws(String ip, int port);
	/**
	 * 根据档案id打开对应密集架
	 * @Title: stop 
	 * @Description: 
	 * @param ip 固定列ip
	 * @param archivesId 档案id
	 * @param port 固定列端口
	 * @throws
	 */
	public boolean openByArchivesId(String ip, int port,int archivesId);
}
