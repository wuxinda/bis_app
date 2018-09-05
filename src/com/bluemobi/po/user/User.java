package com.bluemobi.po.user;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【用户表】持久化对象 数据库表：user
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-09
 * 
 */
public class User extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 用户id
    private Integer userId;
    // 证件类型：0、身份证 1、护照 7、组织机构代码
    private Integer certType;
    // 证件号码
    private String certNo;
    // 性别：1、男 2、女 3、其他
    private Integer sex;
    // 是否实名认证：0、未通过验证 1、已通过验证
    private Integer idCheckStatus;
    // 用户名
    private String userName;
    // 密码
    private String password;
    // 混淆码
    private String salt;
    // 真实姓名
    private String realName;
    // 英文名全称
    private String enName;
    // 英文名首字母
    private String initial;
    // 手机号
    private String mobile;
    // 邮箱
    private String email;
    // 联系地址
    private String address;
    // 状态：0、正常 1、锁定 2、冻结 3、未激活
    private Integer userStatus;
    // 当日抽奖次数
    private Integer lotteryCount;
    // 注册时间
    private Date registerTime;
    // 最后登录时间
    private Date loginTime;
    // 环信注册的用户密码
    private String easemobPassword;
    // 经度
    private Double longitude;
    // 纬度
    private Double latitude;

    /** 获取 用户id */
    public Integer getUserId() {
        return userId;
    }

    /** 设置 用户id */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 证件类型：0、身份证 1、护照 7、组织机构代码 */
    public Integer getCertType() {
        return certType;
    }

    /** 设置 证件类型：0、身份证 1、护照 7、组织机构代码 */
    public void setCertType(Integer certType) {
        this.certType = certType;
    }

    /** 获取 证件号码 */
    public String getCertNo() {
        return certNo;
    }

    /** 设置 证件号码 */
    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    /** 获取 性别：1、男 2、女 3、其他 */
    public Integer getSex() {
        return sex;
    }

    /** 设置 性别：1、男 2、女 3、其他 */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /** 获取 是否实名认证：0、未通过验证 1、已通过验证 */
    public Integer getIdCheckStatus() {
        return idCheckStatus;
    }

    /** 设置 是否实名认证：0、未通过验证 1、已通过验证 */
    public void setIdCheckStatus(Integer idCheckStatus) {
        this.idCheckStatus = idCheckStatus;
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

    /** 获取 真实姓名 */
    public String getRealName() {
        return realName;
    }

    /** 设置 真实姓名 */
    public void setRealName(String realName) {
        this.realName = realName;
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

    /** 获取 手机号 */
    public String getMobile() {
        return mobile;
    }

    /** 设置 手机号 */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /** 获取 邮箱 */
    public String getEmail() {
        return email;
    }

    /** 设置 邮箱 */
    public void setEmail(String email) {
        this.email = email;
    }

    /** 获取 联系地址 */
    public String getAddress() {
        return address;
    }

    /** 设置 联系地址 */
    public void setAddress(String address) {
        this.address = address;
    }

    /** 获取 状态：0、正常 1、锁定 2、冻结 3、未激活 */
    public Integer getUserStatus() {
        return userStatus;
    }

    /** 设置 状态：0、正常 1、锁定 2、冻结 3、未激活 */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /** 获取 当日抽奖次数 */
    public Integer getLotteryCount() {
        return lotteryCount;
    }

    /** 设置 当日抽奖次数 */
    public void setLotteryCount(Integer lotteryCount) {
        this.lotteryCount = lotteryCount;
    }

    /** 获取 注册时间 */
    public Date getRegisterTime() {
        return registerTime;
    }

    /** 设置 注册时间 */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /** 获取 最后登录时间 */
    public Date getLoginTime() {
        return loginTime;
    }

    /** 设置 最后登录时间 */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /** 获取 环信注册的用户密码 */
    public String getEasemobPassword() {
        return easemobPassword;
    }

    /** 设置 环信注册的用户密码 */
    public void setEasemobPassword(String easemobPassword) {
        this.easemobPassword = easemobPassword;
    }

    /** 获取 经度 */
    public Double getLongitude() {
        return longitude;
    }

    /** 设置 经度 */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /** 获取 纬度 */
    public Double getLatitude() {
        return latitude;
    }

    /** 设置 纬度 */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("User");
        sb.append("{userId=").append(userId);
        sb.append(", certType=").append(certType);
        sb.append(", certNo=").append(certNo);
        sb.append(", sex=").append(sex);
        sb.append(", idCheckStatus=").append(idCheckStatus);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", realName=").append(realName);
        sb.append(", enName=").append(enName);
        sb.append(", initial=").append(initial);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", address=").append(address);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", lotteryCount=").append(lotteryCount);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", easemobPassword=").append(easemobPassword);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof User) {
            User user = (User) obj;
            if (this.getUserId().equals(user.getUserId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getUserId();
        return pkStr.hashCode();
    }

}