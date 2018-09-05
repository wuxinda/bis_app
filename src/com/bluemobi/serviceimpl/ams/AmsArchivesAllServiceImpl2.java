package com.bluemobi.serviceimpl.ams;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.dao.wms.WmsStoreDao;
import com.bluemobi.po.ams.AmsArchives;
import com.bluemobi.po.ams.AmsArchivesFilingmethod;
import com.bluemobi.po.ams.AmsArchivesKeepyear;
import com.bluemobi.service.ams.AmsArchivesFilingmethodService;
import com.bluemobi.service.ams.AmsArchivesKeepyearService;
import com.bluemobi.service.ams.AmsArchivesService;
import com.bluemobi.service.ams.AmsArchivesTypeService;
import com.bluemobi.service.wms.WmsStoreAreaService;
import com.bluemobi.service.wms.WmsStoreService;
import com.bluemobi.tk.mybatis.mapper.ams.AmsArchivesMapper;

/**
 * 【档案详情表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "amsArchivesService")
public class AmsArchivesAllServiceImpl2 extends MybatisBaseServiceImpl implements AmsArchivesService {

	@Autowired
	private AmsArchivesDao amsArchivesDao;
	@Autowired
	private WmsStoreService wmsStoreService;
	@Autowired
	private WmsStoreAreaService wmsStoreAreaService;

	@Autowired
	private AmsArchivesMapper amsArchivesMapper;
	@Override
	public MyBatisBaseDao getDao() {
		return amsArchivesDao;
	}

	@Autowired
	private WmsStoreDao wmsStoreDao;
	@Autowired
	private AmsArchivesTypeService amsArchivesTypeService;
	@Autowired
	private AmsArchivesFilingmethodService amsArchivesFilingmethodService;
	@Autowired
	private AmsArchivesKeepyearService amsArchivesKeepyearService;

	/*
	 * 档案信息同步增删改
	 */
	@Override
	public String confirmarchivesSync(List<Map<String, Object>> datass) {
		try {
			for (Map<String, Object> paraMap : datass) {// 便利每条数据
				// 校验是否为空
				if (paraMap.get("archiveno") == null || paraMap.get("archiveno").equals("")) {
					return "档案号不能为空";
				} else if (paraMap.get("archivesType") == null || paraMap.get("archivesType").equals("")) {
					return "类型编码不能为空";
				} else if (paraMap.get("filingmethod") == null || paraMap.get("filingmethod").equals("")) {
					return "立卷方式不能为空";
				} else if (paraMap.get("title") == null || paraMap.get("title").equals("")) {
					return "正题名不能为空";
				} else if (paraMap.get("qzh") == null || paraMap.get("qzh").equals("")) {
					return "全宗号不能为空";
				}else if (paraMap.get("qzhName") == null || paraMap.get("qzhName").equals("")) {
					return "全宗名称不能为空";
				}/*
					 * else if (paraMap.get("paratitle") == null ||
					 * paraMap.get("paratitle").equals("")) { return "案卷标题不能为空"; }
					 */ else if (paraMap.get("keepyear") == null) {
					return "保管年限不能为空";
				} /*
					 * else if (paraMap.get("security").equals("") || paraMap.get("security") ==
					 * null) { return "密级等级不能为空"; }
					 *//*
						 * else if (paraMap.get("subject").equals("") || paraMap.get("subject") == null)
						 * { return "主题词不能为空"; } else if (paraMap.get("keyword").equals("") ||
						 * paraMap.get("keyword") == null) { return "关键词不能为空"; }
						 */ else if (paraMap.get("clientId") == null || paraMap.get("clientId").equals("")) {
					return "客户端ID不能为空";
				} else if (paraMap.get("type") == null || paraMap.get("type").equals("")) {
					return "同步类型不能为空";
				} else if (paraMap.get("totalNum") == null || paraMap.get("totalNum").equals("")) {
					// return "总库存不能为空";
					paraMap.put("totalNum", "1");
				} else if (paraMap.get("nowNum") == null || paraMap.get("nowNum").equals("")) {
					// return "实际库存不能为空";
					paraMap.put("nowNum", "1");
				}
				paraMap.put("archivesTypeId", paraMap.get("archiveType"));
				paraMap.put("filingMethod", paraMap.get("filingmethod"));
				paraMap.put("ClientID", paraMap.get("clientId"));
				paraMap.put("totalNumber", Integer.parseInt(String.valueOf(paraMap.get("totalNum"))));
				paraMap.put("nowNumber", Integer.parseInt(String.valueOf(paraMap.get("nowNum"))));
				paraMap.put("creator", paraMap.get("clientId"));
				paraMap.put("ctime", new Date());
				paraMap.put("roomnum", paraMap.get("qzh"));
				paraMap.put("qzName", paraMap.get("qzhName"));

				/**
				 * 
				 * 将传入的标识转化为保管年限的主键ID 校验数据字典 中信直接传保管年限，先去保管年限表查询是否有此保管年限如果有就得到id，并将id赋值给档案
				 * 如果没有就在保管年限中新建一条，并把这条保管年限的id赋值给档案
				 */
				Map<String, Object> mapForKeepyear = new HashMap<String, Object>();
				mapForKeepyear.put("sortOrder", paraMap.get("keepyear"));
				List<Map<String, Object>> lists01 = amsArchivesKeepyearService.selectMapList(mapForKeepyear);
				if (lists01 != null && lists01.size() > 0) {
					paraMap.put("keepyear", lists01.get(0).get("amsArchivesKeepyear").toString());
				} else {// 不存在就添加一条
					// return "请求失败,该保管年限不存在";
					AmsArchivesKeepyear amsArchivesKeepyear = new AmsArchivesKeepyear();
					amsArchivesKeepyear.setName(String.valueOf(paraMap.get("keepyear")));
					amsArchivesKeepyear.setSortOrder(String.valueOf(paraMap.get("keepyear")));
					amsArchivesKeepyear.setRemark(String.valueOf(paraMap.get("keepyear")));
					amsArchivesKeepyearService.insert(amsArchivesKeepyear);
					paraMap.put("keepyear", amsArchivesKeepyear.getAmsArchivesKeepyear());
				}

				/**
				 * 将传入的标识转化为立卷方式的主键ID
				 */
				Map<String, Object> mapForFilingmethod = new HashMap<String, Object>();
				mapForFilingmethod.put("sortOrder", paraMap.get("filingMethod"));
				List<Map<String, Object>> lists02 = amsArchivesFilingmethodService.selectMapList(mapForFilingmethod);
				if (lists02 != null && lists02.size() > 0) {
					paraMap.put("filingmethod", lists02.get(0).get("archivesFilingmethodId").toString());
				} else {
					AmsArchivesFilingmethod archivesFilingmethod = new AmsArchivesFilingmethod();
					archivesFilingmethod.setName(String.valueOf(paraMap.get("filingmethod")));
					archivesFilingmethod.setSortOrder(String.valueOf(paraMap.get("filingmethod")));
					archivesFilingmethod.setRemark(String.valueOf(paraMap.get("filingmethod")));
					amsArchivesFilingmethodService.insert(archivesFilingmethod);
					paraMap.put("filingMethod", archivesFilingmethod.getArchivesFilingmethodId());
				}
				/**
				 * 将传入的标识转化为档案类型的主键ID
				 */
				Map<String, Object> mapForArchiveType = new HashMap<String, Object>();
				mapForArchiveType.put("sortOrder", paraMap.get("archivesTypeId"));
				List<Map<String, Object>> lists03 = amsArchivesTypeService.selectMapList(mapForArchiveType);
				String archivesTypeId = "";
				if (lists03 != null && lists03.size() > 0) {
					archivesTypeId = lists03.get(0).get("archivesTypeId").toString();
					paraMap.put("archivesTypeId", archivesTypeId);
				} else {
					return "请求失败,该档案类型不存在";
				}

				/**
				 * 处理档案 中信业务逻辑：分两个仓库，临时库和正常库，档案首先进入临时库， 在临时库中可以进行修改删除等加工操作，加工好的档案会进入到正常库，
				 * 在正常库中不能进行删除修改操作，若想操作就必须先退回到临时库在临时库中操作，
				 * 档案进入正常库时会请求八益系统，进行添加操作，档案移除正常库是会请求八益系统进行删除操作
				 * 
				 * 唯一标示使用档案号与档案类型组合方式 八益系统： 删除时将档案状态变更为删除状态（只有状态是在库的时候（inflag 0:在库
				 * 1.出库）才能够删除）（inflag变更为-1） 添加时先判断数据库中是否有此份档案，如果没有直接添加一条新的，
				 * 如果有，先判断是否是删除状态，如果是删除状态将档案变更为在库，并更新档案 如果不是删除状态，说明此份档案重复，则同步失败
				 */
				String type = String.valueOf(paraMap.get("type"));
				Map<String, Object> mapForArchives = new HashMap<String, Object>();
				mapForArchives.put("archiveno", paraMap.get("archiveno"));
				mapForArchives.put("archivesTypeId", archivesTypeId);
				List<AmsArchives> amsArchives = this.selectObjectList(mapForArchives);
				// 1是添加
				AmsArchives amsArchive = new AmsArchives();
				if (type.equals("1")) {
					if (amsArchives.size() > 0) {// 文档已存在，修改字段即可
						// return "请求失败，文档已经存在!";
						amsArchive = amsArchives.get(0);
						amsArchive.setTitle(String.valueOf(paraMap.get("title")));
						amsArchive.setArchivesTypeId(String.valueOf(paraMap.get("archivesTypeId")));
						amsArchive.setFilingmethod(String.valueOf(paraMap.get("filingMethod")));
						// amsArchive.setParatitle(String.valueOf(paraMap.get("paratitle")));
						amsArchive.setKeyword(String.valueOf(paraMap.get("keyword")));
						amsArchive.setRoomnum(String.valueOf(paraMap.get("roomnum")));
						amsArchive.setQzName(String.valueOf(paraMap.get("qzName")));
						// amsArchive.setSubject(String.valueOf(paraMap.get("subject")));
						// amsArchive.setSecurity(String.valueOf(paraMap.get("security")));
						amsArchive.setMtime(new Date());
						amsArchive.setModifier(Integer.parseInt(String.valueOf(paraMap.get("creator"))));
						amsArchive.setTotalNumber(Integer.parseInt(String.valueOf(paraMap.get("totalNumber"))));
						amsArchive.setNowNumber(Integer.parseInt(String.valueOf(paraMap.get("nowNumber"))));
						if (amsArchive.getInflag() == -1 && amsArchive.getCheckStatus() == -1) {
							amsArchive.setInflag(0);
							amsArchive.setCheckStatus(5);
						} else if (amsArchive.getInflag() == -2 && amsArchive.getCheckStatus() == -1) {
							amsArchive.setInflag(1);
							amsArchive.setCheckStatus(4);
						}
						this.update(amsArchive);
						continue;
					} else {
						amsArchive.setArchiveno(String.valueOf(paraMap.get("archiveno")));
						amsArchive.setTitle(String.valueOf(paraMap.get("title")));
						amsArchive.setArchivesTypeId(String.valueOf(paraMap.get("archivesTypeId")));
						amsArchive.setFilingmethod(String.valueOf(paraMap.get("filingMethod")));
						// amsArchive.setParatitle(String.valueOf(paraMap.get("paratitle")));
						amsArchive.setKeyword(String.valueOf(paraMap.get("keyword")));
						amsArchive.setRoomnum(String.valueOf(paraMap.get("roomnum")));
						amsArchive.setQzName(String.valueOf(paraMap.get("qzName")));
						// amsArchive.setSubject(String.valueOf(paraMap.get("subject")));
						// amsArchive.setSecurity(String.valueOf(paraMap.get("security")));
						amsArchive.setMtime(new Date());
						amsArchive.setModifier(Integer.parseInt(String.valueOf(paraMap.get("creator"))));
						amsArchive.setTotalNumber(Integer.parseInt(String.valueOf(paraMap.get("totalNumber"))));
						amsArchive.setNowNumber(Integer.parseInt(String.valueOf(paraMap.get("nowNumber"))));
						amsArchive.setInflag(1);
						amsArchive.setCheckStatus(4);// 待上架
						this.insert(amsArchive);
						continue;
					}
				}

				// 2是删除
				if (type.equals("2")) {
					if (amsArchives.size() >= 1) {
						amsArchive = amsArchives.get(0);
						if (amsArchive.getInflag() == 0 && amsArchive.getCheckStatus() == 5) {// 在库删除
							amsArchive.setInflag(-1);
							amsArchive.setCheckStatus(-1);
							this.update(amsArchive);
							continue;
						} else if (amsArchive.getInflag() == 1 && amsArchive.getCheckStatus() == 4) {// 未上架删除
							amsArchive.setInflag(-2);
							amsArchive.setCheckStatus(-2);
							this.update(amsArchive);
							continue;
						} else {
							return "请求失败，文档状态不支持删除";
						}

					}
					return "请求失败，要删除的文档不存在";
				}
				return "请求失败，同步类型不存在";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "请求失败，数据解析出错";
		}
		return null;
	}

	/**
	 * 
	 * @Date:2017年5月12日
	 * @author:Tony
	 * @param paraMap
	 * @return
	 */
	@Override
	public String confirmdictSync(Map<String, Object> paraMap) {
		String result = null;
		if (paraMap.get("dictNo").equals("1")) {
			result = amsArchivesTypeService.dictSync(paraMap);
		} else if (paraMap.get("dictNo").equals("2")) {
			result = amsArchivesFilingmethodService.dictSync(paraMap);
		} else if (paraMap.get("dictNo").equals("3")) {
			result = amsArchivesKeepyearService.dictSync(paraMap);
		} else if (paraMap.get("dictNo").equals("4")) {

		} else {
			return "请求失败：您同步的数据字典<<" + paraMap.get("dictNo") + ">>不存在";
		}
		return result;
	}

	@Override
	public Map<String, Object> getCountAms(Map<String, Object> map) {
		Map<String, Object> countAms = amsArchivesDao.getCountAms(map);
		Map<String, Object> maps = wmsStoreDao.selectObjectById(map);
		String storeName = maps.get("name").toString();
		countAms.put("storeName", storeName);
		return countAms;
	}

	/**
	 * rfid上下架数据同步
	 * 
	 * @Date:2017年5月12日
	 * @author:Tony
	 * @param paraMap
	 * @return
	 */
	@Override
	public String confirmrfidsxjSync(List<Map<String, Object>> datass) {
		try {
			for (Map<String, Object> paraMap : datass) {// 便利每条数据
				// 校验是否为空
				if (paraMap.get("archiveno") == null || paraMap.get("archiveno").equals("")) {
					return "档案号不能为空";
				} else if (paraMap.get("archivesType") == null || paraMap.get("archivesType").equals("")) {
					return "类型编码不能为空";
				} else if (paraMap.get("clientId") == null || paraMap.get("clientId").equals("")) {
					return "客户端ID不能为空";
				}/* else if (paraMap.get("type") == null || paraMap.get("type").equals("")) {
					return "操作类型不能为空";
				}*/ else if (String.valueOf(paraMap.get("type")).equals("0")) {
					if (paraMap.get("storeplace") == null || paraMap.get("storeplace").equals("")) {
						return "存址不能为空";
					} else {
						paraMap.put("storeplace", "");
					}
				}
				// 处理档案地址信息，根据档案号和档案类型查出当前档案
				Map<String, Object> paraSelect = new HashMap<String, Object>();
				paraSelect.put("archiveno", paraMap.get("archiveno"));
				//paraSelect.put("archivesTypeId", paraMap.get("archivesType"));/////////////////////////////////// ???????????????
				List<AmsArchives> amsArchives = this.selectObjectList(paraSelect);
				if (amsArchives.size() > 1 || amsArchives.size() == 0) {
					return "数据错误（档案不存在或存在多份）";
				}
				// 盘点状态 0.待入库 1.入库审批中 2.上架审批中3.借阅审批中4.待上架5.在库6.待归还7.出库8.待出库 9.出库审批中10.任务操作中

				if (String.valueOf(paraMap.get("type")).equals("0")) {// 上架
					if (!String.valueOf(amsArchives.get(0).getCheckStatus()).equals("4")) {// 待上架
						return "档案状态不支持上架";
					}
					amsArchives.get(0).setInflag(0);// 在库
					amsArchives.get(0).setCheckStatus(5);// 在库
				} else if (String.valueOf(paraMap.get("type")).equals("1")) {// 下架
					if (!String.valueOf(amsArchives.get(0).getCheckStatus()).equals("5")) {// 在库
						return "档案状态不支持下架";
					}
					amsArchives.get(0).setInflag(1);// 出库
					amsArchives.get(0).setCheckStatus(4);// 待上架
					continue;
				} else {
					return "操作类型不存在";
				}
				// 格式化存址
				String[] storeplace = this.formatStoreplace(String.valueOf(paraMap.get("storeplace")));
				if(storeplace!=null) {
					
				}else {
					return "存址（"+String.valueOf(paraMap.get("storeplace"))+"）解析失败，请检查存址是否有误，或者库房库区基础数据有误！";
				}
				amsArchives.get(0).setStoreplace(storeplace[6]);
				amsArchives.get(0).setStroreId(Integer.parseInt(storeplace[0]));
				amsArchives.get(0).setStroreAreaId(Integer.parseInt(storeplace[1]));
				amsArchives.get(0).setStoreColumn(storeplace[2]);
				amsArchives.get(0).setStoreLr(storeplace[3]);
				amsArchives.get(0).setStoreSection(storeplace[4]);
				amsArchives.get(0).setStoreLayer(storeplace[5]);
				this.update(amsArchives);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "请求失败，数据解析出错";
		}
		return null;
	}

	/**
	 * 存址格式化方法
	 */
	public String[] formatStoreplace(String param) {
		String[] storeplace = new String[7];
		storeplace[0] = param.substring(0, 2);
		storeplace[1] = param.substring(2, 4);
		storeplace[2] = param.substring(4, 6);
		storeplace[3] = param.substring(6, 9);
		storeplace[4] = param.substring(9, 12);
		storeplace[5] = param.substring(12, 14);
		storeplace[6] = "";
		Map<String, Object> paraSelect = new HashMap<String, Object>();
		// 处理库房 输入库房code
		paraSelect.put("code",
				String.valueOf((Integer.parseInt(storeplace[0]) + 5) < 10
						? ("0" + String.valueOf(Integer.parseInt(storeplace[0]) + 5))
						: (Integer.parseInt(storeplace[0]) + 5)) + String.valueOf(storeplace[1]));
		// 查询库房
		List<Map<String, Object>> store = wmsStoreService.selectMapList(paraSelect);
		if (store.size() != 1) {
			return null;
		} else {
			storeplace[0] = String.valueOf(store.get(0).get("storeId"));// 得到库房id
			storeplace[6] +=String.valueOf(store.get(0).get("name"));
		}
		// 处理库区
		paraSelect.put("storeId", String.valueOf(storeplace[0]));
		paraSelect.put("code", String.valueOf(storeplace[2]));
		List<Map<String, Object>> storearea = wmsStoreAreaService.selectMapList(paraSelect);
		if (storearea.size() != 1) {
			return null;
		} else {
			storeplace[1] = String.valueOf(storearea.get(0).get("stroreAreaId"));// 得到库区id
			storeplace[6] +=String.valueOf(storearea.get(0).get("name"));
		}
		//处理密集架列号和左右面
		if(Integer.parseInt(storeplace[3])%2==1) {
			storeplace[3] = "L";
		}else {
			storeplace[2] = String.valueOf((Integer.parseInt(storeplace[3])/2));
			storeplace[3] = "R";
		}
		//处理列号
		storeplace[4] = String.valueOf(Integer.parseInt(storeplace[4]));
		//处理层
		storeplace[5] = String.valueOf(Integer.parseInt(storeplace[5]));
		storeplace[6] += storeplace[2]+"号"+storeplace[3]+"面"+storeplace[4]+"列"+storeplace[5]+"层";
		return storeplace;
	}

	/**
     * 
     * 按档案号和档案类型查询档案
     * @return
     */
	public Map<String, Object> findArchivesByNoAndType(Map<String, Object> map) {
		
		Map<String,Object> remap=amsArchivesDao.findArchivesByNoAndType(map);
		return remap;
	}

    /**
     * 
     * 档案搜索
     * @return
     */
    public Map<String,Object> searchArchives(Map<String,Object> map){
    	List<Map<String,Object>> list = amsArchivesDao.searchArchives(map);
    	int count=amsArchivesDao.searchArchivesCount(map);
    	Map<String,Object> resmap=new HashMap<String,Object>();
    	resmap.put("count", count);
    	resmap.put("data", list);
		return resmap;
    	
    }
    /**
     * 
     * 档案图表
     * @return
     */
    public List<Map<String,Object>> selectStrore(Map<String,Object> map){
    	List<Map<String,Object>> list = amsArchivesDao.selectStrore(map);
		return list;
    }
    /**
     * 
     * 档案在架图表
     * @return
     */
    public List<Map<String,Object>> selectInflag(Map<String,Object> map){
    	List<Map<String,Object>> list = amsArchivesDao.selectInflag(map);
		return list;
    	
    }
    
    /**
     * 档案分类调档统计图表
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectAmsArchivesByCategory(Map<String,Object> map){
    	List<Map<String,Object>> list=amsArchivesMapper.selectAmsArchivesByCategory(map);
		return list;
    }
    
    /**
     * 库房分类调档统计图表
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectAmsArchivesByStoreId(Map<String,Object> map){
    	List<Map<String,Object>> list=amsArchivesMapper.selectAmsArchivesByStoreId(map);
		return list;
    }
    
    /**
     * 获取所有档案数量
     */
    public Integer selectCountAll() {
    	return amsArchivesMapper.selectCount(null);
    }
    
    /**
     * 档案异常数据查询
     */
    public Map<String,Object> selectAlarmArchives(){
    	Map<String,Object> map=new HashMap<>();
    	List<Map<String,Object>> list=amsArchivesMapper.selectAlarmArchives();
    	map.put("list", list);
    	map.put("count", list.size());
    	return map;
    }
}
