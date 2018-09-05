package com.bluemobi.po.device;


import com.appcore.model.AbstractObject;

/**
 * 【设备分类绑定品牌表】持久化对象 数据库表：device_category_brand
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public class DeviceCategoryBrand extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer categoryBrandId;
    // 设备分类id
    private Integer categoryId;
    // 设备品牌id
    private Integer brandId;

    /** 获取 主键ID */
    public Integer getCategoryBrandId() {
        return categoryBrandId;
    }

    /** 设置 主键ID */
    public void setCategoryBrandId(Integer categoryBrandId) {
        this.categoryBrandId = categoryBrandId;
    }

    /** 获取 设备分类id */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 设备分类id */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 设备品牌id */
    public Integer getBrandId() {
        return brandId;
    }

    /** 设置 设备品牌id */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceCategoryBrand");
        sb.append("{categoryBrandId=").append(categoryBrandId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", brandId=").append(brandId);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DeviceCategoryBrand) {
            DeviceCategoryBrand deviceCategoryBrand = (DeviceCategoryBrand) obj;
            if (this.getCategoryBrandId().equals(deviceCategoryBrand.getCategoryBrandId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getCategoryBrandId();
        return pkStr.hashCode();
    }

}