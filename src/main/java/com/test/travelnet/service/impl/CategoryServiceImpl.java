package com.test.travelnet.service.impl;

import com.test.travelnet.dao.CategoryDao;
import com.test.travelnet.dao.impl.CategoryDaoImpl;
import com.test.travelnet.domain.Category;
import com.test.travelnet.service.CategoryService;

import java.util.List;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/17 16:45
 * @Version V1.0
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
