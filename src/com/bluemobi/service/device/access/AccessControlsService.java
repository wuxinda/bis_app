package com.bluemobi.service.device.access;

import com.bluemobi.service.device.access.util.ZKQueryResponse;


/**
 * <p>
 * Title: 门禁硬件接口
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
public interface AccessControlsService {
	
	/**
	 * 开门
	 * 
	 * @param request
	 * @return
	 */
	public ZKQueryResponse open(int id);
	
	/**
	 * 关门
	 * 
	 * @param request
	 * @return
	 */
	public ZKQueryResponse close(int id);
	
	/**
	 * 查询状态
	 * 
	 * @param request
	 * @return
	 */
	public ZKQueryResponse query();
}
