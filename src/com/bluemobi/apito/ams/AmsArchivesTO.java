package com.bluemobi.apito.ams;

import com.appcore.model.AbstractObject;

/**
 * 档案数据接口返回实体
 * @author Riven
 *
 */
public class AmsArchivesTO extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer archivesId;

    // 档案类型id
    private String archivesTypeId;
    // 档案类型名称
    private String archivesTypeName;
    // 正题名
    private String title;
    // 存址
    private String storeplace;
    // 盘点状态
    private Integer checkStatus;
    // 在库状态 在库状态 0:在库 1.出库		
    private Integer inflag;
    // 所属库房ID
    private Integer stroreId;
    // 所属库房ID
    private Integer stroreAreaId;
    // 所属库房ID
    private String stroreColumn;
    public String getStroreRl() {
		return stroreRl;
	}
	public void setStroreRl(String stroreRl) {
		this.stroreRl = stroreRl;
	}
	// 所属库房ID
    private String stroreRl;
    //
	public Integer getArchivesId() {
		return archivesId;
	}
	public Integer getStroreAreaId() {
		return stroreAreaId;
	}
	public void setStroreAreaId(Integer stroreAreaId) {
		this.stroreAreaId = stroreAreaId;
	}
	public String getStroreColumn() {
		return stroreColumn;
	}
	public void setStroreColumn(String stroreColumn) {
		this.stroreColumn = stroreColumn;
	}
	public void setArchivesId(Integer archivesId) {
		this.archivesId = archivesId;
	}
	public String getArchivesTypeId() {
		return archivesTypeId;
	}
	public void setArchivesTypeId(String archivesTypeId) {
		this.archivesTypeId = archivesTypeId;
	}
	public String getArchivesTypeName() {
		return archivesTypeName;
	}
	public void setArchivesTypeName(String archivesTypeName) {
		this.archivesTypeName = archivesTypeName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStoreplace() {
		return storeplace;
	}
	public void setStoreplace(String storeplace) {
		this.storeplace = storeplace;
	}
	public Integer getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getInflag() {
		return inflag;
	}
	public void setInflag(Integer inflag) {
		this.inflag = inflag;
	}
	public Integer getStroreId() {
		return stroreId;
	}
	public void setStroreId(Integer stroreId) {
		this.stroreId = stroreId;
	}
}