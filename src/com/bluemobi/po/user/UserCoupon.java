package com.bluemobi.po.user;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【用户优惠券表】持久化对象 数据库表：user_coupon
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-09
 * 
 */
public class UserCoupon extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键
    private Integer id;
    // 用户id
    private Integer userId;
    // 优惠券id
    private Integer couponId;
    // 优惠券编码
    private String code;
    // 状态：0、未使用 1、已使用
    private Integer status;
    // 订单号
    private String orderNo;
    // 是否删除：0、未删除 1、删除
    private Integer isDel;
    // 创建时间
    private Date ctime;
    // 使用时间
    private Date utime;

    /** 获取 主键 */
    public Integer getId() {
        return id;
    }

    /** 设置 主键 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 用户id */
    public Integer getUserId() {
        return userId;
    }

    /** 设置 用户id */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 优惠券id */
    public Integer getCouponId() {
        return couponId;
    }

    /** 设置 优惠券id */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /** 获取 优惠券编码 */
    public String getCode() {
        return code;
    }

    /** 设置 优惠券编码 */
    public void setCode(String code) {
        this.code = code;
    }

    /** 获取 状态：0、未使用 1、已使用 */
    public Integer getStatus() {
        return status;
    }

    /** 设置 状态：0、未使用 1、已使用 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** 获取 订单号 */
    public String getOrderNo() {
        return orderNo;
    }

    /** 设置 订单号 */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /** 获取 是否删除：0、未删除 1、删除 */
    public Integer getIsDel() {
        return isDel;
    }

    /** 设置 是否删除：0、未删除 1、删除 */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /** 获取 创建时间 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 使用时间 */
    public Date getUtime() {
        return utime;
    }

    /** 设置 使用时间 */
    public void setUtime(Date utime) {
        this.utime = utime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("UserCoupon");
        sb.append("{id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", couponId=").append(couponId);
        sb.append(", code=").append(code);
        sb.append(", status=").append(status);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", isDel=").append(isDel);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UserCoupon) {
            UserCoupon userCoupon = (UserCoupon) obj;
            if (this.getId().equals(userCoupon.getId())) {
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