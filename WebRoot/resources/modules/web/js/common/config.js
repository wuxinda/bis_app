var config = {
	// 当前第几页，从0开始
	begin : 0,
	// 默认分页大小
	count : 10,
	projectName:location.pathname.substring(0,location.pathname.substr(1).indexOf('/')+1),
	// 项目根目录
	serverPath:location.protocol + '//' + location.host,
	// web端服务器请求地址
	server : location.protocol + '//' + location.host+ "/web/",
	// 接口地址
	interfaceServer : location.protocol + '//' + location.host+ '/web/',
	// 静态页面跳转地址
    jumpServer:location.protocol + '//' + location.host+ '/webJump/'
};

if (location.href.indexOf(config.projectName) != -1) {
	// 项目根目录
	config.serverPath = location.protocol + '//' + location.host + config.projectName;
	// web端服务器请求地址
	config.server = location.protocol + '//' + location.host + config.projectName+"/web/",
	// 接口地址
	config.interfaceServer = location.protocol + '//' + location.host + config.projectName + '/web/',
	// 静态页面跳转地址
	config.jumpServer = location.protocol + '//' + location.host + config.projectName + '/webJump/';
}
/** ********************静态页面跳转管理地址--begin--**************************** */
// 首页跳转地址
config.index = config.jumpServer+"index";
config.index2 = config.jumpServer+"index2";
//馆藏量跳转地址
config.holding = config.jumpServer+"holding";
//库房平面图跳转地址
config.layout = config.jumpServer+"layout";
//温湿度跳转地址
config.humiture = config.jumpServer+"humiture";
//视频跳转地址
config.video = config.jumpServer+"video";
//档案出入跳转地址
config.archive = config.jumpServer+"archive";
//报警跳转地址
config.alarm = config.jumpServer+"alarm";
//后台跳转地址
config.service = config.serverPath+"/service";
//能耗跳转地址
config.ecs = config.jumpServer+"ecs";
//未处理任务
config.pendingList = config.jumpServer+"pendingList";
//门禁跳转地址
config.access = config.jumpServer+"access";
//密集架跳转地址
config.ims = config.jumpServer+"ims";
//智能分析页面跳转
config.zntj_datj = config.jumpServer+"zntj_datj";
//智能分析页面跳转
config.smartlog1 = config.jumpServer+"smartlog1";
//异常报警日志跳转
config.alarmError = config.jumpServer+"alarmError";
//待处理日志
config.smartlogPending = config.jumpServer+"smartlogPending";
//待处理任务列表
config.pendingList = config.jumpServer+"pendingList";
//待处理任务列表
config.pendingList2 = config.jumpServer+"pendingList2";
//智能分析页面跳转
config.ywcz_dacx = config.jumpServer+"ywcz_dacx";
//智能分析页面跳转
config.znfx_dahx = config.jumpServer+"znfx_dahx";
//智能分析页面跳转
config.hump = config.jumpServer+"hump";
//智能分析页面跳转
config.deviceop1 = config.jumpServer+"deviceop1";
//智能分析页面跳转
config.smartlog2 = config.jumpServer+"smartlog2";
/** ********************静态页面跳转管理地址--end--****************************** */

