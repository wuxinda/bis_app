package com.bluemobi.po.ams;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.appcore.model.AbstractObject;

/**
 * 【档案详情表】持久化对象 数据库表：ams_archives
 * 
 * @author Riven
 * @date 2016-11
 * 
 */
@Table(name="ams_archives")
public class AmsArchives extends AbstractObject implements Serializable{

    public static final long serialVersionUID = 1L;

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer archivesId;
    // RFID号
    private String rfidnum;
    // 档案类型id
    private String archivesTypeId;
    // 档案类型名称
    private String archivesTypeName;
    // 立卷方式 0.文件级 1.案卷级		
    private String filingmethod;
    // 全宗号
    private String roomnum;
    // 馆编档号
    private String archiveno;
    // 正题名
    private String title;
    // 并列标题
    private String paratitle;
    // 保管年限 0.永久 1.长期 2.短期 3.10年 4.30年				
    private String keepyear;
    // 密级 0.普通 1.秘密 2.机密 3.绝密		
    private String security;
    // 存址
    private String storeplace;
    // 主题词
    private String subject;
    // 关键词
    private String keyword;
    // 在库状态 在库状态 0:在库 1.出库		
    private Integer inflag;
    // 视频回放开始时间
    private Date playbackstarttime;
    // 临时记录出入库人
    private Integer inoutUserId;
    // 全宗号名字
    private String qzName;
    /**                                                       （审核拒绝）---审核拒绝 （档案状态改为在库/出库）
     * 手机端档案操作和申请状态之间的关系    在库/出库---（出库/入库申请）---待审批---（审核通过）---待执行（审核通过）---（执行任务完成）---已完成   
     * 手机端档案操作和档案状态之间的关系                                                                            入/出库审批中                            任务操作中                                                                 出库/在库
     * 服务端档案操作和档案状态之间的关系    待入库---（入库操作）--- 待上架 ---（上架操作）--- 在库---（借阅操作）---待归还---（归还操作）---在库          
     * 
     */
    // 盘点状态 0.待入库 1.入库审批中 2.上架审批中3.借阅审批中4.待上架5.在库6.待归还7.出库8.待出库 9.出库审批中10.任务操作中
    private Integer checkStatus;
    // 所属库房ID
    private Integer stroreId;
    // 所属库区ID
    private Integer stroreAreaId;
    // 所属列
    private String storeColumn;
    // 所属节
    private String storeSection;
    // 所属层
    private String storeLayer;
    // 方位左右
    private String storeLr;
    // 备注
    private String remark;
    // 创建人
    private Integer creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 密集架id
    private Integer deviceId;
    // 最后一次更新时间
    private Date mtime;
    // 现有库存
    private Integer nowNumber;
    // 总库存
    private Integer totalNumber;
    /** 获取 主键ID */
    public Integer getArchivesId() {
        return archivesId;
    }

    /** 设置 主键ID */
    public void setArchivesId(Integer archivesId) {
        this.archivesId = archivesId;
    }

    /** 获取 RFID号 */
    public String getRfidnum() {
        return rfidnum;
    }

    /** 设置 RFID号 */
    public void setRfidnum(String rfidnum) {
        this.rfidnum = rfidnum;
    }

    /** 获取 档案类型 */
    public String getArchivesTypeId() {
        return archivesTypeId;
    }

    /** 设置 档案类型 */
    public void setArchivesTypeId(String archivesTypeId) {
        this.archivesTypeId = archivesTypeId;
    }

    /** 获取 立卷方式 */
    public String getFilingmethod() {
        return filingmethod;
    }

    /** 设置 立卷方式 */
    public void setFilingmethod(String filingmethod) {
        this.filingmethod = filingmethod;
    }

    /** 获取 室编档号 */
    public String getRoomnum() {
        return roomnum;
    }

    /** 设置 室编档号 */
    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    /** 获取 馆编档号 */
    public String getArchiveno() {
        return archiveno;
    }

    /** 设置 馆编档号 */
    public void setArchiveno(String archiveno) {
        this.archiveno = archiveno;
    }

    /** 获取 正题名 */
    public String getTitle() {
        return title;
    }

    /** 设置 正题名 */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 并列标题 */
    public String getParatitle() {
        return paratitle;
    }

    /** 设置 并列标题 */
    public void setParatitle(String paratitle) {
        this.paratitle = paratitle;
    }

    /** 获取 保管年限 */
    public String getKeepyear() {
        return keepyear;
    }

    /** 设置 保管年限 */
    public void setKeepyear(String keepyear) {
        this.keepyear = keepyear;
    }

    /** 获取 密级 */
    public String getSecurity() {
        return security;
    }

    /** 设置 密级 */
    public void setSecurity(String security) {
        this.security = security;
    }

    /** 获取 存址 */
    public String getStoreplace() {
        return storeplace;
    }

