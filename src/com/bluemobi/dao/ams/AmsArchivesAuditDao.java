package com.bluemobi.dao.ams;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【档案申请审批表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface AmsArchivesAuditDao extends MyBatisBaseDao {
    /**
     * 根据用户分组获取审核列表
     * 
     * @param amsArchivesAudit
     * @return
     */
    public <T> List<T> getAuditUserGroupList(Map<String, Object> parmMap);

    /**
     * 批量操作审核事项
     * 
     * @param archivesIds
     * @param handType
     */
    public void handAmsArchivesAudit(Map<String, Object> parmMap);

    /**
     * 批量添加申请事项
     */
    public void addAmsArchivesAudits(Map<String, Object> parmMap);

    /**
     * 获取任务详情
     */
    public List<Map<String, Object>> selectTaskInfo(Map<String, Object> parmMap);

    /**
     * 获取任务库房档案数量
     */
    public List<Map<String, Object>> selectTaskStoreNum(Map<String, Object> parmMap);

    /**
     * 获取任务库房库区档案数量
     */
    public List<Map<String, Object>> selectTaskStoreAreaNum(Map<String, Object> parmMap);

    /**
     * 通过通道获取档案列表分页
     */
    public List<Map<String, Object>> selectTaskExecute(Map<String, Object> parmMap);

    /**
     * 获取审批档案集合审批时更新档案状态
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> selectHandleAms(Map<String, Object> map);

    /**
     * 获取档指定档案类型数量
     * 
     * @param map
     * @return
     */
    Integer selectAuditStatusCount(Map<String, Object> map);

    /**
     * 批量删除
     * 
     * @param map
     * @return
     */
    Integer updates(Map<String, Object> map);

    /**
     * 获取待审批/已完成档案列表
     * 
     * @return
     */
    List<Map<String, Object>> selectAuditAmsList(Map<String, Object> map);

    /**
     * 查询申请单中档案状态，验证是否能返回上一步
     * 
     * @return
     */
    List<Map<String, Object>> selectAmsIdByAuditId(Map<String, Object> map);

    /**
     * 获取任务列表3.1
     * 
     * @return
     */
    List<Map<String, Object>> getTaskList(Map<String, Object> map);

    /**
     * 获取任务列表存取数量3.1
     * 
     * @return
     */
    List<Map<String, Object>> getTaskListNum(Map<String, Object> map);

    /**
     * 获取任务单里档案列表3.1
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> getApplyAmsList(Map<String, Object> map);

    /**
     * 检查申请单和档案状态是否可以提交任务3.1
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> getApplyAms(Map<String, Object> map);

    /**
     * 提交任务更行档案状态3.1
     * 
     * @param map
     * @return
     */
    Integer commitTaskUpdayeAms(Map<String, Object> map);

    /**
     * 获取任务详情3.1
     */
    public List<Map<String, Object>> selectTaskGallaryList(Map<String, Object> parmMap);

    /**
     * 获取任务库房档案数量3.1
     */
    public List<Map<String, Object>> selectTaskStoreNums(Map<String, Object> parmMap);

    /**
     * 获取任务库房库区档案数量3.1
     */
    public List<Map<String, Object>> selectTaskStoreAreaNums(Map<String, Object> parmMap);

    /**
     * 获取所有任务的档案总数3.1
     * 
     * @param map
     * @return
     */
    Integer selectTaskAmsNums(Map<String, Object> map);

    /**
     * 获取通道内档案列表3.1
     * 
     * @param parmMap
     * @return
     */
    public List<Map<String, Object>> getGalleryAmsList(Map<String, Object> parmMap);
    /**
     * 档案出入库调用外部服务接口
     * 
     * @param parmMap
     * @return
     */
    public List<Map<String, String>> getOtherAPIparam(Map<String, Object> parmMap);
    /**
     * 档案出入库信息获取
     * 
     * @param parmMap
     * @return
     */
    public List<Map<String, String>> getAmsArchiveAudit();
    /**
     * 档案当日出入库信息统计
     * 
     * @param parmMap
     * @return
     */
    public Map<String, String> getAmsArchiveAuditCount();
    /**
     * 档案出入库信息统计
     * 
     * @param parmMap
     * @return
     */
    public List<Map<String, String>> getArchivesAuditCount();
    /**
     * 获取取档还档列表
     * 
     * @param parmMap
     * @return
     */
    public List<Map<String, String>> getArchivesAuditList(Map<String, Object> parmMap);
    /**
     * 获取取档还档列表
     * 
     * @param parmMap
     * @return
     */
    public int getAmsArchivesAuditCountToday();
    /**
     * 获取最新更改数据
     * 
     * @param parmMap
     * @return
     */
    public Map<String, Object> getNewData();
    /**
     * 获取取档还档列表
     * 
     * @param parmMap
     * @return
     */
    public List<Map<String, Object>> getAmsArchivesAudit(Map<String, Object> parmMap);
    /**
     * 获取取档还档列表
     * 
     * @param parmMap
     * @return
     */
    public int getAmsArchivesAuditCount(Map<String, Object> parmMap);
}
