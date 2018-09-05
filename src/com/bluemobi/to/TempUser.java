package com.bluemobi.to;

import com.appcore.model.AbstractObject;

public class TempUser  extends AbstractObject{
    private static final long serialVersionUID = 1L;
    //用户手机号
    private String phone;
    //用户手机验证码
    private String smsCode;
    //创建时间(毫秒数)
    private long ctime; 
    //最后过期时间(毫秒数)
    private long ptime;
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getSmsCode() {
        return smsCode;
    }
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
    public long getCtime() {
        return ctime;
    }
    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
    public long getPtime() {
        return ptime;
    }
    public void setPtime(long ptime) {
        this.ptime = ptime;
    }
    
}