    /** 设置 存址 */
    public void setStoreplace(String storeplace) {
        this.storeplace = storeplace;
    }

    /** 获取 主题词 */
    public String getSubject() {
        return subject;
    }

    /** 设置 主题词 */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /** 获取 关键词 */
    public String getKeyword() {
        return keyword;
    }

    /** 设置 关键词 */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /** 获取 在库状态 */
    public Integer getInflag() {
        return inflag;
    }

    /** 设置 在库状态 */
    public void setInflag(Integer inflag) {
        this.inflag = inflag;
    }

    /** 获取 视频回放开始时间 */
    public Date getPlaybackstarttime() {
        return playbackstarttime;
    }

    /** 设置 视频回放开始时间 */
    public void setPlaybackstarttime(Date playbackstarttime) {
        this.playbackstarttime = playbackstarttime;
    }

    /** 获取 临时记录出入库人 */
    public Integer getInoutUserId() {
        return inoutUserId;
    }

    /** 设置 临时记录出入库人 */
    public void setInoutUserId(Integer inoutUserId) {
        this.inoutUserId = inoutUserId;
    }
    /** 获取 全宗号名字 */
    public String getQzName() {
		return qzName;
	}
    /** 获取 全宗号名字 */
	public void setQzName(String qzName) {
		this.qzName = qzName;
	}

	/** 获取 盘点状态 */
    public Integer getCheckStatus() {
        return checkStatus;
    }

    /** 设置 盘点状态 */
    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    /** 获取 所属库房ID */
    public Integer getStroreId() {
        return stroreId;
    }

    /** 设置 所属库房ID */
    public void setStroreId(Integer stroreId) {
        this.stroreId = stroreId;
    }

    /** 获取 所属库区ID */
    public Integer getStroreAreaId() {
        return stroreAreaId;
    }

    /** 设置 所属库区ID */
    public void setStroreAreaId(Integer stroreAreaId) {
        this.stroreAreaId = stroreAreaId;
    }

    /** 获取 所属列 */
    public String getStoreColumn() {
        return storeColumn;
    }

    /** 设置 所属列 */
    public void setStoreColumn(String storeColumn) {
        this.storeColumn = storeColumn;
    }

    /** 获取 所属节 */
    public String getStoreSection() {
        return storeSection;
    }

    /** 设置 所属节 */
    public void setStoreSection(String storeSection) {
        this.storeSection = storeSection;
    }

    /** 获取 所属层 */
    public String getStoreLayer() {
        return storeLayer;
    }

    /** 设置 所属层 */
    public void setStoreLayer(String storeLayer) {
        this.storeLayer = storeLayer;
    }

    /** 获取 方位左右 */
    public String getStoreLr() {
        return storeLr;
    }

    /** 设置 方位左右 */
    public void setStoreLr(String storeLr) {
        this.storeLr = storeLr;
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
    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
    public String getArchivesTypeName() {
		return archivesTypeName;
	}

	public void setArchivesTypeName(String archivesTypeName) {
		this.archivesTypeName = archivesTypeName;
	}

	public Integer getNowNumber() {
		return nowNumber;
	}

	public void setNowNumber(Integer nowNumber) {
		this.nowNumber = nowNumber;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AmsArchives");
        sb.append("{archivesId=").append(archivesId);
        sb.append(", rfidnum=").append(rfidnum);
        sb.append(", archivesTypeId=").append(archivesTypeId);
        sb.append(", filingmethod=").append(filingmethod);
        sb.append(", roomnum=").append(roomnum);
        sb.append(", archiveno=").append(archiveno);
        sb.append(", title=").append(title);
        sb.append(", paratitle=").append(paratitle);
        sb.append(", keepyear=").append(keepyear);
        sb.append(", security=").append(security);
        sb.append(", storeplace=").append(storeplace);
        sb.append(", subject=").append(subject);
        sb.append(", keyword=").append(keyword);
        sb.append(", inflag=").append(inflag);
        sb.append(", playbackstarttime=").append(playbackstarttime);
        sb.append(", inoutUserId=").append(inoutUserId);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", stroreId=").append(stroreId);
        sb.append(", stroreAreaId=").append(stroreAreaId);
        sb.append(", storeColumn=").append(storeColumn);
        sb.append(", storeSection=").append(storeSection);
        sb.append(", storeLayer=").append(storeLayer);
        sb.append(", storeLr=").append(storeLr);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", ctime=").append(ctime);
        sb.append(", modifier=").append(modifier);
        sb.append(", mtime=").append(mtime);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", nowNumber=").append(nowNumber);
        sb.append(", totalNumber=").append(totalNumber);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AmsArchives) {
            AmsArchives amsArchives = (AmsArchives) obj;
            if (this.getArchivesId().equals(amsArchives.getArchivesId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getArchivesId();
        return pkStr.hashCode();
    }

}