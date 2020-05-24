package com.test.travelnet.dao.impl;


import com.test.travelnet.dao.RouteImgDao;
import com.test.travelnet.domain.RouteImg;
import com.test.travelnet.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/24 19:42
 * @Version V1.0
 */
public class RouteImgDaoImpl implements RouteImgDao {
   private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImg> findByRouteId(int id) {
        String sql = "select * from tab_route_img where rid = ? ";

        return template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class),id);
    }
}
