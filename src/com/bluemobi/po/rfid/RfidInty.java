package com.bluemobi.po.rfid;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：rfid_inty
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-10
 * 
 */
@Table(name="rfid_inty")
public class RfidInty extends AbstractObject implements Serializable{

    public static final long serialVersionUID = 1L;

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rfidIntyId;
    // 档案id
    private Integer archiveId;
    // 档案号
    private String archiveno;
    // 档案标题
    private String tittle;
    // 盘点状态：0.在架 1.离架 2.错架
    private String type;
    // 存址
    private String storeplace;
    // 用户id
    private Integer userId;
    // 盘点人员
    private String username;
    // 创建人
    private String creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 最后一次更新时间
    private Date mtime;
    // 备注
    private String remark;

    /** 获取 主键ID */
    public Integer getRfidIntyId() {
        return rfidIntyId;
    }

    /** 设置 主键ID */
    public void setRfidIntyId(Integer rfidIntyId) {
        this.rfidIntyId = rfidIntyId;
    }

    /** 获取 档案id */
    public Integer getArchiveId() {
        return archiveId;
    }

    /** 设置 档案id */
    public void setArchiveId(Integer archiveId) {
        this.archiveId = archiveId;
    }

    /** 获取 档案号 */
    public String getArchiveno() {
        return archiveno;
    }

    /** 设置 档案号 */
    public void setArchiveno(String archiveno) {
        this.archiveno = archiveno;
    }

    /** 获取 档案标题 */
    public String getTittle() {
        return tittle;
    }

    /** 设置 档案标题 */
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    /** 获取 盘点状态：0.在架 1.离架 2.错架 */
    public String getType() {
        return type;
    }

    /** 设置 盘点状态：0.在架 1.离架 2.错架 */
    public void setType(String type) {
        this.type = type;
    }

    /** 获取 存址 */
    public String getStoreplace() {
        return storeplace;
    }

    /** 设置 存址 */
    public void setStoreplace(String storeplace) {
        this.storeplace = storeplace;
    }

    /** 获取 用户id */
    public Integer getUserId() {
        return userId;
    }

    /** 设置 用户id */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 盘点人员 */
    public String getUsername() {
        return username;
    }

    /** 设置 盘点人员 */
    public void setUsername(String username) {
        this.username = username;
    }

    /** 获取 创建人 */
    public String getCreator() {
        return creator;
    }

    /** 设置 创建人 */
    public void setCreator(String creator) {
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

    /** 获取 备注 */
    public String getRemark() {
        return remark;
    }

    /** 设置 备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("RfidInty");
        sb.append("{rfidIntyId=").append(rfidIntyId);
        sb.append(", archiveId=").append(archiveId);
        sb.append(", archiveno=").append(archiveno);
        sb.append(", tittle=").append(tittle);
        sb.append(", type=").append(type);
        sb.append(", storeplace=").append(storeplace);
        sb.append(", userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", creator=").append(creator);
        sb.append(", ctime=").append(ctime);
        sb.append(", modifier=").append(modifier);
        sb.append(", mtime=").append(mtime);
        sb.append(", remark=").append(remark);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RfidInty) {
            RfidInty rfidInty = (RfidInty) obj;
            if (this.getRfidIntyId().equals(rfidInty.getRfidIntyId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getRfidIntyId();
        return pkStr.hashCode();
    }

}