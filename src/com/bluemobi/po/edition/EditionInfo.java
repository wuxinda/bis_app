package com.bluemobi.po.edition;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理】持久化对象 数据库表：edition_info
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-06
 * 
 */
public class EditionInfo extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 版本id
    private Integer editionId;
    // 版本号
    private String editionNumber;
    // 应用名称
    private String editionName;
    // 应用存放地址
    private String editionUrl;
    // 应用平台：0.ios 1.安卓 2.ipad
    private Integer editionType;
    // 版本创建者
    private Integer editionCreator;
    // 版本创建时间
    private Date editionCreatetime;
    // 版本更新者
    private Integer editionUpdater;
    // 版本更新时间
    private Date editionUpdatetime;
    // 备注
    private String remark;
    private Integer isPublish;
    //应用类型 001.安卓手机app/iphone手机/ipad平板   002.固定列   003.移动列
    private String sunType;

    public String getSunType() {
		return sunType;
	}

	public void setSunType(String sunType) {
		this.sunType = sunType;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	/** 获取 版本id */
    public Integer getEditionId() {
        return editionId;
    }

    /** 设置 版本id */
    public void setEditionId(Integer editionId) {
        this.editionId = editionId;
    }

    /** 获取 版本号 */
    public String getEditionNumber() {
        return editionNumber;
    }

    /** 设置 版本号 */
    public void setEditionNumber(String editionNumber) {
        this.editionNumber = editionNumber;
    }

    /** 获取应用名称 */
    public String getEditionName() {
        return editionName;
    }

    /** 设置 应用名称 */
    public void setEditionName(String editionName) {
        this.editionName = editionName;
    }

    /** 获取 版本地址 */
    public String getEditionUrl() {
        return editionUrl;
    }

    /** 设置 版本地址 */
    public void setEditionUrl(String editionUrl) {
        this.editionUrl = editionUrl;
    }

    /** 获取 不同服务版本类型区分：0.web 1.安卓 2.密集架 */
    public Integer getEditionType() {
        return editionType;
    }

    /** 设置 不同服务版本类型区分：0.web 1.安卓 2.密集架 */
    public void setEditionType(Integer editionType) {
        this.editionType = editionType;
    }

    /** 获取 版本创建者 */
    public Integer getEditionCreator() {
        return editionCreator;
    }

    /** 设置 版本创建者 */
    public void setEditionCreator(Integer editionCreator) {
        this.editionCreator = editionCreator;
    }

    /** 获取 版本创建时间 */
    public Date getEditionCreatetime() {
        return editionCreatetime;
    }

    /** 设置 版本创建时间 */
    public void setEditionCreatetime(Date editionCreatetime) {
        this.editionCreatetime = editionCreatetime;
    }

    /** 获取 版本更新者 */
    public Integer getEditionUpdater() {
        return editionUpdater;
    }

    /** 设置 版本更新者 */
    public void setEditionUpdater(Integer editionUpdater) {
        this.editionUpdater = editionUpdater;
    }

    /** 获取 版本更新时间 */
    public Date getEditionUpdatetime() {
        return editionUpdatetime;
    }

    /** 设置 版本更新时间 */
    public void setEditionUpdatetime(Date editionUpdatetime) {
        this.editionUpdatetime = editionUpdatetime;
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
        sb.append("EditionInfo");
        sb.append("{editionId=").append(editionId);
        sb.append(", editionNumber=").append(editionNumber);
        sb.append(", editionName=").append(editionName);
        sb.append(", editionUrl=").append(editionUrl);
        sb.append(", editionType=").append(editionType);
        sb.append(", editionCreator=").append(editionCreator);
        sb.append(", editionCreatetime=").append(editionCreatetime);
        sb.append(", editionUpdater=").append(editionUpdater);
        sb.append(", editionUpdatetime=").append(editionUpdatetime);
        sb.append(", remark=").append(remark);
        sb.append(", isPublish=").append(isPublish);
        sb.append(", sunType=").append(sunType);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EditionInfo) {
            EditionInfo editionInfo = (EditionInfo) obj;
            if (this.getEditionId().equals(editionInfo.getEditionId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getEditionId();
        return pkStr.hashCode();
    }

}