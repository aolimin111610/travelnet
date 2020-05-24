package com.test.travelnet.dao.impl;

import com.test.travelnet.dao.SellerDao;
import com.test.travelnet.domain.Seller;
import com.test.travelnet.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/24 20:02
 * @Version V1.0
 */
public class SerllerDaoImpl implements SellerDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findBySid(int sid) {
        String sql = "select * from tab_seller where sid = ?";

        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),sid);
    }
}
