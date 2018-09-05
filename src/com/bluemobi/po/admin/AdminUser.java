package com.bluemobi.po.admin;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【管理员用户表】持久化对象 数据库表：admin_user
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
public class AdminUser extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 用户id
    private Integer userId;
    // 用户名
    private String userName;
    // 密码
    private String password;
    // 混淆码
    private String salt;
    // 电话
    private String phone;
    // 邮箱
    private String email;
    // 全名
    private String fullname;
    // 昵称
    private String nickname;
    // 英文名全称
    private String enName;
    // 英文名首字母
    private String initial;
    // 性别：0、未知 1、男 2、女
    private Integer sex;
    // 状态：0、未审核 1、审核通过 2、审核不通过
    private Integer status;
    // 是否在线：0、不在线 1、在线
    private Integer isOnline;
    // 最后登录时间
    private Date loginTime;
    // 创建人
    private Integer creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 修改时间
    private Date mtime;
    // 用户类型：0.系统管理员  1、领导者 2、操作者
    private Integer userType;
    //用户头像
    private String avatar;
    /** 获取 用户id */
    public Integer getUserId() {
        return userId;
    }

    /** 设置 用户id */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 用户名 */
    public String getUserName() {
        return userName;
    }

    /** 设置 用户名 */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 获取 密码 */
    public String getPassword() {
        return password;
    }

    /** 设置 密码 */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 获取 混淆码 */
    public String getSalt() {
        return salt;
    }

    /** 设置 混淆码 */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /** 获取 电话 */
    public String getPhone() {
        return phone;
    }

    /** 设置 电话 */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** 获取 邮箱 */
    public String getEmail() {
        return email;
    }

    /** 设置 邮箱 */
    public void setEmail(String email) {
        this.email = email;
    }

    /** 获取 全名 */
    public String getFullname() {
        return fullname;
    }

    /** 设置 全名 */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /** 获取 昵称 */
    public String getNickname() {
        return nickname;
    }

    /** 设置 昵称 */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /** 获取 英文名全称 */
    public String getEnName() {
        return enName;
    }

    /** 设置 英文名全称 */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /** 获取 英文名首字母 */
    public String getInitial() {
        return initial;
    }

    /** 设置 英文名首字母 */
    public void setInitial(String initial) {
        this.initial = initial;
    }

    /** 获取 性别：0、未知 1、男 2、女 */
    public Integer getSex() {
        return sex;
    }

    /** 设置 性别：0、未知 1、男 2、女 */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /** 获取 状态：0、未审核 1、审核通过 2、审核不通过 */
    public Integer getStatus() {
        return status;
    }

    /** 设置 状态：0、未审核 1、审核通过 2、审核不通过 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** 获取 是否在线：0、不在线 1、在线 */
    public Integer getIsOnline() {
        return isOnline;
    }

    /** 设置 是否在线：0、不在线 1、在线 */
    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    /** 获取 最后登录时间 */
    public Date getLoginTime() {
        return loginTime;
    }

    /** 设置 最后登录时间 */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
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

    /** 获取 修改时间 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 修改时间 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    /** 获取 用户类型：1、平台用户 2、商户 */
    public Integer getUserType() {
        return userType;
    }

    /** 设置 用户类型：1、平台用户 2、商户 */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdminUser");
        sb.append("{userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", fullname=").append(fullname);
        sb.append(", nickname=").append(nickname);
        sb.append(", enName=").append(enName);
        sb.append(", initial=").append(initial);
        sb.append(", sex=").append(sex);
        sb.append(", status=").append(status);
        sb.append(", isOnline=").append(isOnline);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", creator=").append(creator);
        sb.append(", ctime=").append(ctime);
        sb.append(", modifier=").append(modifier);
        sb.append(", mtime=").append(mtime);
        sb.append(", userType=").append(userType);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdminUser) {
            AdminUser adminUser = (AdminUser) obj;
            if (this.getUserId().equals(adminUser.getUserId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getUserId();
        return pkStr.hashCode();
    }

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}