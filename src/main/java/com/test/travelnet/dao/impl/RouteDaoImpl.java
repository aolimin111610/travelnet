package com.test.travelnet.dao.impl;

import com.test.travelnet.dao.RouteDao;
import com.test.travelnet.domain.Route;
import com.test.travelnet.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/20 22:12
 * @Version V1.0
 */
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template= new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid) {
        String sql ="select count(*) form tab_route where cid = ?";
        return template.queryForObject(sql,Integer.class,cid);
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize) {
        String sql = "select  * from tab_route where cid = ? limit ?,?";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),cid,start,pageSize);
    }
}
