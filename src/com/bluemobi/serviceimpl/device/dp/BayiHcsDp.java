package com.bluemobi.serviceimpl.device.dp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appcore.context.AppContext;
import com.bluemobi.dao.alarm.AlarmHcsdeviceConfigDao;
import com.bluemobi.dao.alarm.AlarmManageDao;
import com.bluemobi.dao.alarm.AlarmTypeDao;
import com.bluemobi.dao.device.DeviceManageDao;
import com.bluemobi.dao.perception.PerceptionHcsDao;
import com.bluemobi.po.alarm.AlarmHcsdeviceConfig;
import com.bluemobi.po.alarm.AlarmManage;
import com.bluemobi.po.alarm.AlarmType;
import com.bluemobi.po.device.DeviceManage;
import com.bluemobi.po.perception.PerceptionHcs;
import com.bluemobi.serviceimpl.device.dp.base.AbstractHcsDp;

/**
 * 温湿度设备数据感知具体处理类
 * 
 * @author Riven
 * 
 */

public class BayiHcsDp extends AbstractHcsDp {
    private static final Logger LOGGER = LoggerFactory.getLogger(BayiHcsDp.class);
    // socket返回数据
    private List<String> datas = null;
    // 解析后数据
    List<PerceptionHcs> lists = null;
    private PerceptionHcsDao perceptionHcsDao = AppContext.getBean("perceptionHcsDao");
    private DeviceManageDao deviceManageDao = AppContext.getBean("deviceManageDao");
    private AlarmManageDao alarmManageDao = AppContext.getBean("alarmManageDao");
    private AlarmTypeDao alarmTypeDao = AppContext.getBean("alarmTypeDao");
    private AlarmHcsdeviceConfigDao alarmHcsdeviceConfigDao = AppContext.getBean("alarmHcsdeviceConfigDao");

