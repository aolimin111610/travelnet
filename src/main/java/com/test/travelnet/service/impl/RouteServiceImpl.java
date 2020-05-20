package com.test.travelnet.service.impl;

import com.test.travelnet.dao.RouteDao;
import com.test.travelnet.dao.impl.RouteDaoImpl;
import com.test.travelnet.domain.PageBean;
import com.test.travelnet.domain.Route;
import com.test.travelnet.service.RouteService;

import java.util.List;

/**
 * @Description
 * @Author Alm
 * @Date 2020/5/20 22:08
 * @Version V1.0
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize) {
        //封装pageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid);
        pb.setTotalCount(totalCount);

        //设置当前页显示的数据集合
        int start = (currentPage-1) * pageSize;//开始记录数
        List<Route> list = routeDao.findByPage(cid, start, pageSize);
        pb.setList(list);

        //设置总页数
        int totalPage = totalCount % pageSize ==0 ? totalCount / pageSize : (totalCount / pageSize)+1;
        pb.setTotalPage(totalPage);

        return pb;
    }
}