/** ********************动态数据异步加载管理地址--begin--************************* */
// 加载最新报警数据
config.getNewAlarmList = config.interfaceServer+"alarm/getNewAlarmList";
// 加载最新档案操作数据
config.getNewAmsActLogList = config.interfaceServer+"amsActlog/getNewAmsActLogList";
// 加载馆藏量数据
config.getNewHolding = config.interfaceServer+"holding/getNewHolding";
// 加载温湿度数据
config.getNewHumiture = config.interfaceServer+"humiture/getNewHumiture";
// 加载馆藏量基础统计数据数量（库房，密集架，档案）
config.getBaseNum = config.interfaceServer+"holding/getBaseNum";
// 加载馆藏量获取档案统计数据
config.getAmsSatas = config.interfaceServer+"holding/getAmsSatas";
// 加载库房温湿度报表
config.getHumitureReport = config.interfaceServer+"humiture/getHumitureReport";
// 加载库房温湿度报表
config.getHumiturexlsReport = config.interfaceServer+"humiture/toexcel";
// 获取库房档案进出统计
config.getNewInOutAms = config.interfaceServer+"archive/getNewInOutAms";
// 获取待执行档案列表
config.getNewAuditAms = config.interfaceServer+"archive/getNewAuditAms";
// 获取报警统计
config.getAlarmSata = config.interfaceServer+"alarm/getAlarmSata";
// 获取未处理报警列表
config.searchList = config.interfaceServer+"alarm/searchList";
// 处理报警
config.handleAlarm = config.interfaceServer+"alarm/handle";
// 获取视频设备基础数据
config.getVideoList = config.interfaceServer+"video/getVideoList";
// 获取视频连接属性
config.getVideoLinkVal = config.interfaceServer+"video/getVideoLinkVal";
// 获取视频通道号
config.getDeviceChanal = config.interfaceServer+"video/getDeviceChanal";
//获取指定展示的视频列表
config.getFixVideoList = config.interfaceServer+"video/getFixVideoList";
//视频插件下载
config.getVideoplugin = config.interfaceServer+"video/getVideoplugin";
// 馆能耗页数据
config.getTotalData = config.interfaceServer+"ecs/getTotalData";
// 馆能耗类型统计数据
config.getTypeEcs = config.interfaceServer+"ecs/getTypeEcs";
// 馆能耗库房统计数据
config.getStoreEcs = config.interfaceServer+"ecs/getStoreEcs";
// 馆能耗库房统计数据
config.getNewIntyList = config.interfaceServer+"rfid/getNewIntyList";
//获取rfid盘点记录
config.getRfidIntyByday = config.serverPath+"/rfidInty/getRfidIntyByday";
//获取档案与rfid的总数
config.getRfid = config.serverPath+"/rfidInty/getRfid";
// 按类型查询rfid信息
config.getRfidByType = config.serverPath+"/rfidInty/getRfidByType";
//获取最新rfid记录
config.getNewRfid = config.serverPath+"/rfidInty/getNewRfid";
//按库房查询档案的总藏量
config.getCountAms = config.serverPath+"/amsArchives/getCountAms";
//获取库房列表
config.getStoreList = config.serverPath+"/wmsStore/getWmsStore";

//获取库房列表
config.getWmsStore = config.interfaceServer+"ims/getWmsStore";

//获取库区列表
config.getStoreAreaByStoreId = config.interfaceServer+"ims/getStoreAreaByStoreId";
//获取密集架列
config.getStoreDeviceList = config.interfaceServer+"ims/getStoreDeviceListProperty";
//通过密集架id查找视频通道号
config.getVideoNo = config.interfaceServer+"ims/getVideoNo";
//密集架操作
config.operateDevice = config.interfaceServer+"layout/operateDevice";
//获取门禁数据列表
config.getAccessDataList=config.serverPath+"/deviceAccessRecord/getAccessDataList";
//获取门禁列表
config.getAccessList=config.serverPath+"/deviceManage/getAccessList";
//按时间统计门禁数据列表
config.getAccessDataCount=config.serverPath+"/deviceAccessRecord/getAccessDataCount";
//通过库房获取库区
config.getWmsStroreArea=config.serverPath+"/wmsStoreArea/getStoreAreaByStoreId";
//通过关键词搜索档案
config.searchArchives = config.serverPath+"/amsArchives/searchArchives";
//档案出入日志
config.searchRfidInout = config.serverPath+"/rfidInout/searchRfidInout";
//获取温湿度日志
config.getHumByTime = config.serverPath+"/perceptionHcs/getHumByTime";
//查询最新档案操作纪录
config.getAmsArchiveAudit = config.serverPath+"/amsArchivesAudit/getAmsArchiveAudit";
//查询最新档案操作纪录统计
config.getAmsArchiveAuditCount = config.serverPath+"/amsArchivesAudit/getAmsArchiveAuditCount";
//状态统计
config.getAlarmCount = config.serverPath+"/alarmManage/getAlarmCount";
//查询设备异常报警信息
config.selectAlarmManageByDevice = config.serverPath+"/alarmManage/selectAlarmManageByDevice";
//查询环境异常报警信息
config.selectAlarmManageByEnvironment = config.serverPath+"/alarmManage/selectAlarmManageByEnvironment";
//获取所有设备列表
config.getDevice = config.serverPath+"/deviceManage/getDevice";
//获取所有人员列表
config.getAdminUserName = config.serverPath+"/adminUser/getAdminUserName";