    /**
     * 设备连接
     */
    public void connect() {
	datas = new ArrayList<String>();
	String[] str =  new String[]{":温湿度;",":空调状态;",":去湿状态;",":增湿状态;",":通风状态;",":净化状态;",":告警状态;"};
	// 1.得到设备ip port分组
	List<Map<String, Object>> linkList = deviceManageDao.getHcsDevLinkValue();
	// 2.遍历连接到各个ip port连接到设备
	for (final Map<String, Object> list : linkList) {
	    final StringBuilder sb = new StringBuilder();
	    for(int i=0;i<str.length;i++){
		sb.append("查询：区域" + list.get("minId") + "-" + list.get("maxId") + str[i]);
	    }
	    String response = null;
	    // 控制调用接口的时间
	    ExecutorService executor = Executors.newSingleThreadExecutor();
	    FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
		public String call() {
		    try {
			StringBuilder result = new StringBuilder();
			@SuppressWarnings("resource")
			Socket socket = new Socket(String.valueOf(list.get("ip")), (int) Integer.parseInt(String.valueOf(list.get("port"))));// 温湿度控制主机ip和端口
			OutputStream os = socket.getOutputStream();
			os.write(sb.toString().getBytes("GBK"));
			os.flush();
			InputStream in = socket.getInputStream();
			byte[] buf = new byte[1024];
			Thread.sleep(1000);
			String response = new String(buf, 0, in.read(buf), "GBK");
			result.append(response);
			return result.toString();
		    } catch (Exception e) {
			LOGGER.error("连接温控出错", e);
		    }
		    return null;
		}
	    });
	    executor.execute(futureTask);
	    try {
		response = futureTask.get(5000, TimeUnit.MILLISECONDS);
	    } catch (InterruptedException e) {
		futureTask.cancel(true);
	    } catch (ExecutionException e) {
		futureTask.cancel(true);
	    } catch (TimeoutException e) {
		futureTask.cancel(true);
	    } finally {
		executor.shutdown();
	    }
	    if (null == response) {
		dealHcsAlarm(Integer.parseInt(String.valueOf(list.get("deviceId"))), 1);
		continue;
	    } else {
		datas.add(response);
	    }
	}

    }

    /**
     * 设备感知数据收集
     */
    public void collect() {
	lists = new ArrayList<PerceptionHcs>();
	for (String data : datas) {
	    // 解析返回数据
	    int indexQuyu = data.lastIndexOf("区域");
	    String quyuNums = data.substring(indexQuyu + 2, indexQuyu + 3);
	    String replaceStr = data.replace("查询数据;", "").replace("状态数据;", "");
	    String[] data1 = replaceStr.split("区域");
	    for (int i = 1; i <= Integer.valueOf(quyuNums); i++) {
		PerceptionHcs entity = new PerceptionHcs();
		entity.setRemark(String.valueOf(i));
		for (int j = 1; j < data1.length; j++) {
		    String subData = data1[j];
		    if (Integer.valueOf(subData.substring(0, 1)) == i) {
			// 如果是此区域
			if (subData.contains("温度")) {
			    int start = subData.indexOf("温度");
			    int end = subData.indexOf("℃");
			    String status = subData.substring(start + 3, end);
			    entity.setTemperature(status);
			}
			if (subData.contains("湿度")) {
			    int start = subData.indexOf("湿度");
			    int end = subData.indexOf("%RH");
			    String status = subData.substring(start + 3, end);
			    entity.setHumidity(status);
			}
			if (subData.contains("空调状态")) {
			    int start = subData.indexOf("空调状态");
			    String status = subData.substring(start + 5, start + 6);
			    entity.setAirStatus(String.valueOf(status));
			}
			if (subData.contains("去湿状态")) {
			    int start = subData.indexOf("去湿状态");
			    String status = subData.substring(start + 5, start + 6);
			    entity.setDryingStatus(String.valueOf(status));
			}
			if (subData.contains("增湿状态")) {
			    int start = subData.indexOf("增湿状态");
			    String status = subData.substring(start + 5, start + 6);
			    entity.setWettingStatus(String.valueOf(status));
			}
			if (subData.contains("通风状态")) {
			    int start = subData.indexOf("通风状态");
			    String status = subData.substring(start + 5, start + 6);
			    entity.setVentilationStatus(String.valueOf(status));
			}
			if (subData.contains("净化状态")) {
			    int start = subData.indexOf("净化状态");
			    String status = subData.substring(start + 5, start + 6);
			    entity.setCleansingStatus(String.valueOf(status));
			}
			if (subData.contains("告警状态")) {
			    int start = subData.indexOf("告警状态");
			    String status = subData.substring(start + 5, start + 6);
			    entity.setWarningStatus(String.valueOf(status));
			}
			entity.setCollectDate(new Date());
		    }
		}
		lists.add(entity);
	    }
	}
    }

    /**
     * 设备感知数据分析，形成报警纪录
     */
    public void analysis() {
	try {
	    // 获取设备与连接属性对应集合
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("deviceType", 4);
	    map.put("valueName", "第三方Id");
	    List<Map<String, Object>> list = deviceManageDao.getDevLinkValue(map);// 数据库设备列表
	    for (PerceptionHcs perceptionHcs : lists) {
		for (Map<String, Object> lis : list) {
		    if (String.valueOf(lis.get("threeId")).equals(String.valueOf(perceptionHcs.getRemark()))) {
			perceptionHcs.setDeviceId(Integer.parseInt(String.valueOf(lis.get("deviceId"))));
			// 处理Log获取当前设备最新的log进行对比是否变化
			Map<String, Object> rmap = new HashMap<String, Object>();
			rmap.put("deviceId", lis.get("deviceId"));
			List<PerceptionHcs> dperceptionHcs = perceptionHcsDao.getDevicelastHcsLog(rmap);
			if (dperceptionHcs.get(0) == null) {// 如果查到该设备没有log就差入一条新的
			    perceptionHcsDao.insert(perceptionHcs);
			} else if ((!perceptionHcs.getTemperature().equals(dperceptionHcs.get(0).getTemperature()))
				|| (!perceptionHcs.getHumidity().equals(dperceptionHcs.get(0).getHumidity()))
				|| (!perceptionHcs.getAirStatus().equals(dperceptionHcs.get(0).getAirStatus()))
				|| (!perceptionHcs.getDryingStatus().equals(dperceptionHcs.get(0).getDryingStatus()))
				|| (!perceptionHcs.getWettingStatus().equals(dperceptionHcs.get(0).getWettingStatus()))
				|| (!perceptionHcs.getVentilationStatus().equals(dperceptionHcs.get(0).getVentilationStatus()))
				|| (!perceptionHcs.getCleansingStatus().equals(dperceptionHcs.get(0).getCleansingStatus()))
				|| (!perceptionHcs.getWarningStatus().equals(dperceptionHcs.get(0).getWarningStatus()))) {
			    perceptionHcsDao.insert(perceptionHcs);
			}
			// 判断数值是否超标
			try {
			    // 通过设备id获取该设备报警标准
			    List<AlarmHcsdeviceConfig> list0 = alarmHcsdeviceConfigDao.selectObjectList(rmap);
			    if (list0.size() > 0) {
				if (list0.get(0).getAlarmStatus().equals("1")) {// 0报警1不报警
				    continue;// 不报警直接结束本次循环
				}
				if (list0.get(0).getTemUp() != null && !list0.get(0).getTemUp().equals("")) {
				    if (Double.valueOf(perceptionHcs.getTemperature()) > Double.valueOf(list0.get(0).getTemUp())) {
					dealHcsAlarm(Integer.parseInt(String.valueOf(lis.get("deviceId"))), 2);
				    }
				}
				if (list0.get(0).getTemDown() != null && !list0.get(0).getTemDown().equals("")) {
				    if (Double.valueOf(perceptionHcs.getTemperature()) < Double.valueOf(list0.get(0).getTemDown())) {
					dealHcsAlarm(Integer.parseInt(String.valueOf(lis.get("deviceId"))), 3);
				    }
				}
				if (list0.get(0).getHumUp() != null && !list0.get(0).getHumUp().equals("")) {
				    if (Double.valueOf(perceptionHcs.getHumidity()) > Double.valueOf(list0.get(0).getHumUp())) {
					dealHcsAlarm(Integer.parseInt(String.valueOf(lis.get("deviceId"))), 4);
				    }
				}
				if (list0.get(0).getHumDown() != null && !list0.get(0).getHumDown().equals("")) {
				    if (Double.valueOf(perceptionHcs.getHumidity()) < Double.valueOf(list0.get(0).getHumDown())) {
					dealHcsAlarm(Integer.parseInt(String.valueOf(lis.get("deviceId"))), 5);
				    }
				}
			    }
			} catch (Exception e) {
			    LOGGER.error("处理温湿度报警出错", e);
			}
		    }

		}
	    }
	} catch (Exception e) {
	    // 出错日志
	    LOGGER.error(e.getLocalizedMessage());
	}
    }

    /**
     * 设备感知数据分析后，操控设备，进行智能处置
     */
    public void handle() {

    }

    /**
     * 处理温湿度报警
     * 
     * @param deviceId
     *            设备id
     * @param alarmType
     *            报警类型
     */
    public synchronized void dealHcsAlarm(Integer deviceId, Integer alarmTypeId) {
	// 检查该报警是否在报警状态中
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("deviceId", deviceId);
	map.put("alarmType", alarmTypeId);
	map.put("status", "0");// 0未处理
	try {
	    List<AlarmHcsdeviceConfig> list0 = alarmHcsdeviceConfigDao.selectObjectList(map);
	    List<Map<String, Object>> list = alarmManageDao.selectMapList(map);
	    if (list.size() == 0 && list0.get(0).getAlarmStatus().equals("0")) {// 0报警1不报警
		AlarmManage alarmManage = new AlarmManage();
		alarmManage.setAlarmType(alarmTypeId);
		alarmManage.setDeviceId(deviceId);
		DeviceManage deviceManage = deviceManageDao.selectObject(deviceId);
		alarmManage.setCategoryId(deviceManage.getCategoryId());
		alarmManage.setCtime(new Date());
		alarmManage.setDeviceName(deviceManage.getName());
		AlarmType alarmType = alarmTypeDao.selectObject(alarmTypeId);
		alarmManage.setLevel(String.valueOf(alarmType.getAlarmLevel()));
		alarmManage.setStatus("0");
		alarmManage.setStoreId(deviceManage.getStoreId());
		alarmManage.setStroreAreaId(deviceManage.getStroreAreaId());
		alarmManage.setRemark(alarmType.getAlarmTypeName());
		alarmManageDao.insert(alarmManage);
	    }
	} catch (Exception e) {
	    LOGGER.error("报警处理出错", e);

	}

    }
}
