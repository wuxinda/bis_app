package com.bluemobi.apito.cas;

import java.util.Date;

import com.appcore.model.AbstractObject;


/**
 * 用户数据接口返回实体
 * @author Riven
 *
 */
public class CasUserTO extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 用户id
    private Integer userId;
    // 用户名
    private String userName;
    // 电话
    private String phone;
    // 昵称
    private String nickname;
    // 是否在线：0、不在线 1、在线
    private Integer isOnline;
    // 最后登录时间
    private Date loginTime;
    // 用户类型：1、领导者 2、操作者
    private Integer userType;
    //用户头像
    private String avatar;
    //权限库房
    private String enName;
    //权限库房名字
    private String storeName;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}