config.getArchiveYearAdd = config.serverPath+"/amsArchives/getArchiveYearAdd";
//档案出入库信息统计
config.getAdminUserName = config.serverPath+"/amsArchivesAudit/getArchivesAuditCount";
//档案出入库列表
config.getRfidInoutList = config.serverPath+"/rfidInout/getRfidInoutList";
//档案图标
config.selectStrore = config.serverPath+"/amsArchives/selectStrore";
//档案在架图表
config.selectInflag = config.serverPath+"/amsArchives/selectInflag";
//档案分类调档统计图表
config.selectAmsArchivesByCategory=config.serverPath+"/amsArchives/selectAmsArchivesByCategory";
//库房分类调档统计图表
config.selectAmsArchivesByStoreId=config.serverPath+"/amsArchives/selectAmsArchivesByStoreId";
//获取设备信息
config.selectDeviceForArchive = config.serverPath+"/deviceManage/selectDeviceForArchive";
//获取档案类型列表
config.selectAllAmsArchivesType = config.serverPath+"/amsArchivesType/selectAllAmsArchivesType";
//获取档案列表
config.selectArchivesListFroPlace = config.serverPath+"/amsArchives/selectArchivesListFroPlace";
//内部申请调档
config.insertArchivesAuditIn = config.serverPath+"/amsArchivesAuditIn/insertArchivesAuditIn";
config.insertArchivesAudit = config.serverPath+"/amsArchivesAudit/insertArchivesAudit";
config.getAuditAmsList =config.interfaceServer+"archive/getAuditAmsList";
//档案出入库导出
config.getRfidtoexcel = config.serverPath+"/rfidInout/getRfidtoexcel";
//档案出入库导出
config.getAuditAmsListToexcel = config.serverPath+"/web/archive/getAuditAmsListToexcel";
//档案出入库导出
config.updateArchivesStatus = config.serverPath+"/api/rfid/updateArchivesStatus";
config.updateWBArchivesStatus = config.serverPath+"/api/rfid/updateWBArchivesStatus";
//档案出入库导出
config.imageService = config.serverPath+"/logUserImage/imageService";
//档案出入库导出
config.getImgUrl = config.serverPath+"/api/store/getImgUrl";
config.deleteImg = config.serverPath+"/logUserImage/deleteImg";
//获取当日盘点错架记录
config.getSumCuoDangByEnname = config.serverPath+"/rfidInty/getSumCuoDangByEnname";

//获取盘点统计信息
config.selectRfidInfo = config.serverPath+"/rfidInty/selectRfidInfo";
//获取盘点结果统计
config.selectRfidResult = config.serverPath+"/rfidInty/selectRfidResult";

//获取首页的快速搜索记录
config.indexFindArchives = config.interfaceServer+"/archive/indexFindArchives";
//快速调档
config.operateDeviceFast = config.interfaceServer+"/layout/operateDeviceFast";
//档案异常数据查询
config.selectAlarmArchives = config.serverPath+"/amsArchives/selectAlarmArchives";



/** ********************动态数据异步加载管理地址--end----************************* */

