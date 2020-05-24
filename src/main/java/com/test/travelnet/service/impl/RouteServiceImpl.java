package com.test.travelnet.service.impl;

import com.test.travelnet.dao.FavoriteDao;
import com.test.travelnet.dao.RouteDao;
import com.test.travelnet.dao.RouteImgDao;
import com.test.travelnet.dao.SellerDao;
import com.test.travelnet.dao.impl.FavoriteDaoImpl;
import com.test.travelnet.dao.impl.RouteDaoImpl;
import com.test.travelnet.dao.impl.RouteImgDaoImpl;
import com.test.travelnet.dao.impl.SerllerDaoImpl;
import com.test.travelnet.domain.PageBean;
import com.test.travelnet.domain.Route;
import com.test.travelnet.domain.RouteImg;
import com.test.travelnet.domain.Seller;
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

    private RouteImgDao routeImgDao = new RouteImgDaoImpl();

    private SellerDao sellerDao = new SerllerDaoImpl();

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        //封装pageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);

        //设置当前页显示的数据集合
        //开始记录数
        int start = (currentPage-1) * pageSize;
        List<Route> list = routeDao.findByPage(cid, start, pageSize, rname);
        pb.setList(list);

        //设置总页数
        int totalPage = totalCount % pageSize ==0 ? totalCount / pageSize : (totalCount / pageSize)+1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public Route findOne(String rid) {

        //根据rid 查询route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));

        //根据route_id查询图片信息集合数据
        List<RouteImg> routeImgs = routeImgDao.findByRouteId(route.getRid());
        route.setRouteImgList(routeImgs);

        //根据rout中的sid查询对应的商家信息
        Seller seller = sellerDao.findBySid(route.getSid());
        route.setSeller(seller);

        //查询用户收藏的次数
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }
}
