package com.bluemobi.po.ams;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【档案申请审批表】持久化对象 数据库表：ams_archives_AuditIn
 * 
 * @author Riven
 * @date 2016-11
 * 
 */
public class AmsArchivesAuditIn extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 申请审批id
    private Integer AuditInId;
    // 档案id
    private Integer archivesId;
    //档案标题
    private String title;
    // 用户id
    private Integer userId;
    //用户名称
    private String userName;
    // 操作类型 0.入库申请（存档）  1.出库申请 （取档）	
    private String type;
    // 审核状态 0.待审批 1.审核通过  2.审核拒绝   3.已完成
    private String status;
    // 申请人
    private String applyUser;
    // 申请单号
    private String applyNo;
    // kehuduanid
    private String clientId;
    // 备注
    private String remark;
    // 创建人
    private Integer creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 最后一次更新时间
    private Date mtime;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /** 获取 申请审批id */
    public Integer getAuditInId() {
        return AuditInId;
    }

    /** 设置 申请审批id */
    public void setAuditInId(Integer AuditInId) {
        this.AuditInId = AuditInId;
    }

    /** 获取 档案id */
    public Integer getArchivesId() {
        return archivesId;
    }

    /** 设置 档案id */
    public void setArchivesId(Integer archivesId) {
        this.archivesId = archivesId;
    }

    /** 获取 用户id */
    public Integer getUserId() {
        return userId;
    }

    /** 设置 用户id */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 操作类型 0.入库申请  1.借阅申请		 */
    public String getType() {
        return type;
    }

    /** 设置 操作类型 0.入库申请  1.借阅申请		 */
    public void setType(String type) {
        this.type = type;
    }

    /** 获取 审核状态 0.待审批 1.审核通过  2.审核拒绝		 */
    public String getStatus() {
        return status;
    }

    /** 设置 审核状态 0.待审批 1.审核通过  2.审核拒绝		 */
    public void setStatus(String status) {
        this.status = status;
    }

    /** 获取 备注 */
    public String getRemark() {
        return remark;
    }

    /** 设置 备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** 获取 创建人 */
    public Integer getCreator() {
        return creator;
    }

    /** 设置 创建人 */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /** 获取 创建时间 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 修改人 */
    public Integer getModifier() {
        return modifier;
    }

    /** 设置 修改人 */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /** 获取 最后一次更新时间 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 最后一次更新时间 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AmsArchivesAuditIn");
        sb.append("{AuditInId=").append(AuditInId);
        sb.append(", archivesId=").append(archivesId);
        sb.append(", userId=").append(userId);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", ctime=").append(ctime);
        sb.append(", modifier=").append(modifier);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AmsArchivesAuditIn) {
            AmsArchivesAuditIn amsArchivesAuditIn = (AmsArchivesAuditIn) obj;
            if (this.getAuditInId().equals(amsArchivesAuditIn.getAuditInId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getAuditInId();
        return pkStr.hashCode();
    }

}