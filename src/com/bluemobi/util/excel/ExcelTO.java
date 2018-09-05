package com.bluemobi.util.excel;

import java.util.List;

public class ExcelTO {
    
    private List<String> titleName;//title标题列
    private List<String> titleName2;//title标题列2
    private List<String> titleName3;//title标题列3
    private String resultName;//文件名
    private String description;//描述名称
    private List<Object[]> resultList;//遍历数据
    public List<String> getTitleName() {
        return titleName;
    }
    public void setTitleName(List<String> titleName) {
        this.titleName = titleName;
    }
    public String getResultName() {
        return resultName;
    }
    public void setResultName(String resultName) {
        this.resultName = resultName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Object[]> getResultList() {
        return resultList;
    }
    public void setResultList(List<Object[]> resultList) {
        this.resultList = resultList;
    }
    public List<String> getTitleName2() {
        return titleName2;
    }
    public void setTitleName2(List<String> titleName2) {
        this.titleName2 = titleName2;
    }
    public List<String> getTitleName3() {
        return titleName3;
    }
    public void setTitleName3(List<String> titleName3) {
        this.titleName3 = titleName3;
    }
 
}
