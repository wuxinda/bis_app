package com.bluemobi.controller.web.core.impl;

import com.bluemobi.controller.web.core.Response;

/** 
* @ClassName: PageResponse 
* @Description: 带分页返回结果实现类 
* @author chenb
* @date 2015年3月6日 下午2:04:43 
* @param <T> 
*/
public class PageResponse<T> implements Response<T> {

    private String code;
    private String message;
    private T result;
    private int begin = 0;
    private int count = PAGE_SIZE;
    private long countData;
    private int totalPage;
    private int page;//第几页
    private int start;//数据分页起始值
    private int end;//数据分页结束值
    private int pageStart;//页码循环开始
    private int pageEnd;//页码循环结束
    private int currentPage;//当前页

    public PageResponse() {
        this.code = Response.SUCCESS;
    }

    public PageResponse(Integer begin, Integer count) {
        if (count == null || count < 1) {
            count = PAGE_SIZE;
        }
        if (begin == null || begin <=0) {
            begin = 1;
        }
        this.begin = count*(begin-1);
        this.currentPage = begin;
        this.count = count;
        this.code = Response.SUCCESS;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public T getResult() {
        calcStartAndEnd();
        return result;
    }

    public void setResult(T obj) {
        result = obj;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPage() {
        totalPage = (int) ((this.countData + this.count - 1) / this.count);
        return totalPage;
    }

    public long getCountData() {
        //pageSum
        int pageSum = (int) (this.countData / this.count);
        if (this.countData % this.count > 0) {
            pageSum++;
        }
        //start&end
        if (pageSum > 5) {
            if (this.page > 3) {
                if (this.page + 2 > pageSum) {
                    this.end = pageSum;
                } else {
                    this.end = this.page + 2;
                }
                this.start = this.end - 5;
            } else {
                this.start = 1;
                this.end = 5;
            }
        } else {
            this.start = 1;
            this.end = pageSum;
        }
        return countData;
    }

    public void setCountData(long countData) {
    	//完成初始化动作
        this.countData = countData;
        //总页数
        this.totalPage = (int) ((this.countData + this.count - 1) / this.count);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
    //计算缺省分页个数
    private void calcStartAndEnd() {
        int pageCount = getTotalPage();
        int page = this.currentPage + 1;
        if (pageCount > 5) {
            if (page < 4) {
                this.pageStart = 1;
            } else {
                this.pageStart = page - 2;
            }
            if (2 > (pageCount - page)) {
                this.pageEnd = pageCount;
            } else {
                this.pageEnd = page + 2;
            }
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
