package com.bluemobi.po.system;


import com.appcore.model.AbstractObject;

/**
 * 【银行基础数据，银行编号，银行名称】持久化对象 数据库表：system_bank
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-10
 * 
 */
public class SystemBank extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键
    private Integer id;
    // 父级编号
    private String pno;
    // 银行编号
    private String bankNo;
    // 银行名称
    private String bankName;
    // 银行级别：1、主行 2、分行
    private Integer grade;
    // 状态：0、不可用 1、可用
    private Integer status;

    /** 获取 主键 */
    public Integer getId() {
        return id;
    }

    /** 设置 主键 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 父级编号 */
    public String getPno() {
        return pno;
    }

    /** 设置 父级编号 */
    public void setPno(String pno) {
        this.pno = pno;
    }

    /** 获取 银行编号 */
    public String getBankNo() {
        return bankNo;
    }

    /** 设置 银行编号 */
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    /** 获取 银行名称 */
    public String getBankName() {
        return bankName;
    }

    /** 设置 银行名称 */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /** 获取 银行级别：1、主行 2、分行 */
    public Integer getGrade() {
        return grade;
    }

    /** 设置 银行级别：1、主行 2、分行 */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /** 获取 状态：0、不可用 1、可用 */
    public Integer getStatus() {
        return status;
    }

    /** 设置 状态：0、不可用 1、可用 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SystemBank");
        sb.append("{id=").append(id);
        sb.append(", pno=").append(pno);
        sb.append(", bankNo=").append(bankNo);
        sb.append(", bankName=").append(bankName);
        sb.append(", grade=").append(grade);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SystemBank) {
            SystemBank systemBank = (SystemBank) obj;
            if (this.getId().equals(systemBank.getId())) {
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