package com.bluemobi.service.ams;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【档案申请审批表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface AmsArchivesAuditService extends MybatisBaseService {
    /**
     * 根据用户分组获取审核列表
     * 
     * @param amsArchivesAudit
     * @return
     */
    public List<Map<String, Object>> getAuditUserGroupList(Map<String, Object> parmMap);

    /**
     * 批量操作审核(领导版本审批用)
     * 
     * @param archivesIds
     * @param handUserId
     * @param handType
     */
    public void confirmHandAmsArchivesAudit(String[] archivesIds, String handUserId, String handType);

    /**
     * 批量操作审核(3.0按通道提交任务使用)
     * 
     * @param archivesIds
     * @param handUserId
     * @param handType
     */
    public void confirmAmsArchivesAudit(String[] archivesIds, String handUserId, String handType);

    /**
     * 批量操作审核(3.1提交任务使用)
     * 
     * @param archivesIds
     * @param handUserId
     * @param handType
     */
    public String confirmCommitTaskAmsArchivesAudit(String[] archivesIds, String handUserId, String handType, Map<String, Object> parmMap);

    /**
     * 批量添加申请事项
     * 
     * @param amsIds
     * @param type
     * @param userId
     */
    public void addAmsArchivesAudits(String creator, String userId, String type, String[] amsIds, Map<String, Object> parmMap);

    /**
     * 获取任务详情
     * 
     * @param status
     *            任务（申请）状态
     * @param userId
     *            申请人
     */
    public List<Map<String, Object>> selectTaskInfo(String userId, String status);

    /**
     * 执行任务
     */
    public Map<String, Object> selectTaskExecute(Map<String, Object> parmMap);

    /**
     * 提交任务
     */
    public Map<String, Object> selectTaskSubmit(Map<String, Object> parmMap);

    /**
     * 获取审批档案集合
     */
    public Map<String, Object> selectHandleAms(String[] parmMap);

    /**
     * 
     * @param split
     * @return
     */
    public Integer confirmTaskCancel(String[] split);

    /**
     * 返回上一步
     * 
     * @param split
     * @param archivesIds
     * @return
     */
    Integer confirmGoHistory(String[] split, String[] archivesIdsin, String[] archivesIdsout);

    /**
     * 获取档案任务列表3.1
     * 
     * @param parmMap
     * @return
     */
    public List<Map<String, Object>> getTaskList(Map<String, Object> parmMap);

    /**
     * 获取任务单里档案列表3.1
     * 
     * @param parmMap
     * @return
     */
    public List<Map<String, Object>> getApplyAmsList(Map<String, Object> parmMap);

    /**
     * 检查申请单和档案状态是否可以提交任务3.1
     * 
     * @param parmMap
     * @return
     */
    public String checkstatus(Map<String, Object> parmMap);

    /**
     * 获取通道列表3.1
     * 
     * @param userId
     *            用户Id
     * @param status
     *            调档状态
     * @param applyNo
     *            申请单号
     * @return
     */
    public List<Map<String, Object>> getGalleryList(String userId, String status, String applyNo);

    /**
     * 批量添加调档事项3.1
     * return null成功
     * @param parmMap
     */
    public String confirmaddAmsApplys(Map<String, Object> parmMap);
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
}
