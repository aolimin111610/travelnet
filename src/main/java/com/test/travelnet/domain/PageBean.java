package com.test.travelnet.domain;

import java.util.List;

/**
 * @Description 分页对象
 * @Author Alm
 * @Date 2020/5/20 21:35
 * @Version V1.0
 * totalCount  总记录数
 * totalPage   总页数
 * currentPage 当前页码
 * pageSize    每页显示的条数
 * list        每页显示的数据集合
 */
public class PageBean<T> {

    private int totalCount;
    private int totalPage;
    private int currentPage;
    private int pageSize;

    private List<T> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
