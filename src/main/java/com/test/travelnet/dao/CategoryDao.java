package com.test.travelnet.dao;

import com.test.travelnet.domain.Category;

import java.util.List;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/17 16:37
 * @Version V1.0
 */
public interface CategoryDao {
    /**
     * 查询导航栏信息
     * @return
     */
    public List<Category> findAll();
}
