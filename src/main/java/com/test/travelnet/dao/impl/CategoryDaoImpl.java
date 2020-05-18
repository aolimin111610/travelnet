package com.test.travelnet.dao.impl;

import com.test.travelnet.dao.CategoryDao;
import com.test.travelnet.domain.Category;
import com.test.travelnet.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/17 16:39
 * @Version V1.0
 */
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category ";
        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
}
