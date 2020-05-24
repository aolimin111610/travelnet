package com.test.travelnet.dao.impl;

import com.test.travelnet.dao.RouteDao;
import com.test.travelnet.domain.Route;
import com.test.travelnet.domain.RouteImg;
import com.test.travelnet.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
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
    public int findTotalCount(int cid,String rname) {
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(cid != 0){
            sb.append( " and cid = ? ");
            //添加？对应的值
            params.add(cid);
        }

        if(rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sql = sb.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize ,String rname) {
        /*String sql = "select * from tab_route where cid = ? limit ? , ? ";*/
        String sql = "select * from tab_route where 1=1";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();
        if(cid !=0 ){
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if(rname != null && rname.length()>0){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ?, ? ");
        sql = sb.toString();

        params.add(start);
        params.add(pageSize);

        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }
}
