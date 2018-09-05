package com.bluemobi.po.system;


import com.appcore.model.AbstractObject;

/**
 * 【请求url，对应的请求名，用于通过url显示用户操作】持久化对象 数据库表：system_url
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-09
 * 
 */
public class SystemUrl extends AbstractObject {	

    public static final long serialVersionUID = 1L;

    // 主键
    private Integer id;
    // 请求url
    private String url;
    // 请求名
    private String name;
    //状态：0.停用 1.在用		
    private String status;
    //url类型 1.后台url 2.接口url 3.web端rul
    private String urlType;

    /** 获取 主键 */
    public Integer getId() {
        return id;
    }

    /** 设置 主键 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 请求url */
    public String getUrl() {
        return url;
    }

    /** 设置 请求url */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /** 获取 请求名 */
    public String getName() {
        return name;
    }

    /** 设置 请求名 */
    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SystemUrl");
        sb.append("{id=").append(id);
        sb.append(", url=").append(url);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SystemUrl) {
            SystemUrl systemUrl = (SystemUrl) obj;
            if (this.getId().equals(systemUrl.getId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getId();
        return pkStr.hashCode();
    }

}